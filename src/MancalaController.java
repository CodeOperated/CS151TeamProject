
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayDeque;
import java.util.Deque;

import javax.swing.JComponent;
import javax.swing.JLabel;

import javax.swing.JButton;


public class MancalaController {
	//enum for player turn
    public enum Player { A, B }

    //fields
    private boolean undo = true;
    private final MancalaBoard model;
    private final BoardView view;
    private final JComponent viewComponent;
    private final JLabel turnLabel;
    private final JButton returnButton;
    private final JButton undoButton;

    private Player currentPlayer = Player.A;
    private boolean gameOver = false;
    
    //keep a single snapshot for a turn
    private static class Snapshot {
        final int[] stones;
        final Player player;
        Snapshot(int[] stones, Player player) {
            this.stones = stones;
            this.player = player;
        }
    }
    private final Deque<Snapshot> history = new ArrayDeque<>();

    // per-player undo tracking
    private int undoCountA = 0;
    private int undoCountB = 0;
    
    /**
     * get the undo number this turn
     * @param p - the player
     * @return count int - the number of undo performed this turn.
     */
    private int getUndoCount(Player p) {
        return (p == Player.A) ? undoCountA : undoCountB;
    }
    
    /**
     * increase undo count
     * @param p - the player to increase undo count
     */
    private void incrementUndo(Player p) {
        if (p == Player.A) {
        	undoCountA++;
        }
        else undoCountB++;
    }
    
    /**
     * reset undo count
     * @param p - the player to reset undo count
     */
    private void resetUndo(Player p) {
        if (p == Player.A) {
        	undoCountA = 0;
        }
        else undoCountB = 0;
    }
    
    /**
     * Constructor for controller
     * @param model - the mancala board
     * @param view - the chosen view
     * @param viewComponent - the component of view
     * @param turnLabel - lable to show player turn
     * @param undoButton - undo button
     * @param returnButton - return button after game over
     */
    public MancalaController(MancalaBoard model,
                             BoardView view,
                             JComponent viewComponent,
                             JLabel turnLabel,
                             JButton undoButton,
                             JButton returnButton) {
        this.model = model;
        this.view = view;
        this.viewComponent = viewComponent;
        this.turnLabel = turnLabel;
        this.undoButton = undoButton;
        this.returnButton = returnButton;

        if (this.returnButton != null) {
            this.returnButton.setVisible(false);
        }

        this.model.addView(this.view);
        pushHistory();      
        hookMouse();
        updateTurnLabel();
    }
    
