package Beispiel.other;

public enum Dir {

    OBEN(-1, 0,"a"), RECHTS(0, 1,"b"), UNTEN(1, 0,"c"), LINKS(0, -1,"d");

    public int row;
    public int col;
    public String name;

    private Dir(int row, int col,String name){
        this.row = row;
        this.col = col;
        this.name=name;
    }

    public Dir opposite(){
        if(this == OBEN){
            return UNTEN;
        } else if(this == UNTEN){
            return OBEN;
        } else if(this == LINKS){
            return RECHTS;
        } else {
            return LINKS;
        }
    }
}
