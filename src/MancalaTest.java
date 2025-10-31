// --- Main Test Frame ---

import javax.swing.JFrame;

class MancalaTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Square Mancala Board Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 550);
        frame.add(new SquareView());
        frame.setVisible(true);
        
    }
}