/**
 * CS 151 Final Project Solution 
 * @author Hien Ly
 * @version 1.0 12/4/2025
 */

/**
 * Metal board strategy implementation, to plug in the Metal board view.
 */
public class MetalBoardStrategy implements BoardStrategy {
    @Override 
    /**
     * @return string name for metal board
     */
    public String getName() { 
    	return "Metal Mancala Board"; 
    }
    @Override 
    /**
     * @return BoardView - view for metal board
     */
    public BoardView create(MancalaBoard model) { 
    	return new MetalBoard(model); 
    }
}
