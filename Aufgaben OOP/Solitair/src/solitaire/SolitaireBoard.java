package solitaire;

import javafx.util.Pair;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.lang.System.out;
import static java.lang.Integer.signum;
import static java.lang.Math.abs;


public class SolitaireBoard {

    private FieldState[][] field;
/*
a) Die Klasse soll nur den parameterlosen Konstruktor besitzen, der das Spielfeld wie in Abb. 1
initialisiert.
 */
    public SolitaireBoard() {
        //final List<FieldState> pieces = new ArrayList<>();
        //final FieldState[][] field = new FieldState[9][9];
        this.field = new FieldState[9][9];
        for (int a = 0; a < 9; a++) {
            for (int b = 0; b < 9; b++) {
                if (a < 3 && b < 3 || a > 5 && b < 3 || a < 3 && b > 5 || a > 5 && b > 5) {
                    field[a][b] = FieldState.invalid;
                }
                else {
                    field[a][b] = FieldState.occupied;
                }
            }
        }
        field[4][4] = FieldState.empty;
    }

    private String getcont(FieldState cont) {
        if (cont == FieldState.occupied) return "#";
        else return " ";
    }

    public void printboard(PrintStream out) {
        out.print("  "+"012345678"+ "\n");
        out.print(" "+"+---------+"+ "\n");

        for (int a = 0; a < 9; a++) {
            out.print(a + "|");
            for (int b = 0; b < 9; b++) {
                out.print(getcont(field[a][b]));
            }
            out.print("|"+"\n");
        }
        out.print(" "+"+---------+" + "\n");
    }

    /*
    c) Die toString-Methode soll das Spielbrett als String zurückgeben. Im Anfangszustand sei
die Ausgabe die mehrzeilige Zeichenkette
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("  "+"012345678" + "\n");
        output.append(" "+"+---------+" + "\n");

        for (int a = 0; a < 9; a++) {
            output.append(a).append("|");
            for (int b = 0; b < 9; b++) {
                output.append(getcont(field[a][b]));
            }
            output.append("|"+"\n");
        }
        output.append(" "+"+---------+" + "\n");

        return output.toString();
    }

    /*
    b) Die Methode getField(int row, int col) soll den Zustand des Feldes in der Zeile row
und der Zeile col als FieldState-Wert zurückgeben. Beispielsweise muss getField(0, 4)
den Zustand des Feldes (0; 4), also in der Mitte der obersten Zeile zurückgeben, im Anfangszustand
somit occupied. Die Methode muss auch sinnvolle Ergebnisse zurückliefern, wenn
sie mit Koordinaten außerhalb des in Abb. 1 angegebenen Bereichs aufgerufen werden.
     */
    public FieldState getField(int row, int col) {
        if (row < 9 && row >= 0 && col >= 0 && col < 9) return field[row][col];
        else return FieldState.invalid;
    }
/*
d) Die Methode isFinal() prüft, ob das Spielbrett im Endzustand ist, d.h. es nur noch einen
Spielstein enthält, der in der Mitte liegt, also an Position (4; 4). Der Rückgabewert soll ein
entsprechender Boolescher Wert sein.
 */

    //es fehlt glaube die überpüfung ob das teil in der mitte ist
    public Boolean isFinal() {
        int count = 0;
        for (int a = 0; a < 9; a++) {
            for (int b = 0; b < 9; b++) {
            if(field[a][b]== FieldState.occupied) count+=1;
            }
        }
        //out.println("dings auf dem board:"+count);
        return count == 1 && field[4][4]==FieldState.occupied;
    }
    /*
    e) Die Methode isValidMove(int row1, int col1, int row2, int col2) liefert einen
Booleschen Wert zurück. Er ist genau dann true, wenn man einen Spielstein vom Feld
(row1; col1) auf das Feld (row2; col2) springen lassen kann.
     */

    public Boolean isValidMove(int row1, int col1, int row2, int col2){
        if(getField(row1,col1)!=FieldState.occupied || getField(row2,col2)!= FieldState.empty)return false;
        //anfang muss stein sein
        //ende muss empty sein
        //ich subtrahiere die voneinander mit absolut beträgen und schaue ob 0 und 2 raus kommt
        //wenn nicht ist er invalid


        int dr=abs(row1-row2);
        int dc = abs(col1-col2);

        //out.println(dr+ "  " + dc);
        //out.println(dr==0 && dc==2  ||  dr==2 && dc==0);

        return dr==0 && dc==2  ||  dr==2 && dc==0;

    }

    /*
    f) Die Methode move(int row1, int col1, int row2, int col2) führt den Zug aus, indem
der Spielstein vom Feld (row1; col1) auf das Feld (row2; col2) springt (und den dazwischen
liegenden entfernt). Wenn dies kein erlaubter Zug ist, soll sich das Spielbrett nicht ändern
und stattdessen eine IllegalArgumentException mit geeigneter Fehlermeldung geworfen
werden.
     */

    public void move(int row1, int col1, int row2, int col2){
        if(! isValidMove(row1,col1,row2,col2)) {
            throw new IllegalArgumentException("not valid move");
        }
        //springen
        field[row1][col1]= FieldState.empty;
        field[row2][col2]= FieldState.occupied;
        //schlagen
        int dr = signum(row2-row1);
        int dc = signum(col2-col1);
        field[row1+dr][col1+dc]= FieldState.empty;
    }

    /*
    g) Die Methode isValidStart(int row, int col) prüft, ob auf dem Feld (row; col) ein Spielstein
liegt, den man in einem gültigen Zug nach oben, unten, links oder rechts springen lassen
kann. Wenn das möglich ist, liefert der die Methode true zurück, sonst false.
     */
    public Boolean isValidStart(int row, int col){
        if(field[row][col]!= FieldState.occupied)return false;
        //es istd ei frage ob jede richtung erfüllt sein muss
        //oder ob einfach eine reicht
        int dir1= 0;
        int dir2 = 2;
        int zwisch = 0;
        //(a,b) => (b,-a)
        for(int a = 0;a<4;a++){
            if(isValidMove(row,col,row+dir1,col+dir2))return true;
            zwisch = dir1;
            dir1=dir2;
            dir2= -1*zwisch;
        }
        return false;
    }
}