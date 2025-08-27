package com.javaprojects.vector3dengine.graphics.engine;

import com.javaprojects.vector3dengine.graphics.objects.Cube;
import com.javaprojects.vector3dengine.mathlib.matrix.Matrix4x4;
import com.javaprojects.vector3dengine.mathlib.vector.Vector2D;
import com.javaprojects.vector3dengine.mathlib.vector.Vector3D;
import javafx.scene.canvas.GraphicsContext;

public class CubeRenderer{
    Camera3D camera;
    Cube cube;
    Matrix4x4 rotationMatrix;

    public CubeRenderer(Camera3D camera) {
        this.camera = camera;
        this.cube = new Cube();
        this.rotationMatrix = Matrix4x4.identity();
    }

    public void render(GraphicsContext gc){
        Vector3D[] transformedVertices = new Vector3D[8];
        for(int i = 0; i < 8; i++) {
            transformedVertices[i] = rotationMatrix.transform(cube.getVertices()[i]);
        }
        Vector2D[] projectedVertices = new Vector2D[8];
        for(int i = 0; i < 8; i++) {
            projectedVertices[i] = camera.project(transformedVertices[i]);
        }
        for(int[] edge : cube.getEdges()) {
            Vector2D start = projectedVertices[edge[0]];
            Vector2D end = projectedVertices[edge[1]];

            // Converter para coordenadas de tela (centro = 400,300)
            double startX = 400 + start.getX();
            double startY = 300 - start.getY();
            double endX = 400 + end.getX();
            double endY = 300 - end.getY();

            gc.strokeLine(startX, startY, endX, endY);
        }
    }
    public void rotate(double angleX, double angleY, double angleZ){
        Matrix4x4 rotX = Matrix4x4.rotationX(angleX);
        Matrix4x4 rotY = Matrix4x4.rotationY(angleY);
        Matrix4x4 rotZ = Matrix4x4.rotationZ(angleZ);
        rotationMatrix = rotX.multiply(rotY).multiply(rotZ);
    }

}
