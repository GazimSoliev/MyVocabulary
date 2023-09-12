package com.gazim.myvocabluary.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.pop
import com.bumble.appyx.components.backstack.operation.push
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.navigation.composable.AppyxComponent
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import com.bumble.appyx.navigation.node.ParentNode
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsScreen
import com.gazim.myvocabluary.app.feature.word_add.WordAddScreen
import com.gazim.myvocabluary.app.feature.word_list.WordListScreen
import com.gazim.myvocabluary.app.feature.word_test.WordTestScreen
import com.gazim.myvocabluary.app.feature.word_view.WordViewScreen
import com.gazim.myvocabluary.app.navigation.NavTarget.ImportWords
import com.gazim.myvocabluary.app.navigation.NavTarget.WordAdd
import com.gazim.myvocabluary.app.navigation.NavTarget.WordList
import com.gazim.myvocabluary.app.navigation.NavTarget.WordTest
import com.gazim.myvocabluary.app.navigation.NavTarget.WordView

class Navigation(
    buildContext: BuildContext,
    private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = WordList,
            savedStateMap = buildContext.savedStateMap,
        ),
        motionController = { BackStackFader(it) },
    ),
) : ParentNode<NavTarget>(
    appyxComponent = backStack,
    buildContext = buildContext,
) {
    override fun resolve(interactionTarget: NavTarget, buildContext: BuildContext): Node =
        when (interactionTarget) {
            WordList -> WordListScreen(
                buildContext = buildContext,
                addWordAction = { backStack.push(WordAdd) },
                viewWordAction = { backStack.push(WordView) },
                testWordAction = { backStack.push(WordTest) },
                importWordsAction = { backStack.push(ImportWords) }
            )

            WordAdd -> WordAddScreen(
                buildContext = buildContext,
                backAction = { backStack.pop() },
            )

            WordView -> WordViewScreen(
                buildContext = buildContext,
                backAction = { backStack.pop() },
            )

            WordTest -> WordTestScreen(
                buildContext = buildContext,
                backAction = { backStack.pop() },
            )

            ImportWords -> ImportWordsScreen(
                buildContext = buildContext,
                backAction = { backStack.pop() },
            )
        }

    @Composable
    override fun View(modifier: Modifier) {
        AppyxComponent(appyxComponent = backStack)
    }
}
