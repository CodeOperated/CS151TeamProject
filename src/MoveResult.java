/**
 * Represents the outcome of a single Mancala move.
 * Returned by the model after a pit is played.
 */
public class MoveResult {
    private final int lastPitIndex;
    private final boolean extraTurn;
    private final boolean captureOccurred;
    private final boolean gameOver;
    private final int[] boardState;  
    
    /**
     * Constructor
     * @param lastPitIndex - the last pit that stone land on
     * @param extraTurn - extra turn boolean
     * @param captureOccurred - capture occurred boolean
     * @param gameOver - game over boolean
     * @param boardState - snapshot of board
     */
    public MoveResult(int lastPitIndex, boolean extraTurn,
                      boolean captureOccurred, boolean gameOver, int[] boardState) {
        this.lastPitIndex = lastPitIndex;
        this.extraTurn = extraTurn;
        this.captureOccurred = captureOccurred;
        this.gameOver = gameOver;
        this.boardState = boardState.clone(); 
    }
    
    /**
     * 
     * @return lastPitIndex - the last pit stone land on
     */
    public int getLastPitIndex() {
        return lastPitIndex;
    }
    
    /**
     * 
     * @return extraTurn - true if extra turn available, false otherwise
     */
    public boolean isExtraTurn() {
        return extraTurn;
    }
    
    /**
     * 
     * @return isCaptureOccurred - true if capture occurred, false otherwise
     */
    public boolean isCaptureOccurred() {
        return captureOccurred;
    }
    
    /**
     * 
     * @return gameOver - true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /** 
     * @returns a copy of the 14-length array of pit stone counts. 
     */
    public int[] getBoardState() {
        return boardState.clone();
    }
}
