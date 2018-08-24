package com.example.ganesh.dmsmobileapp.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.ganesh.dmsmobileapp.MainApplication;
import com.example.ganesh.dmsmobileapp.R;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainNavigator {

    private TextView textView;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MainApplication) getApplicationContext()).getComponent().inject(this);
        textView = (TextView) findViewById(R.id.activity_main_text_view);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        mainViewModel.callGetSum(4, 5);
    }


    @Override
    public void displaySum(Integer integer) {
        textView.setText(integer);
    }
}
