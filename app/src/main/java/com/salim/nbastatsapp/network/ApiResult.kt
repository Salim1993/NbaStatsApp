package com.salim.nbastatsapp.network

data class ApiResult(val apiStatus: ApiStatus, val code: Int, val message: String) {

    enum class ApiStatus { SUCCESSFUL, ERROR }
}