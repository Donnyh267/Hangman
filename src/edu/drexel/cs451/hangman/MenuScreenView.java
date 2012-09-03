/**
 * The view for menu screen
 * Set up layout and listener
 */
package edu.drexel.cs451.hangman;

import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuScreenView extends GamePanel implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JButton singleB, multiB, timeB, exitB;
    private HangmanGame game;

    public MenuScreenView(HangmanGame hangmanGame) {
        super();
        this.game = hangmanGame;
        this.setLayout(new GridLayout(3, 0));

        singleB = new JButton("Single Player");
        singleB.addActionListener(this);
        multiB = new JButton("Multi Player");
        multiB.addActionListener(this);
        timeB = new JButton("Time Attack");
        timeB.addActionListener(this);
        exitB = new JButton("Exit");
        exitB.addActionListener(this);

        this.add(singleB);
        this.add(multiB);
        this.add(timeB);
        this.add(exitB);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == singleB) {
            // TODO: Single Game
            // SinglePlayerScreenView single = new SinglePlayerScreenView(game);
            // game.display(single);
        } else if (ae.getSource() == multiB) {
            // TODO: play multi player
        } else if (ae.getSource() == timeB) {
            // TODO: play time attack
        } else if (ae.getSource() == exitB) {
            game.end();
        }
    }
}
