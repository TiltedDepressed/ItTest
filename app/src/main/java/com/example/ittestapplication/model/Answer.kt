package com.example.ittestapplication.model

import com.google.gson.annotations.SerializedName

data class Answer(
    @SerializedName("answer_id"   ) var answerId   : Int?    = null,
    @SerializedName("question_id" ) var questionId : Int?    = null,
    @SerializedName("answer"      ) var answer     : String? = null,
    @SerializedName("points"      ) var points     : Int?    = null
)
