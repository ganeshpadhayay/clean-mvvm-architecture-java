package com.example.domain.usecases;

import com.example.domain.base.FlowableUseCase;
import com.example.domain.base.SingleUseCase;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.models.Word;
import com.example.domain.repository.WordRepository;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class GetTheIndexOfTopWord extends SingleUseCase<Word, GetTheIndexOfTopWord.Params> {

    private WordRepository wordRepository;

    public GetTheIndexOfTopWord(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        super(postExecutionThread);
        this.wordRepository = wordRepository;
    }

    @Override
    public Single<Word> buildUseCaseObservable(Params params) {
        return wordRepository.getTheIndexOfTopWord();
    }

    public static final class Params {

        private Params() {
        }

        public static GetTheIndexOfTopWord.Params getTheIndexOfTopWord() {
            return new GetTheIndexOfTopWord.Params();
        }
    }
}
