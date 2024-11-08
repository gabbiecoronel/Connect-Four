package com.example.connectfour;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GameOptionsFragment extends Fragment {

    private static final String PREFS_NAME = "GamePreferences";
    private static final String LEVEL_KEY = "selected_level";

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the fragment_game_options layout
        View view = inflater.inflate(R.layout.fragment_game_options, container, false);

        // Instantiate SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Find the RadioGroup
        RadioGroup difficultyGroup = view.findViewById(R.id.buttons);

        // Select the radio button matching the level selected in SharedPreferences
        int selectedLevelId = sharedPreferences.getInt(LEVEL_KEY, R.id.easy); // Default is "Easy"
        RadioButton selectedButton = view.findViewById(selectedLevelId);

        // Set the selected radio button so it is checked
        if (selectedButton != null) {
            selectedButton.setChecked(true);
        }

        //  Add click listener to radio buttons
        difficultyGroup.setOnCheckedChangeListener(this::onLevelSelected);
        return view;
    }

    // Update method onLevelSelected
    public void onLevelSelected(RadioGroup group, int checkedId) {
        // Save selected level ID in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(LEVEL_KEY, checkedId);
        editor.apply();
    }
}
