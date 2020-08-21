package com.az.yagp.data.model
import com.google.gson.annotations.SerializedName


/**
 *Created by Zorin.A on 21.August.2020.
 */
data class UserSearchResult(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<User>
)

