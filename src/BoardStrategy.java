/**
 * CS 151 Final Project Solution 
 * @author Hien Ly
 * @version 1.0 12/4/2025
 */

/**
 * Board selection strategy interface.
 */
public interface BoardStrategy {
	/**
	 * @return String
	 */
    String getName();
    
    /**
     * @param model MancalaBoard
     * @return boardView
     */
    BoardView create(MancalaBoard model);
}
