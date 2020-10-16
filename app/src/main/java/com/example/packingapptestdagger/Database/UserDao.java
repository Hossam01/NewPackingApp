package com.example.packingapptestdagger.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.packingapptestdagger.Model.Response;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<Response>> getAll();

    @Insert(onConflict = REPLACE)
    Void insertUser(List<Response> mUser);


    @Delete
    void delete(Response mUser);

    @Update
    void updateUser(Response mUser);

    @Query("DELETE FROM user")
    void deleteAll();


}
