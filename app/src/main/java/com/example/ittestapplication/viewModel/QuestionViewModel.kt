package com.example.ittestapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ittestapplication.datasource.ServiceBuilder
import com.example.ittestapplication.interfaces.ItTestApi
import com.example.ittestapplication.model.Answer
import com.example.ittestapplication.model.ApiResponse
import com.example.ittestapplication.model.Question
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionViewModel() : ViewModel() {
    private var questionsLiveData = MutableLiveData<List<Question>>()
    private var answersLiveData = MutableLiveData<List<Answer>>()
fun getQuestionsByProfessionId(id: String){
    val api = ServiceBuilder.buildService(ItTestApi::class.java)
    api.getQuestionsById(id).enqueue(object: Callback<ApiResponse<Question>>{
        override fun onResponse(
            call: Call<ApiResponse<Question>>,
            response: Response<ApiResponse<Question>>
        ) {
                 response.body()?.let {questionList ->
                 questionsLiveData.postValue(questionList.data)
                 }

        }
        override fun onFailure(call: Call<ApiResponse<Question>>, t: Throwable) {
            Log.e("QuestionViewModel",t.message.toString())
        }
    })
}
    fun getAnswersByQuestionId(id: String){
        val api = ServiceBuilder.buildService(ItTestApi::class.java)
        api.getAnswerByQuestionId(id).enqueue(object: Callback<ApiResponse<Answer>>{
            override fun onResponse(
                call: Call<ApiResponse<Answer>>,
                response: Response<ApiResponse<Answer>>
            ) {

              response.body()?.let {answerList ->
                  answersLiveData.postValue(answerList.data)
              }

            }

            override fun onFailure(call: Call<ApiResponse<Answer>>, t: Throwable) {
                Log.e("QuestionViewModel",t.message.toString())
            }

        })
    }
    fun observerQuestionsLiveData(): LiveData<List<Question>>{
    return questionsLiveData
}
    fun observerAnswersLiveData(): LiveData<List<Answer>>{
        return answersLiveData
    }


}