import java.awt.Graphics2D;
import java.awt.Shape;

public interface BoardView {
	/**
	 * @param g2 Graphics2D
	 */
    void drawBoard(Graphics2D g2);
    
    void updatePits();
    
    /**
     * @param row int
     * @param col int
     * @return Shape
     */
    Shape pitShape(int row, int col);
}
