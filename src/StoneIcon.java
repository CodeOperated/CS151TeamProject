/**
 * CS 151 Final Project Solution 
 * @author Emily Thach
 * @version 1.0 12/4/2025
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JLabel;

/**
 * A icon representation of a stone in the Mancala game.
 */
class StoneIcon extends JLabel{
	private static int STONE_SIZE = 10; 
	
	public StoneIcon() {
        setPreferredSize(new Dimension(STONE_SIZE, STONE_SIZE)); // <--- add this

	}
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        Ellipse2D.Double ellipse = new Ellipse2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
        g2.setColor(Color.DARK_GRAY);
        g2.fill(ellipse);
        g2.draw(ellipse);
        
    }
}