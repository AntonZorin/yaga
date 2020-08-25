package com.az.yagp.presentation.screens

import androidx.fragment.app.Fragment
import com.az.yagp.presentation.screens.details.DetailsFragment
import com.az.yagp.presentation.screens.search.SearchFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 *Created by Zorin.A on 20.August.2020.
 */
object Screens {
    object SearchScreen : SupportAppScreen() {
        override fun getFragment(): Fragment = SearchFragment.newInstance()
    }

    data class DetailsScreen(
        val userName: String
    ) : SupportAppScreen() {
        override fun getFragment(): Fragment = DetailsFragment.newInstance(userName)
    }
}