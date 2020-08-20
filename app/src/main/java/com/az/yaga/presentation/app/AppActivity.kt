package com.az.yaga.presentation.app

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.az.yaga.R
import com.az.yaga.databinding.ActivityAppBinding
import com.az.yaga.presentation.base.BaseActivity
import com.az.yaga.presentation.ext.getViewModelOfType

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