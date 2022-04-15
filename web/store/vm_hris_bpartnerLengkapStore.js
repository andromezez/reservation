
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var SqlCustom_vm_hris_bpartnerLengkap = {
  selectFilterByCostCenter: "SELECT a.* FROM vm_hris_bpartnerlengkap a, m_user b WHERE a.kodecostcenter = 'filterCostCenter' and a.kodebp=b.kodebp",
  get_selectFilterByKodeBp: function(pkodebp){
      return "select distinct a.kodebp, a.namabp, a.nik, a.kodejabatan, a.namajabatan, a.kodeorg, a.namaorg, "
        + "a.kodeperusahaan, a.namaperusahaan, a.kodecostcenter, userid "
        + "from VM_HRIS_BPARTNERLENGKAP a "
        + "where  a.kodebp = '"+pkodebp+"' "},
  selectKaryawanPemohon: function(){
      return "select a.nik,a.namabp,a.kodebp,a.kodeperusahaan,a.namaperusahaan,a.kodeorg,a.namaorg,a.kodejabatan,a.namajabatan,a.kodecostcenter,a.userid "+
             "from vm_hris_bpartnerlengkap a "+
             "left join vm_hris_karyawan b on a.kodebp=b.kodebp and a.kodeorg=b.kodeorg "+
             "where a.userid is not null"
  },
  selectKaryawanPemohonAll: function(){
      return "select nik,namabp,kodebp,kodeperusahaan,namaperusahaan,kodeorg,namaorg,kodejabatan,namajabatan,kodecostcenter,userid "+
             "from vm_hris_bpartnerlengkap where bpkaryawan=1 and nik is not null"
  },
  selectCustomer: function(){
    return "select c.nik,c.namabp,c.kodebp,c.kodeperusahaan,c.namaperusahaan,c.kodeorg,c.namaorg,c.kodejabatan,c.namajabatan,c.kodecostcenter,c.userid "+
           "from vm_hris_bpartnerlengkap c where c.bpcustomer=1"  
  },
  selectKaryawanFilterByUserId: function(userId){
      return "select a.nik,a.namabp,a.kodebp,a.kodeperusahaan,a.namaperusahaan,a.kodeorg,a.namaorg,a.kodejabatan,a.namajabatan,a.kodecostcenter,a.userid "+
             "from vm_hris_bpartnerlengkap a "+
             "left join vm_hris_jabatankaryawan b on a.nik=b.nik and a.kodejabatan = b.kodejabatan "+
             "where a.userid='"+userId+"'"
  }
};

var arrayField_vm_hris_bpartnerLengkap = [
    'kodebp', 'nik', 'namabp', 'kodeperusahaan', 'namaperusahaan','kodeorg',
    'namaorg', 'kodejabatan','namajabatan','kodecostcenter','userid'
];

var vm_hris_bpartnerLengkapRecord = Ext.data.Record.create(arrayField_vm_hris_bpartnerLengkap);

var vm_hris_bpartnerLengkap_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy (/*{
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }*/new Ext.data.Connection({
                          url: "maincontroller",
                          timeout: 120000 })),
            baseParams:{
                tablename:'vm_bpartnerLengkap',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodebp'
          }, vm_hris_bpartnerLengkapRecord),
          autoLoad: false
        });

function customSelect_vm_hris_bpartnerLengkap_xmlstore (sqlString) {

    return (new Ext.data.Store({
            proxy : new Ext.data.HttpProxy (/*{
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }*/new Ext.data.Connection({
                          url: "maincontroller",
                          timeout: 120000 })),
            baseParams:{
                tablename:'vm_bpartnerLengkap',
                tableoperation:'READ_CUSTOM_ROW',
                sql : sqlString
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row'
            //idPath:'kodebp'
          }, vm_hris_bpartnerLengkapRecord),
          autoLoad: false
        }));
}