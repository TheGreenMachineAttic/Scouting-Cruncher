/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scouting.export;

import org.scouting.gui.utilities.Sorter;

/*
 * @author aoneill
 * @breif
 */

class PowerPointExport
{
    Sorter sort;
    
    private static String allData[][];
    private static int teamCount;
    private static String fileName;
    private static String filePath;

    public PowerPointExport(String teamData[][], String name, String path)
    {
        allData = teamData;
        teamCount = sort.getArrayLength(allData);
        fileName = name;
        filePath = path;


    }
    





}