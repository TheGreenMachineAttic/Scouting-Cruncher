package com.edinarobotics.filer;

import com.edinarobotics.scout.Global;

/*
 * @author aoneill
 * @breif A class to manage taking data from a string by following a simple 
 *          formats
 */
public class Extracter 
{
    // Store the separator in a common place
    public static char defaultSeparator = Global.DATA_SEPARATOR.charAt(0);

    /**
     * Used to take the word from the string input at the given entry.
     * Takes info from string: [entry1][separator][entry2]
     * @param input the String to be separated
     * @param entryNumber the entry to be extracted
     * @return the extracted entry
     */
    public String extractEntry(String input, int entryNumber)
    {
        // Declare variables to be used for extaction
        String result;
        int stringLength = input.length();
        int newStringLength;
        int finalStringLength;
        char array[];
        int cutOff = 0;
        int count = 0;

        // Convert the input to an array of characters
        array = input.toCharArray();

        // Sacn the array for the occurance of a ":", and store this
        // position to a variable. Repeat until the count reaches the
        // entryNumber

        for(int i = 0; i < stringLength; i++)
        {
            if(array[i] == defaultSeparator)
            {
                cutOff = i;
                count++;

                if(count == entryNumber - 1)
                {
                    break;
                }

            }
        }


        // The length of the new string depends upon where it is taken from
        // If the entryNumber is 1, store the length of the string as the cutOff
        // value. Else, store the newStringLength as the original length minus
        // the sum of the cutOff and one
        if(entryNumber == 1)
        {
            newStringLength = cutOff;
        }
        else
        {
            newStringLength = stringLength - (cutOff + 1);
        }

        // Set up a new array for the String to be returned
        char array2[] = new char[newStringLength];

        if(entryNumber == 1)
        {
            // Transfer the data from the first array to the second array up to the
            // cutoff point
            for(int i = 0; i < newStringLength; i++)
            {
                // Direct transfer
                array2[i] = array[i];
            }
        }
        else
        {
            // Transfer the data from the first array to the second array from the
            // cutoff to the end of the original string
            for(int i = 0; i < newStringLength; i++)
            {
                // Indirect transfer
                array2[i] = array[i + cutOff + 1];
            }
        }

        // Check the new array of the string for an occurance of the separator
        // to isolate the correct result
        // The process is identical to the one on top (more or less)
        boolean foundSep = false;
        for(int i = 0; i < newStringLength; i++)
        {
            if(array2[i] == defaultSeparator)
            {
                foundSep = true;
                cutOff = i;
                break;
            }
        }

        finalStringLength = cutOff;
        char array3[] = new char[finalStringLength];

        // If a separator was found, remove the unneeded data
        if(foundSep)
        {
            for(int i = 0; i < finalStringLength; i++)
            {
                // Direct transfer
                array3[i] = array2[i];
            }

            // Convert the array to a string, remove any white space, before or
            // after, and then return the string
            result = String.valueOf(array3).trim();
        }
        else
        {
            // Same here, just with array2
            result = String.valueOf(array2).trim();
        }
        
        // Return the result
        return result;
    }

    /**
     * Returns the content of a String past a certain data separator
     * @param input the string to be analyzed
     * @param entryNumber the entry number to go from
     * @return the cut off string
     */
    public String contentPast(String input, int entryNumber)
    {
        // Declare variables to be used for extaction
        String result;
        int stringLength = input.length();
        int newStringLength;
        char array[];
        int cutOff = 0;
        int count = 0;

        // Convert the input to an array of characters
        array = input.toCharArray();

        // Sacn the array for the occurance of a ":", and store this
        // position to a variable. Repeat until the count reaches the
        // entryNumber

        for(int i = 0; i < stringLength; i++)
        {
            if(array[i] == defaultSeparator)
            {
                cutOff = i;
                count++;

                if(count == entryNumber - 1)
                {
                    break;
                }

            }
        }


        // The length of the new string depends upon where it is taken from
        // If the entryNumber is 1, store the length of the string as the cutOff
        // value. Else, store the newStringLength as the original length minus
        // the sum of the cutOff and one
        if(entryNumber == 1)
        {
            newStringLength = cutOff;
        }
        else
        {
            newStringLength = stringLength - (cutOff + 1);
        }

        // Set up a new array for the String to be returned
        char array2[] = new char[newStringLength];

        if(entryNumber == 1)
        {
            // Transfer the data from the first array to the second array up to the
            // cutoff point
            for(int i = 0; i < newStringLength; i++)
            {
                // Direct transfer
                array2[i] = array[i];
            }
        }
        else
        {
            // Transfer the data from the first array to the second array from the
            // cutoff to the end of the original string
            for(int i = 0; i < newStringLength; i++)
            {
                // Indirect transfer
                array2[i] = array[i + cutOff + 1];
            }
        }

        // Same here, just with array2
        result = String.valueOf(array2).trim();

        // Return the result
        return result;
    }

    // UNUSED CURRENTLY
//    // Used to take the word from the string input at the given entry
//    // Takes info from string: <entry1><separator><entry2>
//    public String extractEntry2(String input, int entryNumber)
//    {
//        // Declare variables to be used for extaction
//        String result = "";
//        int stringLength = input.length();
//
//        java.util.regex.Matcher math = new Matcher();
//    }
}
