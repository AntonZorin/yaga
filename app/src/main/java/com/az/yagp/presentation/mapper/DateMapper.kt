package com.az.yagp.presentation.mapper

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 *Created by Zorin.A on 29.August.2020.
 */
class DateMapper @Inject constructor() : Mapper<String, String> {
    @SuppressLint("SimpleDateFormat")
    override fun map(from: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date = sdf.parse(from)
        return SimpleDateFormat("dd.MM.yyyy").format(date ?: Date())
    }
}