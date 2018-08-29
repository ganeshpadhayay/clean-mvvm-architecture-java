package com.example.ganesh.dmsmobileapp.di;

import android.content.Context;

import com.example.data.data.WordRoomDatabase;
import com.example.data.network.ApiInterface;
import com.example.data.repository.WordRepositoryImpl;
import com.example.data.sharedpreference.SharedPreferenceHelper;
import com.example.domain.repository.WordRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Provides
    @Singleton
    WordRepository provideWordRepository(ApiInterface apiInterface, SharedPreferenceHelper sharedPreferenceHelper, WordRoomDatabase db, Context context) {
        return new WordRepositoryImpl(apiInterface, sharedPreferenceHelper, db, context);
    }
}
