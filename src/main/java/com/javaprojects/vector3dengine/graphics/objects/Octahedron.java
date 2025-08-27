package com.javaprojects.vector3dengine.graphics.objects;

import com.javaprojects.vector3dengine.mathlib.vector.Vector3D;

public class Octahedron {
    private Vector3D vertices[] = new Vector3D[6];
    private int[][]edges = new int[12][2];


    public Octahedron(double size){
        vertices[0] = new Vector3D(0, size, 0);
        vertices[1] = new Vector3D(0, -size, 0);
        vertices[2] = new Vector3D(size, 0, 0);
        vertices[3] = new Vector3D(-size, 0, 0);
        vertices[4] = new Vector3D(0, 0, size);
        vertices[5] = new Vector3D(0, 0, -size);

        //EDGES

        edges[0] = new int[]{0, 2};
        edges[1] = new int[]{0, 3};
        edges[2] = new int[]{0, 4};
        edges[3] = new int[]{0, 5};
        edges[4] = new int[]{1, 2};
        edges[5] = new int[]{1, 3};
        edges[6] = new int[]{1, 4};
        edges[7] = new int[]{1, 5};
        edges[8] = new int[]{2, 4};
        edges[9] = new int[]{2, 5};
        edges[10] = new int[]{3, 4};
        edges[11] = new int[]{3, 5};


    }
    public Vector3D[] getVertices() {
        return vertices;
    }
    public int[][] getEdges() {
        return edges;
    }
}
