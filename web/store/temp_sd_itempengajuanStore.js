/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function get_temp_sd_itempengajuanStore(formMode,sqlString,pautoload,jsonparametertambahan){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'temp_sd_itempengajuan',
        tableoperation:'READ_ALL_ROW'        
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'temp_sd_itempengajuan',
        tableoperation:'READ_ALL_ROW'
    };
  }
  if (sqlString != ''){
      params.sql = sqlString;
      params.tableoperation = 'READ_CUSTOM_ROW';
  }
  if (typeof jsonparametertambahan != "undefined"){      
    for(var prop in jsonparametertambahan) {
        if(jsonparametertambahan.hasOwnProperty(prop))
              params[prop] = jsonparametertambahan[prop];
    }
  }
  
  var autoload;
  if (typeof pautoload == "undefined"){
      autoload = true;
  }else{
      autoload = pautoload;
  }
 
  var temp_sd_itempengajuanStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, sd_itempengajuanRecord),
          autoLoad: autoload
   });
   return temp_sd_itempengajuanStore;
}