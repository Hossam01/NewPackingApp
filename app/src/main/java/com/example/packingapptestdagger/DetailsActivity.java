package com.example.packingapptestdagger;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.packingapptestdagger.Model.Response;
import com.example.packingapptestdagger.databinding.ActivityDetailsBinding;
import com.example.packingapptestdagger.databinding.ActivitySplashBinding;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transation));
        binding.productIV.setTransitionName("thumbnailTransition");
        ArrayList<Response> arraylist = (ArrayList<Response>) getIntent().getSerializableExtra("list");
        int position=getIntent().getIntExtra("position",0);
        Glide.with(this).load(arraylist.get(position).getImage()).into(binding.productIV);
        binding.name.setText(arraylist.get(position).getName());
        binding.description.setText(arraylist.get(position).getDescription());
    }
}