
package cpu.cpu.scheduling;

import cpu.cpu.scheduling.AGScheduling.AGScheduling;
import cpu.cpu.scheduling.PriorityScheduling.PriorityScheduling;
import cpu.cpu.scheduling.SJFScheduling.SJFScheduling;
import cpu.cpu.scheduling.SRTFScheduling.STRFScheduling;

import java.util.Vector;
import cpu.cpu.simulator.Utilities.Process;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author abdelrahman
 */
public class SchedulingFactory {
    public static Scheduling createScheduling(String name , Vector<Process> processes){
        return switch (name) {
            case "Shortest- Job First(SJF)" -> new SJFScheduling(processes);
            case "Priority Scheduling" -> new PriorityScheduling(processes);
            case "AG Scheduling" -> new AGScheduling(processes);
            case "Shortest- Remaining Time First (SRTF)" -> new STRFScheduling(processes);
            default -> null;
        };
    }
}
