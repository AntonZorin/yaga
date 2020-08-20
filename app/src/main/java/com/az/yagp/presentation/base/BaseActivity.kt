package com.az.yagp.presentation.base


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.az.yagp.R
import com.az.yagp.presentation.common.ViewModelFactory
import dagger.android.AndroidInjection
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

/**
 *Created by Zorin.A on 20.August.2020.
 */
abstract class BaseActivity : AppCompatActivity() {
    //region var
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigatorHolder: NavigatorHolder


    private val navigator = SupportAppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {

        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
//endregion
