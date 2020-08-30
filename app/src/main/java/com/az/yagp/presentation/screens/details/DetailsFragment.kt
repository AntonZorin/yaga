package com.az.yagp.presentation.screens.details

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.az.yagp.R
import com.az.yagp.data.model.UserDetails
import com.az.yagp.databinding.FragmentDetailsBinding
import com.az.yagp.presentation.base.BaseFragment
import com.az.yagp.presentation.ext.provideViewModel
import com.az.yagp.presentation.mapper.DateMapper
import com.az.yagp.presentation.screens.details.list.adapter.DetailsAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber
import javax.inject.Inject

/**
 *Created by Zorin.A on 25.August.2020.
 */
class DetailsFragment : BaseFragment() {
    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding

    @Inject
    lateinit var dateMapper: DateMapper

    @Inject
    lateinit var detailsAdapter: DetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun initViews() {
        parseArguments()
        setupControls()
        setupList()
        subscribeToViewModel()
        viewModel.queryUser()
    }

    override fun onDestroy() {
        binding.rvRepos.adapter = null
        super.onDestroy()
    }

    private fun setupControls() {
        binding.let {
            it.etInput.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    viewModel.filterRepos(p0.toString())
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })
        }
    }

    private fun setupList() {
        binding.rvRepos.adapter = detailsAdapter
    }

    private fun subscribeToViewModel() {
        viewModel.resultLiveData.observe(viewLifecycleOwner, Observer {
            setData(it)
        })
        viewModel.getLoadingLiveData().observe(viewLifecycleOwner, Observer {
            binding.stateView.setState(it)
        })
        viewModel.reposLiveData.observe(viewLifecycleOwner, Observer {
            Timber.d("SIZE: ${it.size}")
            detailsAdapter.swapData(it)
        })
    }

    private fun setData(it: UserDetails) {
        binding.run {
            Glide.with(requireContext())
                .load(it.avatarUrl)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_foreground))
                .into(ivAvatar)
            tvUserName.text = it.name
            tvUserEmail.text = it.email
            tvUserLocation.text = it.location
            tvUserJoinDate.text = dateMapper.map(it.createdAt)
            tvUserFollowers.text = it.followers.toString()
            tvUserFollowings.text = it.following.toString()
            tvUserBio.text = it.bio
        }
    }

    private fun parseArguments() {
        arguments?.let {
            viewModel.userName = it.getString(KEY_USERNAME)
        }
    }

    companion object {
        const val KEY_USERNAME = "key_username"
        fun newInstance(userName: String): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(
                    KEY_USERNAME to userName
                )
            }
        }
    }
}