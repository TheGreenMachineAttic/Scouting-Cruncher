/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.data;

import com.edinarobotics.filer.Extracter;
import com.edinarobotics.filer.FileScanner;
import com.edinarobotics.gui.utilities.Sorter;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Global;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author aoneill
 */
public class DataArray 
{
    // ----- Very Important Variables ----- //
    private static final int EXTRACTED_DATA_POINTS = Global.EXTRACTED_DATA_POINTS;
    private static final int FINAL_DATA_POINTS = Global.FINAL_DATA_POINTS;

    // ----- Variables ----- //
    private static final FileScanner teamFileScanner = new FileScanner();
    private static final FileScanner teamListFileScanner = new FileScanner();
    private static final Extracter extract = new Extracter();

    // ----- Logger Stuff ----- //
    private static final Logger log = Global.log;
    private static final String LOG_ID = "Data Array";

    // ----- Dirs and File Names ----- //
    private static String workspaceDir = Global.workspaceDir;
    private static final String teamFolderName = Global.teamFolderName;
    private static final String teamListFile = Global.teamListFile;
    
    // ----- Other Variables ----- //
    private static int teamCount;
    private static final String DECIMAL_FORMAT = "#.####";
    
    String[][] autonomousData;
    String[][] mainGameData;
    String[][] endGameData;
    String[][] totalScoreData;
    String[][] penaltyData;
    String[][] allData;
    String[] teamArray;
    
