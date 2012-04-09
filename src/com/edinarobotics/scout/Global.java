/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.scout;

import com.edinarobotics.filer.Extracter;
import com.edinarobotics.filer.FileScanner;
import com.edinarobotics.logger.Logger;

/**
 *
 * @author aoneill
 */
public class Global 
{
    // ----- Very Important Variables ----- //
    public static final int EXTRACTED_DATA_POINTS = 4;
    public static final int FINAL_DATA_POINTS = 6;
    public static final String DATA_SEPARATOR = ":";
    public static final String fileHeaderID = "#";
    public static final String VERSION = "Version 2.0";

    // ----- Logger Stuff ----- //
    public static Logger log = new Logger();

    // ----- Dirs and File Names ----- //
    public static final String currentDir = System.getProperty("user.dir");
    public static String workspaceDir;
    public static String commentDir;
    public static final String configFileDir = currentDir;
    public static final String configFile = "config.txt";
    public static final String workspaceFolderName = "Workspace";
    public static final String teamFolderName = "TeamDir";
    public static final String commentFolderName = "Comments";
    public static final String matchFolderName = "Matches";
    public static final String teamListFile = "TeamList.txt";
    public static final String matchListFile = "Match-List.txt";

    // ----- Lists of Data ----- //
    public static String[][] autonomousData;
    public static String[][] mainGameData;
    public static String[][] endGameData;
    public static String[][] totalScoreData;
    public static String[][] penaltyData;
    public static String[][] allData;
    public static String[] teamArray;

    // ----- Other Important Variables ----- //
    public static boolean logActivate = false;
}
