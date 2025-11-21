public class MancalaGame {

    MancalaBoard playBoard;
    String playerTurn;
    Strategy design;


    /**
     * Requests a mancala board design from user input
     * @param design //replace with real type name
     * @return Strategy
     */
    //TODO: Complete whatState with actual design strategy parameter passing in and replace switch cases
    Strategy whatState(design ) {

            switch (input) {
                case "S":
                    this.Strategy = SteelView();//Replace with actual strategy name
                    break;
                case "C":
                    this.Strategy = WoodView();//Replace with actual strategy name
                    break;
                    case "E":
                        System.exit(0);
            }


        }
    }


    public static void main(String[] args) {




    }
}