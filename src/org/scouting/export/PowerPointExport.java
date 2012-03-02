/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scouting.export;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import org.scouting.gui.utilities.Sorter;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;

import java.io.FileOutputStream;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.model.TextShape;
import org.apache.poi.hslf.usermodel.RichTextRun;


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
            Slide introSlide = slideShow.createSlide();
            TextBox intro = introSlide.addTitle();
            intro.setFillColor(Color.LIGHT_GRAY);
            intro.setLineColor(Color.GREEN);
            RichTextRun rtrIntro = intro.getTextRun().getRichTextRunAt(0);
            rtrIntro.setFontSize(60);
            rtrIntro.setFontName("Helvetica");
            intro.setText("Green Machine\nScouting");

            for(int i = 0; i < teamCount; i++)
            {
                Slide slide = slideShow.createSlide();
                TextBox title = slide.addTitle();
                title.setFillColor(Color.LIGHT_GRAY);
                title.setLineColor(Color.GREEN);
                RichTextRun rtrTitle = title.getTextRun().getRichTextRunAt(0);
                rtrTitle.setFontSize(32);
                rtrTitle.setFontName("Arial");
                title.setText("Team " + allData[i][0]);

                TextBox data = slide.addTitle();
                data.setMarginTop(100);
                data.setHorizontalAlignment(TextBox.AlignLeft);
                RichTextRun rtrData = data.getTextRun().getRichTextRunAt(0);
                rtrData.setFontSize(32);
                rtrData.setFontName("Arial");

                String teamAutoScore = "Average Autonomous Score: " + allData[i][1];
                String teamMainGameScore = "Average Main Game Score: " + allData[i][2];
                String teamEndGameScore = "Average End Game Score: " + allData[i][3];
                String teamTotalAverageScore = "Average Total Score: " + allData[i][4];

                data.setText(teamAutoScore + "\n" + teamMainGameScore + "\n" + teamEndGameScore + "\n" + teamTotalAverageScore);

            }
        }
        catch(Exception e) {}
    }

    private void writeOut()
    {
        try
        {
            FileOutputStream out = new FileOutputStream(filePath + "/" + fileName + ".ppt");
            slideShow.write(out);
            out.close();
        }
        catch(Exception e) {}
    }
}