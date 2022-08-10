package com.example.homeworkproject.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homeworkproject.databinding.CountryLayoutBinding
import com.example.homeworkproject.model.Country

class LocationAdapter(
    var countryDataArrayList: ArrayList<Country>?,
    var context: FragmentActivity?,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    fun addList(countryList: ArrayList<Country>?) {
        countryDataArrayList = countryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            CountryLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.binding.country.text= countryDataArrayList!![position].countryName
        //load flag images
        Glide.with(context!!)
            .load("https://raw.githubusercontent.com/hampusborgos/country-flags/main/png250px/" + countryDataArrayList!![position].countryCode.lowercase() + ".png") //url
            .circleCrop()
            .into(holder.binding.flag)
        //click listener for item selection
        holder.binding.country.setOnClickListener(View.OnClickListener { view: View? ->
            clickListener.onItemClick(
                countryDataArrayList!![position]
            )
        })
    }

    override fun getItemCount(): Int {
        return if (countryDataArrayList == null) {
            0
        } else countryDataArrayList!!.size
    }

    class ViewHolder(val binding: CountryLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (item : CountryLayoutBinding){
            with(binding) {
                country.text
                flag
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(countryDataArrayList: Country?)
    }
}