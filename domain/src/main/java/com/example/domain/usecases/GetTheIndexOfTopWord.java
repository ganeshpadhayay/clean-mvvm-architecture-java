package com.example.domain.usecases;

import com.example.domain.base.UseCase;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.models.Word;
import com.example.domain.repository.WordRepository;

import io.reactivex.Observable;

public class GetTheIndexOfTopWord extends UseCase<Word, GetTheIndexOfTopWord.Params> {

    private WordRepository wordRepository;

    public GetTheIndexOfTopWord(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        super(postExecutionThread);
        this.wordRepository = wordRepository;
    }

    @Override
    public Observable<Word> buildUseCaseObservable(Params params) {
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
