/**
 * The view for menu screen
 * Set up layout and listener
 */
package edu.drexel.cs451.hangman;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MenuScreenView extends GamePanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JButton singleB, multiB, timeB, exitB;
    private HangmanGame game;

    public MenuScreenView(HangmanGame hangmanGame) {
        super();
        this.game = hangmanGame;
        this.setLayout(new GridBagLayout());

        singleB = new JButton("Single Player");
        singleB.addActionListener(this);
        multiB = new JButton("Multi Player");
        multiB.addActionListener(this);
        timeB = new JButton("Time Attack");
        timeB.addActionListener(this);
        exitB = new JButton("Exit");
        exitB.addActionListener(this);

        this.setPreferredSize(new Dimension(600,400));
        GridBagConstraints cs = new GridBagConstraints();
        cs.insets.set(5,5,5,5); cs.fill = GridBagConstraints.BOTH;
        
        cs.gridx = 0; cs.gridy = 0; this.add(singleB,cs);
        cs.gridx = 1; cs.gridy = 0; this.add(multiB,cs);
        cs.gridx = 0; cs.gridy = 1; this.add(timeB,cs);
        cs.gridx = 1; cs.gridy = 1; this.add(exitB,cs);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == singleB) {
            SinglePlayerGame singleGame = new SinglePlayerGame(game);
            singleGame.start();

        } else if (ae.getSource() == multiB) {
            // TODO: play multi player
        } else if (ae.getSource() == timeB) {
            // TODO: play time attack
        } else if (ae.getSource() == exitB) {
            game.end();
        }
    }
}
