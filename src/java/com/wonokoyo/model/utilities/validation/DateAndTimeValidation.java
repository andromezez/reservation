/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model.utilities.validation;
import com.wonokoyo.model.utilities.datetime.JavaDateAndTimeStringFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author king peter
 */
public class DateAndTimeValidation extends BasicValidation{
    /**
     *  date1 must in MM/dd/yy format
     */
    public static boolean isDate1_biggerThanOrEqual_currentDate(String date1){
        Date today = new Date();        
        SimpleDateFormat formatter = new SimpleDateFormat(JavaDateAndTimeStringFormat.dateFormat);
       
        
        try {
            today = formatter.parse(formatter.format(today));
            Date tgl1 = formatter.parse(date1);
            if(tgl1.getTime()>=today.getTime()){
            }                                  
        } catch (ParseException ex) {
            ex.printStackTrace();            
        }
        return false;
    }        
    
    /**
     *  date1 must in MM/dd/yy format
     */
    public static boolean isDate1_lowerThanOrEqual_currentDate(String date1){
        Date today = new Date();        
        SimpleDateFormat formatter = new SimpleDateFormat(JavaDateAndTimeStringFormat.dateFormat);       
        
        try {
            today = formatter.parse(formatter.format(today));
            Date tgl1 = formatter.parse(date1);
            if(tgl1.getTime()<=today.getTime()){
                return true;
            }                                  
        } catch (ParseException ex) {
            ex.printStackTrace();            
        }        
        return false;
    }
    
    /**
     * 
     * @param time1 - format 24:00
     * @param time2 - format 24:00
     * @return boolean
     */
    public static boolean isTime1_biggerThanOrEqual_time2(String time1, String time2){
        time1 = time1.replaceFirst(":", "");
        time2 = time2.replaceFirst(":", "");
        int number_time1 = Integer.parseInt(time1);
        int number_time2 = Integer.parseInt(time2);
        if(number_time1>=number_time2)
            return true;
        else
            return false;
    }
    
    /**
     * 
     * @param time1 - format 24:00
     * @param time2 - format 24:00
     * @return boolean
     */
    public static boolean isTime1_lowerThanOrEqual_time2(String time1, String time2){
        time1 = time1.replaceFirst(":", "");
        time2 = time2.replaceFirst(":", "");
        int number_time1 = Integer.parseInt(time1);
        int number_time2 = Integer.parseInt(time2);
        if(number_time1<=number_time2)
            return true;
        else
            return false;
    }
}
