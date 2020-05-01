/*
AUFGABE 1
//sollte gehen
Gegeben ist ein Array zum Beispiel mit Zahlen als Werten:
  {2, 17, 10, 9, 16, 3, 9, 16, 5, 1, 17, 14}
Schreiben Sie ein Programm, das aus einem Array das häufigste Element findet. Sollte es mehrere gleicher Anzahl finden, so darf irgend ein Element dieser Häufigsten ausgegeben werden.

AUFGABE 2
//schon schwer
Schreiben Sie eine Prozedur, die ein Array A vom Typ Array[Int] mit 20 natürlichen, positiven Zahlen
übergeben bekommt. Ihre Prozedur überschreibt so lange positive und doppelt vorkommende
Werte aus A mit dem Wert 0, bis jeder ursprünglich in A enthaltene Wert genau einmal in A
vorkommt. Der Rest der Werte in A ist nach Aufruf Ihrer Prozedur 0.
Schreiben sie eine Prozedur ohne Hilfsprozeduren oder Funktionen, arbeiten Sie auf dem Array A
und erstellen Sie kein weiteres Array.
Für die Eingabe   A = [3,2,1,5,5,5,4,8,9,123,2,1,5,4,5,4,5,18,9,3]
liefert Ihre Prozedur    A = [3,2,1,5,0,0,4,8,9,123,0,0,0,0,0,0,0,18,0,0]

AUFGABE 3
//leicht
bestimmen sie das maximum eines gegebenen arrays

AUFGABE 4
//schwer aber spannend und hilft beim verstehen vom verarbeiten von arrays
Nach Eratosthenes, einem Mathematiker des alten Griechenlands,
wird folgender Algorithmus zur Bestimmungaller Primzahlen bis zu
einer bestimmten Zahl ,,Sieb des Eratosthenes“ genannt:
Angenommen, Sie wollen alle Primzahlen ≤1000 bestimmen.
•Erstellen Sie ein Array vom Typ boolean[]der L̈ange 1001.
•Die Elemente mit Index 0 und 1 sollen den Wert false haben, alle anderen zun ̈achst den Wert true.
(0und 1 sind per definition em keine Primzahlen.)
•Fangen Sie nun an, das Array von vorne an nach Elementen vom Wert true zu durchsuchen.
F ̈ur jedes solche Elementa an Index ia setzen Sie den Wert aller Element eb,
deren Index i bei n ganzzahligesVielfaches von ia ist, auf den Wert false.
•Wenn Sie die Suche beendet haben, sind die Indizes mit Wert true die Primzahlen ≤1000

AUFGABE 5
//relativ leicht
implementieren sie eine funktion die den durschnitt eines gegebenen arrays von Ints berechnet
(alle zusammen rechnen durch die anzahl der zahlen)

AUFGABE 6
Schreiben Sie eine Funktion, die ein Array mit den ersten n Fibonacci–Zahlen liefert

Aufgabe 7
finden sie das erste UND das zweite maximum eines arrays

Aufgabe 8
Addieren sie zwei arrays komponentenweise

Aufgabe 9
prüfen sie ob eine gegebene zahl n in einem array vorhanden ist

Aufgabe 10
schreiben sie ein programm welches die ersten n elemente eines arrays in umgekehrter reihenfolge ausgibt

Aufgaben 11
schreiben sie ein programm welches die anzahl der EINZELN vorkommenden elemente ermittelt

Aufgabe 12
 Aufgabe 1: Prufen von Palindromen (25 Punkte)
Ein Palindrom ist ein Wort, das vorwarts und ruckwarts gelesen exakt gleich ist (die gleiche
Buchstabenfolge). Schreiben Sie einen Algorithmus, der ein Array von Chars
uberpruft, ob es sich um ein Palindrom handelt oder nicht, und die Antwort (wahr/falsch)
ausgibt.

aufgabe 13
//gegeben wird ein array von strings. geben sie zu jdem string jeweils den string und wie oft dieser string vorkommt aus

//aufgbe 14
Eine Collatz-Folge (bekannt auch als Syracuse-Problem, Kakutani-Poblem, Hasse-Algorithmus und Ulam-Problem) von Lothar Collatz, 1937 ist definiert durch

n -> n/2, falls n gerade ist, n -> 3n+1,
falls n ungerade ist. Die Folge ist beendet, wenn 1 erreicht ist.

Beginnt man etwa mit n = 7, durchläuft der Algorithmus die folgenden Zahlen:

    7 -> 22 -> 11 -> 34 -> 17 -> 52 -> 26 -> 13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1

  */