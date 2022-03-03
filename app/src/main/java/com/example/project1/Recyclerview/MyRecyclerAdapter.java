package com.example.project1.Recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.project1.R;
import com.example.project1.Recycledata;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    ArrayList<com.example.project1.Recycledata> Recycledata;
    Context context;
    private ArrayList<Recycledata> fullList = new ArrayList<>();

    public MyRecyclerAdapter(ArrayList<com.example.project1.Recycledata> imageurl, Context context) {
        this.context = context;
        this.Recycledata = imageurl;
        this.fullList = Recycledata;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);

        //passing view to viewholder
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //typecast object to int type
       // holder.imagesdata.setImageResource(Recycledata.get(position).getImage());
        //for first fragment
        Picasso.with(context)
                .load(Recycledata.get(position).getImage())
                .into(holder.imagesdata);
        holder.textdata.setText(Recycledata.get(position).getText());

        Log.d("abc",Recycledata.get(position).getImage());

        //for second fragment
//        Picasso.with(context)
//                .load(Recycledata.get(position).getImage())
//                .into(holder.imagesdata);
        //remove item from recycleview
        holder.itemView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Recycledata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagesdata;
        public TextView textdata;
        public ViewHolder(View view) {
            super(view);
            imagesdata = (ImageView)view.findViewById(R.id.imageView);
            textdata = (TextView) view.findViewById(R.id.text);
        }
    }
    public void removeItem(int position){
        Recycledata.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,Recycledata.size());
    }


//    //method for filtering our recycleview items
//    public void filterList(ArrayList<com.example.project1.Recycledata> updatedlist) {
//        //below code is for add filter list in our course list
//        Log.d("ababab",updatedlist.toString());
//        Recycledata = updatedlist;
//        //below code is for notify that dataset is changed
//        notifyDataSetChanged();
//    }

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Recycledata> results = new ArrayList<Recycledata>();

                if (constraint != null) {

                    for (final Recycledata g : fullList) {
                        if (g.getText().toLowerCase().contains(charString.toLowerCase()))
                            results.add(g);
                    }

                    Recycledata = results;
                    Log.d("res", Recycledata.toString());
                    oReturn.values = Recycledata;

                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Recycledata.addAll((ArrayList<Recycledata>)filterResults.values);
                notifyDataSetChanged();
            }
              /*  ArrayList<com.example.project1.Recycledata> updatedList = new ArrayList<>();
                if (charSequence == null || charSequence.length() == 0){
                    updatedList.addAll(fullList);
                }else{
                    String filterpattern = charSequence.toString().toLowerCase().trim();
                    for (Recycledata item : Recycledata){
                        if (item.getText().toLowerCase().contains(filterpattern)){
                            updatedList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = updatedList;
                return results;*/



        };
    }

}
