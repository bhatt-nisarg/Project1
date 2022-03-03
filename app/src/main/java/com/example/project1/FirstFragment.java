package com.example.project1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.project1.Recyclerview.MyRecyclerAdapter;

import java.util.ArrayList;



public class FirstFragment extends Fragment  {
    RecyclerView myRecyclerView;
    MyRecyclerAdapter myRecyclerAdapter;
    SearchView searchView;


    ArrayList<Recycledata> Imageurl = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        myRecyclerView = view.findViewById(R.id.recyclerview);
        searchView = view.findViewById(R.id.Firstsearch);

        Imageurl.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg", "first"));
        Imageurl.add(new Recycledata("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Jordan_Lipofsky.jpg/170px-Jordan_Lipofsky.jpg", "second"));
        Imageurl.add(new Recycledata("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b3/Jordan_Lipofsky.jpg/170px-Jordan_Lipofsky.jpg", "abc"));
        Imageurl.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg", "third"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=", "first"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/abstract-graphic-world-map-illustration-on-blue-background-big-data-picture-id1294021851?b=1&k=20&m=1294021851&s=170667a&w=0&h=vsypj3JPqiWOU5q21fX3lHt1Z7wphVNE8kfqdpogPSs=", "sec"));
        Imageurl.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg", "four"));
        Imageurl.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg", "first"));
        Imageurl.add(new Recycledata("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg", "one"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=", "second"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=", "third"));
        Imageurl.add(new Recycledata("https://cdn-s3.si.com/s3fs-public/si/dam/assets/13/02/13/130213172915-michael-jordan-05717484-single-image-cut.jpg", "third"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=", "first"));
        Imageurl.add(new Recycledata("https://media.istockphoto.com/photos/asian-male-florist-owner-of-small-business-flower-shop-using-digital-picture-id1317277259?b=1&k=20&m=1317277259&s=170667a&w=0&h=K08QBPPiq5_OOZcksriP_3eHEB1z5diqY14KUad3wiU=", "second"));

        MyRecyclerAdapter recyclerAdapter = new MyRecyclerAdapter(Imageurl, getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        myRecyclerView.setLayoutManager(gridLayoutManager);
        myRecyclerView.setAdapter(recyclerAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                recyclerAdapter.getFilter().filter(s);
                return false;
            }
        });
        return view;
    }
//    private void filter(String text) {
//        // creating a new array list to filter our data.
//        ArrayList<Recycledata> filteredlist = new ArrayList<>();
//
//        Log.d("sds",text);
//        for (Recycledata item : Imageurl) {
//            // checking if the entered string matched with any item of our recycler view.
//            if (item.getText().toLowerCase().contains(text.toLowerCase())) {
//                Log.d("fill",  item.getText().toLowerCase());
//                filteredlist.add(item);
//            }
//        }
//        Log.d("fill", String.valueOf(filteredlist.size()));
//
//
//        // running a for loop to compare elements.
//
//
//            // at last we are passing that filtered
//            // list to our adapter class.
//            Log.d("ab",  filteredlist.size() + "");
//            myRecyclerAdapter.filterList(filteredlist);
//
//
//
//    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//
//        super.onViewCreated(view, savedInstanceState);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//
//                Log.d("asass",s);
//                filter(s);
//
//                return false;
//            }
//        });
//
//    }



}