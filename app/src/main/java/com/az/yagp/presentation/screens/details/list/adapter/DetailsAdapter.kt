package com.az.yagp.presentation.screens.details.list.adapter

import com.az.yagp.presentation.screens.details.list.DetailsListMarker
import com.az.yagp.presentation.screens.details.list.ReposDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import javax.inject.Inject

/**
 *Created by Zorin.A on 30.August.2020.
 */
class DetailsAdapter @Inject constructor(
    reposDelegate: ReposDelegate
) : ListDelegationAdapter<MutableList<DetailsListMarker>>() {

    init {
        items = mutableListOf()
        delegatesManager.addDelegate(reposDelegate)
    }

    fun swapData(data: List<DetailsListMarker>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}

