package org.scouting.filer;

import java.io.File;
import java.util.Scanner;
import org.scouting.logger.*;
import org.scouting.scout.Main;

/**
 * @author Alex O'Neill
 * @breif A class to take data from a text file
 */

public class FileScanner
{
    private Scanner read;
    private static Logger scanLog = Main.mainLog;
    private static final String LOG_ID = "Scanner";

    public FileScanner()
    {
        scanLog.setEnabled(Main.logActivate);
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
            scanLog.log(LOG_ID, "Could not open " + name + "... D:");
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
            scanLog.log(LOG_ID, "File not found...");
        }
        return res;
    }

    // Used to check if the file has another entry
    public boolean hasNextEntry()
    {
        boolean result = false;

        // If the Scanner can read another line, return true
        result = read.hasNext();
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
            scanLog.log(LOG_ID, "Error getting next line");
        }
        return result;
    }
}