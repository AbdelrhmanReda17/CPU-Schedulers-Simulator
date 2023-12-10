/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.SRTFScheduling;

import cpu.cpu.scheduling.Scheduling;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

import cpu.cpu.scheduling.SchedulingType;
import cpu.cpu.simulator.Utilities.Duration;
import cpu.cpu.simulator.Utilities.Process;

/**
 *
 * @author abdelrahman
 */
public class SRTFScheduling extends Scheduling {
    private PriorityQueue<Process> shortestProcessPQ;

    public SRTFScheduling(Vector<Process> ps, int contextSwitch, int quantum) {
        super(ps, contextSwitch, quantum);
        shortestProcessPQ = new PriorityQueue<>(Comparator.comparingInt(Process::getRemainingTime));
        this.schedulingType = SchedulingType.SHORTEST_REMAINING_TIME_FIRST_SCHEDULING;
    }

    @Override
    public void execute() {
        while (!processes.isEmpty()) {
            int currentTime = processes.get(0).getArrivalTime();
            shortestProcessPQ.addAll(getAllProcessesAtTime(currentTime));

            int startTime = currentTime;
            while (!shortestProcessPQ.isEmpty()) {
                Process shortestProcess = shortestProcessPQ.poll();
                shortestProcessPQ.addAll(getAllProcessesAtTime(currentTime));

                if (!shortestProcessPQ.isEmpty()
                        && shortestProcessPQ.peek().getRemainingTime() < shortestProcess.getRemainingTime()
                        && shortestProcess.getAge() < shortestProcess.getMaxAge()) {

                    shortestProcess.setAge(shortestProcess.getAge() + 1);
                    saveDuration(shortestProcess, startTime, currentTime);
                    startTime = currentTime;
                    shortestProcessPQ.add(shortestProcess);
                    shortestProcess = shortestProcessPQ.poll();
                }

                shortestProcess.setRemainingTime(shortestProcess.getRemainingTime() - 1);
                if (shortestProcess.getRemainingTime() <= 0) {
                    saveDuration(shortestProcess, startTime, currentTime);
                    startTime = currentTime;
                    finishedProcesses.add(shortestProcess);
                    shortestProcess.setFinishTime(currentTime);
                } else {
                    shortestProcessPQ.add(shortestProcess);
                }
                currentTime++;
            }
        }

        for (int i = 0; i < finishedProcesses.size(); i++) {
            System.out.println(finishedProcesses.get(i).getName());
            System.out.println("Arrival time: " + finishedProcesses.get(i).getArrivalTime());
            System.out.println("Finish time: " + finishedProcesses.get(i).getFinishTime());
            for (Duration dur : finishedProcesses.get(i).getDurations()) {
                System.out.println(dur.getStartTime() + " " + dur.getEndTime());
            }
            System.out.println("-------------------");
        }
    }

    private List<Process> getAllProcessesAtTime(int currentTime) {
        List<Process> foundProcesses = new ArrayList<>();
        while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentTime) {
            foundProcesses.add(processes.get(0));
            processes.remove(0);
        }

        return foundProcesses;
    }

    private void saveDuration(Process process, int startTime, int currentTime) {
        process.addDuration(new Duration(startTime, currentTime));
    }
}
