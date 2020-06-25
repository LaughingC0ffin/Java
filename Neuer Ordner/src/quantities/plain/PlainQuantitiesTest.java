package quantities.plain;

import static quantities.plain.LengthUnit.KILOMETER;
import static quantities.plain.LengthUnit.MILE;
import static quantities.plain.LengthUnit.MILLIMETER;
import static quantities.plain.LengthUnit.PARSEC;
import static quantities.plain.TimeUnit.HOUR;
import static quantities.plain.TimeUnit.MINUTE;
import static quantities.plain.TimeUnit.SECOND;
import static quantities.plain.VelocityUnit.KMH;
import static quantities.plain.VelocityUnit.METER_PER_SECOND;
import static quantities.plain.VelocityUnit.MPH;

public class PlainQuantitiesTest {
    public static void main(String[] args) {
        final Length l1 = new Length(1, KILOMETER);
        final Length l2 = new Length(1200, MILLIMETER);
        final Length l3 = new Length(1, MILE);

        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l1 + " + " + l2 + " = " + l1.plus(l2));
        System.out.println(l1 + " + " + l2 + " (in mm) = " + l1.plus(l2).to(MILLIMETER));

        System.out.println(l3 + " / " + l1 + " = " + l3.div(l1));

        final Time t1 = new Time(100, SECOND);
        final Time t2 = new Time(5, HOUR);

        // does not work
        // final Quantity<?> diff = t1.plus(l2);

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t1.plus(t2));
        System.out.println(t1.plus(t2).to(MINUTE));

        final var v1 = new Velocity(12, KMH);
        final var v2 = new Velocity(100, METER_PER_SECOND);

        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v2.to(KMH));
        System.out.println(v1.plus(v2));

        final Length l4 = new Length(300, KILOMETER).to(PARSEC);
        final Time t3 = new Time(2, HOUR);
        final Velocity v3 = l4.div(t3);
        System.out.println(l4 + " / " + l3 + " = " + v3);

        System.out.println(v1 + " * " + t1 + " = " + v1.mult(t1).to(KILOMETER));

        final Length l5 = v3.mult(t1.to(HOUR));
        System.out.println(v3 + " * " + t1 + " = " + l5);

        final Time t5 = l4.div(v2);
        System.out.println(l4 + " / " + v2 + " = " + t5.to(MINUTE));

        Velocity v5 = new Velocity(55, MPH);
        System.out.println(v5 + " = " + v5.format("%4.1f %s", KMH));
        System.out.println((v5.mult(new Time(30, MINUTE)).to(MILE)));
    }
}
