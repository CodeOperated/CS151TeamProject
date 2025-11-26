public interface BoardStrategy {
    String getName();
    BoardView create(MancalaBoard model);
}
