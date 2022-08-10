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
import com.example.homeworkproject.adapter.ProvinceAdapter
import com.example.homeworkproject.databinding.FragmentProvincesBinding
import com.example.homeworkproject.model.ApiState
import com.example.homeworkproject.model.Province
import com.example.homeworkproject.viewmodels.LocationViewModel
import java.util.*

class ProvincesFragment : Fragment() {
    private var viewModel: LocationViewModel? = null
    private var provinceAdapter: ProvinceAdapter? = null
    private var countryId: String? = null
    private var binding: FragmentProvincesBinding? = null
    private var provinceArrayList: ArrayList<Province>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            countryId = requireArguments().getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set title and enable back button on toolbar
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)
            ?.setTitle(R.string.p_title)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)
            ?.setDisplayHomeAsUpEnabled(true)
        Objects.requireNonNull((requireActivity() as AppCompatActivity).supportActionBar)
            ?.setDisplayShowHomeEnabled(true)
        binding = FragmentProvincesBinding.inflate(inflater, container, false)
        val view = binding!!.root
        initView()
        return view
    }

    private fun initView() {
        viewModel = ViewModelProvider(requireActivity()).get(LocationViewModel::class.java)
        val layoutManager = LinearLayoutManager(activity)
        provinceAdapter = ProvinceAdapter()
        binding!!.recyclerView.layoutManager = layoutManager
        binding!!.recyclerView.adapter = provinceAdapter
        provinceArrayList = ArrayList()
        binding!!.viewModel = viewModel
        binding!!.lifecycleOwner = viewLifecycleOwner
        binding!!.provinceFragment = this
        getAllProvinces()
    }

    fun getAllProvinces(){
        viewModel!!.getLiveProvinceData(countryId)
        viewModel!!.provinceData!!.observe(
            requireActivity()
        ) { province: ArrayList<Province> ->
            if(province.size < 1) {
                viewModel!!.currentState.value = ApiState.Status.BLANK
            } else{
                viewModel!!.currentState.value = ApiState.Status.SUCCESS
                provinceAdapter!!.addList(province)
                provinceAdapter!!.notifyItemRangeChanged(0, province.size)
            }
        }
    }



    companion object {
        private const val ARG_PARAM1 = "param1"
        fun newInstance(param1: String?): ProvincesFragment {
            val fragment = ProvincesFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}

