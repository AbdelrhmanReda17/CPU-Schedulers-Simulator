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
    StringBuilder stringBuilder;
    protected List<Process> processes;
    protected List<Process> finishedProcesses;
    protected int quantum = 0;
    protected int contextSwitching = 0;
    protected int currentTime;
    public Scheduling(Vector<Process> ps ,int contextSwitch , int quantum){
        this.finishedProcesses = new LinkedList<>();
        this.processes = new LinkedList<>();
        stringBuilder = new StringBuilder();
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
    public void simulate(){
        execute();
    }
    public void setQuantum(int quantum){
        this.quantum=quantum;
    }
    public void setContextSwitching(int contextSwitching){
        this.contextSwitching=contextSwitching;
    }
}
