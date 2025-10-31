import java.awt.*;
import javax.swing.*;

class MainMenu extends JComponent{
    String[] options = {"Start Game", "Exit"};
	JList<String> list = new JList<>(options);
    JScrollPane scrollPane = new JScrollPane(list);
    int width = 120; 
    int height = 80;
    String title = "Mancala!";
    JLabel label = new JLabel(title); 
    
    public MainMenu() {
    	//label design
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setForeground(Color.BLUE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

    	
    	setLayout(new BorderLayout()); 
    	
    	add(label, BorderLayout.NORTH);
    	add(scrollPane, BorderLayout.CENTER);
//    	scrollPane.setBounds(50, 50, 120, 80);
    		
    	
    }
    
    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
//        g2.drawString(title, x, y);
        
    }
    
	
	
	
}