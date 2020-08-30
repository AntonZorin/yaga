package com.az.yagp.presentation.screens.details.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.az.yagp.R
import com.az.yagp.data.model.UserRepo
import com.az.yagp.databinding.ListItemRepoBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import javax.inject.Inject

/**
 *Created by Zorin.A on 30.August.2020.
 */
class ReposDelegate @Inject constructor() : AdapterDelegate<MutableList<DetailsListMarker>>() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(
            ListItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun isForViewType(items: MutableList<DetailsListMarker>, position: Int): Boolean {
        return items[position] is UserRepo
    }

    override fun onBindViewHolder(
        items: MutableList<DetailsListMarker>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position] as UserRepo)
    }

    private inner class ViewHolder(
        private val binding: ListItemRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserRepo) {
            val ctx = binding.root.context
            with(binding) {
                tvRepoName.text = item.name
                tvForks.text = ctx.getString(R.string.forks_count, item.forksCount.toString())
                tvStars.text = ctx.getString(R.string.stars_count, item.stargazersCount.toString())
            }
        }

    }
}