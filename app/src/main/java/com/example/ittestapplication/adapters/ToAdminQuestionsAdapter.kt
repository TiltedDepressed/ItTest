package com.example.ittestapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ittestapplication.databinding.ToAdminQuestionsRecyclerItemBinding
import com.example.ittestapplication.model.Professions
import com.example.ittestapplication.model.Question

class ToAdminQuestionsAdapter: RecyclerView.Adapter<ToAdminQuestionsAdapter.ViewHolder>() {
    private var questionsList =  ArrayList<Question>()
    inner class ViewHolder(val binding: ToAdminQuestionsRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
    }

    fun setQuestionsList(questionsList : List<Question>){
        this.questionsList = questionsList as ArrayList<Question>
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(
           ToAdminQuestionsRecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
       )
    }

    override fun getItemCount(): Int {
        return questionsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.idQuestionTextView.text = questionsList[position].questionId.toString()
        holder.binding.questionTextView.text = questionsList[position].question.toString()
    }
}