package edu.drexel.cs451.hangman.view;

import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import edu.drexel.cs451.hangman.MultiPlayerGame;


public class MultiplayerScreenView extends SinglePlayerScreenView {

    private final static String NAME = "Hangman - Multiplayer";
    
    private MultiPlayerGame multiGame;
    private static JTextPane board = new JTextPane();
    
    public MultiplayerScreenView(MultiPlayerGame game) {
        super(game);
        this.multiGame = game;
        GridBagConstraints cs = new GridBagConstraints();
        cs.gridx = 1; cs.gridy = 1; cs.gridheight = 3;
        cs.fill = GridBagConstraints.BOTH;
        JScrollPane boardScrl = new JScrollPane(board);
        boardScrl.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(boardScrl, cs);
        this.setName(NAME);
    }

    private static final long serialVersionUID = -8028389762745074244L;

    public void addMsg(String string) {
        board.setText(board.getText() + "\n" + string + "\n");
        board.repaint();
        repaint();
    }

    public void makeWait() {
        this.drawLoseScreen();
    }
}
