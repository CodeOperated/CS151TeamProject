/**
 * CS 151 Final Project Solution 
 * @author Emily Thach
 * @version 1.0 12/4/2025
 */

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import javax.swing.*;
import java.util.*;

/**
 * A wooden Mancala board view implementation.
 */
public class WoodenBoard extends JPanel implements BoardView {
    private static final int BOARD_WIDTH = 1000;
    private static final int BOARD_HEIGHT = 400;
    private static final int MANCALA_HEIGHT = 200;
    private static final int MANCALA_WIDTH = 60;
    private static final int PIT_SIZE = 60;
    private static final int ROWS = 2;
    private static final int COLS = 6;
    private static final Color brown = new Color(139, 69, 19); 
    private static final Color tan = new Color(222, 184, 135);
    private boolean built = false;
    private MancalaBoard m; 
    
    
    int pitSpacingX = 50;
    int pitSpacingY = 80;
    
    //index 0 starts at A1, increments counter clockwise including score pits
    ArrayList<PitPanel> pits = new ArrayList<>();
    
    PitPanel pitA;
    PitPanel pitB;
    
    /**
     * The view notify() method. Whenever changes happen to the model call updatePits()
     * The model is passed in through WoodenBoard constructor
     */
    public void updatePits() {
        ArrayList<MancalaPit> manData = m.getBoard();

        // If pits haven't been built yet (initializePits hasn't run), bail out safely.
        if (pits == null || pits.isEmpty()) {
            return; // drawBoard() will build pits and then call updatePits() again
        }

        // In case sizes ever diverge, only iterate over the min size.
        int limit = Math.min(manData.size(), pits.size());
        for (int i = 0; i < limit; i++) {
            PitPanel currPit = pits.get(i);
            currPit.setStones(manData.get(i).getStoneNum());
        }
    }
    
