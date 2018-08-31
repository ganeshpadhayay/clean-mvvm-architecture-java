package com.example.domain.base;


import com.example.domain.executor.PostExecutionThread;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each FlowableUseCase implementation will return the result using a {@link DisposableObserver}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class FlowableUseCase<T, Params> {

    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable compositeDisposable;

    public FlowableUseCase(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread;
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link FlowableUseCase}.
     */
    public abstract Flowable<T> buildUseCaseObservable(Params params);

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    /**
     * Executes the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     *                 by {@link #buildUseCaseObservable(Params)} ()} method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    public void execute(ResourceSubscriber<T> observer, Params params) {
        if (observer != null) {
            final Flowable<T> flowable = this.buildUseCaseObservable(params)
                    .subscribeOn(Schedulers.io())
                    .observeOn(postExecutionThread.getScheduler());
            addDisposable(flowable.subscribeWith(observer));
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        if (disposable != null && compositeDisposable != null)
            compositeDisposable.add(disposable);
    }
}
