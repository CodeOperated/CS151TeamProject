/**
 * CS 151 Final Project Solution 
 * @author Jose Quevedo, Hien Ly
 * @version 1.0 12/4/2025
 */

/**
 * Virtual representation of a Mancala pit
 */
public class MancalaPit {
    protected int stoneNum;
    protected boolean ownerIsA;
    protected boolean isStore;
    /**
     * constructor
     * @param ownerIsA - true if it's A's pit
     * @param isStore - true if it's a store pit
     */
    public MancalaPit(boolean ownerIsA, boolean isStore) {
        this.ownerIsA = ownerIsA;
        this.isStore = isStore;
    }
    
    /**
     * get the number of stone of pit
     * @return stoneNum - the number of stone
     */
    public int getStoneNum() { 
        return stoneNum; 
    }
    /**
     * set the number of stone of pit
     * @return stoneNum - the number of stone to set
     */
    public void setStoneNum(int stoneNum) {
        this.stoneNum = stoneNum; 
    }
    
    /**
     * check if it's player A's pit
     * @return ownerIsA - true if it's player A's pit; false if it's B's
     */
    public boolean isPlayerAPit() { 
        return ownerIsA; 
    }
    /**
     * check if it's a store pit
     * @return isStore - true if it's a store pit; false if playable
     */
    public boolean isStore() { 
        return isStore; 
    }
}
