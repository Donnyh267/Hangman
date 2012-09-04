package edu.drexel.cs451.hangman.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AllLettersPanel extends JPanel {

    public static String NAME = "LETTER";
    public static Color BLURRED = new Color(180, 180, 180);

    private static final long serialVersionUID = 357906985073545789L;
    private ArrayList<JLabel> letters = new ArrayList<JLabel>();
    private MouseListener listener;

    public AllLettersPanel(MouseListener listener) {
        this.listener = listener;
        this.setLayout(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.gridx = 0;
        cs.gridy = 0;
        cs.insets.set(5, 5, 5, 5);
        cs.fill = GridBagConstraints.BOTH;
        for (Character c = 'A'; c <= 'Z'; c++) {
            JLabel l = new JLabel(c.toString());
            l.addMouseListener(this.listener);
            l.setName(NAME);
            letters.add(l);
            cs.gridx = (c - 'A') % 13;
            cs.gridy = (c - 'A') / 13;
            this.add(l, cs);
        }
    }

    public void disableLetter(Character c) {
        letters.get(c - 'A').setForeground(BLURRED);
    }

    public void disableAll() {
        for (JLabel l : letters) {
            l.setForeground(BLURRED);
            l.removeMouseListener(this.listener);
        }
    }
}
