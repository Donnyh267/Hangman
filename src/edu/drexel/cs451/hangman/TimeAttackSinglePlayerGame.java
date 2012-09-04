package edu.drexel.cs451.hangman;

import java.util.Timer;
import java.util.TimerTask;

import edu.drexel.cs451.hangman.accessor.GameStatus;
import edu.drexel.cs451.hangman.accessor.WordAccessor;
import edu.drexel.cs451.hangman.view.TimeAttackSinglePlayerScreenView;

public class TimeAttackSinglePlayerGame extends SinglePlayerGame {

	private final static long time = 1 * 60 * 1000; // 3 mins in ms
	private int score;
	private long endTime;

	public TimeAttackSinglePlayerGame(HangmanGame game) {
		super(game);
	}

	@Override
	public void start() {
		this.endTime = System.currentTimeMillis() + time;
		setScore(0);
		newWord();
	}

	public void newWord() {
		this.setPickedWord(WordAccessor.getInstance().getRandomWord());
		view = new TimeAttackSinglePlayerScreenView(this);
		game.changePanel(view);
		status = GameStatus.CONTINUE;
		view.requestFocus();
		view.requestFocusInWindow();
	}

	@Override
	public void setGameStatus() {
		if (missedLetters.size() == MaxGuesses) {
			status = GameStatus.LOSE;
		} else if (numRemainingLetters == 0) {
			status = GameStatus.WIN;
			setScore(getScore() + 1);
		}
		if (status == GameStatus.LOSE)
			view.drawLoseScreen();
		else if (status == GameStatus.WIN)
			view.drawWinScreen();
		else
			return;
		view.repaint();
		
		//if still have time, new word!
		if (System.currentTimeMillis() < endTime) {
			new Timer().schedule(new TimerTask() {
				public void run() {
					newWord();
				}
			}, 500);
		}
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public long getEndTime() {
		return endTime;
	}

}
