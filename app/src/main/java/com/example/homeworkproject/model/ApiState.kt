package com.example.homeworkproject.model

class ApiState<T>(status: Status?) {
    enum class Status {
        SUCCESS, ERROR, LOADING, BLANK
    }

    companion object {
        var status: Status? = null
        fun <T> success(): ApiState<T> {
            return ApiState(Status.SUCCESS)
        }

        fun <T> error(): ApiState<T> {
            return ApiState(Status.ERROR)
        }

        fun <T> loading(): ApiState<T> {
            return ApiState(Status.LOADING)
        }

        fun <T> blank(): ApiState<T> {
            return ApiState(Status.BLANK)
        }
    }

    init {
        Companion.status = status
    }
}
