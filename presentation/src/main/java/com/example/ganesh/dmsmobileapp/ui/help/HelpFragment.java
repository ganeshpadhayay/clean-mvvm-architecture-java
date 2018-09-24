package com.example.ganesh.dmsmobileapp.ui.help;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ganesh.dmsmobileapp.R;
import com.example.ganesh.dmsmobileapp.base.BaseFragment;

import javax.inject.Inject;

public class HelpFragment extends BaseFragment<HelpViewModel> implements HelpNavigator {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private HelpViewModel helpViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helpViewModel.setNavigator(this);
    }

    public HelpFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_help, container, false);
    }

    @Override
    public HelpViewModel getViewModel() {
        helpViewModel = ViewModelProviders.of(this, viewModelFactory).get(HelpViewModel.class);
        return helpViewModel;
    }
}
