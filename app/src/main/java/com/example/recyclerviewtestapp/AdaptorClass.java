package com.example.recyclerviewtestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptorClass extends RecyclerView.Adapter<AdaptorClass.ViewHolder> {

    Context context;
    ArrayList<ListModelClass> listModelClasses;

    public AdaptorClass(Context context, ArrayList<ListModelClass> listModelClasses) {
        this.context = context;
        this.listModelClasses = listModelClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_display_list,parent,false);
         ViewHolder viewHolder = new ViewHolder(v);
         return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(listModelClasses.get(position));
        ListModelClass aClass = listModelClasses.get(position);
        holder.text_id.setText(aClass.getId());
        holder.text_name.setText(aClass.getName());
        holder.text_salary.setText(aClass.getSalary());
        holder.text_age.setText(aClass.getAge());

    }

    @Override
    public int getItemCount() {
        return listModelClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_id;
        TextView text_name;
        TextView text_salary;
        TextView text_age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_id = itemView.findViewById(R.id.text_id);
            text_name = itemView.findViewById(R.id.text_name);
            text_salary = itemView.findViewById(R.id.text_salary);
            text_age = itemView.findViewById(R.id.text_age);
        }

    }
}
