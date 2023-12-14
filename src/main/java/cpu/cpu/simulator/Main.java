package cpu.cpu.simulator;

import java.awt.*;
import java.util.Objects;
import java.util.Vector;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cpu.cpu.simulator.Utilities.Process;
import java.awt.event.ItemEvent;

public class Main extends javax.swing.JFrame {
    private Vector<Process> processes;
    private static int proccessId = 0;
    Color selectedColor;
    public Main() {
        initComponents();
        this.setResizable(false); 
        this.setLocationRelativeTo(null);
        processes = new Vector<>();

//        Process p1 = new Process(0,"P1", Color.RED, 0   , 17,4);
//        Process p2 = new Process(1,"P2", Color.BLUE, 3, 6, 9);
//        Process p3 = new Process(2,"P3", Color.GREEN, 4, 10,2);
//        Process p4 = new Process(3,"P4", Color.YELLOW, 29,4, 8);
//        p1.setAGFactor(20);
//        p2.setAGFactor(17);
//        p3.setAGFactor(16);
//        p4.setAGFactor(43);

        Process p0 = new Process(0,"P0", Color.RED, 0, 8, 49);
        Process p1 = new Process(1,"P1", Color.BLUE, 17, 5, 24);
        Process p2 = new Process(2,"P2", Color.GREEN, 22, 8,32);
        Process p3 = new Process(3,"P3", Color.YELLOW, 7, 6, 5);
        Process p4 = new Process(4,"P4", Color.ORANGE, 10, 9, 72);
        Process p5 = new Process(5,"P5", Color.BLACK, 25, 9, 87);
        Process p6 = new Process(6,"P6", Color.WHITE, 10, 7, 126);
        Process p7= new Process(7,"P7", Color.CYAN, 26, 7, 42);
        Process p8 = new Process(8,"P8", Color.GRAY, 3, 10, 71);
        Process p9 = new Process(9,"P9", Color.MAGENTA, 3, 10, 24);

        p0.setAGFactor(p0.getBurstTime() + p0.getArrivalTime() + 10 ); // 38
        p1.setAGFactor(p1.getBurstTime() + p1.getArrivalTime() + 10 ); // 37
        p2.setAGFactor(p2.getBurstTime() + p2.getArrivalTime() + 10 ); // 36
        p3.setAGFactor(p3.getBurstTime() + p3.getArrivalTime() + 10 ); // 25
        p4.setAGFactor(p4.getBurstTime() + p4.getArrivalTime() + 10 ); // 21
        p5.setAGFactor(p5.getBurstTime() + p5.getArrivalTime() + 10 ); // 40
        p6.setAGFactor(p6.getBurstTime() + p6.getArrivalTime() + 10 ); // 39
        p7.setAGFactor(p7.getBurstTime() + p7.getArrivalTime() + 10 ); // 19
        p8.setAGFactor(p8.getBurstTime() + p8.getArrivalTime() + 10 ); // 24
        p9.setAGFactor(p9.getBurstTime() + p9.getArrivalTime() + 10 ); // 45
        processes.add(p1);
        processes.add(p2);
        processes.add(p3);
        processes.add(p4);
        processes.add(p5);
        processes.add(p6);
        processes.add(p7);
        processes.add(p8);
        processes.add(p9);
        processes.add(p0);

        Object[][] rows = new Object[processes.size()][5];
        for (int i = 0; i < processes.size(); i++) {
            Process p = processes.get(i);
            rows[i][0] = p.getName();
            rows[i][1] = p.getArrivalTime();
            rows[i][2] = p.getBurstTime();
            rows[i][3] = p.getPriorityNumber();
            rows[i][4] = p.getAGFactor();
        }
        DefaultTableModel model = (DefaultTableModel) this.jProcessTable.getModel();
        model.setDataVector(rows, new Object[]{"Process Name", "Arrival Time", "Brust Time", "Priority Number", "AG Factor"});
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            this.jProcessTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        this.jProcessTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    this.tableChanged(row , column);
                }
            }

            private void tableChanged(int row, int column) {
                if(jProcessTable.getModel().getValueAt(row, column).equals("")) return;
                switch (column) {
                    case 0:
                        processes.get(row).setName((String) jProcessTable.getModel().getValueAt(row, column));
                        break;
                    case 1:
                        processes.get(row).setArrivalTime(Integer.parseInt(jProcessTable.getModel().getValueAt(row, column).toString()));
                        break;
                    case 2:
                        processes.get(row).setBurstTime(Integer.parseInt(jProcessTable.getModel().getValueAt(row, column).toString()));
                        processes.get(row).setRemainingTime(Integer.parseInt(jProcessTable.getModel().getValueAt(row, column).toString()));
                        break;
                    case 3:
                        processes.get(row).setPriorityNumber(Integer.parseInt( jProcessTable.getModel().getValueAt(row, column).toString()));
                        break;
                    case 4:
                        processes.get(row).setAGFactor(Double.parseDouble( jProcessTable.getModel().getValueAt(row, column).toString()));
                        break;
                    default:
                        break;
                }
            }
        });

    }
   
    public Process getProcess(Process newProcess) {
        for(Process p : processes) {
                if (p.getColor().equals(newProcess.getColor()) || p.getName().equals(newProcess.getName())) {
                   return p;
               }
        }
        processes.add(newProcess);
        return null;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSchedulingChooser = new javax.swing.JComboBox<>();
        jStartSimulate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
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
        jPanel4 = new javax.swing.JPanel();
        jContextField = new javax.swing.JTextField();
        jContextLabel = new javax.swing.JLabel();
        jQuantumLabel = new javax.swing.JLabel();
        jQuantumField = new javax.swing.JTextField();
        jAgeLabel = new javax.swing.JLabel();
        jAgeField = new javax.swing.JTextField();
        jProcessPanel = new javax.swing.JPanel();
        jProcessPane = new javax.swing.JScrollPane();
        jProcessTable = new javax.swing.JTable();
        jDeleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CPU Scheduling Simulator");

        jSchedulingChooser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jSchedulingChooser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Priority Scheduling", "AG Scheduling", "Shortest - Job First(SJF)", "Shortest - Remaining Time First (SRTF)" }));
        jSchedulingChooser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jSchedulingChooserItemStateChanged(evt);
            }
        });
        jSchedulingChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSchedulingChooserActionPerformed(evt);
            }
        });

        jStartSimulate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jStartSimulate.setText("Start Simulate");
        jStartSimulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStartSimulateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jStartSimulate, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSchedulingChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSchedulingChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jStartSimulate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
            .addGroup(jNamePanelLayout.createSequentialGroup()
                .addComponent(jNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                .addComponent(jColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jShowColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jColorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jColorPanelLayout.setVerticalGroup(
            jColorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jColorPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jColorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jShowColorLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jColorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPriorityField, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBurstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jArrivalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jNamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPriorityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jColorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jNamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jArrivalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBurstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPriorityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jColorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jAddProcessButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jAddProcessButton.setText("Add Process ");
        jAddProcessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddProcessButtonActionPerformed(evt);
            }
        });

        jContextField.setEnabled(false);
        jContextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFieldKeyTyped(evt);
            }
        });

        jContextLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jContextLabel.setText("Context Switch");
        jContextLabel.setEnabled(false);

        jQuantumLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jQuantumLabel.setText("Quantum");
        jQuantumLabel.setEnabled(false);

        jQuantumField.setEnabled(false);
        jQuantumField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFieldKeyTyped(evt);
            }
        });

        jAgeLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jAgeLabel.setText("Process Age");
        jAgeLabel.setEnabled(false);

        jAgeField.setEnabled(false);
        jAgeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jContextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jQuantumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAgeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jAgeField, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(jContextField, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                    .addComponent(jQuantumField))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jContextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jContextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jQuantumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jQuantumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAgeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAgeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jAddProcessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jAddProcessButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jProcessPane.setPreferredSize(new java.awt.Dimension(500, 500));

        jProcessTable.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jProcessTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Process Name", "Arrival Time", "Brust Time", "Priority Number", "AG Factor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jProcessPane.setViewportView(jProcessTable);

        jDeleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jDeleteButton.setText("Delete Process");
        jDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jProcessPanelLayout = new javax.swing.GroupLayout(jProcessPanel);
        jProcessPanel.setLayout(jProcessPanelLayout);
        jProcessPanelLayout.setHorizontalGroup(
            jProcessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jProcessPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jProcessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jProcessPanelLayout.createSequentialGroup()
                        .addComponent(jDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jProcessPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jProcessPanelLayout.setVerticalGroup(
            jProcessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jProcessPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProcessPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jProcessPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProcessPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                "".equals(this.jPriorityField.getText()) ) {
            JOptionPane.showMessageDialog(rootPane, "All fields must be filled out!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            Process p = new Process(proccessId++ , this.jNameField.getText() , selectedColor ,  Integer.parseInt(this.jArrivalField.getText()),
                    Integer.parseInt(this.jBurstField.getText()), Integer.parseInt(this.jPriorityField.getText()));
            if (getProcess(p) != null) {
                // More meaningful error message
                JOptionPane.showMessageDialog(rootPane, "Cannot add process with the same Color/Name!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Object[] row = {p.getName(), p.getArrivalTime(), p.getBurstTime(), p.getPriorityNumber() , p.getAGFactor()};
            DefaultTableModel model = (DefaultTableModel) this.jProcessTable.getModel();
            model.addRow(row);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < model.getColumnCount(); i++) {
                this.jProcessTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            this.jArrivalField.setText("");
            this.jBurstField.setText("");
            this.jNameField.setText("");
            this.jPriorityField.setText("");
            this.jShowColorLabel.setBackground(null);
            selectedColor = null;
        }
    }//GEN-LAST:event_jAddProcessButtonActionPerformed

    private void jStartSimulateActionPerformed(java.awt.event.ActionEvent evt) {
        boolean isContextSwitching = false , isQuantum = false , isAge = false;
        if(Objects.equals((String) jSchedulingChooser.getSelectedItem(), "Shortest - Job First(SJF)") ){
            if("".equals(this.jContextField.getText())){
                JOptionPane.showMessageDialog(this, "Please enter Context Switching" , "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                isContextSwitching = true;
            }
        }
        if(Objects.equals((String) jSchedulingChooser.getSelectedItem(), "AG Scheduling") ){
            if("".equals(this.jQuantumField.getText())){
                JOptionPane.showMessageDialog(this, "Please enter Quantum", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }else{
                isQuantum = true;
            }
        }
        if(Objects.equals((String) jSchedulingChooser.getSelectedItem(), "Shortest - Remaining Time First (SRTF)") ){
            if("".equals(this.jAgeField.getText())){
                JOptionPane.showMessageDialog(this, "Please enter Age", "ERROR", JOptionPane.ERROR_MESSAGE );
                return;
            }else{
                isAge = true;
            }
        }
        if(processes.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please add processes", "ERROR", JOptionPane.ERROR_MESSAGE );
            return;
        }
        this.setVisible(false);
        CPUSchedulingSimulator.StartGUI( this ,(String) jSchedulingChooser.getSelectedItem() , processes ,
                isContextSwitching ? Integer.parseInt(this.jContextField.getText()) : 0 ,
                isQuantum ? Integer.parseInt(this.jQuantumField.getText()) : 0,
                isAge ? Integer.parseInt(this.jAgeField.getText()) : 0
                );
        this.jQuantumField.setText("");
        this.jContextField.setText("");
        this.jAgeField.setText("");
    }
    private void jDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteButtonActionPerformed
        int  selectedRow = this.jProcessTable.getSelectedRow();
        if (selectedRow != -1) {
            deleteProcess(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row.");
        }
    }//GEN-LAST:event_jDeleteButtonActionPerformed
    
    private void deleteProcess(int selectedRow) {
        String name = (String) this.jProcessTable.getModel().getValueAt(selectedRow, 0);
        Process p = getProcess(new Process(name));
        processes.remove(p);
        String message = "Deleted Process " + name;
        JOptionPane.showMessageDialog(this, message);
        ((DefaultTableModel) this.jProcessTable.getModel()).removeRow(selectedRow);
    }

    
    private void jSchedulingChooserItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jSchedulingChooserItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if("Shortest - Job First(SJF)".equals(evt.getItem().toString())){
                this.jContextField.setEnabled(true);
                this.jContextLabel.setEnabled(true);
                this.jQuantumField.setEnabled(false);
                this.jQuantumLabel.setEnabled(false);
                this.jAgeField.setEnabled(false);
                this.jAgeLabel.setEnabled(false);

            }else if("AG Scheduling".equals(evt.getItem().toString())){
                this.jQuantumField.setEnabled(true);
                this.jQuantumLabel.setEnabled(true);
                this.jContextField.setEnabled(false);
                this.jContextLabel.setEnabled(false);
                this.jAgeField.setEnabled(false);
                this.jAgeLabel.setEnabled(false);
            }else if("Shortest - Remaining Time First (SRTF)".equals(evt.getItem().toString())){
                this.jQuantumField.setEnabled(false);
                this.jQuantumLabel.setEnabled(false);
                this.jContextField.setEnabled(false);
                this.jContextLabel.setEnabled(false);
                this.jAgeField.setEnabled(true);
                this.jAgeLabel.setEnabled(true);
            }
            else{
                this.jQuantumField.setEnabled(false);
                this.jQuantumLabel.setEnabled(false);
                this.jContextField.setEnabled(false);
                this.jContextLabel.setEnabled(false);
                this.jAgeField.setEnabled(false);
                this.jAgeLabel.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jSchedulingChooserItemStateChanged

    private void jSchedulingChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSchedulingChooserActionPerformed
        
    }//GEN-LAST:event_jSchedulingChooserActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddProcessButton;
    private javax.swing.JTextField jAgeField;
    private javax.swing.JLabel jAgeLabel;
    private javax.swing.JTextField jArrivalField;
    private javax.swing.JLabel jArrivalLabel;
    private javax.swing.JPanel jArrivalPanel;
    private javax.swing.JTextField jBurstField;
    private javax.swing.JLabel jBurstLabel;
    private javax.swing.JPanel jBurstPanel;
    private javax.swing.JButton jColorButton;
    private javax.swing.JLabel jColorLabel;
    private javax.swing.JPanel jColorPanel;
    private javax.swing.JTextField jContextField;
    private javax.swing.JLabel jContextLabel;
    private javax.swing.JButton jDeleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jNameField;
    private javax.swing.JLabel jNameLabel;
    private javax.swing.JPanel jNamePanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jPriorityField;
    private javax.swing.JLabel jPriorityLabel;
    private javax.swing.JPanel jPriorityPanel;
    private javax.swing.JScrollPane jProcessPane;
    private javax.swing.JPanel jProcessPanel;
    private javax.swing.JTable jProcessTable;
    private javax.swing.JTextField jQuantumField;
    private javax.swing.JLabel jQuantumLabel;
    private javax.swing.JComboBox<String> jSchedulingChooser;
    private javax.swing.JLabel jShowColorLabel;
    private javax.swing.JButton jStartSimulate;
    // End of variables declaration//GEN-END:variables
}

