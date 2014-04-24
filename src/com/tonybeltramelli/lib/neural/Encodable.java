package com.tonybeltramelli.lib.neural;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 17/04/2014
 */
public interface Encodable
{
    public static final char HIDDEN = 'h';
    public static final char OUTPUT = 'o';
    public static final char INPUT = 'i';
    public static final char WEIGHT = 'w';
    //
    public static final String DNA_REG_EXP = "([iho][0-9]+([w][0-9]+[ho][0-9]+))([w][0-9]+[ho][0-9]+)*";
    public static final String CONNECTION_REG_EXP = "([w][0-9]+[ho][0-9]+)";
    public static final String WEIGHT_REG_EXP = "([who][0-9]+)";
    //
    public String getEncoding();
}
