package edu.drexel.cs451.hangman;

import javax.swing.JPanel;

public class SinglePlayerScreenView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public SinglePlayerScreenView(HangmanGame hangmanGame) {
        // TODO Auto-generated constructor stub

    }
    
    public void drawPrompt(String pickedWord){
    	//TODO Draws the prompt for the word to be guessed on the GUI screen
    	// dashes (_) should be OK.
    }

	public void drawLoseScreen() {
		// TODO Auto-generated method stub
		
	}

	public void drawWinScreen() {
		// TODO Auto-generated method stub
		
	}

	public String getGuessedLetter() {
		// TODO Auto-generated method stub
		return null;
	}

}
