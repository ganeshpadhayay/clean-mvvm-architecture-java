package com.example.data.repository;

import android.content.Context;

import com.example.data.network.ApiInterface;
import com.example.data.sharedpreference.SharedPreferenceHelper;
import com.example.domain.repository.WordRepository;

public class WordRepositoryImplWithoutDB implements WordRepository {

    private ApiInterface apiInterface;
    private SharedPreferenceHelper sharedPreferenceHelper;
    private Context context;

    public WordRepositoryImplWithoutDB(ApiInterface apiInterface, SharedPreferenceHelper sharedPreferenceHelper, Context context) {
        this.apiInterface = apiInterface;
        this.sharedPreferenceHelper = sharedPreferenceHelper;
        this.context = context;
    }


    @Override
    public int sum(int a, int b) {
        return a + b;
    }
}
