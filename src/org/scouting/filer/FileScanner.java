package org.scouting.filer;

import java.io.File;
import java.util.Scanner;

/**
 * @author Alex O'Neill
 * @breif A class to take data from a text file
 */

public class FileScanner
{
    private Scanner read;

    // Used to open the text file in the Scanner
    public void openFile(String path, String name)
    {
        // Try to open the file at the path specified, and warn the user if it
        // fails

        System.out.println("Scanner: Opening " + name + " in " + path);
        try
        {
            read = new Scanner(new File(path + "/" + name));
        }
        catch(Exception e)
        {
            System.out.println("Could not open " + name + "... D:");
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
            System.out.println("File not found...");
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
            System.out.print("Error getting next line");
        }
        return result;
    }
}