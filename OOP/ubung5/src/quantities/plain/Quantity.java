package quantities.plain;

public abstract class Quantity {
    public final double value;
    public final Unit unit;

    protected Quantity(double value, Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getBaseValue() {
        return value * unit.baseFactor;
    }

    public double value(Unit unit) {
        return getBaseValue() / unit.baseFactor;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

    public String format(String fmt, Unit unit) {
        return String.format(fmt, value(unit), unit);
    }
}
