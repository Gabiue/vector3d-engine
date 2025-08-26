package com.javaprojects.vector3dengine.app;
import com.javaprojects.vector3dengine.mathlib.vector.Vector2D;
import com.javaprojects.vector3dengine.mathlib.matrix.Matrix3x3;

public class Teste {
    public static void main(String[] args) {

        Vector2D v = new Vector2D(1, 0);
        Matrix3x3 rot90 = Matrix3x3.rotation(Math.PI/2); // 90 graus
        Vector2D vRotacionado = rot90.transform(v);
        System.out.println(vRotacionado); // deve dar aproximadamente (0, 1)


    }

}
