package com.gazim.myvocabluary.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.bumble.appyx.navigation.integration.NodeComponentActivity
import com.gazim.myvocabluary.app.navigation.Navigation
import com.gazim.myvocabluary.app.theme.MyVocabluaryTheme

class MainActivity : NodeComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyVocabluaryTheme {
                Surface {
                    Navigation()
                }
            }
        }
    }
}
