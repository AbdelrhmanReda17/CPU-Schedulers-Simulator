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
    List<Process> deadList = new LinkedList<>();
    private final int numberOfProcesses;
    private Process currentProcess;
    private int doneProcesses;
    public AGScheduling(Vector<Process> processes , int contextSwitch , int quantum) {
        super(processes , contextSwitch , quantum);
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
        return (double)(sum / count);
    }
    @Override
    public Vector<Process> execute() {
        while(!processes.isEmpty() || !readyQueue.isEmpty()){

        }
    }
    private boolean nonPreemptive(Process process) {

    }

//    private void addProcessToReadyQueue(Process process) {
//        readyQueue.add(process);
//        process.setLastWaitTime(currentTime);
//    }
//    private void ceilAGFactor() {
//        for (Process process : processes) {
//            process.setAGFactor(Math.ceil( process.getAGFactor() * 0.5 ));
//        }
//    }

    private boolean executenonPreemptive(Process process) {
        if(process.getQuantum().getKey() >= process.getRemainingTime()){
            currentTime += process.getRemainingTime();
            process.setRemainingTime(0);
            process.setQuantum(Map.entry(0,0));
            deadList.add(process);
            processes.remove(process);
            processesAG.remove(process);
            return true;
        }else{
            currentTime += (int) Math.ceil(process.getQuantum().getKey() / 2.0);
            process.setRemainingTime(process.getRemainingTime() - process.getQuantum().getKey());
            return false;
        }
    }

    private void remakeReadyList() {
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
