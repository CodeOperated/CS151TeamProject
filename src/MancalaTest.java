/**
 * CS 151 HW4 Solution 
 * @author Emily Thach, Hien Ly
 * @version 1.0 11/11/2025
 */
import javax.swing.*;
import java.awt.*;

/**
 * Main class to run the Mancala game.
 */
public class MancalaTest {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Mancala");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 520);
            frame.setLocationRelativeTo(null);

            // Show the main menu first
            showMenu(frame);

            frame.setVisible(true);
        });
    }

    /** Show the main menu and wire up the board-selection listener. 
     * 
     * @param frame - Jframe
     */
    private static void showMenu(JFrame frame) {
        MainMenu menu = new MainMenu();
        menu.setBoardSelectionListener(strategy -> startGame(frame, strategy));

        frame.getContentPane().removeAll();
        frame.setContentPane(menu);
        frame.revalidate();
        frame.repaint();
    }

    /** Start a new game with the chosen board strategy. 
     * 
     * @param frame - JFrame
     * @param strategy - the board strategy (metal or wooden)
     */
    private static void startGame(JFrame frame, BoardStrategy strategy) {

        MancalaBoard emptyModel = new MancalaBoard(0);
        BoardView emptyView = strategy.create(emptyModel);
        JLabel status = new JLabel("Player A's turn");

        JPanel south = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton undoBtn = new JButton("Undo");
        undoBtn.setEnabled(false); // disabled until game starts

        JButton returnBtn = new JButton("Return to Menu");

        south.add(undoBtn);
        south.add(returnBtn);
        south.add(status);

        // Show empty board + buttons
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add((Component) emptyView, BorderLayout.CENTER);
        frame.add(south, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();

        returnBtn.addActionListener(e -> showMenu(frame));

        int stones = askInitialStones(frame); 

        MancalaBoard model = new MancalaBoard(stones);
        BoardView view = strategy.create(model);

        MancalaController controller =
                new MancalaController(model, view, (JComponent) view, status, undoBtn, returnBtn);

        // Enable undo now
        undoBtn.addActionListener(e -> controller.undo());
        undoBtn.setEnabled(true);

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add((Component) view, BorderLayout.CENTER);
        frame.add(south, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    /** Prompt user for stones per pit (3 or 4), default to 4 if Cancel. 
     * @param parent Component
     */
    private static int askInitialStones(Component parent) {
        while (true) {
            String input = JOptionPane.showInputDialog(
                parent,
                "Enter stones per pit (3 or 4):",
                "Initial Setup",
                JOptionPane.QUESTION_MESSAGE
            );

            if (input == null) return 4;
            input = input.trim();

            if ("3".equals(input) || "4".equals(input))
                return Integer.parseInt(input);

            JOptionPane.showMessageDialog(
                    parent,
                    "Please enter 3 or 4.",
                    "Invalid input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
