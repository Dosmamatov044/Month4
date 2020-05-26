package com.example.taskapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import com.example.taskapp.models.Task;
import com.example.taskapp.ui.home.HomeFragment;


import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> list;
    private OnItemClickListener onItemClickListener;



    public TaskAdapter(List<Task> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));




        holder.setIsRecyclable(true);
        if (position % 2 == 0) {
            holder.layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.layout.setBackgroundColor(Color.parseColor("#848482"));
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private TextView textDesc;




        private LinearLayout layout;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);


            layout = itemView.findViewById(R.id.layout);
            textDesc = itemView.findViewById(R.id.textDesc);


            textTitle=itemView.findViewById(R.id.textTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onItemLongClick(getAdapterPosition());
                    return true;
                }
            });
        }





        public void bind(final Task task) {
            try {



                textTitle.setText(task.getTitle());
                textDesc.setText(task.getDesc());



            }catch (Exception e){}
        }
    }




}
