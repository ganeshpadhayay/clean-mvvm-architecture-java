package com.example.ganesh.dmsmobileapp.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ganesh.dmsmobileapp.MainApplication;
import com.example.ganesh.dmsmobileapp.R;
import com.example.ganesh.dmsmobileapp.base.BaseActivity;
import com.example.ganesh.dmsmobileapp.ui.word.WordActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<MainViewModel> implements MainNavigator {

    private TextView textView;
    private Button buttonWordActivity;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MainApplication) getApplicationContext()).getComponent().inject(this);
        textView = (TextView) findViewById(R.id.activity_main_text_view);
        buttonWordActivity = (Button) findViewById(R.id.activity_main_button_word_activity);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        getViewModel().setNavigator(this);
        getViewModel().callGetSum(4, 5);

        buttonWordActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WordActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void displaySum(Integer integer) {
        textView.setText(integer.toString());
    }


    @Override
    public MainViewModel getViewModel() {
        return mainViewModel;
    }
}
