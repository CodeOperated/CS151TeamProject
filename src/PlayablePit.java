public class PlayablePit extends MancalaPit {
	/**
	 * @param ownerIsA - true if belong to A; false if belong to B
	 * @param startingStones - the starting number of stone
	 */
    public PlayablePit(boolean ownerIsA, int startingStones) {
        super(ownerIsA, false); 
        this.stoneNum = startingStones;
    }
}
