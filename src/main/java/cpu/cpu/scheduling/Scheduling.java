/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling;
import cpu.cpu.simulator.Utilities.Process;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author abdelrahman
 */
public abstract class Scheduling {
    protected List<Process> processes;
    protected List<Process> finishedProcesses;
    protected int quantum;
    protected int contextSwitching;
    protected int CurrentTime;
    public Scheduling(Vector<Process> ps){
        processes.addAll(ps);
        this.finishedProcesses = new LinkedList<>();
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
