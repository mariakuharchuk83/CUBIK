package Lab1;

import Lab1.Lab1;

public class Main {

    public static void main(String[] args) {
        Lab1 lab1 = new Lab1(2.0, 6.0);
        System.out.println("Dichotomy x = " + lab1.Dichotomy());

        System.out.println("\nRelaxation x = " +lab1.Relaxation());
        System.out.println("\nNewton x = " + lab1.Newton()+"\n");
    }
}
