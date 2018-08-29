package com.example.ganesh.dmsmobileapp.ui.word;

import com.example.domain.models.Word;

import java.util.List;

public interface WordNavigator {
    void updateWordList(List<Word> words);

    void updateTopIndex(Integer integer, String action);
}
