package com.example.ittestapplication.viewModel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ittestapplication.datasource.ServiceBuilder
import com.example.ittestapplication.interfaces.ItTestApi
import com.example.ittestapplication.model.ApiResponse
import com.example.ittestapplication.model.Question
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminViewModel(): ViewModel() {
    private var questionsLiveData = MutableLiveData<List<Question>>()

    fun getAllQuestions(){
        val api = ServiceBuilder.buildService(ItTestApi::class.java)
        api.getAllQuestions().enqueue(object:Callback<ApiResponse<Question>>{
            override fun onResponse(
                call: Call<ApiResponse<Question>>,
                response: Response<ApiResponse<Question>>
            ) {
                response.body()?.let {questionList ->
                    questionsLiveData.postValue(questionList.data)
                }
            }

            override fun onFailure(call: Call<ApiResponse<Question>>, t: Throwable) {
                Log.e("AdminViewModel", t.message.toString())
            }

        })
    }

    fun observerQuestionLiveData(): LiveData<List<Question>>{
        return questionsLiveData
    }
}