    /**
     * added mouse listener
     */
    private void hookMouse() {
        viewComponent.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int pit = hitTestPit(e.getPoint());
                if (pit >= 0) {
                	handlePitClick(pit);
                }
            }
        });
    }
    
    /**
     * check for the pit that is clicked
     * @param p - the point of the pit clicked
     * @return int of the pit that is clicked
     */
    private int hitTestPit(Point p) {
        // Bottom A row: 0..5
        for (int col = 0; col < 6; col++) {
            Shape s = view.pitShape(1, col);
            if (s != null && s.contains(p)) {
            	return col;
            }
        }
        // Top B row: 7..12 (left->right visually)
        for (int col = 0; col < 6; col++) {
            Shape s = view.pitShape(0, col);
            if (s != null && s.contains(p)) {
            	return col + 7;
            }
        }
        return -1;
    }
    
    /**
     * play A and B pit accordingly and check for extra turn.
     * @param pitIndex - the index of the pit to play
     */
    private void handlePitClick(int pitIndex) {
        // no moves after game over
        if (gameOver) {
        	return;
        }

        if (!isPlayableForCurrentPlayer(pitIndex)) {
        	return;
        }
        if (model.getMancalaPitArray()[pitIndex].getStoneNum() == 0) {
        	return;
        }

        Player mover = currentPlayer;

        int lastIndex = (currentPlayer == Player.A)
                ? model.PlayPitA(model.getMancalaPitArray()[pitIndex])
                : model.PlayPitB(model.getMancalaPitArray()[pitIndex]);
        // model.PlayPitX already updated pits and notified views

        // ---- game-over check (one side empty) ----
        if (isSideEmpty(0, 5) || isSideEmpty(7, 12)) {
            sweepRemaining();   // this will also notify views
            gameOver = true;

            // new move -> reset this player's undo counter
            resetUndo(mover);

            // store final state in history
            pushHistory();
            updateTurnLabel();   // will show winner message
            return;
        }

        // ---- normal extra-turn logic (only if not game over) ----
        boolean extraTurn;
        if((currentPlayer == Player.A && lastIndex == 6) || (currentPlayer == Player.B && lastIndex == 13)) {
        	extraTurn = true;
        } else {
        	extraTurn = false;
        }

        resetUndo(mover);    // new move: undo counter reset for this player

        if (!extraTurn) {
            switchTurn();
        }
        undo = true;
        // snapshot AFTER the move (so each undo undoes exactly 1 move)
        pushHistory();
        updateTurnLabel();
    }
    
    /**
     * check if the pit clicked is playable for current player
     * @param pitIndex - the pit index to play
     * @return boolean - true if player A clicked 0-5 or player B clicked 7-12; false otherwise
     */
    private boolean isPlayableForCurrentPlayer(int pitIndex) {
        if (pitIndex == 6 || pitIndex == 13) return false; // stores
        return (currentPlayer == Player.A)
                ? (pitIndex >= 0 && pitIndex <= 5)
                : (pitIndex >= 7 && pitIndex <= 12);
    }
    
    /**
     * switch turn after player makes move
     */
    private void switchTurn() {
        currentPlayer = (currentPlayer == Player.A) ? Player.B : Player.A;
    }
    
    
    /**
     * save the resulting board state after each move so that the next undo will return to this state.
     */
    private void pushHistory() {
        history.push(new Snapshot(model.snapshot(), currentPlayer));
    }
    
    
    /**
     * undo the last move by popping the latest snapshot and load the most recent one
     */
    public void undo() {
        if (undo == true) {
            undo = false;
            Player requester = currentPlayer;

            // no more than 3 undos per player
            if (getUndoCount(requester) >= 3) {
            	return;
            }

            if (history.size() <= 1) {
            	return; // nothing to undo
            }

            // current state is top of stack; previous is one move ago
            history.pop();
            Snapshot prev = history.peek();
            if (prev == null) {
            	return;
            }

            model.restore(prev.stones);   // model.restore will notify views
            currentPlayer = prev.player;

            // recompute gameOver from restored board
            gameOver = isSideEmpty(0, 5) || isSideEmpty(7, 12);
            incrementUndo(requester);

            viewComponent.repaint();
            updateTurnLabel();
        } else {
            return;
        }
    }
    
    /**
     * get the current player
     * @return currentPlayer - the current player
     */
    public Player getCurrentPlayer() { 
    	return currentPlayer; 
    }
    
    /**
     * refresh view and repaint
     */
    public void refresh() {
        // views are updated by model; just repaint component
        viewComponent.repaint();
    }

    // ---------- game-over helpers ----------
    
    /**
     * check if pits within lo and hi is empty
     * @param lo - lower bound of index
     * @param hi - upper bound of index
     * @return boolean - true if a side is empty; false otherwise
     */
    private boolean isSideEmpty(int lo, int hi) {
        MancalaPit[] pits = model.getMancalaPitArray();
        for (int i = lo; i <= hi; i++) {
            if (pits[i].getStoneNum() > 0) {
            	return false;
            }
        }
        return true;
    }

    /** Sweep remaining stones from non-empty side to its store (once, at game end). */
    private void sweepRemaining() {
        MancalaPit[] pits = model.getMancalaPitArray();
        boolean aEmpty = isSideEmpty(0, 5);
        boolean bEmpty = isSideEmpty(7, 12);

        if (aEmpty && !bEmpty) {
            int sum = 0;
            for (int i = 7; i <= 12; i++) {
                sum += pits[i].getStoneNum();
                pits[i].setStoneNum(0);
            }
            pits[13].setStoneNum(pits[13].getStoneNum() + sum); // B store
        } else if (bEmpty && !aEmpty) {
            int sum = 0;
            for (int i = 0; i <= 5; i++) {
                sum += pits[i].getStoneNum();
                pits[i].setStoneNum(0);
            }
            pits[6].setStoneNum(pits[6].getStoneNum() + sum); // A store
        }
        // notify views after sweeping
        model.restore(model.snapshot()); // quick trick to sync + notify
    }

    // ---------- label text ----------
    
    private void updateTurnLabel() {
        if (turnLabel == null) return;

        if (gameOver) {
            undo = false;
            MancalaPit[] pits = model.getMancalaPitArray();
            int scoreA = pits[6].getStoneNum();
            int scoreB = pits[13].getStoneNum();

            String msg;
            if (scoreA > scoreB) {
                msg = "Game over: Player A wins (" + scoreA + " - " + scoreB + ")";
            } else if (scoreB > scoreA) {
                msg = "Game over: Player B wins (" + scoreB + " - " + scoreA + ")";
            } else {
                msg = "Game over: Tie (" + scoreA + " - " + scoreB + ")";
            }
            turnLabel.setText(msg);
            if (undoButton != null) {
                undoButton.setEnabled(false);
            }
            if (returnButton != null) {
                returnButton.setVisible(true);
            }
        } else {
            String text = (currentPlayer == Player.A)
                    ? "Player A's turn"
                    : "Player B's turn";
            turnLabel.setText(text);
            if (returnButton != null) {
                returnButton.setVisible(false);
            }
            if (undoButton != null) {
                undoButton.setEnabled(true);
            }
        }
    }
}
