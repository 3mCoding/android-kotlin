package com.mirim.a3mcoding.server.request

import com.google.gson.annotations.SerializedName

class LevelSolveRequest(
    @SerializedName("email")
    val email: String?,

    @SerializedName("id")
    val id: Int?
) {
}