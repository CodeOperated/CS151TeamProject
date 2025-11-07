public class MancalaGame {

    MancalaBoard playBoard;
    String playerTurn;
    Strategy design;


    /**
     * Requests a mancala board design from user input
     * @return Strategy
     */
    Strategy whatState() {

        String input = "";


        while (input != "E" && input != "e") {

            System.out.println("Please enter a mancala board style:");
            System.out.println("[S]quare");
            System.out.println("[C]ircle");
            System.out.println("[E]rmm... Actually, I don't want to play");

            Scanner sc = new Scanner(System.in);


            switch (input) {
                case "S":
                    this.Strategy = SquareView();//what is strategy
                    break;
                case "C":
                    this.Strategy = CircleView();
                    break;
                    case "E":
                        System.exit(0);
            }


        }
    }


    public static void main(String[] args) {

        Strategy design = whatState();



    }
}