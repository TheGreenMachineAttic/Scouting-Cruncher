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
    private static LoggerGUI logGUI = new LoggerGUI();


    private boolean guiEnabled = false;
    private boolean enabled = false;

    public Logger()
    {
        init();
    }

    public Logger(String defaultID)
    {
        DEFAULT_ID = defaultID;
        init();
    }

    private void init() {}

    public void setGUIVisible(boolean enabled)
    {
        guiEnabled = enabled;
        logGUI.setVisible(guiEnabled);
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public void log(String message)
    {
        if(enabled)
        {
            System.out.println(DEFAULT_ID + ": " + message);
        }

        if(guiEnabled)
        {
            logGUI.addMessage(message);
        }
    }
}