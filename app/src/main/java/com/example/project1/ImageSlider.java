package com.example.project1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;
import java.util.TimerTask;


public class ImageSlider extends Fragment {
    //creating object of viewpager
    ViewPager viewPager;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 1000; //delay in milisecond before task is executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task execution
    int[] images = {R.drawable.doramon,R.drawable.jerry,R.drawable.micky,R.drawable.tiger};

    //creating object of viewpagerAdapter
    ViewPagerAdapter viewPagerAdapter;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ImageSlider() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =   inflater.inflate(R.layout.fragment_image_slider, container, false);



        //Initializing the viewpager object
        viewPager = view.findViewById(R.id.viewpager);

        //initializing viewpagerAdapter
        viewPagerAdapter = new ViewPagerAdapter(getContext(),images);

        //Adding the Adapter to the viewPager
        viewPager.setAdapter(viewPagerAdapter);


        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == images.length - 1){
                    currentPage=0;
                }
                viewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }

        },DELAY_MS,PERIOD_MS);

        return view;
    }
}