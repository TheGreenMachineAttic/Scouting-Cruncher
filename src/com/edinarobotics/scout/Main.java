package com.edinarobotics.scout;
import com.edinarobotics.file.Extracter;
import com.edinarobotics.file.FileScanner;
import com.edinarobotics.gui.ResultGUI;
import com.edinarobotics.gui.SettingsGUI;
import com.edinarobotics.gui.utilities.Sorter;
import com.edinarobotics.logger.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

/*
 * @author aoneill
 * @breif A scouting program for the use of 1816
 */

public class Main 
{   
    // ----- Very Important Variables ----- //
    private static final int EXTRACTED_DATA_POINTS = Global.EXTRACTED_DATA_POINTS;
    private static final int FINAL_DATA_POINTS = Global.FINAL_DATA_POINTS;
    private static final String VERSION = Global.VERSION;

    // ----- Variables ----- //
    private static final FileScanner teamFileScanner = Global.teamFileScanner;
    private static final FileScanner teamListFileScanner = Global.teamListFileScanner;
    private static final Extracter extract = Global.extract;


    // ----- Logger Stuff ----- //
    private static final Logger mainLog = Global.log;
    private static final String LOG_ID = "Main";

    // ----- Dirs and File Names ----- //
    private static final String currentDir = Global.currentDir;
    private static String workspaceDir;
    private static String commentDir;
    private static final String workspaceFolderName = Global.workspaceFolderName;
    private static final String teamFolderName = Global.teamFolderName;
    private static final String commentFolderName = Global.commentFolderName;
    private static final String matchFolderName = Global.matchFolderName;
    private static final String teamListFile = Global.teamListFile;
    private static final String matchListFile = Global.matchListFile;
    
    private static int teamCount = 0;
    private static final String DECIMAL_FORMAT = "#.####";

    // ----- Other Important Variables ----- //
    private static boolean logActivate = false;
    
