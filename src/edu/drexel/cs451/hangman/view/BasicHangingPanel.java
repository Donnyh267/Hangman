package edu.drexel.cs451.hangman.view;

import java.awt.Dimension;
import java.awt.Graphics;

public class BasicHangingPanel extends HangingPanel {

    private static final long serialVersionUID = -8606898172794208520L;

    public BasicHangingPanel() {
        super();
        this.setPreferredSize(new Dimension(300, 300));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        // draw base
        g.drawLine(0, 280, 280, 280);
        // pole
        g.drawLine(100, 10, 100, 280);
        // bar
        g.drawLine(100, 10, 230, 10);
        // rope
        g.drawLine(230, 10, 230, 40);
        for (int i = 1; i < getCurrentState() + 1; i++)
            switch (i) {
            case 1:
                // draw a head
                g.drawOval(215, 40, 30, 30);
                break;
            case 2:
                // draw a body
                g.drawLine(230, 70, 230, 160);
                break;
            case 3:
                // draw right leg
                g.drawLine(230, 160, 260, 220);
                break;
            case 4:
                // draw left leg
                g.drawLine(230, 160, 200, 220);
                break;
            case 5:
                // draw right hand
                g.drawLine(230, 70, 270, 90);
                break;
            case 6:
                // draw left hand
                g.drawLine(230, 70, 190, 90);
                break;
            case 7:
                // draw a YOU LOSE thing
                break;
            }

    };

    @Override
    public void next() {
        System.out.println("next");
        currentState++;
        this.repaint();
    }

}
