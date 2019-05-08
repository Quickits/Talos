package cn.quickits.arch.mvvm

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import cn.quickits.arch.mvvm.base.BaseFragment

abstract class QVMFragment<VM : ViewModel> : BaseFragment() {

    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
        createObserve()
    }

    abstract fun createObserve()

    abstract fun initView(view: View)

    abstract fun createViewModel(): VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }
}
