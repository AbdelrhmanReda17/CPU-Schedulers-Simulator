/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling;
import cpu.cpu.simulator.Utilities.Process;

import java.util.*;

/**
 *
 * @author abdelrahman
 */
public abstract class Scheduling {
    protected StringBuilder schedulingData;
    protected List<Process> processes;
    protected SchedulingType schedulingType;
    protected List<Process> finishedProcesses;
    protected int quantum = 0;
    protected int contextSwitching = 0;
    protected int currentTime;
    public Scheduling(Vector<Process> ps ,int contextSwitch , int quantum){
        this.finishedProcesses = new LinkedList<>();
        this.processes = new LinkedList<>();
        schedulingData = new StringBuilder();
        this.quantum = quantum;
        this.currentTime = 0;
        this.contextSwitching = contextSwitch;
        for(Process p : ps){
            Process newProcess = new Process(p);
            newProcess.setQuantum(quantum);
            processes.add(newProcess);
        }
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
    }
    protected List<Process> getProcesses(){
        return processes;
    }
    public abstract void execute();
    public String getSchedulingType(){
        return schedulingType.toString();
    }
    public void simulate(){
        execute();
        int totalTurnAroundTime = 0;
        int totalWaitingTime = 0;
        for(Process p : finishedProcesses){
            p.calculateTurnAroundTime();
            p.calculateWaitingTime();
            totalTurnAroundTime += p.getTurnAroundTime();
            totalWaitingTime += p.getWaitingTime();
        }
        double averageTurnAroundTime = (double) totalTurnAroundTime / finishedProcesses.size();
        double averageWaitingTime = (double) totalWaitingTime / finishedProcesses.size();
        schedulingData.append("Average Turnaround Time: ").append(averageTurnAroundTime).append("\n");
        schedulingData.append("Average Waiting Time: ").append(averageWaitingTime).append("\n");
        schedulingData.append("CPU Utilization: ").append(getSchedulingType()).append("\n");
    }
    public String getSchedulingData(){
        return schedulingData.toString();
    }
    public void setQuantum(int quantum){
        this.quantum=quantum;
    }
    public void setContextSwitching(int contextSwitching){
        this.contextSwitching=contextSwitching;
    }
}
