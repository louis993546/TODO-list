package io.github.louistsaitszho.stand_up.feature_task_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.android.support.AndroidSupportInjection;
import io.github.louistsaitszho.stand_up.R;
import io.github.louistsaitszho.stand_up.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    // TODO does this count as non-null?
    private FragmentMainBinding binding;

    private LinearLayoutManager linearLayoutManager;
    private TaskAdapter taskAdapter = new TaskAdapter();

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main,
                container,
                false
        );

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    private void initRecyclerView() {
        if (linearLayoutManager == null) {
            linearLayoutManager = new LinearLayoutManager(requireContext());
        }
        binding.recyclerViewTaskList.setAdapter(taskAdapter);
        binding.recyclerViewTaskList.setLayoutManager(linearLayoutManager);
    }
}
