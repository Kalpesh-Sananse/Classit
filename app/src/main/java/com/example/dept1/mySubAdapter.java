package com.example.dept1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class mySubAdapter extends RecyclerView.Adapter<mySubAdapter.MyViewHolder> {
    Context context;
    ArrayList<ReadWriteSubDetails>  list;

    public mySubAdapter(Context context, ArrayList<ReadWriteSubDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notesentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ReadWriteSubDetails readWriteSubDetails = list.get(position);

            holder.Subname.setText(readWriteSubDetails.getSubname());
            holder.Tname.setText(readWriteSubDetails.getTname());

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,myNotesActivity.class);
                    intent.putExtra("subname",readWriteSubDetails.getSubname());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Subname ,Tname;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Subname =  itemView.findViewById(R.id.textsubname);
            Tname = itemView.findViewById(R.id.textTeachername);
            linearLayout = itemView.findViewById(R.id.linearLayout);

        }


    }
}
