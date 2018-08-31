package com.example.domain.repository;

import com.example.domain.models.Word;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface WordRepository {

    Single<Integer> sum(int a, int b);

    Flowable<List<Word>> getAllWords();

    Single<Boolean> insertThisWord(Word word);

    Single<Boolean> deleteThisWord(int wordId);

    Single<Boolean> updateThisWord(int wordId, String newWord);

    Single<Word> getTheIndexOfTopWord();

}
