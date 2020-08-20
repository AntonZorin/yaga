package com.az.yaga.presentation.ext

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Created by zorin.a on 23.02.18.
 */

fun Any.shortToast(context: Context?) = Toast.makeText(context, this.toString(), Toast.LENGTH_SHORT).show()

fun Any.longToast(context: Context?) = Toast.makeText(context, this.toString(), Toast.LENGTH_LONG).show()


inline fun <reified T : ViewModel> ViewModelProvider.getViewModelOfType(): T =
        get(T::class.java)


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
