package com.tonybeltramelli.lib.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 24/04/2014
 */
public class RegExp
{
    public static Matcher parse(String regExp, String string)
    {
        Pattern p = Pattern.compile(regExp);
        return p.matcher(string);
    }
}
