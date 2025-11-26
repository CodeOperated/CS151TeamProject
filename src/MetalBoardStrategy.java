public class MetalBoardStrategy implements BoardStrategy {
    @Override public String getName() { return "Metal Mancala Board"; }
    @Override public BoardView create(MancalaBoard model) { return new MetalBoard(model); }
}
