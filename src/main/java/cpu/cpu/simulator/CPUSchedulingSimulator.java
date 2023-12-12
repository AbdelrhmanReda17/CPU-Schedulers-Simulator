package cpu.cpu.simulator;

import cpu.cpu.scheduling.Scheduling;
import cpu.cpu.scheduling.SchedulingFactory;
import cpu.cpu.scheduling.SchedulingType;
import cpu.cpu.simulator.Utilities.Duration;
import cpu.cpu.simulator.Utilities.Process;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

public class CPUSchedulingSimulator extends javax.swing.JFrame {
    // Scheduling Parameter
    private static JFrame main;
    private static Scheduling scheduling;

    public static Scheduling getScheduling() {
        return scheduling;
    }
    public CPUSchedulingSimulator() {
        initComponents();
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        scheduling.simulate();
        this.jSchedulingLabel.setText(SchedulingFactory.getSchedulingType(scheduling));
        this.jScrollPane2.setVisible(Objects.equals(scheduling.getSchedulingType(), SchedulingType.AG_SCHEDULING.name()));
        this.jTable2.setVisible(Objects.equals(scheduling.getSchedulingType(), SchedulingType.AG_SCHEDULING.name()));
        this.jLabel1.setText(String.valueOf(CPUSchedulingSimulator.scheduling.getAverageTT()));
        this.jLabel2.setText(String.valueOf(CPUSchedulingSimulator.scheduling.getAverageWT()));
        GanttChart chart = new GanttChart();
        IntervalCategoryDataset dataset = getCategoryDataset();
        this.jTable1.setModel(new DefaultTableModel(
                CPUSchedulingSimulator.scheduling.getProcessesRows(),
                new String [] {
                        "Process", "PID", "Arrival Time", "Finish TIme"
                }
        ));
        this.jTable1.setShowGrid(true);
        this.jTable1.setEnabled(false);
        this.jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.jTable2.setModel(new DefaultTableModel(
                CPUSchedulingSimulator.scheduling.getQuantumRows(),
                new String [] {
                        "Process", "Current Time", "Quantum Before", "Quantum After"
                }
        ));
        this.jTable2.setEnabled(false);
        this.jTable2.setShowGrid(true);
        chart.setDataset(dataset, scheduling.getProcessesColors()  , "CPU Scheduling Gantt Chart", "Timeline", "Process");
        this.chartPanel.add(chart);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartPanel = new javax.swing.JPanel();
        dataPanel = new javax.swing.JPanel();
        jSchedulingLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jOverheadLabel = new javax.swing.JLabel();
        jTurnaroundLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        chartPanel.setLayout(new java.awt.BorderLayout());

        jSchedulingLabel.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jSchedulingLabel.setForeground(new java.awt.Color(0, 0, 0));
        jSchedulingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jSchedulingLabel.setText("CPU SCHEDULING TYPE");
        jSchedulingLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("30");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("40");

        jOverheadLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jOverheadLabel.setForeground(new java.awt.Color(0, 0, 0));
        jOverheadLabel.setText("Average Waiting Time :");

        jTurnaroundLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTurnaroundLabel.setForeground(new java.awt.Color(0, 0, 0));
        jTurnaroundLabel.setText("Average Turnaround Time : ");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Process", "Current Time", "Arrival Time", "Finish TIme"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane2.setViewportView(jTable2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Process", "PID", "Arrival Time", "Finish TIme"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setEnabled(false);
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dataPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dataPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTurnaroundLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jOverheadLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSchedulingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addComponent(jSchedulingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTurnaroundLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jOverheadLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(dataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
        main.setVisible(true);
    }// GEN-LAST:event_formWindowClosing

    public static void StartGUI(JFrame frame, String SchedulingName, Vector<Process> processes,
            int ContextSwitchingTime, int QuantumTime) {
        main = frame;
        scheduling = SchedulingFactory.createScheduling(SchedulingName, processes, ContextSwitchingTime, QuantumTime);
        // Call the Factor with the Scheduling Name
        java.awt.EventQueue.invokeLater(() -> {
            new CPUSchedulingSimulator().setVisible(true);
        });
    }

    private IntervalCategoryDataset getCategoryDataset() {
        List<Process> processes = scheduling.getFinishedProcesses();
        TaskSeriesCollection dataset = new TaskSeriesCollection();

        // Format for the fixed time
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date fixedTime;
        try {
            fixedTime = dateFormat.parse("00:00:00");
        } catch (ParseException e) {
            // Handle the exception (unlikely in this case)
            e.printStackTrace();
            return dataset;
        }
        for (Process process : processes) {
            TaskSeries series = getTaskSeries(process, fixedTime);
            dataset.add(series);
        }
        return dataset;
    }

    private static TaskSeries getTaskSeries(Process process, Date fixedTime) {
        TaskSeries series = new TaskSeries(process.getName());
        Date startTime = new Date(fixedTime.getTime() + process.getArrivalTime());
        Date endTime = new Date(fixedTime.getTime() + process.getFinishTime());
        Task task = new Task(
                process.getName(),
                startTime,
                endTime);
        for (Duration duration : process.getDurations()) {
            task.addSubtask(
                    new Task(
                   process.getName(),
                    new Date(fixedTime.getTime() + duration.getStartTime()),
                    new Date(fixedTime.getTime() + duration.getEndTime())));
        }
        series.add(task);
        return series;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jOverheadLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jSchedulingLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel jTurnaroundLabel;
    // End of variables declaration//GEN-END:variables

}
