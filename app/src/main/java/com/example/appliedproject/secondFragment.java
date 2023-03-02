package com.example.appliedproject;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.example.appliedproject.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;

public class secondFragment extends Fragment {
    private RecyclerView recyclerView;

    public secondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_second);

        // use the inflater to inflate a layout file
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        // create a new LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // add the inflated view to a ViewGroup in the activity's layout
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Support", "Breathing techniques", R.drawable.support));
        items.add(new Item("Twang", "Duck sound", R.drawable.twang));
        items.add(new Item("The modes", "Know your voice", R.drawable.overview));
        items.add(new Item("The vowels", "How to pronounce in my own language?", R.drawable.all_vowels));

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter(getActivity().getApplicationContext(), items));

        return view;

    }
}