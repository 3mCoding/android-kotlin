package com.mirim.a3mcoding.network

import androidx.annotation.NonNull
import com.mirim.a3mcoding.model.User
import com.mirim.a3mcoding.server.request.*
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
    @GET("/levels/list/{email}")
    fun getLevelList(
        @Path("email") email: String?
    ) : Call<LevelListResponse>

    // 단계별 문제 상세 조회
    @GET("/stages")
    fun getStageProblem(
        @Query("no") no: Int?,
        @Query("type") type: String
    ) : Call<StageProblemResponse>

    // 난이도별 문제 상세 조회
    @GET("/levels")
    fun getLevelProblem(
        @Query("id") id : Int?
    ) : Call<LevelProblemResponse>

    // 난이도별 문제 추천
    @GET("/levels/suggestion")
    fun getRecommendation(
        @Query("level") level :String?,
        @Query("time") time: Int?
    ) : Call<LevelProblemResponse>

    // 단계별 문제 해결 후
    @PUT("/stages/solve")
    fun solveStage(
        @Body body :StageSolveRequest
    ) : Call<StageProblemResponse>

     //난이도별 문제 해결 후
    @PUT("/levels/solve")
    fun solveLevel(
        @Body body : LevelSolveRequest
    ) : Call<LevelProblemResponse>

    // 회원 정보 수정
    @PUT("/users/{email}")
    fun editProfile(
        @Path("email") email: String?,
        @Body body: EditProfileRequest
    ) : Call<Response>

    // 비밀번호 수정
    @PUT("/users/password/{email}")
    fun editPassword(
        @Path("email") email:String?,
        @Body body : EditPasswordRequest
    ) : Call<Response>
}