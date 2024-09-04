package com.example.fragmentspractice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.DestinationViewHolder>
{
    private List<MyModel> destinationData;
    private Context context;

    public MyAdapter( List<MyModel> destinationData)
    {
        this.destinationData = destinationData;
    }
    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.destination_data, parent, false);
        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder holder, int position)
    {
        MyModel model = destinationData.get(position);
        String country_id = model.getCountry_id();
        String country_name = model.getCountry_name();
        String tag_name = model.getTag_name();
        holder.destinationTitleTextView.setText(String.valueOf(country_name+" "+country_id+" "+tag_name));
        holder.destinationIdTextView.setText(String.valueOf(country_name+" ("+country_id+")"));
        //Click Listener
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, LoginFormActivity.class);
            intent.putExtra("country_id", model.getCountry_id());
            intent.putExtra("country_name", model.getCountry_name());
            intent.putExtra("tag_name", model.getTag_name());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount()
    {
        return destinationData.size();
    }

    public static class DestinationViewHolder extends RecyclerView.ViewHolder
    {
        TextView destinationIdTextView;
        TextView destinationTitleTextView;
        public DestinationViewHolder(@NonNull View itemView)
        {
            super(itemView);
            try
            {
                destinationIdTextView = itemView.findViewById(R.id.destinationIdTextView);
                destinationTitleTextView = itemView.findViewById(R.id.destionationtitleTextView);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
