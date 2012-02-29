/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scouting.gui.utilities;
import org.scouting.logger.Logger;
import org.scouting.scout.Main;

/*
 * @author aoneill
 * @breif
 */

public class DataRow 
{
    private String dataRow[];
    private static Logger dataRowLog = new Logger("Data Row");

    public DataRow() {}

    public DataRow(String array[])
    {
        dataRow = array;

        dataRowLog.setEnabled(Main.logActivate);
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

        dataRowLog.setEnabled(Main.logActivate);
    }

    public String valueAt(int member)
    {
        return dataRow[member];
    }

    public void printRowData()
    {
        dataRowLog.log("--------------");
        dataRowLog.log("Data Row Data:");
        dataRowLog.log("== Length: " + dataRow.length);
        dataRowLog.log("== Data:");
        for(int i = 0; i < dataRow.length; i++)
        {
            dataRowLog.log("== Row[" + i + "]: " + dataRow[i]);
        }
        dataRowLog.log("--------------");
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