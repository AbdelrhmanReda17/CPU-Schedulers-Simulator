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

    public void setDataset(IntervalCategoryDataset dataset, String title, String x_label, String y_label) {
        removeAll();
        JFreeChart chart = ChartFactory.createGanttChart(title, y_label, x_label, dataset,false,false,false);
        ChartPanel panel = new ChartPanel(chart);
        add(panel);
        repaint();
        revalidate();
    }
}
    
  
  
  
   



