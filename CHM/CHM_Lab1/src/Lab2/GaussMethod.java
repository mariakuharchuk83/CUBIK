package Lab2;

public class GaussMethod {
 private Helper helper;

 private int n;
 private float[][] Matrix;
 private float[] VectorB;

 public GaussMethod(float[][] Matrix, float[] Vector,  int n){
   this.Matrix = Matrix;
   this.VectorB = Vector;
   this.n = n;
   GetResult();
 }


 public void GetResult(){
     for (int p = 0; p < n; p++) {

         int max = p;
         for (int i = p + 1; i < n; i++) {
             if (Math.abs(Matrix[i][p]) > Math.abs(Matrix[max][p])) {
                 max = i;
             }
         }
         float[] temp = Matrix[p];
         Matrix[p] = Matrix[max];
         Matrix[max] = temp;
         float t = VectorB[p];
         VectorB[p] = VectorB[max];
         VectorB[max] = t;

         if (Math.abs(Matrix[p][p]) <= 1e-10) {
             System.out.println("NO");
             return;
         }

         for (int i = p + 1; i < n; i++) {
             float alpha = Matrix[i][p] / Matrix[p][p];
             VectorB[i] -= alpha * VectorB[p];
             for (int j = p; j < n; j++) {
                 Matrix[i][j] -= alpha * Matrix[p][j];
             }
         }
     }

     // Обратный проход

     float[] x = new float[n];
     for (int i = n - 1; i >= 0; i--) {
         float sum = 0.0F;
         for (int j = i + 1; j < n; j++) {
             sum += Matrix[i][j] * x[j];
         }
         x[i] = (VectorB[i] - sum) / Matrix[i][i];
     }

     /* Вывод результатов */

     for (int i = 0; i < n; i++) {
         System.out.print("X = " + x[i] + " ");
     }
 }


}
