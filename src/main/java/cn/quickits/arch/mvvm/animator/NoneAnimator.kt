package cn.quickits.arch.mvvm.animator

import android.view.View

class NoneAnimator : LceAnimator {

    override fun showLoading(loadingView: View, contentView: View, errorView: View) {
        loadingView.visibility = View.VISIBLE
        contentView.visibility = View.GONE
        errorView.visibility = View.GONE
    }

    override fun showErrorView(loadingView: View, contentView: View, errorView: View) {
        loadingView.visibility = View.GONE
        contentView.visibility = View.GONE
        errorView.visibility = View.VISIBLE
    }

    override fun showContent(loadingView: View, contentView: View, errorView: View) {
        loadingView.visibility = View.GONE
        contentView.visibility = View.VISIBLE
        errorView.visibility = View.GONE
    }
}