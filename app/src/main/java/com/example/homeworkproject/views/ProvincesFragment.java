package com.example.homeworkproject.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homeworkproject.R;
import com.example.homeworkproject.adapter.ProvinceAdapter;
import com.example.homeworkproject.databinding.FragmentProvincesBinding;
import com.example.homeworkproject.model.ApiState;
import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.viewmodels.LocationViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class ProvincesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private LocationViewModel viewModel;
    private ProvinceAdapter provinceAdapter;
    private String countryId;
    private FragmentProvincesBinding binding;
    ArrayList<Province> provinceArrayList;


    public ProvincesFragment() {
        // Required empty public constructor
    }


    public static ProvincesFragment newInstance(String param1) {
        ProvincesFragment fragment = new ProvincesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countryId = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //set title and enable back button on toolbar
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(R.string.p_title);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);

        binding = FragmentProvincesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initView();

        return view;
    }


    private void initView() {

        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        provinceAdapter = new ProvinceAdapter();
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(provinceAdapter);
        provinceArrayList = new ArrayList<>();
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setProvinceFragment(this);
        getAllProvinces();
    }


    public void getAllProvinces() {
        if (viewModel.currentVal == null || !viewModel.currentVal.equals(countryId)){
            viewModel.getLiveProvinceData(countryId);
        }

        viewModel.provinceData.observe(requireActivity(), provinces -> {
            if(provinces == null){
                viewModel.currentState.setValue(ApiState.Status.ERROR);
            } else if (provinces.size() < 1){
                viewModel.currentState.setValue(ApiState.Status.BLANK);
            } else{
                viewModel.currentState.setValue(ApiState.Status.SUCCESS);
                provinceAdapter.addList(provinces);
                provinceAdapter.notifyItemRangeChanged(0, (provinces.size()));
            }
        });

    }

}