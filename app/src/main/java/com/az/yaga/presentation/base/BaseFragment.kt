package com.az.yaga.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.az.yaga.R
import com.az.yaga.presentation.common.ResponseState
import com.az.yaga.presentation.common.ViewModelFactory
import com.az.yaga.presentation.view.StateView
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 *Created by Zorin.A on 20.August.2020.
 */
abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected var disposable = CompositeDisposable()

    private var stateView: StateView? = null

    protected var progressObserver = Observer<ResponseState> { state ->
        stateView?.setState(state!!)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stateView = view?.findViewById(R.id.state_view)
        initViews()
    }


    abstract fun initViews()

}