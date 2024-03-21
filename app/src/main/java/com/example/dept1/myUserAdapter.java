package com.example.dept1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myUserAdapter extends RecyclerView.Adapter<myUserAdapter.MyViewHolder> {

    private Context context;
    ArrayList<ReadWriteUserDetails> list;

    public myUserAdapter(Context context, ArrayList<ReadWriteUserDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.studententry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ReadWriteUserDetails readWriteUserDetails = list.get(position);
        holder.Regno.setText(readWriteUserDetails.getRegno());
        holder.name.setText(readWriteUserDetails.getFullname());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Regno, name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Regno = itemView.findViewById(R.id.textregno);
            name = itemView.findViewById(R.id.textname);
        }
    }
}
