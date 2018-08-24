package com.example.ganesh.dmsmobileapp.ui.di;

import android.arch.lifecycle.ViewModelProvider;

import com.example.domain.usecases.GetSum;
import com.example.ganesh.dmsmobileapp.base.ViewModelProviderFactory;
import com.example.ganesh.dmsmobileapp.ui.main.MainViewModel;

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

}

