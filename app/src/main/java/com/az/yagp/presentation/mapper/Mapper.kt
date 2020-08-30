package com.az.yagp.presentation.mapper

/**
 *Created by Zorin.A on 29.August.2020.
 */
interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}