package com.example.appliedproject;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView support;
    public TextView text_view;
    public TextView textview2;


    public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        support = itemView.findViewById(R.id.support);
        text_view = itemView.findViewById(R.id.text_view);
        textview2 = itemView.findViewById(R.id.textview2);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (recyclerViewInterface != null){
                    int pos = getAbsoluteAdapterPosition();

                    if  (pos != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(pos);
                    }

                }

            }
        });
    }
}

