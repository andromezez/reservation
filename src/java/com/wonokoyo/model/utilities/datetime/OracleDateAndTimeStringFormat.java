/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wonokoyo.model.utilities.datetime;

import java.sql.Date;
import java.sql.Timestamp;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
/**
 *
 * @author Eddy
 */
public class OracleDateAndTimeStringFormat {
    /**
     * format mm/dd/yyyy
     */
    public static final String dateFormat = "mm/dd/yyyy";

    /**
     * format mm/dd/yyyy hh24:mi:ss
     */
    public static final String dateAndTimeFormat = "mm/dd/yyyy hh24:mi:ss";
    
    /**
     * format hh24:mi
     */
    public static final String timeOnlyWithoutSec = "hh24:mi";
    
    /**
     * format parameter must OracleDateAndTimeStringFormat.dateFormat
     */
    /*public static String convertJavaSQLDateToString(Date date,String format){
        //DateTimeFormatter formatTgl = DateTimeFormat.forPattern(format);
        //SimpleDateFormat formatter = new SimpleDateFormat(format);
        //java.util.Date d = new java.util.Date(date.getTime());                
        //DateTime tgl = new DateTime(d);
        //DateFormat df=new SimpleDateFormat(format);
        //String s=df.format(date);
        //return df.format(date);//date.toString(formatTgl);

        LocalDate tgl = new LocalDate(date.getTime());
        format = format.replace("dd", Integer.toString(tgl.getDayOfMonth()));
        format = format.replace("mm", Integer.toString(tgl.getMonthOfYear()));
        format = format.replace("yyyy", Integer.toString(tgl.getYear()));
        return format;        
    }*/

    /**
     * format parameter must OracleDateAndTimeStringFormat.dateAndTimeFormat
     */
    /*public static String convertJavaSQLTimestampToString(Timestamp times,String format){
        //DateTimeFormatter formatTgl = DateTimeFormat.forPattern(format);
        //SimpleDateFormat formatter = new SimpleDateFormat(format);
        //java.util.Date d = new java.util.Date(date.getTime());
        //DateTime tgl = new DateTime(d);
        //DateFormat df=new SimpleDateFormat(format);
        //String s=df.format(date);
        //return df.format(date);//date.toString(formatTgl);
    
            LocalDateTime tgl = new LocalDateTime(times.getTime());
            format = format.replace("dd", Integer.toString(tgl.getDayOfMonth()));
            format = format.replace("mm", Integer.toString(tgl.getMonthOfYear()));
            format = format.replace("yyyy", Integer.toString(tgl.getYear()));
            format = format.replace("hh24", Integer.toString(tgl.getHourOfDay()));
            format = format.replace("mi", Integer.toString(tgl.getMinuteOfHour()));
            format = format.replace("ss", Integer.toString(tgl.getSecondOfMinute()));
        
        return format;
    }*/
}
