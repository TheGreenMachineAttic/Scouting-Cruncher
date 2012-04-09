package com.edinarobotics.scout;
import com.edinarobotics.data.ConfigFile;
import com.edinarobotics.data.DataArray;
import com.edinarobotics.filer.Extracter;
import com.edinarobotics.filer.FileScanner;
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
    // ----- Logger Stuff ----- //
    private static Logger log = Global.log;
    private static final String LOG_ID = "Main";
    
    public static void main(String[] args) throws InterruptedException, FileNotFoundException
    {
        // Enable the logger by default
        log.setEnabled(true);

        log.log(LOG_ID, "Starting Data Crunching");
        
        
        
        // Initiate a new SettingsGUI
        SettingsGUI sGUI = new SettingsGUI();

        while(!sGUI.getScoutStatus()) {}
        sGUI.setVisible(false);
        
        DataArray dArray = new DataArray();

        ResultGUI rGUI = new ResultGUI();
        rGUI.setTeamCount(Global.teamArray.length);
        rGUI.setCommentDir(Global.commentDir);
        rGUI.setAutonomousTable(Global.autonomousData);
        rGUI.setMainTable(Global.mainGameData);
        rGUI.setEndTable(Global.endGameData);
        rGUI.setOverallTable(Global.totalScoreData);
        rGUI.setAllScoresTable(Global.allData);
        rGUI.setTeamList(Global.teamArray);
        rGUI.setVisible(true);
    }
}