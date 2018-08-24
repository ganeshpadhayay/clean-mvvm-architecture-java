package com.example.ganesh.dmsmobileapp.di;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.repository.WordRepository;
import com.example.domain.usecases.GetSum;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    GetSum provideGetSum(PostExecutionThread postExecutionThread, WordRepository wordRepository) {
        return new GetSum(postExecutionThread, wordRepository);
    }

}
