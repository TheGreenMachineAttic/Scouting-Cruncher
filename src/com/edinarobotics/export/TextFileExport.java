/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.export;
import com.edinarobotics.file.Extracter;
import com.edinarobotics.file.FileCreator;
import com.edinarobotics.gui.utilities.Sorter;

/*
 * @author aoneill
 * @breif
 */

public class TextFileExport 
{
    private FileCreator fileCreo = new FileCreator();
    private Extracter extract = new Extracter();
    private Sorter sort = new Sorter();

    private static String allData[][];
    private static int teamCount;
    private static String fileName;
    private static String filePath;

    public TextFileExport(String teamData[][], String name, String path)
    {
        allData = teamData;
        teamCount = sort.getArrayLength(allData);
        fileName = name + ".txt";
        filePath = path;

        createFile();
        addData();
    }

    private void createFile()
    {
        fileCreo.createFile(filePath, fileName);
    }

    private void addData()
    {
        fileCreo.openFile(filePath, fileName);

        fileCreo.addEntry("#####################");
        fileCreo.addEntry("# All Data by Teams #");
        fileCreo.addEntry("#####################");
        fileCreo.addEntry();

        for(int i = 0; i < teamCount; i++)
        {
            String teamNumber = allData[i][0];
            String teamAutoScore = allData[i][1];
            String teamMainGameScore = allData[i][2];
            String teamEndGameScore = allData[i][3];
            String teamTotalAverageScore = allData[i][4];

            fileCreo.addEntry("Team Number:" + teamNumber);
            fileCreo.addEntry("Average Autonomous Score: " + teamAutoScore);
            fileCreo.addEntry("Average Main Game Score: " + teamMainGameScore);
            fileCreo.addEntry("Average End Game Score: " + teamEndGameScore);
            fileCreo.addEntry("Average Total Score: " + teamTotalAverageScore);

            fileCreo.addEntry("Penalties: ");

            if(Integer.parseInt(allData[i][5]) > 1)
            {
                for(int j = 0; j < Integer.parseInt(allData[i][5]); j++)
                {
                    fileCreo.addEntry(" - " + extract.extractEntry(allData[i][6], j));
                }
            }
            else
            {
                fileCreo.addEntry(" - " + allData[i][6]);
            }

            if(i != teamCount - 1)
            {
                fileCreo.addEntry();
                fileCreo.addEntry("#####################");
                fileCreo.addEntry();
            }

        }

        fileCreo.closeFile();
    }
}