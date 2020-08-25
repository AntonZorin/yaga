package com.az.yagp.presentation.screens.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.az.yagp.databinding.FragmentSearchBinding
import com.az.yagp.presentation.base.BaseFragment
import com.az.yagp.presentation.ext.provideViewModel
import com.az.yagp.presentation.screens.search.list.adapter.SearchAdapter
import com.az.yagp.presentation.view.StateView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 *Created by Zorin.A on 20.August.2020.
 */
class SearchFragment : BaseFragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private val inputObservable = Observable.create<String> { emitter ->
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                if (!emitter.isDisposed) {
                    emitter.onNext(editable.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        }
        emitter.setCancellable { binding.etInput.removeTextChangedListener(textWatcher) }
        binding.etInput.addTextChangedListener(textWatcher)
    }

    @Inject
    lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun initViews() {
        setupControls()
        setupList()
        subscribeToViewModel()
    }

    private fun setupList() {
        adapter.clickCallback = {
            viewModel.onUserSelected(it)
        }
        binding.rvResults.adapter = adapter
    }

    private fun setupControls() {
        binding.run {
            disposable += inputObservable
                .filter { it.length > 1 }
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result ->
                    viewModel.performSearch(result)
                }
            stateView.listener = object : StateView.OnRetryListener {
                override fun onRetryClicked() {
                    viewModel.retrySearch()
                }
            }
        }
    }

    private fun subscribeToViewModel() {
        viewModel.resultLiveData.observe(viewLifecycleOwner, Observer {
            adapter.swapData(it)
        })
        viewModel.getLoadingLiveData().observe(viewLifecycleOwner, Observer {
            binding.stateView.setState(it)
        })
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}