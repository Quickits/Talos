package cn.quickits.talos.app.lce

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.quickits.talos.app.lce.data.ErrorData
import cn.quickits.talos.livecyle.SingleLiveEventLiveData

abstract class LceViewModel<M> : ViewModel() {

    val loader: MutableLiveData<Boolean> =
        SingleLiveEventLiveData()

    val content: MutableLiveData<M> = MutableLiveData()

    val error: MutableLiveData<ErrorData> =
        SingleLiveEventLiveData()

    fun displayLoader(pullToRefresh: Boolean) {
        loader.value = pullToRefresh
    }

    fun displayError(pullToRefresh: Boolean, e: Throwable) {
        error.value = ErrorData(e, pullToRefresh)
    }

}
