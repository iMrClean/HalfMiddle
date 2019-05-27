package sample.function;

public class HalfMiddle {

    private static double eps = 0.0001;

    public static double bisection(Function1 func, double a, double b, double x) {
        double c = (a + b) / 2;

        if (func.f(x, a) * func.f(x, c) < 0)
            b = c;
        else
            a = c;
        if (Math.abs(b - a) > eps && func.f(x, c) != 0 && func.f(x, a) != 0)
            return bisection(func, a, b, x);
        else
            return c;
    }

    public static double bisection(Function2 func, double a, double b, double x, double z) {
        double c = (a + b) / 2;

        if (func.f(x, a, z) * func.f(x, c, z) < 0)
            b = c;
        else
            a = c;
        if (Math.abs(b - a) > eps && func.f(x, c, z) != 0 && func.f(x, a, z) != 0)
            return bisection(func, a, b, x, z);
        else
            return c;
    }
}
