package com.example.packingapptestdagger.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.packingapptestdagger.Model.User;

import java.util.ArrayList;
import java.util.List;


import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Insert(onConflict = REPLACE)
    Void insertUser(User mUser);


    @Delete
    void delete(User mUser);

    @Update
    void updateUser(User mUser);

    @Query("DELETE FROM user")
    void deleteAll();


}
