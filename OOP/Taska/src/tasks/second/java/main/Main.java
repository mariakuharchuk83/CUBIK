package tasks.second.java.main;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MatrixValidator matrixValidator = new MatrixValidator();
        ThreeDiagonalMatrix threeDiagonalMatrix = new ThreeDiagonalMatrix(new File("src/tasks/second/java/resources/matrix.txt"));
        Solver solver = new Solver(matrixValidator);
        List<Double> res = solver.solve(threeDiagonalMatrix);
        if (res != null) {
            System.out.println(res);
        }
    }
}
