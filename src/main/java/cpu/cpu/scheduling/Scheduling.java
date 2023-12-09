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
    protected List<Process> processes;
    protected List<Process> finishedProcesses;
    protected int quantum = 0;
    protected int contextSwitching = 0;
    protected int currentTime;
    public void print(){
        System.out.println(contextSwitching + " " + quantum );
    }
    public Scheduling(Vector<Process> ps){
        this.finishedProcesses = new LinkedList<>();
        this.processes = new LinkedList<>();
        for(Process p : ps){
            p.setQuantum(new AbstractMap.SimpleEntry<>(quantum, 0));
            processes.add(p);
            System.out.println(p);
        }
        processes.addAll(ps);
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));
    }
    List<Process> getProcesses(){
        return processes;
    }
    public abstract Vector<Process> execute();
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
