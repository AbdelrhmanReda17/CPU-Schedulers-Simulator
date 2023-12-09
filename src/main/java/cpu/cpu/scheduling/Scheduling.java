/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling;
import cpu.cpu.simulator.Utilities.Process;

import java.util.Vector;

/**
 *
 * @author abdelrahman
 */
public abstract class Scheduling {
    Vector<Process> Processes;
    public Scheduling(Vector<Process> ps){
        this.Processes=ps;
    }
    Vector<Process> getProcesses(){
        return Processes;
    }
    public abstract Vector<Process> execute();
    public void simulate(){
        execute();
    }
}
