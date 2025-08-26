package com.javaprojects.vector3dengine.mathlib.vector;

public class Vector3D {
    private double x;
    private double y;
    private double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getMagnitude() {
       return Math.sqrt(x * x + y * y + z * z);
    }
    public Vector3D normalize(){
        double magnitude = getMagnitude();
        if (magnitude == 0) {
            throw new ArithmeticException("Cannot normalize a zero vector");
        }
        return new Vector3D(x / magnitude, y / magnitude, z / magnitude);
    }

    public Vector3D add(Vector3D other){
        return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }
    public Vector3D subtract(Vector3D other){
        return new Vector3D(this.x - other.x, this.y - other.y, this.z - other.z);
    }
    public Vector3D scale(double scalar){
        return new Vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
    }
    public double dot(Vector3D other){
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }
    public Vector3D crossProduct(Vector3D other) {
        double crossX = this.y * other.z - this.z * other.y;
        double crossY = this.z * other.x - this.x * other.z;
        double crossZ = this.x * other.y - this.y * other.x;
        return new Vector3D(crossX, crossY, crossZ);
    }

    @Override
    public String toString() {
        return String.format("Vector3D{x=%.2f, y=%.2f, z=%.2f}", x, y, z);
    }
}
