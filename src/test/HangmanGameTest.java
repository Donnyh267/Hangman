package test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.drexel.cs451.hangman.GamePanel;
import edu.drexel.cs451.hangman.HangmanGame;

public class HangmanGameTest {

	@Test
	public void changePanel() {
		final HangmanGame game = new HangmanGame();
		game.start();
		final GamePanel panel = new GamePanel();
		game.changePanel(panel);		
		assertEquals(game.getCurrentPanel(),panel);
	}

}
