package com.example.ittestapplication.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ittestapplication.adapters.ProfessionsAdapter
import com.example.ittestapplication.databinding.ActivityMainBinding
import com.example.ittestapplication.datasource.ServiceBuilder
import com.example.ittestapplication.interfaces.ItTestApi
import com.example.ittestapplication.model.ApiResponse
import com.example.ittestapplication.model.Professions
import com.example.ittestapplication.viewModel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object{
        const val PROFESSION_ID = "prof_id"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var professionsAdapter: ProfessionsAdapter
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.observerProfessionsLiveData().observe(this) { professionsList ->
            professionsAdapter.setProfessionsList(professionsList)
        }

        prepareRecyclerView()
        mainViewModel.getProfessions()
        onProfessionClick()

    }

    private fun onProfessionClick() {
        professionsAdapter.onItemClick = {profession ->
            val intent = Intent(this@MainActivity,QuestionActivity::class.java)
            intent.putExtra(PROFESSION_ID,profession.profId)
            Toast.makeText(this, "${profession.profName}", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }

    private fun prepareRecyclerView() {
        professionsAdapter = ProfessionsAdapter()
        binding.recyclerViewProfessions.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = professionsAdapter
        }
    }


}