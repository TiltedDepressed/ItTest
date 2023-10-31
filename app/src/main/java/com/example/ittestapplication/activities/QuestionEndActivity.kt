package com.example.ittestapplication.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ittestapplication.adapters.ProfessionsAdapter
import com.example.ittestapplication.databinding.ActivityQuestionEndBinding
import com.example.ittestapplication.viewModel.MainViewModel

class QuestionEndActivity : AppCompatActivity() {
    companion object{
        const val PROFESSION_ID = "prof_id"
    }

    private lateinit var binding: ActivityQuestionEndBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profId = intent.getStringExtra(QuestionActivity.PROFESSION_ID)

        binding.repeatTestButton.setOnClickListener {
            val intent = Intent(this@QuestionEndActivity,QuestionActivity::class.java)
            intent.putExtra(PROFESSION_ID,profId)
            startActivity(intent)
            finish()
        }

        binding.choiceAnotherTestButton.setOnClickListener {
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

}