import java.awt.*;
import java.awt.geom.Rectangle2D;
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
    private static final int STONE_SIZE = 10;
    private static final int ROWS = 2;
    private static final int COLS = 6;
    private static final Color brown = new Color(139, 69, 19); 
    private static final Color tan = new Color(222, 184, 135);
    private boolean built = false;
    
    int pitSpacingX = 50;
    int pitSpacingY = 80;
    
    //index 0 starts at A1, increments counter clockwise including score pits
    ArrayList<PitPanel> pits = new ArrayList<>();
    
    PitPanel pitA;
    PitPanel pitB;
    
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
    	        String label = "B" + (col + 1);
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
     * The pits method that will be updated
     */
    public void updatePits() {
    	//get man arraylist as an argument
    	
    	//tester man[]
    	ArrayList<Integer> manTest = new ArrayList<>(Arrays.asList(2, 2, 4, 1, 0, 6, 0, 1, 2, 3, 4, 4, 3, 2));
    	
    	for (int i = 0; i < manTest.size(); i++) {
    		PitPanel currPit = pits.get(i);
    		currPit.setStones(manTest.get(i));
    	}
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
}
