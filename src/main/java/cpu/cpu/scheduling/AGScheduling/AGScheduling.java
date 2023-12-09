/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.AGScheduling;
import cpu.cpu.simulator.Utilities.Duration;
import cpu.cpu.simulator.Utilities.Process;
import cpu.cpu.scheduling.Scheduling;

import java.sql.Time;
import java.util.*;

/**
 *
 * @author abdelrahman
 */
public class AGScheduling extends Scheduling {
    Queue<Process> readyQueue;
    PriorityQueue<Process> processesAG;
    List<Process> ReadyProcesses;
    private Process currentProcess;
    boolean isInterrupted = false;
    private final int numberOfProcesses;

    private int doneProcesses;
    public AGScheduling(Vector<Process> processes , int contextSwitch , int quantum) {
        super(processes , contextSwitch , quantum);
        this.numberOfProcesses = processes.size();
        this.doneProcesses = 0;
        this.currentTime = 0;
        this.processesAG = new PriorityQueue<>(Comparator.comparingDouble(Process::getAGFactor));
        this.readyQueue = new LinkedList<>();
        this.ReadyProcesses = new LinkedList<>();
    }
    private double GetMean(List<Process> processes) {
        double sum = 0.0, count = processes.size();
        for (Process ps : processes) {
            sum += ps.getBurstTime();
        }
        return (double)(sum / count);
    }
    @Override
    public List<Process> execute() {
        if(!processes.isEmpty() && readyQueue.isEmpty()) {
                this.currentTime = this.processes.get(0).getArrivalTime();
        }
        while(doneProcesses != numberOfProcesses){
            remakeReadyQueue();
            if(isInterrupted){
                currentProcess = processesAG.poll();
                readyQueue.remove(currentProcess);
                isInterrupted = false;
            }else{
                currentProcess = readyQueue.poll();
                processesAG.remove(currentProcess);
            }
            assert currentProcess != null;
            currentProcess.setWaitingTime(currentProcess.getWaitingTime() +  currentTime - currentProcess.getLastWaitTime());
            if(nonPreemptive(currentProcess)){
                doneProcesses++;
            }else{
                remakeReadyQueue();
                if(preemtive(currentProcess)){
                    doneProcesses++;
                }
            }
        }
        return null;
    }
    private boolean checkPreemtive(Process process) {
        for(Process p : ReadyProcesses){
            if(process.getAGFactor() > p.getAGFactor()){
                return false;
            }
        }
        return true;
    }
    private boolean preemtive(Process process) {
        for(Process p : ReadyProcesses){
            System.out.println(p);
        }
        if(checkPreemtive(process)){

        }else{

        }
        return false;
    }
    private boolean nonPreemptive(Process process) {
        Time startTime = new Time(currentTime);
        if(executeProcess(process)){
            Time endTime = new Time((long) currentTime);
            process.addDuration(new Duration(startTime , endTime));
            isInterrupted = true;
            return true;
        }else{
            currentTime += (int) Math.ceil(process.getQuantum() / 2.0);
            return false;
        }
    }
    private boolean executeProcess(Process process) {
        if(process.getQuantum() >= process.getRemainingTime()){
            currentTime += process.getRemainingTime();
            process.setRemainingTime(0);
            process.setQuantum(0);
            processesAG.remove(process);
            ReadyProcesses.remove(process);
            finishedProcesses.add(process);
            return true;
        }else{
            process.setRemainingTime(process.getRemainingTime() - process.getQuantum());
            return false;
        }
    }
    private void remakeReadyQueue() {
        while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentTime) {
            Process topProcess = processes.get(0);
            readyQueue.add(topProcess);
            ReadyProcesses.add(topProcess);
            processesAG.add(topProcess);
            processes.remove(topProcess);
        }
    }
}
