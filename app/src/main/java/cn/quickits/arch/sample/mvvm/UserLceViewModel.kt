package cn.quickits.arch.sample.mvvm

import android.os.SystemClock
import cn.quickits.arch.mvvm.QLceViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class UserLceViewModel : QLceViewModel<Long>() {

    override fun load(pullToRefresh: Boolean) {
        if (content.value != null) return

        displayLoader(pullToRefresh)

        Flowable.just(SystemClock.elapsedRealtime())
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    content.value = it
                }
    }
}