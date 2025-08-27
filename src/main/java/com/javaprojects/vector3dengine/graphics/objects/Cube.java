package com.javaprojects.vector3dengine.graphics.objects;

import com.javaprojects.vector3dengine.mathlib.vector.Vector3D;

public class Cube {
    private Vector3D vertices[] = new Vector3D[8];
    private int[][] edges = new int [12][2];

    public Cube (){
        vertices[0] = new Vector3D(-0.5, -0.5, -0.5);
        vertices[1] = new Vector3D(0.5, -0.5, -0.5);
        vertices[2] = new Vector3D(0.5, 0.5, -0.5);
        vertices[3] = new Vector3D(-0.5, 0.5, -0.5);
        vertices[4] = new Vector3D(-0.5, -0.5, 0.5);
        vertices[5] = new Vector3D(0.5, -0.5, 0.5);
        vertices[6] = new Vector3D(0.5, 0.5, 0.5);
        vertices[7] = new Vector3D(-0.5, 0.5, 0.5);

        edges [0] = new int[]{0, 1};
        edges [1] = new int[]{1, 2};
        edges [2] = new int[]{2, 3};
        edges [3] = new int[]{3, 0};
        edges [4] = new int[]{4, 5};
        edges [5] = new int[]{5, 6};
        edges [6] = new int[]{6, 7};
        edges [7] = new int[]{7, 4};
        edges [8] = new int[]{0, 4};
        edges [9] = new int[]{1, 5};
        edges [10] = new int[]{2, 6};
        edges [11] = new int[]{3, 7};
    }

    public Vector3D[] getVertices() {
        return vertices;
    }

    public int[][] getEdges() {
        return edges;
    }
}
