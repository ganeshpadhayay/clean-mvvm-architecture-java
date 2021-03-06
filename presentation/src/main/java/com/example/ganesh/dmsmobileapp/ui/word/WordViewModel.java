package com.example.ganesh.dmsmobileapp.ui.word;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.domain.models.Word;
import com.example.domain.usecases.DeleteThisWord;
import com.example.domain.usecases.GetAllWords;
import com.example.domain.usecases.GetTheIndexOfTopWord;
import com.example.domain.usecases.InsertWord;
import com.example.domain.usecases.UpdateThisWord;
import com.example.ganesh.dmsmobileapp.base.BaseViewModel;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.subscribers.ResourceSubscriber;

public class WordViewModel extends BaseViewModel<WordNavigator> {

    //get all the use cases here
    private GetAllWords getAllWords;
    private InsertWord insertWord;
    private DeleteThisWord deleteThisWord;
    private UpdateThisWord updateThisWord;
    private GetTheIndexOfTopWord getTheIndexOfTopWord;

    //data
    public MutableLiveData<List<Word>> allWords;


    public WordViewModel(GetAllWords getAllWords, InsertWord insertWord, DeleteThisWord deleteThisWord, UpdateThisWord updateThisWord, GetTheIndexOfTopWord getTheIndexOfTopWord) {
        this.getAllWords = getAllWords;
        this.insertWord = insertWord;
        this.deleteThisWord = deleteThisWord;
        this.updateThisWord = updateThisWord;
        this.getTheIndexOfTopWord = getTheIndexOfTopWord;
    }

    public void getAllWords() {
        getAllWords.execute(new ResourceSubscriber<List<Word>>() {
            @Override
            public void onNext(List<Word> words) {
                allWords.setValue(words);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        }, GetAllWords.Params.getAllWords());
    }


    public void insertWord(Word word) {
        insertWord.execute(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    Log.e("ganesh", "word inserted successfully!!!");
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        }, InsertWord.Params.insertWord(word));
    }

    public void getTheIndexOfTopWord(final String action) {
        getTheIndexOfTopWord.execute(new DisposableSingleObserver<Word>() {
            @Override
            public void onSuccess(Word word) {
                if (word != null) {
                    getNavigator().updateTopIndex(word.getWordId(), action);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        }, GetTheIndexOfTopWord.Params.getTheIndexOfTopWord());
    }

    public void deleteThisWord(int wordId) {
        deleteThisWord.execute(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    Log.e("ganesh", "word deleted successfully!!!");
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        }, DeleteThisWord.Params.deleteThisWord(wordId));
    }

    public void updateThisWord(int wordId, String newWord) {
        updateThisWord.execute(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean) {
                    Log.e("ganesh", "word updated successfully!!!");
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        }, UpdateThisWord.Params.updateThisWord(wordId, newWord));
    }

    public MutableLiveData<List<Word>> getWords() {
        if (allWords == null) {
            allWords = new MutableLiveData<>();
        }
        return allWords;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (getAllWords != null)
            getAllWords.dispose();
        if (deleteThisWord != null)
            deleteThisWord.dispose();
        if (insertWord != null)
            insertWord.dispose();
        if (updateThisWord != null)
            updateThisWord.dispose();
        if (getTheIndexOfTopWord != null)
            getTheIndexOfTopWord.dispose();
    }
}
