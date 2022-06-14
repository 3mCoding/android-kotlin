package com.mirim.a3mcoding.model

import com.google.gson.annotations.SerializedName

class LevelProblem(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("level")
    val level: String?,

    @SerializedName("time")
    val time : Int,

    @SerializedName("title")
    val title: String?,

    @SerializedName("question")
    val question: String?,

    @SerializedName("print")
    val print: String?,

    @SerializedName("code")
    val code: String?,

    @SerializedName("answer_count")
    val answer_count: Int?,

    @SerializedName("answers")
    val answers: String?,

    @SerializedName("solved")
    val solved: Int?,

    @SerializedName("desc_path")
    val desc_path: String?,

    @SerializedName("createdAt")
    val createdAt: String?,

    @SerializedName("updatedAt")
    val updatedAt: String?
) {

    companion object {
        fun levelKoreanConverter (level: String?) : String {
            return when(level) {
                "0" -> "순한맛"
                "1" -> "중간맛"
                else -> "매운맛"
            }
        }

        fun levelStringConverter(level: String) : String {
            return when(level) {
                "순한맛" -> "0"
                "중간맛" -> "1"
                else -> "2"
            }
        }
    }
}