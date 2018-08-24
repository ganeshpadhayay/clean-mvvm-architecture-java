package com.example.ganesh.dmsmobileapp;

import android.app.Application;

import com.example.ganesh.dmsmobileapp.di.AppModule;
import com.example.ganesh.dmsmobileapp.di.DaggerMyComponent;
import com.example.ganesh.dmsmobileapp.di.MyComponent;

public class MainApplication extends Application {

    private MyComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMyComponent.builder().appModule(new AppModule(getApplicationContext())).build();
    }

    public MyComponent getComponent() {
        return this.component;
    }
}
