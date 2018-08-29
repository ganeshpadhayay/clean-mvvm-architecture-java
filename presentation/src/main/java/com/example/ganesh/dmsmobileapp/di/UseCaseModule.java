package com.example.ganesh.dmsmobileapp.di;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.repository.WordRepository;
import com.example.domain.usecases.DeleteThisWord;
import com.example.domain.usecases.GetAllWords;
import com.example.domain.usecases.GetSum;
import com.example.domain.usecases.GetTheIndexOfTopWord;
import com.example.domain.usecases.InsertWord;
import com.example.domain.usecases.UpdateThisWord;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    GetSum provideGetSum(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        return new GetSum(postExecutionThread, wordRepository);
    }

    @Provides
    GetAllWords provideGetAllWords(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        return new GetAllWords(postExecutionThread, wordRepository);
    }

    @Provides
    DeleteThisWord provideDeleteThisWord(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        return new DeleteThisWord(postExecutionThread, wordRepository);
    }

    @Provides
    InsertWord provideInsertWord(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        return new InsertWord(postExecutionThread, wordRepository);
    }

    @Provides
    UpdateThisWord provideUpdateThisWord(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        return new UpdateThisWord(postExecutionThread, wordRepository);
    }

    @Provides
    GetTheIndexOfTopWord provideGetTheIndexOfTopWord(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        return new GetTheIndexOfTopWord(postExecutionThread, wordRepository);
    }

}
