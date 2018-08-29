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

import io.reactivex.Observable;

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
    public Observable<Integer> sum(final int a, final int b) {
        return Observable.fromCallable(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return (a + b);
            }
        });
    }

    @Override
    public Observable<List<Word>> getAllWords() {
        return Observable.fromCallable(new Callable<List<Word>>() {
            @Override
            public List<Word> call() throws Exception {
                List<Word> list = new ArrayList<>();
                List<com.example.data.models.Word> listWords = db.wordDao().getAllWords();
                for (com.example.data.models.Word item : listWords) {
                    list.add(new Word(item.getWordId(), item.getWord(), item.getWordLength()));
                }
                return list;
            }
        });
    }

    @Override
    public Observable<Boolean> insertThisWord(final Word word) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                com.example.data.models.Word item = new com.example.data.models.Word(word.getWord(), word.getWordLength());
                db.wordDao().insertThisWord(item);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> deleteThisWord(final int wordId) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                db.wordDao().deleteThisWord(wordId);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> updateThisWord(final int wordId, final String newWord) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                db.wordDao().updateThisWord(wordId, newWord);
                return true;
            }
        });
    }

    @Override
    public Observable<Word> getTheIndexOfTopWord() {
        return Observable.fromCallable(new Callable<Word>() {
            @Override
            public Word call() throws Exception {
                com.example.data.models.Word item = db.wordDao().getTheIndexOfTopWord();
                Word word = new Word(item.getWordId(), item.getWord(), item.getWordLength());
                return word;
            }
        });
    }
}
