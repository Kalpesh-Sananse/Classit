package com.example.dept1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {

    private Context context;
    ArrayList<ReadWriteFacDetails> list;

    public myAdapter(Context context, ArrayList<ReadWriteFacDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ReadWriteFacDetails readWriteFacDetails = list.get(position);
        holder.Regno.setText(readWriteFacDetails.getRegno());
        holder.name.setText(readWriteFacDetails.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Regno,name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Regno =itemView.findViewById(R.id.textregno);
            name = itemView.findViewById(R.id.textname);
        }
    }
}
