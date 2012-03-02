/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scouting.export;

import org.scouting.gui.utilities.Sorter;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

import java.io.FileOutputStream;


/*
 * @author aoneill
 * @breif
 */

class PowerPointExport
{
    Sorter sort = new Sorter();

    private static SlideShow slideShow = new SlideShow();
    
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

        createPPT();
        writeOut();
    }

    private void createPPT()
    {
        try
        {
            Slide slide = slideShow.createSlide();
        }
        catch(Exception e) {}
    }

    private void writeOut()
    {
        try
        {
            FileOutputStream out = new FileOutputStream("slideshow.ppt");
            slideShow.write(out);
            out.close();
        }
        catch(Exception e) {}
    }
}