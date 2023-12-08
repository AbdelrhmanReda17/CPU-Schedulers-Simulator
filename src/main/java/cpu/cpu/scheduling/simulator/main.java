package cpu.cpu.scheduling.simulator;

import java.awt.*;
import java.util.Map;
import java.util.Optional;
import java.util.Vector;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cpu.cpu.scheduling.simulator.Utilities.Process;

public class main extends javax.swing.JFrame {
    private Vector<Process> processes;
    Color selectedColor;
    public main() {
        initComponents();
        this.setResizable(false);
        processes = new Vector<>();
    }
    public boolean insertProcess(Process newProcess) {
        for(Process p : processes) {
            if (p.getColor().toString().equals(newProcess.getColor().toString()) || p.getName().equals(newProcess.getName())) {
                return false;
            }
        }
        processes.add(newProcess);
        return true;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jArrivalPanel = new javax.swing.JPanel();
        jArrivalLabel = new javax.swing.JLabel();
        jArrivalField = new javax.swing.JTextField();
        jNamePanel = new javax.swing.JPanel();
        jNameLabel = new javax.swing.JLabel();
        jNameField = new javax.swing.JTextField();
        jColorPanel = new javax.swing.JPanel();
        jColorButton = new javax.swing.JButton();
        jColorLabel = new javax.swing.JLabel();
        jShowColorLabel = new javax.swing.JLabel();
        jBurstPanel = new javax.swing.JPanel();
        jBurstLabel = new javax.swing.JLabel();
        jBurstField = new javax.swing.JTextField();
        jPriorityPanel = new javax.swing.JPanel();
        jPriorityLabel = new javax.swing.JLabel();
        jPriorityField = new javax.swing.JTextField();
        jAddProcessButton = new javax.swing.JButton();
        jProcessPanel = new javax.swing.JPanel();
        jProcessPane = new javax.swing.JScrollPane();
        jProcessTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CPU Scheduling Simulator");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Start Simulate");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jArrivalLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jArrivalLabel.setText("Process Arrival Time");

