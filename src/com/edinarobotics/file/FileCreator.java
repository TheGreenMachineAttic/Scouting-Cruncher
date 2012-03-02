package com.edinarobotics.file;

import java.util.Formatter;
import com.edinarobotics.scout.*;
import com.edinarobotics.logger.Logger;

/**
 * @author Alex O'Neill
 * @breif A class to create a text file
 */
public class FileCreator 
{
    private Formatter format;
    private static Logger fileLog = Main.mainLog;
    private static String LOG_ID = "File Creator";

    // Used to create the text file
    public void createFile(String path, String name)
    {
        // Try to create the file, and warn the user if it fails
        try
        {
            format = new Formatter(path + "/" + name);

            //System.out.println("Formatter: Creating " + name + " in " + path);
        }
        catch(Exception e)
        {
            fileLog.log(LOG_ID, "Could not create " + name + " in " + path);
        }
    }

    // Used to open the text file
    public void openFile(String path, String name)
    {
        // Try to open the file for editing, and warn the user if it fails
        try
        {
            format = new Formatter(path + "/" + name);

            //System.out.println("Formatter: Opening " + name + " in " + path);
        }
        catch(Exception e)
        {
            fileLog.log(LOG_ID, "Could not open " + name + " in " + path);
        }
    }

    // Used to add directions to the text file
    public void addConfigEntries()
    {
        // Once the file is open in the Formatter, put in the directions
        format.format("%s", "# Default Settings" + System.getProperty("line.separator"));
        format.format("%s", "defaultTeamDir:" + System.getProperty("user.dir")+ "/Workspace/TeamDir" + System.getProperty("line.separator"));
        format.format("%s", "changeLogActivate:false" + System.getProperty("line.separator"));
    }

    public void addUpdatedConfigEntries(String defaultTeamDir, boolean changeLogActivate)
    {
        format.format("%s", "# Default Settings" + System.getProperty("line.separator"));
        format.format("%s", "defaultTeamDir:" + defaultTeamDir + System.getProperty("line.separator"));
        format.format("%s%b%s", "changeLogActivate:", changeLogActivate, System.getProperty("line.separator"));
    }

    public void addTeamHeader()
    {
        format.format("%s%s", "# Format #", System.getProperty("line.separator"));
        format.format("%s%s", "# <roundNum>:<autoPoints>:<mainPoints>:<endPoints>:<penalties>", System.getProperty("line.separator"));
    }

    public void addCommentHeader()
    {
        format.format("%s%s", "# Comments #", System.getProperty("line.separator"));
    }

    // Adds a generic entry with a carriage return
    public void addEntry(String entry)
    {
        format.format("%s", entry + System.getProperty("line.separator"));
    }

    public void addEntry()
    {
        format.format("%s", System.getProperty("line.separator"));
    }

    // Used to close the text file
    // Make sure you do this!! It wont actaully add stuff to your file if you
    // don't!
    public void closeFile()
    {
        // Close the Formatter when you are done
        format.close();
    }
}
