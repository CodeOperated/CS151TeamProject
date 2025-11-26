import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

class PitPanel extends JPanel {
    private Color c;
    private int stoneCount;
    private static int stoneSize = 10;
    private static int PIT_SIZE = 60;
    private static int MANCALA_HEIGHT = 200;
  
    public PitPanel(Color c) {
    	this.c = c;
    	setBackground(c);
    	setLayout(new FlowLayout(FlowLayout.CENTER, 4, 4));
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(c);
       
    }
    
    public void setStones(int stoneCount) {
        this.stoneCount = stoneCount;

        // Clear old stones so we don't keep adding more every update
        removeAll();

        for (int i = 0; i < this.stoneCount; i++) {
            add(new StoneIcon());
        }

        revalidate();
        repaint();
    }
}
