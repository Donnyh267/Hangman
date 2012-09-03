package edu.drexel.cs451.hangman;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = -8480722121237177820L;

    // background Filename
    private String backgroundFilename = null;
    // if background is tiled or not
    private boolean tile = false;

    public String getBackgroundFilename() {
        return backgroundFilename;
    }

    public void setBackgroundFilename(String backgroundFilename) {
        this.backgroundFilename = backgroundFilename;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        if (backgroundFilename != null) {
            try {
                // TODO Replace this with ResourceManager
                Image bg = ImageIO.read(this.getClass().getResource(
                        backgroundFilename));
                if (tile) {
                    int iw = bg.getWidth(this);
                    int ih = bg.getHeight(this);
                    if (iw > 0 && ih > 0) {
                        for (int x = 0; x < getWidth(); x += iw) {
                            for (int y = 0; y < getHeight(); y += ih) {
                                g.drawImage(bg, x, y, iw, ih, this);
                            }
                        }
                    }
                } else {
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                }
            } catch (IOException e) {
                System.out.println("Cannot load background: "
                        + backgroundFilename + "\n" + e);
            }
        }
    }

    public boolean isTile() {
        return tile;
    }

    public void setTile(boolean tile) {
        this.tile = tile;
    }
}
