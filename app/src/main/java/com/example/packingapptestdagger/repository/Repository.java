package com.example.packingapptestdagger.repository;

import androidx.lifecycle.LiveData;

import com.example.packingapptestdagger.Database.UserDao;
import com.example.packingapptestdagger.Model.User;
import com.example.packingapptestdagger.Model.UserResponse;
import com.example.packingapptestdagger.Network.ApiService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private ApiService pokemonApiService;
    private UserDao userDao;

    @Inject
    public Repository(ApiService pokemonApiService, UserDao userDao) {
        this.pokemonApiService = pokemonApiService;
        this.userDao = userDao;
    }


    public Observable<UserResponse> getPokemons(String name, String Password){
        HashMap<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("password", Password);
        return pokemonApiService.getPokemons(map);
    }
    public void insertPokemon(User pokemon){
        userDao.insertUser(pokemon);
    }
    public void deletePokemon(User user){
        userDao.delete(user);
    }

    public void deleteAll(){
        userDao.deleteAll();
    }

    public LiveData<List<User>> getFavoritePokemon(){
        return userDao.getAll();
    }

}
