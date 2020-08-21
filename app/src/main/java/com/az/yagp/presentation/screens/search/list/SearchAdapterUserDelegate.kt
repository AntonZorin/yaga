package com.az.yagp.presentation.screens.search.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.az.yagp.data.model.User
import com.az.yagp.databinding.ListItemUserBinding
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import javax.inject.Inject

/**
 *Created by Zorin.A on 21.August.2020.
 */
class SearchAdapterUserDelegate @Inject constructor() :
    AdapterDelegate<MutableList<SearchListMarker>>() {
    var clickCallback: ((user: User) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun isForViewType(items: MutableList<SearchListMarker>, position: Int): Boolean {
        return items[position] is User
    }

    override fun onBindViewHolder(
        items: MutableList<SearchListMarker>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as ViewHolder).bind(items[position] as User)
    }

    private inner class ViewHolder(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            with(binding) {
                root.setOnClickListener {
                    clickCallback?.invoke(item)
                }
                tvName.text = item.login
                tvReposValue.text = item.id.toString()
                Glide.with(itemView.context)
                    .load(item.avatarUrl)
                    .into(ivAvatar)
            }
        }
    }
}