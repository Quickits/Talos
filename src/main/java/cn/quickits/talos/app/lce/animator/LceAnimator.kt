package cn.quickits.talos.app.lce.animator

import android.view.View

interface LceAnimator {

    fun showLoading(loadingView: View, contentView: View, errorView: View)

    fun showErrorView(loadingView: View, contentView: View, errorView: View)

    fun showContent(loadingView: View, contentView: View, errorView: View)
}