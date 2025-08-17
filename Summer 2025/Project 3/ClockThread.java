/***************************
 CMSC 335, Project 3, 7/6/25 (Summer 2025) 
 Purpose: ClockThread.java - Manages the threads that keeps track of the elapsed simulation time,
 updates a JLabel with the current time every second, and supports pause/resume functionality.
 ***************************/

import javax.swing.*;

public class ClockThread extends Thread {
    private JLabel label;
    private int time = 0;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object(); // new

    public ClockThread(JLabel label) {
        this.label = label;
    }

    public void pauseThread() {
        synchronized (pauseLock) {
            paused = true;
        }
    }

    public void resumeThread() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll(); // wake up the thread
        }
    }

    public void run() {
        try {
            while (true) {
                synchronized (pauseLock) {
                    while (paused) {
                        pauseLock.wait(); // wait while paused
                    }
                }

                time++;
                label.setText("Time: " + time + " seconds");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Clock interrupted.");
        }
    }
}