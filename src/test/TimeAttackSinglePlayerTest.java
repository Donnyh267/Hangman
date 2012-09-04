package test;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import org.junit.Test;

import edu.drexel.cs451.hangman.HangmanGame;
import edu.drexel.cs451.hangman.TimeAttackSinglePlayerGame;

public class TimeAttackSinglePlayerTest {

	@Test
	public void setScore() {
		final HangmanGame  hangmanGame = mock(HangmanGame.class);
		final TimeAttackSinglePlayerGame game = new TimeAttackSinglePlayerGame(hangmanGame);
		game.start();
		final int score = 1;
		
		game.setScore(score);
		assertEquals(game.getScore(),score);
	}

}
