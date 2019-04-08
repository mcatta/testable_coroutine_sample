package eu.marcocattaneo.samplecoroutines.utils

class LiveDataResult<T>(val status: STATUS, val data: T? = null, val error: Throwable? = null) {

    enum class STATUS {
        SUCCESS, LOADING, ERROR
    }

    companion object {
        fun <T> success (data: T) = LiveDataResult<T>(STATUS.SUCCESS, data)
        fun <T> error(err: Throwable) = LiveDataResult<T>(STATUS.ERROR, null, err)
        fun <T> loading() = LiveDataResult<T>(STATUS.LOADING)
    }
}