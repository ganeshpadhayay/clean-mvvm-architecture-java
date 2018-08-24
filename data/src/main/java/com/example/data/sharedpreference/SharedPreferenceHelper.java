package com.example.data.sharedpreference;

import android.content.Context;

public class SharedPreferenceHelper {

    private WordPreference wordPreference;

    public SharedPreferenceHelper(Context context) {
        if (wordPreference == null)
            wordPreference = new WordPreference(context);
    }

    public WordPreference getWordPreference() {
        return wordPreference;
    }
}
