package com.tonybeltramelli.lib.util;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 01/04/2014
 */
public class UMath
{
    public static double sigmoid(double x)
    {
        return (1 / (1 + Math.pow(Math.E, (-1 * x))));
    }

    public static double percent(double current, double total)
    {
        return (current * 100) / total;
    }

    public static double boundaryRestrict(double n, double min, double max)
    {
        if(n < min)
        {
            n = min;
        } else if(n > max)
        {
            n = max;
        }
        return n;
    }

    public static double accurateRound(double n, int i)
    {
        return Math.round(n * Math.pow(10, i)) / Math.pow(10, i);
    }

    public static double average(double[] values)
    {
        int i = values.length;
        int n = values.length;
        double total = 0;
        while(--i != -1) total += values[i];
        return total / n;
    }

    public static double degToRad(double deg)
    {
        return (deg / 180.0) * Math.PI;
    }

    public static double radToDeg(double rad)
    {
        return (rad / Math.PI) * 180.0;
    }
}
