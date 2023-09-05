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
import com.gazim.myvocabluary.app.feature.word_add.WordAddScreen
import com.gazim.myvocabluary.app.feature.word_list.WordListScreen
import com.gazim.myvocabluary.app.feature.word_test.WordTestScreen
import com.gazim.myvocabluary.app.feature.word_view.WordViewScreen

class Navigation(
    buildContext: BuildContext, private val backStack: BackStack<NavTarget> = BackStack(
        model = BackStackModel(
            initialTarget = NavTarget.WordList,
            savedStateMap = buildContext.savedStateMap,
        ),
        motionController = { BackStackFader(it) }
    )
) : ParentNode<NavTarget>(
    appyxComponent = backStack,
    buildContext = buildContext
) {
    override fun resolve(interactionTarget: NavTarget, buildContext: BuildContext): Node =
        when (interactionTarget) {
            NavTarget.WordList -> WordListScreen(
                buildContext = buildContext,
                addWordAction = { backStack.push(NavTarget.WordAdd) },
                viewWordAction = { backStack.push(NavTarget.WordView) },
                testWordAction = { backStack.push(NavTarget.WordTest) }
            )

            NavTarget.WordAdd -> WordAddScreen(
                buildContext = buildContext,
                backAction = { backStack.pop() }
            )

            NavTarget.WordView -> WordViewScreen(
                buildContext = buildContext,
                backAction = { backStack.pop() }
            )

            NavTarget.WordTest -> WordTestScreen(
                buildContext = buildContext,
                backAction = { backStack.pop() }
            )
        }

    @Composable
    override fun View(modifier: Modifier) {
        AppyxComponent(appyxComponent = backStack)
    }
}