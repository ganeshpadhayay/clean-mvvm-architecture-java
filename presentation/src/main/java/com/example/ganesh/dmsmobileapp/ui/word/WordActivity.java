package com.example.ganesh.dmsmobileapp.ui.word;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.domain.models.Word;
import com.example.ganesh.dmsmobileapp.MainApplication;
import com.example.ganesh.dmsmobileapp.R;
import com.example.ganesh.dmsmobileapp.base.BaseActivity;
import com.example.ganesh.dmsmobileapp.ui.adapter.WordListAdapter;

import java.util.List;

import javax.inject.Inject;

import static com.example.ganesh.dmsmobileapp.utils.Const.TOP_INDEX_ACTION_DELETE;
import static com.example.ganesh.dmsmobileapp.utils.Const.TOP_INDEX_ACTION_UPDATE;

public class WordActivity extends BaseActivity<WordViewModel> implements View.OnClickListener, WordNavigator {

    @Inject
    WordViewModel wordViewModel;

    private Button deleteButton, updateButton, addButton;
    private EditText editTextWord;
    private WordListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        ((MainApplication) getApplicationContext()).getComponent().inject(this);
        editTextWord = findViewById(R.id.activity_word_et_word);
        deleteButton = findViewById(R.id.activity_main_delete_button);
        updateButton = findViewById(R.id.activity_main_update_button);
        addButton = findViewById(R.id.activity_word_btn_add_word);

        deleteButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);
        addButton.setOnClickListener(this);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getViewModel().setNavigator(this);

        getViewModel().getAllWords();

        getViewModel().getWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable List<Word> words) {
                adapter.setWords(words);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_delete_button:
                getViewModel().getTheIndexOfTopWord(TOP_INDEX_ACTION_DELETE);
                break;
            case R.id.activity_main_update_button:
                getViewModel().getTheIndexOfTopWord(TOP_INDEX_ACTION_UPDATE);
                break;
            case R.id.activity_word_btn_add_word:
                handleAddButtonClick();
                break;
        }
    }

    public void handleAddButtonClick() {
        String text = editTextWord.getText().toString();
        if (text.equals("")) {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        } else {
            Word word = new Word(text, text.length());
            getViewModel().insertWord(word);
            editTextWord.setText("");
            editTextWord.clearFocus();
        }
    }

    @Override
    public void updateTopIndex(Integer wordId, String action) {
        if (action.equals(TOP_INDEX_ACTION_DELETE))
            getViewModel().deleteThisWord(wordId);
        else
            getViewModel().updateThisWord(wordId, "dsakagdad");
    }

    @Override
    public WordViewModel getViewModel() {
        return wordViewModel;
    }
}
