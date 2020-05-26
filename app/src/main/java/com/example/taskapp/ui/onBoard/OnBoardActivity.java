package com.example.taskapp.ui.onBoard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.taskapp.MainActivity;
import com.example.taskapp.R;

import com.google.android.material.tabs.TabLayout;

public class OnBoardActivity extends AppCompatActivity {
    ViewPager viewPager;
    Button fab_skip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        fab_skip = findViewById(R.id.btn_skip);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager,true);

        fab_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveIsShown();
                startActivity(new Intent(OnBoardActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void saveIsShown() {
        SharedPreferences preferences=OnBoardActivity.this.getSharedPreferences("settings", Context.MODE_PRIVATE);
        preferences.edit().putBoolean("isShown",true).apply();

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("pos", position);
            BoardFragment fragment = new BoardFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}