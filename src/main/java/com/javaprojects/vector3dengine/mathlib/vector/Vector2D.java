package com.javaprojects.vector3dengine.mathlib.vector;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public double getMagnitude() {
       return Math.sqrt(x * x + y * y);
    }

    public double getAngle(){
        return Math.atan2(y, x);
    }

    public Vector2D normalize(){
        double magnitude = getMagnitude();
        if (magnitude == 0) {
            throw new ArithmeticException("Cannot normalize a zero vector");
        }
        return new Vector2D(x / magnitude, y / magnitude);
    }

    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y + other.y);
    }
    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x - other.x, this.y - other.y);
    }
    public Vector2D scale(double scalar){
        return new Vector2D(this.x * scalar, this.y * scalar);
    }
    public double dot(Vector2D other){
        return this.x * other.x + this.y * other.y;
    }
}
