public class WoodenBoardStrategy implements BoardStrategy {
    @Override public String getName() { return "Wooden Mancala Board"; }
    @Override public BoardView create(MancalaBoard model) { return new WoodenBoard(model); }
}
