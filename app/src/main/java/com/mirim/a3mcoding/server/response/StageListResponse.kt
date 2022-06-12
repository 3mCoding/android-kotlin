package com.mirim.a3mcoding.server.response

import com.google.gson.annotations.SerializedName
import com.mirim.a3mcoding.model.StageProblem

class StageListResponse(
    @SerializedName("data")
    val data : List<StageProblem>
) {
}