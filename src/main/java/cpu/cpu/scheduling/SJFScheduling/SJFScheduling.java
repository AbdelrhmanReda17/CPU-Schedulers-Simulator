/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package cpu.cpu.scheduling.SJFScheduling;
import cpu.cpu.scheduling.SchedulingType;
import cpu.cpu.simulator.Utilities.Duration;
import cpu.cpu.simulator.Utilities.Process;
import cpu.cpu.scheduling.Scheduling;

import java.util.*;


/**
 *
 * @author abdelrahman
 */
public class SJFScheduling extends Scheduling {
    public SJFScheduling(Vector<Process> ps , int contextSwitch , int quantum , int age){
        super(ps , contextSwitch , quantum ,age);
        this.schedulingType = SchedulingType.SHORTEST_JOB_FIRST_SCHEDULING;
    }
    @Override
    public void execute() {
        int startTime = 0;
        currentTime = 0;
        List<Process> processArrivalTimeSet = new LinkedList<>(processes);
        int processArrivalTimeSetSize = processArrivalTimeSet.size();
        PriorityQueue<Process> processQueue = new PriorityQueue<>(Comparator.comparing(Process::getBurstTime));
        Process currentlyRunningProcess = null;

        while (finishedProcesses.size() != processArrivalTimeSetSize) {
            boolean isFinished = false;
            Iterator<Process> iterator = processArrivalTimeSet.iterator();
            while(iterator.hasNext()) {
                //search if there are processes arrived every unit time.

                Process process = iterator.next();
                if (process.getArrivalTime() <= currentTime) {
                    processQueue.add(process);
                    iterator.remove();
                }
            }

            if (!processQueue.isEmpty() && currentlyRunningProcess == null) {
                currentlyRunningProcess = processQueue.poll();
                startTime = currentTime;
            }
            else if (currentlyRunningProcess != null) {
                currentlyRunningProcess.setRemainingTime(currentlyRunningProcess.getRemainingTime() - 1);
                if (currentlyRunningProcess.getRemainingTime() == 0) {
                    //a process has finished executing.
                    isFinished = true;
                    calculateFinishedProcessTiming(currentlyRunningProcess,startTime);
                    currentlyRunningProcess = null;
                    currentTime += contextSwitching;
                }
            }
            if (!isFinished) {
                currentTime++;
            }
        }
    }

    private void calculateFinishedProcessTiming(Process currentlyRunningProcess,int startTime) {
        currentlyRunningProcess.setFinishTime(currentTime);
        int endTime = currentTime;

        finishedProcesses.add(currentlyRunningProcess);
        Duration processDuration = new Duration(startTime,endTime);
        currentlyRunningProcess.addDuration(processDuration);
    }
}
