package com.example.domain.base;


import com.example.domain.executor.PostExecutionThread;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public abstract class SingleUseCase<T, Params> {

    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable compositeDisposable;

    public SingleUseCase(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread;
        this.compositeDisposable = new CompositeDisposable();
    }


    public abstract Single<T> buildUseCaseObservable(Params params);


    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


    public void execute(DisposableSingleObserver<T> observer, Params params) {
        if (observer != null) {
            final Single<T> single = this.buildUseCaseObservable(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(postExecutionThread.getScheduler());
            addDisposable(single.subscribeWith(observer));
        }
    }


    private void addDisposable(Disposable disposable) {
        if (disposable != null && compositeDisposable != null)
            compositeDisposable.add(disposable);
    }
}
