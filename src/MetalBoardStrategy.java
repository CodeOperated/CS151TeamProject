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
