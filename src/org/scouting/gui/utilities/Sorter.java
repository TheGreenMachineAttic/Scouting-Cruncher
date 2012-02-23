/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.scouting.gui.utilities;
/*
 * @author aoneill
 * @breif
 */

public class Sorter 
{
    public static final int LOW_TO_HIGH = 1;
    public static final int HIGH_TO_LOW = 2;
    private int DATA_POINTS;

    public Sorter(int dataPoints)
    {
        DATA_POINTS = dataPoints;
    }

    public String[][] sortBest(String array[][], int member, int direction)
    {
        // System.out.println("--------------------------");

        int arrayLength = getArrayWidth(array);
        DataRow list[] = new DataRow[arrayLength];
        DataRow dr2;
        DataRow dr1;
        DataRow parser = new DataRow();

        //System.out.println("Creating DataRow list...");
        for(int mainC = 0; mainC < arrayLength; mainC++)
        {
            list[mainC] = new DataRow(array, mainC, DATA_POINTS);
            //list[mainC].printRowData();
            //System.out.println("Storing Team " + list[mainC].valueAt(0));
        }

        //System.out.println("--------------------------");
        boolean finished = false;
        int iter = 0;
        while(!finished)
        {
            finished = true;
            for(int tIter = 1; tIter < arrayLength; tIter++)
            {
                dr1 = list[tIter - 1];
                //System.out.println("Trying Team " + dr1.valueAt(0) + "'s value of " + dr1.valueAt(member) + " at member " + member);

                dr2 = list[tIter];
                //System.out.println("Trying Team " + dr2.valueAt(0) + "'s value of " + dr2.valueAt(member) + " at member " + member);

                switch(direction)
                {
                    case LOW_TO_HIGH:
                        if(Double.parseDouble(dr2.valueAt(member)) < Double.parseDouble(dr1.valueAt(member)))
                        {
                            //System.out.println("Team " + dr2.valueAt(0) + " is better than team " + dr1.valueAt(0));
                            list[tIter] = dr1;
                            list[tIter - 1] = dr2;

                            finished = false;
                            iter++;
                            //System.out.println("Swapping teams...");
                        }
                        break;
                    case HIGH_TO_LOW:
                        if(Double.parseDouble(dr2.valueAt(member)) > Double.parseDouble(dr1.valueAt(member)))
                        {
                            //System.out.println("Team " + dr2.valueAt(0) + " is better than team " + dr1.valueAt(0));
                            list[tIter] = dr1;
                            list[tIter - 1] = dr2;

                            finished = false;
                            iter++;
                            //System.out.println("Swapping teams...");
                        }
                        break;
                    default:
                        System.err.println("I wont even try...");
                        break;
                }
            }
        }

        System.out.println("Loops to Sort: " + iter);

        return parser.dataRowArrayToStringArray(list, DATA_POINTS);
    }

    private int getArrayWidth(String array[][])
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