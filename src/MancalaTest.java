import javax.swing.JFrame;

class MancalaTest {
	private JFrame frame; 
	private MancalaBoard m; 
	
    public static void main(String[] args) {
        JFrame frame = new JFrame("Square Mancala Board Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //center 
        frame.setLocationByPlatform(true); // center window on screen
        frame.setSize(1100, 550);
        
        //main menu
        frame.add(new MainMenu()); 
        frame.setVisible(true);
        
    }
    
    public void selectBoard(String s) {
    	m = new MancalaBoard();
    	
    	//user selects wood
    	if (s.equals("Wooden Mancala Board")) {
    		frame.add(new WoodenBoard(m));
    	} else {
    	//user selects metal 
    		frame.add(new MetalBoard(m));
    	}
    	
    }
    
}