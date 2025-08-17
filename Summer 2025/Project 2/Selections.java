/***************************
 CMSC 335, Project 2, 6/8/25 (Summer 2025) 
 Purpose: The Selections.java class is called by MainDemo, and provides the
 logic to display each of the shape types on the screen.
 ***************************/

package com.example.project2_shapesapplicationdemo;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

public class Selections {

    public static Node createShape(String shapeType, String size) {
        double sizeValue = getSizeValue(size);

        switch (shapeType) {
            case "Circle":
                Circle circle = new Circle(sizeValue, Color.GRAY);
                circle.setTranslateX(0);
                circle.setTranslateY(0);
                return circle;

            case "Square":
                Rectangle square = new Rectangle(sizeValue * 2, sizeValue * 2);
                square.setFill(Color.GRAY);
                square.setTranslateX(-sizeValue);
                square.setTranslateY(-sizeValue);
                return square;

            case "Rectangle":
                Rectangle rect = new Rectangle(sizeValue * 3, sizeValue * 2);
                rect.setFill(Color.GRAY);
                rect.setTranslateX(-1.5 * sizeValue);
                rect.setTranslateY(-sizeValue);
                return rect;

            case "Triangle":
                return createTriangle(sizeValue);

            case "Sphere":
                Sphere sphere = new Sphere(sizeValue);
                apply3DMaterialAndTransforms(sphere);
                return sphere;

            case "Cube":
                Box cube = new Box(sizeValue * 2, sizeValue * 2, sizeValue * 2);
                apply3DMaterialAndTransforms(cube);
                return cube;

            case "Cylinder":
                Cylinder cylinder = new Cylinder(sizeValue, sizeValue * 2);
                apply3DMaterialAndTransforms(cylinder);
                return cylinder;

            case "Cone":
                MeshView cone = createCone(sizeValue, sizeValue * 2);
                apply3DMaterialAndTransforms(cone);
                return cone;

            case "Torus":
                Group torus = createTorus(sizeValue * 1.5, sizeValue / 2, 36, 12);
                torus.getTransforms().addAll(
                        new Rotate(30, Rotate.X_AXIS),
                        new Rotate(45, Rotate.Y_AXIS)
                );
                return torus;

            default:
                return null;
        }
    }

    private static Node createTriangle(double sizeValue) {
        TriangleMesh mesh = new TriangleMesh();

        mesh.getPoints().addAll(
                0.0f, (float) -sizeValue, 0.0f,
                (float) -sizeValue, (float) sizeValue, 0.0f,
                (float) sizeValue, (float) sizeValue, 0.0f
        );

        mesh.getTexCoords().addAll(0, 0);
        mesh.getFaces().addAll(0, 0, 1, 0, 2, 0);
        mesh.getFaceSmoothingGroups().addAll(0);

        MeshView meshView = new MeshView(mesh);
        PhongMaterial material = new PhongMaterial(Color.GRAY);
        meshView.setMaterial(material);
        meshView.setCullFace(CullFace.NONE);

        meshView.setTranslateX(0);
        meshView.setTranslateY(0);
        meshView.setTranslateZ(0);
        meshView.getTransforms().add(new Rotate(30, Rotate.X_AXIS));

        return meshView;
    }

    private static MeshView createCone(double radius, double height) {
        int divisions = 32;
        TriangleMesh mesh = new TriangleMesh();

        float halfHeight = (float) (height / 2.0);

        mesh.getPoints().addAll(0, -halfHeight, 0); // Apex

        for (int i = 0; i < divisions; i++) {
            double angle = 2 * Math.PI * i / divisions;
            float x = (float) (radius * Math.cos(angle));
            float z = (float) (radius * Math.sin(angle));
            mesh.getPoints().addAll(x, halfHeight, z);
        }

        mesh.getTexCoords().addAll(0, 0);

        for (int i = 1; i < divisions; i++) {
            mesh.getFaces().addAll(0, 0, i, 0, i + 1, 0);
        }
        mesh.getFaces().addAll(0, 0, divisions, 0, 1, 0);

        return new MeshView(mesh);
    }

    private static Group createTorus(double majorRadius, double minorRadius, int majorDivisions, int minorDivisions) {
        Group torusGroup = new Group();

        for (int i = 0; i < majorDivisions; i++) {
            double majorAngle = 2 * Math.PI * i / majorDivisions;
            double centerX = majorRadius * Math.cos(majorAngle);
            double centerZ = majorRadius * Math.sin(majorAngle);

            for (int j = 0; j < minorDivisions; j++) {
                double minorAngle = 2 * Math.PI * j / minorDivisions;

                double x = centerX + minorRadius * Math.cos(minorAngle) * Math.cos(majorAngle);
                double y = minorRadius * Math.sin(minorAngle);
                double z = centerZ + minorRadius * Math.cos(minorAngle) * Math.sin(majorAngle);

                Cylinder tube = new Cylinder(minorRadius / 3, minorRadius / 2);
                tube.setMaterial(new PhongMaterial(Color.GRAY));
                tube.setTranslateX(x);
                tube.setTranslateY(y);
                tube.setTranslateZ(z);

                tube.getTransforms().add(new Rotate(Math.toDegrees(majorAngle), Rotate.Y_AXIS));
                tube.getTransforms().add(new Rotate(Math.toDegrees(minorAngle), Rotate.X_AXIS));

                torusGroup.getChildren().add(tube);
            }
        }
        return torusGroup;
    }

    private static void apply3DMaterialAndTransforms(Shape3D shape3d) {
        PhongMaterial material = new PhongMaterial(Color.GRAY);
        shape3d.setMaterial(material);
        shape3d.setTranslateX(0);
        shape3d.setTranslateY(0);
        shape3d.setTranslateZ(0);
        shape3d.getTransforms().add(new Rotate(30, Rotate.X_AXIS));
        shape3d.getTransforms().add(new Rotate(45, Rotate.Y_AXIS));
    }

    private static double getSizeValue(String size) {
        switch (size) {
            case "Small": return 40;
            case "Large": return 80;
            default: return 60;
        }
    }
}
