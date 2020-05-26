package com.example.taskapp.ui.home;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import com.example.taskapp.App;
import com.example.taskapp.FormActivity;
import com.example.taskapp.OnItemClickListener;
import com.example.taskapp.R;
import com.example.taskapp.TaskAdapter;
import com.example.taskapp.models.Task;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.R.layout.simple_list_item_1;


public class HomeFragment extends Fragment {



    private RecyclerView recyclerView;

    private List<Task> list = new ArrayList<>();
    private TaskAdapter adapter;

    private int pos;

    Task task;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);


        initList();












        return root;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        list = new ArrayList<>();
        App.getInstance().getDatabase().taskDao().getAllLive().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(final List<Task> tasks) {
                list.clear();
                list.addAll(tasks);
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
            }
        });



    }

    private void initList() {
        list = App.getInstance().getDatabase().taskDao().getAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                pos=position;
                task = list.get(position);
                Toast.makeText(getContext(), task.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), FormActivity.class);
                intent.putExtra("task", task);
                startActivityForResult(intent,101);
            }

            @Override
            public void onItemLongClick(final int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("");
                builder.setMessage("Вы хотите удалить это слово?");


                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        task = list.get(position);
                        App.getInstance().getDatabase().taskDao().delete(task);
                        list.remove(task);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Нет", null);


                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });
    }










    public void sortList(){
        list.clear();
        list.addAll(App.getInstance().getDatabase().taskDao().getAllsorted());
        adapter.notifyDataSetChanged();
    }
    public void initialList(){
        list.clear();
        list.addAll(App.getInstance().getDatabase().taskDao().getAll());
        adapter.notifyDataSetChanged();
    }







    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK ) {
            Task task = (Task) data.getSerializableExtra("task");
            if (requestCode==100) {
                list.add(task);
            }else if (requestCode==101){
                list.set(pos,task);
            }
            adapter.notifyDataSetChanged();


        }
    }












    }








