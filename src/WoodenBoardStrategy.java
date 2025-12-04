/**
 * CS 151 Final Project Solution 
 * @author Hien Ly
 * @version 1.0 12/4/2025
 */

/**
 * Wooden board strategy implementation, to plug in the Wooden board view.
 */
public class WoodenBoardStrategy implements BoardStrategy {
    @Override 
    /**
     * @return String - wooden board strategy name
     */
    public String getName() { 
    	return "Wooden Mancala Board"; 
    }
    @Override 
    /**
     * @param model - MancalaBoard the board
     * @return BoardView - board view for wooden board
     */
    public BoardView create(MancalaBoard model) { 
    	return new WoodenBoard(model); 
    }
}
