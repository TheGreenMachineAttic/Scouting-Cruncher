/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.gui.utilities;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Global;

/*
 * @author aoneill
 * @breif
 */

public class DataRow 
{
    private String dataRow[];
    private static Logger dataRowLog = Global.log;
    private static String LOG_ID = "Data Row";

    public DataRow() {}

    public DataRow(String array[])
    {
        dataRow = array;
    }

    // Assumes that the first dimension is the column count
    public DataRow(String array[][], int rowNumber, int arrayWidth)
    {
        String result[] = new String[arrayWidth];

        for(int i = 0; i < arrayWidth; i++)
        {
            result[i] = array[rowNumber][i];
        }

        dataRow = result;
    }

    public String valueAt(int member)
    {
        return dataRow[member];
    }

    public void printRowData()
    {
        dataRowLog.log(LOG_ID, "--------------");
        dataRowLog.log(LOG_ID, "Data Row Data:");
        dataRowLog.log(LOG_ID, "== Length: " + dataRow.length);
        dataRowLog.log(LOG_ID, "== Data:");
        for(int i = 0; i < dataRow.length; i++)
        {
            dataRowLog.log(LOG_ID, "== Row[" + i + "]: " + dataRow[i]);
        }
        dataRowLog.log(LOG_ID, "--------------");
    }

    public String[][] dataRowArrayToStringArray(DataRow array[], int finalWidth)
    {
        int finalLength = array.length;
        String result[][] = new String[finalLength][finalWidth];

        for(int i = 0; i < finalLength; i++)
        {
            for(int j = 0; j < finalWidth; j++)
            {
                result[i][j] = array[i].valueAt(j);
            }
        }

        return result;
    }
}