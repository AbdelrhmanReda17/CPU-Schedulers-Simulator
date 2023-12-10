/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling;

import cpu.cpu.simulator.Utilities.Process;

import java.util.*;
import java.util.stream.Collectors;

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

    public Scheduling(Vector<Process> ps, int contextSwitch, int quantum) {
        this.finishedProcesses = new LinkedList<>();
        this.processes = new LinkedList<>();
        schedulingData = new StringBuilder();
        this.quantum = quantum;
        this.currentTime = 0;
        this.contextSwitching = contextSwitch;
        for (Process p : ps) {
            Process newProcess = new Process(p);
            newProcess.setDurations(new LinkedList<>());
            newProcess.setQuantum(quantum);
            processes.add(newProcess);
        }
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
    }

    protected List<Process> getProcesses() {
        return processes;
    }

    public abstract void execute();

    public String getSchedulingType() {
        return schedulingType.toString();
    }

    public List<Process> getFinishedProcesses() {
        return finishedProcesses.stream()
                .sorted(Comparator.comparingInt(Process::getArrivalTime))
                .collect(Collectors.toList());
    }

    public void simulate() {
        execute();
        int totalTurnAroundTime = 0;
        int totalWaitingTime = 0;
        for (Process p : getFinishedProcesses()) {
            p.calculateTurnAroundTime();
            p.calculateWaitingTime();
            p.reCalculateDurations();
            schedulingData.append("Process ").append(p.getName()).append(":\n");
            schedulingData.append("Turnaround Time = ").append(p.getTurnAroundTime()).append("\n");
            schedulingData.append("Waiting Time = ").append(p.getWaitingTime()).append("\n");
            schedulingData.append("------------------------------------\n");
            totalTurnAroundTime += p.getTurnAroundTime();
            totalWaitingTime += p.getWaitingTime();
        }
        float averageTurnAroundTime = (float) totalTurnAroundTime / finishedProcesses.size();
        float averageWaitingTime = (float) totalWaitingTime / finishedProcesses.size();
        schedulingData.append("Average Turnaround Time: ").append(averageTurnAroundTime).append("\n");
        schedulingData.append("Average Waiting Time: ").append(averageWaitingTime).append("\n");
    }

    public String getSchedulingData() {
        return schedulingData.toString();
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public void setContextSwitching(int contextSwitching) {
        this.contextSwitching = contextSwitching;
    }
}
