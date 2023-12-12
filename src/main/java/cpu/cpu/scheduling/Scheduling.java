/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling;

import cpu.cpu.simulator.Utilities.Process;
import java.awt.Color;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author abdelrahman
 */
public abstract class Scheduling {
    protected List<Process> processes;
    protected SchedulingType schedulingType;
    protected List<Process> finishedProcesses;
    protected int quantum = 0;
    protected int contextSwitching = 0;
    protected int currentTime;

    public Scheduling(Vector<Process> ps, int contextSwitch, int quantum) {
        this.finishedProcesses = new LinkedList<>();
        this.processes = new LinkedList<>();
        this.quantum = quantum;
        this.currentTime = 0;
        this.processesColors = new Vector<>();
        this.quantumRows = new Object[0][];
        this.processesRows = new Object[ps.size()][];
        this.contextSwitching = contextSwitch;
        for (Process p : ps) {
            Process newProcess = new Process(p);
            newProcess.setDurations(new LinkedList<>());
            newProcess.setQuantum(quantum);
            processes.add(newProcess);
        }
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
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
    private float averageTurnAroundTime;
    private float averageWaitingTime;
    private final Vector<Color> processesColors;
    private final Object[][] processesRows;
    protected Object[][]  quantumRows;
    public void simulate() {
        execute();
        int totalTurnAroundTime = 0;
        int totalWaitingTime = 0;
        for (int i = 0; i < finishedProcesses.size(); i++) {
            Process p = finishedProcesses.get(i);
            p.calculateTurnAroundTime();
            p.calculateWaitingTime();
            p.reCalculateDurations();
            Object[] row = {p.getName(), p.getPid(), p.getArrivalTime() , p.getFinishTime()};
            processesRows[i] = row;
            totalTurnAroundTime += p.getTurnAroundTime();
            totalWaitingTime += p.getWaitingTime();
            processesColors.add(p.getColor());
        }
        averageTurnAroundTime = (float) totalTurnAroundTime / finishedProcesses.size();
        averageWaitingTime = (float) totalWaitingTime / finishedProcesses.size();
    }
    public float getAverageTT(){
        return averageTurnAroundTime;
    }
    public float getAverageWT(){
        return averageWaitingTime;
    }
    public Object[][] getQuantumRows() {
        return quantumRows;
    }
    public Object[][] getProcessesRows() {
        return processesRows;
    }
    public Vector<Color> getProcessesColors() {
        return processesColors;
    }
}
