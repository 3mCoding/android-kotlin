package com.mirim.a3mcoding.server.response

import com.google.gson.annotations.SerializedName
import com.mirim.a3mcoding.model.User

class JoinResponse(
    @SerializedName("message")
    var message: String,

    @SerializedName("data")
    val data: User?
) {
}