    public DataArray()
    {
        // Open the file which holds the list of teams
        teamListFileScanner.openFile(workspaceDir, teamListFile);

        // Transfwer the data from the file if it does not start with the Data ID
        ArrayList<String> listData = new ArrayList<String>();
        while(teamListFileScanner.hasNextEntry())
        {
            String line = teamListFileScanner.getNextLine();
            if(!line.startsWith(Global.fileHeaderID))
            {
                listData.add(line);
            }
        }
        
        // Update the team count
        teamCount = listData.size();
        
        // Transfer the data list to an array
        teamArray = new String[teamCount];
        for(int i = 0; i < listData.size(); i++)
        {
            teamArray[i] = listData.get(i);
        }

        // Create an array to hold the team data
        String teamData[][] = new String[teamCount][FINAL_DATA_POINTS];
        
        // Get the data from the files and put it in this list
        for(int i = 0; i < teamCount; i++)
        {
            // Debug stateent
            log.log(LOG_ID, "Team " + teamArray[i] + ":");
            
            // Open a team's file
            String dir = workspaceDir + "/" + teamFolderName;
            teamFileScanner.openFile(dir , teamArray[i] + ".txt");
            
            // Transfwer the data from the file if it does not start with the Data ID
            ArrayList<String> teamFileData = new ArrayList<String>();
            while(teamFileScanner.hasNextEntry())
            {
                String line = teamFileScanner.getNextLine();
                if(!line.startsWith(Global.fileHeaderID))
                {
                    teamFileData.add(line);
                }
            }
            
            // Update the team count
            int dataPoints = teamFileData.size();

            // Transfer the data list to an array
            String[] content = new String[dataPoints];
            for(int j = 0; j < teamFileData.size(); j++)
            {
                content[j] = teamFileData.get(j);
            }
            
            // Extract data from the scores into a second array
            String extractedData[][] = new String[EXTRACTED_DATA_POINTS][dataPoints];
            for(int j = 0; j < dataPoints; j++)
            {
                extractedData[0][j] = extract.extractEntry(content[j], 2);
                extractedData[1][j] = extract.extractEntry(content[j], 3);
                extractedData[2][j] = extract.extractEntry(content[j], 4);
                extractedData[3][j] = extract.extractEntry(content[j], 5);
            }

            // Declare variables to hold the different aspects of a team
            double averageAutoPoints = 0;
            double averageMainPoints = 0;
            double averageEndPoints = 0;
            double averageTotalScore;
            int penaltyNumber = 0;
            String totalPenalties = "";
            boolean penaltyFlag = false;

            // Compile the different data parts
            for(int j = 0; j < dataPoints; j++)
            {
                // Add up the averages
                averageAutoPoints = averageAutoPoints + Integer.parseInt(extractedData[0][j]);
                averageMainPoints = averageMainPoints + Integer.parseInt(extractedData[1][j]);
                averageEndPoints = averageEndPoints + Integer.parseInt(extractedData[2][j]);

                // Add Penalty separator if second penalty
                String separator = "";
                if(penaltyFlag)
                {
                    separator = Global.DATA_SEPARATOR;
                }

                // Add penalties if they exist
                if(!extractedData[3][j].equals("") && !extractedData[3][j].equals("none"))
                {
                    totalPenalties = totalPenalties + separator + extractedData[3][j];
                    penaltyNumber++;

                    penaltyFlag = true;
                }
            }

            // Average the score data
            averageAutoPoints = (double) averageAutoPoints/dataPoints;
            averageMainPoints = (double) averageMainPoints/dataPoints;
            averageEndPoints = (double) averageEndPoints/dataPoints;
            averageTotalScore = averageAutoPoints + averageMainPoints + averageEndPoints;

            // if there were no penalties against a team make the penalties teaxt be "none"
            if(totalPenalties.equals(""))
            {
                totalPenalties = "none";
            }

            // Debug
            log.log(LOG_ID, "Avr. Auto: " + round(averageAutoPoints));
            log.log(LOG_ID, "Avr. Main: " + round(averageMainPoints));
            log.log(LOG_ID, "Avr. End: " + round(averageEndPoints));
            log.log(LOG_ID, "Total Average Score: " + round(averageTotalScore));
            log.log(LOG_ID, "Total Penalties: " + totalPenalties);
            log.log();

            // Store the average data to an array
            teamData[i][0] = Double.toString(round(averageAutoPoints));
            teamData[i][1] = Double.toString(round(averageMainPoints));
            teamData[i][2] = Double.toString(round(averageEndPoints));
            teamData[i][3] = Double.toString(round(averageTotalScore));
            teamData[i][4] = Integer.toString(penaltyNumber);
            teamData[i][5] = totalPenalties;
        }
        
        // Sort the big data into different arrays
        int arrayWidth = 2;
        Sorter sort = new Sorter(arrayWidth);

        autonomousData = new String[teamCount][arrayWidth];
        for(int i = 0; i < teamCount; i++)
        {
            autonomousData[i][0] = teamArray[i];
            autonomousData[i][1] = teamData[i][0];
        }
        autonomousData = sort.sortBest(autonomousData, 1, Sorter.HIGH_TO_LOW);

        mainGameData = new String[teamCount][arrayWidth];
        for(int i = 0; i < teamCount; i++)
        {
            mainGameData[i][0] = teamArray[i];
            mainGameData[i][1] = teamData[i][1];
        }
        mainGameData = sort.sortBest(mainGameData, 1, Sorter.HIGH_TO_LOW);

        endGameData = new String[teamCount][arrayWidth];
        for(int i = 0; i < teamCount; i++)
        {
            endGameData[i][0] = teamArray[i];
            endGameData[i][1] = teamData[i][2];
        }
        endGameData = sort.sortBest(endGameData, 1, Sorter.HIGH_TO_LOW);

        totalScoreData = new String[teamCount][arrayWidth];
        for(int i = 0; i < teamCount; i++)
        {
            totalScoreData[i][0] = teamArray[i];
            totalScoreData[i][1] = teamData[i][3];
        }
        totalScoreData = sort.sortBest(totalScoreData, 1, Sorter.HIGH_TO_LOW);

        penaltyData = new String[teamCount][3];
        for(int i = 0; i < teamCount; i++)
        {
            penaltyData[i][0] = teamArray[i];
            penaltyData[i][1] = teamData[i][4];
            penaltyData[i][2] = teamData[i][5];
        }
        
        allData = new String[teamCount][FINAL_DATA_POINTS + 1];
        for(int i = 0; i < teamCount; i++)
        {
            allData[i][0] = teamArray[i];
            allData[i][1] = teamData[i][0];
            allData[i][2] = teamData[i][1];
            allData[i][3] = teamData[i][2];
            allData[i][4] = teamData[i][3];
            allData[i][5] = teamData[i][4];
            allData[i][6] = teamData[i][5];
        }
        
        Global.autonomousData = autonomousData;
        Global.mainGameData = mainGameData;
        Global.endGameData = endGameData;
        Global.totalScoreData = totalScoreData;
        Global.penaltyData = penaltyData;
        Global.allData = allData;
        Global.teamArray = teamArray;
    }
    
    private double round(double number)
    {
        DecimalFormat dFormat = new DecimalFormat(DECIMAL_FORMAT);
        return Double.valueOf(dFormat.format(number));
    }
}