/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function getForm_trealisasi(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint)
{
    
    var SDItemRealisasistore;
    var DTglRealisasiStore;   
    var DCostCenterStore;
    var status=0;
    var mode=0;
    var awal;
    var akhir;var Sql;var kodejabatan;var userid;
    var rowItem;var kodecost;var StorePemohonPengajuan;
    var approvStore = new Ext.data.ArrayStore({
               fields: ['id', 'approve_name'],
               data : [['1','approved'],['0','unapproved']]
            });
    function rendererApprove_name(val) {
             return approvStore.queryBy(function(rec){
                return rec.data.id == val;
              }).itemAt(0).get('approve_name');
            }

    var hidden_kodebp= new Ext.form.Hidden({
            itemId :'kodebp',
            name: 'kodebp',
            value:''
         });
 
   var hidden_versi= new Ext.form.Hidden({
            itemId :'versi',
            name: 'versi',
            value:''
         });
   var hidden_biayaTotal= new Ext.form.Hidden({
            itemId :'biayatotal',
            name: 'biayatotal',
            value:''
         });
         /*
   var txt_kodeRealisasi = new Ext.form.TextField({
        itemId :'koderealisasi',
                     fieldLabel: 'Kode',
                     name: 'kodeRealisasi',
                     value:'',
                     disabled:false,
                     readOnly:true,
                     anchor:'95%'
    });*/
     var txtBtn_kodeRealisasi = new Ext.form.TriggerField({
       itemId :'koderealisasi',
       fieldLabel: 'Kode Realisasi',
       name: 'koderealisasi',
       value:'',
       disabled:false,
       allowBlank: true,
       triggerClass: 'x-form-search-trigger',
       anchor:'95%',
       editable:false
   });
    var txtBtn_kodePengajuan = new Ext.form.TriggerField({
       itemId :'kodepengajuan',
       fieldLabel: 'Kode Pengajuan',
       name: 'kodepengajuan',
       value:'',
       disabled:true,
       allowBlank: false,
       triggerClass: 'x-form-search-trigger',
       anchor:'95%',
       editable:false
   });
  
  var txtBtn_ApprovingCostCenter = new Ext.form.TriggerField({
           itemId :'approval',
           name: 'approval',
           value:'',
           triggerClass: 'x-form-search-trigger',
           editable:false
       });
       
   var txt_jenisrealisasi = new Ext.form.TextField({
        itemId :'jenisrealisasi',
        fieldLabel: 'Jenis',
        name: 'jenisrealisasi',
        value:'',
        disabled:false,
        readOnly:true,
        anchor:'95%'
    });
     var txt_namaPemohon = new Ext.form.TextField({
        itemId :'namapemohon',
        fieldLabel: 'Nama Pemohon',
        name: 'namapemohon',
        value:'',
        disabled:true,
        anchor:'95%'
    });
    var txt_nik = new Ext.form.TextField({
        itemId :'nikpemohon',
        fieldLabel: 'NIK',
        name: 'nikpemohon',
        value:'',
        disabled:true,
        anchor:'95%'
    });
    var txt_Perusahaan = new Ext.form.TextField({
        itemId :'namaperusahaan',
        fieldLabel: 'Perusahaan',
        name: 'namaorganisasi',
        value:'',
        disabled:true,
        anchor:'95%'
    });
    var txt_organisasi = new Ext.form.TextField({
        itemId :'namaorganisasi',
        fieldLabel: 'Organisasi',
        name: 'namaorganisasi',
        value:'',
        disabled:true,
        anchor:'95%'
    });
    var txt_namajabatan = new Ext.form.TextField({
        itemId :'namajabatan',
        fieldLabel: 'Nama Jabatan',
        name: 'namajabatan',
        value:'',
        disabled:true,
        anchor:'95%'
    });
    var txt_tglPengajuan = new Ext.form.TextField({
        itemId :'tglpengajuan',
        fieldLabel: 'Tgl Pengajuan',
        name: 'tglpengajuan',
        value:'',
        disabled:true,
        anchor:'95%'
    });
    
    var txt_tglRealisasi = new Ext.form.DateField({
        itemId :'tglrealisasi',
        fieldLabel: 'Tgl Realisasi',
        name: 'tglrealisasi',
        editable:false,
        allowBlank: false,
        value:getCurrentDate(),
        anchor:'95%'
    });
    var txtBtn_costCenter = new Ext.form.TriggerField({
       itemId :'kodecostcenter',
       name: 'kodecostcenter',
       value:'',
       triggerClass: 'x-form-search-trigger'
   });
   var txtBtn_userid = new Ext.form.TriggerField({
       itemId :'userid',
       name: 'userid',
       value:'',
       triggerClass: 'x-form-search-trigger'
   });

   var txt_number_grid1 = new Ext.form.NumberField({
        itemId :'numbergrid',
        name: 'numbergrid',
        value:0
    });
    var txt_number_grid2 = new Ext.form.NumberField({
        itemId :'numbergrid',
        name: 'numbergrid',
        value:0
    });
    var txt_number_grid3 = new Ext.form.NumberField({
        itemId :'numbergrid',
        name: 'numbergrid',
        value:0
    });
    var txt_number_grid4 = new Ext.form.NumberField({
        itemId :'numbergrid',
        name: 'numbergrid',
        value:0
    });
    var txt_number_grid5 = new Ext.form.NumberField({
        itemId :'numbergrid',
        name: 'numbergrid',
        value:0
    });
    var hidden_IE= new Ext.form.Hidden({
            itemId :'IE',
            name: 'IE',
            value:'I'
         }); 
    var rb_jenistarif=new Ext.form.RadioGroup({            
            fieldLabel: 'Jenis tarif',
            items: [
                {boxLabel: 'Internal', name: 'JenisTarif', inputValue: 'I', checked: true},
                {boxLabel: 'Eksternal', name: 'JenisTarif', inputValue: 'E'},              
            ],
            listeners:{
                change: function(rd,checked){
                 hidden_IE.setValue(rb_jenistarif.getValue().getGroupValue());
                }
              }
                
        });
    //var storePermission=get_m_permissionStore('READ',SqlCustom_m_permission.get_selectFilterByKodeFormAndUserId('01-04-03', useridsession));    
    var colModel_gridCostCenter = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [
                      {header: "Cost Center", dataIndex: arrayField_d_costcenterrealisasi[1],
                         editor: txtBtn_costCenter},
                      {header: "User Approval", dataIndex: arrayField_d_costcenterrealisasi[3],
                         editor: txtBtn_userid},
                      {header: "Status", dataIndex: arrayField_d_costcenterrealisasi[7],
                         editor: txtBtn_ApprovingCostCenter,renderer:rendererApprove_name},
                      {header: "Presentase", dataIndex: arrayField_d_costcenterrealisasi[8], xtype: 'numbercolumn',
                        editor:txt_number_grid1,align:'right'},
                      {header: "Nominal", dataIndex: arrayField_d_costcenterrealisasi[9], xtype: 'numbercolumn',
                        editor:txt_number_grid2,align:'right'}
                    
                    ]
                });
         
                    
   var gridCostCenter = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 160,
                anchor:'100%',
                itemId:'gridCostCenter',
                title:'Cost Center',
                store: get_d_costCenterRealisasiStore(formMode),
                selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
                colModel: colModel_gridCostCenter,
                //disabled:true,
                listeners: {
                    afteredit: function(e){
                       if (e.field == 'presentase'){
                             var total = 0;
                             gridSDItemRealisasi.getStore().clearFilter();
                             for(i=0;i<gridSDItemRealisasi.getStore().getCount();i++){
                                 total = total + parseInt(gridSDItemRealisasi.getStore().getAt(i).get('biaya'));
                             }
                             e.record.set('nominal',(parseFloat(e.value)*total)/100);
                             e.record.set('approval','0');
                             if(gridTglAwalTglAkhir.getSelectionModel().hasSelection()){
                                var sm = gridTglAwalTglAkhir.getSelectionModel();
                                var row = sm.getSelected();                                  
                                gridSDItemRealisasi.getStore().filter('kodetp', row.get('kodetp'));
                             }else if(gridTglAwalTglAkhir.getStore().getCount()>0){
                                 gridTglAwalTglAkhir.getSelectionModel().selectFirstRow();
                             }                                               
                        }else if(e.field == 'nominal'){
                             var total = 0;
                             gridSDItemRealisasi.getStore().clearFilter();
                             for(i=0;i<gridSDItemRealisasi.getStore().getCount();i++){
                                 total = total + parseInt(gridSDItemRealisasi.getStore().getAt(i).get('biaya'));
                             }
                             e.record.set('presentase',(parseInt(e.value)/total)*100);
                             e.record.set('approval','0');
                             if(gridTglAwalTglAkhir.getSelectionModel().hasSelection()){
                                var sm = gridTglAwalTglAkhir.getSelectionModel();
                                var row = sm.getSelected();                                  
                                gridSDItemRealisasi.getStore().filter('kodetp', row.get('kodetp'));
                             }else if(gridTglAwalTglAkhir.getStore().getCount()>0){
                                 gridTglAwalTglAkhir.getSelectionModel().selectFirstRow();
                             }   
                        }
                    }
                },
                tbar: [{
                  text: 'Add row',
                  handler: function(){
                     var sisapersen = 100;
                     for(i=0;i<gridCostCenter.getStore().getCount();i++){
                         sisapersen = sisapersen - gridCostCenter.getStore().getAt(i).get('presentase');
                     }
                     var total = 0;
                     gridSDItemRealisasi.getStore().clearFilter();
                     for(i=0;i<gridSDItemRealisasi.getStore().getCount();i++){
                         total = total + parseInt(gridSDItemRealisasi.getStore().getAt(i).get('biaya'));
                     }                     
                     if(gridTglAwalTglAkhir.getSelectionModel().hasSelection()){
                        var sm = gridTglAwalTglAkhir.getSelectionModel();
                        var row = sm.getSelected();                                  
                        gridSDItemRealisasi.getStore().filter('kodetr', row.get('kodetr'));
                     }else if(gridTglAwalTglAkhir.getStore().getCount()>0){
                         gridTglAwalTglAkhir.getSelectionModel().selectFirstRow();
                     }
                     
                     gridCostCenter.getStore().insert( gridCostCenter.getStore().getCount(),
                        new d_costCenterRealisasiRecord({
                            koderealisasi:txtBtn_kodeRealisasi.getValue(),
                            kodecostcenter:kodecost,
                            userid:'',
                            approval:'0',
                            presentase:sisapersen,
                            nominal:parseInt((sisapersen*total)/100)
                         })
                      );
                    }
                  },{
                  text: 'Delete row',
                  handler: function(btn){
                    var sm = gridCostCenter.getSelectionModel();
                    sel = sm.getSelected();
                    gridCostCenter.getStore().remove(sel);
                  }
                }]
   });
   for(i=0;i<colModel_gridCostCenter.getColumnCount();i++){
       colModel_gridCostCenter.setEditable( i, false);
   }
   gridCostCenter.getTopToolbar().disable();

   txtBtn_costCenter.onTriggerClick = function(e){
            var sm = gridCostCenter.getSelectionModel();
            var record_gridCostCenter = sm.getSelected();
            if (status==1)
               {return;}
           else if (status==0)
                {listObjectInWIndowBrowse = browseCostCenter(null);status=1;}
           // browseCostCenter(/*null,null,null,*/record_vm_hris_costcenterRecord);
           // listObjectInWIndowBrowse = browseCostCenter(null);
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridCostCenter.set('kodecostcenter',row.get('kodecostcenter'));
                record_gridCostCenter.set('userid','');
                record_gridCostCenter.set('approval','0');
                listObjectInWIndowBrowse.extwindow.close();
                status=0;
            });
            listObjectInWIndowBrowse.extwindow.on('close', function(p){
                status=0;
            });
       };
       txtBtn_userid.onTriggerClick = function(e){
            var sm = gridCostCenter.getSelectionModel();
            var record_gridCostCenter = sm.getSelected();
            if (status==1)
               {return;}
            else if (status==0)
                {listObjectInWIndowBrowse = browseBPartneLengkap(customSelect_vm_hris_bpartnerLengkap_xmlstore (
                 (SqlCustom_vm_hris_bpartnerLengkap.selectFilterByCostCenter).replace('filterCostCenter', record_gridCostCenter.get('kodecostcenter'))));
                 status=1;}
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridCostCenter.set('userid',row.get('userid'));
                record_gridCostCenter.set('approval','0');
                listObjectInWIndowBrowse.extwindow.close();status=0;
            });
            listObjectInWIndowBrowse.extwindow.on('close', function(p){
                status=0;
            });
       };

   var colModel_gridTglAwalTglAkhir = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [
                      {header: "Tanggal",width:95, dataIndex: arrayField_d_tglrealisasiStore[2],
                         editor: new Ext.form.DateField({
                            itemId :'TglAwal',
                            name: 'TglAwal'
                         }),renderer: Ext.util.Format.dateRenderer( 'd M Y')},
                     {header: "Jenis Hari", dataIndex: 'namatarif'}
                      /*{header: "Tgl Akhir", dataIndex: arrayField_d_tglrealisasiStore[3],
                         editor:new Ext.form.DateField({
                            itemId :'TglAkhir',
                            name: 'TglAkhir'
                         }),renderer: Ext.util.Format.dateRenderer( 'd M Y')}*/
                    ]
                });
   var gridTglAwalTglAkhir = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 160,
                width:210,
                itemId:'gridTglAwalTglAkhir',
                store: get_d_tglrealisasiStore(formMode),
                selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
                colModel: colModel_gridTglAwalTglAkhir,
                tbar: [{
                  text: 'Add row',
                  handler: function(){
                      var a=new d_tglRealisasiRecord({
                            kodetr:parseToDateTimeOnly(new Date())+'-'+Math.floor(Math.random()*11),
                            koderalisasi:txtBtn_kodeRealisasi.getValue(),
                            tglawal:getCurrentDate(),
                            tglakhir:getCurrentDate(),
                            namatarif:''
                         });
                     gridTglAwalTglAkhir.getStore().insert( gridTglAwalTglAkhir.getStore().getCount(),a
                        );
                     var store = customSelect_vm_harilibur_xmlstore(SqlCustom_vm_harilibur.selectFilterByTanggal( parseToDateOnly(getCurrentDate())));
                     store.on('load', function() {
                          if(store.getCount()>0){
                              a.set('namatarif','libur');
                          }else{
                              a.set('namatarif','kerja');
                          }
                      });
                     store.load();
                     gridTglAwalTglAkhir.getSelectionModel().selectFirstRow();
                    }
                  },{
                  text: 'Delete row',
                  handler: function(btn){
                    var sm = gridTglAwalTglAkhir.getSelectionModel();
                    sel = sm.getSelected();
                    gridTglAwalTglAkhir.getStore().remove(sel);
                  }
                },{
                  text: 'Copy to',
                  handler: function(btn){
                    var sm = gridTglAwalTglAkhir.getSelectionModel();
                    var record = sm.getSelected();
                    showCopySDItemToOtherDateWindow(gridTglAwalTglAkhir.getStore(),gridSDItemRealisasi.getStore(),record.get('tglawal'),record,false);
                  }
               }],
            listeners: {
                afteredit: function(e){
                  if (e.field == 'tglawal'){
                      e.record.set('namatarif','');
                      var store = customSelect_vm_harilibur_xmlstore(SqlCustom_vm_harilibur.selectFilterByTanggal( parseToDateOnly(e.value)));
                      store.on('load', function() {
                          if(store.getCount()>0){
                              e.record.set('namatarif','libur');
                          }else{
                              e.record.set('namatarif','kerja');
                          }
                      });
                      store.load();
                      e.record.set('tglakhir',e.value);                        
                  }
                }
              }
   });
   for(i=0;i<colModel_gridTglAwalTglAkhir.getColumnCount();i++){
       colModel_gridTglAwalTglAkhir.setEditable( i, false);
   }
   gridTglAwalTglAkhir.getTopToolbar().disable();
   
   gridTglAwalTglAkhir.getSelectionModel().on(
        'rowselect',function(sm, rowIndex, record){
            gridSDItemRealisasi.getStore().filter('kodetr', record.get('kodetr'));
        }
      );

   var txtBtn_item = new Ext.form.TriggerField({
       itemId :'namaitem',
       name: 'namaitem',
       value:'',
       triggerClass: 'x-form-search-trigger'
   });
    
   var txtBtn_datetime_jamawal = new Ext.form.TriggerField({
       itemId :'jamawal',       
       name: 'jamawal',
       value:'',                     
       triggerClass: 'x-form-search-trigger'   ,
       editable:false
   });
   var txt_number_grid6 = new Ext.form.NumberField({
        itemId :'numbergrid',
        name: 'numbergrid',
        value:0
    });
     var txt_number_grid7 = new Ext.form.NumberField({
        itemId :'numbergrid',
        name: 'numbergrid',
        value:0
    });
   var colModel_gridSDItemRealisasi = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [
                      {header: "Ruang dan Perlengkapan", dataIndex: 'namaitem',width:180,
                         editor: txtBtn_item},
                      {header: "Jumlah", dataIndex: 'jumlahakhir',align:'right',width:50,
                         editor: txt_number_grid3, xtype: 'numbercolumn',format: '0'},
                     {header: "Jam Awal", dataIndex: 'jamawal',width:70,
                         editor: txtBtn_datetime_jamawal,type: 'date', dateFormat: 'H:i'},
                      {header: "Jam Akhir", dataIndex: 'jamakhir',width:70,
                         type: 'date', dateFormat: 'H:i'},
                      {header: "Durasi", dataIndex: 'durasi',align:'right',width:50,
                         editor: txt_number_grid4,renderer:rendererApprove_name,xtype: 'numbercolumn',format: '0'},
                      {header: "Biaya Perjam", dataIndex: 'biayaperjam', xtype: 'numbercolumn',
                         align:'right',editor:txt_number_grid7},
                      {header: "Biaya", dataIndex: 'biaya', xtype: 'numbercolumn',
                         editor:txt_number_grid5,align:'right'},
                      {header: "Jumlah Peserta", dataIndex: 'jumlahpeserta', xtype: 'numbercolumn',
                         editor:txt_number_grid6,format: '0',align:'right'},
                      {header: "Status Sebelum", dataIndex: 'statussebelum',
                         editor: new Ext.form.TextField({value:''})}, 
                      {header: "Status Sesudah", dataIndex: 'statussesudah',
                         editor: new Ext.form.TextField({value:''})},
                      {header: "Keterangan", dataIndex: 'keterangan',
                         editor: new Ext.form.TextField({value:''})}
                    ]
                });
   var gridSDItemRealisasi = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 160,
                //anchor:'100%',
                columnWidth:1,
                itemId:'gridSDItemRealisasi',
                store: get_sd_itemrealisasiStore(formMode),
                selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
                colModel: colModel_gridSDItemRealisasi,
                tbar: [{
                  text: 'Add row',
                  handler: function(){
                     gridSDItemRealisasi.getStore().insert( gridSDItemRealisasi.getStore().getCount(),
                        new sd_itemrealisasiRecord({
                              kodetr : (function(){
                                  var sm = gridTglAwalTglAkhir.getSelectionModel();
                                  var row = sm.getSelected();
                                  return row.get('kodetr');
                              })(),
                              kodeitem  : '',
                              namaitem: '',
                              jumlahakhir : 1,
                              durasi  : 0,
                              biayaperjam    : 0,
                              biaya   :0,
                              statussebelum :'',
                              statussesudah :'',
                              jumlahpeserta:0,
                              keterangan  : ''
                         })
                      );
                      //gridSDItemPengajuan.startEditing(gridSDItemPengajuan.getStore().getCount()-1,0);
                    }
                  },{
                  text: 'Delete row',
                  handler: function(btn){
                    var sm = gridSDItemRealisasi.getSelectionModel();
                    record = sm.getSelected();
                    
                    gridSDItemRealisasi.getStore().remove(record);
                  }
                }],
            listeners: {
                afteredit: function(e){
                  if (e.field == 'jumlahakhir'){
                    if (e.field == 'jumlahakhir'){
                        if ((e.record.get('jamawal') != null) && (e.record.get('jamakhir') != null)
                            && (e.value != null)  && (e.record.get('kodeitem') != null)
                            && (hidden_kodebp.getValue()!='') && (e.record.get('biayaperjam')!=null) && 
                            (e.record.get('durasi')!=null)){
                            /*var store = get_sd_itempengajuanStore("READ",
                            SqlCustom_sd_itempengajuan.get_selectBiayaAndDurasi(
                                record_gridSDItemPengajuan.get('kodeitem'), hidden_kodebp.getValue(), 
                                record_gridSDItemPengajuan.get('jamawal'), record_gridSDItemPengajuan.get('jamakhir'), record_gridSDItemPengajuan.get('jumlahawal')),
                            false);
                        store.on('load', function() {
                            record_gridSDItemPengajuan.set('durasi', store.getAt(0).get('durasi'));
                            record_gridSDItemPengajuan.set('biaya', store.getAt(0).get('biaya'));
                        });
                        store.load();        */
                            e.record.set('biaya',e.record.get('biayaperjam')*e.record.get('durasi')*e.value);
                        }else{
                            Ext.Msg.show({
                                title: 'Error!',
                                msg: 'kode bp, jam awal, jam akhir, kode item, biaya perjam, durasi dan jumlah tidak boleh kosong.',
                                icon: Ext.Msg.ERROR,
                                buttons: Ext.MessageBox.OK
                            });
                            e.record.set('jumlahakhir',e.originalValue);
                        }                  
                    }else if(e.field == 'biayaperjam'){
                        var store = get_m_itemStore(SqlCustom_m_item.get_selectFilterByKodeitem(e.record.get('kodeitem')));
                        store.on('load',function(){
                            if(store.getCount()<1){
                                Ext.Msg.show({
                                    title: 'Error!',
                                    msg: 'Item ini sudah dihapus dari master item',
                                    icon: Ext.Msg.ERROR,
                                    buttons: Ext.MessageBox.OK
                                });
                                e.record.set('biayaperjam',e.originalValue);
                            }else{
                                if(store.getAt(0).get('pricechangeable')==0){
                                    Ext.Msg.show({
                                        title: 'Error!',
                                        msg: 'Biaya perjam tidak dapat dirubah',
                                        icon: Ext.Msg.ERROR,
                                        buttons: Ext.MessageBox.OK
                                    });
                                    e.record.set('biayaperjam',e.originalValue);
                                }else{
                                    if ((e.record.get('jamawal') != null) && (e.record.get('jamakhir') != null)
                                        && (e.record.get('jumlahakhir') != null)  && (e.record.get('kodeitem') != null)
                                        && (hidden_kodebp.getValue()!='') && (e.value!=null) && 
                                        (e.record.get('durasi')!=null)){
                                    
                                        e.record.set('biaya',e.record.get('jumlahakhir')*e.record.get('durasi')*e.value);
                                    }else{
                                        Ext.Msg.show({
                                            title: 'Error!',
                                            msg: 'kode bp, jam awal, jam akhir, kode item, biaya perjam, durasi dan jumlah tidak boleh kosong.',
                                            icon: Ext.Msg.ERROR,
                                            buttons: Ext.MessageBox.OK
                                        });
                                        e.record.set('biayaperjam',e.originalValue); 
                                    }                                  
                                }
                            }
                        });
                    }else if(e.field == 'biaya'){
                        var store = get_m_itemStore(SqlCustom_m_item.get_selectFilterByKodeitem(e.record.get('kodeitem')));
                        store.on('load',function(){
                            if(store.getCount()<1){
                                Ext.Msg.show({
                                    title: 'Error!',
                                    msg: 'Item ini sudah dihapus dari master item',
                                    icon: Ext.Msg.ERROR,
                                    buttons: Ext.MessageBox.OK
                                });
                                e.record.set('biaya',e.originalValue);
                            }else{
                                if(store.getAt(0).get('pricechangeable')==0){
                                    Ext.Msg.show({
                                        title: 'Error!',
                                        msg: 'Biaya tidak dapat dirubah',
                                        icon: Ext.Msg.ERROR,
                                        buttons: Ext.MessageBox.OK
                                    });
                                    e.record.set('biaya',e.originalValue);
                                }else{
                                    if ((e.record.get('jamawal') != null) && (e.record.get('jamakhir') != null)
                                        && (e.record.get('jumlahakhir') != null)  && (e.record.get('kodeitem') != null)
                                        && (hidden_kodebp.getValue()!='') && (e.value!=null) && 
                                        (e.record.get('durasi')!=null)){
                                    
                                        e.record.set('biayaperjam',e.value/(e.record.get('jumlahakhir')*e.record.get('durasi')));
                                    }else{
                                        Ext.Msg.show({
                                            title: 'Error!',
                                            msg: 'kode bp, jam awal, jam akhir, kode item, biaya, durasi dan jumlah tidak boleh kosong.',
                                            icon: Ext.Msg.ERROR,
                                            buttons: Ext.MessageBox.OK
                                        });
                                        e.record.set('biaya',e.originalValue); 
                                    }                                  
                                }
                            }
                        });
                    }
                  }
                }
              }
   });
   for(i=0;i<colModel_gridSDItemRealisasi.getColumnCount();i++){
       colModel_gridSDItemRealisasi.setEditable( i, false);
   }
   gridSDItemRealisasi.getTopToolbar().disable();
   
   function getData(rowIndex){
                    var sqlGridItem;var sqlGridCostCenter;var sqlGridTglAwalAkhir;
                   
                    
                    gridCostCenter.getStore().removeAll();
                    gridTglAwalTglAkhir.getStore().removeAll();
                    gridSDItemRealisasi.getStore().removeAll();
                    var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                    txtBtn_kodeRealisasi.setValue(row.get('koderealisasi'));
                    txtBtn_kodePengajuan.setValue(row.get('kodepengajuan'));
                    hidden_kodebp.setValue(row.get('kodebp'));
                    txt_jenisrealisasi.setValue(row.get('jenisrealisasi'));
                    txt_namaPemohon.setValue(row.get('namabp'));
                    txt_nik.setValue(row.get('nik'));
                    txt_Perusahaan.setValue(row.get('namaperusahaan'));
                    hidden_versi.setValue(row.get('versi'));
                    txt_organisasi.setValue(row.get('namaorg'));
                    txt_namajabatan.setValue(row.get('namajabatan'));
                    
                    
                    userid=row.get('userid');
                    kodecost = row.get('kodecostcenter');
                    StorePemohonPengajuan=customSelect_vm_hris_bpartnerLengkap_xmlstore(SqlCustom_vm_hris_bpartnerLengkap.get_selectFilterByKodeBp(row.get('kodebp')));
                    if (row.get('IE')=='I'){
                        rb_jenistarif.setValue('I');
                    }
                    else if(row.get('IE')=='E'){
                        rb_jenistarif.setValue('E');
                    }
                    colModel_gridCostCenter.setEditable( 2, true);
                    if (mode==0)//realisasi
                        {
                            txt_tglPengajuan.setValue(row.get('tglpengajuan'));
                            txt_tglRealisasi.setValue(row.get('tglrealisasi'));
                            sqlGridCostCenter=SqlCustom_d_costcenterrealisasi.get_selectFilterByKoderealisasi(row.get('koderealisasi'));
                            sqlGridItem=SqlCustom_sd_itemrealisasi.get_selectFilterByKodeTr(row.get('koderealisasi'));
                            sqlGridTglAwalAkhir=SqlCustom_d_tglrealisasi.get_selectFilterByKoderealisasi(row.get('koderealisasi'));
                            DCostCenterStore=get_d_costCenterRealisasiStore(form_trealisasi.getComponent('formmode').getValue(),sqlGridCostCenter);
                            DTglRealisasiStore=get_d_tglrealisasiStore(form_trealisasi.getComponent('formmode').getValue(),sqlGridTglAwalAkhir);
                            SDItemRealisasistore=get_sd_itemrealisasiStore(form_trealisasi.getComponent('formmode').getValue(),sqlGridItem,false);
                            gridAcc = form_trealisasi.getComponent('gridAcc');
                            generate_accTable(formMode,pformid,ptitle,txtBtn_kodeRealisasi.getValue(),gridAcc,form_trealisasi,dataSubmit);                    
                            
                            DTglRealisasiStore.on('load',function(){
                                    for(i=0;i<DTglRealisasiStore.getTotalCount();i++){
                                        gridTglAwalTglAkhir.getStore().insert(gridTglAwalTglAkhir.getStore().getCount(),
                                    new d_tglRealisasiRecord({
                                        kodetr:DTglRealisasiStore.getAt(i).get('kodetr'),
                                        koderealisasi: txtBtn_kodeRealisasi.getValue(),
                                        tglawal: DTglRealisasiStore.getAt(i).get('tglawal'),
                                        tglakhir:DTglRealisasiStore.getAt(i).get('tglakhir'),
                                        namatarif:DTglRealisasiStore.getAt(i).get('namatarif')
                                         }));
                                        }
                                gridTglAwalTglAkhir.getSelectionModel().selectFirstRow();
                                SDItemRealisasistore.load();
                              });
                              
                           SDItemRealisasistore.on('load',function(){
                               
                               for(j=0;j<DTglRealisasiStore.getTotalCount();j++){
                                    for(i=0;i<SDItemRealisasistore.getTotalCount();i++){
                               
                                        if (DTglRealisasiStore.getAt(j).get('kodetr') == SDItemRealisasistore.getAt(i).get('kodetr')){
                                            gridSDItemRealisasi.getStore().insert( gridSDItemRealisasi.getStore().getCount(),
                                            new sd_itemrealisasiRecord({
                                                  kodetr : SDItemRealisasistore.getAt(i).get('kodetr'),
                                                  kodeitem  : SDItemRealisasistore.getAt(i).get('kodeitem'),
                                                  namaitem: SDItemRealisasistore.getAt(i).get('namaitem'),
                                                  jumlahawal: SDItemRealisasistore.getAt(i).get('jumlahawal'),
                                                  jumlahakhir : SDItemRealisasistore.getAt(i).get('jumlahakhir'),
                                                  jamawal  : SDItemRealisasistore.getAt(i).get('jamawal'),
                                                  jamakhir : SDItemRealisasistore.getAt(i).get('jamakhir'),
                                                  durasi  :SDItemRealisasistore.getAt(i).get('durasi'),
                                                  biayaperjam    : SDItemRealisasistore.getAt(i).get('biayaperjam'),
                                                  biaya   :(SDItemRealisasistore.getAt(i).get('biayaperjam')*SDItemRealisasistore.getAt(i).get('durasi')*SDItemRealisasistore.getAt(i).get('jumlahakhir')),
                                                  jumlahpeserta  :SDItemRealisasistore.getAt(i).get('jumlahpeserta'),
                                                  statussebelum :SDItemRealisasistore.getAt(i).get('statussebelum'),
                                                  statussesudah :SDItemRealisasistore.getAt(i).get('statussesudah'),
                                                  keterangan  : SDItemRealisasistore.getAt(i).get('keterangan')                                
                                                }));
                                        }
                                       
                                    }
                               }
                           
                            var sm = gridTglAwalTglAkhir.getSelectionModel();
                            var a = sm.getSelected();
                            gridSDItemRealisasi.getStore().filter('kodetr', a.get('kodetr'));
                        });
                        }
                   else if (mode==1)//pengajuan
                        {
                            txt_tglPengajuan.setValue(row.get('tanggal'));
                            var now = new Date();
                            var mon=now.getMonth();
                            var dateString = (mon+1) + "/" + now.getDate() + "/" + now.getFullYear();
                            txt_tglRealisasi.setValue(dateString);    
                            sqlGridCostCenter=SqlCustom_d_costcenterpengajuan.get_selectFilterByKodepengajuan(row.get('kodepengajuan'));
                            sqlGridItem=SqlCustom_sd_itempengajuan.get_selectFilterByKodeTp(row.get('kodepengajuan'));
                            sqlGridTglAwalAkhir=SqlCustom_d_tglpengajuan.get_selectFilterByKodepengajuan(row.get('kodepengajuan'));
                            DCostCenterStore=get_d_costCenterPengajuanStore(form_trealisasi.getComponent('formmode').getValue(),sqlGridCostCenter);
                            DTglRealisasiStore=get_d_tglpengajuanStore(form_trealisasi.getComponent('formmode').getValue(),sqlGridTglAwalAkhir);
                            SDItemRealisasistore=get_sd_itempengajuanStore(form_trealisasi.getComponent('formmode').getValue(),sqlGridItem,false);
                            
                            DTglRealisasiStore.on('load',function(){
                            for(i=0;i<DTglRealisasiStore.getTotalCount();i++){
                                gridTglAwalTglAkhir.getStore().insert(gridTglAwalTglAkhir.getStore().getCount(),
                            new d_tglRealisasiRecord({
                                kodetr:parseToDateTimeOnly(new Date())+'-'+gridTglAwalTglAkhir.getStore().getCount(),
                                koderealisasi: txtBtn_kodeRealisasi.getValue(),
                                tglawal: DTglRealisasiStore.getAt(i).get('tglawal'),
                                tglakhir:DTglRealisasiStore.getAt(i).get('tglakhir'),
                                namatarif:DTglRealisasiStore.getAt(i).get('namatarif')
                                    }));
                                }
                                gridTglAwalTglAkhir.getSelectionModel().selectFirstRow();
                                SDItemRealisasistore.load();
                             }); 
                             
                            SDItemRealisasistore.on('load',function(){
                                for(j=0;j<DTglRealisasiStore.getTotalCount();j++){
                                    for(i=0;i<SDItemRealisasistore.getTotalCount();i++){
                                        if (DTglRealisasiStore.getAt(j).get('kodetp') == SDItemRealisasistore.getAt(i).get('kodetp')){
                                            gridSDItemRealisasi.getStore().insert( gridSDItemRealisasi.getStore().getCount(),
                                            new sd_itemrealisasiRecord({
                                              kodetr : gridTglAwalTglAkhir.getStore().getAt(j).get('kodetr'),
                                              kodeitem  : SDItemRealisasistore.getAt(i).get('kodeitem'),
                                              namaitem: SDItemRealisasistore.getAt(i).get('namaitem'),
                                              jumlahawal: SDItemRealisasistore.getAt(i).get('jumlahawal'),
                                              jumlahakhir : SDItemRealisasistore.getAt(i).get('jumlahakhir'),
                                              jamawal  : SDItemRealisasistore.getAt(i).get('jamawal'),
                                              jamakhir : SDItemRealisasistore.getAt(i).get('jamakhir'),
                                              durasi  :SDItemRealisasistore.getAt(i).get('durasi'),
                                              biayaperjam    : SDItemRealisasistore.getAt(i).get('biayaperjam'),
                                              biaya   :(SDItemRealisasistore.getAt(i).get('biayaperjam')*SDItemRealisasistore.getAt(i).get('durasi')*SDItemRealisasistore.getAt(i).get('jumlahakhir')),
                                              jumlahpeserta  :SDItemRealisasistore.getAt(i).get('jumlahpeserta'),
                                              statussebelum :'',
                                              statussesudah :'',
                                              keterangan  : ''                                
                                            }));
                                        }
                                        
                                    }
                                    
                                }
                                rowItem=gridSDItemRealisasi.getStore().getCount();
                                var sm = gridTglAwalTglAkhir.getSelectionModel();
                                var a = sm.getSelected();
                                gridSDItemRealisasi.getStore().filter('kodetr', a.get('kodetr'));
                        });
                          
                        }
                    
                    DCostCenterStore.on('load',function(){
                        for(i=0;i<DCostCenterStore.getTotalCount();i++){
                            gridCostCenter.getStore().insert(gridCostCenter.getStore().getCount(),
                        new d_costCenterRealisasiRecord({
                            koderealisasi: txtBtn_kodeRealisasi.getValue(),
                            kodecostcenter:DCostCenterStore.getAt(i).get('kodecostcenter'),
                            namacostcenter: DCostCenterStore.getAt(i).get('namacostcenter'),
                            userid:DCostCenterStore.getAt(i).get('userid'),
                            approval:DCostCenterStore.getAt(i).get('approval'),
                            presentase:DCostCenterStore.getAt(i).get('presentase'),
                            nominal:DCostCenterStore.getAt(i).get('nominal')
                        }));
                        }
                    });    

                    gridAcc.getColumnModel().setEditable(4,true);
   }
   var tglStore=get_m_parameter("READ",SqlCustom_m_parameter.get_selectFilterByParName(),true);
   tglStore.on('load',function(){
                  awal=tglStore.getAt(0).get('parvalue');
                  akhir=tglStore.getAt(0).get('partype');
    });
   txtBtn_kodeRealisasi.onTriggerClick = function(e){
                mode=0;
                
                var rbAll;var rbHistory;var column;var text;var pvalue;
                if(txtBtn_kodeRealisasi.disabled){
                    return;
                }
               
               listObjectInWIndowBrowse = browseHRealisasi(get_t_realisasiStore('READ', SqlCustom_t_realisasi.getAccRealisasi(useridsession),false),awal,akhir);
               listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').disable();
               
               listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter1').getComponent('rbAll').on('check',function(a,b){
                    if(b==true)
                    {
                         rbAll=true;rbHistory=false;
                         column=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('combo').getValue();
                         text=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('txt_Search').getValue();
                        
                        if(text=='')
                            {
                                pvalue='';
                            }
                        else{pvalue=text.toUpperCase()}
                       
                        Sql=SqlCustom_t_realisasi.get_selectRealisasi(awal, akhir,column,pvalue);    
                        
                        listObjectInWIndowBrowse.extgrid.getStore().setBaseParam('sql',Sql);
                        listObjectInWIndowBrowse.extgrid.getStore().load();
                    }
                    
                    });
                    
               listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter1').getComponent('rbHistory').on('check',function(a,b){
                    if(b==true)
                    {
                         rbAll=false;rbHistory=true;
                         column=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('combo').getValue();
                         text=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('txt_Search').getValue();
                        
                        if(text=='')
                            {
                                pvalue='';
                            }
                        else{pvalue=text.toUpperCase()}
                       
                        Sql=SqlCustom_t_realisasi.getSelectHistoryAll(awal, akhir, column, pvalue);    
                        
                        listObjectInWIndowBrowse.extgrid.getStore().setBaseParam('sql',Sql);
                        listObjectInWIndowBrowse.extgrid.getStore().load();
                    }
                });
               listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter1').getComponent('rbAcc').on('check',function(a,b){
                     if(b==true)
                    {
                        Sql=SqlCustom_t_realisasi.getAccRealisasi(useridsession);
                        listObjectInWIndowBrowse.extgrid.getStore().setBaseParam('sql',Sql);
                        listObjectInWIndowBrowse.extgrid.getStore().load();
                    }
               });
               
               listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('btnSearch').on('click', function() {
                        awal=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('hTglAwal').getValue();
                        akhir=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('hTglAkhir').getValue();
                       
                       if(rbAll==true)
                           {
                                 column=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('combo').getValue();
                                 text=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('txt_Search').getValue();
                                 
                                
                                if(text=='')
                                    {
                                        pvalue='';
                                    }
                                else{pvalue=text.toUpperCase();}
                                Sql=SqlCustom_t_realisasi.get_selectRealisasi(awal, akhir,column,pvalue); 
                                listObjectInWIndowBrowse.extgrid.getStore().setBaseParam('sql',Sql);
                                listObjectInWIndowBrowse.extgrid.getStore().load();
                           }
                       if (rbHistory==true)
                           {
                                column=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('combo').getValue();
                                text=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('txt_Search').getValue();
                               
                                if(text=='')
                                    {
                                        pvalue='';
                                    }
                                
                                else{pvalue=text.toUpperCase();}    
                                
                                    Sql=SqlCustom_t_realisasi.getSelectHistoryAll(awal, akhir, column, pvalue);    
                                
                                listObjectInWIndowBrowse.extgrid.getStore().setBaseParam('sql',Sql);
                                listObjectInWIndowBrowse.extgrid.getStore().load();
                           }
                    });
               
               listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                            
                            getData(rowIndex);
                            listObjectInWIndowBrowse.extwindow.close();
                            txtBtn_kodePengajuan.setReadOnly(true);
                            txtBtn_kodeRealisasi.setEditable(false);
                        });
   };
   var StorePemohonRealisasi=customSelect_vm_hris_bpartnerLengkap_xmlstore(SqlCustom_vm_hris_bpartnerLengkap.selectKaryawanFilterByUserId(useridsession));
   StorePemohonRealisasi.on('load',function(){
                  gridAcc = form_trealisasi.getComponent('gridAcc');
                  for(i=0;i<gridAcc.getStore().getCount();i++){
                    if(gridAcc.getStore().getAt(i).get('prioritas')==1){
                    var rec=gridAcc.getStore().getAt(i);
                    rec.set('namabp',StorePemohonRealisasi.getAt(0).get('namabp'));
                    rec.set('namajabatan',StorePemohonRealisasi.getAt(0).get('namajabatan'));
                    rec.set('userid',StorePemohonRealisasi.getAt(0).get('userid'));
                    rec.set('kodejabatan',StorePemohonRealisasi.getAt(0).get('kodejabatan'));
                    }
                } 
        });
   
   var StoreDefault=get_md_konfigurasiaccStore('READ',SqlCustom_md_konfigurasiacc.get_selectFilterByKeterangan(pformid),false);
   StoreDefault.on('load',function(){
                    if (StoreDefault.getCount()==0)
                        {
                            StorePemohonRealisasi.load();
                            StorePemohonPengajuan.load();
                        }
                    if (StoreDefault.getCount()==1)
                        {
                            if(StoreDefault.getAt(0).get('keterangan')==gridAcc.getStore().getAt(0).get('keterangan'))
                                {
                                    StorePemohonPengajuan.load();
                                }
                            else if(StoreDefault.getAt(0).get('keterangan')==gridAcc.getStore().getAt(1).get('keterangan'))
                                {
                                    StorePemohonRealisasi.load();
                                }
                        }
                    gridAcc = form_trealisasi.getComponent('gridAcc');
                    for(i=0;i<gridAcc.getStore().getCount();i++){
                        for (j=0;j<StoreDefault.getCount();j++)
                            {
                                if(gridAcc.getStore().getAt(i).get('keterangan')==StoreDefault.getAt(j).get('keterangan')){
                                    var rec=gridAcc.getStore().getAt(i);
                                    rec.set('namabp',StoreDefault.getAt(j).get('namabp'));
                                    rec.set('namajabatan',StoreDefault.getAt(j).get('namajabatan'));
                                    rec.set('userid',StoreDefault.getAt(j).get('userid'));
                                    rec.set('kodejabatan',StoreDefault.getAt(j).get('kodejabatan'));
                                }
                            }                        
                        }
                    });    
   txtBtn_kodePengajuan.onTriggerClick = function(e){
                mode=1;
                
                var column;var text;var pvalue;
                if(txtBtn_kodePengajuan.disabled){
                    return;
                }
              
                listObjectInWIndowBrowse = browseHPengajuan(get_t_pengajuanStore('READ',SqlCustom_t_pengajuan.get_selectRealisasi(awal,akhir,'kodepengajuan',''),false),formMode,awal,akhir);
                listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter1').getComponent('rbAcc').disable();
                listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter1').getComponent('rbHistory').disable();
                listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter1').getComponent('rbAll').setValue(true);
                
                listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('btnSearch').on('click', function() {
                    awal=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('hTglAwal').getValue();
                    akhir=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('hTglAkhir').getValue();
                    column=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('combo').getValue();
                    text=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('txt_Search').getValue();
                    
                    if(text=='')
                        {pvalue='';}
                    else{pvalue=text.toUpperCase();}
                    Sql=SqlCustom_t_pengajuan.get_selectRealisasi(awal,akhir,column,pvalue);
                    listObjectInWIndowBrowse.extgrid.getStore().setBaseParam('sql',Sql);
                    listObjectInWIndowBrowse.extgrid.getStore().load();
                });
                listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                    
                    
                    getData(rowIndex);
                    StoreDefault.load();
                    
                    
                    StorePemohonPengajuan.on('load',function(){      
                        gridAcc = form_trealisasi.getComponent('gridAcc');
                        for(i=0;i<gridAcc.getStore().getCount();i++){
                            if(gridAcc.getStore().getAt(i).get('prioritas')==2){
                            var rec=gridAcc.getStore().getAt(i);
                            rec.set('userid',StorePemohonPengajuan.getAt(0).get('userid'));
                            rec.set('namabp',StorePemohonPengajuan.getAt(0).get('namabp'));
                            rec.set('kodejabatan',StorePemohonPengajuan.getAt(0).get('kodejabatan'));
                            rec.set('namajabatan',StorePemohonPengajuan.getAt(0).get('namajabatan'));
                           }
                         } 
                    });
                    listObjectInWIndowBrowse.extwindow.close();
                    txtBtn_kodeRealisasi.setReadOnly(true);
                    txtBtn_kodePengajuan.setEditable(false);
                });
           };

    txtBtn_ApprovingCostCenter.onTriggerClick = function(e){
          var sm = gridCostCenter.getSelectionModel();
          var record_gridCostCenter = sm.getSelected();
          if (status==1)
               {return;}
           else if (status==0)
               {var window=showApproveWindow('READ',record_gridCostCenter.get('kodecostcenter'),record_gridCostCenter.get('presentase'),
                record_gridCostCenter.get('nominal'),record_gridCostCenter.get('userid'),record_gridCostCenter,form_trealisasi,dataSubmit);
                status=1;}
          window.on('close', function(p){
                status=0;
                });
          window.buttons[1].on('click', function(e){
                status=0;
                });
      }; 

     txtBtn_item.onTriggerClick = function(e){
          var sm = gridSDItemRealisasi.getSelectionModel();
          var record_gridSDItemRealisasi = sm.getSelected();
          if (status==1)
               {return;}
           else if (status==0)
                {listObjectInWIndowBrowse = browseItem(null);status=1;}
          listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridSDItemRealisasi.set('kodeitem',row.get('kodeitem'));
                record_gridSDItemRealisasi.set('namaitem',row.get('namaitem'));
                listObjectInWIndowBrowse.extwindow.close();status=0;
          });
          listObjectInWIndowBrowse.extwindow.on('close', function(p){
                status=0;
            });
      };

   var form_trealisasi = new Ext.FormPanel({
         url: 'maincontroller',
         region:'center',
         autoScroll: true,
         tbar:[],
         frame: true,
         bodyStyle:'padding:5px 5px 0',
         //labelAlign: 'top',
         items: [{
            layout:'column',
            items:[{
                columnWidth:.5,
                layout: 'form',
                items: [txtBtn_kodeRealisasi,txtBtn_kodePengajuan,txt_jenisrealisasi,rb_jenistarif,txt_namaPemohon,txt_nik,
                        txt_Perusahaan,txt_organisasi,txt_namajabatan]
            },{
                columnWidth:.5,
                layout: 'form',
                items: [txt_tglPengajuan,txt_tglRealisasi,gridCostCenter]
            }]
         },
         {
            xtype:'fieldset',
            title: 'Detail view',
            layout:'column',
            anchor: '100%',
            collapsible: true,
            autoHeight:true,
            items :[gridTglAwalTglAkhir,gridSDItemRealisasi]
         },hidden_kodebp,hidden_versi,hidden_IE,hidden_biayaTotal,
         {
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

      var gridAcc = null;
      var dataSubmit = new DataSubmit('maincontroller', function(){
          //var params = Ext.urlDecode(form_tpengajuan.getForm().getValues(true));
          //params.arraycoba1 = [1,2,3,4,5];
          //params.arraycoba2 = [5,4,3,2,1];
          gridAcc = form_trealisasi.getComponent('gridAcc');
          //(arrayXmlSisipan,tableType,tableName,markModified,
          //onlyModified, prettyPrint, showType, indent,setHeaderXML)
          gridSDItemRealisasi.getStore().clearFilter();
          var xmlSdItemRealisasi = gridSDItemRealisasi.getStore().xmlSerialize(null,'subdetail',
                'sd_itemrealisasi',false, false, false, false, 3,false);
          var xmlDTglAwalTglAkhir = gridTglAwalTglAkhir.getStore().xmlSerialize([xmlSdItemRealisasi],
                'detail','d_tglrealisasi',false, false, false, false, 2,false);
          var xmlDCostCenter = gridCostCenter.getStore().xmlSerialize(null,'detail','d_costcenterrealisasi',
                false, false, false, false, 0,false);
          var xmlDAcc=gridAcc.getStore().xmlSerialize(null,'detail','m_acc',false, false, false, false, 0,false);
          var xmlHTRealisasi = convertFormValues_toXMLString(form_trealisasi,'header','h_realisasi',
                [xmlDCostCenter,xmlDTglAwalTglAkhir,xmlDAcc], false, 0,true);
          //console.log(xmlHTPengajuan);
          //params.xmldata = xmlHTPengajuan;
          var params = {
              formid:form_trealisasi.getComponent('formid').getValue(),
              formmode:form_trealisasi.getComponent('formmode').getValue(),
              xmldata : xmlHTRealisasi};
        return params;
      });
      generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_trealisasi,dataSubmit);
      
      txtBtn_datetime_jamawal.onTriggerClick = function(e){
           var sm = gridTglAwalTglAkhir.getSelectionModel();
           var record_gridTglAwalTglAkhir = sm.getSelected();                                            
           sm = gridSDItemRealisasi.getSelectionModel();
           var record_gridSDItemRealisasi = sm.getSelected();
            if (status==1)
               {return;}
           else if (status==0)
               {var window=insertJamawalJamakhirItem(record_gridSDItemRealisasi.get('kodeitem'),hidden_kodebp.getValue(),
                record_gridSDItemRealisasi.get('jumlahakhir'),record_gridSDItemRealisasi.get('jumlahakhir'),null,
                record_gridTglAwalTglAkhir.get('namatarif'),gridSDItemRealisasi,record_gridTglAwalTglAkhir.get('kodetr'),hidden_IE.getValue());
               status=1;}
           window.on('close', function(p){
                status=0;
            });
          window.buttons[1].on('click', function(e){
                status=0;
          });
       };
      var customBtnCRUD = new CustomBtnCRUD (function(){
            gridAcc = form_trealisasi.getComponent('gridAcc');
            generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_trealisasi,dataSubmit);
      } //beforeClickBaru
        ,function(){
           
            txtBtn_kodePengajuan.setReadOnly(false);
            txtBtn_kodeRealisasi.setReadOnly(true);
            txt_jenisrealisasi.setReadOnly(true);
            txt_namaPemohon.disable();
            txt_nik.disable();
            txt_Perusahaan.disable();
            txt_organisasi.disable();
            txt_namajabatan.disable();
            txt_tglPengajuan.disable();
    
            gridCostCenter.getStore().removeAll();
            gridTglAwalTglAkhir.getStore().removeAll();
            gridSDItemRealisasi.getStore().removeAll();
            gridAcc.getColumnModel().setEditable(4,true);
        } //afterClickBaru
        ,function(){
            if(txt_namaPemohon.getValue()=='')
                {
                    alert('Pilih Kode Pengajuan yang Ingin Diubah.');
                    return true;
                }
            var store = form_trealisasi.getComponent('gridAcc').getStore();
            var noUpdate = false;
            for(i=0;i<store.getCount();i++){
                
                if( store.getAt(i).get('statusacc').toString()!='0'){
                    noUpdate = true;
                    break;
                }                    
            }
            if(noUpdate){  
                var jumbelumacc = 0;
                for(i=0;i<store.getCount();i++){
                    if( store.getAt(i).get('statusacc').toString()!='0'){
                        jumbelumacc = jumbelumacc+1;
                    }  
                }
                if(jumbelumacc==store.getCount()){
                     Ext.Msg.show({
                                   title: 'Warning!',
                                   msg: 'transaksi ini tidak bisa di ubah',
                                   icon: Ext.Msg.WARNING,
                                   buttons: Ext.MessageBox.OK
                                });                
                     return true;
                }
                
            }
            return false;
        } //beforeClickUbah
        ,function(){ //afterClickUbah
            
                 
            var store = form_trealisasi.getComponent('gridAcc').getStore();
            var jumbelumacc = 0;
            for(i=0;i<store.getCount();i++){
                if( store.getAt(i).get('statusacc').toString()!='0'){
                    jumbelumacc = jumbelumacc+1;
                }  
            }
            txt_jenisrealisasi.setReadOnly(true);
            txt_namaPemohon.disable();
            txt_nik.disable();
            txt_Perusahaan.disable();
            txt_organisasi.disable();
            txt_namajabatan.disable();
            txt_tglPengajuan.disable();
            rb_jenistarif.disable();
            txtBtn_kodePengajuan.setReadOnly(true);
            if(jumbelumacc<store.getCount() && jumbelumacc!=0){          
            txt_organisasi.disable();
                for(i=0;i<colModel_gridCostCenter.getColumnCount();i++){
                   colModel_gridCostCenter.setEditable( i, false);
                }
                for(i=0;i<colModel_gridSDItemRealisasi.getColumnCount();i++){
                   colModel_gridSDItemRealisasi.setEditable( i, false);
                }
                for(i=0;i<colModel_gridTglAwalTglAkhir.getColumnCount();i++){
                    colModel_gridTglAwalTglAkhir.setEditable( i, false);
                }
            }
        }
        ,function(){
           if(txt_namaPemohon.getValue()=='')
                {
                    alert('Pilih Kode Pengajuan yang Ingin Dihapus.');
                    return true;
                }
            var store = form_trealisasi.getComponent('gridAcc').getStore();
            var noUpdate = false;
            for(i=0;i<store.getCount();i++){
                if( store.getAt(i).get('statusacc').toString()!='0'){
                    noUpdate = true;
                    break;
                }                    
            }
            if(noUpdate){                
                 Ext.Msg.show({
                               title: 'Warning!',
                               msg: 'transaksi ini tidak bisa di hapus',
                               icon: Ext.Msg.WARNING,
                               buttons: Ext.MessageBox.OK
                            });                
                 return true;
            }
            return false;
        } //beforeClickHapus
        ,function(success){//afterClickHapus
            
            if(success){
                txtBtn_kodeRealisasi.enable();
            }else{

            }
        }
        ,function(){ //beforeClickSimpan
           
                txtBtn_kodePengajuan.setReadOnly(true);
        }
        ,function(success){ //afterClickSimpan
            if(success){
                txtBtn_kodeRealisasi.setReadOnly(false);
                txtBtn_kodeRealisasi.setValue('');
                txtBtn_kodeRealisasi.enable();
                txt_Perusahaan.setValue('');
                txtBtn_kodePengajuan.setValue('');
                txt_jenisrealisasi.setValue('');
                txt_namaPemohon.setValue('');
                txt_nik.setValue('');
                txt_organisasi.setValue('');
                txt_tglPengajuan.setValue('');
                txt_namajabatan.setValue('');
                txt_tglRealisasi.setValue('');
                
                gridAcc = form_trealisasi.getComponent('gridAcc');
                generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_trealisasi,dataSubmit);
            }else{
                    txtBtn_kodePengajuan.setReadOnly(true);
            }
        }
        ,function(){
            //console.log(form_tpengajuan.getForm().getValues());
            txtBtn_kodePengajuan.setReadOnly(false);
        } //beforeClickBatal
        ,function(){
            txtBtn_kodeRealisasi.setReadOnly(false);
            txtBtn_kodeRealisasi.enable();
            gridAcc = form_trealisasi.getComponent('gridAcc');
            generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_trealisasi,dataSubmit);
        } //afterClickBatal
        ,function(){} //beforeClickPrint
        ,function(){
            console.log(dataSubmit.getParams());
            console.log(form_trealisasi.getForm().getValues());} //afterClickPrint
      );
          
    setCRUDFormButton(form_trealisasi,trealisasi_xmlstore,null,customBtnCRUD,dataSubmit);
    filterPermissionButtonForm(form_trealisasi,pview,padd,pupdate,pdelete,pprint,trealisasi_xmlstore);
    return getTwoPanelVerticalBorderLayoout(form_trealisasi,null,ptitle,pformid);
}

