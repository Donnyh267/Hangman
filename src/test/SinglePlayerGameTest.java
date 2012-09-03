package test;

import static org.junit.Assert.*;

import org.mockito.Mock;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import edu.drexel.cs451.hangman.SinglePlayerGame;


public class SinglePlayerGameTest {

	@Test
	public void checkLetter() {
		final String letter = "l";
		final String pickedWord = "yellow";
		
		final SinglePlayerGame game = mock(SinglePlayerGame.class);
		game.setPickedWord(pickedWord);
		//assertTrue(game.checkLetter(letter));
		
		
		
	}

}
