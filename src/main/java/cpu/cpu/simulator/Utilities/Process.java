/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package cpu.cpu.simulator.Utilities;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    private Map.Entry<Integer , Integer> quantum ;
    private int lastWaitTime;
    private int finishTime;
    private int turnAroundTime;
    private List<Duration> durations;
    private int remainingTime;
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
        this.durations = new LinkedList<>();
        this.turnAroundTime = 0;
        this.quantum = null;
        this.remainingTime = 0;
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
        this.quantum = null;
        this.durations = new LinkedList<>();
        this.turnAroundTime = 0;
        this.remainingTime = 0;
        this.AGFactor = 0;
        this.lastWaitTime = 0;
        this.PriorityNumber = 0;
    }
    public Process(Process process){
        this.name = process.name;
        this.color = process.color;
        this.arrivalTime = process.arrivalTime;
        this.burstTime = process.burstTime;
        this.waitingTime = process.waitingTime;
        this.quantum = process.quantum;
        this.durations = process.durations;
        this.turnAroundTime = process.turnAroundTime;
        this.remainingTime = process.remainingTime;
        this.AGFactor = process.AGFactor;
        this.lastWaitTime = process.lastWaitTime;
        this.PriorityNumber = process.PriorityNumber;
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
        this.durations = new LinkedList<>();
        this.turnAroundTime = 0;
        this.remainingTime = burstTime;
        this.quantum = null;
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
    public int getTurnAroundTime() {
        return turnAroundTime;
    }
    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }
    public List<Duration> getDurations() {
        return durations;
    }
    public void addDuration(Duration duration){
        durations.add(duration);
    }
    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDurations(List<Duration> durations) {
        this.durations = durations;
    }
    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
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
    public Map.Entry<Integer , Integer> getQuantum() {
        return quantum;
    }
    public void setQuantum(Map.Entry<Integer , Integer> quantum) {
        this.quantum = quantum;
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

    @Override
    public String toString() {
        return "Process {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", arrivalTime=" + arrivalTime +
                ", burstTime=" + burstTime +
                ", waitingTime=" + waitingTime +
                ", quantum =" + quantum.getKey() +
                ", numberOfQuantums=" + quantum.getValue() +
                ", lastWaitTime=" + lastWaitTime +
                ", finishTime=" + finishTime +
                ", remainingTime=" + remainingTime +
                ", AGFactor=" + AGFactor +
                ", PriorityNumber=" + PriorityNumber +
                '}';
    }
}
