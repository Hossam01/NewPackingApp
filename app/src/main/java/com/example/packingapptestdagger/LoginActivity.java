package com.example.packingapptestdagger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.packingapptestdagger.Model.User;
import com.example.packingapptestdagger.databinding.ActivityMainBinding;
import com.example.packingapptestdagger.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private UserViewModel viewModel;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getPokemons(binding.username.getText().toString(),binding.password.getText().toString());



            }
        });



        viewModel.getPokemonList().observe(this, new Observer<ArrayList<User>>() {

            @Override
            public void onChanged(ArrayList<User> pokemons) {
               // Toast.makeText(LoginActivity.this,pokemons.get(0).getUserDescription().toString(),Toast.LENGTH_SHORT).show();
                viewModel.deleteAll();
                viewModel.insertPokemon(pokemons.get(0));

                viewModel.getFavoritePokemonList().observe(LoginActivity.this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        Toast.makeText(LoginActivity.this,users.get(0).getUserDescription(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        viewModel.getPokemonListError().observe(this, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                Toast.makeText(LoginActivity.this,"برجاء التاكد من اسم المستخدم ورقم السري",Toast.LENGTH_SHORT).show();
            }

        });

    }
}