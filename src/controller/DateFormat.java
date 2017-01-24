package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
//        Date dt = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//        String strDate = sdf.format(dt);
//        System.out.println(strDate);
//        Date tgl = new Date("7/20/1994 00:00:00");
//        DateFormat df = new DateFormat();
//        System.out.println("Tgl : "+df.dateFormat(tgl));

    public static String dateFormat(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
        String strDate = sdf.format(dt);
        return strDate;
    }
    
    public static String dateFormatDash(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String strDate = sdf.format(dt);
        return strDate;
    }
    
    public static String dateFormatFull(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
        String strDate = sdf.format(dt);
        return strDate;
    }

    public static String dateFormatGetMonth(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        String strDate = sdf.format(dt);
        return strDate;
    }

    public static String dateFormatGetDate(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String strDate = sdf.format(dt);
        return strDate;
    }

    public static String dateFormatGetYear(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String strDate = sdf.format(dt);
        return strDate;
    }
    
    public static String dateFormatGetDay(Date dt) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String strDate = sdf.format(dt);
        return strDate;
    }
}
