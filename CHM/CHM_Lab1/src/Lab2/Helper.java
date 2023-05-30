package Lab2;

import java.util.Random;

public class Helper {
    private final Random random = new Random();
    public Helper(){}

    public float[] GetVectorB(int n) {
        float B[] = new float[n];
        for (int i = 0; i < n; i++) {
            B[i] = random.nextInt(10);
        }
        return B;
    }
    public float[][] GetRandomMatrix(int n){
        float M[][] = new float[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = random.nextInt(10);
            }
        }
        return  M;
    }

    //Матриця Гільберта — це квадратна матриця, кожен елемент якої є одиничним дробом.
    //Властивості:
    //Це симетрична матриця.
    //Його визначальне значення завжди додатне.
    public float[][] GetHilbertMatrix(int n){
        float H[][] = new float[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                H[i][j] = (float)1.0 /
                        ((i + 1) + (j + 1) -
                                (float)1.0);
            }
        }
        return H;
    }

    public void printMatrix(float[][] Matrix){
        for(int i=0;i<Matrix.length;i++){
            for(int j = 0; j < Matrix[i].length;j++){
                System.out.print(Matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void printVector(float[] Vector){
        for(int i=0;i<Vector.length;i++){
            System.out.print(Vector[i] + " ");
        }
    }
}
