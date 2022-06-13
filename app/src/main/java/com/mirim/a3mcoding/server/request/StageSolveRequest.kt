package com.mirim.a3mcoding.server.request

import com.google.gson.annotations.SerializedName

class StageSolveRequest(
    @SerializedName("email")
    val email: String?
) {
}