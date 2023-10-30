package com.example.ittestapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ittestapplication.databinding.ProfessionsRecyclerItemBinding
import com.example.ittestapplication.model.Professions

class ProfessionsAdapter(
    private val professionsList: List<Professions>
): RecyclerView.Adapter<ProfessionsAdapter.ViewHolder>() {

  //  private var professionsList =  ArrayList<Professions>()
    var onItemClick: ((Professions) -> Unit)? = null


//    fun setProfessionsList(professionsList : List<Professions>){
//        this.professionsList = professionsList as ArrayList<Professions>
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(val binding: ProfessionsRecyclerItemBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProfessionsRecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }


    override fun getItemCount(): Int {
        return professionsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.professionButton.text = professionsList[position].profName.toString()

        holder.itemView.setOnClickListener{
            onItemClick!!.invoke(professionsList[position])
        }
    }


}