        jArrivalField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jArrivalPanelLayout = new javax.swing.GroupLayout(jArrivalPanel);
        jArrivalPanel.setLayout(jArrivalPanelLayout);
        jArrivalPanelLayout.setHorizontalGroup(
            jArrivalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jArrivalPanelLayout.createSequentialGroup()
                .addComponent(jArrivalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jArrivalField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jArrivalPanelLayout.setVerticalGroup(
            jArrivalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jArrivalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jArrivalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jArrivalLabel)
                    .addComponent(jArrivalField))
                .addContainerGap())
        );

        jNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jNameLabel.setText("Process Name");

        jNameField.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        javax.swing.GroupLayout jNamePanelLayout = new javax.swing.GroupLayout(jNamePanel);
        jNamePanel.setLayout(jNamePanelLayout);
        jNamePanelLayout.setHorizontalGroup(
            jNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jNamePanelLayout.createSequentialGroup()
                .addComponent(jNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(jNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jNamePanelLayout.setVerticalGroup(
            jNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jNamePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jColorButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jColorButton.setText("Choose Color ");
        jColorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jColorButtonActionPerformed(evt);
            }
        });

        jColorLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jColorLabel.setText("Color");

        javax.swing.GroupLayout jColorPanelLayout = new javax.swing.GroupLayout(jColorPanel);
        jColorPanel.setLayout(jColorPanelLayout);
        jColorPanelLayout.setHorizontalGroup(
            jColorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jColorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jColorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jShowColorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jColorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jColorPanelLayout.setVerticalGroup(
            jColorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jColorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jColorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jShowColorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jColorButton, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jColorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jBurstLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBurstLabel.setText("Process Brust Time");

        jBurstField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jBurstPanelLayout = new javax.swing.GroupLayout(jBurstPanel);
        jBurstPanel.setLayout(jBurstPanelLayout);
        jBurstPanelLayout.setHorizontalGroup(
            jBurstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBurstPanelLayout.createSequentialGroup()
                .addComponent(jBurstLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jBurstField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jBurstPanelLayout.setVerticalGroup(
            jBurstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jBurstPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jBurstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBurstLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBurstField, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPriorityLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPriorityLabel.setText("Process Priority Number");

        jPriorityField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPriorityPanelLayout = new javax.swing.GroupLayout(jPriorityPanel);
        jPriorityPanel.setLayout(jPriorityPanelLayout);
        jPriorityPanelLayout.setHorizontalGroup(
            jPriorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPriorityPanelLayout.createSequentialGroup()
                .addComponent(jPriorityLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jPriorityField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPriorityPanelLayout.setVerticalGroup(
            jPriorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPriorityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPriorityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPriorityLabel)
                    .addComponent(jPriorityField))
                .addContainerGap())
        );

        jAddProcessButton.setText("Add Process");
        jAddProcessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddProcessButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(191, 191, 191))
            .addComponent(jArrivalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jNamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBurstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jColorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPriorityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jAddProcessButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(8, 8, 8)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jNamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jArrivalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBurstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPriorityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jColorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jAddProcessButton, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jProcessPane.setPreferredSize(new java.awt.Dimension(500, 500));

        jProcessTable.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jProcessTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Process Name", "Arrival Time", "Brust Time", "Priority Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jProcessPane.setViewportView(jProcessTable);

        javax.swing.GroupLayout jProcessPanelLayout = new javax.swing.GroupLayout(jProcessPanel);
        jProcessPanel.setLayout(jProcessPanelLayout);
        jProcessPanelLayout.setHorizontalGroup(
            jProcessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jProcessPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProcessPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jProcessPanelLayout.setVerticalGroup(
            jProcessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jProcessPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProcessPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProcessPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProcessPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFieldKeyTyped
        char enter = evt.getKeyChar();
        if(!(Character.isDigit(enter))){
            evt.consume();
        }
    }//GEN-LAST:event_jFieldKeyTyped
    private void jColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jColorButtonActionPerformed
        selectedColor = JColorChooser.showDialog(null, "Choose Color", Color.BLACK);
        this.jShowColorLabel.setOpaque(true);
        this.jShowColorLabel.setBackground(selectedColor);
    }//GEN-LAST:event_jColorButtonActionPerformed
    private void jAddProcessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddProcessButtonActionPerformed
        if ("".equals(this.jArrivalField.getText()) || "".equals(this.jBurstField.getText()) ||
                null == selectedColor ||
                "".equals(this.jNameField.getText()) ||
                "".equals(this.jPriorityField.getText())) {
            JOptionPane.showMessageDialog(rootPane, "All fields must be filled out!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            Process p = new Process(this.jNameField.getText() , selectedColor ,  Integer.parseInt(this.jArrivalField.getText()),
                    Integer.parseInt(this.jBurstField.getText()), Integer.parseInt(this.jPriorityField.getText()));
            if (!insertProcess(p)) {
                // More meaningful error message
                JOptionPane.showMessageDialog(rootPane, "Cannot add process with the same Color/Name!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Object[] row = {p.getName(), p.getArrivalTime(), p.getBurstTime(), p.getPriorityNumber()};
            DefaultTableModel model = (DefaultTableModel) this.jProcessTable.getModel();
            model.addRow(row);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < model.getColumnCount(); i++) {
                this.jProcessTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }//GEN-LAST:event_jAddProcessButtonActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new main().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddProcessButton;
    private javax.swing.JTextField jArrivalField;
    private javax.swing.JLabel jArrivalLabel;
    private javax.swing.JPanel jArrivalPanel;
    private javax.swing.JTextField jBurstField;
    private javax.swing.JLabel jBurstLabel;
    private javax.swing.JPanel jBurstPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jColorButton;
    private javax.swing.JLabel jColorLabel;
    private javax.swing.JPanel jColorPanel;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jNameField;
    private javax.swing.JLabel jNameLabel;
    private javax.swing.JPanel jNamePanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jPriorityField;
    private javax.swing.JLabel jPriorityLabel;
    private javax.swing.JPanel jPriorityPanel;
    private javax.swing.JScrollPane jProcessPane;
    private javax.swing.JPanel jProcessPanel;
    private javax.swing.JTable jProcessTable;
    private javax.swing.JLabel jShowColorLabel;
    // End of variables declaration//GEN-END:variables
}

