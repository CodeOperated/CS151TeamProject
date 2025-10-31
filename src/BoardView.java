
import java.awt.Graphics2D;
import java.awt.Shape;

interface BoardView {
    void drawEmptyBoard(Graphics2D g2);
    
    void refreshBoard(Graphics2D g2);
    
    Shape boardShape();
    
    Shape pitShape(int row, int col);
    
    Shape stoneShape(int x, int y);
}