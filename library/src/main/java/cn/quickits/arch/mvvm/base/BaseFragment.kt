package cn.quickits.arch.mvvm.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.quickits.arch.mvvm.OnBackPressedHandler
import cn.quickits.arch.mvvm.OnWindowFocusChangedHandler

abstract class BaseFragment : Fragment(), OnBackPressedHandler, OnWindowFocusChangedHandler {

    private var isStopped = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(bindLayout(), container, false)
    }

    abstract fun bindLayout(): Int

    abstract fun pageName(): String

    override fun onStart() {
        if (isStopped) {
            onRestart()
            isStopped = false
        }
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        isStopped = true
    }

    open fun onRestart() {

    }

    fun setSupportActionBar(toolbar: Toolbar?) {
        getAppCompatActivity()?.setSupportActionBar(toolbar)
    }

    fun getAppCompatActivity(): AppCompatActivity? {
        val act = activity
        return act as? AppCompatActivity
    }

    fun getSupportActionBar(): ActionBar? = getAppCompatActivity()?.supportActionBar

    override fun onBackPressed(): Boolean = false

    override fun onWindowFocusChanged(hasFocus: Boolean) {

    }

}