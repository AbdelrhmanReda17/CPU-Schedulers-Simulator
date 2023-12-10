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
    List<Process> readyQueue;
    PriorityQueue<Process> processesQueue;
    List<Process> AllProcesses;
    private final int numberOfProcesses;
    private int doneProcesses;
    public AGScheduling(Vector<Process> processes , int contextSwitch , int quantum) {
        super(processes , contextSwitch , quantum);
        this.numberOfProcesses = processes.size();
        this.doneProcesses = 0;
        this.currentTime = 0;
        this.readyQueue = new LinkedList<>();
        this.finishedProcesses = new LinkedList<>();
        this.processesQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getArrivalTime));
        this.AllProcesses = new LinkedList<>();
        for(Process p : processes){
            Process newProcess = new Process(p);
            newProcess.setQuantum(quantum);
            AllProcesses.add(newProcess);
        }
    }
    private double getMean(List<Process> processes) {
        double sum = 0;
        for (Process process : processes) {
            sum += process.getQuantum();
        }
        return (sum / processes.size()) * 0.1 ;
    }

    private boolean checkSmallestAG(Process currentProcess , boolean isReadyQueue) {
        if(isReadyQueue){
            if(readyQueue.isEmpty()){
                return true;
            }
            for (Process process : readyQueue) {
                if (process.getAGFactor() < currentProcess.getAGFactor()) {
                    return false;
                }
            }
            return true;
        }else{
            return processesQueue.isEmpty() || processesQueue.peek().getAGFactor() > currentProcess.getAGFactor();
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
    public List<Process> execute() {
        while(finishedProcesses.size() < numberOfProcesses){
            remakeProcessesQueue();
            if(processesQueue.isEmpty() && !AllProcesses.isEmpty() && readyQueue.isEmpty()) {currentTime++; continue;}
            Process currentProcess = null;
            if(processesQueue.isEmpty()){
                currentProcess = readyQueue.get(0);
                readyQueue.remove(0);
            }else {
                currentProcess = processesQueue.poll();
            }
            if(runNonPreemptive(currentProcess)){
                doneProcesses++;
            }else if (runPreemptive(currentProcess)){
                doneProcesses++;
            }
        }
        for(Process process : finishedProcesses){
            System.out.println(process);
        }
        return finishedProcesses;
    }

    private boolean runPreemptive(Process currentProcess){
        boolean isQuantumFinished = true;
        Process old = currentProcess;
        double quantum = Math.ceil((currentProcess.getQuantum() / 2));
        int ct = this.currentTime;
        while (currentTime < ct + Math.floor(old.getQuantum() / 2)  && old.getRemainingTime() > 0 ){
            remakeProcessesQueue();
            if(processesQueue.isEmpty()){
                if(readyQueue.isEmpty() || checkSmallestAG(currentProcess , true)){
                    currentTime += 1;
                    if(currentProcess.getRemainingTime() == 1){
                        currentProcess.setFinishTime(currentTime);
                        currentProcess.setQuantum(0);
                        currentProcess.setRemainingTime(0);
                        currentProcess.setTurnAroundTime(currentTime - currentProcess.getArrivalTime());
                        currentProcess.setWaitingTime(currentProcess.getTurnAroundTime() - currentProcess.getBurstTime());
                        currentProcess.addDuration(new Duration(currentTime - 1 , currentTime));
                        readyQueue.remove(currentProcess);
                        finishedProcesses.add(currentProcess);
                        return true;
                    }
                    currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);
                    currentProcess.addDuration(new Duration(currentTime - 1 , currentTime));
                    quantum++;
                }else{
                    readyQueue.add(currentProcess);
                    currentProcess = getSmallestAG();
                    break;
                }
            }else{
                if(checkSmallestAG(currentProcess , false)) {
                    currentTime += 1;
                    if (currentProcess.getRemainingTime() == 1) {
                        currentProcess.setFinishTime(currentTime);
                        currentProcess.setQuantum(0);
                        currentProcess.setRemainingTime(0);
                        currentProcess.setTurnAroundTime(currentTime - currentProcess.getArrivalTime());
                        currentProcess.setWaitingTime(currentProcess.getTurnAroundTime() - currentProcess.getBurstTime());
                        currentProcess.addDuration(new Duration(currentTime - 1 , currentTime));
                        finishedProcesses.add(currentProcess);
                        readyQueue.remove(currentProcess);
                        return true;
                    }
                    currentProcess.setRemainingTime(currentProcess.getRemainingTime() - 1);
                    currentProcess.addDuration(new Duration(currentTime - 1 , currentTime));
                    quantum++;
                }else{
                    readyQueue.add(currentProcess);
                    currentProcess = processesQueue.poll();
                    break;
                }
            }
        }
        if(quantum == (Math.ceil(old.getQuantum()))){
            getMean(processes);
            old.setQuantum((int) Math.ceil(getMean(processes) + old.getQuantum()));
            processes.get(old.getPid()).setQuantum(old.getQuantum());
            readyQueue.add(old);
            return false;
        }else{
            old.setQuantum(old.getQuantum() +  ( old.getQuantum() - (int) Math.ceil(quantum) ));
            processes.get(old.getPid()).setQuantum(old.getQuantum());
            readyQueue.remove(currentProcess);
        }
        if(runNonPreemptive(currentProcess)){
            return true;
        }else if (runPreemptive(currentProcess)){
            return true;
        }
        return false;
    }
    private boolean runNonPreemptive(Process currentProcess) {
        if((Math.ceil(currentProcess.getQuantum() / 2) ) >= currentProcess.getRemainingTime()){
            currentTime += currentProcess.getRemainingTime();
            currentProcess.addDuration(new Duration((currentTime - currentProcess.getRemainingTime()) , (currentTime)));
            currentProcess.setRemainingTime(0);
            currentProcess.setQuantum(0);
            currentProcess.setFinishTime(currentTime);
            currentProcess.setTurnAroundTime(currentTime - currentProcess.getArrivalTime());
            currentProcess.setWaitingTime(currentProcess.getTurnAroundTime() - currentProcess.getBurstTime());
            finishedProcesses.add(currentProcess);
            return true;
        }
        else{
            currentProcess.addDuration(new Duration(currentTime , (currentTime + (int) Math.ceil(currentProcess.getQuantum() / 2) )));
            currentTime += (int) Math.ceil(currentProcess.getQuantum() / 2);
            currentProcess.setRemainingTime(currentProcess.getRemainingTime() - (int) (currentProcess.getQuantum() / 2));
            return false;
        }
    }
    private void remakeProcessesQueue() {
        while (!AllProcesses.isEmpty() && AllProcesses.get(0).getArrivalTime() <= currentTime) {
            processesQueue.add(AllProcesses.get(0));
            AllProcesses.remove(AllProcesses.get(0));
        }
    }
}
