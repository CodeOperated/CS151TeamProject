/**
 * CS 151 Final Project Solution 
 * @author Jose Quevado
 * @version 1.0 12/4/2025
 */

/**
 * Score pit that represents the Mancala where stones scores are collected.
 */
public class ScorePit extends MancalaPit {
	/**
	 * @param ownerIsA - true if belong to A; false if belong to B
	 */
    public ScorePit(boolean ownerIsA) {
        super(ownerIsA, true);   
        this.stoneNum = 0;           
    }
}
