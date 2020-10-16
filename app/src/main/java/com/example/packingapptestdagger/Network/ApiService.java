package com.example.packingapptestdagger.Network;

import com.example.packingapptestdagger.Model.Response;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("android-test/recipes.json")
    Observable<ArrayList<Response>> getPokemons();
}
