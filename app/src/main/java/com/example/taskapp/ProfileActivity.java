package com.example.taskapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
   private EditText editName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_activity);
editName=findViewById(R.id.editName);
     //getData();
     getData2();
    }

    private void getData2() {
     String uid=FirebaseAuth.getInstance().getUid();
     FirebaseFirestore.getInstance().collection("users").document(uid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
         @Override
         public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
             if(documentSnapshot.exists()){
                 User user=documentSnapshot.toObject(User.class);
                 editName.setText(user.getName());

             }
         }
     });




    }

    private void getData() {
        String uid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore.getInstance().collection("users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


             if (task.isSuccessful()){
                 User user=task.getResult().toObject(User.class);
                 editName.setText(user.getName());

             }


               // if (task.isSuccessful()){
               //     String name =task.getResult().getString("name");
               //     editName.setText(name);

               // }
            }
        });


    }


    public void beka (View view) {
       String uid = FirebaseAuth.getInstance().getUid();
       String name=editName.getText().toString().trim();

       User user = new User(name, 22, null);

       //  Map<String,Object>map=new HashMap<>();
       //  map.put("name","bekzat");
       //  map.put("year",2012);


       FirebaseFirestore.getInstance().collection("users").document(uid).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {


               if (task.isSuccessful()){

                   Toast.makeText(ProfileActivity.this,"Успешно",Toast.LENGTH_LONG).show();

               }else {Toast.makeText(ProfileActivity.this,"Ошибка",Toast.LENGTH_LONG).show();}










           }
       });
       {


       }


   }
}
