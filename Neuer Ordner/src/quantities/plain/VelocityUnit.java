package quantities.plain;

public class VelocityUnit extends Unit {
    public VelocityUnit(String name, double baseFactor) {
        super(name, baseFactor);
    }

    public static final VelocityUnit METER_PER_SECOND = new VelocityUnit("m/s", 1);
    public static final VelocityUnit KMH = new VelocityUnit("km/h", 1.0 / 3.6);
    public static final VelocityUnit MPH = new VelocityUnit("mph", 0.44704);
}
