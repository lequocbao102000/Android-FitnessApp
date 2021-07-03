package com.example.fitnesstest.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesstest.Class.DBHelper;
import com.example.fitnesstest.Class.Exercise;
import com.example.fitnesstest.R;

import java.util.Calendar;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class StartWorkAct extends AppCompatActivity {

    TextView intropage, subcriptiondetails, namedetails, timerValue;
    GifImageView gifImageView;
    View divpage;
    LinearLayout fitone;
    ImageView imgTimer,level;
    Exercise exercise;
    DBHelper dbHelper;

    private static final long START_TIME_IN_MILLIS = 50000;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    Animation btthree, bttfour, ttbone, ttbtwo, alphago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_work);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");
        dbHelper = new DBHelper(this);
        Calendar calendar=Calendar.getInstance();
        int monthnow = calendar.get(Calendar.MONTH)+1;
        final String datenow=calendar.get(Calendar.DAY_OF_MONTH)+"/"+monthnow+"/"+calendar.get(Calendar.YEAR);

        AnhXa();
        Intent intent=getIntent();
        exercise=(Exercise) intent.getSerializableExtra("exercise");
        gifImageView.setImageResource(exercise.getGif());
        namedetails.setText(exercise.getName());
        subcriptiondetails.setText(exercise.getDesription());
        level.setImageResource(exercise.getLevel());

        Animation();

        startTimer(datenow,exercise.getName(),String.valueOf(exercise.getGif()),String.valueOf(exercise.getLevel()));
    }

    private void AnhXa() {
        intropage =  findViewById(R.id.intropage);
        subcriptiondetails =  findViewById(R.id.subcriptiondetails);
        namedetails = findViewById(R.id.name_details);
        timerValue =  findViewById(R.id.timerValue);
        level = findViewById(R.id.imagelevel);
        gifImageView=findViewById(R.id.gif_details);


        divpage =  findViewById(R.id.divpage);
        fitone =  findViewById(R.id.fitone);
        imgTimer =  findViewById(R.id.imgtimer);
    }

    private void Animation() {
        //Load Ahieu ung
        btthree = AnimationUtils.loadAnimation(this, R.anim.btthree);
        bttfour = AnimationUtils.loadAnimation(this, R.anim.bttfour);
        ttbone = AnimationUtils.loadAnimation(this, R.anim.ttbone);
        ttbtwo = AnimationUtils.loadAnimation(this, R.anim.ttbtwo);
        alphago = AnimationUtils.loadAnimation(this, R.anim.alphago);
        //gan hieu ung
        fitone.startAnimation(ttbone);
        intropage.startAnimation(ttbtwo);
        divpage.startAnimation(ttbtwo);
        timerValue.startAnimation(alphago);
        imgTimer.startAnimation(alphago);
    }
    private void startTimer(final String date, final String name, final String gif, final String level){
        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                dbHelper.insertDailyEx(date, name, gif, level);
                callToast();
            }
        }.start();
        mTimerRunning = true;
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds) ;
        timerValue.setText(timeLeft);

    }
    private void callToast()
    {
        Toast.makeText(this,"Done Exercise !",Toast.LENGTH_SHORT).show();
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