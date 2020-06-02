package com.example.taskapp.ui.firestore;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.models.Task;

import java.util.ArrayList;


public  class AdapterF extends RecyclerView.Adapter<AdapterF.ViewHolder>{
    ArrayList <Task>  list;

    public AdapterF(ArrayList<Task> list) {
        this.list = list;

      //  list=new ArrayList<>();
    }
  //  public void update(ArrayList<Task> list1){
    //       list = list1;
    //        notifyDataSetChanged();
     //     }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fire_story,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Onbind(list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
       TextView textView;
          TextView desc;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);



           textView = itemView.findViewById(R.id.listener);
          desc=itemView.findViewById(R.id.desc);

       }



         public void Onbind(Task task) {

         textView.setText(task.getTitle());
         desc.setText(task.getDesc());
         }

     }
}


   /*public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textTitle);
    }

    public void onbind(Task task){
        textView.setText(task.getTitle());


    }
*/