package solitaire;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;
//import static java.lang.System.out;

public class SolitaireApp {
    private final Scanner scanner;
    private final PrintStream out;
    private final SolitaireBoard board;

    public static void main(String[] args) {
        final SolitaireBoard board = new SolitaireBoard();
        new SolitaireApp(System.in, System.out, board).playSolitaire();
    }

    SolitaireApp(InputStream in, PrintStream out, SolitaireBoard board) {
        scanner = new Scanner(in);
        this.out = out;
        this.board = board;
    }
    //automatisch enden wenn is final true ist
    //isfinal
    //isvalidmove => sagt mir ob man diesen move machen kann
    //move => kann exception geben => verändert aber spielfeld
    //is valid start => sagt ob man den stein bewegen kann
    //schauen ob man stein bewegen kann

    public void playSolitaire(){
        board.printboard(out);
        while(true){
            out.println("Type in next move (four integers) or abort by any non-integer: ");
            try{
                if(!scanner.hasNext()) throw new InputMismatchException();
                //out.println(command);
                //out.println(command.length());
                //if(command.contains(Character)) out.println("hah");
                /*
                String command = scanner.nextLine().replace(" ", "");

                int row = Integer.parseInt(Character.toString(command.charAt(0)));
                int col = Integer.parseInt(Character.toString(command.charAt(1)));
                int row2 = Integer.parseInt(Character.toString(command.charAt(2)));
                int col2 = Integer.parseInt(Character.toString(command.charAt(3)));
                //if(command.length()!=4) throw new StringIndexOutOfBoundsException();
                 */
                /*
                int  row = Integer.parseInt( String.valueOf(command.charAt(0)));  // 5
                int col = Integer.parseInt( String.valueOf(command.charAt(1)));
                int row2 = Integer.parseInt( String.valueOf(command.charAt(2)));
                int col2 = Integer.parseInt( String.valueOf(command.charAt(3)));
                */
                final int row = scanner.nextInt();
                final int col = scanner.nextInt();
                final int row2 = scanner.nextInt();
                final int col2 = scanner.nextInt();


                out.println(row+ " "+col+ " "+row2+ " "+col2);

                if(!board.isValidStart(row,col)) throw new IllegalArgumentException();
                if(board.isValidMove(row,col,row2,col2)) board.move(row,col,row2,col2);
                else throw new IllegalArgumentException("wrong move from"+ row+"/"+col +" to " + row2+"/"+col2);
                board.printboard(out);
                if(board.isFinal()) {
                    out.println("fertig");
                    return;
                }
            }


            catch (InputMismatchException ex){
             out.println("Aborted");
             return;
            }
            catch (NumberFormatException ex){
                out.println("aborted");
                return;
            }
            catch (IllegalArgumentException ex){
                out.println("Invalider Zug: " + ex);
            }
            catch (StringIndexOutOfBoundsException ex){
                out.println("nicht genug argumente");
            }
            catch(Exception ex){
                out.println("was anderes ist schief gegangen");
                out.println(ex);
            }


        }
    }
}
