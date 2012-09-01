/**
 * **Add more documentation here **
 * Main class 
 */

package edu.drexel.cs451.hangman;

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
        final HangmanGame game = new HangmanGame();
        
        //TODO:This section is for testing purposes. Remove when GUIs are implemented
        WordAccessor wordAccessor = new WordAccessor("HangmanWords.txt");
        wordAccessor.loadDictionary();
        String ramdomWord = wordAccessor.getRandomWord();
        System.out.println("Random word provided was " + ramdomWord);
        
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
        	public void run(){
        		game.start();
        	}
        });
        
        
        
    }
}
