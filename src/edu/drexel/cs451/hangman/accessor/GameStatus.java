package edu.drexel.cs451.hangman.accessor;

public enum GameStatus {
    WIN, // Game is finished and player won the game
    LOSE, // Game is finished and player lose the game
    CONTINUE, // Continue play
    TIMEOUT
}
