package com.mirim.a3mcoding.model

import com.google.gson.annotations.SerializedName

open class Problem(
    @SerializedName("title")
    open val title: String?,

    @SerializedName("question")
    open val question: String?,

    @SerializedName("print")
    open val print: String?,

    @SerializedName("code")
    open val code: String?,

    @SerializedName("answer_count")
    open val answer_count: Int?,

    @SerializedName("answers")
    open val answers: String?,

    @SerializedName("desc_path")
    open val desc_path: String?,

    @SerializedName("createdAt")
    open val createdAt: String?,

    @SerializedName("updatedAt")
    open val updatedAt: String?
) {
}