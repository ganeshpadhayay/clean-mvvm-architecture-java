package com.example.ganesh.dmsmobileapp.ui.word;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ganesh.dmsmobileapp.MainApplication;
import com.example.ganesh.dmsmobileapp.R;
import com.example.ganesh.dmsmobileapp.base.BaseActivity;
import com.example.ganesh.dmsmobileapp.ui.adapter.WordListAdapter;
import com.example.ganesh.dmsmobileapp.ui.newword.NewWordActivity;

import java.util.List;

import javax.inject.Inject;

import static com.example.ganesh.dmsmobileapp.utils.Const.TOP_INDEX_ACTION_DELETE;
import static com.example.ganesh.dmsmobileapp.utils.Const.TOP_INDEX_ACTION_UPDATE;

public class WordActivity extends BaseActivity<WordViewModel> implements View.OnClickListener, WordNavigator {

    @Inject
    WordViewModel wordViewModel;

    private Button deleteButton, updateButton;
    private WordListAdapter adapter;


    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        ((MainApplication) getApplicationContext()).getComponent().inject(this);

        deleteButton = findViewById(R.id.activity_main_delete_button);
        updateButton = findViewById(R.id.activity_main_update_button);

        deleteButton.setOnClickListener(this);
        updateButton.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WordActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.setNavigator(this);
        wordViewModel.getAllWords();
//        wordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
//            @Override
//            public void onChanged(@Nullable List<Word> words) {
//                //update the cached copy of words in adapter
//                adapter.setWords(words);
//            }
//        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            com.example.domain.models.Word word = new com.example.domain.models.Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY), data.getStringExtra(NewWordActivity.EXTRA_REPLY).length());
            wordViewModel.insertWord(word);
        } else {
            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_delete_button:
                wordViewModel.getTheIndexOfTopWord(TOP_INDEX_ACTION_DELETE);

                break;
            case R.id.activity_main_update_button:
                wordViewModel.getTheIndexOfTopWord(TOP_INDEX_ACTION_UPDATE);
        }
    }


    @Override
    public void updateWordList(List<com.example.domain.models.Word> words) {
        adapter.setWords(words);

    }

    @Override
    public void updateTopIndex(Integer wordId, String action) {
        if (action.equals(TOP_INDEX_ACTION_DELETE))
            wordViewModel.deleteThisWord(wordId);
        else
            wordViewModel.updateThisWord(wordId, "dsakagdad");
    }

    @Override
    public WordViewModel getViewModel() {
        return wordViewModel;
    }
}
