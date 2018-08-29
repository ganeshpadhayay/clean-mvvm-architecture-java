package com.example.domain.usecases;

import com.example.domain.base.UseCase;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.models.Word;
import com.example.domain.repository.WordRepository;

import io.reactivex.Observable;

public class InsertWord extends UseCase<Boolean, InsertWord.Params> {
    private WordRepository repository;

    public InsertWord(PostExecutionThread postExecutionThread, WordRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
    }

    @Override
    public Observable<Boolean> buildUseCaseObservable(Params params) {
        return repository.insertThisWord(params.word);
    }

    public static final class Params {
        private Word word;

        private Params(Word word) {
            this.word = word;
        }

        public static InsertWord.Params insertWord(Word word) {
            return new InsertWord.Params(word);
        }
    }
}
