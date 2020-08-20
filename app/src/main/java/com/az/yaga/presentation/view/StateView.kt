package com.az.yaga.presentation.view

import android.content.Context
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayoutgt
import com.az.yaga.R
import com.az.yaga.databinding.StateBinding
import com.az.yaga.presentation.common.ResponseState
import com.az.yaga.presentation.ext.setVisible
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign

class StateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val binding = StateBinding.inflate(LayoutInflater.from(context), this, true)
    var listener: OnRetryListener? = null
    private val disposable: CompositeDisposable = CompositeDisposable()
    var networkErrorMessage: String? = null

    //region override
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        disposable += RxView.clicks(binding.retry).subscribe({
            listener?.onRetryClicked()
        }, {})
    }

    override fun onDetachedFromWindow() {
        disposable.dispose()
        super.onDetachedFromWindow()
    }
    //endregion

    //region fun
    fun setMessageText(text: String) {
        binding.message.text = text
    }

    fun setState(rs: ResponseState) {
        when (rs.state) {
            ResponseState.State.IDLE -> {
                switchVisibility(false, false, false)
            }
            ResponseState.State.LOADING -> {
                switchVisibility(true, false, false)
            }
            ResponseState.State.EMPTY -> {
                switchVisibility(false, true, true)
                binding.message.text = context.getString(R.string.empty_data)
            }
            ResponseState.State.ERROR -> {
                switchVisibility(false, true, true)
                binding.message.text = context.getString(R.string.error)
            }
            ResponseState.State.NETWORK_ERROR -> {
                switchVisibility(false, true, true)
                binding.message.text =
                    networkErrorMessage ?: context.getString(R.string.network_error)
            }
        }
    }

    private fun switchVisibility(
        isProgressShow: Boolean,
        isRetryShow: Boolean,
        isMessageShow: Boolean
    ) {
        binding.run {
            TransitionManager.beginDelayedTransition(root)
            progress.setVisible(isProgressShow)
            retry.setVisible(isRetryShow)
            message.setVisible(isMessageShow)
        }
    }

    //endregion

    interface OnRetryListener {
        fun onRetryClicked()
    }
}