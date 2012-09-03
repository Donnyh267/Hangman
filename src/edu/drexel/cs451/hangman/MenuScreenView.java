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

public class MenuScreenView extends JPanel implemnts ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*public MenuScreenView(HangmanGame hangmanGame) {
		// TODO Auto-generated constructor stub
	}*/
	//ray starts messing with things here

	static JButton singleB, multiB, timeB;
	BufferedImage image;
	JFrame frame = new JFrame("Hangman");
    	JPanel ePane = new JPanel();
	
	public MenuScreenView()
	{
		//image = ImageIO.read(new File("hang.jpg"));

  	  	//JLabel picLabel = new JLabel(new ImageIcon( image ));
         
		
        	ePane.setLayout(new GridLayout(3,0));
        	JPanel wPane = new JPanel();
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	frame.setSize(400,300);
         
        	singleB = new JButton("Single Player");
        	singleB.addActionListener(this);
        	multiB = new JButton("Multi Player");
        	multiB.addActionListener(this);
        	timeB = new JButton("Time Attack");
        	timeB.addActionListener(this);
        
        	ePane.add(singleB);
        	ePane.add(multiB);
        	ePane.add(timeB);

        	//wPane.add(picLabel);
        
        	frame.add(ePane, BorderLayout.EAST);
        	frame.add(wPane, BorderLayout.WEST);
	}
	
	public void show()
	{
		frame.setVisible(true);
	}
      
      	public static void main(String args[]) throws IOException
      	{
		Menu play = new Menu();
		play.show();
     	}
      
      	public void actionPerformed(ActionEvent ae)
      	{
         	if(ae.getSource() == singleB)
         	{
        		//play single player 
        	 	System.out.println("single");
         	}
         	else if(ae.getSource() == multiB)
         	{
            		//play multi player
        	 	System.out.println("multi");
         	}
         	else if(ae.getSource() == timeB)
         	{
            		//play time attack
        	 	System.out.println("time");
         	}
      }
}
