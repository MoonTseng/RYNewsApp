package com.example.materialtest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mcontext;
    private List<Fruit> mfruitList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view){
            super(view);
            cardView  = (CardView) view;
            fruitImage = view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter (List<Fruit> fruitList){
        mfruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mcontext == null){
            mcontext = parent.getContext();
        }
        View view = LayoutInflater.from(mcontext).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = mfruitList.get(position);
                Intent intent = new Intent(mcontext,FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                mcontext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mfruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mcontext).load(fruit.getImageId()).into(holder.fruitImage);

    }

    @Override
    public int getItemCount() {
        return mfruitList.size();
    }
}
