/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Get current date in mm/dd/yyyy format
 */
function getCurrentDate(){
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1;//January is 0!
    var yyyy = today.getFullYear();
    if(dd<10){dd='0'+dd}
    if(mm<10){mm='0'+mm}

    return mm+'/'+dd+'/'+yyyy;
}

/**
 * Parsing date variable into mm/dd/yyyy format.
 * If success, return a string. If not return original value.
 */
function parseToDateOnly(dt){
    if(typeof dt != "undefined"){
        if (dt.getMonth) {
            return dt.format('m/d/Y');
        }
    }
    return dt;
}

/**
 * parsing date variable into mm/dd/yyyy H:i:s format.
 * If success, return a string. If not return original value.
 */
function parseToDateTimeOnly(dt){
//    console.log(dt);
    if(typeof dt != "undefined"){
        if (dt.getMonth) {
            return dt.format('m/d/Y H:i:s');
        }
    }
    return dt;    
}

/**
 * get the difference from time1 and time2. <br/>
 * format time is hh24:mi. <br/>
 * if time1 less than time2, function will return -1. <br/>
 * If not, function will return the difference in minute. <br/>
 */
function getTimeDifference(time1,time2){
    var time1Array = time1.split(":");
    var time2Array = time2.split(":");
    //console.log("time1: "+time1Array[0].concat(time1Array[1]));
    //console.log("time2: "+time2Array[0].concat(time2Array[1]));   
    if(parseInt(time1Array[0],10)>parseInt(time2Array[0],10)){
        var minute = parseInt(time1Array[0].concat(time1Array[1]),10) - parseInt(time2Array[0].concat(time2Array[1]),10);
        minute = minute - (40*(parseInt(time1Array[0],10)-parseInt(time2Array[0],10)));
        return minute;
    }else if(time1Array[0]==time2Array[0]){
        var minute = parseInt(time1Array[0].concat(time1Array[1]),10) - parseInt(time2Array[0].concat(time2Array[1]),10);
        return minute;
    }
    return -1;    
}