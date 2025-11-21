
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

interface BoardView {
    void drawBoard(Graphics2D g2);
    
<<<<<<< HEAD
    ArrayList<PitPanel> getPitPanels();
    
    void updatePits(); 
    
    public void initializePits(Graphics2D g2);
=======
    Shape boardShape();
    
    void updatePits(); 
    
    Shape pitShape(int row, int col);
    
    Shape stoneShape(int x, int y);
>>>>>>> f2a796f2f13faf57dc9497aa487392ce1bf9e95d
}