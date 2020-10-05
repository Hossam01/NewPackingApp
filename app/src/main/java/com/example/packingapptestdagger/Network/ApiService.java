package com.example.packingapptestdagger.Network;

import com.example.packingapptestdagger.Model.UserResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("Packing_Api/Login/Auth.php")
    Observable<UserResponse> getPokemons(@Body Map<String, String> mobile);
}
