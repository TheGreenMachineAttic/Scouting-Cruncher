package org.scouting.scout;
import org.scouting.filer.*;
import org.scouting.gui.*;
import org.scouting.gui.utilities.*;

// These may be unused, but they are still a good idea to keep!
import java.io.*;

/*
 * @author aoneill
 * @breif A scouting program for the use of 1816
 */

public class Main 
{
    private static int EXTRACTED_DATA_POINTS = 4;
    private static int FINAL_DATA_POINTS = 6;
    private static int teamCount = 0;


    // ----- Variables -----//
    private static FileScanner teamFileScanner = new FileScanner();
    private static FileScanner teamListFileScanner = new FileScanner();
    private static Extracter extract = new Extracter();

    public static String currentDir = System.getProperty("user.dir");
    public static String workspaceFolderName = "Workspace";
    public static String teamFolderName = "TeamDir";
    public static String commentFolderName = "Comments";
    public static String matchFolderName = "Matches";
    public static String teamListFile = "TeamList.txt";
    public static String matchListFile = "Match-List.txt";

    public static void main(String[] args) throws InterruptedException, FileNotFoundException
    {
        String workspaceDir = currentDir;
        String commentDir;
        boolean logActivate = false;
        
        if((new File(currentDir + "/" + workspaceFolderName).isDirectory()))
        {
            workspaceDir = workspaceDir + "/" + workspaceFolderName;
        }

        commentDir = currentDir + "/" + workspaceFolderName + "/" + commentFolderName;

        SettingsGUI sGUI = new SettingsGUI();
        sGUI.setTeamDirField(workspaceDir);
        sGUI.setLogBox(logActivate);
        while(!sGUI.getScoutStatus()) {}
        sGUI.setVisible(false);

        workspaceDir = sGUI.getTeamDirPath();
        logActivate = sGUI.getLogBox();


        teamListFileScanner.openFile(currentDir + "/" + workspaceFolderName, teamListFile);

        while(teamListFileScanner.hasNextEntry())
        {
            teamCount++;
            teamListFileScanner.getNextLine();
        }

        String teamArray[] = new String[teamCount];
        teamListFileScanner.openFile(currentDir + "/" + workspaceFolderName, teamListFile);
        teamCount = 0;
        while(teamListFileScanner.hasNextEntry())
        {
            teamArray[teamCount] = teamListFileScanner.getNextLine();
            teamCount++;
        }

        System.out.println();

        String teamData[][] = new String[teamCount][FINAL_DATA_POINTS];
        for(int i = 1; i < teamCount; i++)
        {
            System.out.println("Team " + teamArray[i] + ":");
            teamFileScanner.openFile(currentDir + "/" + workspaceFolderName + "/" + teamFolderName, teamArray[i] + ".txt");

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
            teamFileScanner.openFile(currentDir + "/" + workspaceFolderName + "/" + teamFolderName, teamArray[i] + ".txt");

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

            averageAutoPoints = averageAutoPoints/lineCount;
            averageMainPoints = averageMainPoints/lineCount;
            averageEndPoints = averageEndPoints/lineCount;

            averageTotalScore = (double) (averageAutoPoints + averageMainPoints + averageEndPoints)/3;

            if(totalPenalties.equals(""))
            {
                totalPenalties = "none";
            }

            System.out.println("Avr. Auto: " + averageAutoPoints);
            System.out.println("Avr. Main: " + averageMainPoints);
            System.out.println("Avr. End: " + averageEndPoints);
            System.out.println("Total Penalties: " + totalPenalties);
            System.out.println("Total Average Score: " + averageTotalScore);
            System.out.println();

            teamData[i - 1][0] = Double.toString(averageAutoPoints);
            teamData[i - 1][1] = Double.toString(averageMainPoints);
            teamData[i - 1][2] = Double.toString(averageEndPoints);
            teamData[i - 1][3] = Double.toString(averageTotalScore);
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
        rGUI.setPeanltiesData(penaltyData);
        rGUI.setAllScoresTable(allData);
        rGUI.setTeamList(teamArray);
        rGUI.setVisible(true);
    }

//    public static String[][] sortBest(String array[][])
//    {
//        String result[][] = new String[array.length][2];
//
//        double topNumber = 0;
//        int teamNumber = 0;
//        for(int i = 0; i < array.length; i++)
//        {
//            double firstNumber = Double.parseDouble(array[i][1]);
//            if(firstNumber > topNumber)
//            {
//                teamNumber = Integer.parseInt(array[i][0]);
//                topNumber = Double.parseDouble(array[i][1]);
//            }
//        }
//
//        result[0][0] = Integer.toString(teamNumber);
//        result[0][1] = Double.toString(topNumber);
//
//        for(int i = 0; i < array.length - 1; i++)
//        {
//            topNumber = 0;
//            teamNumber = 0;
//            for(int j = 0; j < array.length; j++)
//            {
//                double currentNumber = Double.parseDouble(array[j][1]);
//                double lastNumber = Double.parseDouble(result[i][1]);
//
//                if(currentNumber > topNumber && currentNumber < lastNumber)
//                {
//                    teamNumber = Integer.parseInt(array[j][0]);
//                    topNumber = Double.parseDouble(array[j][1]);
//                }
//            }
//
//            result[i + 1][0] = Integer.toString(teamNumber);
//            result[i + 1][1] = Double.toString(topNumber);
//        }
//
//        return result;
//    }

    public String getWorkspaceFolderName()
    {
        return workspaceFolderName;
    }

    public String getTeamFolderName()
    {
        return teamFolderName;
    }
}