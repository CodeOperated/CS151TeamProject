/**
 * CS 151 Final Project Solution 
 * @author Hien Ly, Jose Quevado
 * @version 1.0 12/4/2025
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Virtual representation of a Mancala board with actions included, no rules are provided for turn taking
 */
public class MancalaBoard {

    MancalaPit[] man = new MancalaPit[14];
    int playerAScore;
    int playerBScore;

    private final List<BoardView> views = new ArrayList<>();
    
    
    public void addView(BoardView v) {
        if (v != null && !views.contains(v)) {
            views.add(v);
        }
    }

    public void removeView(BoardView v) {
        views.remove(v);
    }

    private void notifyViews() {
        for (BoardView v : views) {
            v.updatePits();
        }
    }


    /**
     * Constructs Mancala Board with default stone settings - leftmost playable pit is MancalaPit[0]
     */
    public MancalaBoard() {
        this(4);
    }

    /**
     * Constructs Mancala board with a custom set
     * @param StartingStones
     */
    public MancalaBoard(int StartingStones) {

        // ---- Player A side ----
        for (int i = 0; i <= 5; i++) {
            man[i] = new PlayablePit(true, StartingStones);
        }

        // A store
        man[6] = new ScorePit(true);

        // ---- Player B side ----
        for (int i = 7; i <= 12; i++) {
            man[i] = new PlayablePit(false, StartingStones);
        }

        // B store
        man[13] = new ScorePit(false);

        syncScores();
    }

    /**
     * Helper Function - Finds the index of the provided MancalaPit in man
     * @param chosenPit
     * @return index
     */
    int findmancalaPit(MancalaPit chosenPit) {
        for (int i = 0; i < man.length; i++) {
            if (man[i] == chosenPit) return i;
        }
        return -1;
    }

    /** Snapshot of the board counts (14 ints). */
    public int[] snapshot() {
        int[] s = new int[14];
        for (int i = 0; i < 14; i++) s[i] = man[i].getStoneNum();
        return s;
    }

    /** Restore board from a 14-int snapshot. */
    public void restore(int[] snap) {
        if (snap == null || snap.length != 14) throw new IllegalArgumentException("Bad snapshot");
        for (int i = 0; i < 14; i++) man[i].setStoneNum(snap[i]);
        syncScores();

        notifyViews();
  
    }

    /**
     * Iterates through man while incrementing stones as if pit was played for player A
     * No pit validation provided, only pass pits that can be legally played
     * @param chosenPit
     * @return index
     * Note: the index returned is the last pit that the stone has landed in
     * 0-5 is playable-A
     * 6 is mancala-A
     * 7-12 playable-B
     * 13 mancala-B
     */
    public int PlayPitA(MancalaPit chosenPit) {
        int index = findmancalaPit(chosenPit);
        if (index < 0 || index > 5) throw new IllegalArgumentException("Invalid pit for A");

        int stonesInHand = chosenPit.getStoneNum();
        chosenPit.setStoneNum(0);

        while (stonesInHand > 0) {
            index = (index + 1) % 14;
            if (index == 13) continue; // skip B store
            man[index].setStoneNum(man[index].getStoneNum() + 1);
            stonesInHand--;
        }

        //capture function
        if (index >= 0 && index <= 5 && man[index].getStoneNum() == 1) {
            int opposite = 12 - index;
            int captured = man[opposite].getStoneNum();
            if (captured > 0) {
                man[opposite].setStoneNum(0);
                man[index].setStoneNum(0);
                man[6].setStoneNum(man[6].getStoneNum() + captured + 1);
            }
        }
        syncScores();
        notifyViews();
        return index;
    }

    /**
     * Iterates through man while incrementing stones as if pit was played for player B
     * No pit validation provided, only pass pits that can be legally played
     * @param chosenPit
     * @return index
     * Note: the index returned is the last pit that the stone has landed in
     * 0-5 is playable-A
     * 6 is mancala-A
     * 7-12 playable-B
     * 13 mancala-B
     */
    public int PlayPitB(MancalaPit chosenPit) {
        int index = findmancalaPit(chosenPit);
        if (index < 7 || index > 12) throw new IllegalArgumentException("Invalid pit for B");

        int stonesInHand = chosenPit.getStoneNum();
        chosenPit.setStoneNum(0);

        while (stonesInHand > 0) {
            index = (index + 1) % 14;
            if (index == 6) continue; // skip A store
            man[index].setStoneNum(man[index].getStoneNum() + 1);
            stonesInHand--;
        }

        //capture function
        if (index >= 7 && index <= 12 && man[index].getStoneNum() == 1) {
            int opposite = 12 - index;
            int captured = man[opposite].getStoneNum();
            if (captured > 0) {
                man[opposite].setStoneNum(0);
                man[index].setStoneNum(0);
                man[13].setStoneNum(man[13].getStoneNum() + captured + 1);
            }
        }

        syncScores();

        notifyViews();

        return index;
    }

    private void syncScores() {
        playerAScore = man[6].getStoneNum();
        playerBScore = man[13].getStoneNum();
    }

    public void setPlayerAScore(int playerAScore) {
        this.playerAScore = playerAScore;
    }
    public void setPlayerBScore(int playerBScore) {
        this.playerBScore = playerBScore;
    }

    public int getPlayerAScore() {
        return playerAScore;
    }
    public int getPlayerBScore() {
        return playerBScore;
    }

    public MancalaPit[] getMancalaPitArray() {
        return man;
    }

    /**
     * Note: Relies on other Garbage Collection methods for each call
     */
    public ArrayList<MancalaPit> getBoard() {
        ArrayList<MancalaPit> manArray = new ArrayList<>();
        for (int i = 0; i < 14; i++ ) {
            manArray.add(man[i]);
        }
        return manArray;
    }
}
