/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package cpu.cpu.scheduling.simulator.Utilities;

import java.awt.*;

/**
 *
 * @author abdelrahman
 */
public class Process {
    private String name;
    private Color color;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private int waitingTime;
    private int PriorityNumber;
    public Process(){
        this.name = "";
        this.color = null;
        this.arrivalTime = 0;
        this.burstTime = 0;
        this.priority = 0;
        this.waitingTime = 0;
        this.PriorityNumber = 0;
    };
    public Process(String name, Color color, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.waitingTime = 0;
        this.PriorityNumber = 0;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getPriorityNumber() {
        return PriorityNumber;
    }

    public void setPriorityNumber(int priorityNumber) {
        PriorityNumber = priorityNumber;
    }

}
