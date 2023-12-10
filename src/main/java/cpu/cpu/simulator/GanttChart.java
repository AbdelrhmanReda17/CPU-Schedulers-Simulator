/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpu.cpu.simulator;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.time.LocalDate;  
import java.time.ZoneOffset;  
import java.util.Date;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.data.category.IntervalCategoryDataset;  
import org.jfree.data.gantt.Task;  
import org.jfree.data.gantt.TaskSeries;  
import org.jfree.data.gantt.TaskSeriesCollection;  

public class GanttChart extends JPanel {

    public GanttChart() {
        setLayout(new BorderLayout());
    }

    public void setDataset( String title, String x_label, String y_label) {
        removeAll();
        IntervalCategoryDataset dataset=getCategoryDataset();
        JFreeChart chart = ChartFactory.createGanttChart(title, y_label, x_label, dataset,false,false,false);
        ChartPanel panel = new ChartPanel(chart);
        add(panel);
        repaint();
        revalidate();
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
} 
    
  
  
  
   



