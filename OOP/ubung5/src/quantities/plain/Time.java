package quantities.plain;

public class Time extends Quantity {
    private final TimeUnit unit;

    public Time(double value, TimeUnit unit) {
        super(value, unit);
        this.unit = unit;
    }

    public Time plus(Time other) {
        return new Time(value + other.getBaseValue() / unit.baseFactor, unit);
    }

    public Time minus(Time other) {
        return new Time(value - other.getBaseValue() / unit.baseFactor, unit);
    }

    public Time mult(double f) {
        return new Time(value * f, unit);
    }

    public Time div(double f) {
        return new Time(value / f, unit);
    }

    public Time to(TimeUnit unit) {
        return new Time(getBaseValue() / unit.baseFactor, unit);
    }

    public double div(Time other) {
        return getBaseValue() / other.getBaseValue();
    }

    public Length mult(Velocity v) {
        return v.mult(this);
    }
}
