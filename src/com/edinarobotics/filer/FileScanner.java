package com.edinarobotics.filer;

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
    private Logger log = Global.log;
    private static final String LOG_TAG = "File Scanner";

    private String recentName;
    private String recentPath;

    /**
     * Opens a file to be read
     * @param path the location of the file
     * @param name the name of the file
     */
    public void openFile(String path, String name)
    {
        // Try to open the file at the path specified, and warn the user if it
        // fails

        // Update the recent file data 
        recentName = name;
        recentPath = path;

        // Notify the console
        log.log(LOG_TAG, "Opening " + name + " in " + path);
        
        // Try to open the file
        try
        {
            read = new Scanner(new File(path + "/" + name));
        }
        catch(Exception e)
        {
            log.log(LOG_TAG, "Could not open " + name + " in " + path);
        }
    }

    /**
     * Checks to see if a file is created
     * @param path the location of the file
     * @param name the name of the file
     * @return if the file is created or not
     */
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
            log.log(LOG_TAG, name + " not found in " + path);
        }
        return res;
    }

    /**
     * Checks to see if the file opened has another line of content
     * @return whether there is more content
     */
    public boolean hasNextEntry()
    {
        // Variable to store the result
        boolean result;

        // If the Scanner can read another line, return true
        result = read.hasNext();
        return result;
    }

    /**
     * Gets the next line of readable data from the file
     * @return the line of data read
     */
    public String getNextLine()
    {
        // String to hold the line of data
        String result = "";

        // Try to read the file and return the result
        // If it fails, tell the user
        try
        {
            result = read.nextLine();
        }
        catch(Exception e)
        {
           log.log(LOG_TAG, "Error getting next line in " + recentName + " in " + recentPath);
        }
        return result;
    }

    /**
     * Closes the Scanner to prevent memory leaks
     */
    public void close()
    {
        try
        {
            log.log(LOG_TAG, "Closing " + recentName + " in " + recentPath);
            read.close();
        }
        catch(Exception e)
        {
            log.log(LOG_TAG, "Error closing the stream");
        }
    }
}