/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.SJFScheduling;
import cpu.cpu.simulator.Utilities.Process;
import cpu.cpu.scheduling.Scheduling;
import java.util.Vector;
import cpu.cpu.simulator.Utilities.Process;
import java.util.List;


/**
 *
 * @author abdelrahman
 */
public class SJFScheduling extends Scheduling {
    public SJFScheduling(Vector<Process> ps , int contextSwitch , int quantum){
        super(ps , contextSwitch , quantum);
    }
    @Override
    public List<Process> execute() {
        return null;
    }
}
