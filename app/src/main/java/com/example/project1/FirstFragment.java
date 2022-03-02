package com.example.project1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.project1.Recyclerview.MyRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;


public class FirstFragment extends Fragment {
    RecyclerView myRecyclerAdapter;
    ArrayList<Recycledata> Imageurl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        url.add(0,"https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg");
//        url.add(1,"https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Jordan_Lipofsky.jpg/170px-Jordan_Lipofsky.jpg");
//        url.add(2,"http://cdn.playbuzz.com/cdn/62b7af36-65b7-41aa-8db2-e34fd8a76acf/62c5efd3-fa55-464b-8ee5-9a3e2543c830.jpg");
//        url.add(3,"http://cdn.playbuzz.com/cdn/fa415381-3e73-4678-915d-7abf8983ce09/813d91c3-f7c9-4a20-9e7b-7e7b6da78941.jpg");
//        url.add(4,"http://cdn.playbuzz.com/cdn/62b7af36-65b7-41aa-8db2-e34fd8a76acf/1e93e32c-7662-4de7-a441-59d4c29d6faf.jpg");
//        url.add(5,"http://cdn.playbuzz.com/cdn/5cb29908-40a5-42d4-831d-5bea595bcf05/3e9f0c63-60c6-4a0c-964c-1302d56295da.jpg");
//        url.add(6,"https://pmcfootwearnews.files.wordpress.com/2015/06/michael-jordan-chicago-bulls.jpg");
//        url.add(7,"http://healthyceleb.com/wp-content/uploads/2015/03/Michael-Jordan.jpg");
//        url.add(8,"http://thelegacyproject.co.za/wp-content/uploads/2015/04/Michael_Jordan_Net_Worth.jpg");
//        url.add(9,"http://www.guinnessworldrecords.com/Images/Michael-Jordan-main_tcm25-15662.jpg");
//        url.add(10,"http://sportsmockery.com/wp-content/uploads/2015/03/michael-jordan-1600x1200.jpg");
//        url.add(11,"https://cdn-s3.si.com/s3fs-public/si/dam/assets/13/02/13/130213172915-michael-jordan-05717484-single-image-cut.jpg");
//        url.add(12,"http://cdn.playbuzz.com/cdn/5cb29908-40a5-42d4-831d-5bea595bcf05/5246cb13-4c32-45ba-89ad-c63cbbcdfde3.jpg");
//        url.add(13,"http://i.dailymail.co.uk/i/pix/2015/09/24/17/2CB89DDF00000578-0-image-a-1_1443111464150.jpg");


        View view = inflater.inflate(R.layout.fragment_first, container, false);
        myRecyclerAdapter = view.findViewById(R.id.recyclerview);

        Imageurl.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg","0"));
        Imageurl.add(new Recycledata("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Jordan_Lipofsky.jpg/170px-Jordan_Lipofsky.jpg","1"));
        Imageurl.add(new Recycledata("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Jordan_Lipofsky.jpg/170px-Jordan_Lipofsky.jpg","2"));
        Imageurl.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg","3"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=","4"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/abstract-graphic-world-map-illustration-on-blue-background-big-data-picture-id1294021851?b=1&k=20&m=1294021851&s=170667a&w=0&h=vsypj3JPqiWOU5q21fX3lHt1Z7wphVNE8kfqdpogPSs=","5"));
        Imageurl.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg","6"));
        Imageurl.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg","7"));
        Imageurl.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg","8"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=","9"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=","10"));
        Imageurl.add(new Recycledata("https://cdn-s3.si.com/s3fs-public/si/dam/assets/13/02/13/130213172915-michael-jordan-05717484-single-image-cut.jpg","11"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=","12"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=","13"));


//        recycledata.setImage("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg");
//        recycledata.setText("0");
//        Imageurl.add(recycledata);
        MyRecyclerAdapter recyclerAdapter = new MyRecyclerAdapter(Imageurl,getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        myRecyclerAdapter.setLayoutManager(gridLayoutManager);
        myRecyclerAdapter.setAdapter(recyclerAdapter);

        return view;


    }
}