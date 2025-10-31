import javax.swing.JFrame;

class MancalaTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Square Mancala Board Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //center 
        frame.setLocationRelativeTo(null); // center window on screen

        frame.setSize(550, 550);
        frame.add(new SquareView());
        frame.add(new MainMenu()); 
        frame.setVisible(true);
        
    }
}