package com.javaprojects.vector3dengine.app;
import com.javaprojects.vector3dengine.mathlib.vector.Vector2D;

public class Teste {
    public static void main(String[] args) {

        Vector2D vetor = new Vector2D(3,4);
        System.out.println("Vetor: " + vetor);
        System.out.println("Magnitude: " + vetor.getMagnitude());
        System.out.println("Ângulo: " +vetor.getAngle() );
        System.out.println("Vetor Normalizado: " + vetor.normalize());

        Vector2D outroVetor = new Vector2D(1,2);
        System.out.println("Outro Vetor: " + outroVetor);
        System.out.println("Soma: " + vetor.add(outroVetor));
        System.out.println("Subtração: " + vetor.subtract(outroVetor));
        System.out.println("Escala (2x): " + vetor.scale(2));
        System.out.println("Produto Escalar: " + vetor.dot(outroVetor));
    }
}
