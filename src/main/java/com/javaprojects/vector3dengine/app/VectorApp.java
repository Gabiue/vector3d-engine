package com.javaprojects.vector3dengine.app;

import com.javaprojects.vector3dengine.mathlib.vector.Vector2D;
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

        //PRIMARY STAGE
        primaryStage.setTitle("Vector 3D Engine");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        canvas.setOnMouseMoved(mouseEvent -> {
            double centroX = canvas.getWidth() / 2;
            double centroY = canvas.getHeight() / 2;
            double mouseX = mouseEvent.getX();
            double mouseY = mouseEvent.getY();

            Vector2D vetorMouse = new Vector2D(mouseX - centroX, centroY - mouseY);

            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, 800, 600);

            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);

            gc.strokeLine(centroX, centroY, mouseX, mouseY);
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
