/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_sd_itempengajuan  = {
  get_selectBlankRow: function(){return "select '' kodetp,'' kodeitem,'' namaitem,0 jumlahawal,0 jumlahakhir,sysdate jamawal, " +
	   "sysdate jamakhir,0 durasi, 0 biaya,0 antrian,'' keterangan, 0 biayaperjam,0 jumlahpeserta from dual"},
  get_selectCmb: function(){
      return "select distinct c.kodeitem,d.namaitem,'' kodetp,0 jumlahawal, 0 jumlahakhir,'' jamawal, '' jamakhir,0 durasi, 0 biaya,0 antrian,'' keterangan,0 biayaperjam,0 jumlahpeserta"+
             "from h_pengajuan a, d_tglpengajuan b, sd_itempengajuan c, m_item d "+
             "where a.kodepengajuan=b.kodepengajuan and b.kodetp=c.kodetp and c.kodeitem=d.kodeitem "+
             "order by d.namaitem"},
  get_selectFilterByKodeTp: function(kodetp){
      return "select a.kodetp,a.kodeitem,b.namaitem,a.jumlahakhir jumlahawal, "+
	   "a.jumlahakhir jumlahakhir,TO_CHAR(a.jamawal,'hh24:mi') jamawal,  "+
	   "TO_CHAR(a.jamakhir,'hh24:mi') jamakhir,a.durasi,a.biaya,a.antrian,a.keterangan,a.biayaperjam,a.jumlahpeserta  "+
           "from sd_itempengajuan a, m_item b "+
           "where SUBSTR(a.kodetp,1,16) = '"+ kodetp +"'  "+
           "and a.kodeitem = b.kodeitem(+)"},   
   get_selectBiayaAndDurasi: function(pkodeitem,pkodebp,pjamawal,pjamakhir,pjumlah){
       return "select '' kodetp, '' kodeitem, '' namaitem, 0 jumlahawal, 0 jumlahakhir, '' jamawal, '' jamakhir, " +
        "calc_pembulatandurasisewa('"+pjamawal+"', '"+pjamakhir+"') durasi, " +
        "(totalbiayasewaitem('"+pkodebp+"','"+pkodeitem+"','"+pjamawal+"', '"+pjamakhir+"')*"+pjumlah+") biaya, 0 antrian, '' keterangan, 0 biayaperjam,0 jumlahpeserta"+
        "from dual " }
}; 
var arrayField_sd_itempengajuanStore = [
    'kodetp','kodeitem','namaitem','jumlahawal','jumlahakhir','jamawal','jamakhir','durasi',
    'biaya','antrian','keterangan','biayaperjam','jumlahpeserta'
];
var sd_itempengajuanRecord = Ext.data.Record.create(arrayField_sd_itempengajuanStore);

function get_sd_itempengajuanStore(formMode,sqlString,pautoload){
  var params ;
  if(formMode =='READ') {
    params ={
        tablename:'sd_itempengajuan',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };
  }
  else if(formMode =='DELETE'){}
  else if(formMode =='UPDATE'){}
  else if(formMode =='INSERT'){
    params ={
        tablename:'sd_itempengajuan',
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
 
  var sd_itempengajuanStore = new Ext.data.Store({
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
   return sd_itempengajuanStore;
}

