package edu.drexel.cs451.hangman;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SinglePlayerGame {

    private WordAccessor wordAccessor;
    private SinglePlayerScreenView view;
    public static int MaxGuesses = 6;
    private List<String> foundLetters;
    private List<String> missedLetters;
    private String pickedWord;
    private String neededLetters;
    private GameStatus status;
    private int numRemainingLetters;
    private HangmanGame game;

    public SinglePlayerGame(HangmanGame game) {
        wordAccessor = WordAccessor.getInstance();
        this.game = game;
    }

    public void setPickedWord(String word) {
        this.pickedWord = word.toUpperCase();
        System.out.println(pickedWord);
    }

    public void start() {
        wordAccessor.loadDictionary();
        setPickedWord(wordAccessor.getRandomWord());
        view = new SinglePlayerScreenView(this);
        game.changePanel(view);
        status = GameStatus.CONTINUE;
        view.requestFocus();
        view.requestFocusInWindow();

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

        if (status == GameStatus.LOSE)
            view.drawLoseScreen();
        else if (status == GameStatus.WIN)
            view.drawWinScreen();
    }

    private void guessWrong(String guessedCharacter) {
        missedLetters.add(guessedCharacter);
        view.disableLetter(guessedCharacter);
        view.hangNext();
        System.out.println("W: " + guessedCharacter);
    }

    private void guessRight(String guessedCharacter) {
        foundLetters.add(guessedCharacter);
        view.disableLetter(guessedCharacter);
        view.showLetter(guessedCharacter);
        numRemainingLetters--;
        System.out.println("R: " + guessedCharacter);
    }

    public void end() {
        game.changePanel(game.menuScreenView);
    }
}