package cn.quickits.arch.mvvm

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.view.View
import android.widget.TextView
import android.widget.Toast
import cn.quickits.arch.base.app.BaseFragment
import cn.quickits.arch.mvvm.data.ErrorData
import cn.quickits.arch.mvvm.animator.LceAnimator
import cn.quickits.arch.mvvm.animator.MaterialLceAnimator

abstract class LceFragment<M, VM : LceViewModel<M>, CV : View> : BaseFragment() {

    lateinit var viewModel: VM

    lateinit var loadingView: View
    lateinit var contentView: CV
    lateinit var errorView: TextView

    var lceAnimator: LceAnimator = MaterialLceAnimator()

    abstract fun createViewModel(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingView = createLoadingView(view)
        contentView = createContentView(view)
        errorView = createErrorView(view)

        viewModel.loader.observe(this, Observer { pullToRefresh ->
            showLoading(pullToRefresh)
        })

        viewModel.content.observe(this, Observer { content ->
            showContent(content)
        })

        viewModel.error.observe(this, Observer { errorData ->
            showError(errorData)
        })
    }

    protected open fun createLoadingView(view: View): View = view.findViewById(R.id.loading_view)

    protected open fun createContentView(view: View): CV = view.findViewById(R.id.content_view)

    protected open fun createErrorView(view: View): TextView = view.findViewById(R.id.error_view)

    open fun showLoading(pullToRefresh: Boolean?) {
        pullToRefresh ?: return

        if (!pullToRefresh) {
            animateLoadingViewIn()
        }
    }

    open fun showContent(content: M?) {
        content ?: return

        animateContentViewIn()
    }

    open fun showError(errorData: ErrorData?) {
        errorData ?: return

        val msg: CharSequence = getErrorMessage(errorData)

        if (errorData.pullToRefresh) {
            showToastError(msg)
        } else {
            errorView.text = msg

            if (errorData.eIcon != -1) {
                val drawable = ContextCompat.getDrawable(context!!, errorData.eIcon)
                errorView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    drawable,
                    null,
                    null
                )
            }
            animateErrorViewIn()
        }
    }

    private fun showToastError(msg: CharSequence) {
        if (activity != null) {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }
    }

    protected open fun getErrorMessage(errorData: ErrorData?): CharSequence {
        errorData ?: return ""
        return errorData.e.message ?: ""
    }

    protected open fun animateLoadingViewIn() {
        lceAnimator.showLoading(loadingView, contentView, errorView)
    }

    protected open fun animateContentViewIn() {
        lceAnimator.showContent(loadingView, contentView, errorView)
    }

    protected open fun animateErrorViewIn() {
        lceAnimator.showErrorView(loadingView, contentView, errorView)
    }

}