package com.example.ganesh.dmsmobileapp.ui.main;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ganesh.dmsmobileapp.MainApplication;
import com.example.ganesh.dmsmobileapp.R;
import com.example.ganesh.dmsmobileapp.ui.about.AboutFragment;
import com.example.ganesh.dmsmobileapp.ui.word.WordActivity;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainNavigator {

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

        mainViewModel.setNavigator(this);
        mainViewModel.callGetSum(4, 5);

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

}
