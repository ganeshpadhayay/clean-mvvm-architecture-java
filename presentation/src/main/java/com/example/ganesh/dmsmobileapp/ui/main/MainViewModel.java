package com.example.ganesh.dmsmobileapp.ui.main;

import android.util.Log;

import com.example.domain.usecases.GetSum;
import com.example.ganesh.dmsmobileapp.base.BaseViewModel;

import io.reactivex.observers.DisposableSingleObserver;

public class MainViewModel extends BaseViewModel<MainNavigator> {

    private GetSum getSumUseCase;

    public MainViewModel(GetSum getSumUseCase) {
        this.getSumUseCase = getSumUseCase;
    }

    public void callGetSum(int number1, int number2) {
        getSumUseCase.execute(new DisposableSingleObserver<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                Log.e("ganesh", "use case on next  " + integer);
                getNavigator().displaySum(integer);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
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
