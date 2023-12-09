/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.PriorityScheduling;
import cpu.cpu.simulator.Utilities.Process;

import cpu.cpu.scheduling.Scheduling;

import java.util.List;
import java.util.Vector;

/**
 *
 * @author abdelrahman
 */
public class PriorityScheduling extends Scheduling {
    public PriorityScheduling(Vector<Process> ps , int contextSwitch , int quantum){
        super(ps , contextSwitch , quantum);
    }
    @Override
    public List<Process> execute() {
        return null;
    }


}
