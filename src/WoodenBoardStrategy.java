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
