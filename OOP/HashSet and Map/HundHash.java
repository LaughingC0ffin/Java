package hash;

import java.util.Objects;

public class HundHash {

    public String name;
    int kilo;

    public HundHash(String name, int kilo) {
        this.name = name;
        this.kilo = kilo;
    }

    //streut nicht genug
    //aber genug für dieses Beispiel
    //https://de.wikipedia.org/wiki/Hashfunktion
    @Override
    public int hashCode() {
        int out=kilo;
        for(char c : name.toCharArray()){
            out+=c;
        }
        return out;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HundHash hundHash = (HundHash) o;
        return hundHash.hashCode()==this.hashCode();
    }

    @Override
    public String toString(){
        return name;
    }
}
