package com.example.fitnesstest.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fitnesstest.Class.DBHelper;
import com.example.fitnesstest.Class.Exercise;
import com.example.fitnesstest.Class.ExerciseAdapter;
import com.example.fitnesstest.R;
import com.example.fitnesstest.Class.Utils;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {
    ListView listViewExercise;
    ArrayList<Exercise> exerciseArrayList;
    ArrayAdapter<Exercise> exerciseArrayAdapter;
    Utils utils;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        listViewExercise=findViewById(R.id.listviewexercise);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Exercise");
        Intent intent=getIntent();
        utils=new Utils(this);
        dbHelper=new DBHelper(this);
        exerciseArrayList=dbHelper.getExercise(Integer.parseInt(intent.getSerializableExtra("ma").toString()));
        exerciseArrayAdapter=new ExerciseAdapter(this,exerciseArrayList);
        listViewExercise.setAdapter(exerciseArrayAdapter);

        listViewExercise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1=new Intent(ExerciseActivity.this,StartWorkAct.class);
                intent1.putExtra("exercise",exerciseArrayList.get(position));
                startActivity(intent1);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}