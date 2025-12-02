public class ScorePit extends MancalaPit {
	/**
	 * @param ownerIsA - true if belong to A; false if belong to B
	 */
    public ScorePit(boolean ownerIsA) {
        super(ownerIsA, true);   
        this.stoneNum = 0;           
    }
}
