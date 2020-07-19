package cn.quickits.talos.app.lce.animator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import cn.quickits.talos.app.lce.R

class MaterialLceAnimator : LceAnimator {

    override fun showLoading(loadingView: View, contentView: View, errorView: View) {
        contentView.visibility = View.GONE
        errorView.visibility = View.GONE
        loadingView.visibility = View.VISIBLE
    }

    override fun showErrorView(loadingView: View, contentView: View, errorView: View) {
        contentView.visibility = View.GONE

        val resources = loadingView.resources

        val set = AnimatorSet()
        val errorFadeIn = ObjectAnimator.ofFloat(errorView, View.ALPHA, 1f)
        val loadingFadeOut = ObjectAnimator.ofFloat(loadingView, View.ALPHA, 0f)

        set.playTogether(errorFadeIn, loadingFadeOut)
        set.duration = resources.getInteger(R.integer.lce_error_view_show_animation_time).toLong()

        set.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                errorView.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                loadingView.visibility = View.GONE
                loadingView.alpha = 1f
            }
        })

        set.start()
    }

    override fun showContent(loadingView: View, contentView: View, errorView: View) {
        if (contentView.visibility == View.VISIBLE) {
            errorView.visibility = View.GONE
            loadingView.visibility = View.GONE
        } else {
            errorView.visibility = View.GONE

            val resources = loadingView.resources
            val translateInPixels = resources.getDimensionPixelSize(R.dimen.lce_content_view_animation_translate_y).toFloat()

            val set = AnimatorSet()
            val contentFadeIn = ObjectAnimator.ofFloat(contentView, View.ALPHA, 0f, 1f)
            val contentTranslateIn = ObjectAnimator.ofFloat<View>(contentView, View.TRANSLATION_Y, translateInPixels, 0f)

            val loadingFadeOut = ObjectAnimator.ofFloat(loadingView, View.ALPHA, 1f, 0f)
            val loadingTranslateOut = ObjectAnimator.ofFloat<View>(loadingView, View.TRANSLATION_Y, 0f, -translateInPixels)

            set.playTogether(contentFadeIn, contentTranslateIn, loadingFadeOut, loadingTranslateOut)
            set.duration = resources.getInteger(R.integer.lce_content_view_show_animation_time).toLong()

            set.addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationStart(animation: Animator) {
                    contentView.translationY = 0f
                    loadingView.translationY = 0f
                    contentView.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: Animator) {
                    loadingView.visibility = View.GONE
                    loadingView.alpha = 1f
                    contentView.translationY = 0f
                    loadingView.translationY = 0f
                }
            })

            set.start()
        }
    }
}