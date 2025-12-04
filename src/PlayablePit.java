/**
 * CS 151 Final Project Solution 
 * @author Hien Ly
 * @version 1.0 12/4/2025
 */

/**
 * Playable pit that represents the pits the user can interact with.
 */
public class PlayablePit extends MancalaPit {
    public PlayablePit(boolean ownerIsA, int startingStones) {
        super(ownerIsA, false); 
        this.stoneNum = startingStones;
    }
}
