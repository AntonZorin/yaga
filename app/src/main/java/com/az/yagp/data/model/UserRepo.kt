package com.az.yagp.data.model

import com.az.yagp.presentation.screens.details.list.DetailsListMarker
import com.google.gson.annotations.SerializedName


/**
 *Created by Zorin.A on 29.August.2020.
 */
data class UserRepo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("private")
    val isPrivate: Boolean,
    @SerializedName("size")
    val size: Int,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("language")
    val language: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("pushed_at")
    val pushedAt: String
) : DetailsListMarker