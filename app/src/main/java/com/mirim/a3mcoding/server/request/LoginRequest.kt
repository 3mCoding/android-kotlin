package com.mirim.a3mcoding.server.request

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("email")
    var userEmail: String?,

    @SerializedName("password")
    var userPw: String?,
) {
}