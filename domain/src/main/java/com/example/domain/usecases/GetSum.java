package com.example.domain.usecases;

import com.example.domain.base.UseCase;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.repository.WordRepository;

import io.reactivex.Observable;

public class GetSum extends UseCase<Integer, GetSum.Params> {

    //take repository here
    private WordRepository wordRepository;

    public GetSum(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        super(postExecutionThread);
        this.wordRepository = wordRepository;
    }

    @Override
    public Observable<Integer> buildUseCaseObservable(final Params params) {
        return wordRepository.sum(params.number1, params.number2);
    }

    public static final class Params {

        private int number1;
        private int number2;

        private Params(int number1, int number2) {
            this.number1 = number1;
            this.number2 = number2;
        }

        public static GetSum.Params getSum(int number1, int number2) {
            return new GetSum.Params(number1, number2);
        }
    }
}
