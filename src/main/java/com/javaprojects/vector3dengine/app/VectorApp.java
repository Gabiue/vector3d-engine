package com.javaprojects.vector3dengine.app;

import com.javaprojects.vector3dengine.graphics.engine.Camera3D;
import com.javaprojects.vector3dengine.graphics.engine.CubeRenderer;
import com.javaprojects.vector3dengine.mathlib.matrix.Matrix4x4;
import com.javaprojects.vector3dengine.mathlib.vector.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class VectorApp extends Application {

    @Override
    public void start(Stage primaryStage)throws Exception {
        Canvas canvas = new Canvas(800, 600);
        VBox container = new VBox(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Scene scene = new Scene(container, 800, 600);
        Camera3D camera = new Camera3D(400,5);
        CubeRenderer renderer = new CubeRenderer(camera);

        //PRIMARY STAGE
        primaryStage.setTitle("Vector 3D Engine");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        AnimationTimer timer = new AnimationTimer() {
            private long startTime = System.nanoTime();

            @Override
            public void handle(long now) {
                double time = (now - startTime) / 1_000_000_000.0; // converter para segundos

                // Limpar tela
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, 800, 600);

                // Configurar linha
                gc.setStroke(Color.WHITE);
                gc.setLineWidth(5);

                // Rotacionar e renderizar
                renderer.rotateWithDifferentSpeeds(time);// velocidades diferentes nos 3 eixos
                renderer.render(gc);
            }
        };
        timer.start();

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
