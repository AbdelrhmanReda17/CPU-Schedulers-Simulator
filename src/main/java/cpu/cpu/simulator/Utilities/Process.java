/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package cpu.cpu.simulator.Utilities;

import java.awt.*;
import java.util.Random;

/**
 *
 * @author abdelrahman
 */
public class Process {
    private int id = 0;
    private String name;
    private Color color;
    private int arrivalTime;
    private int burstTime;
    private int waitingTime;
    private int lastWaitTime;
    private final int finishTime;
    private double AGFactor;
    private int PriorityNumber;
    public Process(){
        this.name = "";
        this.color = null;
        this.arrivalTime = 0;
        this.burstTime = 0;
        this.waitingTime = 0;
        this.lastWaitTime = 0;
        this.AGFactor = 0;
        this.finishTime = 0;
        this.PriorityNumber = 0;
    };
    public Process(String name){
        this.name = name;
        this.color = null;
        this.arrivalTime = 0;
        this.burstTime = 0;
        this.finishTime = 0;
        this.waitingTime = 0;
        this.AGFactor = 0;
        this.lastWaitTime = 0;
        this.PriorityNumber = 0;
    }
    private static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public Process(int id,String name, Color color, int arrivalTime, int burstTime, int priority) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.waitingTime = 0;
        this.lastWaitTime = 0;
        this.AGFactor = arrivalTime + burstTime ;
        this.finishTime = 0;
        this.PriorityNumber = priority;
        int randomNumber = getRandomNumber(0 , 20);
        if(randomNumber < 10)
            this.AGFactor = this.AGFactor + randomNumber;
        else if (randomNumber > 10)
            this.AGFactor = this.AGFactor + 10 ;
        else
            this.AGFactor =this.AGFactor + priority;
    }
    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public double getAGFactor() {
        return AGFactor;
    }

    public void setAGFactor(double AGFactor) {
        this.AGFactor = AGFactor;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public String getName() {
        return name;
    }
    public int getLastWaitTime() {
        return lastWaitTime;
    }
    public void setLastWaitTime(int lastWaitTime) {
        this.lastWaitTime = lastWaitTime;
    }
    public int getFinishTime() {
        return finishTime;
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
