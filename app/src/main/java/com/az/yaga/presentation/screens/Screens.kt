package com.az.yaga.presentation.screens

import androidx.fragment.app.Fragment
import com.az.yaga.presentation.screens.search.SearchFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 *Created by Zorin.A on 20.August.2020.
 */
object Screens {
    object SearchScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = SearchFragment.newInstance()
    }
}