package com.example.ittestapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ittestapplication.databinding.AnswerRecyclerItemBinding
import com.example.ittestapplication.model.Answer

class AnswerAdapter(
): RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {

    private var answersList =  ArrayList<Answer>()
    var onItemClick: ((Answer) -> Unit)? = null



    fun setAnswersList(answersList : List<Answer>){
        this.answersList = answersList as ArrayList<Answer>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: AnswerRecyclerItemBinding):RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AnswerRecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }


    override fun getItemCount(): Int {
        return answersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.answerRadioButton.text = answersList[position].answer.toString()

        holder.binding.answerRadioButton.setOnClickListener{
            onItemClick!!.invoke(answersList[position])
        }
    }


}