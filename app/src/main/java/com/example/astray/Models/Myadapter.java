package com.example.astray.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astray.R;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyviewHolder> {


    ArrayList<Model> mList;
    Context context;

    public  Myadapter(Context context , ArrayList<Model> mList){

        this.mList = mList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.item , parent ,false);
       return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        Model model = mList.get(position);
        holder.name.setText(model.getName());
        holder.age.setText(model.getAge());
        holder.gender.setText(model.getGender());
        holder.missingplace.setText(model.getMissingplace());
        holder.aboutchild.setText(model.getAboutchild());
        holder.mno.setText(model.getMno());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        TextView name, age , gender , missingplace , aboutchild,mno;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_text);
            age = itemView.findViewById(R.id.age_text);
            gender = itemView.findViewById(R.id.gender_text);
            missingplace = itemView.findViewById(R.id.missingplace_text);
            aboutchild = itemView.findViewById(R.id.abouchild_text);
            mno = itemView.findViewById(R.id.m_text);
        }
    }
}
