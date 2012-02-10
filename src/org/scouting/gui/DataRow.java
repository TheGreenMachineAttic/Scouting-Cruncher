/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scouting.gui;
/*
 * @author aoneill
 * @breif
 */

public class DataRow 
{
    private String dataRow[];
    private int dataLength;

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
        dataLength = arrayWidth;
    }

    public String valueAt(int member)
    {
        return dataRow[member];
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