package com.az.yagp.presentation.screens.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.az.yagp.databinding.FragmentSearchBinding
import com.az.yagp.presentation.base.BaseFragment
import com.az.yagp.presentation.ext.getViewModelOfType
import com.az.yagp.presentation.screens.search.list.adapter.SearchAdapter
import com.az.yagp.presentation.view.StateView
import javax.inject.Inject

/**
 *Created by Zorin.A on 20.August.2020.
 */
class SearchFragment : BaseFragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

    @Inject
    lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).getViewModelOfType()
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
            etInput.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(editable: Editable?) {
                    viewModel.performSearch(editable.toString())
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
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