package com.example.fitnesstest.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.fitnesstest.Fragment.BMIFragment;
import com.example.fitnesstest.Fragment.ClassicFragment;
import com.example.fitnesstest.Class.DBHelper;
import com.example.fitnesstest.Fragment.DailyFragment;
import com.example.fitnesstest.Fragment.MeFragment;
import com.example.fitnesstest.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WorkoutAct extends AppCompatActivity {


    BottomNavigationView navView;
    boolean status=false;
    MenuItem menuItem;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        navView=findViewById(R.id.nagivationmenu);

        DBHelper dbHelper=new DBHelper(this);
        //Drop truoc khi create
        dbHelper.dropTable();

        dbHelper.createTable();
        if(!dbHelper.check())
        {
            dbHelper.insertWorkout();
            dbHelper.insertExercise();
        }
        LoadFragment(new ClassicFragment());
        navView.setSelectedItemId(R.id.menuclassic);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.menuclassic:
                        fragment=new ClassicFragment();
                        LoadFragment(fragment);
                        return true;
                    case R.id.menudaily:
                        fragment=new DailyFragment();
                        LoadFragment(fragment);
                        return true;
                    case R.id.menuibm:
                        fragment=new BMIFragment();
                        LoadFragment(fragment);
                        return true;
                    case R.id.menume:
                        fragment=new MeFragment();
                        LoadFragment(fragment);
                        return true;

                }
                return  false;
            }
        });






    }
    private void LoadFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main2,fragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}