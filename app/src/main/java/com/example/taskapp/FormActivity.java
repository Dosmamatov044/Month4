package com.example.taskapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.example.taskapp.models.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class FormActivity extends AppCompatActivity implements View.OnClickListener {

  EditText editTitle;
     EditText editDesc;
    Task task;
    SharedPreferences preferences;
    final String SAVE_TEXT = "saved_text";
    final String LOAD_TEXT = "load_text";
    Button button;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);
        button = findViewById(R.id.beka);
        loadText();





        task = (Task) getIntent().getSerializableExtra("task");
        if (task != null) {
            editTitle.setText(task.getTitle());
            editDesc.setText(task.getDesc());

        }
    }
    public void onClick(View view) {
        String title = editTitle.getText().toString().trim();
        String desc = editDesc.getText().toString().trim();





              if (TextUtils.isEmpty(title)) {
            //editTitle.animate().scaleY(1.5f).start();
             editTitle.setError("Заполните это поле");return; }

              if (TextUtils.isEmpty(desc)) {
            editDesc.setError("Заполните это поле");return;}


        if (task != null) {
            task.setTitle(title);
            task.setDesc(desc);

            App.getInstance().getDatabase().taskDao().update(task);
        } else {
            task = new Task(title, desc);
            App.getInstance().getDatabase().taskDao().insert(task);
        }
        Intent intent = new Intent();
        intent.putExtra("task", task);
        setResult(RESULT_OK, intent);
        finish();
    }





    private void loadText() {
        preferences = getPreferences(MODE_PRIVATE);
        String savedText = preferences.getString(SAVE_TEXT, "");
        String loadText = preferences.getString(LOAD_TEXT, "");
        editTitle.setText(savedText);
        editDesc.setText(loadText);
        Toast.makeText(FormActivity.this, "Данные считаны", Toast.LENGTH_SHORT).show();
    }

    private void saveText() {
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = preferences.edit();
        ed.putString(SAVE_TEXT, editTitle.getText().toString());
        ed.putString(LOAD_TEXT, editDesc.getText().toString());
        ed.commit();
        Toast.makeText(FormActivity.this, "Ваш текст Сохранен!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }





}


































/*public class FormActivity extends AppCompatActivity {
    private EditText editTitle;
    private EditText editDesc;
   private  EditText editCount;
      private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("New Task");

        }

        editCount=findViewById(R.id.count);
        editTitle = findViewById(R.id.editTitle);
        editDesc = findViewById(R.id.editDesc);



    }
    public void save (View view) {
        String title = editTitle.getText().toString().trim();
        String desc = editDesc.getText().toString().trim();
         String count= editCount.getText().toString().trim();

        Task task = new Task(desc,title,count);
       
       
       // task.setDesc(desc);
      //  task.setTitle(title);
      //  task.setHow_are_you(count);
        App.getInstance().getDatabase().taskDao().insert(task);
     //   Intent intent = new Intent();
     //   intent.putExtra("task", task);
      //  setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setResult(RESULT_CANCELED);
        finish();
        return true;
    }


}*/
