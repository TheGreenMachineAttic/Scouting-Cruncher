/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ResultGUI.java
 *
 * Created on Nov 23, 2011, 8:40:02 AM
 */

package com.edinarobotics.gui;

import com.edinarobotics.export.ExportGUI;
import com.edinarobotics.scout.Main;

import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;

/*
 * @author aoneill
 * @note Made by the Netbeans built-in GUI creator
 */



public class ResultGUI extends javax.swing.JFrame
{
    private static String tableHeader[] = {"Team Name", "Score"};
    private static String autoData[][];
    private static String mainData[][];
    private static String endData[][];
    private static String totalData[][];
    private static String[][] allData;
    private static String[] teamList;

    private int teamCount;
    private String commentDir;
    private String DECIMAL_FORMAT = "#.###";

    /** Creates new form ResultGUI */
    public ResultGUI()
    {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        avrAutoScorePanel = new javax.swing.JPanel();
        autoLabel = new javax.swing.JLabel();
        autoScrolPane = new javax.swing.JScrollPane();
        autoTable = new javax.swing.JTable();
        avrMainScorePanel = new javax.swing.JPanel();
        mainLabel = new javax.swing.JLabel();
        mainScrollPane = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        avrEndScorePanel = new javax.swing.JPanel();
        endLabel = new javax.swing.JLabel();
        endScrollPane = new javax.swing.JScrollPane();
        endTable = new javax.swing.JTable();
        avrTotalScorePanel = new javax.swing.JPanel();
        totalLabel = new javax.swing.JLabel();
        totalScrollPane = new javax.swing.JScrollPane();
        totalTable = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        dataMenu = new javax.swing.JMenu();
        averageData = new javax.swing.JMenuItem();
        dataPerTeam = new javax.swing.JMenuItem();
        dataByMatch = new javax.swing.JMenuItem();
        commentData = new javax.swing.JMenuItem();
        penaltyData = new javax.swing.JMenuItem();
        export = new javax.swing.JMenuItem();
        help = new javax.swing.JMenu();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Results");
        setResizable(false);

        avrAutoScorePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        autoLabel.setText("Best Average Autonomous Score");

        autoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", ""},
                {null, null},
                {"", null},
                {null, null}
            },
            new String [] {
                "Team Name", "Overall"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        autoTable.setFocusable(false);
        autoScrolPane.setViewportView(autoTable);

        org.jdesktop.layout.GroupLayout avrAutoScorePanelLayout = new org.jdesktop.layout.GroupLayout(avrAutoScorePanel);
        avrAutoScorePanel.setLayout(avrAutoScorePanelLayout);
        avrAutoScorePanelLayout.setHorizontalGroup(
            avrAutoScorePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(avrAutoScorePanelLayout.createSequentialGroup()
                .add(autoLabel)
                .addContainerGap(116, Short.MAX_VALUE))
            .add(autoScrolPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );
        avrAutoScorePanelLayout.setVerticalGroup(
            avrAutoScorePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(avrAutoScorePanelLayout.createSequentialGroup()
                .add(autoLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(autoScrolPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
        );

        avrMainScorePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        mainLabel.setText("Best Average Main Game Score");

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", ""},
                {null, null},
                {"", null},
                {null, null}
            },
            new String [] {
                "Team Name", "Overall"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        mainTable.setFocusable(false);
        mainScrollPane.setViewportView(mainTable);

        org.jdesktop.layout.GroupLayout avrMainScorePanelLayout = new org.jdesktop.layout.GroupLayout(avrMainScorePanel);
        avrMainScorePanel.setLayout(avrMainScorePanelLayout);
        avrMainScorePanelLayout.setHorizontalGroup(
            avrMainScorePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(avrMainScorePanelLayout.createSequentialGroup()
                .add(mainLabel)
                .add(69, 69, 69))
            .add(mainScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );
        avrMainScorePanelLayout.setVerticalGroup(
            avrMainScorePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(avrMainScorePanelLayout.createSequentialGroup()
                .add(mainLabel)
                .add(9, 9, 9)
                .add(mainScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
        );

        avrEndScorePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        endLabel.setText("Best Average End Game Score");

        endTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", ""},
                {null, null},
                {"", null},
                {null, null}
            },
            new String [] {
                "Team Name", "Overall"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        endTable.setFocusable(false);
        endScrollPane.setViewportView(endTable);

        org.jdesktop.layout.GroupLayout avrEndScorePanelLayout = new org.jdesktop.layout.GroupLayout(avrEndScorePanel);
        avrEndScorePanel.setLayout(avrEndScorePanelLayout);
        avrEndScorePanelLayout.setHorizontalGroup(
            avrEndScorePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(endLabel)
            .add(endScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
        );
        avrEndScorePanelLayout.setVerticalGroup(
            avrEndScorePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(avrEndScorePanelLayout.createSequentialGroup()
                .add(endLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(endScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
        );

        avrTotalScorePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        totalLabel.setText("Best Average Overall Score");

        totalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", ""},
                {null, null},
                {"", null},
                {null, null}
            },
            new String [] {
                "Team Name", "Overall"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        totalTable.setFocusable(false);
        totalScrollPane.setViewportView(totalTable);

        org.jdesktop.layout.GroupLayout avrTotalScorePanelLayout = new org.jdesktop.layout.GroupLayout(avrTotalScorePanel);
        avrTotalScorePanel.setLayout(avrTotalScorePanelLayout);
        avrTotalScorePanelLayout.setHorizontalGroup(
            avrTotalScorePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(avrTotalScorePanelLayout.createSequentialGroup()
                .add(totalLabel)
                .add(97, 97, 97))
            .add(totalScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
        );
        avrTotalScorePanelLayout.setVerticalGroup(
            avrTotalScorePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(avrTotalScorePanelLayout.createSequentialGroup()
                .add(totalLabel)
                .add(9, 9, 9)
                .add(totalScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
        );

        file.setText("File");

        dataMenu.setText("Data");

        averageData.setText("See All Average Data");
        averageData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                averageDataActionPerformed(evt);
            }
        });
        dataMenu.add(averageData);

        dataPerTeam.setText("See All Data By Team");
        dataPerTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataPerTeamActionPerformed(evt);
            }
        });
        dataMenu.add(dataPerTeam);

        dataByMatch.setText("See Data By Match");
        dataByMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataByMatchActionPerformed(evt);
            }
        });
        dataMenu.add(dataByMatch);

        commentData.setText("See Comments");
        commentData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentDataActionPerformed(evt);
            }
        });
        dataMenu.add(commentData);

        penaltyData.setText("See Penalties");
        penaltyData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penaltyDataActionPerformed(evt);
            }
        });
        dataMenu.add(penaltyData);

