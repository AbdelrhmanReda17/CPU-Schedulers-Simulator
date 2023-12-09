/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.SJFScheduling;
import cpu.cpu.simulator.Utilities.Process;
import cpu.cpu.scheduling.Scheduling;

import java.util.*;

import cpu.cpu.simulator.Utilities.Process;


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
        currentTime = 0;
        List<Process> finalProcessList = new LinkedList<>();
        Set<Process> processArrivalTimeSet = new TreeSet<>(Comparator.comparing(Process::getArrivalTime));
        processArrivalTimeSet.addAll(processes);
        int processArrivalTimeSetSize = processArrivalTimeSet.size();
        PriorityQueue<Process> processQueue = new PriorityQueue<>(Comparator.comparing(Process::getBurstTime));
        Process currentlyRunningProcess = null;
        while (finalProcessList.size() != processArrivalTimeSetSize) {
            Iterator<Process> iterator = processArrivalTimeSet.iterator();
            while(iterator.hasNext()) {
                Process process = iterator.next();
                if (process.getArrivalTime() <= currentTime) {
                    processQueue.add(process);
                    iterator.remove();
                }
            }
            if (!processQueue.isEmpty() || finalProcessList.size() == processArrivalTimeSetSize - 1) {
                if (currentlyRunningProcess == null ) {
                    currentlyRunningProcess = processQueue.peek();
                    processQueue.poll();
                }
                currentlyRunningProcess.setRemainingTime(currentlyRunningProcess.getRemainingTime() - 1);
                currentTime++;
                if (currentlyRunningProcess.getRemainingTime() == 0) {
                    Process finishedProcess = currentlyRunningProcess;
                    currentTime += contextSwitching;
                    finishedProcess.setFinishTime(currentTime);
                    int turnAroundTime = currentTime - finishedProcess.getArrivalTime();
                    finishedProcess.setTurnAroundTime(turnAroundTime);
                    int waitingTime = turnAroundTime - finishedProcess.getBurstTime();
                    finishedProcess.setWaitingTime(waitingTime);
                    finalProcessList.add(finishedProcess);
                    currentlyRunningProcess = processQueue.peek();
                    processQueue.poll();
                }
            }

        }

        for (Process process : finalProcessList) {
            System.out.println(process);
        }
        return finalProcessList;
    }
}
