/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.SRTFScheduling;

import cpu.cpu.scheduling.Scheduling;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

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
    }

    @Override
    public List<Process> execute() {
        while (!processes.isEmpty()) {
            int currentTime = processes.get(0).getArrivalTime();
            shortestProcessPQ.addAll(getAllProcessesAtTime(currentTime));

            Time startTime = new Time(currentTime);
            Time endTime = new Time(0);
            while (!shortestProcessPQ.isEmpty()) {
                Process shortestProcess = shortestProcessPQ.poll();
                shortestProcessPQ.addAll(getAllProcessesAtTime(currentTime));

                if (!shortestProcessPQ.isEmpty()
                        && shortestProcessPQ.peek().getRemainingTime() < shortestProcess.getRemainingTime()) {

                    saveDuration(shortestProcess, startTime, endTime, currentTime);
                    shortestProcessPQ.add(shortestProcess);
                    shortestProcess = shortestProcessPQ.poll();
                }

                shortestProcess.setRemainingTime(shortestProcess.getRemainingTime() - 1);
                if (shortestProcess.getRemainingTime() <= 0) {
                    saveDuration(shortestProcess, startTime, endTime, currentTime);
                    finishedProcesses.add(shortestProcess);
                } else {
                    shortestProcessPQ.add(shortestProcess);
                }
                currentTime++;
            }
        }

        for (int i = 0; i < finishedProcesses.size(); i++) {
            System.out.println(finishedProcesses.get(i).getName() + " " + finishedProcesses.get(i).getDurations());
        }

        return finishedProcesses;
    }

    private List<Process> getAllProcessesAtTime(int currentTime) {
        List<Process> foundProcesses = new ArrayList<>();
        while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentTime) {
            foundProcesses.add(processes.get(0));
            processes.remove(0);
        }

        return foundProcesses;
    }

    private void saveDuration(Process process, Time startTime, Time endTime, int currentTime) {
        endTime = new Time(currentTime);
        process.addDuration(new Duration(startTime, endTime));
        startTime = new Time(currentTime);
    }
}
