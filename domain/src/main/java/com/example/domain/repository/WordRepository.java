package com.example.domain.repository;

import com.example.domain.models.Word;

import java.util.List;

import io.reactivex.Observable;

public interface WordRepository {

    Observable<Integer> sum(int a, int b);

    Observable<List<Word>> getAllWords();

    Observable<Boolean> insertThisWord(Word word);

    Observable<Boolean> deleteThisWord(int wordId);

    Observable<Boolean> updateThisWord(int wordId, String newWord);

    Observable<Word> getTheIndexOfTopWord();

}
