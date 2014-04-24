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
    public String getEncoding();
}
