package com.example.ganesh.dmsmobileapp.base;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<V extends BaseViewModel> extends AppCompatActivity {
    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();
}
