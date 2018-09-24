package com.example.ganesh.dmsmobileapp.ui.about;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ganesh.dmsmobileapp.R;
import com.example.ganesh.dmsmobileapp.base.BaseFragment;

import javax.inject.Inject;

public class AboutFragment extends BaseFragment<AboutViewModel> implements AboutNavigator {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private AboutViewModel aboutViewModel;
    private Button button;
    private EditText editText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutViewModel.setNavigator(this);
    }

    public AboutFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        button = (Button) view.findViewById(R.id.fragment_about_btn);
        editText = (EditText) view.findViewById(R.id.fragment_about_et_word);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(getViewModel().getName());
            }
        });
        return view;
    }

    @Override
    public AboutViewModel getViewModel() {
        aboutViewModel = ViewModelProviders.of(this, viewModelFactory).get(AboutViewModel.class);
        return aboutViewModel;
    }
}
