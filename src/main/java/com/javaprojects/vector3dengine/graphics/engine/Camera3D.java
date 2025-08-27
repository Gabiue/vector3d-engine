package com.javaprojects.vector3dengine.graphics.engine;

import com.javaprojects.vector3dengine.mathlib.vector.Vector2D;
import com.javaprojects.vector3dengine.mathlib.vector.Vector3D;

public class Camera3D{
    private double focalLength;
    private double viewerDistance;

    public Camera3D(double focalLength, double viewerDistance) {
        this.focalLength = focalLength;
        this.viewerDistance = viewerDistance;
    }

    public Vector2D project (Vector3D point3D){
        double screenX = (focalLength * point3D.getX()) / (viewerDistance + point3D.getZ());
        double screenY = (focalLength * point3D.getY()) / (viewerDistance + point3D.getZ());
        return new Vector2D(screenX, screenY);
    }
}
