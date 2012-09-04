/**
 * **Add more documentation here **
 * Main class 
 */

package edu.drexel.cs451.hangman;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.drexel.cs451.hangman.view.GamePanel;
import edu.drexel.cs451.hangman.view.MenuScreenView;

public class HangmanGame {

    public static String title = "A New Hangman Game";

    private JFrame frame;
    private JPanel currentPanel;
    public MenuScreenView menuScreenView;

    // create a new game instance
    public HangmanGame() {
        frame = new JFrame(title);
        frame.setContentPane(new JPanel(new BorderLayout()));
    }

    public void start() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuScreenView = new MenuScreenView(this);
        frame.getContentPane().add(menuScreenView, BorderLayout.CENTER);
        currentPanel = menuScreenView;

        frame.pack();
        frame.setVisible(true);
    }

    public void changePanel(GamePanel p) {
    	frame.getContentPane().remove(currentPanel);
    	frame.getContentPane().add(p, BorderLayout.CENTER);
    	frame.getContentPane().repaint();
    	frame.setTitle(p.getName());
        frame.setVisible(true);
    	currentPanel = p;
    }
    
    public GamePanel getCurrentPanel()
    {
    	return (GamePanel) this.currentPanel;
    }

    public static void main(String[] args) {
        final HangmanGame game = new HangmanGame();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                game.start();
            }
        });
    }

    // End the program
    public void end() {
        System.exit(0);
    }
}
