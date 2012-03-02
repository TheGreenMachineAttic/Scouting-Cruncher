/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.logger;
/*
 * @author aoneill
 * @breif
 */

public class Logger 
{
    private static String PREFIX = " ";
    private static String SUFFIX = "";

    private boolean enabled = false;

    public Logger()
    {
        init();
    }

    private void init() {}

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public void log(String ID, String message)
    {
        String id = "[" + ID + "]";

        if(enabled)
            System.out.println(id + PREFIX + message + SUFFIX);
    }

    public void log()
    {
        if(enabled)
            System.out.println();
    }
}