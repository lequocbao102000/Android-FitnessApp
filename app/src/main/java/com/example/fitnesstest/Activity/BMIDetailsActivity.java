package com.example.fitnesstest.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnesstest.R;

public class BMIDetailsActivity extends AppCompatActivity {
    ImageView imgnguoi;
    TextView txtname,txtcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("BMI Details");
        imgnguoi = findViewById(R.id.bminguoi);
        txtname =  findViewById(R.id.bminame);
        txtcontent = findViewById(R.id.bmicontent);
        int bmilv=Integer.parseInt(getIntent().getSerializableExtra("bmilv").toString());

        if (bmilv==1){
            imgnguoi.setImageResource(R.drawable.bminguoi1);
            txtname.setText("UNDERWEIGHT");
            txtcontent.setText(R.string.advice_underweight);
            txtname.setTextColor(Color.parseColor("#93b4d7"));
        }
        else if (bmilv==2){
            imgnguoi.setImageResource(R.drawable.bminguoi2);
            txtname.setText("NORMAL");
            txtcontent.setText(R.string.advice_normal);
            txtname.setTextColor(Color.parseColor("#8fc69f"));
        }
        else if (bmilv==3){
            imgnguoi.setImageResource(R.drawable.bminguoi3);
            txtname.setText("OVERWEIGHT");
            txtcontent.setText(R.string.advice_overweight);
            txtname.setTextColor(Color.parseColor("#fad548"));
        }
        else if (bmilv==4){
            imgnguoi.setImageResource(R.drawable.bminguoi4);
            txtname.setText("OBESE");
            txtcontent.setText(R.string.advice_obses);
            txtname.setTextColor(Color.parseColor("#e8975f"));
        }
        else{
            imgnguoi.setImageResource(R.drawable.bminguoi5);
            txtname.setText("EXTREMLY OBESE");
            txtcontent.setText(R.string.advice_extremlyobses);
            txtname.setTextColor(Color.parseColor("#d65c5a"));
        }

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