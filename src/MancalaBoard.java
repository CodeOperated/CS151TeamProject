

public class MancalaBoard {
    MancalaPit[14] mancalaPits;
    int playerAScore;
    int playerBScore;

    MancalaBoard(){
        for(int i = 0; i < mancalaPits){
            if (i != 6 && i != 13) MancalaPit[i].setStoneNum(4);
            else continue;

        }
    }

    MancalaBoard(int StartingStones){
        for(int i = 0; i < mancalaPits){
            if (i != 6 && i != 13) MancalaPit[i].setStoneNum(StartingStones);
            else continue;
        }
    }

}