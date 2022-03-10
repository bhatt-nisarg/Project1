package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {
    //context
    Context context;

    //Array
    int[] images;

    //inflate layout
    LayoutInflater layoutInflater;

    //view pager constructor
    public ViewPagerAdapter(Context context, int[] images){
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        //return the number of images
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }
    public Object instantiateItem(@NonNull ViewGroup container, final int position){

        View itemView = layoutInflater.inflate(R.layout.slide_item,container,false);

        //referencing the imageview from item.xml file
        ImageView imageView = itemView.findViewById(R.id.imageview);

        //setting the image in the imageview
        imageView.setImageResource(images[position]);

        //Adding the view
        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }
    public void destroyItem(ViewGroup container,int position, Object object){
        container.removeView((LinearLayout) object);
    }
}
