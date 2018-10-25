package cn.quickits.arch.mvvm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cn.quickits.arch.mvvm.data.ErrorData
import cn.quickits.arch.mvvm.livecyle.SingleLiveEventLiveData

abstract class QLceViewModel<M> : ViewModel() {

    val loader: MutableLiveData<Boolean> = SingleLiveEventLiveData()

    val content: MutableLiveData<M> = MutableLiveData()

    val error: MutableLiveData<ErrorData> = SingleLiveEventLiveData()

    fun displayLoader(pullToRefresh: Boolean) {
        loader.value = pullToRefresh
    }

    fun displayError(pullToRefresh: Boolean, e: Throwable) {
        error.value = ErrorData(e, pullToRefresh)
    }

}
