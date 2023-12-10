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

import cpu.cpu.simulator.Utilities.Process;


/**
 *
 * @author abdelrahman
 */
public class SJFScheduling extends Scheduling {
    public SJFScheduling(Vector<Process> ps , int contextSwitch , int quantum){
        super(ps , contextSwitch , quantum);
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
            Iterator<Process> iterator = processArrivalTimeSet.iterator();
            while(iterator.hasNext()) {
                //search if there are processes arrived every unit time.

                Process process = iterator.next();
                if (process.getArrivalTime() <= currentTime) {
                    processQueue.add(process);
                    iterator.remove();
                }
            }
            if (!processQueue.isEmpty() || finishedProcesses.size() == processArrivalTimeSetSize - 1) {
                if (currentlyRunningProcess == null ) {
                    //first process has started executing.

                    startTime = currentTime;
                    currentlyRunningProcess = processQueue.peek();
                    processQueue.poll();
                }
                assert currentlyRunningProcess != null;
                currentlyRunningProcess.setRemainingTime(currentlyRunningProcess.getRemainingTime() - 1);
                currentTime++;

                if (currentlyRunningProcess.getRemainingTime() == 0) {
                    //a process has finished executing.

                    calculateFinishedProcessTiming(currentlyRunningProcess,processArrivalTimeSetSize,startTime);

                    //set the next Process start Time.

                    startTime = currentTime;

                    //pull the next Process to execute.

                    currentlyRunningProcess = processQueue.peek();
                    processQueue.poll();
                }
            } else {
                currentTime++;
            }
        }
    }

    private void calculateFinishedProcessTiming(Process currentlyRunningProcess,int processArrivalTimeSetSize,int startTime) {
        if (finishedProcesses.size() != processArrivalTimeSetSize - 1) {
            currentTime += contextSwitching;
        }
        currentlyRunningProcess.setFinishTime(currentTime);
        int endTime = currentTime;
        finishedProcesses.add(currentlyRunningProcess);
        Duration processDuration = new Duration(startTime,endTime);
        currentlyRunningProcess.addDuration(processDuration);
    }
}
