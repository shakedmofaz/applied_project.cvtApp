package com.example.appliedproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appliedproject.databinding.ActivityMainBinding;
import com.example.appliedproject.databinding.FragmentFirstBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link firstFragment} factory method to
 * create an instance of this fragment.
 */
public class firstFragment extends Fragment {

    private FragmentFirstBinding binding;

    public firstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "use your goddamn support!", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("FUCK YEAH", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.setActionTextColor(getResources().getColor(android.R.color.holo_red_dark)); // set the action button color
                snackbar.show();
            }
        });
        return view;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}