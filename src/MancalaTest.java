import javax.swing.JFrame;

class MancalaTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Square Mancala Board Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //center 
        frame.setLocationByPlatform(true); // center window on screen

        frame.setSize(1100, 550);
        
       //frame.add(new MetalBoard()); 
        frame.add(new WoodenBoard());
        //frame.add(new MainMenu()); 
        
        
        frame.setVisible(true);
        
    }
}