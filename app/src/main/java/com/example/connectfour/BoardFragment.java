package com.example.connectfour;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

public class BoardFragment extends Fragment {

    // initializing variables
    private static final String GAME_STATE = "gameState";
    ConnectFourGame mGame;
    GridLayout mGrid;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // instantiate connect four game
        mGame = new ConnectFourGame();

        // checks if the bundle saved instance state is null
        if (savedInstanceState == null)
            startGame();

        // bundle saved instance state is not null
        else {
            // initializing string gameState
            String gameState = savedInstanceState.getString(GAME_STATE);
            if (gameState != null)
                mGame.setState(gameState);
            setDisc();
        }

        // inflating fragment_board layout
        View view = inflater.inflate(R.layout.fragment_board, container, false);

        // instantiating the mGrid variable to the GridLayout in fragment_board
        mGrid = view.findViewById(R.id.boardFragment);

        // for loop runs through the Button elements to set an onClick listener
        for (int i = 0; i < mGrid.getChildCount(); i++) {
            View child = mGrid.getChildAt(i);
            if (child instanceof Button)
                child.setOnClickListener(this::onButtonClick);
        }
        return view;
    }

    // onButtonClick method
    public void onButtonClick(View view) {
        // initializing variables buttonIndex, row, and col
        int buttonIndex = mGrid.indexOfChild(view), row = buttonIndex / ConnectFourGame.COL, col = buttonIndex % ConnectFourGame.COL;

        // calling selectDisc method
        mGame.selectDisc(row, col);
        setDisc();

        // checks if game is over
        if (mGame.isGameOver()) {
            // checks if there's a win
            if (mGame.isWin())
                Toast.makeText(getActivity(), "Congratulations you win!", Toast.LENGTH_SHORT).show();
            // creating a new game
            mGame.newGame();
            setDisc();
        }
    }

    // start game method
    public void startGame() {
        mGame.newGame();
        setDisc();
    }

    // setDisc method
    public void setDisc() {
        if (mGrid == null || getActivity() == null) return;
        for (int buttonIndex = 0; buttonIndex < mGrid.getChildCount(); buttonIndex++) {
            // instantiating an instance of class button
            Button gridButton = (Button) mGrid.getChildAt(buttonIndex);

            // find the button's row and col
            int row = buttonIndex / ConnectFourGame.COL, col = buttonIndex % ConnectFourGame.COL;

            // instantiating an instance class drawable for three drawable discs
            Drawable white = ContextCompat.getDrawable(getActivity(), R.drawable.circle_white), red = ContextCompat.getDrawable(getActivity(), R.drawable.circle_red), blue = ContextCompat.getDrawable(getActivity(), R.drawable.circle_blue);

            // setting each drawable object equal DrawableCompat.wrap
            if (white != null)
                white = DrawableCompat.wrap(white);
            if (red != null)
                red = DrawableCompat.wrap(red);
            if (blue != null)
                blue = DrawableCompat.wrap(blue);

            // getting the value of the element stored in the current row and column
            mGame.getDisc(row, col);

            // checks if the value of the element stored in the current row and column is blue
            if (mGame.getDisc(row, col) == ConnectFourGame.BLUE)
                gridButton.setBackground(blue);

            // checks if the value of the element stored in the current row and column is red
            else if (mGame.getDisc(row, col) == ConnectFourGame.RED)
                gridButton.setBackground(red);

            // checks if the value of the element stored in the current row and column is empty
            else if (mGame.getDisc(row, col) == ConnectFourGame.EMPTY)
                gridButton.setBackground(white);
        }
    }

    // Override onSaveInstanceState
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Call putString on outState, passing GAME_STATE and mGame.getState()
        outState.putString(GAME_STATE, mGame.getState());
    }
}