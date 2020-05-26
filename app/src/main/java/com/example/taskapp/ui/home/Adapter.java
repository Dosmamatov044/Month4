/*package com.example.taskapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.models.Task;
import com.example.taskapp.ui.home.ViewHolder;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<Task> arrayList;
    public Adapter(ArrayList<Task> arrayList) {

       this.arrayList=arrayList;

        //  arrayList = new ArrayList<>();
    }



    // public void update(ArrayList<Task> list){
    //    arrayList = list;
   //     notifyDataSetChanged();
  //  }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onbind(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}*/