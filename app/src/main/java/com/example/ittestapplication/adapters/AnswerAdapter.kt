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
    private var isNewRadioButtonChecked = false
    private var lastCheckedPosition = -1

    fun setAnswersList(answersList : List<Answer>){
        this.answersList = answersList as ArrayList<Answer>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: AnswerRecyclerItemBinding):RecyclerView.ViewHolder(binding.root){

    }

    private fun handleRadioButtonChecks(adapterPosition: Int) {
        isNewRadioButtonChecked = true
        answersList[lastCheckedPosition].isSelected = false
        answersList[adapterPosition].isSelected = true
        lastCheckedPosition = adapterPosition
        notifyDataSetChanged()
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

        if(isNewRadioButtonChecked){
            holder.binding.answerRadioButton.isChecked = answersList[position].isSelected
        } else{
            if (holder.adapterPosition == 0){
                lastCheckedPosition = 0
            }
        }

        holder.binding.answerRadioButton.setOnClickListener {
            handleRadioButtonChecks(holder.adapterPosition)
            onItemClick!!.invoke(answersList[position])

        }

    }


}