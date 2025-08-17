/***************************
 CMSC 335, Project 3, 7/6/25 (Summer 2025)
 Purpose: TrafficSimulatorGUI.java (main file) - Represents a traffic light at an intersection
 (cycles through green, yellow, and red), each traffic light runs in its own thread, provides pause/resume
 functionality, and maintains the GUI panel for the traffic light for each road.
 ***************************/

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;

public class TrafficSimulatorGUI extends JFrame {
    private JLabel timeLabel = new JLabel("Time: 0 s");
    private JButton startButton = new JButton("Start");
    private JButton pauseButton = new JButton("Pause");
    private JButton stopButton = new JButton("Stop");
    private JButton addCarButton = new JButton("Add Car");
    private JButton addIntersectionButton = new JButton("Add Intersection");
    private JPanel trafficLightPanel = new JPanel(new GridLayout(0, 1));
    private JPanel carInfoPanel = new JPanel(new GridLayout(3, 1));

    private SimulationPanel simulationPanel; // new

    private ClockThread clockThread;
    private boolean running = false;
    private final List<TrafficLightThread> trafficLights = new ArrayList<>();
    private final List<CarThread> carThreads = new ArrayList<>();

    public TrafficSimulatorGUI() {
        setTitle("Traffic Congestion Simulation");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(timeLabel);
        topPanel.add(startButton);
        topPanel.add(pauseButton);
        topPanel.add(stopButton);
        topPanel.add(addCarButton);
        topPanel.add(addIntersectionButton);
        add(topPanel, BorderLayout.NORTH);

        add(trafficLightPanel, BorderLayout.CENTER);
        add(carInfoPanel, BorderLayout.SOUTH);

        setupListeners();
        initializeSimulation();

        Timer repaintTimer = new Timer(100, e -> {
            if (simulationPanel != null) {
                simulationPanel.refresh();
            }
        });
        repaintTimer.start();
    }

    private void initializeSimulation() {
        clockThread = new ClockThread(timeLabel);
        for (int i = 0; i < 3; i++) {
            TrafficLightThread lightThread = new TrafficLightThread("Intersection " + (i + 1));
            trafficLights.add(lightThread);
            trafficLightPanel.add(lightThread.getPanel());
        }

        for (int i = 0; i < 3; i++) {
            CarThread carThread = new CarThread("Car " + (i + 1), carInfoPanel, trafficLights, i, null);
            carThreads.add(carThread);
        }

        simulationPanel = new SimulationPanel(carThreads, trafficLights);
        add(simulationPanel, BorderLayout.CENTER);

        for (CarThread car : carThreads) {
            car.setSimulationPanel(simulationPanel);
        }
    }

    private void setupListeners() {
        startButton.addActionListener(e -> startSimulation());
        pauseButton.addActionListener(e -> pauseSimulation());
        stopButton.addActionListener(e -> stopSimulation());
        addCarButton.addActionListener(e -> {
            int road = carThreads.size() % trafficLights.size(); // Distribute cars across available roads
            CarThread newCar = new CarThread("Car " + (carThreads.size() + 1), carInfoPanel, trafficLights, road, simulationPanel);
            carThreads.add(newCar);
            newCar.start();
        });
        addIntersectionButton.addActionListener(e -> {
            int intersectionNumber = trafficLights.size() + 1;
            TrafficLightThread newLight = new TrafficLightThread("Intersection " + intersectionNumber);
            trafficLights.add(newLight);
            trafficLightPanel.add(newLight.getPanel());

            // Refresh the GUI layout
            trafficLightPanel.revalidate();
            trafficLightPanel.repaint();

            newLight.start();
        });
    }

    private void startSimulation() {
        if (!running) {
            running = true;
            clockThread.start();
            trafficLights.forEach(Thread::start);
            carThreads.forEach(Thread::start);
        } else {
            // Resume all paused threads
            clockThread.resumeThread();
            trafficLights.forEach(light -> light.resumeThread());
            carThreads.forEach(car -> car.resumeThread());
        }
    }

    private void pauseSimulation() {
        clockThread.pauseThread();
        trafficLights.forEach(TrafficLightThread::pauseThread);
        carThreads.forEach(CarThread::pauseThread);
    }

    private void stopSimulation() {
        running = false;
        System.exit(0); // or gracefully stop threads
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TrafficSimulatorGUI().setVisible(true));
    }
}