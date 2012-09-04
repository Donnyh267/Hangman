package edu.drexel.cs451.hangman.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.Date;

import javax.swing.JLabel;

import edu.drexel.cs451.hangman.TimeAttackSinglePlayerGame;

public class TimeAttackSinglePlayerScreenView extends SinglePlayerScreenView {
	
	private final JLabel stopWatch, score;

	private final static java.text.SimpleDateFormat timerFormat = new java.text.SimpleDateFormat("mm : ss");
	private Thread updater;
	private static final String NAME = "Hangman - Time Attack";
	private final TimeAttackSinglePlayerScreenView thisview = this;
	private final TimeAttackSinglePlayerGame attackGame;

	public TimeAttackSinglePlayerScreenView(final TimeAttackSinglePlayerGame attackGame) {
		super(attackGame);
		this.attackGame = attackGame;
		GridBagConstraints cs = new GridBagConstraints();
		cs.gridx = 2; cs.gridy  = 0;
		stopWatch = new JLabel("Time left: ");
		this.add(stopWatch,cs);
		cs.gridx = 3; cs.gridy = 0;
		score = new JLabel(" Score: " + attackGame.getScore());
		this.add(score, cs);
		this.setName(NAME);
		
		updater = new Thread(new Runnable(){
			@Override
			public void run() {
				while (System.currentTimeMillis() < attackGame.getEndTime()) {
					long timeleft = attackGame.getEndTime() - System.currentTimeMillis();
					stopWatch.setText("Time left: " + timerFormat.format(new Date(timeleft)));
					if (timeleft <= 10000) {
						stopWatch.setForeground(Color.RED);
					}
					thisview.repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				thisview.drawLoseScreen();
			}});
		updater.start();
	}
	
	private static final long serialVersionUID = -3163157828605285460L;

	@Override
    public void drawWinScreen() {
		super.drawWinScreen();
		score.setText(" Score: " + attackGame.getScore());
    }

}
