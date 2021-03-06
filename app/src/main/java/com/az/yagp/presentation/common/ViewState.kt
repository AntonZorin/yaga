package com.az.yagp.presentation.common

class ViewState(var state: State, var message: String? = null, var errorCode: Int? = null) {
    enum class State { IDLE, LOADING, EMPTY, ERROR, NETWORK_ERROR }
    companion object {
        const val TEST_ERROR_CODE = 0
    }
}