import java.util.ArrayList;


/** MancalaBoard class created by Jose
 * Virtual representation of a Mancala board with actions included, no rules are provided for turn taking
 *
 */
public class MancalaBoard {
    MancalaPit[] man = new MancalaPit[14];
    int playerAScore;
    int playerBScore;

    /**
     * Constructs Mancala Board with default stone settings - leftmost playable pit is MancalaPit[0]
     */
    MancalaBoard(){
        for(int i = 0; i < man.length;){
            if (i != 6 && i != 13) man[i].setStoneNum(4);
            else continue;

        }
    }

    /**
     * Constructs Mancala board with a custom set
     * @param StartingStones
     */
    MancalaBoard(int StartingStones){
        for(int i = 0; i < man.length;){
            if (i != 6 && i != 13) man[i].setStoneNum(StartingStones);
            else continue;
        }
    }

    /**
     * Constructs Mancala board with manually setting each stone for each pit using an integer array
     * @param int[] StartingStones
     */
    MancalaBoard(int[] StartingStones){
        for(int i = 0; i < man.length;){
            man[i].setStoneNum(StartingStones[i]);
        }
    }

    /**
     * Constructs Mancala board with manually setting each stone for each pit using a MancalaPit array
     * @param MancalaPit[] StartingStones
     */
    MancalaBoard(MancalaPit[] StartingStones){
        for(int i = 0; i < man.length;){
            man[i].setStoneNum(StartingStones[i].getStoneNum());
        }
    }

    /**
     * Helper Function - Finds the index of the provided MancalaPit in man
     * @param chosenPit
     * @return index
     */
    int findmancalaPit(MancalaPit chosenPit){
        int index = 0;

        for (int i = 0; i < 13; i++){
            if (man[i] == chosenPit){
                index = i;
            }
        }
        return index;
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
    public int PlayPitA(MancalaPit chosenPit){
        int stonesInHand = chosenPit.getStoneNum();
        int index = findmancalaPit(chosenPit);

        chosenPit.setStoneNum(0);

        for (int stones = stonesInHand; stones > 0; stones--) {
            index++;//starts on adjacent pit
            if (index >= 13) {
                stones++; //returns stone due to skipping
                index = 0;//skips Score pit for player B, resets index because player B pit is last pit in array
                continue;
            }

            man[index].setStoneNum(man[index].getStoneNum() + 1);

        }

        //capture function (fun fact: Waterfall playstyle would just make this a recursive function
        if (man[index].getStoneNum() == 1 && index < 6) {
            man[6].setStoneNum(man[6].getStoneNum() + man[index].getStoneNum() + man[12-index].getStoneNum());
            man[index].setStoneNum(0);
            man[12-index].setStoneNum(0);
        }

        setPlayerAScore(man[6].getStoneNum());

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
    public int PlayPitB(MancalaPit chosenPit){
        int stonesInHand = chosenPit.getStoneNum();
        int index = findmancalaPit(chosenPit);

        chosenPit.setStoneNum(0);

        for (int stones = stonesInHand; stones > 0; stones--) {
            index++;//starts on adjacent pit
            if (index == 6) {
                stones++;//returns stone due to skipping
                index = 7;
                //skips Score pit for player A
                continue;
            }

            man[index].setStoneNum(man[index].getStoneNum() + 1);

        }

        //capture function (fun fact: Waterfall playstyle would just make this a recursive function
        if (man[index].getStoneNum() == 1 && index > 6 && index != 13) {
            man[13].setStoneNum(man[13].getStoneNum() + man[index].getStoneNum() + man[12-index].getStoneNum());
            man[index].setStoneNum(0);
            man[12-index].setStoneNum(0);
        }

        setPlayerBScore(man[13].getStoneNum());

        return index;
    }


    /**
     * Sets playerAScore and Mancala
     * @param playerAScore
     */
    public void setPlayerAScore(int playerAScore) {
        man[6].setStoneNum() = playerAScore;
        this.playerAScore = playerAScore;
    }

    /**
     * Sets playerBScore and Mancala
     * @param playerBScore
     */
    public void setPlayerBScore(int playerBScore) {
        man[13].setStoneNum() = playerBScore;
        this.playerBScore = playerBScore;
    }

    /**
     * updates and returns playerA Mancala score as an integer value
     * @return playerAScore
     */
    public int getPlayerAScore() {
        this.playerAScore = man[6].getStoneNum();
        return playerAScore;
    }

    /**
     * updates and returns playerB Mancala score as an integer value
     * @return playerBScore
     */
    public int getPlayerBScore() {
        this.playerAScore = man[13].getStoneNum();
        return playerBScore;
    }
    public MancalaPit[] getMancalaPitArray() {
        return man;
    }

    /**
     * Converts MancalaPit Array into MancalaPit Arraylist for compatibility
     * @return ArrayList
     */
    //Note: Relies on other Garbage Collection methods for each call
    public ArrayList<MancalaPit> getBoard(){
        ArrayList<MancalaPit> manArray = new ArrayList<>();
        for (int i = 0; i < 13; i++ ){
            manArray.add(man[i]);
        }
        return manArray;
    }
}