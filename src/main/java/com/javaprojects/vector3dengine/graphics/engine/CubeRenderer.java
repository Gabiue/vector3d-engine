package com.javaprojects.vector3dengine.graphics.engine;

import com.javaprojects.vector3dengine.graphics.objects.Cube;
import com.javaprojects.vector3dengine.mathlib.matrix.Matrix4x4;
import com.javaprojects.vector3dengine.mathlib.vector.Vector2D;
import com.javaprojects.vector3dengine.mathlib.vector.Vector3D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CubeRenderer{
    Camera3D camera;
    Cube[] cubes;
    Matrix4x4[] rotationMatrices;

    public CubeRenderer(Camera3D camera) {
        this.camera = camera;
        this.cubes = new Cube[5];
        this.rotationMatrices = new Matrix4x4[5];

        cubes[0] = new Cube(3);
        cubes[1] = new Cube(2);
        cubes[2] = new Cube(1);
        cubes[3] = new Cube(0.5);
        cubes[4] = new Cube(0.25);

        for(int i = 0; i < 5; i++) {
            rotationMatrices[i] = Matrix4x4.identity();
        }
    }
    public void render(GraphicsContext gc) {
        for (int cubeIndex = 0; cubeIndex < cubes.length; cubeIndex++) {
            if(cubeIndex == 0) {
                gc.setStroke(Color.web("#8B00FF")); // roxo elétrico
            } else if(cubeIndex == 1) {
                gc.setStroke(Color.web("#FF0080")); // magenta neon
            } else if(cubeIndex == 2) {
                gc.setStroke(Color.web("#00FFFF")); // ciano brilhante
            } else if(cubeIndex == 3) {
                gc.setStroke(Color.web("#FF4500")); // laranja fogo
            } else {
                gc.setStroke(Color.web("#00FF41")); // verde matrix
            }
            Vector3D[] transformedVertices = new Vector3D[8];
            for (int i = 0; i < 8; i++) {
                transformedVertices[i] = rotationMatrices[cubeIndex].transform(cubes[cubeIndex].getVertices()[i]);
            }
            Vector2D[] projectedVertices = new Vector2D[8];
            for (int i = 0; i < 8; i++) {
                projectedVertices[i] = camera.project(transformedVertices[i]);
            }
            for(int[] edge : cubes[cubeIndex].getEdges()) {
                Vector2D start = projectedVertices[edge[0]];
                Vector2D end = projectedVertices[edge[1]];

                double startX = 400 + start.getX();
                double startY = 300 - start.getY();
                double endX = 400 + end.getX();
                double endY = 300 - end.getY();

                Color currentColor = getCurrentColor(cubeIndex);

                gc.setLineWidth(20);
                gc.setStroke(currentColor.deriveColor(0, 1, 1, 0.01));
                gc.strokeLine(startX, startY, endX, endY);

                gc.setLineWidth(16);
                gc.setStroke(currentColor.deriveColor(0, 1, 1, 0.08));
                gc.strokeLine(startX, startY, endX, endY);

                gc.setLineWidth(6);
                gc.setStroke(currentColor.deriveColor(0, 1, 1, 0.2));
                gc.strokeLine(startX, startY, endX, endY);

                // 2. Linha média semi-transparente
                gc.setLineWidth(3);
                gc.setStroke(currentColor.deriveColor(0, 1, 1, 0.6));
                gc.strokeLine(startX, startY, endX, endY);

                // 3. Linha fina sólida (core)
                gc.setLineWidth(1);
                gc.setStroke(currentColor);
                gc.strokeLine(startX, startY, endX, endY);


            }

        }
    }
    public void rotate(double angleX, double angleY, double angleZ) {
        for(int i = 0; i < rotationMatrices.length; i++) {
            Matrix4x4 rotX, rotY, rotZ;

            if(i == 0 ){
                rotX = Matrix4x4.rotationX(angleX);
                rotY = Matrix4x4.rotationY(angleY);
                rotZ = Matrix4x4.rotationZ(angleZ);
            } else if(i == 1) {
                // Cubo 1: X oposto
                rotX = Matrix4x4.rotationX(-angleX);
                rotY = Matrix4x4.rotationY(angleY);
                rotZ = Matrix4x4.rotationZ(angleZ);
            }
            else if(i == 2) {
                // Cubo 2: Y oposto
                rotX = Matrix4x4.rotationX(angleX);
                rotY = Matrix4x4.rotationY(-angleY);
                rotZ = Matrix4x4.rotationZ(angleZ);
            }
            else if(i == 3) {
                // Cubo 3: Z oposto
                rotX = Matrix4x4.rotationX(angleX);
                rotY = Matrix4x4.rotationY(angleY);
                rotZ = Matrix4x4.rotationZ(-angleZ);
            }
            else {
                // Cubo 4: Todos opostos
                rotX = Matrix4x4.rotationX(-angleX);
                rotY = Matrix4x4.rotationY(-angleY);
                rotZ = Matrix4x4.rotationZ(-angleZ);
            }
            rotationMatrices[i] = rotX.multiply(rotY).multiply(rotZ);
        }
    }

    public void rotateWithDifferentSpeeds(double time){
        for(int i = 0; i < rotationMatrices.length; i++) {
            double speedFactor = 0.5 + i * 0.3; // Fator de velocidade diferente para cada cubo
            double pulseSpeed = 1.0 + i * 0.3; // Velocidade de pulsação diferente para cada cubo
            double scale = 1.0 + 0.2 * Math.sin(time * pulseSpeed); // Escala pulsante

            double angleX = time * speedFactor;
            double angleY = time * speedFactor * 1.5;
            double angleZ = time * speedFactor * 0.8;
            Matrix4x4 rotX, rotY, rotZ;

            if(i==0){
                rotX = Matrix4x4.rotationX(angleX);
                rotY = Matrix4x4.rotationY(angleY);
                rotZ = Matrix4x4.rotationZ(angleZ);
            }
            else if(i==1){
                rotX = Matrix4x4.rotationX(-angleX);
                rotY = Matrix4x4.rotationY(angleY);
                rotZ = Matrix4x4.rotationZ(angleZ);
            }
            else if(i==2){
                rotX = Matrix4x4.rotationX(angleX);
                rotY = Matrix4x4.rotationY(-angleY);
                rotZ = Matrix4x4.rotationZ(angleZ);
            }
            else if(i==3){
                rotX = Matrix4x4.rotationX(angleX);
                rotY = Matrix4x4.rotationY(angleY);
                rotZ = Matrix4x4.rotationZ(-angleZ);
            }
            else{
                rotX = Matrix4x4.rotationX(-angleX);
                rotY = Matrix4x4.rotationY(-angleY);
                rotZ = Matrix4x4.rotationZ(-angleZ);
            }
            rotationMatrices[i] = rotX.multiply(rotY).multiply(rotZ);
        }
    }
    private Color getCurrentColor(int cubeIndex) {
        if(cubeIndex == 0) return Color.web("#8B00FF");
        else if(cubeIndex == 1) return Color.web("#FF0080");
        else if(cubeIndex == 2) return Color.web("#00FFFF");
        else if(cubeIndex == 3) return Color.web("#FF4500");
        else return Color.web("#00FF41");
    }
}
