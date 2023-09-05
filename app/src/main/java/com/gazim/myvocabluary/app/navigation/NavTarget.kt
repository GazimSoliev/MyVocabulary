package com.gazim.myvocabluary.app.navigation

import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize

@Parcelize
sealed class NavTarget: Parcelable {
    data object WordList: NavTarget()
    data object WordAdd: NavTarget()
    data object WordView: NavTarget()
    data object WordTest: NavTarget()
}