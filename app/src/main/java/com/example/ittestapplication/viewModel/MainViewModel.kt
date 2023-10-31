package com.example.ittestapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ittestapplication.datasource.ServiceBuilder
import com.example.ittestapplication.interfaces.ItTestApi
import com.example.ittestapplication.model.ApiResponse
import com.example.ittestapplication.model.Professions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel() : ViewModel() {

    private var professionsLiveData = MutableLiveData<List<Professions>>()

    fun getProfessions(){
        val api = ServiceBuilder.buildService(ItTestApi::class.java)
        api.getAllProfessions().enqueue(object: Callback<ApiResponse<Professions>>{
            override fun onResponse(
                call: Call<ApiResponse<Professions>>,
                response: Response<ApiResponse<Professions>>
            ) {
               response.body()?.let {   professionsList ->
               professionsLiveData.postValue(professionsList.data)
               }
            }

            override fun onFailure(call: Call<ApiResponse<Professions>>, t: Throwable) {
               Log.e("MainViewModel",t.message.toString())
            }

        })

    }

    fun observerProfessionsLiveData(): LiveData<List<Professions>> {
        return professionsLiveData
    }

}