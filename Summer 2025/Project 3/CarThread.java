/***************************
 CMSC 335, Project 3, 7/6/25 (Summer 2025) 
 Purpose: CarThread.java - Displays cars moving on three or more roads, each car runs on a separate thread,
 interacts with traffic lights, and supports pause/resume driving.
 ***************************/

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CarThread extends Thread {
    private String name;
    private double x = 0;
    private double y = 0;
    private double speedMPH = 45; // mph
    private JLabel label = new JLabel();
    private final Object pauseLock = new Object();
    private boolean paused = false;
    private List<TrafficLightThread> lights;
    private int roadIndex;
    private SimulationPanel simulationPanel;

    public CarThread(String name, JPanel panel, List<TrafficLightThread> lights, int roadIndex, SimulationPanel simPanel) {
        this.name = name;
        this.lights = lights;
        this.roadIndex = roadIndex;
        this.simulationPanel = simPanel;

        label.setText(name + " - X: 0.0 miles,  Y: 0.0 miles,  Speed: " + speedMPH + " mph");
        panel.add(label);
    }

    public int getRoadIndex() {
        return roadIndex;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setSimulationPanel(SimulationPanel simPanel) {
        this.simulationPanel = simPanel;
    }

    public void pauseThread() {
        synchronized (pauseLock) {
            paused = true;
        }
    }

    public void resumeThread() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll(); // wake the thread if it’s waiting
        }
    }

    public void run() {
        try {
            final double pixelsPerMile = 600.0;
            final double carWidthInMiles = 30.0 / pixelsPerMile;
            final double stopBuffer = 0.01; // ~53 feet in miles

            while (true) {
                synchronized (pauseLock) {
                    while (paused) {
                        pauseLock.wait();
                    }
                }

                double speedMps = speedMPH / 3600.0; // convert to miles per second
                double maxX = simulationPanel.getMaxX();
                double lightX = simulationPanel.getLightPositionMiles();

                TrafficLightThread.Light light = lights.get(roadIndex).getCurrentLight();

                boolean approachingLight = x + speedMps + carWidthInMiles >= lightX - stopBuffer &&
                        x < lightX;

                if (light == TrafficLightThread.Light.RED && approachingLight) {
                    x = lightX - stopBuffer - carWidthInMiles;
                } else if (x + speedMps + carWidthInMiles < maxX) {
                    x += speedMps;
                } else {
                    x = maxX - carWidthInMiles;
                }

                label.setText(name + " - X: " + String.format("%.3f", x) + " miles,  Y: " + String.format("%.3f", y) + " miles,  Speed: " + speedMPH + " mph");
                if (simulationPanel != null) {
                    simulationPanel.refresh();
                }

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted.");
        }
    }
}
