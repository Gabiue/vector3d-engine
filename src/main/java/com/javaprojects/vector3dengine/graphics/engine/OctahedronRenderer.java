package com.javaprojects.vector3dengine.graphics.engine;

import com.javaprojects.vector3dengine.graphics.objects.Octahedron;
import com.javaprojects.vector3dengine.mathlib.matrix.Matrix4x4;
import com.javaprojects.vector3dengine.mathlib.vector.Vector2D;
import com.javaprojects.vector3dengine.mathlib.vector.Vector3D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class OctahedronRenderer {
    Camera3D camera;
    Octahedron octahedron;
    Matrix4x4 rotationMatrix;

    public OctahedronRenderer(Camera3D camera) {
        this.camera = camera;
        this.octahedron = new Octahedron(1.5); // Tamanho médio
        this.rotationMatrix = Matrix4x4.identity();
    }

    public void render(GraphicsContext gc) {
        Vector3D[] transformedVertices = new Vector3D[6];
        for(int i = 0; i < 6; i++) {
            transformedVertices[i] = rotationMatrix.transform(octahedron.getVertices()[i]);
        }

        Vector2D[] projectedVertices = new Vector2D[6];
        for(int i = 0; i < 6; i++) {
            projectedVertices[i] = camera.project(transformedVertices[i]);
        }

        for(int[] edge : octahedron.getEdges()) {
            Vector2D start = projectedVertices[edge[0]];
            Vector2D end = projectedVertices[edge[1]];

            double startX = 400 + start.getX();
            double startY = 300 - start.getY();
            double endX = 400 + end.getX();
            double endY = 300 - end.getY();

            // Cor dourada para diferenciá-lo dos cubos
            Color color = Color.web("#FFD700");

            // Efeito glow
            gc.setLineWidth(8);
            gc.setStroke(color.deriveColor(0, 1, 1, 0.2));
            gc.strokeLine(startX, startY, endX, endY);

            gc.setLineWidth(3);
            gc.setStroke(color.deriveColor(0, 1, 1, 0.6));
            gc.strokeLine(startX, startY, endX, endY);

            gc.setLineWidth(1);
            gc.setStroke(color);
            gc.strokeLine(startX, startY, endX, endY);
        }
    }

    public void rotate(double angleX, double angleY, double angleZ) {
        Matrix4x4 rotX = Matrix4x4.rotationX(angleX);
        Matrix4x4 rotY = Matrix4x4.rotationY(angleY);
        Matrix4x4 rotZ = Matrix4x4.rotationZ(angleZ);
        rotationMatrix = rotX.multiply(rotY).multiply(rotZ);
    }
}