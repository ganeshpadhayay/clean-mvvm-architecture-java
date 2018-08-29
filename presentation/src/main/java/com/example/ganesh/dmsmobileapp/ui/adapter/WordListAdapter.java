package com.example.ganesh.dmsmobileapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ganesh.dmsmobileapp.R;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater inflater;
    private List<com.example.domain.models.Word> words; // Cached copy of words

    public WordListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (words != null) {
            com.example.domain.models.Word current = words.get(position);
            holder.wordItemView.setText("Id: " + current.getWordId() + " word: " + current.getWord() + " word length: " + current.getWordLength());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    public void setWords(List<com.example.domain.models.Word> words) {
        this.words = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // words has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (words != null)
            return words.size();
        else return 0;
    }
}
