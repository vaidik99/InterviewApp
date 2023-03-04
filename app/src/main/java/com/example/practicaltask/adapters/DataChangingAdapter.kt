package com.example.practicaltask.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaltask.databinding.ListItemsBinding

class DataChangingAdapter(val context: Context,val array: ArrayList<Int>,private val listner : OnItemClickListener) :
    RecyclerView.Adapter<DataChangingAdapter.MyViewHolder>() {

    var generatedNumber : Int = -1

    inner class MyViewHolder(val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(array.get(position)){



                binding.itemNumbers.text = this.toString()

                if(generatedNumber == this){
                    binding.mainLayHolder.setBackgroundColor(Color.RED)

                    binding.mainLayHolder.setOnClickListener(){


                        listner.onItemClick()

                    }


                }else{
                    binding.mainLayHolder.setBackgroundColor(Color.WHITE)
                }




            }
        }
    }

     fun changeGeneratedButton(value : Int){

        generatedNumber = value
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return array.size
    }

    interface OnItemClickListener{
       fun onItemClick()
    }

}