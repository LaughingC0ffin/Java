package chess;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChessApp {
    private static final String HELP = "h";
    private static final String CHECK = "c";
    private static final String ABORT = "a";
    private static final String WHITE_QUEEN = "q";
    private static final String BLACK_QUEEN = "Q";
    private static final String WHITE_KNIGHT = "n";
    private static final String BLACK_KNIGHT = "N";
    private static final String WHITE_BISHOP = "b";
    private static final String BLACK_BISHOP = "B";

    private final Scanner scanner;
    private final PrintStream out;
    private final Board board;

    public static void main(String[] args) {
        new ChessApp(System.in, System.out, new Board()).playChess();
    }

    ChessApp(InputStream in, PrintStream out, Board board) {
        scanner = new Scanner(in);
        this.out = out;
        this.board = board;
    }


    //ich brauche nen addBishop
    //dann muss in didesem if konstrukt was ergänzt werden
    void playChess() {
        board.printTo(out);
        while (true) {
            out.println("Type in command (" + HELP + " for help):");
            try {
                final String command = scanner.next();
                if (command.equals(ABORT))
                    break;
                else if (command.equals(HELP))
                    help();
                else if (command.equals(CHECK))
                    board.check(out);
                else if (command.equals(BLACK_QUEEN))
                    addQueen(Color.black);
                else if (command.equals(WHITE_QUEEN))
                    addQueen(Color.white);
                else if (command.equals(BLACK_KNIGHT))
                    addKnight(Color.black);
                else if (command.equals(WHITE_KNIGHT))
                    addKnight(Color.white);
                else if(command.equals(WHITE_BISHOP))
                    addBishop(Color.white);
                else if(command.equals(BLACK_BISHOP))
                    addBishop(Color.black);
                else
                    out.println("Invalid command " + command);
            }
            catch (IllegalArgumentException ex) {
                out.println(ex.getMessage());
            }
            catch (NoSuchElementException ex) {
                break;
            }
        }
        out.println("Terminated");
    }

    private void addKnight(Color color) {
        final int row = scanner.nextInt();
        final int col = scanner.nextInt();
        new Knight(color, row, col, board);
        board.printTo(out);
    }

    private void addQueen(Color color) {
        final int row = scanner.nextInt();
        final int col = scanner.nextInt();
        new Queen(color, row, col, board);
        board.printTo(out);
    }
    private void addBishop(Color color){
        final int row = scanner.nextInt();
        final int col = scanner.nextInt();
        new Bishop(color, row, col, board);
        board.printTo(out);
    }

    private void help() {
        out.println("Commands:");
        out.println(ABORT + ": terminate the program");
        out.println(CHECK + ": check the pieces on the board");
        out.println(WHITE_KNIGHT + " <int> <int>: place a new white knight at specified position");
        out.println(BLACK_KNIGHT + " <int> <int>: place a new black knight at specified position");
        out.println(WHITE_QUEEN + " <int> <int>: place a new white queen at specified position");
        out.println(BLACK_QUEEN + " <int> <int>: place a new black queen at specified position");
        out.println(WHITE_BISHOP + " <int> <int>: place a new white bishop at specified position");
        out.println(BLACK_BISHOP + " <int> <int>: place a new black bishop at specified position");
    }
}
