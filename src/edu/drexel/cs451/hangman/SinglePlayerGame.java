package edu.drexel.cs451.hangman;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import edu.drexel.cs451.hangman.accessor.GameStatus;
import edu.drexel.cs451.hangman.accessor.WordAccessor;
import edu.drexel.cs451.hangman.view.SinglePlayerScreenView;

public class SinglePlayerGame {

	protected WordAccessor wordAccessor;
	protected SinglePlayerScreenView view;
	public static int MaxGuesses = 6;
	protected List<String> foundLetters;
	protected List<String> missedLetters;
	protected String pickedWord;
	protected String neededLetters;
	protected GameStatus status;
	protected int numRemainingLetters;
	protected HangmanGame game;

	public SinglePlayerGame(HangmanGame game) {
		wordAccessor = WordAccessor.getInstance();
		this.game = game;
	}

	public void setPickedWord(String word) {
		this.pickedWord = word.toUpperCase();

		char[] chars = pickedWord.toCharArray();
		Set<Character> charSet = new LinkedHashSet<Character>();
		for (char c : chars) {
			charSet.add(c);
		}

		StringBuilder sb = new StringBuilder();
		for (Character character : charSet) {
			sb.append(character);
		}

		neededLetters = sb.toString();
		neededLetters = neededLetters.toUpperCase();

		foundLetters = new ArrayList<String>();
		missedLetters = new ArrayList<String>();
		numRemainingLetters = neededLetters.length();
		System.out.println(pickedWord);
	}

	// start a single game
	public void start() {
		setPickedWord(wordAccessor.getRandomWord());
		view = new SinglePlayerScreenView(this);
		game.changePanel(view);
		status = GameStatus.CONTINUE;
		getView().requestFocus();
		getView().requestFocusInWindow();
	}

	public Boolean checkLetter(String letter) {
		String letterToCheck = null;

		for (int i = 0; i < pickedWord.length(); i++) {
			letterToCheck = pickedWord.substring(i, i + 1).toUpperCase();
			if (letterToCheck.equals(letter.trim().toUpperCase()))
				return true;
		}

		return false;
	}

	public void setGameStatus() {
		if (missedLetters.size() == MaxGuesses)
			status = GameStatus.LOSE;
		else if (numRemainingLetters == 0)
			status = GameStatus.WIN;

		if (status == GameStatus.LOSE)
			getView().drawLoseScreen();
		else if (status == GameStatus.WIN)
			getView().drawWinScreen();
	}
	
	public GameStatus getGameStatus()
	{
		return status;
	}
	public String getPickedWord() {
		return pickedWord;
	}

	public void guess(String guessedCharacter) {
		if (status == GameStatus.CONTINUE) {
			if (neededLetters.contains(guessedCharacter)) {
				if (!foundLetters.contains(guessedCharacter)) {
					// find a correct letter
					guessRight(guessedCharacter);
				}
			} else if (!missedLetters.contains(guessedCharacter)) {
				guessWrong(guessedCharacter);
			}
		}

		setGameStatus();
	}

	private void guessWrong(String guessedCharacter) {
		missedLetters.add(guessedCharacter);
		getView().disableLetter(guessedCharacter);
		getView().hangNext();
		System.out.println("W: " + guessedCharacter);
	}

	private void guessRight(String guessedCharacter) {
		foundLetters.add(guessedCharacter);
		getView().disableLetter(guessedCharacter);
		getView().showLetter(guessedCharacter);
		numRemainingLetters--;
		System.out.println("R: " + guessedCharacter);
	}

	public void exit() {
		game.changePanel(game.menuScreenView);
	}

    public SinglePlayerScreenView getView() {
        return view;
    }
}