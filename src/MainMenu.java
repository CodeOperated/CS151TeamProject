import java.awt.*;
import javax.swing.*;

class MainMenu extends JPanel{
    String[] options = {"Wooden Mancala Board", "Metal Mancala Board"};
	JList<String> list = new JList<>(options);
    JScrollPane scrollPane = new JScrollPane(list);
    int width = 120; 
    int height = 80;
    
    String title = "Let's Play Mancala! Choose a Board Design:";
    JLabel label = new JLabel(title); 
    MancalaTest tester = new MancalaTest();

    
    public MainMenu() {
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setForeground(Color.BLUE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        list.setFont(new Font("Arial", Font.PLAIN, 20)); 
        
    	setLayout(new BorderLayout()); 
    	add(label, BorderLayout.NORTH);
    	add(scrollPane, BorderLayout.CENTER);
    	
    	list.addListSelectionListener(e -> {
    		
    		//prevents double clicks
            if (!e.getValueIsAdjusting()) {  
            	 String selected = list.getSelectedValue();
                 tester.selectBoard(selected); 
            }
        });
    }
    
    /**
     * 
     * @return - Returns the list of options. Controller is to add an action listener to each option and 
     * invoke the corresponding board design
     */
    public JList<String> getMenu() {
		return list;
    }
    
    public void paintComponent(Graphics g) {
    	
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //update if needed
    }
    
	
	
	
}