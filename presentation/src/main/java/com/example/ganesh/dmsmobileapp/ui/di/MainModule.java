package com.example.ganesh.dmsmobileapp.ui.di;

import android.arch.lifecycle.ViewModelProvider;

import com.example.domain.usecases.DeleteThisWord;
import com.example.domain.usecases.GetAllWords;
import com.example.domain.usecases.GetSum;
import com.example.domain.usecases.GetTheIndexOfTopWord;
import com.example.domain.usecases.InsertWord;
import com.example.domain.usecases.UpdateThisWord;
import com.example.ganesh.dmsmobileapp.base.ViewModelProviderFactory;
import com.example.ganesh.dmsmobileapp.ui.main.MainViewModel;
import com.example.ganesh.dmsmobileapp.ui.word.WordViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainViewModel provideMainViewModel(GetSum getSumUseCase) {
        return new MainViewModel(getSumUseCase);
    }

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    WordViewModel provideWordViewModel(GetAllWords getAllWords, InsertWord insertWord, DeleteThisWord deleteThisWord, UpdateThisWord updateThisWord, GetTheIndexOfTopWord getTheIndexOfTopWord) {
        return new WordViewModel(getAllWords, insertWord, deleteThisWord, updateThisWord, getTheIndexOfTopWord);
    }
}

