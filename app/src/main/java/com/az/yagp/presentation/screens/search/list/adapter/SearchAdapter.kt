package com.az.yagp.presentation.screens.search.list.adapter

import com.az.yagp.data.model.User
import com.az.yagp.presentation.screens.search.list.SearchAdapterUserDelegate
import com.az.yagp.presentation.screens.search.list.SearchListMarker
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import javax.inject.Inject

/**
 *Created by Zorin.A on 21.August.2020.
 */
class SearchAdapter @Inject constructor(
    private val searchAdapterUserDelegate: SearchAdapterUserDelegate
) : ListDelegationAdapter<MutableList<SearchListMarker>>() {
    var clickCallback: ((user: User) -> Unit)? = null

    init {
        items = mutableListOf()
        searchAdapterUserDelegate.clickCallback = clickCallback
        delegatesManager.addDelegate(searchAdapterUserDelegate)
    }

    fun swapData(data: List<SearchListMarker>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}