package cn.quickits.arch.sample.mvvm

import android.os.SystemClock
import cn.quickits.arch.mvvm.QLceViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class UserLceViewModel : QLceViewModel<Long>() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    fun load(pullToRefresh: Boolean) {
        if (content.value != null) return

        displayLoader(pullToRefresh)

        val disposable = Flowable.just(SystemClock.elapsedRealtime())
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    content.value = it
                }

        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}