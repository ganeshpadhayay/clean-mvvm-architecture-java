package com.example.ganesh.dmsmobileapp.ui.main;

import com.example.domain.usecases.GetSum;
import com.example.ganesh.dmsmobileapp.base.BaseViewModel;

import io.reactivex.observers.DisposableObserver;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    //get your Navigator here
    private MainNavigator mainNavigator;

    //get the use case here
    private GetSum getSumUseCase;

    public MainViewModel() {
    }

    public MainViewModel(GetSum getSumUseCase) {
        this.getSumUseCase = getSumUseCase;
    }

    public void callGetSum(int number1, int number2) {
        getSumUseCase.execute(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                mainNavigator.displaySum(integer);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        }, GetSum.Params.getSum(number1, number2));
    }

    @Override
    public void onNavigatorAttached(MainNavigator navigator) {
        this.mainNavigator = mainNavigator;
    }

    @Override
    public void onNavigatorDetached() {
        this.mainNavigator = null;
    }

    @Override
    public void onDestroyed() {
        if (getSumUseCase != null) {
            getSumUseCase.dispose();
        }
    }
}
