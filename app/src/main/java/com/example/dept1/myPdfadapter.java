package com.example.dept1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myPdfadapter extends RecyclerView.Adapter<myPdfadapter.MyViewHolder> {

    Context context;
    ArrayList<ReadWritePdfDetails> list;

    public myPdfadapter(Context context, ArrayList<ReadWritePdfDetails> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.pdfentry,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ReadWritePdfDetails readWritePdfDetails = list.get(position);

        holder.title.setText(readWritePdfDetails.getPdfTitle());
    holder.imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(holder.imageView.getContext(),viewpdf.class);
            intent.putExtra("filename",readWritePdfDetails.getPdfTitle());
            intent.putExtra("fileurl",readWritePdfDetails.getPdfUrl());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          context.startActivity(intent);
        }
    });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pdfimg);
            title = itemView.findViewById(R.id.pdftext);
        }
    }
}
