package com.example.packingapptestdagger.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Fade;

import com.bumptech.glide.Glide;
import com.example.packingapptestdagger.DetailsActivity;
import com.example.packingapptestdagger.LoginActivity;
import com.example.packingapptestdagger.Model.Response;
import com.example.packingapptestdagger.R;

import java.io.Serializable;
import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.PokemonViewHolder> {
    private ArrayList<Response> mList = new ArrayList<>();
    private Context mContext;

    public RecipeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(ArrayList<Response> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Context context=holder.itemView.getContext();

        holder.recipeName.setText(mList.get(position).getName());
        Glide.with(mContext).load(mList.get(position).getThumb()).into(holder.recipeImage);
        holder.calories.setText("Calories : "+mList.get(position).getCalories());
        holder.carbos.setText("Fats : "+mList.get(position).getFats());



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("list", mList);
                intent.putExtra("position",position);
                holder.recipeImage.setTransitionName("thumbnailTransition");
                Pair<View, String> pair1 = Pair.create((View) holder.recipeImage, holder.recipeImage.getTransitionName());
                Pair<View, String> pair2 = Pair.create((View) holder.recipeImage, holder.recipeImage.getTransitionName());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext, pair1, pair2);

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        (Activity) context, holder.recipeImage, ViewCompat.getTransitionName(holder.recipeImage));
                context.startActivity(intent,optionsCompat.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }





    public class PokemonViewHolder extends RecyclerView.ViewHolder {
        private ImageView recipeImage;
        private TextView recipeName,calories,carbos;
        private CardView cardView;
        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeImage = itemView.findViewById(R.id.productIV);
            recipeName = itemView.findViewById(R.id.nameTV);
            calories=itemView.findViewById(R.id.caloriesTV);
            carbos=itemView.findViewById(R.id.Carbos);
            cardView=itemView.findViewById(R.id.card);


        }


    }


}
