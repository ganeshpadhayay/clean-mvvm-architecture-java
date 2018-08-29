package com.example.data.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.data.models.Word;

import java.util.List;

//this is the DAO(Data Access Object) which generates the SQL queries using annotations such as @Insert
//Room uses the DAO to create a clean API for your code.
@Dao
public interface WordDao {
    @Insert
    void insertThisWord(Word word);

    @Query("delete from word_table")
    void deleteAll();

    @Query("select * from word_table order by word_id asc")
    List<Word> getAllWords();
    //LiveData<T> has no public methods to set/post value in LiveData as here we won't be adding values to this list
    //it will be synced with the room database
    //If the developer wants to update the contents of LiveData then he/she should use MutableLiveData which has
    //setValue(T) and postValue(T) methods
    //Usually, MutableLiveData is used in the ViewModel, and then the ViewModel only exposes immutable LiveData objects to the observers.

    @Query("delete from word_table where word_id = :wordId")
    void deleteThisWord(int wordId);

    @Query("update word_table set word = :newWord where word_id = :wordId")
    void updateThisWord(int wordId, String newWord);

    @Query("select * from word_table order by word_id asc limit 1")
    Word getTheIndexOfTopWord();
}


//@Insert(onConflict = OnConflictStrategy.REPLACE) for CONFLICT_STRATEGY
/*

You create an Observer of the data in the onCreate() method of MainActivity and override the observer's onChanged()
method. When the LiveData changes, the observer is notified and onChanged() is executed. You will then update the
cached data in the adapter, and the adapter will update what the user sees.
 */