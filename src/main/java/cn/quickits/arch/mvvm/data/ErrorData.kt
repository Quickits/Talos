package cn.quickits.arch.mvvm.data

data class ErrorData(val e: Throwable,
                     val pullToRefresh: Boolean,
                     val eIcon: Int = -1)