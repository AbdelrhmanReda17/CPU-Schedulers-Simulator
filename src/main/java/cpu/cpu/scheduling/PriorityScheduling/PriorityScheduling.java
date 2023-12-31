/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.PriorityScheduling;

import cpu.cpu.scheduling.SchedulingType;
import cpu.cpu.simulator.Utilities.Duration;
import cpu.cpu.simulator.Utilities.Process;
import cpu.cpu.scheduling.Scheduling;
import java.util.*;

/**
 *
 * @author abdelrahman
 */
public class PriorityScheduling extends Scheduling {
    final int  agingThreshold = 8;
    private PriorityQueue<Process> queue;

    public PriorityScheduling(Vector<Process> ps, int contextSwitch, int quantum , int age) {
        super(ps, contextSwitch, quantum ,age);
        queue = new PriorityQueue<>(Comparator.comparingInt(Process::getPriorityNumber));
        this.schedulingType = SchedulingType.PRIORITY_SCHEDULING;

    }

    @Override
    public void execute() {
        while (!processes.isEmpty()) {
            currentTime = processes.get(0).getArrivalTime();
            getProcesses(currentTime);
            while (!queue.isEmpty()) {
                Process runningProcess = queue.poll();
                int start = (currentTime);
                int agingCounter=0;
                while (runningProcess.getRemainingTime() > 0) {
                    getProcesses(currentTime);
                    agingCounter++;
                    if (agingCounter==agingThreshold) {
                        for (Process process : queue) {
                            process.setPriorityNumber(process.getPriorityNumber()-1);
                        }
                        agingCounter=0;
                    }
                    currentTime++;
                    runningProcess.setRemainingTime(runningProcess.getRemainingTime() - 1);

                }
                int end = (currentTime);
                runningProcess.addDuration(new Duration(start, end));
                finishedProcesses.add(runningProcess);
                runningProcess.setFinishTime(currentTime);
            }
        }
    }

    public void getProcesses(int timer) {
        List<Process> toRemove = new ArrayList<>();
        for (Process process : processes) {
            if (process.getArrivalTime() == timer) {
                queue.add(process);
                toRemove.add(process);
            }
        }
        processes.removeAll(toRemove);
    }
}
