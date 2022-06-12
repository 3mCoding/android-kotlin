package com.mirim.a3mcoding.model

import com.google.gson.annotations.SerializedName

class StageProblem(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("no")
    val no: Int?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("title")
    val title : String?,

    @SerializedName("question")
    val question : String?,

    @SerializedName("print")
    val print : String?,

    @SerializedName("code")
    val code: String?,

    @SerializedName("answer_count")
    val answer_count: Int?,

    @SerializedName("answers")
    val answers: String?,

    @SerializedName("desc_path")
    val desc_path : String?,

    @SerializedName("createdAt")
    val createdAt: String?,

    @SerializedName("updatedAt")
    val updatedAt: String?
) {
}