import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.util.*;

/**
 * A square Mancala board view implementation.
 */
public class SquareView extends JPanel implements BoardView {
    private static final int BOARD_WIDTH = 1000;
    private static final int BOARD_HEIGHT = 400;
    private static final int MANCALA_HEIGHT = 200;
    private static final int MANCALA_WIDTH = 60;
    private static final int PIT_SIZE = 60;
    private static final int STONE_SIZE = 10;
    private static final int ROWS = 2;
    private static final int COLS = 6;

    Rectangle2D.Double pitA;
    Rectangle2D.Double pitB;
    
    /**
     * Draws the empty Mancala board background and pits.
     */
    public void drawEmptyBoard(Graphics2D g2) {
        Rectangle2D.Double body = new Rectangle2D.Double(50, 50, BOARD_WIDTH, BOARD_HEIGHT);
        g2.draw(body);

        int pitSpacingX = 50;
        int pitSpacingY = 80;

        //calculate total width and height of grid
        int totalPitsWidth = (COLS * PIT_SIZE) + ((COLS - 1) * pitSpacingX);
        int totalPitsHeight = (ROWS * PIT_SIZE) + ((ROWS - 1) * pitSpacingY);

        int startAtX = (int) (50 + (BOARD_WIDTH - totalPitsWidth)/ 2.0);
        int startAtY = (int) (50 + (BOARD_HEIGHT - totalPitsHeight)/ 2.0);
        
        //draw pits
        for (int row = 0; row < ROWS; row++) {
        	int x = 0;
        	int y = 0; 
        	int col; 
        	
            for (col = 0; col < COLS; col++) {
                x = startAtX + col * (PIT_SIZE + pitSpacingX);
                y = startAtY + row * (PIT_SIZE + pitSpacingY);
               
                Rectangle2D.Double pit = new Rectangle2D.Double(x, y, PIT_SIZE, PIT_SIZE);
                g2.draw(pit);
                String label; 
                if (row == 0) {
                	label = "B" + (6 - col);
                	
                	//fm centers text
                	FontMetrics fm = g2.getFontMetrics();
                    int textWidth = fm.stringWidth(label);
                    
                    int textX = x + (PIT_SIZE - textWidth) / 2;
                    int textY = y - 10;

                    g2.drawString(label, textX, textY);
                } else {
                	label = "A" + (col + 1);
                	
                	//fm centers text
                	FontMetrics fm = g2.getFontMetrics();
                    int textWidth = fm.stringWidth(label);

                    int textX = x + (PIT_SIZE - textWidth) / 2;
                    int textY = y + PIT_SIZE + 20 ;
                    g2.drawString(label, textX, textY);
                    
                    
                }
                

            }
            
            pitA = new Rectangle2D.Double(startAtX + col * (PIT_SIZE + pitSpacingX), startAtY, MANCALA_WIDTH, MANCALA_HEIGHT);
          g2.drawString("Mancala A", startAtX + col * (PIT_SIZE + pitSpacingX), startAtY - 20); 

        }
        
        
        //draw Mancala pits
        pitB = new Rectangle2D.Double(startAtX - (PIT_SIZE + pitSpacingX), startAtY, MANCALA_WIDTH, MANCALA_HEIGHT);
        g2.drawString("Mancala B", startAtX - (PIT_SIZE + pitSpacingX), startAtY - 20); 

        g2.draw(pitA);
        g2.draw(pitB);
        
        
    }

    /**
     * Refreshes the board visuals — currently empty.
     */
    public void refreshBoard(Graphics2D g2) {
        // TODO: Implement stone drawing and board state updates here
    	
    }

    /**
     * Returns the overall board shape.
     */
    public Shape boardShape() {
        return new Rectangle2D.Double(50, 50, BOARD_WIDTH, BOARD_HEIGHT);
    }

    /**
     * Returns the rectangular shape of a specific pit.
     */
    public Shape pitShape(int row, int col) {
        int x = 100 + col * (PIT_SIZE + 10);
        int y = 100 + row * (PIT_SIZE + 10);
        return new Rectangle2D.Double(x, y, PIT_SIZE, PIT_SIZE);
    }

    /**
     * Returns the shape of a stone positioned at (x, y).
     */
    public Shape stoneShape(int x, int y) {
        return new Rectangle2D.Double(x, y, STONE_SIZE, STONE_SIZE);
    }

    /**
     * Paints the Mancala board on the panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawEmptyBoard(g2);
        
    }
}
