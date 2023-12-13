/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.AGScheduling;

import cpu.cpu.scheduling.SchedulingType;
import cpu.cpu.simulator.Utilities.Duration;
import cpu.cpu.simulator.Utilities.Process;
import cpu.cpu.scheduling.Scheduling;

import java.util.*;

/**
 *
 * @author abdelrahman
 */
public class AGScheduling extends Scheduling {
    List<Process> readyQueue;
    PriorityQueue<Process> processesQueue;
    List<Process> AllProcesses;
    boolean isProcessFinished = false;
    private int quantumCounter = 0;
    private final int numberOfProcesses;
    public AGScheduling(Vector<Process> processes, int contextSwitch, int quantum , int age) {
        super(processes, contextSwitch, quantum , age);
        this.numberOfProcesses = processes.size();
        this.schedulingType = SchedulingType.AG_SCHEDULING;
        this.currentTime = 0;
        this.readyQueue = new LinkedList<>();
        this.finishedProcesses = new LinkedList<>();
        this.processesQueue = new PriorityQueue<>(Comparator.comparingDouble(Process::getAGFactor));
        this.AllProcesses = new LinkedList<>();
        for (Process p : this.processes) {
            Process newProcess = new Process(p);
            newProcess.setQuantum(quantum);
            AllProcesses.add(newProcess);
        }
    }
    private Process getSmallestArrival() {
        Process smallestArrival = processesQueue.peek();
        for (Process process : processesQueue) {
            if (process.getArrivalTime() < smallestArrival.getArrivalTime()) {
                smallestArrival = process;
            }
        }
        return smallestArrival;
    }
    private double getMean(List<Process> processes) {
        double sum = 0;
        for (Process process : processes) {
            sum += process.getQuantum();
        }
        return (sum / processes.size()) * 0.1;
    }
    private boolean checkSmallestAG(Process currentProcess, boolean isAG) {
        if (isAG) {
            if (readyQueue.isEmpty()) {
                return true;
            }
            for (Process process : readyQueue) {
                if (process.getAGFactor() < currentProcess.getAGFactor()) {
                    return false;
                }
            }
            return true;
        }else{
            if (readyQueue.isEmpty()) {
                return true;
            }
            for (Process process : readyQueue) {
                if (process.getArrivalTime() < currentProcess.getArrivalTime()) {
                    return false;
                }
            }
            return true;
        }
    }
    private Process getSmallestAG() {
        Process smallestAG = readyQueue.get(0);
        for (Process process : readyQueue) {
            if (process.getAGFactor() < smallestAG.getAGFactor()) {
                smallestAG = process;
            }
        }
        return smallestAG;
    }
    @Override
    public void execute() {
        while (finishedProcesses.size() < numberOfProcesses) {
            remakeProcessesQueue();
            if (readyQueue.isEmpty() && !AllProcesses.isEmpty()) {
                currentTime++;
                continue;
            }
            Process currentProcess = null;
            if(isProcessFinished){
                currentProcess = readyQueue.get(0);
                readyQueue.remove(0);
                isProcessFinished = false;
            }else{
                currentProcess = getSmallestAG();
                readyQueue.remove(currentProcess);
            }
            if (!runNonPreemptive(currentProcess)) {
                runPreemptive(currentProcess);
            }
        }
    }
    private void runPreemptive(Process currentProcess) {
        Process old = currentProcess;
        double quantum = Math.ceil((currentProcess.getQuantum() / 2));
        int ct = this.currentTime;
        while (currentTime < ct + Math.floor(old.getQuantum() / 2) && old.getRemainingTime() > 0) {
            remakeProcessesQueue();
                if (readyQueue.isEmpty() || checkSmallestAG(currentProcess, true)) {
                    currentTime += 1;
                    if (currentProcess.getRemainingTime() == 1) {
                        processFinished(currentProcess);
                        return;
                    }
                    currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);
                    currentProcess.addDuration(new Duration(currentTime - 1, currentTime));
                    quantum++;
                } else {
                    readyQueue.add(currentProcess);
                    currentProcess = getSmallestAG();
                    break;
                }
        }
        if (quantum == (Math.ceil(old.getQuantum()))) {
            getMean(processes);
            addQuantum(old , getMean(processes) + old.getQuantum() );
            old.setQuantum((int) Math.ceil(getMean(processes) + old.getQuantum()));
            processes.get(old.getPid()).setQuantum(old.getQuantum());
            readyQueue.add(old);
            isProcessFinished = true;
            return;
        } else {
            old.setQuantum(old.getQuantum() + (old.getQuantum() - (int) Math.ceil(quantum)));
            addQuantum(old , old.getQuantum() + (old.getQuantum() - (int) Math.ceil(quantum)) );
            processes.get(old.getPid()).setQuantum(old.getQuantum());
            readyQueue.remove(currentProcess);
        }
        if (!runNonPreemptive(currentProcess)) {
            runPreemptive(currentProcess);
        }
    }
    private void addQuantum(Process p , Double Quantum){
        quantumRows = Arrays.copyOf(quantumRows, quantumRows.length + 1);
        Object[] row = {p.getName(), currentTime, p.getQuantum() , Math.ceil(Quantum)};
        quantumRows[quantumCounter++] = row;
    }
    private void processFinished(Process currentProcess) {
        currentProcess.setFinishTime(currentTime);
        addQuantum(currentProcess , 0.0 );
        currentProcess.setQuantum(0);
        isProcessFinished = true;
        currentProcess.setRemainingTime(0);
        currentProcess.addDuration(new Duration(currentTime - 1, currentTime));
        readyQueue.remove(currentProcess);
        finishedProcesses.add(currentProcess);
    }
    private boolean runNonPreemptive(Process currentProcess) {
        if ((Math.ceil(currentProcess.getQuantum() / 2)) >= currentProcess.getRemainingTime()) {
            addQuantum(currentProcess , 0.0 );
            isProcessFinished = true;
            currentTime += currentProcess.getRemainingTime();
            currentProcess.addDuration(new Duration((currentTime - currentProcess.getRemainingTime()), (currentTime)));
            currentProcess.setRemainingTime(0);
            currentProcess.setQuantum(0);
            currentProcess.setFinishTime(currentTime);
            finishedProcesses.add(currentProcess);
            return true;
        } else {
            currentProcess.addDuration(
                    new Duration(currentTime, (currentTime + (int) Math.ceil(currentProcess.getQuantum() / 2))));
            currentTime += (int) Math.ceil(currentProcess.getQuantum() / 2);
            currentProcess.setRemainingTime(
                    currentProcess.getRemainingTime() - (int) Math.ceil(currentProcess.getQuantum() / 2));
            return false;
        }
    }
    private void remakeProcessesQueue() {
        while (!AllProcesses.isEmpty() && AllProcesses.get(0).getArrivalTime() <= currentTime) {
            readyQueue.add(AllProcesses.get(0));
            AllProcesses.remove(AllProcesses.get(0));
        }
    }
}
