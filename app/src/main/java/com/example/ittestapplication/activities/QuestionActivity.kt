package com.example.ittestapplication.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ittestapplication.adapters.AnswerAdapter
import com.example.ittestapplication.databinding.ActivityQuestionBinding
import com.example.ittestapplication.model.Answer
import com.example.ittestapplication.viewModel.QuestionViewModel

class QuestionActivity : AppCompatActivity() {

    companion object{
        const val POINTS = "total_points"
        const val PROFESSION_ID = "prof_id"
    }

    private lateinit var binding: ActivityQuestionBinding
    private lateinit var questionViewModel: QuestionViewModel
    private lateinit var answerAdapter: AnswerAdapter
    private var counter = 1;
    private var points = 0;
    private var totalPoints = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questionViewModel = ViewModelProvider(this)[QuestionViewModel::class.java]

        questionViewModel.getQuestionsByProfessionId(intent.getStringExtra(MainActivity.PROFESSION_ID)!!)

        observeQuestionLiveData(counter)

        observeAnswerLiveData()
        prepareRecyclerView()
        onAnswerClick()


        onNextButtonClick()


        }
    private fun onAnswerClick(): Int {
        answerAdapter.onItemClick = {answer ->
          totalPoints += answer.points!!
        }
        return totalPoints
    }

    private fun prepareRecyclerView() {
        answerAdapter = AnswerAdapter()
        binding.recyclerViewAnswers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = answerAdapter

        }
    }

    private fun onNextButtonClick() {
        binding.nextButton.setOnClickListener {
            counter++
            observeQuestionLiveData(counter)
        }
    }
    private fun observeAnswerLiveData(){
        questionViewModel.observerAnswersLiveData().observe(this){answerList ->
            answerAdapter.setAnswersList(answerList)
         }
    }

    @SuppressLint("SetTextI18n")
    private fun observeQuestionLiveData(counter:Int) {
            val profId = intent.getStringExtra(MainActivity.PROFESSION_ID)!!
            questionViewModel.observerQuestionsLiveData().observe(
                this
            ) { questionList ->
                if(counter < questionList.size ){
                    binding.counterQuestions.text = "${counter}/${(questionList.size-1)}"
                    binding.questionText.text = questionList[counter].question
                    questionViewModel.getAnswersByQuestionId(questionList[counter].questionId.toString())
                }
                else{
                    Toast.makeText(this, "End", Toast.LENGTH_SHORT).show()
                    intent = Intent(this@QuestionActivity,QuestionEndActivity::class.java)
                    intent.putExtra(PROFESSION_ID,profId)
                    startActivity(intent)

                }
            }
    }
}


