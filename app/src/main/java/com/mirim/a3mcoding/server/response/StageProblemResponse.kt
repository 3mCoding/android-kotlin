package com.mirim.a3mcoding.server.response

import com.google.gson.annotations.SerializedName
import com.mirim.a3mcoding.model.StageProblem

class StageProblemResponse(
    @SerializedName("data")
    val data : StageProblem
) {
}