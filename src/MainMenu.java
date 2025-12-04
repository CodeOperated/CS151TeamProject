/**
 * CS 151 Final Project Solution 
 * @author Hien Ly, Emily Thach
 * @version 1.0 12/4/2025
 */

import java.awt.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * Main menu panel for selecting board design.
 */
public class MainMenu extends JPanel {
	private final String[] options = {"Wooden Mancala Board", "Metal Mancala Board"};
    private final JList<String> list = new JList<>(options);
    private final JScrollPane scrollPane = new JScrollPane(list);;
    private final String title = "Let's Play Mancala! Choose a Board Design:";
    private JLabel label = new JLabel(title);
    private BoardSelectionListener listener;
    
    // The available view strategies
    private final List<BoardStrategy> strategies = Arrays.asList(
            new WoodenBoardStrategy(),
            new MetalBoardStrategy()
    );
    
    /**
     * set listener for board selection
     * @param listener BoardSelectionListener
     */
    public void setBoardSelectionListener(BoardSelectionListener listener) {
        this.listener = listener;
    }
    /**
     * Main menu layout
     */
    public MainMenu() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setForeground(Color.BLUE);
        label.setHorizontalAlignment(SwingConstants.CENTER);        
        list.setFont(new Font("Arial", Font.PLAIN, 20));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(label, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int idx = list.getSelectedIndex();
                if (idx >= 0 && idx < strategies.size()) {
                    if (listener != null) {
                        listener.onBoardChosen(strategies.get(idx));
                    }
                }
            }
        });
    }
}
