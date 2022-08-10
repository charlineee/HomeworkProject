package com.example.homeworkproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkproject.databinding.ProvinceLayoutBinding
import com.example.homeworkproject.model.Province

class ProvinceAdapter : RecyclerView.Adapter<ProvinceAdapter.ViewHolder>() {
    private var provinceDataArrayList: ArrayList<Province>? = null
    fun addList(provinceList: ArrayList<Province>?) {
        provinceDataArrayList = provinceList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProvinceLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.province.text= provinceDataArrayList!![position].provinceName
    }

    override fun getItemCount(): Int {
        return if (provinceDataArrayList == null) {
            0
        } else provinceDataArrayList!!.size
    }

    inner class ViewHolder(val binding: ProvinceLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (item : ProvinceLayoutBinding){
            with(binding) {
                 province.text
            }
        }
    }
}