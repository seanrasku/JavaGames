package cs310.games;

import java.util.Scanner;

public class Play implements UserInteraction {
    Game game;
    private Scanner scan = new Scanner(System.in);
    private Scanner userscanner = new Scanner(System.in);
    private String gameType;
    private String first = "None";

    public Play() throws IllegalArgumentException{
        int[] ans = askForInput();
        String g = ans[0] == 0 ? "Nim" : ans[0] == 1 ? "Tic Tac Toe" : "undefined";
        int f = ans[1];
        if (g.equals("Nim")) {
            game = new Nim(f);
            gameType = g;
            if(f == 0) first = "Human";
            else first = "Computer";
        }
        else if (g.equals("Tic Tac Toe")) {
            game = new TicTacToe();
            gameType = g;
            if(f == 0) first = "Human";
            else first = "Computer";
        }
        else throw new IllegalArgumentException("Invalid Game Choice");

    }
    private int[] askForInput() {
        int[] answers = new int[2];
        System.out.println("Select Game By Entering Number:");
        System.out.println("0: Nim\n1: Tic Tac Toe");
        System.out.print("Your Choice: ");
        int game = Integer.parseInt(scan.next());
        System.out.println("Who Goes First? (0: Human, 1: Computer): ");
        int first = Integer.parseInt(scan.next());
        if(game < 0 || game > 1 || first < 0 || first > 1) {
            throw new IllegalArgumentException("Invalid input, try again");
        }
        answers[0] = game;
        answers[1] = first;

        return answers;
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
        game.printBoard();
        do {
            int row;
            int col;
            if(gameType.equals("Nim")) {
                System.out.println("row: ");
                row = scan.nextInt();
                System.out.println("stars: ");
                col = scan.nextInt();
            }
            else {
                System.out.println("row: ");
                row = scan.nextInt();
                System.out.println("column: ");
                col = scan.nextInt();
            }
            legal = game.makeMove(game.HUMAN, row, col);
            if (!legal)
                System.out.println("Illegal move, try again");
        } while (!legal);
    }

    // return true if game is continuing, false if done
    private boolean checkAndReportStatus() {
        if (game.isAWin(game.COMPUTER)) {
            System.out.println("Computer says: I WIN!!");
            game.printBoard();
            return false; // game is done
        }
        if (game.isAWin(game.HUMAN)) {
            System.out.println("Computer says: YOU WIN!!");
            game.printBoard();
            return false; // game is done
        }
        if (game.isADraw()){
            System.out.println("DRAW!!");
            game.printBoard();
            return false;
        }
        return true;
    }

    // do one round of playing the game, return true at end of game
    private boolean getAndMakeMoves() {
        // let computer go first...
        // game over
        if(first.equals("Human")) {
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
