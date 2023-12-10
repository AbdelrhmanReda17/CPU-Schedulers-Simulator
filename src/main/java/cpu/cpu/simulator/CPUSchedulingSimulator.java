package cpu.cpu.simulator;

import cpu.cpu.scheduling.Scheduling;
import cpu.cpu.scheduling.SchedulingFactory;
import cpu.cpu.simulator.Utilities.Duration;
import cpu.cpu.simulator.Utilities.Process;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JFrame;
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
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        scheduling.simulate();
        this.jSchedulingLabel.setText(CPUSchedulingSimulator.scheduling.getSchedulingType());
        this.jDataArea.setText(CPUSchedulingSimulator.scheduling.getSchedulingData());
        GanttChart chart = new GanttChart();
        IntervalCategoryDataset dataset = getCategoryDataset();
        Vector<Color> processesColor = new Vector<>();
        for (Process process : scheduling.getFinishedProcesses()) {
            processesColor.add(process.getColor());
        }
        String title = "CPU Scheduling Gantt Chart";
        String x_label = "Timeline";
        String y_label = "Process";
        chart.setDataset(dataset, processesColor  , title, x_label, y_label);
        this.chartPanel.add(chart);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chartPanel = new javax.swing.JPanel();
        dataPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDataArea = new javax.swing.JTextArea();
        jSchedulingLabel = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        chartPanel.setLayout(new java.awt.BorderLayout());

        jDataArea.setEditable(false);
        jDataArea.setColumns(20);
        jDataArea.setRows(5);
        jScrollPane1.setViewportView(jDataArea);

        jSchedulingLabel.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jSchedulingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jSchedulingLabel.setText("CPU SCHEDULING TYPE");
        jSchedulingLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addComponent(jSchedulingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addComponent(jSchedulingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(dataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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
        System.out.println("Start Time: " + process.getArrivalTime() + " End Time: " + process.getFinishTime());
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
    private javax.swing.JTextArea jDataArea;
    private javax.swing.JLabel jSchedulingLabel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
