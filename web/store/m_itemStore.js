/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_m_item  = {
  get_selectMItemFilterByStatusavailability: function(){
      return "select a.kodeitem, a.kodejenis, b.namajenis, " +
            "a.namaitem, a.durasiminsewa, a.kapasitas, "+
            "a.keterangan, a.aktif, a.statusavailability, "+
            "a.pricechangeable "+
            "from m_item a, m_jenis b "+
            "where a.statusavailability = 1 and a.kodejenis = b.kodejenis and a.aktif='1'"+
            "order by kodejenis "
  },
  get_selectFilterByKodeitem: function(pKodeItem){
      return "select a.kodeitem, a.kodejenis, b.namajenis, " +
            "a.namaitem, a.durasiminsewa, a.kapasitas, "+
            "a.keterangan, a.aktif, a.statusavailability, "+
            "a.pricechangeable "+
            "from m_item a, m_jenis b "+
            "where a.kodeitem ='"+pKodeItem+"' and a.kodejenis = b.kodejenis ";            
  }
};
var m_itemRecord = Ext.data.Record.create([
            'kodeitem',
            'kodejenis',
            'namajenis',
            'namaitem',
            'durasiminsewa',
            'kapasitas',
            'keterangan',
            'aktif',
            'statusavailability',
            'pricechangeable'
        ]);
var m_item_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'m_item',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodeitem'
          }, m_itemRecord),
          autoLoad: false
        });

function get_m_itemStore(sqlString,url){
  var params ={
        tablename:'m_item',
        tableoperation:'READ_CUSTOM_ROW',
        sql:sqlString
    };  
  var urlget = "maincontroller";
  if (typeof url != "undefined"){
      urlget = url;
  }
  var m_itemStore = new Ext.data.Store({
          proxy : new Ext.data.HttpProxy ({
                url: urlget
          }),
          baseParams:params,
          //url:'',
          reader: new Ext.data.XmlReader({
            record:'row'//,idPath:'id'
          }, m_itemRecord),
          autoLoad: false
   });
   return m_itemStore;
}