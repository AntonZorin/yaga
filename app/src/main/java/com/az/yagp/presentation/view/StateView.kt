package com.az.yagp.presentation.view

import android.content.Context
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.az.yagp.R
import com.az.yagp.databinding.StateBinding
import com.az.yagp.presentation.common.ViewState
import com.az.yagp.presentation.ext.setVisible

class StateView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    val binding = StateBinding.inflate(LayoutInflater.from(context), this, true)
    var listener: OnRetryListener? = null
    var networkErrorMessage: String? = null

    //region override
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        binding.retry.setOnClickListener {
            listener?.onRetryClicked()
        }
    }
    //endregion

    //region fun
    fun setMessageText(text: String) {
        binding.message.text = text
    }

    fun setState(rs: ViewState) {
        when (rs.state) {
            ViewState.State.IDLE -> {
                switchVisibility(false, false, false)
            }
            ViewState.State.LOADING -> {
                switchVisibility(true, false, false)
            }
            ViewState.State.EMPTY -> {
                switchVisibility(false, true, true)
                binding.message.text = context.getString(R.string.empty_data)
            }
            ViewState.State.ERROR -> {
                switchVisibility(false, true, true)
                binding.message.text = context.getString(R.string.error)
            }
            ViewState.State.NETWORK_ERROR -> {
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