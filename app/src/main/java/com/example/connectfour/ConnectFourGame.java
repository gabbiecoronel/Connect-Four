package com.example.connectfour;

public class ConnectFourGame {

    // initializing variables
    int ROW = 7, DISCS = 42, player = BLUE;
    static int EMPTY = 0, BLUE = 1, RED = 2, COL = 6;
    // creating the Connect Four boardGrid
    private final int[][] boardGrid = new int[ROW][COL];

    // newGame method creates a new Connect Four game
    public void newGame() {
        player = BLUE;
        // for loop runs through the number of rows in the Connect Four boardGrid
        for (int i = 0; i < ROW; i++) {
            // for loop runs through the number of columns in the Connect Four boardGrid
            for (int j = 0; j < COL; j++)
                boardGrid[i][j] = EMPTY;
        }
    }

    // getDisc method finds the current disc of the boardGrid
    public int getDisc(int ROW, int COL) {
        return boardGrid[ROW][COL];
    }

    // isGameOver method determines if the game is over
    public boolean isGameOver() {
        // for loop runs through the number of rows
        for (int row = 0; row < ROW; row++)
            // for loop runs through the number of columns
            for (int col = 0; col < COL; col++) {
                // checks if the board grid is empty and there's no win
                if (boardGrid[row][col] == EMPTY && !isWin())
                    return false;
            }
        // there's a win
        return true;
    }

    // isWin method determines if there's a winner
    public boolean isWin() {
        // checks if there's four in a row either horizontally, vertically, or diagonally
        return checkHorizontal() || checkVertical() || checkDiagonal();
    }

    // checkHorizontal method checks if there's a horizontal win
    public boolean checkHorizontal() {
        // for loop runs through the number of rows
        for (int row = 0; row < ROW; row++) {
            // for loop runs through the number of columns
            for (int col = 0; col < COL - 3; col++) {
                if (boardGrid[row][col] != EMPTY && boardGrid[row][col] == boardGrid[row][col + 1] && boardGrid[row][col] == boardGrid[row][col + 2] && boardGrid[row][col] == boardGrid[row][col + 3])
                    return true;
            }
        }
        return false;
    }

    // checkVertical method checks if there's a vertical win
    public boolean checkVertical() {
        // for loop runs through the number of cols
        for (int col = 0; col < COL; col++)
            // for loop runs through the number of rows
            for (int row = 0; row < ROW - 3; row++) {
                if (boardGrid[row][col] != EMPTY && boardGrid[row][col] == boardGrid[row + 1][col] && boardGrid[row][col] == boardGrid[row + 2][col] && boardGrid[row][col] == boardGrid[row + 3][col]) {
                    return true;
                }
            }
        return false;
    }

    // checkDiagonal method checks if there's a diagonal win
    public boolean checkDiagonal() {
        for (int row = 0; row < ROW - 3; row++) {
            for (int col = 0; col < COL - 3; col++) {
                // checks top-left to bottom-right diagonals
                if (boardGrid[row][col] != EMPTY && boardGrid[row][col] == boardGrid[row + 1][col + 1] && boardGrid[row][col] == boardGrid[row + 2][col + 2] && boardGrid[row][col] == boardGrid[row + 3][col + 3]) {
                    return true;
                }
            }
        }

        for (int row = 0; row < ROW - 3; row++) {
            for (int col = 3; col < COL; col++) {
                // checks top-right to bottom-left diagonals
                if (boardGrid[row][col] != EMPTY && boardGrid[row][col] == boardGrid[row + 1][col - 1] && boardGrid[row][col] == boardGrid[row + 2][col - 2] && boardGrid[row][col] == boardGrid[row + 3][col - 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    // setState method sets the game state
    public void setState(String gameState) {
        // Assuming gameState is a string representation of the board
        String[] stateArray = gameState.split(",");
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                boardGrid[i][j] = Integer.parseInt(stateArray[i * COL + j]);
    }

    // method selectDisc selects the disc in the board grid
    public void selectDisc(int row, int col) {
        for (row = ROW - 1; row >= 0; row--) {
            // checks if the cell in the board grid is empty
            if (boardGrid[row][col] == EMPTY) {
                // current cell in board grid is player
                boardGrid[row][col] = player;

                // checks if the current player is blue
                if (player == BLUE)
                    // changing the current player to red
                    player = RED;
                // player blue's turn
                else
                    player = BLUE;
                break;
            }
        }
    }

    // method getState gets the state
    public String getState() {
        // creating a string builder
        StringBuilder stateBuilder = new StringBuilder();

        // for loop runs through the Connect Four boardGrid
        for (int[] ints : boardGrid)
            for (int anInt : ints)
                // append each element to the string builder
                stateBuilder.append(anInt);
        // return string builder with toString()
        return stateBuilder.toString();
    }
}