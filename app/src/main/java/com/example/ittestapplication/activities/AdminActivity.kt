package com.example.ittestapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ittestapplication.R
import com.example.ittestapplication.adapters.ProfessionsAdapter
import com.example.ittestapplication.adapters.ToAdminProfessionsAdapter
import com.example.ittestapplication.adapters.ToAdminQuestionsAdapter
import com.example.ittestapplication.databinding.ActivityAdminBinding
import com.example.ittestapplication.datasource.ServiceBuilder
import com.example.ittestapplication.interfaces.ItTestApi
import com.example.ittestapplication.model.Answer
import com.example.ittestapplication.model.ApiResponse
import com.example.ittestapplication.model.Question
import com.example.ittestapplication.viewModel.AdminViewModel
import com.example.ittestapplication.viewModel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private lateinit var administratorProfessionsAdapter: ToAdminProfessionsAdapter
    private lateinit var administratorQuestionsAdapter: ToAdminQuestionsAdapter
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adminViewModel: AdminViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.observerProfessionsLiveData().observe(this) { professionsList ->
            administratorProfessionsAdapter.setProfessionsList(professionsList)
        }

        adminViewModel = ViewModelProvider(this)[AdminViewModel::class.java]

        adminViewModel.observerQuestionLiveData().observe(this){questionList ->
            administratorQuestionsAdapter.setQuestionsList(questionList)
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this@AdminActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        prepareRecyclerView()
        mainViewModel.getProfessions()
        adminViewModel.getAllQuestions()


        binding.addQuestionButton.setOnClickListener {
            addQuestion()
        }
        binding.addAnswerButton.setOnClickListener {
            addAnswer()
        }
    }

    private fun addAnswer() {
        val answer = binding.answerEditText.text.toString()
        val questionId = binding.idQuestionEditText.text.toString()
        val points = binding.countPointsEditText.text.toString()
        val api = ServiceBuilder.buildService(ItTestApi::class.java)
        api.addAnswerByQuestionId(questionId,answer,points).enqueue(object: Callback<ApiResponse<Answer>>{
            override fun onResponse(
                call: Call<ApiResponse<Answer>>,
                response: Response<ApiResponse<Answer>>
            ) {
                if (response.body()!!.success == true){
                    Toast.makeText(this@AdminActivity, "New answer added", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@AdminActivity, "Something goes wrong", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse<Answer>>, t: Throwable) {
                Log.e("AdminActivity", t.message.toString())
            }

        })
    }

    private fun addQuestion() {
            val questionText = binding.questionEditText.text.toString()
            val profId = binding.idQuestionEditText.text.toString()
            val api = ServiceBuilder.buildService(ItTestApi::class.java)
            api.addQuestionByProfId(questionText,profId).enqueue(object:Callback<ApiResponse<Question>>{
                override fun onResponse(
                    call: Call<ApiResponse<Question>>,
                    response: Response<ApiResponse<Question>>
                ) {
                    if (response.body()!!.success == true){
                        Toast.makeText(this@AdminActivity, "New question added", Toast.LENGTH_SHORT).show()
                    } else{
                        Toast.makeText(this@AdminActivity, "Something goes wrong", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ApiResponse<Question>>, t: Throwable) {
                   Log.e("AdminActivity",t.message.toString())
                }

            })
        }


    private fun prepareRecyclerView() {
        administratorProfessionsAdapter = ToAdminProfessionsAdapter()
        binding.recyclerViewProfessions.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = administratorProfessionsAdapter
        }

        administratorQuestionsAdapter = ToAdminQuestionsAdapter()
        binding.questionsRecyclerView.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = administratorQuestionsAdapter
        }


    }
}