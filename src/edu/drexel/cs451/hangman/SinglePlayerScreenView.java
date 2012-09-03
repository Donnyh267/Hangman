package edu.drexel.cs451.hangman;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import edu.drexel.cs451.hangman.view.AllLettersPanel;
import edu.drexel.cs451.hangman.view.BasicHangingPanel;
import edu.drexel.cs451.hangman.view.GuessedLettersPanel;
import edu.drexel.cs451.hangman.view.HangingPanel;

public class SinglePlayerScreenView extends GamePanel implements MouseListener,
        KeyListener {

    private static final long serialVersionUID = 1L;
    private HangmanGame game;
    private SinglePlayerGame singleGame;

    private JButton backButton;
    private HangingPanel hangingManPanel;
    private GuessedLettersPanel mainLetters;
    private AllLettersPanel allLetters;
    private String pickedWord;

    public SinglePlayerScreenView(final SinglePlayerGame singleGame) {
        this.singleGame = singleGame;
        this.setLayout(new GridBagLayout());
        this.pickedWord = singleGame.getPickedWord();
        GridBagConstraints cs = new GridBagConstraints();
        this.setPreferredSize(new Dimension(600,400));
        cs.gridx = 0;
        cs.gridy = 0;
        cs.anchor = GridBagConstraints.EAST;
        backButton = new JButton("Back");
        this.add(backButton, cs);
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                singleGame.end();
            }});

        hangingManPanel = new BasicHangingPanel();
        cs.gridx = 0;
        cs.gridy = 1;
        cs.anchor = GridBagConstraints.CENTER;
        cs.fill = GridBagConstraints.HORIZONTAL;
        this.add(hangingManPanel, cs);

        mainLetters = new GuessedLettersPanel(pickedWord);
        cs.gridx = 0;
        cs.gridy = 2;
        this.add(mainLetters, cs);

        allLetters = new AllLettersPanel(this);
        cs.gridx = 0;
        cs.gridy = 3;
        this.add(allLetters, cs);

        this.setFocusable(true);
        this.addKeyListener(this);

    }

    public void drawLoseScreen() {
        // TODO Change this
        mainLetters.lose();
        allLetters.disableAll();
    }

    public void drawWinScreen() {
        // TODO Change this
        mainLetters.win();
        allLetters.disableAll();
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        if (arg0.getKeyChar() >= 'a' && arg0.getKeyChar() <= 'z') {
            singleGame.guess(String.valueOf(arg0.getKeyChar()).toUpperCase());
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        if (arg0.getComponent().getName() == AllLettersPanel.NAME) {
            singleGame.guess(((JLabel) arg0.getComponent()).getText());
        }
    }

    public void disableLetter(String guessedCharacter) {
        allLetters.disableLetter(guessedCharacter.charAt(0));
    }

    public void showLetter(String guessedCharacter) {
        for (int i = 0; i < pickedWord.length(); i++)
            if (guessedCharacter.charAt(0) == pickedWord.charAt(i))
                mainLetters.show(i);
    }

    public void hangNext() {
        hangingManPanel.next();
    }

}
