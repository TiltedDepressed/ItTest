package com.example.ittestapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ittestapplication.databinding.ToAdminProfessionsRecyclerItemBinding
import com.example.ittestapplication.model.Professions

class ToAdminProfessionsAdapter: RecyclerView.Adapter<ToAdminProfessionsAdapter.ViewHolder>() {

    private var professionsList =  ArrayList<Professions>()

    inner class ViewHolder(val binding:ToAdminProfessionsRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    fun setProfessionsList(professionsList : List<Professions>){
        this.professionsList = professionsList as ArrayList<Professions>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ToAdminProfessionsRecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int {
     return  professionsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.idProfessionTextView.text = professionsList[position].profId
        holder.binding.nameProfessionTextView.text = professionsList[position].profName
    }
}