package com.example.packingapptestdagger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.packingapptestdagger.Adapter.RecipeAdapter;
import com.example.packingapptestdagger.Model.Response;
import com.example.packingapptestdagger.databinding.ActivityMainBinding;
import com.example.packingapptestdagger.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    private UserViewModel viewModel;
    ActivityMainBinding binding;
    RecipeAdapter adapter;
    ArrayList<Response> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        adapter = new RecipeAdapter(this);
        binding.recycle.setAdapter(adapter);
        list=new ArrayList<>();
        viewModel.getPokemons();

        viewModel.getPokemonList().observe(this, new Observer<ArrayList<Response>>() {

            @Override
            public void onChanged(ArrayList<Response> responses) {
                adapter.setList(responses);
                viewModel.deleteAll();
                viewModel.insertPokemon(responses);
                list.addAll(responses);
            }
        });


        viewModel.getPokemonListError().observe(this, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                viewModel.getFavoritePokemonList().observe(LoginActivity.this, new Observer<List<Response>>() {
                    @Override
                    public void onChanged(List<Response> responses) {
                        adapter.setList((ArrayList<Response>) responses);
                        list.addAll(responses);
                    }
                });

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.setList(viewModel.search(newText,list));
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }
        };

        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.calories)
        {
           adapter.setList(viewModel.sortCaloris(list));
        }


        else if (id==R.id.fat)
        {
            adapter.setList(viewModel.sortFat(list));
        }
        return super.onOptionsItemSelected(item);
    }
}