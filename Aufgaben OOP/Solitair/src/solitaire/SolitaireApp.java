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


                //out.println(command);
                //out.println(command.length());
                //if(command.contains(Character)) out.println("hah");


                String command = scanner.nextLine().replace(" ", "");
                int row = Integer.parseInt(Character.toString(command.charAt(0)));
                int col = Integer.parseInt(Character.toString(command.charAt(1)));
                int row2 = Integer.parseInt(Character.toString(command.charAt(2)));
                int col2 = Integer.parseInt(Character.toString(command.charAt(3)));
                if(command.length()!=4) throw new StringIndexOutOfBoundsException();
                //String myString = "1234";
                //int foo = Integer.parseInt(myString);
                /*
                int  row = Integer.parseInt( String.valueOf(command.charAt(0)));  // 5
                int col = Integer.parseInt( String.valueOf(command.charAt(1)));
                int row2 = Integer.parseInt( String.valueOf(command.charAt(2)));
                int col2 = Integer.parseInt( String.valueOf(command.charAt(3)));
                */
                /*
                final int row = scanner.nextInt();
                final int col = scanner.nextInt();
                final int row2 = scanner.nextInt();
                final int col2 = scanner.nextInt();
                 */


                out.println(row+ " "+col+ " "+row2+ " "+col2);


                if(!board.isValidStart(row,col)) throw new IllegalArgumentException();
                if(board.isValidMove(row,col,row2,col2)) board.move(row,col,row2,col2);
                else throw new IllegalArgumentException("wrong move from"+ row+"/"+col +" to " + row2+"/"+col2);
                board.printboard(out);
                if(board.isFinal()) {
                    out.println("fertig");
                    return;
                }

            out.println("ein durchlauf");

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

    /*
    Sie müssen jetzt noch die Methode playSolitaire() schreiben. Ihre Methode soll in einer
Schleife das Spielbrett board auf out ausgeben und dann auf ein Kommando vom Benutzer
warten, dieses ausführen und dann die Schleife wiederholen.
Kommandos sollen wie beim Schachprogramm mittels scanner gelesen werden. Sobald man
etwas anderes als eine Zahl eingibt, soll die Methode und damit das ganze Programm enden.
Andernfalls werden, sobald vier ganze Zahlen eingelesen sind, diese als Zug interpretiert. Die
ersten beiden Zahlen sind die Koordinaten des Spielsteins, der an die Zielposition bewegt werden
soll, angegeben durch die dritte und vierte eingegebene Zahl. Wenn diese Koordinaten keinen
erlaubten Zug beschreiben, soll eine Fehlermeldung ausgegeben, andernfalls der Zug ausgeführt
werden und der nächste Schleifendurchlauf beginnen.

Das Programm soll automatisch enden, wenn der Endzustand des Spielbretts erreicht ist, sonst
nur, wenn etwas anderes als eine Zahl eingegeben wird.
     */
}


/*
Aufgabe 3
Schreiben Sie die Klasse SolitaireApp, um Solitaire interaktiv auf der Konsole spielen zu können.
Der Programmcode von SolitaireApp soll (analog zum Schachprogramm, das Thema in
der Vorlesung und im ersten Testataufgabenblatt war) mit den folgenden Zeilen beginnen:



In Abb. 2 ist ein Beispieldurchlauf des Programms angegeben. Benutzereingaben sind kursiv
gesetzt.
3
012345678
+---------+
0| ### |
1| ### |
2| ### |
3|#########|
4|#### ####|
5|#########|
6| ### |
7| ### |
8| ### |
+---------+


Type in next move (four integers) or abort by any non-integer:
4 2 4 4
012345678
+---------+
0| ### |
1| ### |
2| ### |
3|#########|
4|## #####|
5|#########|
6| ### |
7| ### |
8| ### |
+---------+
Type in next move (four integers) or abort by any non-integer:
4 1 4 2
Invalid move from 4/1 to 4/2
Type in next move (four integers) or abort by any non-integer:
abort
Aborted
Abbildung 2: Beispieldurchlauf des Programms SolitaireApp. Benutzereingaben sind kursiv
gesetzt.

 */