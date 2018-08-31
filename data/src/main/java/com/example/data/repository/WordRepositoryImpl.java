package com.example.data.repository;

import android.content.Context;

import com.example.data.data.WordRoomDatabase;
import com.example.data.data.dao.WordDao;
import com.example.data.network.ApiInterface;
import com.example.data.sharedpreference.SharedPreferenceHelper;
import com.example.domain.models.Word;
import com.example.domain.repository.WordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class WordRepositoryImpl implements WordRepository {

    private ApiInterface apiInterface;
    private SharedPreferenceHelper sharedPreferenceHelper;
    private Context context;

    private WordDao wordDao;
    private WordRoomDatabase db;

    public WordRepositoryImpl(ApiInterface apiInterface, SharedPreferenceHelper sharedPreferenceHelper, WordRoomDatabase db, Context context) {
        this.apiInterface = apiInterface;
        this.sharedPreferenceHelper = sharedPreferenceHelper;
        this.context = context;
        this.db = db;
        wordDao = db.wordDao();
    }


    @Override
    public Single<Integer> sum(final int a, final int b) {
        return Single.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return (a + b);
            }
        });
    }

    @Override
    public Flowable<List<Word>> getAllWords() {
        return db.wordDao().getAllWords().map(new Function<List<com.example.data.models.Word>, List<Word>>() {
            @Override
            public List<Word> apply(List<com.example.data.models.Word> words) throws Exception {
                List<Word> list = new ArrayList<>();
                for (com.example.data.models.Word item : words) {
                    list.add(new Word(item.getWordId(), item.getWord(), item.getWordLength()));
                }
                return list;
            }
        });
    }

    @Override
    public Single<Boolean> insertThisWord(final Word word) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                com.example.data.models.Word item = new com.example.data.models.Word(word.getWord(), word.getWordLength());
                db.wordDao().insertThisWord(item);
                return true;
            }
        });
    }

    @Override
    public Single<Boolean> deleteThisWord(final int wordId) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                db.wordDao().deleteThisWord(wordId);
                return true;
            }
        });
    }

    @Override
    public Single<Boolean> updateThisWord(final int wordId, final String newWord) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                db.wordDao().updateThisWord(wordId, newWord);
                return true;
            }
        });
    }

    @Override
    public Single<Word> getTheIndexOfTopWord() {
        return db.wordDao().getTheIndexOfTopWord().map(new Function<com.example.data.models.Word, Word>() {
            @Override
            public Word apply(com.example.data.models.Word word) throws Exception {
                return new Word(word.getWordId(), word.getWord(), word.getWordLength());
            }
        });
    }
}
