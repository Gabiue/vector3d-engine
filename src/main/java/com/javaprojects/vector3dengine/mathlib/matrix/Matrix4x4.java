package com.javaprojects.vector3dengine.mathlib.matrix;

import com.javaprojects.vector3dengine.mathlib.vector.Vector3D;

public class Matrix4x4 {
    private double[][] matrix = new double[4][4];

    public Matrix4x4() {
    }

    public static Matrix4x4 identity() {
        Matrix4x4 identity = new Matrix4x4();
        for (int i = 0; i < 4; i++) {
            identity.matrix[i][i] = 1.0;
        }
        return identity;
    }

    public static Matrix4x4 rotationX(double angleInRadians) {
        Matrix4x4 rotation = new Matrix4x4();
        double cos = Math.cos(angleInRadians);
        double sin = Math.sin(angleInRadians);

        rotation.matrix[0][0] = 1.0;
        rotation.matrix[0][1] = 0.0;
        rotation.matrix[0][2] = 0.0;
        rotation.matrix[0][3] = 0.0;
        rotation.matrix[1][0] = 0.0;
        rotation.matrix[1][1] = cos;
        rotation.matrix[1][2] = -sin;
        rotation.matrix[1][3] = 0.0;
        rotation.matrix[2][0] = 0.0;
        rotation.matrix[2][1] = sin;
        rotation.matrix[2][2] = cos;
        rotation.matrix[2][3] = 0.0;
        rotation.matrix[3][0] = 0.0;
        rotation.matrix[3][1] = 0.0;
        rotation.matrix[3][2] = 0.0;
        rotation.matrix[3][3] = 1.0;

        return rotation;
    }

    public static Matrix4x4 rotationY(double angleInRadians) {
        Matrix4x4 rotation = new Matrix4x4();
        double cos = Math.cos(angleInRadians);
        double sin = Math.sin(angleInRadians);

        rotation.matrix[0][0] = cos;
        rotation.matrix[0][1] = 0.0;
        rotation.matrix[0][2] = sin;
        rotation.matrix[0][3] = 0.0;
        rotation.matrix[1][0] = 0.0;
        rotation.matrix[1][1] = 1.0;
        rotation.matrix[1][2] = 0.0;
        rotation.matrix[1][3] = 0.0;
        rotation.matrix[2][0] = -sin;
        rotation.matrix[2][1] = 0.0;
        rotation.matrix[2][2] = cos;
        rotation.matrix[2][3] = 0.0;
        rotation.matrix[3][0] = 0.0;
        rotation.matrix[3][1] = 0.0;
        rotation.matrix[3][2] = 0.0;
        rotation.matrix[3][3] = 1.0;

        return rotation;
    }

    public static Matrix4x4 rotationZ(double angleInRadians) {
        Matrix4x4 rotation = new Matrix4x4();
        double cos = Math.cos(angleInRadians);
        double sin = Math.sin(angleInRadians);
        rotation.matrix[0][0] = cos;
        rotation.matrix[0][1] = -sin;
        rotation.matrix[0][2] = 0.0;
        rotation.matrix[0][3] = 0.0;
        rotation.matrix[1][0] = sin;
        rotation.matrix[1][1] = cos;
        rotation.matrix[1][2] = 0.0;
        rotation.matrix[1][3] = 0.0;
        rotation.matrix[2][0] = 0.0;
        rotation.matrix[2][1] = 0.0;
        rotation.matrix[2][2] = 1.0;
        rotation.matrix[2][3] = 0.0;
        rotation.matrix[3][0] = 0.0;
        rotation.matrix[3][1] = 0.0;
        rotation.matrix[3][2] = 0.0;
        rotation.matrix[3][3] = 1.0;
        return rotation;
    }

    public double getValue(int row, int col) {
        return matrix[row][col];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] row : matrix) {
            sb.append("[");
            for (double val : row) {
                sb.append(String.format("%8.3f", val)).append(" ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
    public Matrix4x4 scale(double scaleX, double scaleY, double scaleZ) {
        Matrix4x4 scale = new Matrix4x4();

        scale.matrix[0][0] = scaleX;
        scale.matrix[0][1] = 0.0;
        scale.matrix[0][2] = 0.0;
        scale.matrix[0][3] = 0.0;
        scale.matrix[1][0] = 0.0;
        scale.matrix[1][1] = scaleY;
        scale.matrix[1][2] = 0.0;
        scale.matrix[1][3] = 0.0;
        scale.matrix[2][0] = 0.0;
        scale.matrix[2][1] = 0.0;
        scale.matrix[2][2] = scaleZ;
        scale.matrix[2][3] = 0.0;
        scale.matrix[3][0] = 0.0;
        scale.matrix[3][1] = 0.0;
        scale.matrix[3][2] = 0.0;
        scale.matrix[3][3] = 1.0;

        return scale;
    }
    public Matrix4x4 translation(double translateX, double translateY, double translateZ) {
        Matrix4x4 translation = new Matrix4x4();

        translation.matrix[0][0] = 1.0;
        translation.matrix[0][1] = 0.0;
        translation.matrix[0][2] = 0.0;
        translation.matrix[0][3] = translateX;
        translation.matrix[1][0] = 0.0;
        translation.matrix[1][1] = 1.0;
        translation.matrix[1][2] = 0.0;
        translation.matrix[1][3] = translateY;
        translation.matrix[2][0] = 0.0;
        translation.matrix[2][1] = 0.0;
        translation.matrix[2][2] = 1.0;
        translation.matrix[2][3] = translateZ;
        translation.matrix[3][0] = 0.0;
        translation.matrix[3][1] = 0.0;
        translation.matrix[3][2] = 0.0;
        translation.matrix[3][3] = 1.0;

        return translation;
    }

    public Matrix4x4 multiply(Matrix4x4 other) {
        Matrix4x4 result = new Matrix4x4();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                result.matrix[row][col] = this.matrix[row][0] * other.matrix[0][col] +
                        this.matrix[row][1] * other.matrix[1][col] +
                        this.matrix[row][2] * other.matrix[2][col] +
                        this.matrix[row][3] * other.matrix[3][col];
            }
        }
        return result;
    }

    public Vector3D transform(Vector3D vec) {
        double x = vec.getX();
        double y = vec.getY();
        double z = vec.getZ();
        double w = 1.0; // Homogeneous coordinate

        double newX = matrix[0][0] * x + matrix[0][1] * y + matrix[0][2] * z + matrix[0][3] * w;
        double newY = matrix[1][0] * x + matrix[1][1] * y + matrix[1][2] * z + matrix[1][3] * w;
        double newZ = matrix[2][0] * x + matrix[2][1] * y + matrix[2][2] * z + matrix[2][3] * w;
        double newW = matrix[3][0] * x + matrix[3][1] * y + matrix[3][2] * z + matrix[3][3] * w;

        if (newW != 0) {
            newX /= newW;
            newY /= newW;
            newZ /= newW;
        }

        return new Vector3D(newX, newY, newZ);
    }


}
