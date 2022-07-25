package com.example.homeworkproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homeworkproject.databinding.CountryLayoutBinding;
import com.example.homeworkproject.model.Country;

import java.util.ArrayList;
import java.util.Locale;


public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    public ArrayList<Country> countryDataArrayList;
    private final ItemClickListener clickListener;
    Context context;

    public LocationAdapter(ArrayList<Country> countryArrayList, Context context, ItemClickListener clickListener) {
        this.countryDataArrayList = countryArrayList;
        this.context = context;
        this.clickListener = clickListener;
    }

    public void addList(ArrayList<Country> countryList){
        this.countryDataArrayList = countryList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(CountryLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.itemView.country.setText(countryDataArrayList.get(position).getCountryName());
        //load flag images
        Glide.with(context)
                .load("https://raw.githubusercontent.com/hampusborgos/country-flags/main/png250px/" + countryDataArrayList.get(position).getCountryCode().toLowerCase(Locale.ROOT)+".png")//url
                .circleCrop()
                .into(holder.itemView.flag);
        //click listener for item selection
        holder.itemView.country.setOnClickListener(view -> clickListener.onItemClick(countryDataArrayList.get(position)));
    }

    @Override
    public int getItemCount() {
        if (countryDataArrayList == null){
            return 0;
        }
        return countryDataArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CountryLayoutBinding itemView;

        public ViewHolder(@NonNull CountryLayoutBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

    }
    public interface ItemClickListener {
        void onItemClick(Country countryDataArrayList);
    }

}
