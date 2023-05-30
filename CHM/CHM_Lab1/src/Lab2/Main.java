package Lab2;

public class Main {
    public static void main(String[] args) {
        int n = 3;
        Helper helper = new Helper();
        float[][] randMatrix = helper.GetRandomMatrix(n);
        float[] Vector = helper.GetVectorB(n);
        System.out.println("FOR RANDOM MATRIX:");
        System.out.println("Matrix:");
        helper.printMatrix(randMatrix);
        System.out.println("Vector:");
        helper.printVector(Vector);
        System.out.println("\n\nResult:");
        System.out.println("Gauss: ");
        new GaussMethod(randMatrix, Vector, n);
        System.out.println("\nJacobi: ");
        new JacobiMethod(randMatrix, Vector, n);
        System.out.println("\nZedel: ");
        new ZedelMethod(randMatrix, Vector, n);

        float[][] HilbertMatrix = helper.GetHilbertMatrix(n);
        System.out.println("\n\nFOR HILBERT MATRIX:");
        System.out.println("Matrix:");
        helper.printMatrix(HilbertMatrix);
        System.out.println("Vector:");
        helper.printVector(Vector);
        System.out.println("\n\nResult:");
        System.out.println("Gauss: ");
        new GaussMethod(HilbertMatrix, Vector, n);
        System.out.println("\nJacobi: ");
        new JacobiMethod(HilbertMatrix, Vector, n);
        System.out.println("\nZedel: ");
        new ZedelMethod(HilbertMatrix, Vector, n);

    }
}
