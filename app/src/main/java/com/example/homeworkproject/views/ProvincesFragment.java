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
import com.example.homeworkproject.model.Province;
import com.example.homeworkproject.viewmodels.LocationViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class ProvincesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private LocationViewModel viewModel;
    private ProvinceAdapter provinceAdapter;
    private String mParam1;
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
            mParam1 = getArguments().getString(ARG_PARAM1);
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

        binding.retryButton.setOnClickListener(view -> {
            binding.retryButton.setVisibility(View.GONE);
            binding.provinceText.setVisibility(View.GONE);
            viewModel.getLiveProvinceData(mParam1);
        });

        getAllProvinces(mParam1);
    }

    public void getAllProvinces(String value) {

        if (viewModel.currentVal == null || !viewModel.currentVal.equals(value)){
            viewModel.getLiveProvinceData(value);
        }

        viewModel.provinceData.observe(requireActivity(), provinces -> {

            switch(provinces.status){

                case SUCCESS:
                    binding.progressBar.setVisibility(View.GONE);
                    provinceAdapter.addList(provinces.data);
                    provinceAdapter.notifyItemRangeChanged(0, (provinces.data).size());
                    if (provinces.data.size() < 1) {
                        binding.provinceText.setText(R.string.provinceNone);
                    }
                    break;
                case LOADING:
                    binding.progressBar.setVisibility(View.VISIBLE);
                    break;
                case ERROR:
                    binding.progressBar.setVisibility(View.GONE);
                    binding.provinceText.setText(R.string.error);
                    binding.retryButton.setVisibility(View.VISIBLE);
                    break;
            }

        });

    }


}