package tasks.second.test.testJava;

import org.junit.Test;
import tasks.second.java.main.ThreeDiagonalMatrix;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ThreeDiagonalMatrixTest {
    @Test
    public void createMatrixFromFile(){
        //given
        ThreeDiagonalMatrix matrix = new ThreeDiagonalMatrix(new File("src/tasks/second/test/testResources/testMatrix.txt"));
        //when
        List<Double> expectedA = new ArrayList<>(Arrays.asList(1.0, 3.0, 1.0, 1.0));
        List<Double> expectedC = new ArrayList<>(Arrays.asList(2.0, 2.0, 4.0, 2.0, 3.0));
        List<Double> expectedB = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0));
        List<Double> expectedF = new ArrayList<>(Arrays.asList(1.0, 2.0, 1.0, 1.0, 1.0));
        //then
        assertEquals(expectedA, matrix.getA());
        assertEquals(expectedC, matrix.getC());
        assertEquals(expectedB, matrix.getB());
        assertEquals(expectedF, matrix.getF());
    }

}