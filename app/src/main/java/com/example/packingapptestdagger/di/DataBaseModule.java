package com.example.packingapptestdagger.di;

import android.app.Application;

import androidx.room.Room;

import com.example.packingapptestdagger.Database.AppDatabase;
import com.example.packingapptestdagger.Database.UserDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {
    @Provides
    @Singleton
    public static AppDatabase providePokemonDB(Application application){
        return Room.databaseBuilder(application,AppDatabase.class,"Favorite Database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static UserDao providePokeDao(AppDatabase pokemonDB){
        return pokemonDB.userDao();
    }
}
