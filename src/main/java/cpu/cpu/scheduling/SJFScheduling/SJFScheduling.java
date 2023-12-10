/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.SJFScheduling;
import cpu.cpu.simulator.Utilities.Duration;
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
    public void execute() {
        int startTime = 0;
        int endTime = 0;
        currentTime = 0;
        Set<Process> processArrivalTimeSet = new TreeSet<>(Comparator.comparing(Process::getArrivalTime));
        processArrivalTimeSet.addAll(processes);
        int processArrivalTimeSetSize = processArrivalTimeSet.size();
        PriorityQueue<Process> processQueue = new PriorityQueue<>(Comparator.comparing(Process::getBurstTime));
        Process currentlyRunningProcess = null;
        while (finishedProcesses.size() != processArrivalTimeSetSize) {
            Iterator<Process> iterator = processArrivalTimeSet.iterator();
            while(iterator.hasNext()) {
                Process process = iterator.next();
                if (process.getArrivalTime() <= currentTime) {
                    processQueue.add(process);
                    iterator.remove();
                }
            }
            if (!processQueue.isEmpty() || finishedProcesses.size() == processArrivalTimeSetSize - 1) {
                if (currentlyRunningProcess == null ) {
                    //first process has started executing.
                    currentlyRunningProcess = processQueue.peek();
                    processQueue.poll();
                }
                assert currentlyRunningProcess != null;
                currentlyRunningProcess.setRemainingTime(currentlyRunningProcess.getRemainingTime() - 1);
                currentTime++;
                //a process has finished executing.
                if (currentlyRunningProcess.getRemainingTime() == 0) {
                    Process finishedProcess = currentlyRunningProcess;
                    currentTime += contextSwitching;
                    finishedProcess.setFinishTime(currentTime);
                    endTime = currentTime;
                    int turnAroundTime = currentTime - finishedProcess.getArrivalTime();
                    finishedProcess.setTurnAroundTime(turnAroundTime);
                    int waitingTime = turnAroundTime - finishedProcess.getBurstTime();
                    finishedProcess.setWaitingTime(waitingTime);
                    finishedProcesses.add(finishedProcess);
                    Duration processDuration = new Duration(startTime,endTime);
                    finishedProcess.addDuration(processDuration);
                    System.out.println(processDuration);
                    startTime = currentTime;
                    currentlyRunningProcess = processQueue.peek();
                    processQueue.poll();
                }
            }

        }

        for (Process process : finishedProcesses) {
            System.out.println(process);
        }

    }
}
