package cn.quickits.talos.app.lce.data

data class ErrorData(val e: Throwable,
                     val pullToRefresh: Boolean,
                     val eIcon: Int = -1)