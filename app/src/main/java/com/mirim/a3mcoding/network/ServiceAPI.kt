package com.mirim.a3mcoding.network

import com.mirim.a3mcoding.model.User
import com.mirim.a3mcoding.server.request.JoinRequest
import com.mirim.a3mcoding.server.request.LoginRequest
import com.mirim.a3mcoding.server.response.*
import retrofit2.Call
import retrofit2.http.*

interface ServiceAPI {
    // 회원가입
    @POST("/users/join")
    fun userJoin(
        @Body data: JoinRequest
    ): Call<JoinResponse>

    // 로그인
    @POST("/users/login")
    fun userLogin(
        @Body data: LoginRequest
    ): Call<LoginResponse>

    // 유저 리스트
    @GET("/users/ranking")
    fun userRank() : Call<RankResponse>

    // 단계별 문제 리스트
    @GET("/stages/list")
    fun getStageList() : Call<StageListResponse>

    // 난이도별 문제 리스트
    @GET("/levels/list")
    fun getLevelList() : Call<LevelListResponse>
}