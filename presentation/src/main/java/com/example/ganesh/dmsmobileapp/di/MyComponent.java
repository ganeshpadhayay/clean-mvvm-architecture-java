package com.example.ganesh.dmsmobileapp.di;

import com.example.ganesh.dmsmobileapp.ui.di.MainModule;
import com.example.ganesh.dmsmobileapp.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, UseCaseModule.class, RepositoryModule.class, MainModule.class})
public interface MyComponent {
    void inject(MainActivity mainActivity);
}
