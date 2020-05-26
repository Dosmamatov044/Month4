package com.example.taskapp.ui.onBoard;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorStateListDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taskapp.MainActivity;
import com.example.taskapp.R;


public class BoardFragment extends Fragment {

    TextView textTitle, textDesc;
    Button button;
    LinearLayout color;

    public BoardFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textTitle = view.findViewById(R.id.textTitle);
        textDesc = view.findViewById(R.id.textDesc);
        button = view.findViewById(R.id.button_Getstart);
     // color=view.findViewById(R.id.rr);
        ImageView image = view.findViewById(R.id.imageView);
        int pos = getArguments().getInt("pos");
        switch (pos){
            case 0:


             //   image.setImageResource(R.drawable.oneph);
                textTitle.setText("Hi");
          //  color.setBackgroundColor(Color.RED);
                button.setVisibility(View.GONE);
                break;
            case 1:
             //   image.setImageResource(R.drawable.twoph);
                textTitle.setText("how are you");
                ;
                button.setVisibility(View.GONE);
                break;
            case 2:
            //    image.setImageResource(R.drawable.threeph);
                textTitle.setText("I am bad");
            //  color.setBackgroundColor(333);
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        saveIsShown();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });


                break;
        }
    }

private  void saveIsShown(){
  SharedPreferences preferences=getActivity().getSharedPreferences("settings",Context.MODE_PRIVATE);
  preferences.edit().putBoolean("isShown",true).apply();

}


}