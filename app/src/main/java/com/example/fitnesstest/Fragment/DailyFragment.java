package com.example.fitnesstest.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitnesstest.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class DailyFragment extends Fragment {
    TabItem tabexcercise , tabbmi;
    TabLayout tabLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabexcercise = view.findViewById(R.id.tabexcercise);
        tabbmi = view.findViewById(R.id.tabbmi);
        tabLayout = view.findViewById(R.id.tablayout);
        LoadFragment (new Daily_ExcerciseFragment());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment;
                switch (tab.getPosition()){
                    case 0:
                        fragment=new Daily_ExcerciseFragment();
                        LoadFragment(fragment);
                        break;

                    case 1:
                        fragment=new Daily_BMIFragment();
                        LoadFragment(fragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }

    private void LoadFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.dailylayout,fragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        fragmentTransaction.show(fragment);
    }

}