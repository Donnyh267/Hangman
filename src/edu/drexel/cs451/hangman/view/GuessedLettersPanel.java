package edu.drexel.cs451.hangman.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.xml.internal.ws.util.StringUtils;

public class GuessedLettersPanel extends JPanel {

    private static final long serialVersionUID = -3893703834322140202L;
    private String pickedWord;
    ArrayList<JLabel> letters = new ArrayList<JLabel>();

    //constructor: draw empty spaces with length = length of pickedWord;
    public GuessedLettersPanel(String pickedWord) {
        this.pickedWord = pickedWord;
        this.setLayout(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.gridx = 0; cs.gridy = 0; cs.insets.set(5,5,5,5);
        for (int i=0; i < pickedWord.length(); i++) {
            JLabel l = new JLabel("_");
            letters.add(l);
            this.add(l, cs);
            cs.gridx++;
        }
    }
    
    //display the character at position X
    public void show(int position) {
        letters.get(position).setText(StringUtils.capitalize(pickedWord.substring(position, position+1)));
    }

    public void lose() {
        for (int i=0; i<pickedWord.length(); i++)
            show(i);
        for (JLabel l:letters) {
            l.setForeground(Color.RED);
        }
    }
    
    public void win() {
        for (JLabel l:letters) {
            l.setForeground(Color.GREEN);
        }
    }
}
