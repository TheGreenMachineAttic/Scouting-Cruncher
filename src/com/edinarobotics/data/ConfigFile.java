/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.data;

import com.edinarobotics.filer.Extracter;
import com.edinarobotics.filer.FileCreator;
import com.edinarobotics.filer.FileScanner;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Global;
import java.io.File;
import java.util.ArrayList;

/*
 * @author aoneill
 * @breif
 */

public class ConfigFile
{
    // Variables for Config 
    private static FileScanner configScanner = new FileScanner();
    private static FileCreator fileCreo = new FileCreator();
    private static Extracter extract = new Extracter();
    
    // Logger stuff
    private static Logger log = Global.log;
    private static String LOG_TAG = "Config File";
    
    // Other config stuff
    private static String DATA_SEPARATOR = Global.DATA_SEPARATOR;

    private static String configDir = Global.configFileDir;
    private static String configFile = Global.configFile;
    private static int configEntries = 2;

    private static String[] configFormat = {"deafultWorkspace", "changeLogActivate"};
    private static String[] defaultConfigValues = {System.getProperty("user.dir"), "false"};
    private static String[] configValues = new String[2];
    
    private static String[] header = {"# Config #"};


    /**
     * Read the Config file and return the values
     * @return the values from the file
     */
    public String[] configRead()
    {
        // If the config file is not created, create it and give it the default values
        // Also return the default values
        if(!configScanner.isFileCreated(configDir, configFile))
        {
            log.log(LOG_TAG, "Error in Config File! (Not found)");
            log.log(LOG_TAG, "Creating new file with default values");
            
            // Write to the config file
            configWrite(configFormat, defaultConfigValues);

            return defaultConfigValues;
        }
        
        // Else read the existing config file
        else
        {
            // Open the Config file file to read defaults
            configScanner.openFile(configDir, configFile);

            // Transfer the contents to a list
            ArrayList<String> list = new ArrayList<String>();
            while(configScanner.hasNextEntry())
            {
                list.add(configScanner.getNextLine());
            }
            // Close the scanner
            configScanner.close();

            // Remove the header from the list
            list.remove(header.length - 1);

            // if the contents of the config file exceed the amount they should have,
            // create a new config file with default values
            if(list.size() > configEntries)
            {
                log.log(LOG_TAG, "Error in Config File! (Too many entries)");
                log.log(LOG_TAG, "Creating new file with default values");
                
                // Write to the config file
                configWrite(configFormat, defaultConfigValues);

                return defaultConfigValues;
            }
            // Else read the regular config
            else
            {
                // Get the config data
                String[] configData = listToArray(list);
                
                // Extract the actual data
                configValues = extractConfigValues(configData);
            }
            
            // Check to see if the recorded defaultWorkspace exists
            // If it does not, return default values
            // This issue usually appears when copy/pasting programs to other computers
            if(!new File(configValues[0]).isDirectory())
            {
                log.log(LOG_TAG, "Error in Config File! (Workspace recoreded does not exist)");
                log.log(LOG_TAG, "Creating new file with default values");
                
                // Write to the config file
                configWrite(configFormat, defaultConfigValues);

                return defaultConfigValues;
            }
            
            // Return the data
            return configValues;
        }
    }

    /**
     * Write to the Config file
     * @param value the data to be written
     */
    public void configWrite(String[] value)
    {
        // Write the data
        configWrite(configFormat, value);
    }

    /**
     * Write the data to the Config file
     * @param id the list of identifiers
     * @param value the list of values
     */
    private void configWrite(String[] id, String[] value)
    {
        // Check to see that the list lengths match up
        if(id.length == value.length)
        {
            // Create, open and add a header to the Config file
            fileCreo.createFile(configDir, configFile);
            fileCreo.openFile(configDir, configFile);
            fileCreo.addEntry(header);

            // Add the data
            for(int i = 0; i < id.length; i++)
            {
                fileCreo.addEntry(id[i] + DATA_SEPARATOR + value[i]);
            }

            // close the file
            fileCreo.closeFile();
        }
        
        // Else notify the user, and write nothing to the file
        else
        {
            log.log(LOG_TAG, "Error in config write out! (ids not equal to content)");
        }
    }

    /**
     * Gets the important data from the file.
     * A.K.A. the data after the identifier
     * @param data the list of data extracted
     * @return the extracted data
     */
    private String[] extractConfigValues(String[] data)
    {
        // Declare variable to hold the extracted data
        String[] result = new String[configEntries];
        
        // Iterate through the data array and get the content past the second entry
        for(int i = 0; i < data.length; i++)
        {
            result[i] = extract.contentPast(data[i], 2);
            
            // Print out the findings
            log.log(LOG_TAG, "Data Line " + i + ": "  + result[i]);
        }

        // Return the result
        return result;
    }

    /**
     * Function to convert an ArrayList to a String[].
     * The built in one + casting = not working
     * @param list the ArrayList to be converted
     * @return the array
     */
    private String[] listToArray(ArrayList<String> list)
    {
        // Create an aray of same size as the list 
        String[] result = new String[list.size()];
        
        // Iterate though the list, transferring data
        for(int i = 0; i < list.size(); i++)
        {
            result[i] = list.get(i);
        }
        
        // Return the result
        return result;
    }
}