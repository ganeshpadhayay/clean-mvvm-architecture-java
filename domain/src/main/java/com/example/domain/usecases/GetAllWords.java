package com.example.domain.usecases;

import com.example.domain.base.FlowableUseCase;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.models.Word;
import com.example.domain.repository.WordRepository;

import java.util.List;

import io.reactivex.Flowable;

public class GetAllWords extends FlowableUseCase<List<Word>, GetAllWords.Params> {

    private WordRepository wordRepository;

    public GetAllWords(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        super(postExecutionThread);
        this.wordRepository = wordRepository;
    }

    @Override
    public Flowable<List<Word>> buildUseCaseObservable(Params params) {
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
