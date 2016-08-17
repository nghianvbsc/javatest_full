package bscenter.tracnghiemlaptrinh.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by NIT Admin on 05/06/2016
 */

public class TimeHelper {

    public static String getCurrentTimeUTC() {
        SimpleDateFormat simpleDateFormatUTC = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormatUTC.format(new Date());
    }

    public static String convertTimeUtcToLocal(String timeUtc) {
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        destFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date date = destFormat.parse(timeUtc);
            destFormat.setTimeZone(TimeZone.getDefault());
            return destFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
