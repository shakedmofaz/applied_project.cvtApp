package com.example.appliedproject;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class secondFragment extends Fragment implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private List<Item> items;
    public secondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_second);

        // use the inflater to inflate a layout file        View view = inflater.inflate(R.layout.fragment_second, container, false);

        // create a new LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // add the inflated view to a ViewGroup in the activity's layout
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        items = new ArrayList<Item>();
        items.add(new Item("Support", "Breathing techniques", R.drawable.support));
        items.add(new Item("Twang", "Duck sound", R.drawable.twang));
        items.add(new Item("The modes", "Know your voice", R.drawable.overview));
        items.add(new Item("The vowels", "How to pronounce in my own language?", R.drawable.all_vowels));

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter(this, getActivity().getApplicationContext(), items));

        return view;

    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), explain_activity.class);
        intent.putExtra("NAME", "SHAKED");
        intent.putExtra("POSITION", position);
        intent.putExtra("TITLE", items.get(position).getTitle());
        intent.putExtra("SUBTITLE", items.get(position).getSubtitle());
        intent.putExtra("IMAGE", items.get(position).getImage());
        startActivity(intent);
    }    /*

    @Override    public void onItemClick(int position) {
        Intent.putExtra("Support", "Breathing techniques", R.drawable.support);        items.add(new Item("Twang", "Duck sound", R.drawable.twang));        items.add(new Item("The modes", "Know your voice", R.drawable.overview));        items.add(new Item("The vowels", "How to pronounce in my own language?", R.drawable.all_vowels));


    }*/}
