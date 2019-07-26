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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import dagger.android.support.AndroidSupportInjection;
import io.github.louistsaitszho.stand_up.R;
import io.github.louistsaitszho.stand_up.databinding.FragmentTaskListBinding;
import timber.log.Timber;

public class TaskListFragment extends Fragment {

    private static final String TAG = "TaskListFragment";

    public static TaskListFragment newInstance() {
        Timber.tag(TAG).d("newInstance");
        return new TaskListFragment();
    }

    private TaskListViewModel viewModel;

    // TODO does this count as non-null?
    private FragmentTaskListBinding binding;

    private LinearLayoutManager linearLayoutManager;
    private TaskAdapter taskAdapter = new TaskAdapter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(TaskListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_task_list,
                container,
                false
        );

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        initRecyclerView();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.fakeListLiveData.observe(getViewLifecycleOwner(), tasks -> {
            Timber.tag(TAG).d(tasks.toString());
        });
    }

    private void initRecyclerView() {
        if (linearLayoutManager == null) {
            linearLayoutManager = new LinearLayoutManager(requireContext());
        }
        binding.recyclerViewTaskList.setAdapter(taskAdapter);
        binding.recyclerViewTaskList.setLayoutManager(linearLayoutManager);
    }
}
