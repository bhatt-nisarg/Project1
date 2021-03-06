package com.example.project1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import com.example.project1.Recyclerview.MyRecyclerAdapter;
import java.util.ArrayList;


public class SecondFragment extends Fragment {
    RecyclerView secrecyclerView;
    SearchView secsearch;

    ArrayList<Recycledata> secondList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_second,container,false);
        secrecyclerView = view.findViewById(R.id.frag_secrecyclerview);
        secsearch = view.findViewById(R.id.secondsearch);

        secondList.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg","0"));
        secondList.add(new Recycledata("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Jordan_Lipofsky.jpg/170px-Jordan_Lipofsky.jpg","1"));
        secondList.add(new Recycledata("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Jordan_Lipofsky.jpg/170px-Jordan_Lipofsky.jpg","2"));
        secondList.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg","3"));
        secondList.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=","4"));
        secondList.add(new Recycledata("https://media.istockphoto.com/photos/abstract-graphic-world-map-illustration-on-blue-background-big-data-picture-id1294021851?b=1&k=20&m=1294021851&s=170667a&w=0&h=vsypj3JPqiWOU5q21fX3lHt1Z7wphVNE8kfqdpogPSs=","5"));
        secondList.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg","6"));
        secondList.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg","7"));
        secondList.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg","8"));
        secondList.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=","9"));
        secondList.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=","10"));
        secondList.add(new Recycledata("https://cdn-s3.si.com/s3fs-public/si/dam/assets/13/02/13/130213172915-michael-jordan-05717484-single-image-cut.jpg","11"));
        secondList.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=","12"));
        secondList.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=","13"));

        MyRecyclerAdapter secondfragAdapter = new MyRecyclerAdapter(secondList,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        secrecyclerView.setLayoutManager(linearLayoutManager);
        secrecyclerView.setAdapter(secondfragAdapter);
        secsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                secondfragAdapter.getFilter().filter(s);
                return false;
            }
        });
        return view;
    }
}