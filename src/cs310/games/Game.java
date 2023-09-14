package cs310.games;

public interface Game {
    int HUMAN = 0;
    int COMPUTER = 1;
    int HUMAN_WIN = 0;
    int UNCLEAR = 2;
    int COMPUTER_WIN = 3;

    BestMove chooseMove(int side, int depth);
    boolean makeMove(int side, int i, int j);
    boolean isAWin(int side);
    boolean isADraw();
    void printBoard();
    void init();



    }
