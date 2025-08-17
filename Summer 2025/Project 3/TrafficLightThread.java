/***************************
 CMSC 335, Project 3, 7/6/25 (Summer 2025)
 Purpose: TrafficLightThread.java - Represents the traffic lights for each intersection (cycles through
 green, yellow, and red lights), each light runs as a separate thread, provides methods to pause/resume,
 and manages the GUI panel for the current light color.
 ***************************/

import javax.swing.*;
import java.awt.*;

public class TrafficLightThread extends Thread {
    enum Light {GREEN, YELLOW, RED}
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private volatile boolean paused = false;
    private String intersectionName;
    private final Object pauseLock = new Object();

    public TrafficLightThread(String name) {
        this.intersectionName = name;
        panel.add(new JLabel(name));
        panel.add(label);
        label.setText("RED");
        label.setForeground(Color.RED);
    }

    public JPanel getPanel() {
        return panel;
    }

    public Light getCurrentLight() {
        switch (label.getText()) {
            case "GREEN": return Light.GREEN;
            case "YELLOW": return Light.YELLOW;
            default: return Light.RED;
        }
    }

    public void pauseThread() {
        paused = true;
    }

    public void resumeThread() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    public void run() {
        Light[] cycle = {Light.GREEN, Light.YELLOW, Light.RED};
        int[] times = {5000, 2000, 5000};

        int i = 0;
        try {
            while (true) {
                synchronized (pauseLock) {
                    while (paused) {
                        pauseLock.wait();
                    }
                }

                Light current = cycle[i % 3];
                label.setText(current.name());
                switch (current) {
                    case GREEN -> label.setForeground(Color.GREEN);
                    case YELLOW -> label.setForeground(Color.ORANGE);
                    case RED -> label.setForeground(Color.RED);
                }

                Thread.sleep(times[i % 3]);
                i++;
            }
        } catch (InterruptedException e) {
            System.out.println("Traffic light thread stopped.");
        }
    }
}