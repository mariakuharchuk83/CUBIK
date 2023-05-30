package Lab2;

public class JacobiMethod {
    private double EPS = 0.000001;
    private int n;
    private float[][] Matrix;
    private float[] VectorB;
    public JacobiMethod(float[][] Matrix, float[] Vector,  int n){
        this.Matrix = Matrix;
        this.VectorB = Vector;
        this.n = n;
        GetResult();
    }

    public void GetResult(){
        float[] X0 = new float[n];
        float[] X = new float[n];
        for (int i = 0; i < n; i++) {
            X0[i] = VectorB[i] / Matrix[i][i];
        }
        double norm;
        int k=0; // Записать количество циклов
        do{
            for(int i = 0; i < n; i++) {
                double sum = 0;
                for(int j = 0; j < n; j++) {
                    if(j == i) continue;
                    sum += Matrix[i][j] * X0[j];
                }
                X[i] = (float) ((VectorB[i] - sum) / Matrix[i][i]);
            }
            norm = normCalc(X0, X, n);
            for(int i = 0; i < n; i++) {
                X0[i] = X[i];
            }

            k++;
            if(k>100){
                System.out.println("Итерация не удалась! (Может быть, функция не сходится");
                return ;
            }
        }while( norm > EPS);

        for (int i = 0; i < n; i++) {
            System.out.print("X = " + X0[i] + " ");
        }
    }

    private double normCalc(float[] x1, float[] x2, int n) {
        double sum = 0;
        for(int i = 0; i < n; i++) {
            sum += Math.abs(x1[i] - x2[i]);
        }
        return sum;
    }
}
