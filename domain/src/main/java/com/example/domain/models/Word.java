package com.example.domain.models;

import java.util.Objects;

public class Word {

    private int wordId;
    private String word;
    private int wordLength;

    public Word(int wordId, String word, int wordLength) {
        this.wordId = wordId;
        this.word = word;
        this.wordLength = wordLength;
    }

    public int getWordId() {
        return wordId;
    }

    public Word(String word, int wordLength) {
        this.word = word;
        this.wordLength = wordLength;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return wordId == word1.wordId &&
                Objects.equals(word, word1.word) &&
                Objects.equals(wordLength, word1.wordLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordId, word, wordLength);
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordId=" + wordId +
                ", word='" + word + '\'' +
                ", wordLength='" + wordLength + '\'' +
                '}';
    }
}
