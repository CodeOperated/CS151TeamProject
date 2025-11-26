public class MancalaPit {
    protected int stoneNum;
    protected boolean ownerIsA;
    protected boolean isStore;

    public MancalaPit(boolean ownerIsA, boolean isStore) {
        this.ownerIsA = ownerIsA;
        this.isStore = isStore;
    }

    public int getStoneNum() { 
        return stoneNum; 
    }
    public void setStoneNum(int stoneNum) {
        this.stoneNum = stoneNum; 
    }

    public boolean isPlayerAPit() { 
        return ownerIsA; 
    }
    public boolean isStore() { 
        return isStore; 
    }
}
