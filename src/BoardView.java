
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

interface BoardView {
    void drawBoard(Graphics2D g2);
    
    ArrayList<PitPanel> getPitPanels();
    
    void updatePits(); 
    
    public void initializePits(Graphics2D g2);
}