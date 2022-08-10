package com.example.homeworkproject.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkproject.R
import com.example.homeworkproject.adapter.LocationAdapter
import com.example.homeworkproject.adapter.LocationAdapter.ItemClickListener
import com.example.homeworkproject.databinding.FragmentCountriesBinding
import com.example.homeworkproject.model.ApiState
import com.example.homeworkproject.model.Country
import com.example.homeworkproject.viewmodels.LocationViewModel
import java.util.*

class CountriesFragment : Fragment(), ItemClickListener {
    private var locationAdapter: LocationAdapter? = null
    private var viewModel: LocationViewModel? = null
    private var binding: FragmentCountriesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountriesBinding.inflate(inflater, container, false)
        val view = binding!!.root
        initView()

        //set title on toolbar
        Objects.requireNonNull((activity as AppCompatActivity?)!!.supportActionBar)
            ?.setTitle(R.string.c_title)
        Objects.requireNonNull((activity as AppCompatActivity?)!!.supportActionBar)
            ?.setDisplayHomeAsUpEnabled(false)
        Objects.requireNonNull((activity as AppCompatActivity?)!!.supportActionBar)
            ?.setDisplayShowHomeEnabled(false)
        return view
    }

    private fun initView() {
        viewModel = ViewModelProvider(requireActivity()).get(LocationViewModel::class.java)
        val layoutManager = LinearLayoutManager(activity)
        locationAdapter = LocationAdapter(countryArrayList, activity, this)
        binding!!.recyclerView.layoutManager = layoutManager
        binding!!.recyclerView.adapter = locationAdapter
        binding!!.viewModel = viewModel
        binding!!.lifecycleOwner = viewLifecycleOwner
        binding!!.countriesFragment = this
        getAllCountries()
    }

    fun getAllCountries(){
            viewModel!!.getLiveCountryData()
            viewModel!!.countryData!!.observe(
                requireActivity()
            ) { country: ArrayList<Country> ->
                viewModel!!.currentState.value = ApiState.Status.SUCCESS
                locationAdapter!!.addList(country)
                locationAdapter!!.notifyItemRangeChanged(0, country.size)
            }
        }

    override fun onItemClick(countryDataArrayList: Country?) {
        val value = countryDataArrayList?.countryId.toString()
        val fragment: Fragment = ProvincesFragment.newInstance(value)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameContainer, fragment, "provinces")
        transaction.addToBackStack("countries")
        transaction.commit()
    }

    companion object {
        var countryArrayList: ArrayList<Country>? = null
        fun newInstance(): CountriesFragment {
            return CountriesFragment()
        }
    }

}