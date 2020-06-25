package quantities.plain;

import static quantities.plain.LengthUnit.METER;
import static quantities.plain.TimeUnit.SECOND;
import static quantities.plain.VelocityUnit.METER_PER_SECOND;

public class Velocity extends Quantity {
    private final VelocityUnit unit;

    public Velocity(double value, VelocityUnit unit) {
        super(value, unit);
        this.unit = unit;
    }

    public Velocity plus(Velocity other) {
        return new Velocity(value + other.getBaseValue() / unit.baseFactor, unit);
    }

    public Velocity minus(Velocity other) {
        return new Velocity(value - other.getBaseValue() / unit.baseFactor, unit);
    }

    public Velocity mult(double f) {
        return new Velocity(value * f, unit);
    }

    public Velocity div(double f) {
        return new Velocity(value / f, unit);
    }

    public Velocity to(VelocityUnit unit) {
        return new Velocity(getBaseValue() / unit.baseFactor, unit);
    }

    public double div(Velocity other) {
        return getBaseValue() / other.getBaseValue();
    }

    public Length mult(Time t) {
        return new Length(this.value(METER_PER_SECOND) * t.value(SECOND), METER);
    }
}
