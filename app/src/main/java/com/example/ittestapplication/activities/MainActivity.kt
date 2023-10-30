package com.example.ittestapplication.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ittestapplication.adapters.ProfessionsAdapter
import com.example.ittestapplication.databinding.ActivityMainBinding
import com.example.ittestapplication.datasource.ServiceBuilder
import com.example.ittestapplication.interfaces.ItTestApi
import com.example.ittestapplication.model.ApiResponse
import com.example.ittestapplication.model.Professions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var professionsList: List<Professions>
    private lateinit var professionsAdapter: ProfessionsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getAllProfessions()

    }

    private fun getAllProfessions() {
        val api = ServiceBuilder.buildService(ItTestApi::class.java)
        api.getAllProfessions().enqueue(object: Callback<ApiResponse<Professions>>{
            override fun onResponse(
                call: Call<ApiResponse<Professions>>,
                response: Response<ApiResponse<Professions>>
            ) {
                val response = response.body()!!
                professionsList = response.data
                professionsAdapter = ProfessionsAdapter(professionsList)
                binding.recyclerViewProfessions.apply {
                    layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
                    adapter = professionsAdapter
               }
            }

            override fun onFailure(call: Call<ApiResponse<Professions>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", t.message.toString())
            }

        })
    }
}