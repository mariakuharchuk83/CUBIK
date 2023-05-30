package Lab4;

public class Functions {


    public static double f(double x) {
        return x * x * x - 3.5 * x * x + 0.5 * x + 5;
    }

    public static double prf(double x) {
        return 3 * x * x - 7 * x + 0.5;
    }

    // обычная формула
    public static double Newton(double x, double e) {
        double xn = x - f(x) / prf(x);

        if (Math.abs(xn - x) < e) {
            return xn;
        }
        return Newton(xn, e);
    }

    // упрощённая формула
    public static double NewtonModify(double x, double e) {
        double v = f(x) / prf(x);

        if (Math.abs(v) < e) {
            return x - v;
        }
        return NewtonModify(x - v, e);
    }
}
