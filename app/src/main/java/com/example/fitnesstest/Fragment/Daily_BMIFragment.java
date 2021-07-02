package com.example.fitnesstest.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fitnesstest.Class.BMI;
import com.example.fitnesstest.Class.BMIAdapter;
import com.example.fitnesstest.Class.DBHelper;
import com.example.fitnesstest.Class.WorkoutAdapter;
import com.example.fitnesstest.R;

import java.util.ArrayList;
import java.util.Calendar;



public class Daily_BMIFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    TextView txtdate;
    ListView listViewbmi;
    ImageView previous,next;
    ArrayList<BMI> bmiArrayList;
    BMIAdapter bmiAdapter;
    DBHelper dbHelper;
    Calendar calendar=Calendar.getInstance();
    int monthnow = calendar.get(Calendar.MONTH)+1;
    int daynow = calendar.get(Calendar.DAY_OF_MONTH);
    int yearnow = calendar.get(Calendar.YEAR);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dbHelper=new DBHelper(getContext());
        return inflater.inflate(R.layout.fragment_daily__b_m_i, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtdate = view.findViewById(R.id.date);
        listViewbmi = view.findViewById(R.id.listdailybmi);
        previous = view.findViewById(R.id.previousdate);
        next = view.findViewById(R.id.nextdate);

        txtdate.setText(daynow+"/"+monthnow+"/"+yearnow);

        bmiArrayList=dbHelper.getDailyBMI(txtdate.getText().toString());
        bmiAdapter=new BMIAdapter(getContext(),bmiArrayList);
        listViewbmi.setAdapter(bmiAdapter);

        txtdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDiaLog();
            }
        });

        txtdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bmiArrayList=dbHelper.getDailyBMI(txtdate.getText().toString());
                bmiAdapter=new BMIAdapter(getContext(),bmiArrayList);
                listViewbmi.setAdapter(bmiAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtdate.setText(previousdate());
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtdate.setText(nextdate());
            }
        });


    }


    private  void showDatePickerDiaLog(){
        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        monthnow = month+1;
        daynow=dayOfMonth;
        yearnow=year;
        String date=daynow+ "/" +monthnow+ "/" + yearnow;
        txtdate.setText(date);
    }

    public String previousdate(){
        switch (monthnow){
            case 5: case 7: case 8: case 10: case 12: {
                if (daynow > 1) {
                    daynow = daynow - 1;
                } else {
                    daynow = 30;
                    monthnow -= 1;
                }
                break;
            }

            case 2: case 4: case 6: case 9: case 11: {
                if (daynow > 1) {
                    daynow = daynow - 1;
                } else {
                    daynow = 31;
                    monthnow -= 1;
                }
                break;
            }

            case 1: {
                if (daynow > 1) {
                    daynow = daynow - 1;
                } else {
                    daynow = 31;
                    monthnow = 12;
                    yearnow -= 1;
                }
                break;
            }

            case 3:{
                if (yearnow%4==0){
                    if (daynow>1){
                        daynow-=1;
                    }
                    else {
                        daynow=29;
                        monthnow-=1;
                    }
                }
                else{
                    if (daynow>1){
                        daynow-=1;
                    }
                    else {
                        daynow=28;
                        monthnow-=1;
                    }
                }
                break;
            }

        }
        return daynow+ "/" +monthnow+ "/" + yearnow;


    }

    public String nextdate() {
        switch (monthnow){
            case 1: case 3: case 5: case 7: case 8: case 10:{
                if(daynow<=30)
                    daynow+=1;
                else {
                    daynow=1;
                    monthnow+=1;
                }
                break;
            }
            case 4: case 6: case 9: case 11:{
                if (daynow<=29)
                    daynow+=1;
                else {
                    daynow=1;
                    monthnow+=1;
                }
                break;
            }
            case 12:{
                if(daynow<=30)
                    daynow+=1;
                else {
                    daynow=1;
                    monthnow=1;
                    yearnow+=1;
                }
                break;
            }
            case 2:{
                if(yearnow%4==0){
                    if (daynow<=28)
                        daynow+=1;
                    else {
                        daynow=1;
                        monthnow+=1;
                    }
                }
                else {
                    if(daynow<=27)
                        daynow+=1;
                    else {
                        daynow=1;
                        monthnow+=1;
                    }
                }
                break;
            }
        }
        return daynow+ "/" +monthnow+ "/" + yearnow;
    }
}