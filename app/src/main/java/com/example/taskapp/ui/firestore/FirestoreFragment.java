package com.example.taskapp.ui.firestore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.taskapp.R;
import com.example.taskapp.models.Task;

import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;



public class FirestoreFragment extends Fragment {

    AdapterF Adapter;
     ArrayList <Task> list=new ArrayList<>();






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_firestore, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




       RecyclerView  recyclerView = view.findViewById(R.id.FireStore_recycler_view);





       //         String name = getArguments().getString("key");




                Adapter = new AdapterF(list);
                recyclerView.setAdapter(Adapter);
LoadData();






            }

            private void LoadData() {

           FirebaseFirestore.getInstance().collection("tasks").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
               @Override
               public void onSuccess(QuerySnapshot Snapshots) {
              list.addAll(Snapshots.toObjects(Task.class));
              Adapter.notifyDataSetChanged();

               }
           });


            }

private  void loadData2(){
  FirebaseFirestore.getInstance().collection("tasks").addSnapshotListener(new EventListener<QuerySnapshot>() {
      @Override
      public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
    if(queryDocumentSnapshots!=null){
        list.addAll(queryDocumentSnapshots.toObjects(Task.class));
        Adapter.notifyDataSetChanged();


    }


      }
  });


}


















}
