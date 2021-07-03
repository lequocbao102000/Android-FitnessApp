package com.example.fitnesstest.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnesstest.Activity.BMIDetailsActivity;
import com.example.fitnesstest.Class.DBHelper;
import com.example.fitnesstest.R;

import java.text.DecimalFormat;
import java.util.Calendar;


public class BMIFragment extends Fragment {
    Button btncal,btnreset;
    EditText txtcao,txtnang;
    ImageView bmilv1,bmilv2,bmilv3,bmilv4,bmilv5;
    TextView lblkq,txtkq;
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
        return inflater.inflate(R.layout.fragment_b_m_i, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtcao=view.findViewById(R.id.textcao);
        txtnang=view.findViewById(R.id.textnang);
        btncal=view.findViewById(R.id.btntinhtoan);
        btnreset=view.findViewById(R.id.btnreset);
        bmilv1=view.findViewById(R.id.bmilv1);
        bmilv2=view.findViewById(R.id.bmilv2);
        bmilv3=view.findViewById(R.id.bmilv3);
        bmilv4=view.findViewById(R.id.bmilv4);
        bmilv5=view.findViewById(R.id.bmilv5);
        lblkq=view.findViewById(R.id.lblketqua);
        txtkq=view.findViewById(R.id.txtketqua);

        Calendar calendar=Calendar.getInstance();
        int monthnow = calendar.get(Calendar.MONTH)+1;
        final String datenow=calendar.get(Calendar.DAY_OF_MONTH)+"/"+monthnow+"/"+calendar.get(Calendar.YEAR);


        lblkq.setVisibility(View.INVISIBLE);
        txtkq.setVisibility(View.INVISIBLE);

        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtcao.getText().toString().length()==0||txtnang.getText().toString().length()==0) {
                    Toast.makeText(getContext(),"Nhập đầy đủ chiều cao và cân nặng",Toast.LENGTH_SHORT).show();
                }
                else {
                    DecimalFormat df = new DecimalFormat("0.00");
                    final double cao = Double.parseDouble(txtcao.getText().toString()) / 100;
                    final double nang = Double.parseDouble(txtnang.getText().toString());
                    final double giatribmi = nang / (cao * cao);
                    lblkq.setVisibility(View.VISIBLE);
                    txtkq.setVisibility(View.VISIBLE);
                    txtkq.setText(df.format(giatribmi));
                    final  double bmi = Double.parseDouble(df.format(giatribmi));
                    int hinh=0;
                    if (bmi<18.5){
                        hinh=R.drawable.bminguoi1;
                    }
                    else if(bmi>=18.5&&bmi<25){
                        hinh=R.drawable.bminguoi2;
                    }
                    else if(bmi>=25&&bmi<30){
                        hinh=R.drawable.bminguoi3;
                    }
                    else if(bmi>=30&&bmi<35){
                        hinh=R.drawable.bminguoi4;
                    }
                    else{
                        hinh=R.drawable.bminguoi5;
                    }
                    dbHelper.insertDailyBMI(datenow,cao*100,nang,bmi,String.valueOf(hinh));
                }
            }
        });
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtcao.setText("");
                txtnang.setText("");
                lblkq.setVisibility(View.INVISIBLE);
                txtkq.setVisibility(View.INVISIBLE);
            }
        });

        bmilv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), BMIDetailsActivity.class);
                intent.putExtra("bmilv",1);
                startActivity(intent);
            }
        });

        bmilv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),BMIDetailsActivity.class);
                intent.putExtra("bmilv",2);
                startActivity(intent);
            }
        });

        bmilv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),BMIDetailsActivity.class);
                intent.putExtra("bmilv",3);
                startActivity(intent);
            }
        });

        bmilv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),BMIDetailsActivity.class);
                intent.putExtra("bmilv",4);
                startActivity(intent);
            }
        });

        bmilv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),BMIDetailsActivity.class);
                intent.putExtra("bmilv",5);
                startActivity(intent);
            }
        });
    }


}