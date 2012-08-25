/**
 * **Add more documentation here **
 * Main class 
 */

package edu.drexel.cs451.hangman;

import java.awt.Container;

import javax.swing.JFrame;

public class HangmanGame {
    
    public static String title = "A New Hangman Game";
    
    //create a new game instance
    public HangmanGame() {
        
    }
    
    public void start() {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuScreenView menuScreenView = new MenuScreenView(this);
        frame.setContentPane(menuScreenView);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        HangmanGame game = new HangmanGame();
        
        
    }
}
