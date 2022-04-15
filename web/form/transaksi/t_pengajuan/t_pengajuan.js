/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function getForm_tpengajuan(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint){
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
    }
    var tglAwal;var tglAkhir;
    var cmb_jenisrealisasi = new Ext.form.ComboBox({
        //xtype: 'combo',
        hiddenName: 'jenisrealisasi',
                    //name: 'jenisrealisasi',
                    fieldLabel: 'Jenis',
                    mode: 'local',
                    store: genres,
                    displayField:'genre_name',
                    valueField:'id',
                    anchor:'95%',
                    value :'sewa ruang',
                    /*listeners: {
                        select: function(field, rec, selIndex){
                            if (selIndex == 0){
                                    Ext.Msg.prompt('New Genre', 'Name', Ext.emptyFn);
                            }
                            //console.log(form_tpengajuan.getForm().getValues());
                        }
                    },*/
                    disabled:true,
                    allowBlank: false
    });  /*  
    var txt_kodePengajuan = new Ext.form.TextField({
        itemId :'kodepengajuan',                     
                     fieldLabel: 'Kode Pengajuan',
                     name: 'kodepengajuan',
                     value:'',
                     disabled:true,
                     //allowBlank: false,
                     anchor:'95%'
    });*/
    var txtBtn_kodePengajuan = new Ext.form.TriggerField({
       itemId :'kodepengajuan',
       fieldLabel: 'Kode Pengajuan',
       name: 'kodepengajuan',
       value:'',
       disabled:false,
       allowBlank: true,
       //submitValue:false     ,
       triggerClass: 'x-form-search-trigger',
       anchor:'95%',
       editable:false
   });
    var txt_NIK = new Ext.form.TextField({
        itemId :'nikpemohon',
                     fieldLabel: 'NIK',
                     name: 'nikpemohon',
                     value:'',
                     disabled:true,
                     anchor:'95%'
    });
    var txt_perusahaan = new Ext.form.TextField({
        itemId :'namaperusahaan',
                     fieldLabel: 'Perusahaan',
                     name: 'namaperusahaan',
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
   var txtBtn_namaPemohon = new Ext.form.TriggerField({
       itemId :'namabp',
       fieldLabel: 'Nama Pemohon',
       name: 'namabp',
       value:'',
       disabled:true,
       allowBlank: false,
       submitValue:false     ,
       triggerClass: 'x-form-search-trigger',
       anchor:'95%'
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
   var hidden_versi= new Ext.form.Hidden({
            itemId :'versi',
            name: 'versi',
            value:''
         });
   var hidden_batal= new Ext.form.Hidden({
            itemId :'batal',
            name: 'batal',
            value:''
         });
   
   var txt_totalbiaya= new Ext.form.NumberField({
        itemId :'totalbiaya',
        fieldLabel: 'Total Biaya',
        name: 'totalbiaya',
        disabled:true,
        value:0,
        readOnly:true,
        anchor:'95%'
    });
   var tgl_transaksi = new Ext.form.DateField({
       itemId :'tgltransaksi',
       fieldLabel: 'Tgl. Transaksi',
       name: 'tgltransaksi',
       disabled:true,
       allowBlank: false,
       value:getCurrentDate(),
       anchor:'95%'
   });
   var txtBtn_costCenter = new Ext.form.TriggerField({
       itemId :'kodecostcenter',
       name: 'kodecostcenter',
       value:'',
       triggerClass: 'x-form-search-trigger',
       editable:false
   });
   var txtBtn_ApprovingCostCenter = new Ext.form.TriggerField({
       itemId :'approval',
       name: 'approval',
       value:'',
       triggerClass: 'x-form-search-trigger',
       editable:false
   });
   var txtBtn_userid = new Ext.form.TriggerField({
       itemId :'userid',
       name: 'userid',
       value:'',
       triggerClass: 'x-form-search-trigger',
       editable:false
   });
        
        
   /*var cmb_approval = new Ext.form.ComboBox({
        //xtype: 'combo',
        itemId:'approval',
        hiddenName: 'approval',        
        mode: 'local',
        store: approvStore,
        displayField:'approve_name',
        valueField:'id'
    });*/
    var txt_number_grid1 = new Ext.form.NumberField({
        itemId :'numbergrid1',
        name: 'numbergrid1',
        value:0
    });    
    var txt_number_grid2 = new Ext.form.NumberField({
        itemId :'numbergrid2',
        name: 'numbergrid2',
        value:0
    });
    var txt_number_jumlahawal = new Ext.form.NumberField({
        itemId :'numbergrid3',
        name: 'numbergrid3',
        value:0
    });
    var txt_number_grid4 = new Ext.form.NumberField({
        itemId :'numbergrid4',
        name: 'numbergrid4',
        readOnly:true,
        value:0
    });
    var txt_number_grid5 = new Ext.form.NumberField({
        itemId :'numbergrid5',
        name: 'numbergrid5',
        readOnly:true,
        value:0
    });    
   var colModel_gridCostCenter = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [                      
                      {header: "Cost Center", dataIndex: arrayField_d_costcenterpengajuan[1],
                         editor: txtBtn_costCenter},
                      {header: "User Approval", dataIndex: arrayField_d_costcenterpengajuan[3],
                         editor: txtBtn_userid},
                      {header: "Status", dataIndex: arrayField_d_costcenterpengajuan[7],
                         editor: txtBtn_ApprovingCostCenter/*cmb_approval*/,renderer:rendererApprove_name},
                      {header: "Presentase", dataIndex: arrayField_d_costcenterpengajuan[8], xtype: 'numbercolumn',
                        editor:txt_number_grid1},
                      {header: "Nominal", dataIndex: arrayField_d_costcenterpengajuan[9], xtype: 'numbercolumn',format: '0',editor:txt_number_grid2}
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
                       // e.record.commit();
                    }
                },
                tbar: [{
                  text: 'Add row',
                  handler: function(){                    
                     gridCostCenter.getStore().insert( gridCostCenter.getStore().getCount(),
                        new d_costCenterPengajuanRecord({
                            kodepengajuan:txtBtn_kodePengajuan.getValue(),
                            kodecostcenter:StorePemohon.getAt(0).get('kodecostcenter'),
                            userid:'',
                            approval:'0',
                            presentase:100,
                            nominal:0
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

   var colModel_gridTglAwalTglAkhir = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [                      
                      {header: "Tanggal", dataIndex: arrayField_d_tglpengajuanStore[2],
                         editor: new Ext.form.DateField({
                            itemId :'TglAwal',
                            name: 'TglAwal'
                         }),renderer: Ext.util.Format.dateRenderer( 'd M Y')},
                      // {header: "Tgl Akhir", dataIndex: 'tglakhir',renderer: Ext.util.Format.dateRenderer( 'd M Y')},
                      {header: "Jenis Hari", dataIndex: 'namatarif'}
                    ]
                });
   var gridTglAwalTglAkhir = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 160,
                width:210,
                itemId:'gridTglAwalTglAkhir',
                store: get_d_tglpengajuanStore(formMode),
                selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
                colModel: colModel_gridTglAwalTglAkhir,
                tbar: [{
                  text: 'Add row',
                  handler: function(){                                   
                     gridTglAwalTglAkhir.getStore().insert( gridTglAwalTglAkhir.getStore().getCount(),
                        new d_tglPengajuanRecord({
                            kodetp:Math.floor(Math.random()*11),
                            kodepengajuan:txtBtn_kodePengajuan.getValue(),
                            tglawal:getCurrentDate(),
                            tglakhir:getCurrentDate()
                         })
                      );                      
                    }
                  },{
                  text: 'Delete row',
                  handler: function(btn){
                    var sm = gridTglAwalTglAkhir.getSelectionModel();
                    sel = sm.getSelected();
                    gridTglAwalTglAkhir.getStore().remove(sel);
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
   
   var txtBtn_item = new Ext.form.TriggerField({
       itemId :'namaitem',
       name: 'namaitem',
       value:'',
       triggerClass: 'x-form-search-trigger'
   });
   /*var txtBtn_datetime = new Ext.ux.form.DateTimeField({
                dateFormat: 'd M Y',
                timeFormat: 'H:i',
                picker: {
                    timePicker: {
                        hourIncrement: 1,
                        minIncrement: 1
                    }
                }
   });*/
   var txtBtn_datetime_jamawal = new Ext.form.TriggerField({
       itemId :'jamawal',       
       name: 'jamawal',
       value:'',                     
       triggerClass: 'x-form-search-trigger'   ,
       editable:false
   });
   
   var colModel_gridSDItemPengajuan = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [
                      {header: "Ruang dan Perlengkapan", dataIndex: 'namaitem' ,
                         editor: txtBtn_item},
                      {header: "Jumlah", dataIndex: 'jumlahakhir',
                         editor: txt_number_jumlahawal, xtype: 'numbercolumn',format: '0'},
                     {header: "Jam Awal", dataIndex: 'jamawal',
                         editor: txtBtn_datetime_jamawal, /*renderer: Ext.util.Format.dateRenderer( 'd M Y H:i')*/type: 'date', dateFormat: 'H:i'},
                      {header: "Jam Akhir", dataIndex: 'jamakhir',
                         /*renderer: Ext.util.Format.dateRenderer( 'd M Y H:i')*/type: 'date', dateFormat: 'H:i'},
                      {header: "Durasi", dataIndex: 'durasi',
                         editor: txt_number_grid4,renderer:rendererApprove_name,xtype: 'numbercolumn',format: '0'},
                      {header: "Biaya perjam", dataIndex: 'biayaperjam', xtype: 'numbercolumn'},
                      {header: "Biaya", dataIndex: 'biaya', xtype: 'numbercolumn',
                        editor:txt_number_grid5},
                      {header: "Antrian", dataIndex:'antrian', xtype: 'numbercolumn',format: '0'},                      
                      {header: "Keterangan", dataIndex: 'keterangan',
                         editor: new Ext.form.TextField({value:''})}
                    ]
                });
   var gridSDItemPengajuan = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 160,
                //anchor:'100%',
                columnWidth:1,
                itemId:'gridSDItemPengajuan',
                store: get_sd_itempengajuanStore(formMode),
                selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
                colModel: colModel_gridSDItemPengajuan,
                tbar: [{
                  text: 'Add row',
                  handler: function(){                     
                     gridSDItemPengajuan.getStore().insert( gridSDItemPengajuan.getStore().getCount(),
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
                    var sm = gridSDItemPengajuan.getSelectionModel();
                    sel = sm.getSelected();
                    gridSDItemPengajuan.getStore().remove(sel);
                  }
                }],
            listeners: {
                afteredit: function(e){
                  if (e.field == 'jumlahakhir'){
                    //var sm = gridSDItemPengajuan.getSelectionModel();
                    var record_gridSDItemPengajuan = e.record;//sm.getSelected();
                    e.record.set('jumlahawal',e.value);  
                    //console.log(record_gridSDItemPengajuan);
                    if ((record_gridSDItemPengajuan.get('jamawal') != null) && (record_gridSDItemPengajuan.get('jamakhir') != null)
                        && (record_gridSDItemPengajuan.get('jumlahakhir') != null)  && (record_gridSDItemPengajuan.get('kodeitem') != null)
                        && (hidden_kodebp.getValue()!='')){
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
                    }                  
                  }
                }
              }
   });
  
   for(i=0;i<colModel_gridSDItemPengajuan.getColumnCount();i++){
       colModel_gridSDItemPengajuan.setEditable( i, false);
   }
   gridSDItemPengajuan.getTopToolbar().disable();   
   var form_tpengajuan = new Ext.FormPanel({
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
                items: [txtBtn_kodePengajuan,tgl_transaksi,cmb_jenisrealisasi,txtBtn_namaPemohon,
                        txt_NIK,txt_perusahaan,txt_organisasi,txt_totalbiaya]
            },{
                columnWidth:.5,
                layout: 'form',
                items: [gridCostCenter]
            }]
         },
         {
            xtype:'fieldset',
            title: 'Detail view',
            layout:'column',
            anchor: '100%',
            collapsible: true,
            autoHeight:true,            
            items :[gridTglAwalTglAkhir,gridSDItemPengajuan]
         },hidden_kodebp,hidden_kodeorg,hidden_versi,hidden_batal,
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
      gridTglAwalTglAkhir.getSelectionModel().on(
        'rowselect',function(sm, rowIndex, record){
            gridSDItemPengajuan.getStore().filter('kodetp', record.get('kodetp'));
        }
      );
      txtBtn_ApprovingCostCenter.onTriggerClick = function(e){
          var sm = gridCostCenter.getSelectionModel();
          var record_gridCostCenter = sm.getSelected();                          
          showApproveWindow('READ',record_gridCostCenter.get('kodecostcenter'),record_gridCostCenter.get('presentase'),
          record_gridCostCenter.get('nominal'),record_gridCostCenter.get('userid'),record_gridCostCenter);
      };     
      txtBtn_item.onTriggerClick = function(e){
          var sm = gridSDItemPengajuan.getSelectionModel();
          var record_gridSDItemPengajuan = sm.getSelected();
          listObjectInWIndowBrowse = browseItem(null);
          listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridSDItemPengajuan.set('kodeitem',row.get('kodeitem'));
                record_gridSDItemPengajuan.set('namaitem',row.get('namaitem'));
                listObjectInWIndowBrowse.extwindow.close();
          });
      };
      txtBtn_namaPemohon.onTriggerClick = function(e){
            //var record_vm_hris_bpartnerLengkapRecord = Ext.data.Record.create(arrayField_vm_hris_bpartnerLengkap);
           /* browseBPartneLengkap(hidden_kodebp, txt_NIK, txtBtn_namaPemohon, null, txt_perusahaan,
                hidden_kodeorg, txt_organisasi, null, null,null,null,record_vm_hris_bpartnerLengkapRecord);*/
            if(txtBtn_namaPemohon.disabled){
                return;
            }
            var sql=SqlCustom_vm_hris_bpartnerLengkap.selectKaryawanPemohon();
            listObjectInWIndowBrowse = browseBPartneLengkap(customSelect_vm_hris_bpartnerLengkap_xmlstore(SqlCustom_vm_hris_bpartnerLengkap.selectKaryawanPemohon()),sql);            
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);                
                hidden_kodebp.setValue(row.get('kodebp'));
                txt_NIK.setValue(row.get('nik'));
                txtBtn_namaPemohon.setValue(row.get('namabp'));
                txt_perusahaan.setValue(row.get('namaperusahaan'));
                hidden_kodeorg.setValue(row.get('kodeorg'));
                txt_organisasi.setValue(row.get('namaorg'));
                listObjectInWIndowBrowse.extwindow.close();
            });            
       };
       txtBtn_costCenter.onTriggerClick = function(e){
            var sm = gridCostCenter.getSelectionModel();
            var record_gridCostCenter = sm.getSelected();
           // browseCostCenter(/*null,null,null,*/record_vm_hris_costcenterRecord);
            listObjectInWIndowBrowse = browseCostCenter(null);            
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridCostCenter.set('kodecostcenter',row.get('kodecostcenter'));
                record_gridCostCenter.set('userid','');
                record_gridCostCenter.set('approval','0');
                listObjectInWIndowBrowse.extwindow.close();
            });
       };
       txtBtn_userid.onTriggerClick = function(e){
            var sm = gridCostCenter.getSelectionModel();
            var record_gridCostCenter = sm.getSelected();

            listObjectInWIndowBrowse = browseBPartneLengkap(
                customSelect_vm_hris_bpartnerLengkap_xmlstore (
                    (SqlCustom_vm_hris_bpartnerLengkap.selectFilterByCostCenter).replace('filterCostCenter', record_gridCostCenter.get('kodecostcenter'))
                )
            );
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridCostCenter.set('userid',row.get('userid'));
                record_gridCostCenter.set('approval','0');
                listObjectInWIndowBrowse.extwindow.close();
            });
            
       };

       txtBtn_datetime_jamawal.onTriggerClick = function(e){
           var sm = gridTglAwalTglAkhir.getSelectionModel();
           var record_gridTglAwalTglAkhir = sm.getSelected();                                            
           sm = gridSDItemPengajuan.getSelectionModel();
           var record_gridSDItemPengajuan = sm.getSelected();
           insertJamawalJamakhirItem(record_gridSDItemPengajuan.get('kodeitem'),hidden_kodebp.getValue(),
                record_gridSDItemPengajuan.get('jumlahawal'),record_gridSDItemPengajuan.get('jumlahakhir'),record_gridTglAwalTglAkhir.get('kodetp'),
                record_gridTglAwalTglAkhir.get('namatarif'),gridSDItemPengajuan);               
       };
       
       var tglStore=get_m_parameter("READ",SqlCustom_m_parameter.get_selectFilterByParName(),false);
       tglStore.on('load',function(){
                  tglAwal=tglStore.getAt(0).get('parvalue');
                  tglAkhir=tglStore.getAt(0).get('partype');
        });
        tglStore.load();

       
       txtBtn_kodePengajuan.onTriggerClick = function(e){
           if(txtBtn_kodePengajuan.disabled){
                return;
            }
            var sqlString=SqlCustom_t_pengajuan.get_selectPengajuan(tglAwal, tglAkhir);
            listObjectInWIndowBrowse = browseHPengajuan(get_t_pengajuanStore('READ',SqlCustom_t_pengajuan.get_selectPengajuan(tglAwal,tglAkhir)),formMode,tglAwal,tglAkhir,sqlString);
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);                
          txtBtn_kodePengajuan.setValue(row.get('kodepengajuan'));
          tgl_transaksi.setValue(row.get('tanggal'));
          cmb_jenisrealisasi.setValue(row.get('jenisrealisasi'));
          txtBtn_namaPemohon.setValue(row.get('namabp'));
          txt_NIK.setValue(row.get('nik'));
          txt_organisasi.setValue(row.get('namaorg'));
          txt_totalbiaya.setValue(row.get('totalbiaya'));
          hidden_kodebp.setValue(row.get('kodebp'));
          hidden_kodeorg.setValue(row.get('kodeorg'));
          hidden_versi.setValue(row.get('versi'));
          hidden_batal.setValue(row.get('batal'));         
          gridCostCenter.reconfigure(
            get_d_costCenterPengajuanStore(form_tpengajuan.getComponent('formmode').getValue(),
                SqlCustom_d_costcenterpengajuan.get_selectFilterByKodepengajuan(row.get('kodepengajuan'))
            ),
            colModel_gridCostCenter
          );          
          gridTglAwalTglAkhir.reconfigure(
            get_d_tglpengajuanStore(form_tpengajuan.getComponent('formmode').getValue(),
                SqlCustom_d_tglpengajuan.get_selectFilterByKodepengajuan(row.get('kodepengajuan'))
            ),
            colModel_gridTglAwalTglAkhir
          );
          gridSDItemPengajuan.reconfigure(
            get_sd_itempengajuanStore(form_tpengajuan.getComponent('formmode').getValue(),
                SqlCustom_sd_itempengajuan.get_selectFilterByKodeTp(row.get('kodepengajuan'))
            ),
            colModel_gridSDItemPengajuan
          );
          gridAcc = form_tpengajuan.getComponent('gridAcc');
          generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_tpengajuan);
                listObjectInWIndowBrowse.extwindow.close();
            }); 
       }
       
      var StorePemohon=customSelect_vm_hris_bpartnerLengkap_xmlstore(SqlCustom_vm_hris_bpartnerLengkap.selectKaryawanFilterByUserId(useridsession));
      StorePemohon.on('load',function(){
                  txtBtn_namaPemohon.setValue(StorePemohon.getAt(0).get('namabp'));
                  txt_NIK.setValue(StorePemohon.getAt(0).get('nik'));
                  txt_perusahaan.setValue(StorePemohon.getAt(0).get('namaperusahaan'));
                  txt_organisasi.setValue(StorePemohon.getAt(0).get('namaorg'));
        });
      var gridAcc = null;
      generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_tpengajuan);
      var dataSubmit = new DataSubmit('maincontroller', function(){
          //var params = Ext.urlDecode(form_tpengajuan.getForm().getValues(true));
          //params.arraycoba1 = [1,2,3,4,5];
          //params.arraycoba2 = [5,4,3,2,1];
          gridAcc = form_tpengajuan.getComponent('gridAcc');
          //(arrayXmlSisipan,tableType,tableName,markModified, 
          //onlyModified, prettyPrint, showType, indent,setHeaderXML) 
          gridSDItemPengajuan.getStore().clearFilter(); 
          var xmlSdItemPengajuan = gridSDItemPengajuan.getStore().xmlSerialize(null,'subdetail',
                'sd_itempengajuan',false, false, false, false, 3,false);
          var xmlDTglAwalTglAkhir = gridTglAwalTglAkhir.getStore().xmlSerialize([xmlSdItemPengajuan],
                'detail','d_tglpengajuan',false, false, false, false, 2,false);
          var xmlDCostCenter = gridCostCenter.getStore().xmlSerialize(null,'detail','d_costcenterpengajuan',
                false, false, false, false, 0,false);
          var xmlDAcc=gridAcc.getStore().xmlSerialize(null,'detail','m_acc',false, false, false, false, 0,false);
          var xmlHTPengajuan = convertFormValues_toXMLString(form_tpengajuan,'header','h_pengajuan',
                [xmlDCostCenter,xmlDTglAwalTglAkhir,xmlDAcc], false, 0,true);
          
          var params = {
              formid:form_tpengajuan.getComponent('formid').getValue(),
              formmode:form_tpengajuan.getComponent('formmode').getValue(),
              xmldata : xmlHTPengajuan};      
          /*var sm = gridTglAwalTglAkhir.getSelectionModel();
          var record = sm.getSelected();          
          if (typeof record != "undefined"){
            gridSDItemPengajuan.getStore().filter('kodetp', record.get('kodetp'));
          }*/
          return params;
      });
      var customBtnCRUD = new CustomBtnCRUD (function(){} //beforeClickBaru
        ,function(){
            
            txtBtn_kodePengajuan.setValue('');
            txtBtn_kodePengajuan.setReadOnly(true);            
            txt_NIK.disable();
            txt_perusahaan.disable();
            txt_organisasi.disable();
            StorePemohon.load();
            /*gridCostCenter.reconfigure(
                get_d_costCenterPengajuanStore(form_tpengajuan.getComponent('formmode').getValue(),SqlCustom_d_costcenterpengajuan.get_selectBlankRow()),
                    colModel_gridCostCenter
            );
            gridTglAwalTglAkhir.reconfigure(
                get_d_tglpengajuanStore(form_tpengajuan.getComponent('formmode').getValue(),SqlCustom_d_tglpengajuan.get_selectBlankRow()),
                    colModel_gridTglAwalTglAkhir
            );*/
            gridCostCenter.getStore().removeAll();
            gridTglAwalTglAkhir.getStore().removeAll();
            gridSDItemPengajuan.getStore().removeAll();
            gridAcc = form_tpengajuan.getComponent('gridAcc');
            generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_tpengajuan);            
            
        } //afterClickBaru
        ,function(){   
            var store = form_tpengajuan.getComponent('gridAcc').getStore();
            var noUpdate = false;
            for(i=0;i<store.getCount();i++){
                if( store.getAt(i).get('userid').toString()!=''){
                    noUpdate = true;
                    break;
                }                    
            }
            if(noUpdate){  
                var jumbelumacc = 0;
                for(i=0;i<store.getCount();i++){
                    if( store.getAt(i).get('userid').toString()!=''){
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
            /*var store = form_tpengajuan.getComponent('gridAcc').getStore();
            var noUpdate = false;
            for(i=0;i<store.getCount();i++){
                if( store.getAt(i).get('userid').toString()!=''){
                    noUpdate = true;
                    break;
                }                    
            }
            if(noUpdate){                
                 Ext.Msg.show({
                               title: 'Warning!',
                               msg: 'transaksi ini tidak bisa di ubah',
                               icon: Ext.Msg.WARNING,
                               buttons: Ext.MessageBox.OK
                            });                
                 return true;
            }
            return false;*/
            
        } //beforeClickUbah
        ,function(){ //afterClickUbah
            var store = form_tpengajuan.getComponent('gridAcc').getStore();
            var jumbelumacc = 0;
            for(i=0;i<store.getCount();i++){
                if( store.getAt(i).get('userid').toString()!=''){
                    jumbelumacc = jumbelumacc+1;
                }  
            }
            if(jumbelumacc<store.getCount() && jumbelumacc!=0){
                tgl_transaksi.disable();
                cmb_jenisrealisasi.disable();
                txtBtn_namaPemohon.disable();
                txt_NIK.disable();
                txt_perusahaan.disable();
                txt_organisasi.disable();
                txt_totalbiaya.disable();
                for(i=0;i<colModel_gridCostCenter.getColumnCount();i++){
                   colModel_gridCostCenter.setEditable( i, false);
                }
                for(i=0;i<colModel_gridSDItemPengajuan.getColumnCount();i++){
                   colModel_gridSDItemPengajuan.setEditable( i, false);
                }
                for(i=0;i<colModel_gridTglAwalTglAkhir.getColumnCount();i++){
                    colModel_gridTglAwalTglAkhir.setEditable( i, false);
                }
            }
            txtBtn_kodePengajuan.setReadOnly(true);
            txt_NIK.disable();
            txt_perusahaan.disable();
            txt_organisasi.disable();   
        }
        ,function(){
            var store = form_tpengajuan.getComponent('gridAcc').getStore();
            var noUpdate = false;
            for(i=0;i<store.getCount();i++){
                if( store.getAt(i).get('userid').toString()!=''){
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
                
                tgl_transaksi.enable();
                cmb_jenisrealisasi.enable();
                txtBtn_namaPemohon.enable();
                txt_NIK.enable();
                txt_perusahaan.enable();
                txt_organisasi.enable();
                txt_totalbiaya.enable();
                
                
        }
        ,function(success){ //afterClickSimpan
            if(success){
                txtBtn_kodePengajuan.enable();
            }else{
                    txtBtn_kodePengajuan.setReadOnly(true);
            }
        }
        ,function(){
            //console.log(form_tpengajuan.getForm().getValues());
            txtBtn_kodePengajuan.setReadOnly(false);
            
        } //beforeClickBatal
        ,function(){
            txtBtn_kodePengajuan.enable();
        } //afterClickBatal
        ,function(){} //beforeClickPrint
        ,function(){console.log(dataSubmit.getParams())} //afterClickPrint
      );

       
        
        

     /*
      grid.on('rowdblclick', function(grid, rowIndex, e) {
         // var row = grid.getStore().getAt(rowIndex);
          //form_tpengajuan.getComponent('kodejenis').setValue(row.get('kodejenis'));
          //form_tpengajuan.getComponent('namajenis').setValue(row.get('namajenis'));
          var record_grid_h_pengajuan = grid.getStore().getAt(rowIndex);
          txtBtn_kodePengajuan.setValue(record_grid_h_pengajuan.get('kodepengajuan'));
          tgl_transaksi.setValue(record_grid_h_pengajuan.get('tanggal'));
          cmb_jenisrealisasi.setValue(record_grid_h_pengajuan.get('jenisrealisasi'));
          txtBtn_namaPemohon.setValue(record_grid_h_pengajuan.get('namabp'));
          txt_NIK.setValue(record_grid_h_pengajuan.get('nik'));
          txt_organisasi.setValue(record_grid_h_pengajuan.get('namaorg'));
          txt_totalbiaya.setValue(record_grid_h_pengajuan.get('totalbiaya'));
          hidden_kodebp.setValue(record_grid_h_pengajuan.get('kodebp'));
          hidden_kodeorg.setValue(record_grid_h_pengajuan.get('kodeorg'));
          hidden_versi.setValue(record_grid_h_pengajuan.get('versi'));
          hidden_batal.setValue(record_grid_h_pengajuan.get('batal'));         
          gridCostCenter.reconfigure(
            get_d_costCenterPengajuanStore(form_tpengajuan.getComponent('formmode').getValue(),
                SqlCustom_d_costcenterpengajuan.get_selectFilterByKodepengajuan(record_grid_h_pengajuan.get('kodepengajuan'))
            ),
            colModel_gridCostCenter
          );          
          gridTglAwalTglAkhir.reconfigure(
            get_d_tglpengajuanStore(form_tpengajuan.getComponent('formmode').getValue(),
                SqlCustom_d_tglpengajuan.get_selectFilterByKodepengajuan(record_grid_h_pengajuan.get('kodepengajuan'))
            ),
            colModel_gridTglAwalTglAkhir
          );
          gridSDItemPengajuan.reconfigure(
            get_sd_itempengajuanStore(form_tpengajuan.getComponent('formmode').getValue(),
                SqlCustom_sd_itempengajuan.get_selectFilterByKodeTp(record_grid_h_pengajuan.get('kodepengajuan'))
            ),
            colModel_gridSDItemPengajuan
          );
          gridAcc = form_tpengajuan.getComponent('gridAcc');
          generate_accTable(formMode,pformid,ptitle,txtBtn_kodePengajuan.getValue(),gridAcc,form_tpengajuan);
      });
     //   captureEvents(grid);
     */
      setCRUDFormButton(form_tpengajuan,tpengajuan_xmlstore,null,customBtnCRUD,dataSubmit);

      filterPermissionButtonForm(form_tpengajuan,pview,padd,pupdate,pdelete,pprint,tpengajuan_xmlstore);
      return getTwoPanelVerticalBorderLayoout(form_tpengajuan,null,ptitle,pformid);//form_tpengajuan;
}
