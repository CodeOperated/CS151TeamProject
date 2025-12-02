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
