package quantities.plain;

public abstract class Unit {
    public final String name;
    public final double baseFactor;

    public Unit(String name, double baseFactor) {
        this.name = name;
        this.baseFactor = baseFactor;
    }

    @Override
    public String toString() {
        return name;
    }
}
