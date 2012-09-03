/**
 * **Add more documentation here **
 * Main class 
 */

package edu.drexel.cs451.hangman;

import javax.swing.JFrame;

public class HangmanGame {
    
    public static String title = "A New Hangman Game";
    
    private JFrame frame;
    public MenuScreenView menuScreenView = new MenuScreenView(this);
    
    //create a new game instance
    public HangmanGame() {
        frame = new JFrame(title);
    }
    
    public void start() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.changePanel(menuScreenView);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public void changePanel(GamePanel p) {
        frame.setContentPane(p);
        frame.pack();
        frame.repaint();
    }
    
    public static void main(String[] args) {
        final HangmanGame game = new HangmanGame();
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
        	public void run(){
        		game.start();
        	}
        });
    }

    //End the program
    public void end() {
        System.exit(0);
    }
}
