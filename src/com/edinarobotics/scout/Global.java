/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.scout;

import com.edinarobotics.file.Extracter;
import com.edinarobotics.file.FileScanner;
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
    public static final String VERSION = "Version 2.0";

    // ----- Variables ----- //
    public static final FileScanner teamFileScanner = new FileScanner();
    public static final FileScanner teamListFileScanner = new FileScanner();
    public static final Extracter extract = new Extracter();

    public static final String DECIMAL_FORMAT = "#.####";

    // ----- Logger Stuff ----- //
    public static final Logger log = new Logger();
    public static final String LOG_ID = "Main";

    // ----- Dirs and File Names ----- //
    public static final String currentDir = System.getProperty("user.dir");
    public static String workspaceDir;
    public static String commentDir;
    public static final String workspaceFolderName = "Workspace";
    public static final String teamFolderName = "TeamDir";
    public static final String commentFolderName = "Comments";
    public static final String matchFolderName = "Matches";
    public static final String teamListFile = "TeamList.txt";
    public static final String matchListFile = "Match-List.txt";

    // ----- Other Important Variables ----- //
    public static boolean logActivate = false;
}
