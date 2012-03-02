/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.edinarobotics.gui.utilities;
import com.edinarobotics.logger.Logger;
import com.edinarobotics.scout.Main;


/*
 * @author aoneill
 * @breif
 */

public class Sorter 
{
    public static final int LOW_TO_HIGH = 1;
    public static final int HIGH_TO_LOW = 2;

    private static Logger sortLog = Main.mainLog;
    private static String LOG_ID = "Sorter";

    private int DATA_POINTS;

    public Sorter() {}

    public Sorter(int dataPoints)
    {
        DATA_POINTS = dataPoints;
    }

    public String[][] sortBest(String array[][], int member, int direction)
    {
        // sortLog.log("--------------------------");

        int arrayLength = getArrayLength(array);
        DataRow list[] = new DataRow[arrayLength];
        DataRow dr2;
        DataRow dr1;
        DataRow parser = new DataRow();

        //sortLog.log("Creating DataRow list...");
        for(int mainC = 0; mainC < arrayLength; mainC++)
        {
            list[mainC] = new DataRow(array, mainC, DATA_POINTS);
            //list[mainC].printRowData();
            //sortLog.log("Storing Team " + list[mainC].valueAt(0));
        }

        //sortLog.log("--------------------------");
        boolean finished = false;
        int iter = 0;
        while(!finished)
        {
            finished = true;
            for(int tIter = 1; tIter < arrayLength; tIter++)
            {
                dr1 = list[tIter - 1];
                //sortLog.log("Trying Team " + dr1.valueAt(0) + "'s value of " + dr1.valueAt(member) + " at member " + member);

                dr2 = list[tIter];
                //sortLog.log("Trying Team " + dr2.valueAt(0) + "'s value of " + dr2.valueAt(member) + " at member " + member);

                switch(direction)
                {
                    case LOW_TO_HIGH:
                        if(Double.parseDouble(dr2.valueAt(member)) < Double.parseDouble(dr1.valueAt(member)))
                        {
                            //sortLog.log("Team " + dr2.valueAt(0) + " is better than team " + dr1.valueAt(0));
                            list[tIter] = dr1;
                            list[tIter - 1] = dr2;

                            finished = false;
                            iter++;
                            //sortLog.log("Swapping teams...");
                        }
                        break;
                    case HIGH_TO_LOW:
                        if(Double.parseDouble(dr2.valueAt(member)) > Double.parseDouble(dr1.valueAt(member)))
                        {
                            //sortLog.log("Team " + dr2.valueAt(0) + " is better than team " + dr1.valueAt(0));
                            list[tIter] = dr1;
                            list[tIter - 1] = dr2;

                            finished = false;
                            iter++;
                            //sortLog.log("Swapping teams...");
                        }
                        break;
                    default:
                        System.err.println("I wont even try...");
                        break;
                }
            }
        }

        sortLog.log(LOG_ID, "Loops to Sort: " + iter);

        return parser.dataRowArrayToStringArray(list, DATA_POINTS);
    }

    public String[][] filterPenalties(String array[][], int member, int direction)
    {
        // sortLog.log("--------------------------");

        int arrayLength = getArrayLength(array);
        DataRow list[] = new DataRow[arrayLength];
        DataRow dr2;
        DataRow dr1;
        DataRow parser = new DataRow();

        //sortLog.log("Creating DataRow list...");
        for(int mainC = 0; mainC < arrayLength; mainC++)
        {
            list[mainC] = new DataRow(array, mainC, DATA_POINTS);
            //list[mainC].printRowData();
            //sortLog.log("Storing Team " + list[mainC].valueAt(0));
        }

        //sortLog.log("--------------------------");
        boolean finished = false;
        int iter = 0;
        while(!finished)
        {
            finished = true;
            for(int tIter = 1; tIter < arrayLength; tIter++)
            {
                dr1 = list[tIter - 1];
                //sortLog.log("Trying Team " + dr1.valueAt(0) + "'s value of " + dr1.valueAt(member) + " at member " + member);

                dr2 = list[tIter];
                //sortLog.log("Trying Team " + dr2.valueAt(0) + "'s value of " + dr2.valueAt(member) + " at member " + member);

                switch(direction)
                {
                    case LOW_TO_HIGH:
                        if(dr2.valueAt(member).equals("none") && !dr1.valueAt(member).equals("none"))
                        {
                            //sortLog.log("Team " + dr2.valueAt(0) + " is better than team " + dr1.valueAt(0));
                            list[tIter] = dr1;
                            list[tIter - 1] = dr2;

                            finished = false;
                            iter++;
                            //sortLog.log("Swapping teams...");
                        }
                        break;
                    case HIGH_TO_LOW:
                        if(!dr2.valueAt(member).equals("none") && dr1.valueAt(member).equals("none"))
                        {
                            //sortLog.log("Team " + dr2.valueAt(0) + " is better than team " + dr1.valueAt(0));
                            list[tIter] = dr1;
                            list[tIter - 1] = dr2;

                            finished = false;
                            iter++;
                            //sortLog.log("Swapping teams...");
                        }
                        break;
                    default:
                        System.err.println("I wont even try...");
                        break;
                }
            }
        }

        sortLog.log(LOG_ID, "Loops to Sort: " + iter);

        return parser.dataRowArrayToStringArray(list, DATA_POINTS);
    }

    public int getArrayLength(String array[][])
    {
        boolean done = false;
        int count = 0;
        while(!done)
        {
            try
            {
                String xyz = array[count][0];
                count++;
            }
            catch(Exception e)
            {
                done = true;
            }
        }

        return count;
    }
}