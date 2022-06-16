package com.mirim.a3mcoding.server.response

import com.google.gson.annotations.SerializedName
import com.mirim.a3mcoding.model.User

class JoinResponse(
    @SerializedName("data")
    val data: User?
) {
}