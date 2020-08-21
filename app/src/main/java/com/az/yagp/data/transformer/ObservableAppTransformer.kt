package com.az.yagp.data.transformer

import io.reactivex.ObservableTransformer

/**
 *Created by Zorin.A on 07.August.2019.
 */
abstract class ObservableAppTransformer<T> : ObservableTransformer<T, T>