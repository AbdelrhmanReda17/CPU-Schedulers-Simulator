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
    List<Process> processesAG;
    List<Process> deadList;
    boolean isInterrupted = false;
    private final int numberOfProcesses;
    private Process currentProcess;
    private int doneProcesses;
    public AGScheduling(Vector<Process> processes , int contextSwitch , int quantum) {
        super(processes , contextSwitch , quantum);
        this.numberOfProcesses = processes.size();
        this.doneProcesses = 0;
        this.currentTime = 0;
        this.processesAG = new LinkedList<>();
        this.readyQueue = new LinkedList<>();
        this.deadList = new LinkedList<>();
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
    public List<Process> execute() {
        if(!processes.isEmpty() && readyQueue.isEmpty()) {
                this.currentTime = this.processes.get(0).getArrivalTime();
        }
        while(doneProcesses != numberOfProcesses){
            // remake the ready queue
            for (Process process : processes) {
                System.out.println(process);
            }
            remakeReadyQueue();

            break;
//            doneProcesses++;
//            if(isInterrupted){
//                currentProcess = processesAG.get(0);
//                readyQueue.remove(currentProcess);
//                isInterrupted = false;
//            }else{
//                currentProcess = readyQueue.poll();
//                processesAG.remove(currentProcess);
//            }
//            currentProcess.setWaitingTime(currentProcess.getWaitingTime() +  currentTime - currentProcess.getLastWaitTime());
//            if(nonPreemptive(currentProcess)){
//                doneProcesses++;
//            }else{
//                remakeReadyQueue();
//                if(preemtive(currentProcess)){
//                    doneProcesses++;
//                }
//            }
        }
        return null;
    }
    private boolean preemtive(Process process) {
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
            currentTime += (int) Math.ceil(process.getQuantum().getKey() / 2.0);
            return false;
        }
    }
    private boolean executeProcess(Process process) {
        if(process.getQuantum().getKey() >= process.getRemainingTime()){
            currentTime += process.getRemainingTime();
            process.setRemainingTime(0);
            process.setQuantum(Map.entry(0,process.getQuantum().getValue()));
            deadList.add(process);
            readyQueue.remove(process);
            processesAG.remove(process);
            return true;
        }else{
            process.setRemainingTime(process.getRemainingTime() - process.getQuantum().getKey());
            return false;
        }
    }
    private void remakeReadyQueue() {
        while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentTime) {
            Process topProcess = processes.get(0);
            readyQueue.add(topProcess);
            processesAG.add(topProcess);
            processes.remove(topProcess);
        }
    }
}
