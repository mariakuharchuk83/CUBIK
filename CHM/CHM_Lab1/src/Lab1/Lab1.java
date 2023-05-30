package Lab1;


public class Lab1 {

    private Double a;
    private Double b;

    private static final Double EPS = 0.0001;

    public Lab1(Double a, Double b){
        this.a = a;
        this.b = b;
    }

    private Double F(Double x){
        return Math.pow(x,2) -5*x + 4;
    }

    private Double FDerivative(Double x){
        return Double.valueOf(2*x-5);
    }


    public Double Dichotomy(){
        Double c = Double.valueOf(0);
        int i=0;
        while (Math.abs(a - b) > 2*EPS) {
            c = (a + b) / 2;
            //умова збіжності
            if (F(c) * F(a) == 0 || F(c) * F(b) == 0) {
                return c;
            } else if (F(c) * F(c) > 0) {
                a = c;
            } else {
                b = c;
            }
        }
        return c;
    }

    public Double Relaxation(){
        double m1 = 1;
        double M1 = 5;
        double tau = -2/(m1+M1);
        double x1 = a;
        double x = Double.MAX_VALUE;
        while (Math.abs(x - x1) >= EPS) {
            x = x1;
            x1 = x + tau * F(x);
        }
        return x;
    }

    public Double Newton(){
        Double x = b;
        double h = F(x) / FDerivative(x);
        int i=0;
        while (Math.abs(h) >= EPS)
        {
            h = F(x) / FDerivative(x);
            i++;
            // x(i+1) = x(i) - f(x) / f'(x)
            x = x - h;
        }
        System.out.println("i= "+i);
        return x;
    }
}
