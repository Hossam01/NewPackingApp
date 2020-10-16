package com.example.packingapptestdagger.repository;

import androidx.lifecycle.LiveData;

import com.example.packingapptestdagger.Database.UserDao;
import com.example.packingapptestdagger.Model.Response;
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


    public Observable<ArrayList<Response>> getPokemons(){

        return pokemonApiService.getPokemons();
    }
    public void insertPokemon(List<Response> pokemon){
        userDao.insertUser(pokemon);
    }
    public void deletePokemon(Response user){
        userDao.delete(user);
    }

    public void deleteAll(){
        userDao.deleteAll();
    }

    public LiveData<List<Response>> getFavoritePokemon(){
        return userDao.getAll();
    }

}
