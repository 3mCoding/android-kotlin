package com.mirim.a3mcoding.server.request

import com.google.gson.annotations.SerializedName

class JoinRequest(
    @SerializedName("student_num")
    var userStudentNum: String?,

    @SerializedName("name")
    val userName: String?,

    @SerializedName("email")
    val userEmail: String?,

    @SerializedName("password")
    val userPw: String?
) {
}