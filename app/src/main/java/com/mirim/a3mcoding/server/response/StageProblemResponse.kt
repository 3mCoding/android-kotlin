package com.mirim.a3mcoding.server.response

import com.google.gson.annotations.SerializedName
import com.mirim.a3mcoding.model.StageProblem
import com.squareup.moshi.JsonClass

class StageProblemResponse(
    @JsonClass(generateAdapter = true)
    @SerializedName("data")
    val data : StageProblem
) {
}