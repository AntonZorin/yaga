package com.az.yagp.presentation.app

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.az.yagp.R
import com.az.yagp.databinding.ActivityAppBinding
import com.az.yagp.presentation.base.BaseActivity
import com.az.yagp.presentation.ext.provideViewModel

class AppActivity : BaseActivity() {
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel()
        DataBindingUtil.setContentView<ActivityAppBinding>(
            this,
            R.layout.activity_app
        )

        viewModel.setFirstScreen()
    }
}