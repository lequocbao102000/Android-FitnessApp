package com.example.fitnesstest.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fitnesstest.Activity.ExerciseActivity;
import com.example.fitnesstest.Class.DBHelper;
import com.example.fitnesstest.R;
import com.example.fitnesstest.Class.Utils;
import com.example.fitnesstest.Class.Workout;
import com.example.fitnesstest.Class.WorkoutAdapter;

import java.util.ArrayList;


public class ClassicFragment extends Fragment {
    TextView titlepage, subtitlepage, intropage, subintropage;
    View divpage;
    Animation bttone, bttwo;
    ListView lvworkout;
    ArrayList<Workout> workoutArrayList;
    WorkoutAdapter workoutAdapter;
    Workout workout;
    Utils utils;
    DBHelper dbHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        utils=new Utils(getContext());
        dbHelper=new DBHelper(getContext());
        return inflater.inflate(R.layout.fragment_classic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Load hieu ung
        bttone = AnimationUtils.loadAnimation(getContext(),R.anim.bttone);
        bttwo = AnimationUtils.loadAnimation(getContext(),R.anim.bttwo);

        titlepage =  view.findViewById(R.id.titlepage);
        subtitlepage =  view.findViewById(R.id.subtitlepage);
        intropage =  view.findViewById(R.id.intropage);
        subintropage =  view.findViewById(R.id.subintropage);
        divpage = (View) view.findViewById(R.id.divpage);
        lvworkout=view.findViewById(R.id.listworkoutgroup);


        //gan hoat anh
        titlepage.startAnimation(bttone);
        subtitlepage.startAnimation(bttone);
        divpage.startAnimation(bttone);
        lvworkout.startAnimation(bttwo);

        intropage.startAnimation(bttwo);
        subintropage.startAnimation(bttwo);
        lvworkout=view.findViewById(R.id.listworkoutgroup);
        workoutArrayList=dbHelper.getALLWorkout();
        workoutAdapter=new WorkoutAdapter(getContext(),workoutArrayList);
        lvworkout.setAdapter(workoutAdapter);

        lvworkout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ExerciseActivity.class);
                intent.putExtra("ma",position);
                startActivity(intent);
            }

        });
    }


    @Override
    public void onPause() {
        super.onPause();
        utils.WriteToFile(workoutArrayList);
    }
}