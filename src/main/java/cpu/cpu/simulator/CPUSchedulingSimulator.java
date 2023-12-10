package cpu.cpu.simulator;

import cpu.cpu.scheduling.Scheduling;
import cpu.cpu.scheduling.SchedulingFactory;
import cpu.cpu.simulator.Utilities.Process;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
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
        GanttChart chart = new GanttChart();
         
    
    IntervalCategoryDataset dataset=getCategoryDataset();
    String title = "Gantt Chart Example";
    String x_label = "Software Development Phases";
    String y_label = "Timeline";
    chart.setDataset( dataset,title, x_label, y_label);
    this.chartPanel.add(chart);
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablePanel = new javax.swing.JScrollPane();
        chartPanel = new javax.swing.JPanel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        chartPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tablePanel)
            .addComponent(chartPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public  void StartLogic(){
        scheduling.execute();
         this.setLocationRelativeTo(null);
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       main.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    public  void StartGUI(JFrame frame ,String SchedulingName , Vector<Process> processes , int ContextSwitchingTime , int QuantumTime){
        main = frame;
        scheduling = SchedulingFactory.createScheduling(SchedulingName , processes , ContextSwitchingTime , QuantumTime);
        // Call the Factor with the Scheduling Name
        java.awt.EventQueue.invokeLater(() -> {
            new CPUSchedulingSimulator().setVisible(true);
        });
    }
    private IntervalCategoryDataset getCategoryDataset() {  
  
        TaskSeries series1 = new TaskSeries("Estimated Date"); series1.add(new Task("Requirement",
        Date.from(LocalDate.of(2017,7,3).atStartOfDay().toInstant(ZoneOffset.UTC)),  
        Date.from(LocalDate.of(2017, 7,7).atStartOfDay().toInstant(ZoneOffset.UTC))  
                 ));  

         series1.add(new Task("Design",Date.from(LocalDate.of(2017, 7,10).atStartOfDay().toInstant(ZoneOffset.UTC)),  
         Date.from(LocalDate.of(2017, 7, 14).atStartOfDay().toInstant(ZoneOffset.UTC))  
                 ));  

         series1.add(new Task("Coding",Date.from(LocalDate.of(2017, 7,17).atStartOfDay().toInstant(ZoneOffset.UTC)),  
         Date.from(LocalDate.of(2017, 7, 21).atStartOfDay().toInstant(ZoneOffset.UTC))  
                 ));  

         series1.add(new Task("Testing", Date.from(LocalDate.of(2017, 7,24).atStartOfDay().toInstant(ZoneOffset.UTC)),  
         Date.from(LocalDate.of(2017, 7, 28).atStartOfDay().toInstant(ZoneOffset.UTC))  
                 ));  

         series1.add(new Task("Deployment", Date.from(LocalDate.of(2017, 07,31).atStartOfDay().toInstant(ZoneOffset.UTC)),  
         Date.from(LocalDate.of(2017, 8, 4).atStartOfDay().toInstant(ZoneOffset.UTC))  
                 ));  


        TaskSeries series2 = new TaskSeries("Actual Date");  
        series2.add(new Task("Requirement",Date.from(LocalDate.of(2017, 7,3).atStartOfDay().toInstant(ZoneOffset.UTC)),  
        Date.from(LocalDate.of(2017, 7, 05).atStartOfDay().toInstant(ZoneOffset.UTC))  
                 ));  

         series2.add(new Task("Design",  
         Date.from(LocalDate.of(2017, 7, 6).atStartOfDay().toInstant(ZoneOffset.UTC)),  
         Date.from(LocalDate.of(2017, 7, 17).atStartOfDay().toInstant(ZoneOffset.UTC))  
                 ));  

         series2.add(new Task("Coding",  
         Date.from(LocalDate.of(2017, 7, 18).atStartOfDay().toInstant(ZoneOffset.UTC)),  
         Date.from(LocalDate.of(2017, 7, 27).atStartOfDay().toInstant(ZoneOffset.UTC))  
                 ));  

         series2.add(new Task("Testing",  
         Date.from(LocalDate.of(2017, 7, 28).atStartOfDay().toInstant(ZoneOffset.UTC)),  
         Date.from(LocalDate.of(2017, 8, 1).atStartOfDay().toInstant(ZoneOffset.UTC))  
                 ));  

         series2.add(new Task("Deployment",  
         Date.from(LocalDate.of(2017, 8, 2).atStartOfDay().toInstant(ZoneOffset.UTC)),  
         Date.from(LocalDate.of(2017, 8, 4).atStartOfDay().toInstant(ZoneOffset.UTC))  
                 ));  

         TaskSeriesCollection dataset = new TaskSeriesCollection();  
         dataset.add(series1);dataset.add(series2);  
         return dataset;  
    }

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel;
    private javax.swing.JScrollPane tablePanel;
    // End of variables declaration//GEN-END:variables
}
