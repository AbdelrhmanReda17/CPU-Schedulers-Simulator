/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.AGScheduling;
import cpu.cpu.simulator.Utilities.Process;
import cpu.cpu.scheduling.Scheduling;

import java.util.*;

/**
 *
 * @author abdelrahman
 */
public class AGScheduling extends Scheduling {
    Queue<Process> readyQueue = new LinkedList<>();
    List<Integer> AGFactor = new LinkedList<>();
    List<Process> InReadyList = new LinkedList<Process>();
    private int numberOfProcesses;
    private int doneProcesses;
    Comparator<Process> processComparator = (p1, p2) -> {
        if (p1.getArrivalTime() < p2.getArrivalTime())
            return -1;
        if (p1.getArrivalTime() > p2.getArrivalTime())
            return 1;
        return 0;
    };
    public AGScheduling(Vector<Process> processes) {
        super(processes);
        this.numberOfProcesses = processes.size();
        this.doneProcesses = 0;
        for (Process process : processes) {
            AGFactor.add(quantum);
        }
    }
    private double GetMean(Vector<Process> processes) {
        double sum = 0.0, count = processes.size();
        for (int i = 0; i < count; i++) {
            sum += AGFactor.get(i);
        }
        return (double) (sum / count);
    }

    @Override
    public Vector<Process> execute() {
        while (doneProcesses < numberOfProcesses || !readyQueue.isEmpty()) {
//            remakeReadyList();
        }
        return null;
    }

//    private void remakeReadyList() {
//        while (!processes.isEmpty()) {
//            assert readyQueue.peek() != null;
//            if (!(readyQueue.peek().getArrivalTime() <= CurrentTime)) break;
//            Process topProcess = readyQueue.poll();
//            InReadyList.add(topProcess);
//        }
//    }
    private void updateAGFactor() {
        for (int i = 0; i < AGFactor.size(); i++) {
            AGFactor.set(i, (int) (AGFactor.get(i) / 2));
        }
    }
    private void calculateWaitingTime(Process process) {
        process.setWaitingTime(process.getWaitingTime() +  CurrentTime - process.getLastWaitTime());
    }
}
