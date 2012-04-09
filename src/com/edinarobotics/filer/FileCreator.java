package com.edinarobotics.filer;

import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Global;
import java.util.ArrayList;
import java.util.Formatter;

/**
 * @author Alex O'Neill
 * @breif A class to create a text file
 */
public class FileCreator 
{
    // Variables needed
    private Formatter format;
    private String SEPARATOR = Global.DATA_SEPARATOR;
    private Logger log = Global.log;
    private static final String LOG_TAG = "File Creator";

    // Recently opened file properties
    private String currentFile = "";
    private String currentPath = "";

    /**
     * Creates a given file based on name and path
     * @param path the location of the file
     * @param name the name of the file
     */
    public void createFile(String path, String name)
    {
        // Try to create the file, and warn the user if it fails
        try
        {
            log.log(LOG_TAG, "Creating " + name + " in " + path);
            format = new Formatter(path + "/" + name);
            
            currentFile = name;
            currentPath = path;
        }
        catch(Exception e)
        {
            log.log(LOG_TAG, "Could not create " + name + " in " + path);
        }
    }

    /**
     * Opens a specified file
     * @param path the location of the file
     * @param name the name of the file
     */
    public void openFile(String path, String name)
    {
        // Try to open the file for editing, and warn the user if it fails
        try
        {
            log.log(LOG_TAG, "Opening " + name + " in " + path);
            format = new Formatter(path + "/" + name);

            currentFile = name;
            currentPath = path;
        }
        catch(Exception e)
        {
            log.log(LOG_TAG, "Could not open " + name + " in " + path);
        }
    }
    
    /**
     * Adds only a new line without any other content to a file
     */
    public void addEntry()
    {
        format.format("%s", System.getProperty("line.separator"));
    }
    
    /**
     * Add content to a file with a new line
     * @param entry the String to be added
     */
    public void addEntry(String entry)
    {
        format.format("%s%s", entry, System.getProperty("line.separator"));
    }
    
    /**
     * Adds content to a file from a 1d array with a new line after each item in the array
     * @param list the array to add
     */
    public void addEntry(String list[])
    {
        for(int i = 0; i < list.length; i++)
        {
            format.format("%s%s", list[i], System.getProperty("line.separator"));
        }
    }

    /**
     * Adds content to a file from an ArrayList with a new line after each item
     * @param list the list to be added
     */
    public void addEntry(ArrayList<String> list)
    {
        for(int i = 0; i < list.size(); i++)
        {
            format.format("%s%s", list.get(i), System.getProperty("line.separator"));
        }
    }

    /**
     * Closes an opened file
     */
    public void closeFile()
    {
        // Close the Formatter when you are done
        log.log(LOG_TAG, "Closing " + currentFile + " in " + currentPath);
        format.close();
    }
}