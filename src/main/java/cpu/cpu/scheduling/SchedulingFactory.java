
package cpu.cpu.scheduling;

import cpu.cpu.scheduling.AGScheduling.AGScheduling;
import cpu.cpu.scheduling.PriorityScheduling.PriorityScheduling;
import cpu.cpu.scheduling.SJFScheduling.SJFScheduling;
import cpu.cpu.scheduling.SRTFScheduling.SRTFScheduling;

import java.util.Objects;
import java.util.Vector;

import cpu.cpu.simulator.CPUSchedulingSimulator;
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
    public static Scheduling createScheduling(String name, Vector<Process> processes, int contextSwitch, int quantum , int age) {
        return switch (name) {
            case "Shortest - Job First(SJF)" -> new SJFScheduling(processes, contextSwitch, quantum , age);
            case "Priority Scheduling" -> new PriorityScheduling(processes, contextSwitch, quantum , age);
            case "AG Scheduling" -> new AGScheduling(processes, contextSwitch, quantum , age);
            case "Shortest - Remaining Time First (SRTF)" -> new SRTFScheduling(processes, contextSwitch, quantum , age);
            default -> null;
        };
    }
    public static String getSchedulingType(Scheduling sc) {
        if(Objects.equals(sc.getSchedulingType(), SchedulingType.AG_SCHEDULING.name())){
            return "AG Scheduling";
        }else if(Objects.equals(sc.getSchedulingType(), SchedulingType.PRIORITY_SCHEDULING.name())){
            return "Priority Scheduling";
        }else if(Objects.equals(sc.getSchedulingType(), SchedulingType.SHORTEST_REMAINING_TIME_FIRST_SCHEDULING.name())){
            return "SRTF Scheduling";
        }else if(Objects.equals(sc.getSchedulingType(), SchedulingType.SHORTEST_JOB_FIRST_SCHEDULING.name())) {
            return "SJF Scheduling";
        }
        return "Scheduling";
    }
}
