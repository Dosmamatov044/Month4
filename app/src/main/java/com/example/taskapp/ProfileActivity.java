package com.example.taskapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.taskapp.models.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private EditText edit_name, edit_age;
    ImageView image1;

    Button button1;
ProgressBar progressBar;
     private static final int PICK_IMAGE=100;
  private Uri imageUri;
  private String avatarUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        image1 =findViewById(R.id.profile_image);



progressBar=findViewById(R.id.Progress_bar);

        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        progressBar.setVisibility(View.GONE);
//        getData();
        getData2();







    image1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        openGallery();
      }
       });



        Button save = findViewById(R.id.btn_profile_save);
        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String uid = FirebaseAuth.getInstance().getUid();
                                        String name = edit_name.getText().toString().trim();
                                        String age = edit_age.getText().toString().trim();

                                        User users = new User(name, age,avatarUrl);


                                        FirebaseFirestore.getInstance().collection("users")
                        .document(uid)
                        .set(users)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ProfileActivity.this, "Данные сохранены",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ProfileActivity.this, "Ошибка сохранения",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                Button save = findViewById(R.id.btn_profile_save);
                save.setVisibility(View.GONE);
            }
        });


















    }

        private void openGallery() {


       //Intent gallery=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
      Intent gallery=new Intent();
      gallery.setType("image/*");
      gallery.setAction(Intent.ACTION_PICK);
       startActivityForResult(gallery,PICK_IMAGE);
    }

     @Override
      protected void onActivityResult(int requestCode,int resultCode,Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
    if (resultCode==RESULT_OK && requestCode==PICK_IMAGE &&data.getData()!=null){


//if(image1!=null) {

    //imageUri = data.getData();

    Glide.with(this).load(data.getData()).circleCrop().into(image1);
    //image1.setImageURI(data.getData());
upload(data.getData());

}




    }

  //  }

    private void upload(Uri data) {
        progressBar.setVisibility(View.VISIBLE);
        String uid=FirebaseAuth.getInstance().getUid();                                              //
        final StorageReference reference= FirebaseStorage.getInstance().getReference().child(uid+"avatar.jpg");
        UploadTask uploadTask=reference.putFile(data);
       uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                return reference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                if (task.isSuccessful()){

                   // Toast.makeText(ProfileActivity.this,"успешно",Toast.LENGTH_LONG).show();
                 Uri downloadUrl=task.getResult();
                 Log.e("Profile","downloadUrl"+downloadUrl);
                avatarUrl=downloadUrl.toString();
                 upDateAvatarInfo(downloadUrl);
                }else {
                    Toast.makeText(ProfileActivity.this,"провал",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }




       });



    }

    private void upDateAvatarInfo(Uri downloadUrl) {
        String uid=FirebaseAuth.getInstance().getUid();
FirebaseFirestore.getInstance().collection("users").document(uid).update("avatar",downloadUrl.toString())
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){

                    Toast.makeText(ProfileActivity.this,"успешно",Toast.LENGTH_LONG).show();



                }else {
                    Toast.makeText(ProfileActivity.this,"провал",Toast.LENGTH_LONG).show();
                }


            }
        });


    }


    private void getData2() {
        String uid = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore.getInstance().collection("users")
                .document(uid)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        if (documentSnapshot.exists()) {
                            User users = documentSnapshot.toObject(User.class);
                            edit_name.setText(users.getName());
                            edit_age.setText(users.getAge());
                          showImage(users.getAvatar());


                        }
                    }
                });
    }

    private void showImage(String avatar) {
        Glide.with(this).load(avatar).circleCrop().into(image1);

    }

    //public void onClick(View view) {

     //   openGallery();
    //}
}

//    private void getData() {
//        String uid= FirebaseAuth.getInstance().getUid();
//        FirebaseFirestore.getInstance().collection("users")
//                .document(uid)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                        if (task.isSuccessful()){
//                            Users users=task.getResult().toObject(Users.class);
//                            edit_name.setText(users.getName());
//                            edit_age.setText(users.getAge());
//                            edit_phone.setText(users.getPhone());
//                            edit_email.setText(users.getEmail());
//                            edit_address.setText(users.getAddress());
//
//                        }
//                    }
//                });

//    }

   //1. Добавить imageView в ProfileActivity, по нажатию открыть галерею телефона, выбрать картинку и поставить в imageView






               // if (task.isSuccessful()){
               //     String name =task.getResult().getString("name");
               //     editName.setText(name);

               // }





       //  Map<String,Object>map=new HashMap<>();
       //  map.put("name","bekzat");
       //  map.put("year",2012);


