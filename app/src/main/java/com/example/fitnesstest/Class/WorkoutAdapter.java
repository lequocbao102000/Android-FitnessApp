package com.example.fitnesstest.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fitnesstest.R;

import java.util.List;

public class WorkoutAdapter extends ArrayAdapter<Workout> {
    private Context context;
    private List<Workout> workoutList;
    public WorkoutAdapter(@NonNull Context context, @NonNull List<Workout> objects) {
        super(context, 0, objects);
        this.context=context;
        this.workoutList=workoutList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.list_workout,parent,false);
        ImageView imageView=convertView.findViewById(R.id.imageworkoutgroup);
        TextView textView=convertView.findViewById(R.id.txtworkoutgroup);
        Workout workout=getItem(position);
        imageView.setImageResource(workout.getImage());
        textView.setText(workout.getName());
        return convertView;
    }
}
