package edu.drexel.cs451.hangman.view;

import java.awt.Graphics;

import javax.swing.JPanel;

//Abstract panel - drawing man being hanged
public abstract class HangingPanel extends JPanel {

    private static final long serialVersionUID = 8185843239718132297L;
    protected int currentState = 0;
    
    // the next stage of the man being Hanged;
    public abstract void next();
    
    // the current state
    public int getCurrentState() {
        return currentState;
    }
}
