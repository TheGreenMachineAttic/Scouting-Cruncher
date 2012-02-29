/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scouting.logger;
/*
 * @author aoneill
 * @breif
 */

public class Logger 
{
    private static String DEFAULT_ID = "[Log]";
    private static String PREFIX = " ";
    private static String SUFFIX = "";

    private boolean enabled = false;

    public Logger()
    {
        init();
    }

    public Logger(String defaultID)
    {
        DEFAULT_ID = "[" + defaultID + "]";
        init();
    }

    private void init() {}

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public void log(String message)
    {
        if(enabled)
        {
            System.out.println(DEFAULT_ID + PREFIX + message + SUFFIX);
        }
    }

    public void log()
    {
        if(enabled)
        {
            System.out.println();
        }
    }
}