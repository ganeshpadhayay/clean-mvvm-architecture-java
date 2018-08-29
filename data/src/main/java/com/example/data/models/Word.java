package com.example.data.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//this is an entity class in ROOM which means a table in the SQLite database.
//Annotations identify how each part of this class relates to an entry in the database.
@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "word_id")
    private int wordId;

    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    @NonNull
    @ColumnInfo(name = "word_length")
    private int wordLength;

    public Word(@NonNull String word, @NonNull int wordLength) {
        this.mWord = word;
        this.wordLength = wordLength;
    }


    @Ignore
    public Word(com.example.domain.models.Word word) {
        this.wordId = word.getWordId();
        this.mWord = word.getWord();
        this.wordLength = word.getWordLength();
    }

    public void setWordId(int id) {
        this.wordId = id;
    }

    public String getWord() {
        return this.mWord;
    }

    public int getWordId() {
        return this.wordId;
    }

    public int getWordLength() {
        return this.wordLength;
    }

}
