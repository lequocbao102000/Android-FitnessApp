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
    ArrayList<BMI> bmiArrayList;
    BMIAdapter bmiAdapter;
    DBHelper dbHelper;


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
        Calendar calendar=Calendar.getInstance();
        int monthnow = calendar.get(Calendar.MONTH)+1;
        txtdate.setText(calendar.get(Calendar.DAY_OF_MONTH)+"/"+monthnow+"/"+calendar.get(Calendar.YEAR));

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
        int monthedit = month+1;
        String date=dayOfMonth+ "/" +monthedit+ "/" + year;
        txtdate.setText(date);
    }
}