package com.example.ganesh.dmsmobileapp.ui.main;

import android.util.Log;

import com.example.domain.usecases.GetSum;
import com.example.ganesh.dmsmobileapp.base.BaseViewModel;

import io.reactivex.observers.DisposableObserver;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private GetSum getSumUseCase;

    public MainViewModel(GetSum getSumUseCase) {
        this.getSumUseCase = getSumUseCase;
    }

    public void callGetSum(int number1, int number2) {
        getSumUseCase.execute(new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                Log.e("ganesh", "use case on next  " + integer);
                getNavigator().displaySum(integer);
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
    protected void onCleared() {
        super.onCleared();
        if (getSumUseCase != null) {
            getSumUseCase.dispose();
        }
    }
}
