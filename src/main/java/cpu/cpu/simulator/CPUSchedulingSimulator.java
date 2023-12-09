package cpu.cpu.simulator;

import cpu.cpu.scheduling.Scheduling;
import cpu.cpu.scheduling.SchedulingFactory;
import java.util.Vector;
import cpu.cpu.simulator.Utilities.Process;

import javax.swing.JFrame;


public class CPUSchedulingSimulator extends javax.swing.JFrame {
    // Scheduling Parameter
    private static JFrame main;
    private static Scheduling scheduling;

    public static Scheduling getScheduling() {
        return scheduling;
    }

    public CPUSchedulingSimulator() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void StartLogic(){
        scheduling.execute();
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       main.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    public static void StartGUI(JFrame frame ,String SchedulingName , Vector<Process> processes , int ContextSwitchingTime , int QuantumTime){
        main = frame;
        scheduling = SchedulingFactory.createScheduling(SchedulingName , processes , ContextSwitchingTime , QuantumTime);
        // Call the Factor with the Scheduling Name
        java.awt.EventQueue.invokeLater(() -> {
            new CPUSchedulingSimulator().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
