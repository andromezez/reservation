/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_sd_itemrealisasi  = {
  get_selectBlankRow: function(){return "select '' kodetr,'' kodeitem,'' namaitem,0 jumlahakhir,sysdate jamawal, " +
	   "sysdate jamakhir,0 durasi, 0 biaya,'' statussebelum,'' statussesudah,'' keterangan, 0 biayaperjam,0 jumlahpeserta from dual"},
  get_selectFilterByKodeTr: function(kodetr){
      return "select a.kodetr,a.kodeitem,b.namaitem,a.jumlahakhir,TO_CHAR(a.jamawal,'hh24:mi') jamawal, " +
	   "TO_CHAR(a.jamakhir,'hh24:mi') jamakhir,a.durasi,a.biaya,a.statussebelum,a.statussesudah,a.keterangan,a.biayaperjam,a.jumlahpeserta " +
           "from sd_itemrealisasi a, m_item b " +
           "where SUBSTR(a.kodetr,1,16) = '" +kodetr+ "' " +
           "and a.kodeitem = b.kodeitem(+)"},
   get_selectCmb: function(){
      return "select distinct c.kodeitem,d.namaitem,'' kodetr, 0 jumlahakhir,'' jamawal, '' jamakhir,0 durasi, 0 biaya,'' statussebelum,'' statussesudah,'' keterangan,0 biayaperjam,0 jumlahpeserta  "+
             "from h_realisasi a, d_tglrealisasi b, sd_itemrealisasi c, m_item d  "+
             "where a.koderealisasi=b.koderealisasi and b.kodetr=c.kodetr and c.kodeitem=d.kodeitem  "+
             "order by d.namaitem"}
};

var arrayField_sd_itemrealisasiStore = [
    'kodetr','kodeitem','namaitem','jumlahakhir','jamawal','jamakhir','durasi','biayaperjam',
    'biaya','statussebelum','statussesudah','keterangan','jumlahpeserta'
];

var sd_itemrealisasiRecord = Ext.data.Record.create(arrayField_sd_itemrealisasiStore);

function get_sd_itemrealisasiStore(formMode,sqlString,pautoload){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'sd_itemrealisasi',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'sd_itemrealisasi',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  var autoload;
  if (typeof pautoload == "undefined"){
      autoload = true;
  }else{
      autoload = pautoload;
  }
  var sd_itemrealisasiStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, sd_itemrealisasiRecord),
          autoLoad: autoload
   });
   return sd_itemrealisasiStore;
}