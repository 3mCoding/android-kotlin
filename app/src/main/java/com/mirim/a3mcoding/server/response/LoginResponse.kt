package com.mirim.a3mcoding.server.response

import com.google.gson.annotations.SerializedName
import com.mirim.a3mcoding.model.User

class LoginResponse(
    @SerializedName("result")
    val result: String?,

    @SerializedName("data")
    val data: User?
) {
}