package com.mirim.a3mcoding.server.response

import com.google.gson.annotations.SerializedName
import com.mirim.a3mcoding.model.LevelProblem

class LevelProblemResponse(
    @SerializedName("data")
    val data : LevelProblem
) {
}