package com.example.ittestapplication.model

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("question_id")var questionId: Int? = null,
    @SerializedName("question")var question: String? = null,
    @SerializedName("prof_id")var profId: String? = null
)
