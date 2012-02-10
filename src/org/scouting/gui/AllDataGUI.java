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
    private static String tableHeader[] = new String[] {"Team Name", "Overall Score", "Auto Score", "Main Score", "End Score", "Penalty Number"};
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
            data = sortBestBubble(allData, 0);
        }
        else if(optionChosen.equals("Overall Score"))
        {
            //data = sortBest(allData, 0);
            data = sortBestBubble(allData, 4);
        }
        else if(optionChosen.equals("Autonomous"))
        {
            //data = sortBest(allData, 0);
            data = sortBestBubble(allData, 1);
        }
        else if(optionChosen.equals("Main Game"))
        {
            //data = sortBest(allData, 0);
            data = sortBestBubble(allData, 2);
        }
        else if(optionChosen.equals("End Game"))
        {
            //data = sortBest(allData, 0);
            data = sortBestBubble(allData, 3);
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
        data = sortBest(allData, 0);
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

//    public String[][] sortBestRowMethod(String array[][], int member)
//    {
//        String result[][];
//
//        DataRow list[] = new DataRow[teamCount];
//        DataRow dr1;
//        DataRow dr2;
//        DataRow buffer;
//        DataRow parser;
//
//        for(int mainC = 0; mainC < teamCount; mainC++)
//        {
//            list[mainC] = new DataRow(array, mainC, DATA_POINTS);
//        }
//
//        for(int mainC = 0; mainC < Math.pow(teamCount, 2); mainC++)
//        {
//            for(int tIter = 1; tIter < teamCount; tIter++)
//            {
//                dr1 = new DataRow(array, tIter, DATA_POINTS);
//                dr2 = new DataRow(array, tIter - 1, DATA_POINTS);
//
//                if(Double.parseDouble(dr1.valueAt(member)) > Double.parseDouble(dr2.valueAt(member)))
//                {
//
//                }
//            }
//        }
//
//        return result;
//    }

    public String[][] sortBestBubble(String array[][], int member)
    {
        String result[][] = array;

        for(int mainC = 0; mainC < Math.pow(teamCount, 2); mainC++)
        {
            for(int tIter = 1; tIter < teamCount; tIter++)
            {
                double value0 = Double.parseDouble(array[tIter][0]);
                double value1 = Double.parseDouble(array[tIter][1]);
                double value2 = Double.parseDouble(array[tIter][2]);
                double value3 = Double.parseDouble(array[tIter][3]);
                double value4 = Double.parseDouble(array[tIter][4]);
                double value5 = Double.parseDouble(array[tIter][5]);
                double value6 = Double.parseDouble(array[tIter][6]);
                double valueM = Double.parseDouble(array[tIter][member]);
                
                double valueN10 = Double.parseDouble(array[tIter - 1][0]);
                double valueN11 = Double.parseDouble(array[tIter - 1][1]);
                double valueN12 = Double.parseDouble(array[tIter - 1][2]);
                double valueN13 = Double.parseDouble(array[tIter - 1][3]);
                double valueN14 = Double.parseDouble(array[tIter - 1][4]);
                double valueN15 = Double.parseDouble(array[tIter - 1][5]);
                double valueN16 = Double.parseDouble(array[tIter - 1][6]);
                double valueN1M = Double.parseDouble(array[tIter - 1][member]);
                if(valueM > valueN1M)
                {
                    result[tIter][0] = String.valueOf(valueN10);
                    result[tIter][1] = String.valueOf(valueN11);
                    result[tIter][2] = String.valueOf(valueN12);
                    result[tIter][3] = String.valueOf(valueN13);
                    result[tIter][4] = String.valueOf(valueN14);
                    result[tIter][5] = String.valueOf(valueN15);
                    result[tIter][6] = String.valueOf(valueN16);
                    result[tIter][member] = String.valueOf(valueN1M);

                    result[tIter - 1][0] = String.valueOf(value0);
                    result[tIter - 1][1] = String.valueOf(value1);
                    result[tIter - 1][2] = String.valueOf(value2);
                    result[tIter - 1][3] = String.valueOf(value3);
                    result[tIter - 1][4] = String.valueOf(value4);
                    result[tIter - 1][5] = String.valueOf(value5);
                    result[tIter - 1][6] = String.valueOf(value6);

                    result[tIter - 1][member] = String.valueOf(valueM);
                }
            }
        }

        return result;
    }

    public String[][] sortBest(String array[][], int member)
    {
        System.out.println("------------------------------------------");
        String result[][] = new String[teamCount][7];

        double topMember = 0;
        double topAuto = 0;
        double topMain = 0;
        double topEnd = 0;
        double topOverall = 0;
        String topPenalties = "";
        String penalties = "";
        int teamNumber = 0;
        for(int i = 0; i < teamCount; i++)
        {
            System.out.println("---");
            System.out.println("Trying Team " + array[i][0] + "'s value of " + array[i][member] + " in member column " + member);
            double firstNumber = Double.parseDouble(array[i][member]);
            if(firstNumber > topMember)
            {
                System.out.println("Team " + array[i][0] + "'s Score is the best so far!");
                teamNumber = Integer.parseInt(array[i][0]);
                topAuto = Double.parseDouble(array[i][1]);
                topMain = Double.parseDouble(array[i][2]);
                topEnd = Double.parseDouble(array[i][3]);
                topOverall = Double.parseDouble(array[i][4]);
                topPenalties = array[i][5];
                penalties = array[i][6];
                topMember = Double.parseDouble(array[i][member]);
            }
        }

        result[0][0] = Integer.toString(teamNumber);
        result[0][1] = Double.toString(roundTwoDecimals(topAuto));
        result[0][2] = Double.toString(roundTwoDecimals(topMain));
        result[0][3] = Double.toString(roundTwoDecimals(topEnd));
        result[0][4] = Double.toString(roundTwoDecimals(topOverall));
        result[0][5] = topPenalties;
        result[0][6] = penalties;
        result[0][member] = Double.toString(roundTwoDecimals(topMember));

        if(member == 0)
        {
            result[0][member] = Integer.toString((int) topMember);
        }

        System.out.println("-+-+-");
        System.out.println("+++ Team " + teamNumber + "'is the best with " + topMember + " as a value!");
        System.out.println("-+-+-\n");

        for(int i = 0; i < teamCount - 1; i++)
        {
            topMember = 0;
            topAuto = 0;
            topMain = 0;
            topEnd = 0;
            topOverall = 0;
            topPenalties = "";
            penalties = "";
            teamNumber = 0;
            for(int j = 0; j < teamCount; j++)
            {
                System.out.println("---");
                System.out.println("Trying Team " + array[j][0] + "'s value of " + array[i][member] + " in member column " + member);

                double currentNumber = Double.parseDouble(array[j][member]);
                double lastNumber = Double.parseDouble(result[i][member]);

                if(currentNumber > topMember && currentNumber < lastNumber)
                {
                    teamNumber = Integer.parseInt(array[j][0]);
                    topAuto = Double.parseDouble(array[j][1]);
                    topMain = Double.parseDouble(array[j][2]);
                    topEnd = Double.parseDouble(array[j][3]);
                    topOverall = Double.parseDouble(array[j][4]);
                    topPenalties = array[j][5];
                    penalties = array[j][6];
                    topMember = Double.parseDouble(array[j][member]);
                }
            }

            //TODO: Impliment Stuff here!

            result[i + 1][0] = Integer.toString(teamNumber);
            result[i + 1][1] = Double.toString(roundTwoDecimals(topAuto));
            result[i + 1][2] = Double.toString(roundTwoDecimals(topMain));
            result[i + 1][3] = Double.toString(roundTwoDecimals(topEnd));
            result[i + 1][4] = Double.toString(roundTwoDecimals(topOverall));
            result[i + 1][5] = topPenalties;
            result[i + 1][6] = penalties;
            result[i + 1][member] = Double.toString(roundTwoDecimals(topMember));

            if(member == 0)
            {
                result[i + 1][member] = Integer.toString((int) topMember);
            }
        }

        return result;
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
