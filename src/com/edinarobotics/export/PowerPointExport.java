/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.export;
import com.edinarobotics.gui.utilities.Sorter;
import com.edinarobotics.file.Extracter;

import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hslf.usermodel.RichTextRun;

import java.awt.Color;
import java.io.FileOutputStream;
import java.util.ArrayList;



/*
 * @author aoneill
 * @breif
 */

class PowerPointExport
{
    private static Sorter sort = new Sorter(7);
    private static Extracter ext = new Extracter();
    private static SlideShow slideShow = new SlideShow();

    private static Color TITLE_BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private static Color TITLE_BORDER_COLOR = Color.GREEN;
    private static Color TITLE_TEXT_COLOR = Color.BLACK;
    private static String TITLE_FONT = "Helvetica";
    private static int TITLE_FONT_SIZE = 60;
    private static String TITLE_TEXT = "Green Machine\nScouting";

    private static String DATA_FONT = "Arial";
    private static int DATA_FONT_SIZE = 32*7;
    
    private static String allData[][];
    private static int teamCount;
    private static String fileName;
    private static String filePath;

    public PowerPointExport(String teamData[][], String name, String path)
    {
        allData = sort.sortBest(teamData, 4, Sorter.HIGH_TO_LOW);
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
            intro.setFillColor(TITLE_BACKGROUND_COLOR);
            intro.setLineColor(TITLE_BORDER_COLOR);
            intro.setMarginTop(150);
            intro.setMarginBottom(150);
            RichTextRun rtrIntro = intro.getTextRun().getRichTextRunAt(0);
            rtrIntro.setFontSize(TITLE_FONT_SIZE);
            rtrIntro.setFontName(TITLE_FONT);
            rtrIntro.setFontColor(TITLE_TEXT_COLOR);
            intro.setText(TITLE_TEXT);


            for(int i = 0; i < teamCount; i++)
            {
                Slide slide = slideShow.createSlide();
                
                TextBox title = slide.addTitle();
                title.setFillColor(TITLE_BACKGROUND_COLOR);
                title.setLineColor(TITLE_BORDER_COLOR);
                title.setMarginTop(10);
                RichTextRun rtrTitle = title.getTextRun().getRichTextRunAt(0);
                rtrTitle.setFontSize(TITLE_FONT_SIZE);
                rtrTitle.setFontName(TITLE_FONT);
                rtrTitle.setFontColor(TITLE_TEXT_COLOR);
                title.setText("Team " + allData[i][0]);

                TextBox data = slide.addTitle();
                data.setMarginTop(150);
                data.setHorizontalAlignment(TextBox.AlignLeft);
                RichTextRun rtrData = data.getTextRun().getRichTextRunAt(0);
                rtrData.setFontName(DATA_FONT);

                String teamAutoScore = "    Average Autonomous Score:  " + allData[i][1];
                String teamMainGameScore = "    Average Main Game Score:  " + allData[i][2];
                String teamEndGameScore = "    Average End Game Score:  " + allData[i][3];
                String dataBreak = "+ _____________________________";
                String teamTotalAverageScore = "    Average Total Score:  " + allData[i][4];


                ArrayList<String> penalties = new ArrayList<String>();
                penalties.add("Penalties:");
                if(Integer.parseInt(allData[i][5]) > 1)
                {
                    for(int j = 0; j < Integer.parseInt(allData[i][5]); j++)
                    {
                        penalties.add(ext.extractEntry(allData[i][6], j));
                    }
                }
                else if(Integer.parseInt(allData[i][5]) == 1)
                {
                    penalties.add(allData[i][6]);
                }


                data.setText(teamAutoScore + "\n" + teamMainGameScore + "\n" + teamEndGameScore + "\n" + dataBreak + "\n\n" + teamTotalAverageScore + "\n\n" + listToString(penalties));
                int lineNumbers = 7 + penalties.size();

                rtrData.setFontSize(DATA_FONT_SIZE / lineNumbers);
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

    private String listToString(ArrayList<String> list)
    {
        String result = list.get(0);

        for(int i = 1; i < list.size(); i++)
        {
            result = result + "\n   - " + list.get(i);
        }

        return result;
    }
}