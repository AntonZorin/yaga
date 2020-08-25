package com.az.yagp.presentation.screens.details

import android.os.Bundle
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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 *Created by Zorin.A on 25.August.2020.
 */
class DetailsFragment : BaseFragment() {
    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding
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
        viewModel.queryUser()
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.resultLiveData.observe(viewLifecycleOwner, Observer {
            setData(it)
        })
        viewModel.getLoadingLiveData().observe(viewLifecycleOwner, Observer {
            binding.stateView.setState(it)
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
            tvUserJoinDate.text = it.createdAt
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