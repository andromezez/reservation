/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wonokoyo.model.utilities.validation;



/**
 *
 * @author king peter
 */
public class BasicValidation {
    public static FieldStatus fieldsCannotNullString( StringField[] data){
        FieldStatus fs = new FieldStatus();
        for (int i=0;i<data.length;i++){
            if(data[i].value.equalsIgnoreCase("")){
                fs.fieldname = data[i].fieldname;
                fs.isError = true;
                return fs;
            }
        }
        fs.fieldname = "";
        fs.isError = false;
        return fs;
    }
    
    public static FieldStatus fieldsCannot_0_number( NumberField[] data){
        FieldStatus fs = new FieldStatus();
        for (int i=0;i<data.length;i++){
            if(data[i].value==0){
                fs.fieldname = data[i].fieldname;
                fs.isError = true;
                return fs;
            }
        }
        fs.fieldname = "";
        fs.isError = false;
        return fs;
    }
    
    public static FieldStatus fieldsCannot_under_0_number( NumberField[] data){
        FieldStatus fs = new FieldStatus();        
        for (int i=0;i<data.length;i++){
            if(data[i].value < 0){
                fs.fieldname = data[i].fieldname;
                fs.isError = true;
                return fs;
            }
        }
        fs.fieldname = "";
        fs.isError = false;
        return fs;
    }
    
    public static FieldStatus fieldsCannot_undefined_string( StringField[] data){
        FieldStatus fs = new FieldStatus();       
        for (int i=0;i<data.length;i++){
            if(data[i].value.equalsIgnoreCase("undefined")){
                fs.fieldname = data[i].fieldname;
                fs.isError = true;
                return fs;
            }
        }
        fs.fieldname = "";
        fs.isError = false;
        return fs;
    }
    /**
     * 
     * @param date - list of string
     * @return boolean
     */
    public static boolean isNoDuplicateString(String[] str){        
        for (int i=0;i<str.length;i++){
            for(int j=0;j<str.length;j++){
                if(i!=j){
                    if(str[i].equalsIgnoreCase(str[j]))
                        return false;
                }                
            }
        }
        return true;
    }
    public static FieldStatus fieldsCannot_NaN_string( StringField[] data){
        FieldStatus fs = new FieldStatus();       
        for (int i=0;i<data.length;i++){
            if(data[i].value.equalsIgnoreCase("NaN")){
                fs.fieldname = data[i].fieldname;
                fs.isError = true;
                return fs;
            }
        }
        fs.fieldname = "";
        fs.isError = false;
        return fs;
    }
    
    public static boolean isNumber1_biggerThan_number2(int number1,int number2){       
        if(number1>number2){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean isNumber1_lowerThan_number2(int number1,int number2){       
        if(number1<number2){
            return true;
        }else{
            return false;
        }
    }                
}
