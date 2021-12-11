package com.example.taskapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.taskapp.R;
import com.example.taskapp.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
private TaskAdapter adapter = new TaskAdapter();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        setFragmentListener();
        initRv();
    }

    private void initRv() {
    binding.taskRv.setAdapter(adapter);

    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener("key", getViewLifecycleOwner(), (requestKey, result) -> {
            String text = result.getString("text");
            adapter.setText(text);

        });
    }

    private void initListener() {
        binding.actionBtn.setOnClickListener(v -> {

            openFragment();
        });

    }

    private void openFragment(){
       NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main);
       navController.navigate(R.id.formFragment);    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}