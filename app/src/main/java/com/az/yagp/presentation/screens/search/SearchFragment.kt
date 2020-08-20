package com.az.yagp.presentation.screens.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.az.yagp.databinding.FragmentSearchBinding
import com.az.yagp.presentation.base.BaseFragment
import com.az.yagp.presentation.ext.getViewModelOfType

/**
 *Created by Zorin.A on 20.August.2020.
 */
class SearchFragment : BaseFragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel

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

    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}