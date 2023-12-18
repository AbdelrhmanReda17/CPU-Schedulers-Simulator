# CPU-Scheduling-Simulator
This Java program simulates various CPU scheduling algorithms, including Non-Preemptive Shortest-Job-First (SJF), Shortest-Remaining-Time-First (SRTF), Non-preemptive Priority Scheduling, and AG Scheduling. The AG Scheduling algorithm includes both non-preemptive and preemptive phases.

## Scheduler Algorithms
1. **Non-Preemptive Shortest-Job-First (SJF)**
2. **Shortest-Remaining-Time-First (SRTF)**
3. **Non-preemptive Priority Scheduling**
4. **AG Scheduling:**
   - **Round Robin (RR) with AG-Factor**

## Usage 
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/AbdelrhmanReda17/cpu-schedulers-simulator.git
2. Open the project in NetBeans or IntelliJ
3. Run **Main.java**
4. **Add Process Details (Burst, Arrival, Priority, Color)**
   - Easily edit or delete processes as needed.

   ![Process Details](https://github.com/AbdelrhmanReda17/CPU-Schedulers-Simulator/assets/90706154/b04c1d71-11a4-41b1-9414-3e461be2a41a)

5. **Select Algorithm and Input Parameters**
   - Choose an algorithm and provide essential information such as Age, Quantum, and Context Switch.

6. **Initiate Simulation**

   ![Simulation](https://github.com/AbdelrhmanReda17/CPU-Schedulers-Simulator/assets/90706154/05794b1a-cdb9-4b68-a0ff-8497d6ce5e17)
   - The simulator will generate a Gantt chart using the jFreeChart Library, visually representing the flow of processes.
   - A detailed process table will be generated, displaying information such as finish time, turnaround time, waiting time, and more.
   - **[NOTE IF YOU ARE USING AG]** A detailed Quantum table will be generated to represent the change of the Quantum for each process

## Contributors
- This project is maintained by: [Abdelrhman Reda](https://github.com/AbdelrhmanReda17), [Mahmoud Adel](https://github.com/DarkenSoda), [Abdelrhman Atef](https://github.com/AbdoMan19), [AbdelrhmanAdel](https://github.com/TheOnlyMonster) and [Yassin Tareek](https://github.com/YassinTarekHelmy) and 
- Feel free to explore and analyze the performance of different CPU scheduling algorithms with this simulator.
