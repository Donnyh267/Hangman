package test;

import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import edu.drexel.cs451.hangman.accessor.GameStatus;
import edu.drexel.cs451.hangman.HangmanGame;
import edu.drexel.cs451.hangman.SinglePlayerGame;

public class SinglePlayerGameTest {

    @Test
    public void checkLetterReturnsTrue() {
        final String letter = "l";
        final String pickedWord = "yellow";
        
        final HangmanGame  hangmanGame = mock(HangmanGame.class);

        final SinglePlayerGame game = new SinglePlayerGame(hangmanGame);
        game.setPickedWord(pickedWord);
        assertTrue(game.checkLetter(letter));

    }
    
    @Test
    public void checkLetterReturnsFalse() {
        final String letter = "z";
        final String pickedWord = "yellow";
        
        final HangmanGame  hangmanGame = mock(HangmanGame.class);

        final SinglePlayerGame game = new SinglePlayerGame(hangmanGame);
        game.setPickedWord(pickedWord);
        assertFalse(game.checkLetter(letter));
    }
    

}
