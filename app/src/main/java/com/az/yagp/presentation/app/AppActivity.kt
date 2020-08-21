package com.az.yagp.presentation.app

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.az.yagp.R
import com.az.yagp.databinding.ActivityAppBinding
import com.az.yagp.presentation.base.BaseActivity
import com.az.yagp.presentation.ext.getViewModelOfType

class AppActivity : BaseActivity() {
    lateinit var binding: ActivityAppBinding
    lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).getViewModelOfType()
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_app
        )

        viewModel.setFirstScreen()
    }
}