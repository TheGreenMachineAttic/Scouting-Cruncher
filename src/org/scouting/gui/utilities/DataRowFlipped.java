/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scouting.gui.utilities;
/*
 * @author aoneill
 * @breif
 */

public class DataRowFlipped 
{
    private String dataRow[];
    private int dataLength;

    public DataRowFlipped() {}

    public DataRowFlipped(String array[])
    {
        dataRow = array;
    }

    // Assumes that the first dimension is the column count
    public DataRowFlipped(String array[][], int rowNumber, int arrayWidth)
    {
        String result[] = new String[arrayWidth];

        for(int i = 0; i < arrayWidth; i++)
        {
            System.out.println("Storing Array element " + i);
            result[i] = array[rowNumber][i];
        }

        dataRow = result;
        dataLength = arrayWidth;
    }

    public String valueAt(int member)
    {
        return dataRow[member];
    }

    public void printRowData()
    {
        System.out.println("--------------");
        System.out.println("Data Row Data:");
        System.out.println("== Length: " + dataRow.length);
        System.out.println("== Data:");
        for(int i = 0; i < dataRow.length; i++)
        {
            System.out.println("== Row[" + i + "]: " + dataRow[i]);
        }
        System.out.println("--------------");
    }

    public String[][] dataRowArrayToStringArray(DataRowFlipped array[], int finalWidth)
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