    public static void main(String[] args) throws InterruptedException, FileNotFoundException
    {
        mainLog.setEnabled(true);

        SettingsGUI sGUI = new SettingsGUI();
        
        if((new File(currentDir + "/" + workspaceFolderName).isDirectory()))
        {
            workspaceDir = currentDir + "/" + workspaceFolderName;
        }

        sGUI.setTeamDirField(workspaceDir);
        sGUI.setLogBox(logActivate);
        while(!sGUI.getScoutStatus()) {}
        sGUI.setVisible(false);

        workspaceDir = sGUI.getTeamDirPath();
        commentDir = workspaceDir + "/" + commentFolderName;

        logActivate = sGUI.getLogBox();

        mainLog.setEnabled(logActivate);

        teamListFileScanner.openFile(workspaceDir, teamListFile);

        while(teamListFileScanner.hasNextEntry())
        {
            teamCount++;
            teamListFileScanner.getNextLine();
        }

        String teamArray[] = new String[teamCount];
        teamListFileScanner.openFile(workspaceDir, teamListFile);
        teamCount = 0;
        while(teamListFileScanner.hasNextEntry())
        {
            teamArray[teamCount] = teamListFileScanner.getNextLine();
            teamCount++;
        }

        String teamData[][] = new String[teamCount][FINAL_DATA_POINTS];
        for(int i = 1; i < teamCount; i++)
        {
            mainLog.log(LOG_ID, "Team " + teamArray[i] + ":");
            teamFileScanner.openFile(workspaceDir + "/" + teamFolderName, teamArray[i] + ".txt");

            String nextLine = teamFileScanner.getNextLine();
            while(nextLine.startsWith("#"))
            {
                nextLine = teamFileScanner.getNextLine();
            }

            int lineCount = 1;
            while(teamFileScanner.hasNextEntry())
            {
                teamFileScanner.getNextLine();
                lineCount++;
            }

            String content[] = new String[lineCount];
            teamFileScanner.openFile(workspaceDir + "/" + teamFolderName, teamArray[i] + ".txt");

            nextLine = teamFileScanner.getNextLine();
            while(nextLine.startsWith("#"))
            {
                nextLine = teamFileScanner.getNextLine();
            }

            content[0] = nextLine;
            for(int j = 1; j < lineCount; j++)
            //while(teamFileScanner.hasNextEntry())
            {
                content[j] = teamFileScanner.getNextLine();
            }


            String extractedData[][] = new String[EXTRACTED_DATA_POINTS][lineCount];
            for(int j = 0; j < lineCount; j++)
            {
                // Ignoring Round Number
                // <roundNum>:<autoPoints>:<mainPoints>:<endPoints>:<penalties>
                extractedData[0][j] = extract.extractEntry(content[j], 2);
                extractedData[1][j] = extract.extractEntry(content[j], 3);
                extractedData[2][j] = extract.extractEntry(content[j], 4);
                extractedData[3][j] = extract.extractEntry(content[j], 5);
            }

            double averageAutoPoints = 0;
            double averageMainPoints = 0;
            double averageEndPoints = 0;

            double averageTotalScore = 0;
            int penaltyNumber = 0;
            String totalPenalties = "";
            boolean penaltyFlag = false;

            for(int j = 0; j < lineCount; j++)
            {
                averageAutoPoints = averageAutoPoints + Integer.parseInt(extractedData[0][j]);
                averageMainPoints = averageMainPoints + Integer.parseInt(extractedData[1][j]);
                averageEndPoints = averageEndPoints + Integer.parseInt(extractedData[2][j]);

                String separator = "";
                if(penaltyFlag)
                {
                    separator = ":";
                }

                if(!extractedData[3][j].equals("") && !extractedData[3][j].equals("none"))
                {
                    totalPenalties = totalPenalties + separator + extractedData[3][j];
                    penaltyNumber++;

                    penaltyFlag = true;
                }
            }

            averageAutoPoints = (double) averageAutoPoints/lineCount;
            averageMainPoints = (double) averageMainPoints/lineCount;
            averageEndPoints = (double) averageEndPoints/lineCount;

            averageTotalScore = averageAutoPoints + averageMainPoints + averageEndPoints;

            if(totalPenalties.equals(""))
            {
                totalPenalties = "none";
            }

            mainLog.log(LOG_ID, "Avr. Auto: " + averageAutoPoints);
            mainLog.log(LOG_ID, "Avr. Main: " + averageMainPoints);
            mainLog.log(LOG_ID, "Avr. End: " + averageEndPoints);
            mainLog.log(LOG_ID, "Total Penalties: " + totalPenalties);
            mainLog.log(LOG_ID, "Total Average Score: " + averageTotalScore);
            mainLog.log();

            teamData[i - 1][0] = Double.toString(round(averageAutoPoints));
            teamData[i - 1][1] = Double.toString(round(averageMainPoints));
            teamData[i - 1][2] = Double.toString(round(averageEndPoints));
            teamData[i - 1][3] = Double.toString(round(averageTotalScore));
            teamData[i - 1][4] = Integer.toString(penaltyNumber);
            teamData[i - 1][5] = totalPenalties;
        }
        Sorter sort = new Sorter(2);

        String[][] autonomousData = new String[teamCount - 1][2];
        for(int i = 1; i < teamCount; i++)
        {
            autonomousData[i - 1][0] = teamArray[i];
            autonomousData[i - 1][1] = teamData[i - 1][0];
        }
        autonomousData = sort.sortBest(autonomousData, 1, Sorter.HIGH_TO_LOW);

        String[][] mainGameData = new String[teamCount - 1][2];
        for(int i = 1; i < teamCount; i++)
        {
            mainGameData[i - 1][0] = teamArray[i];
            mainGameData[i - 1][1] = teamData[i - 1][1];
        }
        mainGameData = sort.sortBest(mainGameData, 1, Sorter.HIGH_TO_LOW);

        String[][] endGameData = new String[teamCount - 1][2];
        for(int i = 1; i < teamCount; i++)
        {
            endGameData[i - 1][0] = teamArray[i];
            endGameData[i - 1][1] = teamData[i - 1][2];
        }
        endGameData = sort.sortBest(endGameData, 1, Sorter.HIGH_TO_LOW);

        String[][] totalScoreData = new String[teamCount - 1][2];
        for(int i = 1; i < teamCount; i++)
        {
            totalScoreData[i - 1][0] = teamArray[i];
            totalScoreData[i - 1][1] = teamData[i - 1][3];
        }
        totalScoreData = sort.sortBest(totalScoreData, 1, Sorter.HIGH_TO_LOW);

        String[][] penaltyData = new String[teamCount - 1][3];
        for(int i = 1; i < teamCount; i++)
        {
            penaltyData[i - 1][0] = teamArray[i];
            penaltyData[i - 1][1] = teamData[i - 1][4];
            penaltyData[i - 1][2] = teamData[i - 1][5];
        }
        
        String[][] allData = new String[teamCount - 1][FINAL_DATA_POINTS + 1];
        for(int i = 1; i < teamCount; i++)
        {
            allData[i - 1][0] = teamArray[i];
            allData[i - 1][1] = teamData[i - 1][0];
            allData[i - 1][2] = teamData[i - 1][1];
            allData[i - 1][3] = teamData[i - 1][2];
            allData[i - 1][4] = teamData[i - 1][3];
            allData[i - 1][5] = teamData[i - 1][4];
            allData[i - 1][6] = teamData[i - 1][5];
        }

        ResultGUI rGUI = new ResultGUI();
        rGUI.setTeamCount(teamCount - 1);
        rGUI.setCommentDir(commentDir);
        rGUI.setAutonomousTable(autonomousData);
        rGUI.setMainTable(mainGameData);
        rGUI.setEndTable(endGameData);
        rGUI.setOverallTable(totalScoreData);
        rGUI.setAllScoresTable(allData);
        rGUI.setTeamList(teamArray);
        rGUI.setVisible(true);
    }


    private static double round(double number)
    {
        DecimalFormat dFormat = new DecimalFormat(DECIMAL_FORMAT);
        return Double.valueOf(dFormat.format(number));
    }
}