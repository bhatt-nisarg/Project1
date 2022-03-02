package com.example.project1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class HomeFragment extends Fragment {
    BottomNavigationView topnavigation;
    public HomeFragment() {
        // Required empty public constructor
        super(R.layout.fragment_home);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_home,container,false);
       topnavigation = (BottomNavigationView) view.findViewById(R.id.bottom_navigation);
        loadFragment(new FirstFragment());
        topnavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                int id = item.getItemId();
                switch (id){
                    case R.id.first:
                        fragment = new FirstFragment();
                        loadFragment(fragment);
                        return true;

                    case R.id.second:
                        fragment = new SecondFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.third:
                        fragment = new ThirdFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
        return view;
    }

    private void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.homeframe_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}