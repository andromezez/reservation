/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getForm_lrekapsewaruang(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint){
    var awal;var akhir;var namabp;var namaitem;var c=1; var d=1;
    var tglStore=get_m_parameter("READ",SqlCustom_m_parameter.get_selectFilterByParName(),false);
    tglStore.on('load',function(){
                      
                  awal=tglStore.getAt(0).get('parvalue');
                  akhir=tglStore.getAt(0).get('partype');
                  tglAwal.setValue(awal);
                  tglAkhir.setValue(akhir);
    });
   tglStore.load();
     var arrayCmbPemohon = new Ext.data.ArrayStore({
                     id: 0,
                     fields: ['myId','displayText'],
                     data: [['','']]
                     });
    var cmb_pemohon = new Ext.form.ComboBox({
                    hiddenName: 'combopemohon',
                    mode: 'local',
                    width: 140,
                    store: arrayCmbPemohon,
                    displayField:'displayText',
                    valueField:'myId',
                    anchor:'95%',
                    disabled:true
    });
    var arrayCmbItem = new Ext.data.ArrayStore({
                     id: 0,
                     fields: ['myId','displayText'],
                     data: [['','']]
                     });
    var cmb_item = new Ext.form.ComboBox({
                    hiddenName: 'comboitem',
                    mode: 'local',
                    width: 140,
                    store: arrayCmbItem,
                    displayField:'displayText',
                    valueField:'myId',
                    anchor:'95%',
                    disabled:true
    });
    var storePemohon=get_t_realisasiStore("READ",SqlCustom_t_realisasi.get_selectCmbRealisasi(),false);    
    storePemohon.on('load',function(){
        var arrayColumns=[];        
        for (i = 0 ; i <storePemohon.getCount() ;i++)
          {           
              arrayColumns[i] =[storePemohon.getAt(i).get('kodebp'),storePemohon.getAt(i).get('namabp')];
              
          }          
        arrayCmbPemohon.loadData( arrayColumns );        
    });
    storePemohon.load();
    
  var storeItem=get_sd_itemrealisasiStore("READ",SqlCustom_sd_itemrealisasi.get_selectCmb(),false);
    storeItem.on('load',function(){
        var arrayColumns=[];        
        for (i = 0 ; i <storeItem.getCount() ;i++)
          {           
              arrayColumns[i] =[storeItem.getAt(i).get('kodeitem'),storeItem.getAt(i).get('namaitem')];
              
          }          
        arrayCmbItem.loadData( arrayColumns );   
    });
    storeItem.load();
   
    var tglAwal =new Ext.form.DateField({
                 itemId :'TglAwal',
                 name: 'TglAwal',
                 fieldLabel: 'Tanggal Awal',
                 width: 120
                 });
    var tglAkhir =new Ext.form.DateField({
                 itemId :'TglAkhir',
                 name: 'TglAkhir',
                 fieldLabel: 'Tanggal Awal',
                 width: 120
                 });             
    tglAwal.on('change',function(field, newValue, oldValue){
        awal=(newValue.getMonth()+1)+"/"+newValue.getDate()+"/"+newValue.getFullYear();        
    });   
    tglAkhir.on('change',function(field, newValue, oldValue){
        akhir=(newValue.getMonth()+1)+"/"+newValue.getDate()+"/"+newValue.getFullYear();        
    });  
    
    var btnPriview = new Ext.Button ({
              //xtype: 'button',
              text: 'Priview',
              itemId :'btn_priview',
              disabled : false
              });
    var rb_ItemSemua=new Ext.form.Radio({
        //xtype     : 'radio',
              name      : 'rdItemSemua',
              margin: '0 5 0 0',
              checked : true,
              width: 20,
              allowBlank: false
    });
    rb_ItemSemua.on('check',function(a,b){
        if(b==true)
            {cmb_item.disable();
             c=1;}
        else
           {cmb_item.enable();c=0;}
    });
    var rb_ItemCustom=new Ext.form.Radio({
        //xtype     : 'radio',
              name      : 'rdItemSemua',
              margin: '0 5 0 0',
              width: 20,
              allowBlank: false
    });
    rb_ItemCustom.on('check',function(a,b){
        if(b==true)
            {cmb_item.enable();c=0;}
        else
            {cmb_item.disable();c=1;}
    });
    var rb_PemohonSemua=new Ext.form.Radio({
        //xtype     : 'radio',
              name      : 'rdPemohonSemua',
              margin: '0 5 0 0',
              checked : true,
              width: 20,
              allowBlank: false
    });
    rb_PemohonSemua.on('check',function(a,b){
        if(b==true)
            {cmb_pemohon.disable();
            d=1;}
        else
           {cmb_pemohon.enable();d=0;}
    });
    var rb_PemohonCustom=new Ext.form.Radio({
        //xtype     : 'radio',
              name      : 'rdPemohonSemua',
              margin: '0 5 0 0',
              width: 20,
              allowBlank: false
    });
    rb_PemohonCustom.on('check',function(a,b){
        if(b==true)
            {cmb_pemohon.enable();d=0;}
        else
           {cmb_pemohon.disable();d=1;}
    });
    var form_lrekapreservasi = new Ext.FormPanel({
         url: 'maincontroller',
         region:'center',
         autoScroll: true,
         //tbar:[],
         frame: true,
         bodyStyle:'padding:5px 5px 0',
         //labelAlign: 'top',
         items: [{
               // xtype: 'fieldcontainer',
                fieldLabel: 'Date Range',
                combineErrors: true,
                msgTarget : 'side',
                layout: 'hbox',
                defaults: {
                    flex: 1,
                    hideLabel: false
                },
                items: [
                    tglAwal,
                    {
                        xtype     : 'label',
                        name      : 'lblItemSemua',
                        text      : '',
                        width: 10,
                        margin: '0 5 0 0',
                        allowBlank: false
                    },tglAkhir
                ]
            },{fieldLabel: 'Item',
                combineErrors: true,
                msgTarget : 'side',
                layout: 'hbox',
                defaults: {
                    flex: 1,
                    hideLabel: false
                },
                items: [rb_ItemSemua,
                    {
                        xtype     : 'label',
                        name      : 'lblItemSemua',
                        text      : 'Semua',
                        margin: '0 5 0 0',
                        width: 60,
                        allowBlank: false
                    },rb_ItemCustom,cmb_item
                ]},{fieldLabel: 'Pemohon',
                combineErrors: true,
                msgTarget : 'side',
                layout: 'hbox',
                defaults: {
                    flex: 1,
                    hideLabel: false
                },
                items: [rb_PemohonSemua,
                    {
                        xtype     : 'label',
                        name      : '',
                        text      : 'Semua',
                        margin: '0 5 0 0',
                        width: 60,
                        allowBlank: false
                    },rb_PemohonCustom,cmb_pemohon
                ]},btnPriview,{
            itemId :'formid',
            xtype: 'hidden',
            name: 'formid',
            value:pformid
         },{
            itemId :'formmode',
            xtype: 'hidden',
            name: 'formmode',
            value:formMode
         }]
      });
    btnPriview.on('click', function() {
        if (c==1)
            {namaitem='%%';}
        else
            {namaitem=cmb_item.getValue();}
        if (d==1)
            {namabp='%%';}
        else{namabp=cmb_pemohon.getValue();}
              var a = new  RequestReport ('maincontroller','RekapRealisasiSewaRuang');
              a.params.awal = awal;      
              a.params.akhir = akhir;
              a.params.namabp = namabp;
              a.params.item = namaitem;
              window.open(a.url+'?'+Ext.urlEncode(a.params));
              
       });
      return getTwoPanelVerticalBorderLayoout(form_lrekapreservasi,null,ptitle,pformid);
}
