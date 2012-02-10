/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AllDataGUI.java
 *
 * Created on Dec 17, 2011, 10:47:18 PM
 */

package org.scouting.gui;

import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * @author aoneill
 * @note Made by the Netbeans built-in GUI creator
 */
public class AllDataGUI extends javax.swing.JFrame
{
    private static String tableHeader[] = new String[] {"Team Name", "Auto Score", "Main Score", "End Score", "Overall Score", "Penalty Number"};
    private String[][] allData;
    private int teamCount;
    private static final int DATA_POINTS = 7;
    private String DECIMAL_FORMAT = "#.###";


    /** Creates new form AllDataGUI */
    public AllDataGUI()
    {
        initComponents();
        updateTitle("Team Number");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();

        setTitle("All Scores");

        jLabel1.setText("All Average Scores By");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Team Number", "Overall Score", "Autonomous", "Main Game", "End Game" }));
        jComboBox1.setFocusable(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        dataTable.setMinimumSize(new java.awt.Dimension(64, 64));
        jScrollPane1.setViewportView(dataTable);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 346, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String optionChosen = jComboBox1.getSelectedItem().toString();
        String data[][] = {};

        if(optionChosen.equals("Team Number"))
        {
            //data = sortBest(allData, 0);
            data = sortBestRowMethod(allData, 0);
        }
        else if(optionChosen.equals("Overall Score"))
        {
            //data = sortBest(allData, 0);
            data = sortBestRowMethod(allData, 4);
        }
        else if(optionChosen.equals("Autonomous"))
        {
            //data = sortBest(allData, 0);
            data = sortBestRowMethod(allData, 1);
        }
        else if(optionChosen.equals("Main Game"))
        {
            //data = sortBest(allData, 0);
            data = sortBestRowMethod(allData, 2);
        }
        else if(optionChosen.equals("End Game"))
        {
            //data = sortBest(allData, 0);
            data = sortBestRowMethod(allData, 3);
        }
        else
        {
            System.out.println("OH GOD! THE BLOOD!! ITS EVERYWHERE");
            System.exit(1);
        }

        updateTitle(optionChosen);
        writeToTable(data);

    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllDataGUI().setVisible(true);
            }
        });
    }

    public void initTable()
    {
        String data[][] = {};
        data = sortBestRowMethod(allData, 0);
        writeToTable(data);
    }

    public void writeToTable(String data[][])
    {
        dataTable.setModel(new DefaultTableModel(data, tableHeader));
    }

    public void setAllData(String data[][])
    {
        allData = data;
    }

    public String[][] sortBestRowMethod(String array[][], int member)
    {
        System.out.println("--------------------------");
        
        DataRow list[] = new DataRow[teamCount];
        DataRow dr2;
        DataRow dr1;
        DataRow parser = new DataRow();

        System.out.println("Creating DataRow list...");
        for(int mainC = 0; mainC < teamCount; mainC++)
        {
            list[mainC] = new DataRow(array, mainC, DATA_POINTS);
            System.out.println("Storing Team " + list[mainC].valueAt(0));
        }

        System.out.println("--------------------------");
        for(int mainC = 0; mainC < Math.pow(teamCount, 2); mainC++)
        {
            for(int tIter = 1; tIter < teamCount; tIter++)
            {
                dr1 = list[tIter - 1];
                System.out.println("Trying Team " + dr1.valueAt(0) + "'s value of " + dr1.valueAt(member) + " at member " + member);

                dr2 = list[tIter];
                System.out.println("Trying Team " + dr2.valueAt(0) + "'s value of " + dr2.valueAt(member) + " at member " + member);
                

                if(Double.parseDouble(dr2.valueAt(member)) > Double.parseDouble(dr1.valueAt(member)))
                {
                    System.out.println("Team " + dr2.valueAt(0) + " is better than team " + dr1.valueAt(0));
                    list[tIter] = dr1;
                    list[tIter - 1] = dr2;
                    System.out.println("Swapping teams...");
                }

                System.out.println("--------------------------");
                System.out.println("Team List");
                for(int i = 0; i < teamCount; i++)
                {
                    System.out.println("> " + list[i].valueAt(0));
                }
                System.out.println("--------------------------");
            }
        }

        return parser.dataRowArrayToStringArray(list, DATA_POINTS);
    }

    double roundTwoDecimals(double num)
    {
        DecimalFormat form = new DecimalFormat(DECIMAL_FORMAT);
        return Double.valueOf(form.format(num));
    }
    
    void setTeamCount(int num)
    {
        teamCount = num;
    }

    private void updateTitle(String suffix)
    {
        setTitle("All Scores - " + suffix);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dataTable;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


}
