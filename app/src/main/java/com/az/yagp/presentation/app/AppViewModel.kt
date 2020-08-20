package com.az.yagp.presentation.app

import com.az.yagp.presentation.base.BaseViewModel
import com.az.yagp.presentation.screens.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 *Created by Zorin.A on 20.August.2020.
 */
class AppViewModel @Inject constructor(router: Router) : BaseViewModel(router) {
    fun setFirstScreen() {
        router.navigateTo(Screens.SearchScreen)
    }
}