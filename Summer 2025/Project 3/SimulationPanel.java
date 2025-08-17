/***************************
 CMSC 335, Project 3, 7/6/25 (Summer 2025) 
 Purpose: SimulationPanel.java - Custom JPanel that visually represents the traffic simulation
 (draws roads, traffic lights, and cars based on their current states), and provides a method to
 refresh the display as the simulation updates.
 ***************************/

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationPanel extends JPanel {
    private List<CarThread> cars;
    private List<TrafficLightThread> lights;

    public SimulationPanel(List<CarThread> cars, List<TrafficLightThread> lights) {
        this.cars = cars;
        this.lights = lights;
        setPreferredSize(new Dimension(800, 300));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        int roadSpacing = 80;
        int leftOffset = 50;
        int rightOffset = 100;
        int trafficLightOffset = 50;
        int roadY = 40;
        int roadWidth = getWidth() - leftOffset - rightOffset;

        for (int i = 0; i < lights.size(); i++) {
            int y = roadY + i * roadSpacing;

            // Draw the road
            g.setColor(Color.GRAY);
            g.fillRect(leftOffset, y, roadWidth, 40);

            // Draw the traffic light
            TrafficLightThread.Light light = lights.get(i).getCurrentLight();
            g.setColor(switch (light) {
                case GREEN -> Color.GREEN;
                case YELLOW -> Color.YELLOW;
                case RED -> Color.RED;
            });
            int lightDiameter = 20;
            int lightX = leftOffset + roadWidth / 2 - lightDiameter / 2; // new
            int lightY = y - lightDiameter - 10; // 10px gap above the road
            g.fillOval(lightX, lightY, lightDiameter, lightDiameter);
        }

        // Draw cars
        for (CarThread car : cars) {
            int roadIndex = car.getRoadIndex();
            int y = roadY + roadIndex * roadSpacing;
            int carX = leftOffset + (int)(car.getX() * 600);
            int carWidth = 30;
            int carHeight = 15;
            int carY = y + (40 - carHeight) / 2; // center in road
            g.setColor(Color.BLUE);
            g.fillRect(carX, carY, carWidth, carHeight);
        }
    }

    // new
    public double getLightPositionMiles() {
        int leftOffset = 50;
        int rightOffset = 100;
        int roadWidth = getWidth() - leftOffset - rightOffset;
        int lightPixelX = leftOffset + roadWidth / 2;

        return (lightPixelX - leftOffset) / 600.0; // convert px to miles
    }

    // Calculate maximum car x position in miles before stopping (road end - 100px)
    public double getMaxX() {
        int leftOffset = 50;
        int rightOffset = 100;
        int roadWidth = getWidth() - leftOffset - rightOffset;

        return roadWidth / 600.0; // convert pixels to miles
    }

    public void refresh() {
        repaint();
    }
}