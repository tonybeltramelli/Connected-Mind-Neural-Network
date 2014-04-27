package com.tonybeltramelli.lib.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Tony Beltramelli www.tonybeltramelli.com - created 27/04/2014
 */
public class UDate
{
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FILE_NAME_DATE_FORMAT = "ddMMyyyy-HHmmss";

    public static String getDateNowAsString(String dateFormat)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(Calendar.getInstance().getTime());
    }
}
