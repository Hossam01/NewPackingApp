package com.example.packingapptestdagger.viewmodels;

import android.annotation.SuppressLint;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.packingapptestdagger.Model.User;
import com.example.packingapptestdagger.Model.UserResponse;
import com.example.packingapptestdagger.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<ArrayList<User>> pokemonList = new MutableLiveData<>();
    private MutableLiveData<String> pokemonListError = new MutableLiveData<>();
    private LiveData<List<User>> favoritePokemonList = null;

    @ViewModelInject
    public UserViewModel(Repository repository) {
        this.repository = repository;
        favoritePokemonList = repository.getFavoritePokemon();
    }

    public MutableLiveData<ArrayList<User>> getPokemonList() {
        return pokemonList;
    }

    public MutableLiveData<String> getPokemonListError() {
        return pokemonListError;
    }


    @SuppressLint("CheckResult")
    public void getPokemons(String name,String password){
        repository.getPokemons(name,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserResponse pokemonResponse) {
                        pokemonList.setValue(pokemonResponse.getRecords());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        pokemonListError.setValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


//                .map(new Function<PokemonResponse, ArrayList<Pokemon>>() {
//                    @Override
//                    public ArrayList<Pokemon> apply(PokemonResponse pokemonResponse) throws Throwable {
//                        ArrayList<Pokemon> list =  pokemonResponse.getRecords();
//                        return list;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((ArrayList<Pokemon> result) -> {
//                            pokemonList.setValue(result);
//                        },
//                        error-> pokemonListError.setValue(error.getMessage()));
    }


    public void insertPokemon(User pokemon){
        repository.insertPokemon(pokemon);
    }
    public void deletePokemon(User pokemonName){
        repository.deletePokemon(pokemonName);
    }

    public LiveData<List<User>> getFavoritePokemonList() {
        return favoritePokemonList;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void getFavoritePokemon(){
        favoritePokemonList = repository.getFavoritePokemon();
    }
}
