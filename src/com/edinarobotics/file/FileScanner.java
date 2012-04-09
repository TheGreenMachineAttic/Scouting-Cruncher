package com.edinarobotics.file;

import com.edinarobotics.gui.utilities.ErrorGUI;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Global;
import java.io.File;
import java.util.Scanner;

/**
 * @author Alex O'Neill
 * @breif A class to take data from a text file
 */

public class FileScanner
{
    private Scanner read;
    private static Logger scanLog = Global.log;
    private static final String LOG_ID = "Scanner";

    public FileScanner()
    {
        scanLog.setEnabled(Global.logActivate);
    }

    // Used to open the text file in the Scanner
    public void openFile(String path, String name)
    {
        // Try to open the file at the path specified, and warn the user if it
        // fails

        scanLog.log(LOG_ID, "Opening " + name + " in " + path);
        try
        {
            read = new Scanner(new File(path + "/" + name));
        }
        catch(Exception e)
        {
            scanLog.log(LOG_ID, "Could not open " + name + " in " + path);
        }
    }

    // Test to see if the file is created
    public boolean isFileCreated(String path, String name)
    {
        boolean res = false;

        // Try to find out if the file has content, and return true
        // If the file cant be read, warm the user
        try
        {
            read = new Scanner(new File(path + "/" + name));

            if(read.hasNext())
            {
                res = true;
            }
        }
        catch(Exception e)
        {
            scanLog.log(LOG_ID, name + " not found in " + path);
        }
        return res;
    }

    // Used to check if the file has another entry
    public boolean hasNextEntry()
    {
        boolean result = false;

        // If the Scanner can read another line, return true
        try
        {
            result = read.hasNext();
        }
        catch(Exception e)
        {
            scanLog.log(LOG_ID, "File was not opened correctly");
            ErrorGUI eGUI = new ErrorGUI("File was not Found!\nCannot Continue!", ErrorGUI.ERROR_SEVERE);
        }
        return result;
    }

    // Used to return the next line of text from the file
    public String getNextLine()
    {
        String result = "";

        // Try to read the file and return the result
        // If it fails, tell the user
        try
        {
            result = read.nextLine();
        }
        catch(Exception e)
        {
            scanLog.log(LOG_ID, "Could not get next line");
        }
        return result;
    }
}