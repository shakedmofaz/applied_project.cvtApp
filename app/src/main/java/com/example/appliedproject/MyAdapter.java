package com.example.appliedproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    List<Item> items;


    public MyAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<Item> items) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_design, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

  //return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_design,parent,false));
//}

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text_view.setText(items.get(position).getTitle());
        holder.textview2.setText(items.get(position).getSubtitle());
        holder.support.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

