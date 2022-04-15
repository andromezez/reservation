
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getForm_tperubahan(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint,batal_trans)
{
    var awal;var akhir;var kodeCostCenter;var status=0;
    var genres = new Ext.data.ArrayStore({
               fields: ['id', 'genre_name'],
               data : [['sewa ruang','Sewa Ruang'],['ijin lembur','Ijin Lembur']]
            });
    
    var approvStore = new Ext.data.ArrayStore({
               fields: ['id', 'approve_name'],
               data : [['1','approved'],['0','unapproved']]
            });
    function rendererApprove_name(val) {
             return approvStore.queryBy(function(rec){
                return rec.data.id == val;
              }).itemAt(0).get('approve_name');
    };
    var txt_jenis = new Ext.form.ComboBox({
                    hiddenName: 'jenisrealisasi',
                    //name: 'jenisrealisasi',
                    fieldLabel: 'Jenis Transaksi',
                    mode: 'local',
                    store: genres,
                    displayField:'genre_name',
                    valueField:'id',
                    anchor:'95%',
                    value :'sewa ruang',
                    allowBlank: false
    });  
    
    var rb_jenistarif=new Ext.form.RadioGroup({            
            fieldLabel: 'Jenis tarif',
            items: [
                {boxLabel: 'Internal', name: 'Tarif',itemId:'internal', inputValue: 'I', checked: true},
                {boxLabel: 'Eksternal', name: 'Tarif',itemId:'eksternal', inputValue: 'E'},              
            ],
            listeners:{
                change: function(rd,checked){
                    if(rb_jenistarif.getValue().getGroupValue()){
                      hidden_IE.setValue(rb_jenistarif.getValue().getGroupValue());
                    }
                 
                }
              }
                
        });
   var hidden_IE= new Ext.form.Hidden({
            itemId :'IE',
            name: 'IE',
            value:'I'
         }); 
   var hidden_kodebp= new Ext.form.Hidden({
            itemId :'kodebp',
            name: 'kodebp',
            value:''
         });
   var hidden_kodeorg= new Ext.form.Hidden({
            itemId :'kodeorg',
            name: 'kodeorg',
            value:''
         });
   var hidden_totalBiaya= new Ext.form.Hidden({
            itemId :'totalbiaya',
            name: 'totalbiaya',
            value:0
         });
   var hidden_batal= new Ext.form.Hidden({
            itemId :'batal',
            name: 'batal',
            value:0
         });
         
    var txtBtn_kodePengajuan = new Ext.form.TriggerField({
       itemId :'kodepengajuan',
       fieldLabel: 'Kode Pengajuan',
       name: 'kodepengajuan',
       value:'',
       disabled:false,
       allowBlank: true,
       //submitValue:false,
       triggerClass: 'x-form-search-trigger',
       anchor:'95%',
       editable:false
   });
   var txt_namaPemohon = new Ext.form.TriggerField({
       itemId :'namabp',
       fieldLabel: 'Nama Pemohon',
       name: 'namabp',
       value:'',
       disabled:true,
       allowBlank: false,
       submitValue:false     ,
       triggerClass: 'x-form-search-trigger',
       anchor:'95%',
       editable:false
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
     
    var txt_tglperubahan = new Ext.form.DateField({
           itemId :'tgltransaksi',
           fieldLabel: 'Tgl. Perubahan',
           name: 'tgltransaksi',
           editable:false,
           disabled:true,
           allowBlank: false,
           value:getCurrentDate(),
           anchor:'95%'
       });
    var txtBtn_ApprovingCostCenter = new Ext.form.TriggerField({
           itemId :'approval',
           name: 'approval',
           value:'',
           triggerClass: 'x-form-search-trigger',
           editable:false
       });
    var txt_tglpengajuan = new Ext.form.DateField({
           itemId :'tgltransaksi',
           fieldLabel: 'Tgl. Pengajuan',
           name: 'tgltransaksi',
           editable:false,
           disabled:false,
           allowBlank: false,
           value:getCurrentDate(),
           anchor:'95%'
       });
   
    var txt_versi= new Ext.form.NumberField({
            itemId :'versi',
            fieldLabel: 'Versi',
            name: 'versi',
            value:0,
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
   txt_namaPemohon.onTriggerClick = function(e){
            //var record_vm_hris_bpartnerLengkapRecord = Ext.data.Record.create(arrayField_vm_hris_bpartnerLengkap);
           /* browseBPartneLengkap(hidden_kodebp, txt_NIK, txtBtn_namaPemohon, null, txt_perusahaan,
                hidden_kodeorg, txt_organisasi, null, null,null,null,record_vm_hris_bpartnerLengkapRecord);*/
            if(txt_namaPemohon.disabled){
                return;
            }
            if(storePermission.getAt(0).get('batal_trans')==0){
                    alert('Anda Tidak Memiliki Hak Akses');
                    return;}
                
        var sql=SqlCustom_vm_hris_bpartnerLengkap.selectKaryawanPemohon();
            listObjectInWIndowBrowse = browseBPartneLengkap(customSelect_vm_hris_bpartnerLengkap_xmlstore(SqlCustom_vm_hris_bpartnerLengkap.selectKaryawanPemohon()),sql);            
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);                
                hidden_kodebp.setValue(row.get('kodebp'));
                txt_nik.setValue(row.get('nik'));
                txt_namaPemohon.setValue(row.get('namabp'));
                txt_Perusahaan.setValue(row.get('namaperusahaan'));
                hidden_kodeorg.setValue(row.get('kodeorg'));
                txt_organisasi.setValue(row.get('namaorg'));
                txt_namajabatan.setValue(row.get('namajabatan'));
                kodeCostCenter=row.get('kodecostcenter');
                gridAcc = form_tperubahan.getComponent('gridAcc');
                  for(i=0;i<gridAcc.getStore().getCount();i++){
                    if(gridAcc.getStore().getAt(i).get('prioritas')==1){
                    var rec=gridAcc.getStore().getAt(i);
                    rec.set('namabp',txt_namaPemohon.getValue());
                    rec.set('namajabatan',txt_namajabatan.getValue());
                    rec.set('userid',row.get('userid'));
                    rec.set('kodejabatan',row.get('kodejabatan'));
                    }
                } 
                listObjectInWIndowBrowse.extwindow.close();
            });            
       };
   var StoreDefault=get_md_konfigurasiaccStore('READ',SqlCustom_md_konfigurasiacc.get_selectFilterByKeterangan(pformid),false);
   StoreDefault.on('load',function(){
                    gridAcc = form_tperubahan.getComponent('gridAcc');
                    for(i=0;i<gridAcc.getStore().getCount();i++){
                        for (j=0;j<StoreDefault.getCount();j++)
                            {
                                if(gridAcc.getStore().getAt(i).get('keterangan')==StoreDefault.getAt(j).get('keterangan')){
                                    var rec=gridAcc.getStore().getAt(i);
                                    rec.set('namabp',StoreDefault.getAt(j).get('namabp'));
                                    rec.set('namajabatan',StoreDefault.getAt(j).get('namajabatan'));
                                    rec.set('userid',StoreDefault.getAt(j).get('userid'));
                                    rec.set('kodejabatan',StoreDefault.getAt(j).get('kodejabatan'));
                                    rec.set('tglacc',getCurrentDate());
                                    rec.set('statusacc',0);
                                }
                            }                        
                        }
                    });
   
   var StorePemohon=customSelect_vm_hris_bpartnerLengkap_xmlstore(SqlCustom_vm_hris_bpartnerLengkap.selectKaryawanFilterByUserId(useridsession));
   StorePemohon.on('load',function(){
                  gridAcc = form_tperubahan.getComponent('gridAcc');
                  txt_namaPemohon.setValue(StorePemohon.getAt(0).get('namabp'));
                  txt_nik.setValue(StorePemohon.getAt(0).get('nik'));
                  txt_Perusahaan.setValue(StorePemohon.getAt(0).get('namaperusahaan'));
                  hidden_kodeorg.setValue(StorePemohon.getAt(0).get('kodeorg'));
                  hidden_kodebp.setValue(StorePemohon.getAt(0).get('kodebp'));
                  txt_organisasi.setValue(StorePemohon.getAt(0).get('namaorg'));
                  txt_namajabatan.setValue(StorePemohon.getAt(0).get('namajabatan'));
                  kodeCostCenter=StorePemohon.getAt(0).get('kodecostcenter');
                  
                 
                  for(i=0;i<gridAcc.getStore().getCount();i++){
                    if(gridAcc.getStore().getAt(i).get('prioritas')==1){
                    var rec=gridAcc.getStore().getAt(i);
                    rec.set('namabp',txt_namaPemohon.getValue());
                    rec.set('namajabatan',txt_namajabatan.getValue());
                    rec.set('userid',StorePemohon.getAt(0).get('userid'));
                    rec.set('kodejabatan',StorePemohon.getAt(0).get('kodejabatan'));
                    rec.set('tglacc',getCurrentDate());
                    rec.set('statusacc',0);
                    }
                }    
        });
   
   var storePermission=get_m_permissionStore('READ',SqlCustom_m_permission.get_selectFilterByKodeFormAndUserId('01-04-02', useridsession));
   
   var colModel_gridCostCenter = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [
                      {header: "Cost Center", dataIndex: arrayField_d_costcenterpengajuan[1],
                         editor: txtBtn_costCenter},
                      {header: "User Approval", dataIndex: arrayField_d_costcenterpengajuan[3],
                         editor: txtBtn_userid},
                      {header: "Status", dataIndex: arrayField_d_costcenterpengajuan[7],
                         editor: txtBtn_ApprovingCostCenter,renderer:rendererApprove_name},
                      {header: "Presentase", dataIndex: arrayField_d_costcenterpengajuan[8], xtype: 'numbercolumn',
                        editor:txt_number_grid1,align:'right'},
                      {header: "Nominal", dataIndex: arrayField_d_costcenterpengajuan[9], xtype: 'numbercolumn',
                        editor:txt_number_grid2,align:'right'}
                    ]
                });
   var gridCostCenter = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 160,
                anchor:'100%',
                itemId:'gridCostCenter',
                title:'Cost Center',
                store: get_d_costCenterPengajuanStore(formMode),
                selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
                colModel: colModel_gridCostCenter,
                //disabled:true,
                listeners: {
                    
                    afteredit: function(e){
                        if (e.field == 'presentase'){
                             var total = 0;
                             gridSDItemPerubahan.getStore().clearFilter();
                             for(i=0;i<gridSDItemPerubahan.getStore().getCount();i++){
                                 total = total + parseInt(gridSDItemPerubahan.getStore().getAt(i).get('biaya'));
                             }
                             e.record.set('nominal',(parseFloat(e.value)*total)/100);
                             if(gridTglAwalTglAkhir.getSelectionModel().hasSelection()){
                                var sm = gridTglAwalTglAkhir.getSelectionModel();
                                var row = sm.getSelected();                                  
                                gridSDItemPerubahan.getStore().filter('kodetp', row.get('kodetp'));
                             }else if(gridTglAwalTglAkhir.getStore().getCount()>0){
                                 gridTglAwalTglAkhir.getSelectionModel().selectFirstRow();
                             }                                               
                        }else if(e.field == 'nominal'){
                             var total = 0;
                             gridSDItemPerubahan.getStore().clearFilter();
                             for(i=0;i<gridSDItemPerubahan.getStore().getCount();i++){
                                 total = total + parseInt(gridSDItemPerubahan.getStore().getAt(i).get('biaya'));
                             }
                             e.record.set('presentase',(parseInt(e.value)/total)*100);
                             if(gridTglAwalTglAkhir.getSelectionModel().hasSelection()){
                                var sm = gridTglAwalTglAkhir.getSelectionModel();
                                var row = sm.getSelected();                                  
                                gridSDItemPerubahan.getStore().filter('kodetp', row.get('kodetp'));
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
                     gridSDItemPerubahan.getStore().clearFilter();
                     for(i=0;i<gridSDItemPerubahan.getStore().getCount();i++){
                         total = total + parseInt(gridSDItemPerubahan.getStore().getAt(i).get('biaya'));
                     }
                     if(gridTglAwalTglAkhir.getSelectionModel().hasSelection()){
                        var sm = gridTglAwalTglAkhir.getSelectionModel();
                        var row = sm.getSelected();                                  
                        gridSDItemPerubahan.getStore().filter('kodetp', row.get('kodetp'));
                     }else if(gridTglAwalTglAkhir.getStore().getCount()>0){
                         gridTglAwalTglAkhir.getSelectionModel().selectFirstRow();
                     }
                     
                     gridCostCenter.getStore().insert( gridCostCenter.getStore().getCount(),
                        new d_costCenterPengajuanRecord({
                            kodepengajuan:txtBtn_kodePengajuan.getValue(),
                            kodecostcenter:kodeCostCenter,
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
           // browseCostCenter(/*null,null,null,*/record_vm_hris_costcenterRecord);
           if (status==1)
               {return;}
           else if (status==0)
                {listObjectInWIndowBrowse = browseCostCenter(null);status=1;}
            
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridCostCenter.set('userid','');
                record_gridCostCenter.set('approval','0');
                record_gridCostCenter.set('kodecostcenter',row.get('kodecostcenter'));
                listObjectInWIndowBrowse.extwindow.close();status=0;
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
                {listObjectInWIndowBrowse = browseBPartneLengkap(customSelect_vm_hris_bpartnerLengkap_xmlstore(
                (SqlCustom_vm_hris_bpartnerLengkap.selectFilterByCostCenter).replace('filterCostCenter',record_gridCostCenter.get('kodecostcenter'))));status=1;}
            
           
            listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('rbcustomer').disable();
           
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
               
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridCostCenter.set('userid',row.get('userid'));
                console.log(record_gridCostCenter.get('userid'));
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
                      {header: "Tanggal",width:95, dataIndex: arrayField_d_tglpengajuanStore[2],
                         editor: new Ext.form.DateField({
                            itemId :'TglAwal',
                            name: 'TglAwal'
                         }),renderer: Ext.util.Format.dateRenderer( 'd M Y')},
                     {header: "Jenis Hari", dataIndex: 'namatarif'}
                    /*  {header: "Tgl Akhir", dataIndex: arrayField_d_tglpengajuanStore[3],
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
                //columnWidth:.3,          
                store: get_d_tglpengajuanStore(formMode),
                selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
                colModel: colModel_gridTglAwalTglAkhir,
                tbar: [{
                  text: 'Add row',
                  handler: function(){
                      var a= null;
                          a = new d_tglPengajuanRecord({
                            kodetp:parseToDateTimeOnly(new Date())+'-'+Math.floor(Math.random()*11),
                            kodepengajuan:txtBtn_kodePengajuan.getValue(),
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
                    showCopySDItemToOtherDateWindow(gridTglAwalTglAkhir.getStore(),gridSDItemPerubahan.getStore(),record.get('tglawal'),record,true);
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
            gridSDItemPerubahan.getStore().filter('kodetp', record.get('kodetp'));
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
   var colModel_gridSDItemPerubahan = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [
                      {header: "Ruang dan Perlengkapan", dataIndex: 'namaitem' ,width:180,
                         editor: txtBtn_item},
                      {header: "Jumlah", dataIndex: 'jumlahakhir',width:50,
                         editor: txt_number_grid3, xtype: 'numbercolumn',format: '0',align:'right'},
                      {header: "Jam Awal", dataIndex: 'jamawal',width:70,
                         editor: txtBtn_datetime_jamawal,type: 'date', dateFormat: 'H:i'},
                      {header: "Jam Akhir", dataIndex: 'jamakhir',width:70,
                         stype: 'date', dateFormat: 'H:i'},
                      {header: "Durasi", dataIndex: 'durasi',align:'right',width:50,
                         editor: txt_number_grid4,renderer:rendererApprove_name,xtype: 'numbercolumn',format: '0'},
                      {header: "Biaya perjam", dataIndex: 'biayaperjam', xtype: 'numbercolumn',align:'right',editor:txt_number_grid6},
                      {header: "Biaya", dataIndex: 'biaya', xtype: 'numbercolumn',align:'right',
                        editor:txt_number_grid5},
                      {header: "Jumlah Peserta", dataIndex: 'jumlahpeserta', xtype: 'numbercolumn',
                        editor:txt_number_grid7,format: '0',align:'right'},
                     //{header: "Antrian", dataIndex:'antrian', xtype: 'numbercolumn',format: '0', visible: false},
                      {header: "Keterangan", dataIndex: 'keterangan',
                         editor: new Ext.form.TextField({value:''})}
                    ]
                });
   var gridSDItemPerubahan = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 160,
                //anchor:'100%',
                columnWidth:1,
                itemId:'gridSDItemPerubahan',
                store: get_sd_itempengajuanStore(formMode),
                selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
                colModel: colModel_gridSDItemPerubahan,
                tbar: [{
                  text: 'Add row',
                  handler: function(){
                     gridSDItemPerubahan.getStore().insert( gridSDItemPerubahan.getStore().getCount(),
                        new sd_itempengajuanRecord({
                              kodetp : (function(){
                                  var sm = gridTglAwalTglAkhir.getSelectionModel();
                                  var row = sm.getSelected();
                                  return row.get('kodetp');
                              })(),
                              kodeitem  : '',
                              namaitem: '',
                              jumlahawal: 1,
                              jumlahakhir : 1,
                              jumlahpeserta:0,
                              durasi  : 0,
                              biaya    : 0,
                              antrian : 0,
                              keterangan  : ''
                         })
                      );
                      //gridSDItemPengajuan.startEditing(gridSDItemPengajuan.getStore().getCount()-1,0);
                    }
                  },{
                  text: 'Delete row',
                  handler: function(btn){
                    var sm = gridSDItemPerubahan.getSelectionModel();
                    sel = sm.getSelected();
                    gridSDItemPerubahan.getStore().remove(sel);
                  }
               }],
            listeners: {
                afteredit: function(e){
                  if (e.field == 'jumlahakhir'){
                    //var sm = gridSDItemPengajuan.getSelectionModel();
                    var record_gridSDItemPengajuan = e.record;//sm.getSelected();
                    //console.log(record_gridSDItemPengajuan);
                    if ((record_gridSDItemPengajuan.get('jamawal') != null) && (record_gridSDItemPengajuan.get('jamakhir') != null)
                        && (e.value != null)  && (record_gridSDItemPengajuan.get('kodeitem') != null)
                        && (hidden_kodebp.getValue()!='') && (record_gridSDItemPengajuan.get('biayaperjam')!=null) && 
                        (record_gridSDItemPengajuan.get('durasi')!=null)){
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
                        record_gridSDItemPengajuan.set('biaya',record_gridSDItemPengajuan.get('biayaperjam')*record_gridSDItemPengajuan.get('durasi')*e.value);
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
                      store.load();
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
                      store.load();
                  }
                }
              }
   });
   for(i=0;i<colModel_gridSDItemPerubahan.getColumnCount();i++){
       colModel_gridSDItemPerubahan.setEditable( i, false);
   }
   gridSDItemPerubahan.getTopToolbar().disable();
   
   var tglStore=get_m_parameter("READ",SqlCustom_m_parameter.get_selectFilterByParName(),false);
   tglStore.on('load',function(){
                  awal=tglStore.getAt(0).get('parvalue');
                  akhir=tglStore.getAt(0).get('partype');
    });
   tglStore.load();
    
   txtBtn_kodePengajuan.onTriggerClick = function(e){
                StorePemohon.load();    
                var SqlCustom;var Sql;var rbAll;var rbHistory;var column;var text;var pvalue;
                /*
                if(storePermission.getAt(0).get('batal_trans')==0){
                    SqlCustom=SqlCustom_t_pengajuan.get_selectPerubahan(formMode,awal,akhir,useridsession);
                    }
                else if(storePermission.getAt(0).get('batal_trans')==1){
                    SqlCustom=SqlCustom_t_pengajuan.get_selectPerubahanAll(formMode, awal, akhir);    
                    }*/
                if(formMode=='READ')
                    {
                     SqlCustom=SqlCustom_t_pengajuan.get_selectAccPengajuan(useridsession);   
                    }
                else if(formMode=='INSERT')
                    {
                        rbAll=true;rbHistory=false;
                     if(storePermission.getAt(0).get('batal_trans')==0){
                        SqlCustom=SqlCustom_t_pengajuan.get_selectPerubahan(formMode,awal,akhir,useridsession,'kodepengajuan','');
                    }
                    else if(storePermission.getAt(0).get('batal_trans')==1){
                        SqlCustom=SqlCustom_t_pengajuan.get_selectPerubahanAll(formMode, awal, akhir,'kodepengajuan','');    
                    }   
                    }
                listObjectInWIndowBrowse = browseHPengajuan(get_t_pengajuanStore('READ',SqlCustom,false),formMode,awal,akhir,SqlCustom);
                 
                if(formMode=='READ')
                    {
                        listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').disable();
                    }
                else if(formMode=='INSERT')
                    {
                        //listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('rbcustomer').disable();
                        listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter1').getComponent('rbHistory').disable();
                        listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter1').getComponent('rbAcc').disable();
                        listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter1').getComponent('rbAll').setValue(true);
                    }
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
                        if(storePermission.getAt(0).get('batal_trans')==0){
                            Sql=SqlCustom_t_pengajuan.get_selectPerubahan(formMode,awal,akhir,useridsession,column,pvalue);
                        }
                        else if(storePermission.getAt(0).get('batal_trans')==1){
                            Sql=SqlCustom_t_pengajuan.get_selectPerubahanAll(formMode, awal, akhir,column,pvalue);    
                        }
                        listObjectInWIndowBrowse.extgrid.getStore().setBaseParam('sql',Sql);
                        listObjectInWIndowBrowse.extgrid.getStore().load();
                    }
                    
                    });
                    
                    listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter1').getComponent('rbAcc').on('check',function(a,b){
                     if(b==true)
                    {
                        Sql=SqlCustom_t_pengajuan.get_selectAccPengajuan(useridsession);
                        listObjectInWIndowBrowse.extgrid.getStore().setBaseParam('sql',Sql);
                        listObjectInWIndowBrowse.extgrid.getStore().load();
                    }
                    
                    });
                    
                    listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter1').getComponent('rbHistory').on('check',function(a,b){
                     if(b==true)
                    {rbAll=false;rbHistory=true;
                         column=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('combo').getValue();
                         text=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('txt_Search').getValue();
                        
                        if(text=='')
                            {
                                pvalue='';
                            }
                        else{pvalue=text.toUpperCase()}    
                        if(storePermission.getAt(0).get('batal_trans')==0){
                            Sql=SqlCustom_t_pengajuan.get_selectHistoryFilterByUserId(column, pvalue, awal, akhir, useridsession);
                        }
                        else if(storePermission.getAt(0).get('batal_trans')==1){
                            Sql=SqlCustom_t_pengajuan.get_selectHistoryAll(column, pvalue, awal, akhir);    
                        }
                        listObjectInWIndowBrowse.extgrid.getStore().setBaseParam('sql',Sql);
                        listObjectInWIndowBrowse.extgrid.getStore().load();
                    }
                    
                    });
                    listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('btnSearch').on('click', function() {
                        var tglawal=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('hTglAwal').getValue();
                        var tglakhir=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('hTglAkhir').getValue();
                        if (tglawal!='')
                        {awal=tglawal}
                        if (tglakhir!='')
                        {akhir=tglakhir}
                       
                       if(rbAll==true)
                           {
                                 column=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('combo').getValue();
                                 text=listObjectInWIndowBrowse.extgrid.getTopToolbar().getComponent('Filter2').getComponent('txt_Search').getValue();
                                 
                                
                                if(text=='')
                                    {
                                        pvalue='';
                                    }
                                else{pvalue=text.toUpperCase();}
                                if(storePermission.getAt(0).get('batal_trans')==0){
                                    Sql=SqlCustom_t_pengajuan.get_selectPerubahan(formMode,awal,akhir,useridsession,column,pvalue);
                                }
                                else if(storePermission.getAt(0).get('batal_trans')==1){
                                    Sql=SqlCustom_t_pengajuan.get_selectPerubahanAll(formMode, awal, akhir,column,pvalue);    
                                }
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
                                
                                else{pvalue=text.toUpperCase()}    
                                if(storePermission.getAt(0).get('batal_trans')==0){
                                    Sql=SqlCustom_t_pengajuan.get_selectHistoryFilterByUserId(column, pvalue, awal, akhir, useridsession);
                                }
                                else if(storePermission.getAt(0).get('batal_trans')==1){
                                    Sql=SqlCustom_t_pengajuan.get_selectHistoryAll(column, pvalue, awal, akhir);    
                                }
                                listObjectInWIndowBrowse.extgrid.getStore().setBaseParam('sql',Sql);
                                listObjectInWIndowBrowse.extgrid.getStore().load();
                           }
                    });
                listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                    btnBatalTrans.enable();
                    rb_jenistarif.disable();
                    txt_jenis.setReadOnly(true);
                    gridCostCenter.getStore().removeAll();
                    gridTglAwalTglAkhir.getStore().removeAll();
                    gridSDItemPerubahan.getStore().removeAll();
                    var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                    txtBtn_kodePengajuan.setValue(row.get('kodepengajuan'));                
                    txt_jenis.setValue(row.get('jenisrealisasi'));
                    txt_namaPemohon.setValue(row.get('namabp'));
                    txt_nik.setValue(row.get('nik'));
                    txt_Perusahaan.setValue(row.get('namaperusahaan'));
                    hidden_kodeorg.setValue(row.get('kodeorg'));
                    hidden_batal.setValue(row.get('batal'));
                    hidden_kodebp.setValue(row.get('kodebp'));
                    hidden_totalBiaya.setValue(row.get('totalbiaya'));
                    txt_organisasi.setValue(row.get('namaorg'));
                    txt_namajabatan.setValue(row.get('namajabatan'));
                    txt_tglpengajuan.setValue(row.get('tanggal'));
                    
                    if (formMode=='INSERT' && txtBtn_kodePengajuan.getValue()!='')
                        {txt_tglpengajuan.disable();
                         txt_tglperubahan.enable();
                         txt_namaPemohon.setReadOnly(true);}
                    else if(formMode=='READ' && txtBtn_kodePengajuan.getValue()!='')
                        {txt_tglpengajuan.enable();txt_versi.enable();}
                   
                    if (row.get('IE')=='I'){
                        rb_jenistarif.setValue('I');
                    }
                    else if(row.get('IE')=='E'){
                        rb_jenistarif.setValue('E');
                    }
                    colModel_gridCostCenter.setEditable( 2, true);
                    var now = new Date();
                    var mon=now.getMonth();
                    var dateString = (mon+1) + "/" + now.getDate() + "/" + now.getFullYear();
                    txt_tglperubahan.setValue(dateString);
                    var StoreCostCenter=get_d_costCenterPengajuanStore(form_tperubahan.getComponent('formmode').getValue(),
                    SqlCustom_d_costcenterpengajuan.get_selectFilterByKodepengajuan(row.get('kodepengajuan')));
                    if (formMode=='INSERT')
                   {
                       txt_versi.setValue(parseInt(row.get('versi'))+1);
                       StoreCostCenter.on('load',function(){
                            for(i=0;i<StoreCostCenter.getTotalCount();i++){
                                gridCostCenter.getStore().insert(gridCostCenter.getStore().getCount(),
                            new d_costCenterPengajuanRecord({
                                kodepengajuan: txtBtn_kodePengajuan.getValue(),
                                kodecostcenter:StoreCostCenter.getAt(i).get('kodecostcenter'),
                                namacostcenter: StoreCostCenter.getAt(i).get('namacostcenter'),
                                userid:StoreCostCenter.getAt(i).get('userid'),
                                approval:'0',
                                presentase:StoreCostCenter.getAt(i).get('presentase'),
                                nominal:StoreCostCenter.getAt(i).get('nominal')
                                }));
                            }
                        });    
                   }
                    else if(formMode=='READ')
                   {
                     txt_versi.setValue(parseInt(row.get('versi')));      
                     gridAcc = form_tperubahan.getComponent('gridAcc');

                     gridCostCenter.reconfigure(StoreCostCenter,colModel_gridCostCenter);
                     generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_tperubahan,dataSubmit);
                     gridAcc.getColumnModel().setEditable(4,true);

                   }
                         
                    storeTglAwalAkhir=get_d_tglpengajuanStore(form_tperubahan.getComponent('formmode').getValue(),
                    SqlCustom_d_tglpengajuan.get_selectFilterByKodepengajuan(row.get('kodepengajuan')),false);
                    storeTglAwalAkhir.on('load',function(){
                        
                       gridTglAwalTglAkhir.getSelectionModel().selectFirstRow();
                       gridSDItemPerubahan.getStore().load();
                    });  
                    storeTglAwalAkhir.load();
                    gridTglAwalTglAkhir.reconfigure(storeTglAwalAkhir,colModel_gridTglAwalTglAkhir);
                    
                    
                    gridSDItemPerubahan.reconfigure(
                    get_sd_itempengajuanStore(form_tperubahan.getComponent('formmode').getValue(),SqlCustom_sd_itempengajuan.get_selectFilterByKodeTp(row.get('kodepengajuan')),false),colModel_gridSDItemPerubahan);     
                    gridSDItemPerubahan.getStore().on('load',function(){
                        
                        var sm = gridTglAwalTglAkhir.getSelectionModel();
                        var a = sm.getSelected();
                        gridSDItemPerubahan.getStore().filter('kodetp', a.get('kodetp'));
                    }); 
                    
                    //colModel_gridSDItemPerubahan.setEditable( 1, false)
                    
                    gridAcc = form_tperubahan.getComponent('gridAcc');
                    for(i=0;i<gridAcc.getStore().getCount();i++){
                        if(gridAcc.getStore().getAt(i).get('prioritas')==1){
                        var rec=gridAcc.getStore().getAt(i);
                        rec.set('namabp',txt_namaPemohon.getValue());
                        rec.set('namajabatan',txt_namajabatan.getValue());
                        rec.set('userid',row.get('userid'));
                        rec.set('kodejabatan',row.get('kodejabatan'));
                    }
                }
                    
                    listObjectInWIndowBrowse.extwindow.close();                   
                });
           };
   
   /*gridTglAwalTglAkhir.on('reconfigure',function(Grid, store, colModel){
       gridTglAwalTglAkhir.getSelectionModel().selectFirstRow();
   });        */
   txtBtn_item.onTriggerClick = function(e){
          var sm = gridSDItemPerubahan.getSelectionModel();
          var record_gridSDItemPerubahan = sm.getSelected();
          if (status==1)
               {return;}
           else if (status==0)
                {listObjectInWIndowBrowse = browseItem(null);status=1;}
          
          listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridSDItemPerubahan.set('kodeitem',row.get('kodeitem'));
                record_gridSDItemPerubahan.set('namaitem',row.get('namaitem'));
                record_gridSDItemPerubahan.set('kodejenis',row.get('kodejenis'));
                listObjectInWIndowBrowse.extwindow.close();status=0;
          });
          listObjectInWIndowBrowse.extwindow.on('close', function(p){
                status=0;
            });
      };
   
    
      
    var form_tperubahan = new Ext.FormPanel({
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
                items: [txtBtn_kodePengajuan,txt_jenis,
                        rb_jenistarif,
                        txt_namaPemohon,txt_nik,
                        txt_Perusahaan,txt_organisasi,txt_namajabatan,txt_tglpengajuan]
            },{
                columnWidth:.5,
                layout: 'form',
                items: [txt_tglperubahan,txt_versi,gridCostCenter]
            }]
         },
         {
            xtype:'fieldset',
            title: 'Detail view',
            layout:'column',
            anchor: '100%',
            collapsible: true,
            autoHeight:true,
            items :[gridTglAwalTglAkhir,gridSDItemPerubahan]            
         },hidden_kodebp,hidden_kodeorg,hidden_batal,hidden_IE,hidden_totalBiaya,
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
      var storeTglAwalAkhir;
      var dataSubmit = new DataSubmit('maincontroller', function(){
          //var params = Ext.urlDecode(form_tpengajuan.getForm().getValues(true));
          //params.arraycoba1 = [1,2,3,4,5];
          //params.arraycoba2 = [5,4,3,2,1];
          gridAcc = form_tperubahan.getComponent('gridAcc');
          //(arrayXmlSisipan,tableType,tableName,markModified,
          //onlyModified, prettyPrint, showType, indent,setHeaderXML)
          gridSDItemPerubahan.getStore().clearFilter();
          var xmlSdItemPengajuan = gridSDItemPerubahan.getStore().xmlSerialize(null,'subdetail',
                'sd_itempengajuan',false, false, false, false, 3,false);
          var xmlDTglAwalTglAkhir = gridTglAwalTglAkhir.getStore().xmlSerialize([xmlSdItemPengajuan],
                'detail','d_tglpengajuan',false, false, false, false, 2,false);
          var xmlDCostCenter = gridCostCenter.getStore().xmlSerialize(null,'detail','d_costcenterpengajuan',
                false, false, false, false, 0,false);
          var xmlDAcc=gridAcc.getStore().xmlSerialize(null,'detail','m_acc',false, false, false, false, 0,false);
          var xmlHTPengajuan = convertFormValues_toXMLString(form_tperubahan,'header','h_pengajuan',
                [xmlDCostCenter,xmlDTglAwalTglAkhir,xmlDAcc], false, 0,true);
          //console.log(xmlHTPengajuan);
          //params.xmldata = xmlHTPengajuan;
          var params = {
              formid:form_tperubahan.getComponent('formid').getValue(),
              formmode:form_tperubahan.getComponent('formmode').getValue(),
              xmldata : xmlHTPengajuan};
          /*
          var sm = gridTglAwalTglAkhir.getSelectionModel();
          var record = sm.getSelected();
          gridSDItemPengajuan.getStore().filter('kodetp', record.get('kodetp'));*/
          return params;
      });      
      var gridAcc = null;
      generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_tperubahan,dataSubmit);
      
      txtBtn_ApprovingCostCenter.onTriggerClick = function(e){
          var sm = gridCostCenter.getSelectionModel();
          var record_gridCostCenter = sm.getSelected();
          if (status==1)
               {return;}
          else if (status==0)
                {var window = showApproveWindow('READ',record_gridCostCenter.get('kodecostcenter'),record_gridCostCenter.get('presentase'),         
                 record_gridCostCenter.get('nominal'),record_gridCostCenter.get('userid'),record_gridCostCenter,form_tperubahan,dataSubmit);
                 status=1;}
          window.on('close', function(p){
                status=0;
            });
          window.buttons[1].on('click', function(e){
                status=0;
          });
      };
      txtBtn_datetime_jamawal.onTriggerClick = function(e){
           var sm = gridTglAwalTglAkhir.getSelectionModel();
           var record_gridTglAwalTglAkhir = sm.getSelected();
           sm = gridSDItemPerubahan.getSelectionModel();
           var record_gridSDItemPerubahan = sm.getSelected();
           if (status==1)
               {return;}
           else if (status==0)
               {var window= insertJamawalJamakhirItem(record_gridSDItemPerubahan.get('kodeitem'),hidden_kodebp.getValue(),
                record_gridSDItemPerubahan.get('jumlahawal'),record_gridSDItemPerubahan.get('jumlahakhir'),record_gridTglAwalTglAkhir.get('kodetp'),
                record_gridTglAwalTglAkhir.get('namatarif'),gridSDItemPerubahan,null,hidden_IE.getValue(),txt_jenis.getValue());               
                status=1;}
          window.on('close', function(p){
                status=0;
            });
          window.buttons[1].on('click', function(e){
                status=0;
          });
       };
    var customBtnCRUD = new CustomBtnCRUD (function(){
           gridAcc = form_tperubahan.getComponent('gridAcc');
           //generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_tperubahan,dataSubmit);
            
    } //beforeClickBaru
        ,function(){
            formMode='INSERT'
            
            txt_nik.disable();
            txt_Perusahaan.disable();
            txt_organisasi.disable();
            txt_namajabatan.disable();
            txt_tglperubahan.disable();
            btnBatalTrans.disable();
            colModel_gridCostCenter.setEditable( 2, true);
            txt_versi.setReadOnly(true);
            txt_jenis.setReadOnly(false);
            gridCostCenter.getStore().removeAll();
            gridTglAwalTglAkhir.getStore().removeAll();
            gridSDItemPerubahan.getStore().removeAll();
            txt_namaPemohon.setReadOnly(false);
            gridAcc.getColumnModel().setEditable(4,true);
            StorePemohon.load();
            StoreDefault.load();
        } //afterClickBaru
        ,function(){
            
            if(hidden_batal.getValue()==1){
                alert('Transaksi telah dibatalkan, tidak dapat diubah');
                return true;
            }
            if(txt_namaPemohon.getValue()=='')
                {
                    alert('Pilih Kode Pengajuan yang Ingin Diubah.');
                    return true;
                }
            if(txt_namaPemohon.getValue()!=StorePemohon.getAt(0).get('namabp')){
                    alert('Anda Tidak Memiliki Hak Akses');
                    return true;}
            var store = form_tperubahan.getComponent('gridAcc').getStore();
            //var noUpdate = false;
            for(i=0;i<store.getCount();i++){
                
                if( store.getAt(i).get('statusacc').toString()!='0'){
                    Ext.Msg.show({
                                   title: 'Warning!',
                                   msg: 'transaksi ini tidak bisa di ubah',
                                   icon: Ext.Msg.WARNING,
                                   buttons: Ext.MessageBox.OK
                                });
                    return true;
                }                    
            }
            /*
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
                
            }*/
            return false;
        } //beforeClickUbah
        ,function(){ //afterClickUbah
            //txtBtn_kodePengajuan.setReadOnly(true);
             
            var store = form_tperubahan.getComponent('gridAcc').getStore();
            var jumbelumacc = 0;
            for(i=0;i<store.getCount();i++){
                if( store.getAt(i).get('statusacc').toString()!='0'){
                    jumbelumacc = jumbelumacc+1;
                }  
            }
            txt_jenis.setReadOnly(true);
            txt_namaPemohon.disable(true);
            txt_nik.disable();
            txt_Perusahaan.disable();
            txt_organisasi.disable();
            txt_namajabatan.disable();
            rb_jenistarif.disable();
            txt_tglpengajuan.disable();
            txt_versi.setReadOnly(true);
            if(jumbelumacc<store.getCount() && jumbelumacc!=0){
           
                for(i=0;i<colModel_gridCostCenter.getColumnCount();i++){
                   colModel_gridCostCenter.setEditable( i, false);
                }
                for(i=0;i<colModel_gridSDItemPerubahan.getColumnCount();i++){
                   colModel_gridSDItemPerubahan.setEditable( i, false);
                }
                for(i=0;i<colModel_gridTglAwalTglAkhir.getColumnCount();i++){
                    colModel_gridTglAwalTglAkhir.setEditable( i, false);
                }
            }            
                      
        }
        ,function(){
            if(hidden_batal.getValue()==1){
                alert('Transaksi telah dibatalkan, tidak dapat dihapus');
                return true;
            }
            if(txt_namaPemohon.getValue()=='')
                {
                    alert('Pilih Kode Pengajuan yang Ingin Dihapus.');
                    return true;
                }
            if(txt_namaPemohon.getValue()!=StorePemohon.getAt(0).get('namabp')){
                    alert('Anda Tidak Memiliki Hak Akses');
                    return true;}
            var store = form_tperubahan.getComponent('gridAcc').getStore();
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
        ,function(success){ //afterClickHapus
            if(success){

            }else{

            }
        }
        ,function(){ //beforeClickSimpan
                txtBtn_kodePengajuan.setReadOnly(false);                
        }
        ,function(success){ //afterClickSimpan
            if(success){
                txtBtn_kodePengajuan.enable();
                formMode='READ'
                txt_namaPemohon.setValue('');
                txt_nik.setValue('');
                txt_namajabatan.setValue('');
                txt_Perusahaan.setValue('');
                txt_organisasi.setValue('');
                txtBtn_kodePengajuan.setValue('');
                txt_jenis.enable();
                gridAcc = form_tperubahan.getComponent('gridAcc');
                generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_tperubahan,dataSubmit);
          }else{
                    txtBtn_kodePengajuan.setReadOnly(true);
                    txt_jenis.setReadOnly(true);
                    
                    txt_versi.setReadOnly(true);
            }
        }
        ,function(){
            //console.log(form_tpengajuan.getForm().getValues());
            txtBtn_kodePengajuan.setReadOnly(false);
        } //beforeClickBatal
        ,function(){
            gridAcc = form_tperubahan.getComponent('gridAcc');
            generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_tperubahan,dataSubmit);
            formMode='READ';
            txtBtn_kodePengajuan.enable();
            if(storePermission.getAt(0).get('batal_trans')==1){
               btnBatalTrans.enable();
            }
           txt_jenis.enable();
           txt_jenis.setReadOnly(true);
           txt_namaPemohon.setReadOnly(false);
        } //afterClickBatal
        ,function(){} //beforeClickPrint
        ,function(){console.log(dataSubmit.getParams())} //afterClickPrint
      );
    var btnBatalTrans = new Ext.Button ({
              //xtype: 'button',
              text: 'BATAL TRANSAKSI',
              itemId :'btn_bataltrans',
              disabled : true
             });
    btnBatalTrans.on('click', function() {
                if(txtBtn_kodePengajuan.getValue()=='')
                    {
                       alert('Kode pengajuan harus diisi');
                       return;
                    }
               Ext.Msg.confirm('Konfirmasi!', 'Anda yakin ingin membatalkan?', function(btn, text){
                  if (btn == 'yes'){
                       hidden_batal.setValue(1);
                       form_tperubahan.getComponent('formmode').setValue("UPDATE");
                       form_tperubahan.getForm().getEl().mask('Batal Transaksi...','x-mask-loading');
                       Ext.Ajax.request({
                       url: dataSubmit.url,
                       params: dataSubmit.getParams(),
                       success: function(response,opt) {                        
                       var jsonHasil =  Ext.util.JSON.decode(
                        response.responseText
                        );
                        if (jsonHasil.success){
                          
                            form_tperubahan.getComponent('formmode').setValue("READ");
                            form_tperubahan.getForm().getEl().unmask();
                            Ext.Msg.show({
                               title: 'Success',
                               msg: jsonHasil.successmsg,
                               icon: Ext.Msg.INFO,
                               buttons: Ext.MessageBox.OK
                            });
                            gridAcc = form_tperubahan.getComponent('gridAcc');
                            generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_tperubahan,dataSubmit);
                            txt_namaPemohon.setValue('');
                            txt_nik.setValue('');
                            txt_namajabatan.setValue('');
                            txt_Perusahaan.setValue('');
                            txt_organisasi.setValue('');
                            txt_jenis.setValue('');
                            txtBtn_kodePengajuan.setValue('');
                            gridAcc = form_tperubahan.getComponent('gridAcc');
                            generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_tperubahan,dataSubmit);
            
                            colModel_gridCostCenter.setEditable( 2, true);
                            txt_versi.setReadOnly(true);
                            txt_jenis.setReadOnly(false);
                            gridCostCenter.getStore().removeAll();
                            gridTglAwalTglAkhir.getStore().removeAll();
                            gridSDItemPerubahan.getStore().removeAll();
                            gridAcc.getColumnModel().setEditable(4,true);
                        }else{
                            form_tperubahan.getForm().getEl().unmask();
                            Ext.Msg.show({
                               title: 'Error!',
                               msg: jsonHasil.errormsg,
                               icon: Ext.Msg.ERROR,
                               buttons: Ext.MessageBox.OK
                            });
               
                        }                                                
                     },
                     failure: function(response,opt) {
                         form_tperubahan.getForm().getEl().unmask();
                         Ext.Msg.alert('server-side failure with status code:', response.status);
                     }
                  });
                  }
                  else {
                        // abort, abort!
                  }
               });
               
                  
    });
    
    setCRUDFormButton(form_tperubahan,tpengajuan_xmlstore,null,customBtnCRUD,dataSubmit);
    form_tperubahan.getTopToolbar().addButton(btnBatalTrans);
    filterPermissionButtonForm(form_tperubahan,pview,padd,pupdate,pdelete,pprint,tpengajuan_xmlstore,batal_trans);
    
    return getTwoPanelVerticalBorderLayoout(form_tperubahan,null,ptitle,pformid);
}