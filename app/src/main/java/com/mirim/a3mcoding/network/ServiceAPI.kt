package com.mirim.a3mcoding.network

import com.mirim.a3mcoding.server.request.JoinRequest
import com.mirim.a3mcoding.server.request.LoginRequest
import com.mirim.a3mcoding.server.response.JoinResponse
import com.mirim.a3mcoding.server.response.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ServiceAPI {
    // 회원가입
    @POST("/user/join")
    fun userJoin(
        @Body data: JoinRequest
    ): Call<JoinResponse>

    // 로그인
    @POST("/user/login")
    fun userLogin(
        @Body data: LoginRequest
    ): Call<LoginResponse>
//
//    // 회원 정보 수정
//    @POST("/user/update")
//    fun userUpdate(
//        @Query("email") email: String?,
//        @Query("student_num") student_num: Int,
//        @Query("name") name: String?
//    ): Call<UpdateResponse?>?
//
//    //문제 화면
//    @GET("/question/type/{type}")
//    fun questionData(@Path("type") type: String?, @Query("no") no: Int): Call<List<Question?>?>?
//
//    //문제 - 정답 확인
//    @POST("/question/answer")
//    fun questionAnswer(@Body data: answerData?): Call<AnswerResponse?>?
//
//    //문제 - step update
//    @POST("/user/pass")
//    fun stepData(@Query("email") email: String?, @Query("step") step: Int): Call<StepResponse?>?
//
//    //문제 - 설명
//    @GET("/question/description")
//    fun descriptionData(@Query("id") id: Int): Call<List<Description?>?>?
//
//    //문제 추천
//    @POST("/question/recommend")
//    fun recData(
//        @Query("content") content: String?,
//        @Query("student_num") student_num: Int
//    ): Call<RecommendResponse?>?
//
//    //문제 리스트
//    @GET("/question/list")
//    fun questionListData(): Call<List<QuestionList?>?>?
//
//    //학생 리스트
//    @GET("/user/list")
//    fun studentListData(): Call<List<StudentList?>?>?
}