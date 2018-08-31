package com.example.domain.usecases;

import com.example.domain.base.SingleUseCase;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.repository.WordRepository;

import io.reactivex.Single;

public class DeleteThisWord extends SingleUseCase<Boolean, DeleteThisWord.Params> {

    private WordRepository wordRepository;

    public DeleteThisWord(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        super(postExecutionThread);
        this.wordRepository = wordRepository;
    }

    @Override
    public Single<Boolean> buildUseCaseObservable(final Params params) {
        return wordRepository.deleteThisWord(params.wordId);
    }

    public static final class Params {
        private int wordId;

        private Params(int wordId) {
            this.wordId = wordId;
        }

        public static DeleteThisWord.Params deleteThisWord(int wordId) {
            return new DeleteThisWord.Params(wordId);
        }
    }
}