    public WoodenBoard(MancalaBoard model) {
        this.m = model;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<PitPanel> getPitPanels(){
		return pits;
    }
    
    /**
     * Draws the empty Mancala board background and pits.
     */
    public void drawBoard(Graphics2D g2) {
    	setLayout(null);
        setBounds(50, 50, BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(brown);
        setVisible(true);
        
        if (!built) {
        	 initializePits(g2);
            built = true;
            revalidate(); //recalculates flow layout for each pit
            updatePits(); //initializes pits once, whenever pit is updated, controller will call updatePits itself
        }
        
        drawLabels(g2);
        
    }
    
    
    /**
     * Refreshes the pit visuals, since the pits are the only ones changing
     */
    public void initializePits(Graphics2D g2) {
    	
    	//get man[] from model 
    	//draw the number of stones for each pit 
        g2.setColor(tan);
    	pits.clear(); 
    	
    	//calculate total width and height of grid
    	int totalPitsWidth = (COLS * PIT_SIZE) + ((COLS - 1) * pitSpacingX);
        int totalPitsHeight = (ROWS * PIT_SIZE) + ((ROWS - 1) * pitSpacingY);

        int startAtX = (int) ((BOARD_WIDTH - totalPitsWidth)/ 2.0);
        int startAtY = (int) ((BOARD_HEIGHT - totalPitsHeight)/ 2.0);
        
        //draw labels
        for (int row = 0; row < ROWS; row++) {
            int y;
            if (row == 0) {
                // bottom row 
                y = startAtY + PIT_SIZE + pitSpacingY;
                for (int col = 0; col < COLS; col++) {
                    int x = startAtX + col * (PIT_SIZE + pitSpacingX);
                    PitPanel pit = new PitPanel(tan);
                    pit.setBounds(x, y, PIT_SIZE, PIT_SIZE);
                    pits.add(pit);
                    add(pit);

                }
            } else {
                //top row labels
                y = startAtY;
                for (int col = COLS - 1; col >= 0; col--) {
                    int x = startAtX + col * (PIT_SIZE + pitSpacingX);
                    PitPanel pit = new PitPanel(tan);
                    pit.setBounds(x, y, PIT_SIZE, PIT_SIZE);
                    pits.add(pit);
                    add(pit);
                }
            }
        }

        int mancalaY = startAtY + (totalPitsHeight - MANCALA_HEIGHT) / 2;
        int mancalaBX = startAtX - (pitSpacingX + MANCALA_WIDTH);
        int mancalaAX = startAtX + totalPitsWidth + pitSpacingX;

        pitA = new PitPanel(tan);
        pitA.setBounds(mancalaAX, mancalaY, MANCALA_WIDTH, MANCALA_HEIGHT);
        add(pitA);
        
        pitB = new PitPanel(tan);
        pitB.setBounds(mancalaBX, mancalaY, MANCALA_WIDTH, MANCALA_HEIGHT);
        add(pitB);

        g2.drawString("Mancala B", mancalaBX, mancalaY - 20);
        g2.drawString("Mancala A", mancalaAX, mancalaY - 20);
        
        pits.add(COLS, pitA); 
        pits.add(pitB);      
        
        
    }
    
    /**
     * This method draws the labels for each corresponding pit
     * @param g2 -- Graphics2D
     */
    	private void drawLabels(Graphics2D g2) {
    	    int totalPitsWidth  = (COLS * PIT_SIZE) + ((COLS - 1) * pitSpacingX);
    	    int totalPitsHeight = (ROWS * PIT_SIZE) + ((ROWS - 1) * pitSpacingY);
    	    int startAtX = (BOARD_WIDTH  - totalPitsWidth)  / 2;
    	    int startAtY = (BOARD_HEIGHT - totalPitsHeight) / 2;

    	    //set font color
    	    g2.setColor(tan); 

    	    // bottom row labels
    	    int yA = startAtY + PIT_SIZE + pitSpacingY;
    	    for (int col = 0; col < COLS; col++) {
    	        int x = startAtX + col * (PIT_SIZE + pitSpacingX);
    	        String label = "A" + (col + 1);
    	        FontMetrics fm = g2.getFontMetrics();
    	        int textX = x + (PIT_SIZE - fm.stringWidth(label)) / 2;
    	        int textY = yA + PIT_SIZE + 20;
    	        g2.drawString(label, textX, textY);
    	    }

    	    // top row labels
    	    int yB = startAtY;
    	    for (int col = COLS - 1; col >= 0; col--) {
    	        int x = startAtX + col * (PIT_SIZE + pitSpacingX);
    	        String label = "B" + (COLS  - col);
    	        FontMetrics fm = g2.getFontMetrics();
    	        int textX = x + (PIT_SIZE - fm.stringWidth(label)) / 2;
    	        int textY = yB - 10;
    	        g2.drawString(label, textX, textY);
    	    }

    	    // mancala labels
    	    int mancalaY = startAtY + (totalPitsHeight - MANCALA_HEIGHT) / 2;
    	    int mancalaBX = startAtX - (pitSpacingX + MANCALA_WIDTH);
    	    int mancalaAX = startAtX + totalPitsWidth + pitSpacingX;

    	    g2.drawString("Mancala B", mancalaBX, mancalaY - 20);
    	    g2.drawString("Mancala A", mancalaAX, mancalaY - 20);
    	}

    /**
     * Paints the Mancala board on the panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawBoard(g2);
    }
    
    @Override
    /**
     * @param row - row of pit
     * @param col - column if pit
     * @return Shape - the shape of pit
     */
    public Shape pitShape(int row, int col) {
        if (row != 0 && row != 1) return null;
        if (col < 0 || col >= 6) return null;

        int idx;
        if (row == 1) { 
            idx = col;
        } else {      
            idx = 7 + col;
        }

        if (idx < 0 || idx >= pits.size()) return null;
        Rectangle b = pits.get(idx).getBounds();
        return new Rectangle2D.Float(b.x, b.y, b.width, b.height);
    }
}
