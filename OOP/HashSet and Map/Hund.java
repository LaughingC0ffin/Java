package Bsp;

import java.util.Objects;

public class Hund {

    String name;
    int kilo;

    public Hund(String name, int kilo) {
        this.name = name;
        this.kilo = kilo;
    }

    @Override
    public String toString() {
        return "Hund{" +
               "name='" + name + '\'' +
               ", kilo=" + kilo +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hund hund = (Hund) o;
        return kilo == hund.kilo && Objects.equals(name, hund.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, kilo);
    }
}