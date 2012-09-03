package edu.drexel.cs451.hangman;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SinglePlayerGame {
	
	private WordAccessor wordAccessor;
	private SinglePlayerScreenView view;
	private int maxGuesses = 7;
	private  List<String> foundLetters;
	private  List<String> missedLetters;
	private String pickedWord;
	private String neededLetters;
	private GameStatus status;
	private int numRemainingLetters;
	
	
	
	public SinglePlayerGame(WordAccessor wordAccessor){
		this.wordAccessor= wordAccessor;
	}
	
	public void setPickedWord(String word)
	{
		this.pickedWord = word;
	}
	
	public void Start(){
		wordAccessor.loadDictionary();
		setPickedWord(wordAccessor.getRandomWord());
		
		
		//keep playing while status is continue and keep checking for win or lose
		while (status == GameStatus.CONTINUE)
		{
			//TODO Implement startGame
			
			String guessedLetter = view.getGuessedLetter();
			if(neededLetters.contains(guessedLetter))
			{
				foundLetters.add(guessedLetter);
				numRemainingLetters--;
			}
			setGameStatus();
		}
		
		if (status == GameStatus.LOSE)
			view.drawLoseScreen();		
		else
			view.drawWinScreen();
		
		view.drawPrompt(pickedWord);
		
	}
	
	public Boolean checkLetter(String letter)
	{
		String letterToCheck = null;
			
		for (int i =0; i< pickedWord.length(); i++)
		{
			letterToCheck = pickedWord.substring(i, i+1).toUpperCase();
			if(letterToCheck.equals(letter.trim().toUpperCase()))
				return true;
		}
		
		return false;		
	}
	
	//sets the unique letters from the picked word that the user needs to guess to win
	public void neededLetters(String word)
	{
		   char[] chars = word.toCharArray();
		    Set<Character> charSet = new LinkedHashSet<Character>();
		    for (char c : chars) {
		        charSet.add(c);
		    }

		    StringBuilder sb = new StringBuilder();
		    for (Character character : charSet) {
		        sb.append(character);
		    }
		    
		    neededLetters =sb.toString();
		
	}
	
	public void setGameStatus()
	{
		if (missedLetters.size() == maxGuesses)
			status = GameStatus.LOSE;
		else if (numRemainingLetters == 0)
			status =  GameStatus.WIN;
		else
			status = GameStatus.CONTINUE;
	}

}