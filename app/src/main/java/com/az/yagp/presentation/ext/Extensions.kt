package com.az.yagp.presentation.ext

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.az.yagp.presentation.base.BaseActivity
import com.az.yagp.presentation.base.BaseFragment


/**
 * Created by zorin.a on 23.02.18.
 */

fun Any.shortToast(context: Context?) =
    Toast.makeText(context, this.toString(), Toast.LENGTH_SHORT).show()

fun Any.longToast(context: Context?) =
    Toast.makeText(context, this.toString(), Toast.LENGTH_LONG).show()

inline fun <reified T : ViewModel> BaseFragment. provideViewModel(): T =
    ViewModelProviders.of(this, viewModelFactory).get(T::class.java)

inline fun <reified T : ViewModel> BaseActivity.provideViewModel(): T =
    ViewModelProviders.of(this, viewModelFactory).get(T::class.java)


fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) VISIBLE else GONE
}

fun Activity?.hideKeyboard() =
    this?.currentFocus?.run {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

fun Activity?.showKeyboard() = this?.currentFocus?.run {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.showSoftInput(currentFocus, 0)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
