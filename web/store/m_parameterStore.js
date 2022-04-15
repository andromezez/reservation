/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_m_parameter = {
  get_selectBlankRow: function(){return "select ' ' parname,' ' parvalue,' ' partype from m_parameter"},
  get_selectFilterByParName: function(){
      return "select '' parname, to_char((sysdate-(select parvalue from m_parameter where parname='default_hr_tglawal_lap')),'MM/dd/yyyy') parvalue, "+
             "to_char((sysdate+(select parvalue from m_parameter where parname='default_hr_tglakhir_lap')),'MM/dd/yyyy') partype from dual "
        }
};
    var m_parameterRecord = Ext.data.Record.create([
                'parname',
                'parvalue',
                'partype'
            ]);
    var m_parameter_xmlstore = new Ext.data.Store({
                proxy : new Ext.data.HttpProxy ({
                    url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
                }),
                baseParams:{
                    tablename:'m_parameter',
                    tableoperation:'READ_ALL_ROW'
              },
              //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
              reader: new Ext.data.XmlReader({
                record:'row',
                idPath:'parname'
              }, m_parameterRecord),
              autoLoad: false
            });
    function get_m_parameter(formMode,sqlString,pautoload){
              var params ;
              if(formMode =='READ') {
                params ={
                    tablename:'m_parameter',
                    tableoperation:'READ_CUSTOM_ROW',
                    sql:sqlString
                };
              }
              else if(formMode =='DELETE'){}
              else if(formMode =='UPDATE'){}
              else if(formMode =='INSERT'){}
              var autoload;
              if (typeof pautoload == "undefined"){
                  autoload = true;
              }else{
                  autoload = pautoload;
              }
              var xmlstore = new Ext.data.Store({
                      proxy : new Ext.data.HttpProxy ({
                            url: 'maincontroller'
                      }),
                      baseParams:params,
                      //url:'',
                      reader: new Ext.data.XmlReader({
                        record:'row'//,idPath:'id'
                      }, m_parameterRecord),
                      autoLoad: autoload
               });
               return xmlstore;
    }
