import java.awt.Graphics2D;
import java.awt.Shape;

public interface BoardView {
    void drawBoard(Graphics2D g2);

    void updatePits();

    Shape pitShape(int row, int col);
}
