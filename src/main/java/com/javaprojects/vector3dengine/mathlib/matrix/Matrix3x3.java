package com.javaprojects.vector3dengine.mathlib.matrix;

import com.javaprojects.vector3dengine.mathlib.vector.Vector2D;

import java.util.Arrays;

public class Matrix3x3 {
    private double [][] matrix = new double[3][3];

    public Matrix3x3() {
    }



    public double getValue(int row, int col){
        return matrix[row][col];
    }

    public static Matrix3x3 identity(){
        Matrix3x3 identity = new Matrix3x3();
        for(int i = 0; i<3; i++){
            identity.matrix[i][i] = 1.0;
        }
        return identity;
    }

    public Matrix3x3 multiply(Matrix3x3 other){
        Matrix3x3 result = new Matrix3x3();
        for(int row = 0; row<3; row++){
            for(int col = 0; col<3; col++){
                for(int k = 0; k<3; k++){
                    result.matrix[row][col] += this.matrix[row][k] * other.matrix[k][col];
                }
            }
        }
        return result;
    }
    public Vector2D transform(Vector2D vec){
        double x = vec.getX();
        double y = vec.getY();
        double z = 1.0; // Homogeneous coordinate

        double newX = matrix[0][0] * x + matrix[0][1] * y + matrix[0][2] * z;
        double newY = matrix[1][0] * x + matrix[1][1] * y + matrix[1][2] * z;
        double newZ = matrix[2][0] * x + matrix[2][1] * y + matrix[2][2] * z;

        if (newZ != 0) {
            newX /= newZ;
            newY /= newZ;
        }

        return new Vector2D(newX, newY);
    }

    public static Matrix3x3 rotation(double angleInRadians){
        Matrix3x3 rotation = new Matrix3x3();
        double cos = Math.cos(angleInRadians);
        double sin = Math.sin(angleInRadians);

        rotation.matrix[0][0] = cos;  rotation.matrix[0][1] = -sin; rotation.matrix[0][2] = 0.0;
        rotation.matrix[1][0] = sin;  rotation.matrix[1][1] = cos;  rotation.matrix[1][2] = 0.0;
        rotation.matrix[2][0] = 0.0;  rotation.matrix[2][1] = 0.0;  rotation.matrix[2][2] = 1.0;

        return rotation;
    }

    public static Matrix3x3 scale(double scaleX, double scaleY){
        Matrix3x3 scale = new Matrix3x3();

        scale.matrix[0][0] = scaleX; scale.matrix[0][1] = 0.0;   scale.matrix[0][2] = 0.0;
        scale.matrix[1][0] = 0.0;   scale.matrix[1][1] = scaleY; scale.matrix[1][2] = 0.0;
        scale.matrix[2][0] = 0.0;   scale.matrix[2][1] = 0.0;   scale.matrix[2][2] = 1.0;

        return scale;
    }
    public static Matrix3x3 translation(double translateX, double translateY){
        Matrix3x3 translation = new Matrix3x3();

        translation.matrix[0][0] = 1.0; translation.matrix[0][1] = 0.0; translation.matrix[0][2] = translateX;
        translation.matrix[1][0] = 0.0; translation.matrix[1][1] = 1.0; translation.matrix[1][2] = translateY;
        translation.matrix[2][0] = 0.0; translation.matrix[2][1] = 0.0; translation.matrix[2][2] = 1.0;

        return translation;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int row = 0 ; row<3; row++){
            sb.append("[ ");
            for(int col = 0; col<3; col++){
                sb.append(String.format("%6.2f", matrix[row][col]));
                if(col < 2) sb.append(", ");
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }
}
