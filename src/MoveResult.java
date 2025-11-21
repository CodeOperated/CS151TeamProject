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

    public MoveResult(int lastPitIndex, boolean extraTurn,
                      boolean captureOccurred, boolean gameOver, int[] boardState) {
        this.lastPitIndex = lastPitIndex;
        this.extraTurn = extraTurn;
        this.captureOccurred = captureOccurred;
        this.gameOver = gameOver;
        this.boardState = boardState.clone(); 
    }

    public int getLastPitIndex() {
        return lastPitIndex;
    }

    public boolean isExtraTurn() {
        return extraTurn;
    }

    public boolean isCaptureOccurred() {
        return captureOccurred;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    /** Returns a copy of the 14-length array of pit stone counts. */
    public int[] getBoardState() {
        return boardState.clone();
    }
}
