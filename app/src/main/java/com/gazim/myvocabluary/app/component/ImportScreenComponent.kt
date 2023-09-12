package com.gazim.myvocabluary.app.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gazim.myvocabluary.app.theme.MyVocabluaryTheme
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImportScreenComponent(
    onDateChange: (LocalDate) -> Unit = {},
    onTimeChange: (LocalTime) -> Unit = {},
    textForImport: TextFieldValue = TextFieldValue(""),
    onTextChange: (TextFieldValue) -> Unit = {},
    back: () -> Unit = {},
    import: () -> Unit = {},
) {
    var dateText by remember { mutableStateOf("Select date") }
    var timeText by remember { mutableStateOf("Select time") }
    val timePickerState = rememberTimePickerState()
    val datePickerState = rememberDatePickerState()
    var openDatePickerDialog by remember { mutableStateOf(false) }
    var openTimePickerDialog by remember { mutableStateOf(false) }
    Surface {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Import words")
                    },
                    navigationIcon = {
                        IconButton(onClick = back) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = import) {
                            Icon(Icons.Default.Download, contentDescription = "Download")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    FilledTonalButton(onClick = {
                        openDatePickerDialog = true
                    }) {
                        Text(dateText)
                    }
                    Spacer(Modifier.width(8.dp))
                    FilledTonalButton(onClick = {
                        openTimePickerDialog = true
                    }) {
                        Text(timeText)
                    }
                }
                OutlinedTextField(
                    value = textForImport,
                    onValueChange = onTextChange,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        if (openDatePickerDialog) {
            val confirmEnabled by remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
            DatePickerDialog(
                onDismissRequest = {
                    openDatePickerDialog = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDatePickerDialog = false
                            datePickerState.selectedDateMillis?.let {
                                val selectedLocalTime = Instant.fromEpochMilliseconds(it)
                                    .toLocalDateTime(TimeZone.currentSystemDefault()).date
                                dateText = selectedLocalTime.toString()
                                onDateChange(selectedLocalTime)
                            }
                        },
                        enabled = confirmEnabled
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDatePickerDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
        if (openTimePickerDialog) {
            var timePicker by remember { mutableStateOf(true) }
            TimePickerDialog(
                confirmButton = {
                    TextButton(
                        onClick = {
                            openTimePickerDialog = false
                            val selectedTime = timePickerState.run {
                                LocalTime(hour = hour, minute = minute)
                            }
                            timeText = selectedTime.toString()
                            onTimeChange(selectedTime)
                        },
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openTimePickerDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                },
                onDismissRequest = {
                    openTimePickerDialog = false
                },
                toggleButton = {
                    IconButton(onClick = { timePicker = !timePicker }) {
                        Icon(
                            if (timePicker) Icons.Outlined.Keyboard else Icons.Outlined.Schedule,
                            contentDescription = null
                        )
                    }
                }
            ) {
                if (timePicker) TimePicker(timePickerState)
                else TimeInput(timePickerState)
            }
        }
    }
}

@Preview
@Composable
fun ImportScreenComponentPreview() {
    ImportScreenComponent(
        textForImport = TextFieldValue("Test")
    )
}

@Preview
@Composable
fun ImportScreenComponentPreviewWithTheme() {
    MyVocabluaryTheme {
        ImportScreenComponentPreview()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ImportScreenComponentPreviewWithDarkTheme() {
    ImportScreenComponentPreviewWithTheme()
}

@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit,
    toggleButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            color = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    toggleButton()
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton()
                    confirmButton()
                }
            }
        }
    }
}
