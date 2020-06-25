package quantities.plain;

public class TimeUnit extends Unit {
    public TimeUnit(String name, double baseFactor) {
        super(name, baseFactor);
    }

    public static final TimeUnit SECOND = new TimeUnit("s", 1);
    public static final TimeUnit MINUTE = new TimeUnit("min", 60);
    public static final TimeUnit HOUR = new TimeUnit("h", 3600);
    public static final TimeUnit DAY = new TimeUnit("d", 86400);
}
