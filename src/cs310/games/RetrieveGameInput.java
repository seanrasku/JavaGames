package cs310.games;

import java.util.Scanner;

public class RetrieveGameInput implements GameInputInterface {
    private Scanner scan = new Scanner(System.in);
    private int game;
    private int first;
    private int firstNum;
    public RetrieveGameInput() {};
    public void askForInput() {
        System.out.println("Select Game By Entering Number:");
        System.out.println("1: Nim\n2: Tic Tac Toe");
        System.out.print("Your Choice: ");
        int game = Integer.parseInt(scan.next());
        System.out.println("Who goes first? \n0: You\n 1: Computer\n--> ");
        int first = Integer.parseInt(scan.next());
        if(game < 1 || game > 2 || first < 0 || first > 1) {
            throw new IllegalArgumentException("Invalid Input, try again");
        }
        this.game = game;
        this.first = first;

    }
    public int getGame() {
        return game;
    }
    public String getGameName() {
        return game == 1 ? "Nim" : game == 2 ? "Tic Tac Toe" : "undefined";
    }
    public int getPlayer() {
        return first;
    }

    public int[] queryGameMove() {
        int row;
        int col;
        if(game == 1) {
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
        return new int[]{row, col};

    }
}
