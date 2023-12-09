/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.AGScheduling;
import cpu.cpu.simulator.Utilities.Process;
import cpu.cpu.scheduling.Scheduling;

import java.util.*;

/**
 *
 * @author abdelrahman
 */
public class AGScheduling extends Scheduling {
    Queue<Process> readyQueue = new LinkedList<>();
    List<Process> processesAG = new LinkedList<>();
    private final int numberOfProcesses;
    private boolean isSwitching = false;
    private Process currentProcess;
    private int doneProcesses;
    public AGScheduling(Vector<Process> processes) {
        super(processes);
        this.numberOfProcesses = processes.size();
        this.doneProcesses = 0;
        this.currentTime = 0;
        this.processesAG.addAll(processes);
        this.processesAG.sort(Comparator.comparingDouble(Process::getAGFactor));
    }
    private double GetMean(List<Process> processes) {
        double sum = 0.0, count = processes.size();
        for (Process ps : processes) {
            sum += ps.getBurstTime();
        }
        return (double) (sum / count);
    }
    @Override
    public Vector<Process> execute() {
        while (doneProcesses < numberOfProcesses || !readyQueue.isEmpty()) {
            remakeReadyList();
            if (currentProcess == null && !readyQueue.isEmpty()) {
                currentProcess = readyQueue.poll();
            }
            if (currentProcess != null) {
                executeProcess();
            }
        }
        return null;
    }
    private void addProcessToReadyQueue(Process process) {
        readyQueue.add(process);
        process.setLastWaitTime(currentTime);
    }
    private void ceilAGFactor() {
        for (Process process : processes) {
            process.setAGFactor(Math.ceil( process.getAGFactor() * 0.5 ));
        }
    }

    private void executeProcess() {
        int timeExecute = Math.min(currentProcess.getQuantum().getValue(), currentProcess.getBurstTime());
        currentTime += timeExecute;
        currentProcess.setBurstTime(currentProcess.getBurstTime() - timeExecute);
        this.calculateWaitingTime(currentProcess);
        if(currentProcess.getArrivalTime() > 0){
                readyQueue.add(currentProcess);
                currentProcess.setQuantum(Map.entry(currentProcess.getQuantum().getKey() +
                        (int) Math.ceil(0.1 * GetMean(processes)) , currentProcess.getQuantum().getValue() + 1));
        }else{
            currentProcess.setQuantum(Map.entry(0,0));
            doneProcesses++;
        }
    }

    private void remakeReadyList() {
        if(!processes.isEmpty() && readyQueue.isEmpty()) {
//            this.currentTime = this.processes.getFirst().getArrivalTime();
        }
        while (!processes.isEmpty()) {
            assert readyQueue.peek() != null;
            if (!(readyQueue.peek().getArrivalTime() <= currentTime)) break;
            Process topProcess = readyQueue.poll();
        }
    }
    private void calculateWaitingTime(Process process) {
        process.setWaitingTime(process.getWaitingTime() +  currentTime - process.getLastWaitTime());
    }
}
