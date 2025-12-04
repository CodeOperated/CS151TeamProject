/**
 * CS 151 Final Project Solution 
 * @author Hien Ly
 * @version 1.0 12/4/2025
 */

/**
 * Listener interface for board selection events.
 */
public interface BoardSelectionListener {
	/**
	 * @param strategy board strategy
	 */
    void onBoardChosen(BoardStrategy strategy);
}