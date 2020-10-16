package com.example.packingapptestdagger.viewmodels;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.load.model.Model;
import com.example.packingapptestdagger.LoginActivity;
import com.example.packingapptestdagger.Model.Response;
import com.example.packingapptestdagger.repository.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<ArrayList<Response>> pokemonList = new MutableLiveData<ArrayList<Response>>();
    private MutableLiveData<String> pokemonListError = new MutableLiveData<>();
    private LiveData<List<Response>> favoritePokemonList = null;

    @ViewModelInject
    public UserViewModel(Repository repository) {
        this.repository = repository;
        favoritePokemonList = repository.getFavoritePokemon();
    }

    public MutableLiveData<ArrayList< Response>> getPokemonList() {
        return pokemonList;
    }

    public MutableLiveData<String> getPokemonListError() {
        return pokemonListError;
    }


    @SuppressLint("CheckResult")
    public void getPokemons(){
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Response>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull ArrayList<Response> responses) {
                        pokemonList.setValue(responses);
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        pokemonListError.setValue(e.getMessage());
                        Log.d("Error",e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }


    public void insertPokemon(List<Response> pokemon){
        repository.insertPokemon(pokemon);
    }
    public void deletePokemon(Response pokemonName){
        repository.deletePokemon(pokemonName);
    }

    public LiveData<List<Response>> getFavoritePokemonList() {
        return favoritePokemonList;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void getFavoritePokemon(){
        favoritePokemonList = repository.getFavoritePokemon();
    }

    public ArrayList<Response> search(String newText,ArrayList<Response> list ) {
        ArrayList<Response> items2 = new ArrayList<>();
        for (Response s : list) {
            if (s.getName().contains(newText)) {
                items2.add(s);
            }
        }
            return items2;

    }

    public ArrayList<Response> sortCaloris(ArrayList<Response> list)
    {
        Collections.sort(list, new Comparator<Response>() {
            @Override
            public int compare(Response o1, Response o2) {
                return o1.getCalories().compareTo(o2.getCalories());
            }
        });
        return list;
    }

    public ArrayList<Response> sortFat(ArrayList<Response> list)
    {
        Collections.sort(list, new Comparator<Response>() {
            @Override
            public int compare(Response o1, Response o2) {
                return o1.getFats().compareTo(o2.getFats());
            }
        });
        return list;
    }

}
