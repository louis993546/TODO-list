package io.github.louistsaitszho.stand_up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import io.github.louistsaitszho.stand_up.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    // TODO does this count as non-null?
    private FragmentMainBinding binding;

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

}
