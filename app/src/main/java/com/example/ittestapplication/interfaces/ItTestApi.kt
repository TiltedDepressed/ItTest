package com.example.ittestapplication.interfaces

import com.example.ittestapplication.model.Answer
import com.example.ittestapplication.model.ApiResponse
import com.example.ittestapplication.model.Professions
import com.example.ittestapplication.model.Question
import com.example.ittestapplication.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ItTestApi {
    //User
    @Headers("Content-Type:application/json")
    @GET("profile_users/")
    fun getAuth(
          @Query("login") login: String,
          @Query("password") password: String
    ) : Call<ApiResponse<User>>

    @Headers("Content-Type:application/json")
    @POST("profile_users/")
    fun createUser(
        @Query("login") login : String,
        @Query("password") password: String,
        @Query("firstName") firstName: String,
        @Query("lastName") lastName: String,
        @Query("middleName") middleName: String
    ) : Call<ApiResponse<User>>

    //Question
    @Headers("Content-Type:application/json")
    @GET("questions")
    fun getAllQuestions(): Call<ApiResponse<Question>>

    @Headers("Content-Type:application/json")
    @GET("questions/professions/{prof_id}")
    fun getQuestionsById(@Path("prof_id") id:String) : Call<ApiResponse<Question>>

    @Headers("Content-Type:application/json")
    @POST("questions")
    fun addQuestionByProfId(
        @Query("question") questionText: String,
        @Query("prof_Id")  profId: String
    ): Call<ApiResponse<Question>>

    //Professions
    @Headers("Content-Type:application/json")
    @GET("professions")
    fun getAllProfessions(): Call<ApiResponse<Professions>>

    //Answers
    @Headers("Content-Type:application/json")
    @GET("answers/{question_id}")
    fun getAnswerByQuestionId(@Path("question_id") id : String) : Call<ApiResponse<Answer>>

    @Headers("Content-Type:application/json")
    @POST("answers")
    fun addAnswerByQuestionId(
        @Query("question_id") questionId: String,
        @Query("answer") answer: String,
        @Query("points") points: String,
    ): Call<ApiResponse<Answer>>

    //results
//    @Headers("Content-Type:application/json")
//    @POST("results/")
//    fun createResult(
//        @Query("user_login")  login : String,
//        @Query("prof_id") profId: String,
//        @Query("result_test") resultTest: String
//    ) : ApiResponse<>
}