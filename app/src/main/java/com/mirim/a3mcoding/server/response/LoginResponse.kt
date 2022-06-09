package com.mirim.a3mcoding.server.response

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("code")
     var code: Int,

    @SerializedName("message")
     val message: String?,

    @SerializedName("step")
     val step: Int,

    @SerializedName("rank")
     val rank: Int,

    @SerializedName("student_num")
     val student_num: Int,

    @SerializedName("name")
     val name: String?,

    @SerializedName("date")
     val date: String?,
) {
}