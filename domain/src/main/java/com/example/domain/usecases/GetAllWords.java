package com.example.domain.usecases;

import com.example.domain.base.UseCase;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.models.Word;
import com.example.domain.repository.WordRepository;

import java.util.List;

import io.reactivex.Observable;

public class GetAllWords extends UseCase<List<Word>, GetAllWords.Params> {

    private WordRepository wordRepository;

    public GetAllWords(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        super(postExecutionThread);
        this.wordRepository = wordRepository;
    }

    @Override
    public Observable<List<Word>> buildUseCaseObservable(Params params) {
        return wordRepository.getAllWords();
    }

    public static final class Params {
        private Params() {
        }

        public static GetAllWords.Params getAllWords() {
            return new GetAllWords.Params();
        }
    }
}
