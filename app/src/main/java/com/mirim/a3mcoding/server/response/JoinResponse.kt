package com.mirim.a3mcoding.server.response

import com.google.gson.annotations.SerializedName

class JoinResponse(
    @SerializedName("code")
    var code: Int,

    @SerializedName("message")
    val message: String?
) {
}