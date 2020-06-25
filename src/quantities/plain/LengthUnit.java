package quantities.plain;

public class LengthUnit extends Unit {
    public LengthUnit(String name, double baseFactor) {
        super(name, baseFactor);
    }

    public static final LengthUnit METER = new LengthUnit("m", 1);
    public static final LengthUnit MILLIMETER = new LengthUnit("mm", 0.001);
    public static final LengthUnit KILOMETER = new LengthUnit("km", 1000);
    public static final LengthUnit MILE = new LengthUnit("mi", 1609.344);
    public static final LengthUnit LIGHTYEAR = new LengthUnit("ly", 9460730472580800.0);
    public static final LengthUnit PARSEC = new LengthUnit("pc", 3.0856776e16);
}
