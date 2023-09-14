package cs310.games;


public class GameEngine implements Engine { //Game State
    Game game;
    GameInputInterface input = new RetrieveGameInput();
    private String gameType = "undefined";
    private int first = -1;

    public GameEngine() throws IllegalArgumentException{
        input.askForInput();
        int g = input.getGame();
        int first = input.getPlayer();
        if (g == 1) {
            game = new Nim(first);
            gameType = input.getGameName();
            this.first = first;
        }
        else if (g == 2) {
            game = new TicTacToe();
            gameType = input.getGameName();
            this.first = first;
        }
        else throw new IllegalArgumentException("Invalid Game Choice");

    }
    private void doComputerMove() {
        game.printBoard();
        BestMove compMove = game.chooseMove(game.COMPUTER, 0);
        if (gameType.equals("Nim")) {
            System.out.println("Computer Plays: \nrow = " + compMove.i + "\nstars = " + compMove.j);
        } else {
            System.out.println("Computer Plays: \nrow = " + compMove.i + "\ncolumn = " + compMove.j);
        }
        game.makeMove(game.COMPUTER, compMove.i, compMove.j);
    }

    private void doHumanMove() {
        boolean legal;
        do {
            int[] answers = input.queryGameMove();
            int row = answers[0];
            int col = answers[1];
            legal = game.makeMove(game.HUMAN, row, col);
            if (!legal)
                System.out.println("Illegal move, try again");
        } while (!legal);
        game.printBoard(); //Could be placed in own component if want to print differently in future

    }

    // do one round of playing the game, return true at end of game
    private boolean getAndMakeMoves() {
        game.printBoard();
        if(first == 0) {
            doHumanMove();
            if (!checkAndReportStatus())
                return false; // game over
            doComputerMove();
        }
        else {
            doComputerMove();
            if (!checkAndReportStatus())
                return false; // game over
            doHumanMove();
        }
        return checkAndReportStatus();
    }
    // return true if game is continuing, false if done
    private boolean checkAndReportStatus() {
        if (game.isAWin(game.COMPUTER)) {
            System.out.println("Computer Wins :(");
            game.printBoard();
            return false; // game is done
        }
        if (game.isAWin(game.HUMAN)) {
            System.out.println("Player Wins :D");
            game.printBoard();
            return false; // game is done
        }
        if (game.isADraw()){
            System.out.println("Draw :|");
            game.printBoard();
            return false;
        }
        return true;
    }

    private void playOneGame() {
        boolean continueGame = true;
        game.init();
        while (continueGame) {
            continueGame = getAndMakeMoves();
        }
    }
    public void play() {
        System.out.println("Starting " + gameType + ":\n");
        playOneGame();
    }
}
