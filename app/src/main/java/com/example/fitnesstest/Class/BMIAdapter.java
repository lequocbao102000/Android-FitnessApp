package com.example.fitnesstest.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fitnesstest.R;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class BMIAdapter extends ArrayAdapter<BMI> {

    private Context context;
    private List<BMI> bmiList;

    public BMIAdapter(@NonNull Context context, @NonNull List<BMI> objects) {
        super(context, 0, objects);
        this.context=context;
        this.bmiList=bmiList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(R.layout.list_daily_bmi,parent,false);

        ImageView ImageView=convertView.findViewById(R.id.dailybmihinh);
        TextView textViewbmi=convertView.findViewById(R.id.dailybmi);
        TextView textViewcao=convertView.findViewById(R.id.dailybmicao);
        TextView textViewnang=convertView.findViewById(R.id.dailybminang);

        BMI bmi=getItem(position);

        ImageView.setImageResource(bmi.getHinh());
        textViewbmi.setText("BMI:  " + String.valueOf(bmi.getBmi()));
        textViewcao.setText("-Height-\n "+String.valueOf(bmi.getCao())+" cm");
        textViewnang.setText("-Weight-\n"+String.valueOf(bmi.getNang())+" kg");

        return convertView;
    }
}