        file.add(dataMenu);

        export.setText("Export Data...");
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });
        file.add(export);

        menuBar.add(file);

        help.setText("Help");

        about.setText("About");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        help.add(about);

        menuBar.add(help);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(avrAutoScorePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(avrMainScorePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(avrEndScorePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, avrTotalScorePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(avrEndScorePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(avrTotalScorePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(avrAutoScorePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(avrMainScorePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void averageDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_averageDataActionPerformed
        // TODO add your handling code here:
        AllDataGUI aGUI= new AllDataGUI(allData);
    }//GEN-LAST:event_averageDataActionPerformed

    private void dataPerTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataPerTeamActionPerformed
        // TODO add your handling code here:
        DataByTeamGUI dbtGUI = new DataByTeamGUI(teamList);
    }//GEN-LAST:event_dataPerTeamActionPerformed

    private void dataByMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataByMatchActionPerformed
        // TODO add your handling code here:
        MatchGUI mGUI = new MatchGUI();
    }//GEN-LAST:event_dataByMatchActionPerformed

    private void commentDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentDataActionPerformed
        // TODO add your handling code here:
        CommentsGUI cGUI = new CommentsGUI(teamCount, commentDir, allData);
    }//GEN-LAST:event_commentDataActionPerformed

    private void penaltyDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penaltyDataActionPerformed
        // TODO add your handling code here:
        PenaltiesGUI pGUI = new PenaltiesGUI(teamCount, allData);
    }//GEN-LAST:event_penaltyDataActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        // TODO add your handling code here:
        AboutGUI aGUI = new AboutGUI(Main.VERSION);
    }//GEN-LAST:event_aboutActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        // TODO add your handling code here:
        ExportGUI eGUI = new ExportGUI(allData);
    }//GEN-LAST:event_exportActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResultGUI().setVisible(true);
            }
        });
    }

    public void setAutonomousTable(String data[][])
    {
        autoData = data;
        String[][] displayData = new String[5][2];
        int counter = 0;
        try
        {
            while(!data[counter][0].equals(null))
            {
                counter++;
            }
        }
        catch(Exception e) {}

        if(counter > 5)
        {
            for(int i = 0; i < 5; i++)
            {
                displayData[i][0] = data[i][0];
                displayData[i][1] = Double.toString(roundTwoDecimals(Double.parseDouble(data[i][1])));
            }

            autoTable.setModel(new DefaultTableModel(displayData, tableHeader));
        }
        else
        {
            autoTable.setModel(new DefaultTableModel(autoData, tableHeader));
        }
    }

    public void setMainTable(String data[][])
    {
        mainData = data;
        String[][] displayData = new String[5][2];
        int counter = 0;
        try
        {
            while(!data[counter][0].equals(null))
            {
                counter++;
            }
        }
        catch(Exception e) {}

        if(counter > 5)
        {
            for(int i = 0; i < 5; i++)
            {
                displayData[i][0] = data[i][0];
                displayData[i][1] = Double.toString(roundTwoDecimals(Double.parseDouble(data[i][1])));
            }

            mainTable.setModel(new DefaultTableModel(displayData, tableHeader));
        }
        else
        {
            mainTable.setModel(new DefaultTableModel(mainData, tableHeader));
        }
    }
    
    public void setEndTable(String data[][])
    {
        endData = data;
        String[][] displayData = new String[5][2];
        int counter = 0;
        try
        {
            while(!data[counter][0].equals(null))
            {
                counter++;
            }
        }
        catch(Exception e) {}

        if(counter > 5)
        {
            for(int i = 0; i < 5; i++)
            {
                displayData[i][0] = data[i][0];
                displayData[i][1] = Double.toString(roundTwoDecimals(Double.parseDouble(data[i][1])));
            }

            endTable.setModel(new DefaultTableModel(displayData, tableHeader));
        }
        else
        {
            endTable.setModel(new DefaultTableModel(endData, tableHeader));
        }
    }

    public void setOverallTable(String data[][])
    {
        totalData = data;
        String[][] displayData = new String[5][2];
        int counter = 0;
        try
        {
            while(!data[counter][0].equals(null))
            {
                counter++;
            }
        }
        catch(Exception e) {}

        if(counter > 5)
        {
            for(int i = 0; i < 5; i++)
            {
                displayData[i][0] = data[i][0];
                displayData[i][1] = Double.toString(roundTwoDecimals(Double.parseDouble(data[i][1])));
            }

            totalTable.setModel(new DefaultTableModel(displayData, tableHeader));
        }
        else
        {
            totalTable.setModel(new DefaultTableModel(totalData, tableHeader));
        }
    }

    public void setAllScoresTable(String data[][])
    {
        allData = data;
    }

    public void setTeamList(String data[])
    {
        String newData[] = new String[data.length - 1];

        for(int i = 0; i < newData.length; i++)
        {
            newData[i] = data[i + 1];
        }

        teamList = newData;
    }
    
    public void setTeamCount(int num)
    {
        teamCount = num;
    }

    public void setCommentDir(String dir)
    {
        commentDir = dir;
    }

    double roundTwoDecimals(double num)
    {
        DecimalFormat form = new DecimalFormat(DECIMAL_FORMAT);
        return Double.valueOf(form.format(num));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about;
    private javax.swing.JLabel autoLabel;
    private javax.swing.JScrollPane autoScrolPane;
    private javax.swing.JTable autoTable;
    private javax.swing.JMenuItem averageData;
    private javax.swing.JPanel avrAutoScorePanel;
    private javax.swing.JPanel avrEndScorePanel;
    private javax.swing.JPanel avrMainScorePanel;
    private javax.swing.JPanel avrTotalScorePanel;
    private javax.swing.JMenuItem commentData;
    private javax.swing.JMenuItem dataByMatch;
    private javax.swing.JMenu dataMenu;
    private javax.swing.JMenuItem dataPerTeam;
    private javax.swing.JLabel endLabel;
    private javax.swing.JScrollPane endScrollPane;
    private javax.swing.JTable endTable;
    private javax.swing.JMenuItem export;
    private javax.swing.JMenu file;
    private javax.swing.JMenu help;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JTable mainTable;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem penaltyData;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JScrollPane totalScrollPane;
    private javax.swing.JTable totalTable;
    // End of variables declaration//GEN-END:variables

}
