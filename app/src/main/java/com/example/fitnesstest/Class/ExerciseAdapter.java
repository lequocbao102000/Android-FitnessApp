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

import pl.droidsonroids.gif.GifImageView;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {
    private Context context;
    private List<Exercise> exerciseList;
    public ExerciseAdapter(@NonNull Context context, @NonNull List<Exercise> objects) {
        super(context, 0, objects);
        this.context=context;
        this.exerciseList=exerciseList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.list_exercise,parent,false);

        GifImageView gifImageView=convertView.findViewById(R.id.gif_exercise);
        TextView textView=convertView.findViewById(R.id.name_exercise);
        ImageView imageView=convertView.findViewById(R.id.level_exercise);
        Exercise exercise=getItem(position);

        gifImageView.setImageResource(exercise.getGif());
        textView.setText(exercise.getName());
        imageView.setImageResource(exercise.getLevel());

        return convertView;
    }
}
