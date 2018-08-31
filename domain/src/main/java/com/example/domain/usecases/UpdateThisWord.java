package com.example.domain.usecases;

import com.example.domain.base.SingleUseCase;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.repository.WordRepository;

import io.reactivex.Single;

public class UpdateThisWord extends SingleUseCase<Boolean, UpdateThisWord.Params> {

    private WordRepository repository;

    public UpdateThisWord(PostExecutionThread postExecutionThread, WordRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
    }

    @Override
    public Single<Boolean> buildUseCaseObservable(Params params) {
        return repository.updateThisWord(params.wordId, params.newWord);
    }

    public static final class Params {
        private int wordId;
        private String newWord;

        private Params(int wordId, String newWord) {
            this.wordId = wordId;
            this.newWord = newWord;
        }

        public static UpdateThisWord.Params updateThisWord(int wordId, String newWord) {
            return new UpdateThisWord.Params(wordId, newWord);
        }
    }

}
