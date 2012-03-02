/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MatchGUI.java
 *
 * Created on Feb 24, 2012, 11:42:15 AM
 */

package com.edinarobotics.gui;
import com.edinarobotics.file.*;
import com.edinarobotics.gui.utilities.*;
import com.edinarobotics.scout.Main;

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;


/*
 * @author aoneill
 * @note Made by the Netbeans built-in GUI creator
 */
public class MatchGUI extends javax.swing.JFrame
{
    private static String VERSION = "";
    private static FileScanner scan = new FileScanner();
    private static Extracter ext = new Extracter();

    private String dataTableHeader[] = new String[] {"Team", "Auto Score", "Main Game", "End Game", "Penalties"};
    private String matchTableHeader[] = new String[] {"Match"};
    private static String matchList[];
    private static String recentData[][];
    private String matchPath = Main.currentDir + "/" + Main.workspaceFolderName + "/" + Main.matchFolderName;

    private static final int DATA_POINTS = 5;

    /** Creates new form MatchGUI */
    public MatchGUI()
    {
        initComponents();

        sortColComboBox.setModel(new DefaultComboBoxModel(dataTableHeader));

        init();
    }

    public MatchGUI(String version)
    {
        initComponents();
        
        VERSION = version;

        sortColComboBox.setModel(new DefaultComboBoxModel(dataTableHeader));

        init();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataScrollPane = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        matchScrollPane = new javax.swing.JScrollPane();
        matchTable = new javax.swing.JTable();
        sortDirComboBox = new javax.swing.JComboBox();
        sortColComboBox = new javax.swing.JComboBox();
        sortedByLabel = new javax.swing.JLabel();
        resultsLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Team", "Auto Score", "Main Game", "End Game", "Penalties"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dataScrollPane.setViewportView(dataTable);

        matchTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Match"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        matchTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                matchTableMousePressed(evt);
            }
        });
        matchScrollPane.setViewportView(matchTable);

        sortDirComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Low to High", "High to Low" }));
        sortDirComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortDirComboBoxActionPerformed(evt);
            }
        });

        sortColComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Team", "Auto Score", "Main Game", "End Game" }));
        sortColComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortColComboBoxActionPerformed(evt);
            }
        });

        sortedByLabel.setText("Data sorted by");

        resultsLabel.setText("with results");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(matchScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 129, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(13, 13, 13)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(sortedByLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(sortColComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(resultsLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(sortDirComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(dataScrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 508, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(matchScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(sortDirComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(resultsLabel)
                            .add(sortColComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(sortedByLabel))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(dataScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void matchTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_matchTableMousePressed
        // TODO add your handling code here:
        int matchNumber = getMatchNumber();
        showData(matchPath, matchNumber);
    }//GEN-LAST:event_matchTableMousePressed

    private void sortDirComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortDirComboBoxActionPerformed
        // TODO add your handling code here:
        sortColComboBoxActionPerformed(evt);
    }//GEN-LAST:event_sortDirComboBoxActionPerformed

    private void sortColComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortColComboBoxActionPerformed
        // TODO add your handling code here:
        Sorter sort = new Sorter(DATA_POINTS);

        String optionChosen = sortColComboBox.getSelectedItem().toString();

        int mode = sortDirComboBox.getSelectedItem().toString().equals("Low to High") ?
            Sorter.LOW_TO_HIGH : Sorter.HIGH_TO_LOW;

        String data[][] = {};

        if(optionChosen.equals(dataTableHeader[0]))
        {
            data = sort.sortBest(recentData, 0, mode);
        }
        else if(optionChosen.equals(dataTableHeader[1]))
        {
            data = sort.sortBest(recentData, 1, mode);
        }
        else if(optionChosen.equals(dataTableHeader[2]))
        {
            data = sort.sortBest(recentData, 2, mode);
        }
        else if(optionChosen.equals(dataTableHeader[3]))
        {
            data = sort.sortBest(recentData, 3, mode);
        }
        else if(optionChosen.equals(dataTableHeader[4]))
        {
            // Penalties box! Make another sorter!
            data = sort.filterPenalties(recentData, 4, mode);
        }
        else
        {
            System.err.println("OH GOD! THE BLOOD!! ITS EVERYWHERE");
            System.exit(1);
        }

        displayData(data);
    }//GEN-LAST:event_sortColComboBoxActionPerformed

    private void init()
    {
        String list[] = getMatchList(matchPath, Main.matchListFile);
        showMatches(list);

        showData(matchPath, Integer.parseInt(list[0]));

        setVisible(true);
    }

    private void showData(String matchFolder, int matchNumber)
    {
        String table[][] = getTableData(matchFolder, matchNumber);

        recentData = table;

        displayData(table);
    }

    public void showMatches(String list[])
    {
        String data[][] = new String[list.length][1];

        for(int i = 0; i < list.length; i++)
        {
            data[i][0] = list[i];
        }

        matchTable.setModel(new DefaultTableModel(data, matchTableHeader));
    }

    private String[][] getTableData(String matchFolder, int matchNumber)
    {
        scan.openFile(matchFolder, String.format("Match_%d.txt", matchNumber));

        ArrayList<String> list = new ArrayList<String>();
        while(scan.hasNextEntry())
        {
            list.add(scan.getNextLine());
        }
        list.remove(0);

        String data[] = new String[list.size()];
        for(int i = 0; i < list.size(); i++)
        {
            data[i] = list.get(i).toString();
        }

        String table[][] = new String[list.size()][DATA_POINTS];

        for(int i = 0; i < list.size(); i++)
        {
            table[i][0] = ext.extractEntry(data[i], 1);
            table[i][1] = ext.extractEntry(data[i], 2);
            table[i][2] = ext.extractEntry(data[i], 3);
            table[i][3] = ext.extractEntry(data[i], 4);
            table[i][4] = ext.extractEntry(data[i], 5);
        }

        return table;
    }

    private String[] getMatchList(String matchListFolder, String matchListFileName)
    {
        scan.openFile(matchListFolder, matchListFileName);

        ArrayList<String> list = new ArrayList<String>();
        while(scan.hasNextEntry())
        {
            list.add(scan.getNextLine());
        }
        list.remove(0);


        String data[] = new String[list.size()];
        for(int i = 0; i < list.size(); i++)
        {
            data[i] = list.get(i).toString();
        }

        matchList = data;

        return data;
    }

    private int getMatchNumber()
    {
        return Integer.parseInt(matchTable.getValueAt(matchTable.getSelectedRow(), matchTable.getSelectedColumn()).toString());
    }

    private void displayData(String data[][])
    {
        dataTable.setModel(new DefaultTableModel(data, dataTableHeader));
    }

    private void updateTitle(String suffix)
    {
        String titleBase = "Match ";
        setTitle(titleBase + suffix);
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MatchGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane dataScrollPane;
    private javax.swing.JTable dataTable;
    private javax.swing.JScrollPane matchScrollPane;
    private javax.swing.JTable matchTable;
    private javax.swing.JLabel resultsLabel;
    private javax.swing.JComboBox sortColComboBox;
    private javax.swing.JComboBox sortDirComboBox;
    private javax.swing.JLabel sortedByLabel;
    // End of variables declaration//GEN-END:variables

}