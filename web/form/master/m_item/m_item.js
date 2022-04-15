/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
function captureEvents(observable) {
    Ext.util.Observable.capture(
        observable,
        function(eventName) {
            console.info(eventName);
        },
        this
    );
}*/

function getForm_mitem(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint){

    var namaTarifStore = new Ext.data.ArrayStore({
                   fields: ['id', 'tarif_name'],
                   data : [['',''],['kerja','Tarif Hari Kerja'],['libur','Tarif Hari Libur']]
                });
    function rendererTarif_name(val) {
                 return namaTarifStore.queryBy(function(rec){
                    return rec.data.id == val;
                  }).itemAt(0).get('tarif_name');
       }
   var jenisRealisasiStore = new Ext.data.ArrayStore({
                   fields: ['id', 'jenis_name'],
                   data : [['',''],['sewa ruang','Sewa Ruang'],['ijin lembur','Ijin Lembur']]
                });
    function rendererJenis_Realisasi(val) {
                 return jenisRealisasiStore.queryBy(function(rec){
                    return rec.data.id == val;
                  }).itemAt(0).get('jenis_name');
       }    
   var txtBtn_namaJenis = new Ext.form.TriggerField({
       itemId :'namajenis',
       fieldLabel: 'Nama Jenis',
       name: 'namajenis',
       value:'',
       disabled:true,
       allowBlank: false,
       submitValue:false,
       triggerClass: 'x-form-search-trigger',
       editable:false,
       width: 250
   });
   var hidden_kodejenis = new Ext.form.Hidden({
            itemId :'kodejenis',
            name: 'kodejenis',
            value:''
   });

   /*var txtBtn_namatarif = new Ext.form.TriggerField({
       itemId :'namatarif',
       name: 'namatarif',
       value:'',
       triggerClass: 'x-form-search-trigger'
   });*/
     
   var cmb_namatarif=new Ext.form.ComboBox({
                     typeAhead: true,
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: namaTarifStore,
                     valueField: 'id',
                     displayField: 'tarif_name'
                    });

  var cmb_namatarif2=new Ext.form.ComboBox({
                     typeAhead: true,
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: namaTarifStore,
                     valueField: 'id',
                     displayField: 'tarif_name'
                    });
                    
   var cmb_jenistarif=new Ext.form.ComboBox({
                     typeAhead: true,
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: jenisRealisasiStore,
                     valueField: 'id',
                     displayField: 'jenis_name'
                    });
                    
   var cmb_jenistarif2=new Ext.form.ComboBox({
                     typeAhead: true,
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: jenisRealisasiStore,
                     valueField: 'id',
                     displayField: 'jenis_name'
                    });  
                    
   var txt_number_grid = new Ext.form.NumberField({
        itemId :'numbergrid',
        name: 'numbergrid',
        value:0
    });

   var txt_number_grid2 = new Ext.form.NumberField({
        itemId :'numbergrid2',
        name: 'numbergrid2',
        value:0
    });

   var txtBtn_datetime = new Ext.ux.form.DateTimeField({
                dateFormat: 'd M Y',
                timeFormat: 'H:i',
                picker: {
                    timePicker: {
                        hourIncrement: 1,
                        minIncrement: 1
                    }
                }
   });
   
   var startTimePicker = new Ext.form.TimeField({
    minValue: '0:00 AM',
    maxValue: '11:00 PM',
    increment: 30,
    format: 'H:i'
  });
   startTimePicker.on('change',function(field, newValue, oldValue){
        //var j=parseInt(newValue.substr(0,2))+1;
        //var jam=j+":"+newValue.substr(3,2);
        //startTimePicker2.setMinValue(jam);
   });
   var startTimePicker2 = new Ext.form.TimeField({
    minValue: '0:00 AM',
    maxValue: '11:00 PM',
    increment: 30,
    format: 'H:i'
  });
   startTimePicker2.on('change',function(field, newValue, oldValue){
        var j=parseInt(newValue.substr(0,2),10);
        var m=newValue.substr(3,2);
        if (m=='00')
            {
                m='59';j=j-1;
            }
        else{m=m-1;}
        var jam=j+":"+m;
        startTimePicker2.setValue(jam);
   });
   var startTimePicker3 = new Ext.form.TimeField({
    minValue: '0:00 AM',
    maxValue: '11:00 PM',
    increment: 30,
    format: 'H:i'
  });
  startTimePicker3.on('change',function(field, newValue, oldValue){
        //var j=parseInt(newValue.substr(0,2))+1;
        //var jam=j+":"+newValue.substr(3,2);
        //startTimePicker4.setMinValue(jam);
   });
  var startTimePicker4 = new Ext.form.TimeField({
    minValue: '0:00 AM',
    maxValue: '11:00 PM',
    increment: 30,
    format: 'H:i'
  });
  startTimePicker4.on('change',function(field, newValue, oldValue){
      
        var j=parseInt(newValue.substr(0,2),10);
        var m=newValue.substr(3,2);
      
        if (m=='00')
            {
                m='59';j=j-1;
            }
        else{m=m-1;}
        var jam=j+":"+m;
        startTimePicker4.setValue(jam);
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

  var txt_kodeItem = new Ext.form.TextField({
        itemId :'kodeitem',
        fieldLabel: 'Kode Item',
        name: 'kodeitem',
        value:'',
        disabled:true,
        allowBlank: false
    });

   var colModel_gridTarifItemIn = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [
                      {header: "Kode Item", dataIndex: 'kodeitem'},
                      {header: "Jenis Transaksi", dataIndex: 'jenisrealisasi',
                         editor: cmb_jenistarif, renderer:rendererJenis_Realisasi},
                      {header: "Nama Tarif", dataIndex: 'namatarif',
                         editor: cmb_namatarif, renderer:rendererTarif_name},
                      {header: "Jam Awal", dataIndex: 'jamawal',
                          editor: startTimePicker/*,renderer: Ext.util.Format.dateRenderer( 'd M Y H:i')*/},
                      {header: "Jam Akhir", dataIndex: 'jamakhir',
                          editor: startTimePicker2/*,renderer: Ext.util.Format.dateRenderer( 'd M Y H:i')*/},
                      {header: "Biaya/jam", dataIndex: 'biaya',xtype: 'numbercolumn',
                        editor:txt_number_grid,align:'right'}
                    ]
                });
    var gridTarifItemIn = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 250,
                anchor:'95%',
                title:'Tarif Internal',
                //store: get_m_permissionStore(formMode,SqlCustom_m_permission.get_selectFilterByUserId('admin')),
                store: get_md_tarifItemStore(formMode,'',false),
                selModel: new Ext.grid.RowSelectionModel({singleSelect:true}),
                colModel: colModel_gridTarifItemIn,
                listeners: {
                    afteredit: function(e){
                       // e.record.commit();
                    }
                },
                tbar: [{
                  text: 'Add row',
                  handler: function(){
                     gridTarifItemIn.getStore().insert( gridTarifItemIn.getStore().getCount(),
                        new md_tarifItemRecord({
                            kodeitem: txt_kodeItem.getValue(),
                            namatarif:'',
                            jenisrealisasi:'',
                            jamawal  : '',
                            jamakhir : '',
                            biaya:0,
                            ie : 'I'
                         })
                      );
                    }
                  },{
                  text: 'Delete row',
                  handler: function(btn){
                    var sm = gridTarifItemIn.getSelectionModel();
                    sel = sm.getSelected();
                    gridTarifItemIn.getStore().remove(sel);
                  }
                }]
   });

   var colModel_gridTarifItemEx = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [
                      {header: "Kode Item", dataIndex: 'kodeitem'},
                      {header: "Jenis Transaksi", dataIndex: 'jenisrealisasi',
                         editor: cmb_jenistarif2, renderer:rendererJenis_Realisasi},
                      {header: "Nama Tarif", dataIndex: 'namatarif',
                         editor: cmb_namatarif2, renderer:rendererTarif_name},
                      {header: "Jam Awal", dataIndex: 'jamawal',editor: startTimePicker3/*,
                          renderer: Ext.util.Format.dateRenderer( 'd M Y H:i')*/},
                      {header: "Jam Akhir", dataIndex: 'jamakhir',editor: startTimePicker4/*,
                          renderer: Ext.util.Format.dateRenderer( 'd M Y H:i')*/},
                      {header: "Biaya/jam", dataIndex: 'biaya',xtype: 'numbercolumn',
                        editor:txt_number_grid2,align:'right'}
                    ]
                });

   var gridTarifItemEx = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 250,
                anchor:'95%',
                title:'Tarif Eksternal',
                //store: get_m_permissionStore(formMode,SqlCustom_m_permission.get_selectFilterByUserId('admin')),
                store: get_md_tarifItemStore(formMode,'',false),
                selModel: new Ext.grid.RowSelectionModel({singleSelect:true}),
                colModel: colModel_gridTarifItemEx,
                listeners: {
                    afteredit: function(e){
                       // e.record.commit();
                    }
                },
                tbar: [{
                  text: 'Add row',
                  handler: function(){
                     gridTarifItemEx.getStore().insert( gridTarifItemEx.getStore().getCount(),
                        new md_tarifItemRecord({
                            kodeitem: txt_kodeItem.getValue(),
                            namatarif:'',
                            jenisrealisasi:'',
                            jamawal  : '',
                            jamakhir : '',
                            biaya:0,
                            ie : 'E'
                         })
                      );
                    }
                  },{
                  text: 'Delete row',
                  handler: function(btn){
                    var sm = gridTarifItemEx.getSelectionModel();
                    sel = sm.getSelected();
                    gridTarifItemEx.getStore().remove(sel);
                  }
                }]
   });

   for(i=0;i<colModel_gridTarifItemIn.getColumnCount();i++){
       colModel_gridTarifItemIn.setEditable( i, false);
   }
   gridTarifItemIn.getTopToolbar().disable();

   for(i=0;i<colModel_gridTarifItemEx.getColumnCount();i++){
       colModel_gridTarifItemEx.setEditable( i, false);
   }
   gridTarifItemEx.getTopToolbar().disable();
   
   var txt_namaItem = new Ext.form.TextField({
         itemId :'namaitem',
         fieldLabel: 'Nama Item',
         name: 'namaitem',
         width: 250,
         value:'',
         disabled:true,
         allowBlank: false
    });
    
   var txt_durasiminsewa = new Ext.form.NumberField({
         itemId :'durasiminsewa',
         fieldLabel: 'Durasi Min. Sewa (menit)',
         name: 'durasiminsewa',
         //width: 50,
         value:'',
         disabled:true
    });
    
   var txt_kapasitas = new Ext.form.NumberField({
         itemId :'kapasitas',
         fieldLabel: 'Kapasitas (orang)',
         name: 'kapasitas',
         //width: 50,
         value:'',
         disabled:true
    });
    
   var txt_keterangan = new Ext.form.TextField({
         itemId :'keterangan',
         fieldLabel: 'Keterangan',
         name: 'keterangan',
         //width: 250,
         value:'',
         disabled:true
    });
    
   var txt_aktif = new Ext.form.Checkbox({
         itemId :'aktif',
         fieldLabel: 'Aktif',
         name: 'aktif',
         checked:true,
         disabled:true
    });
    
   var txt_statusavailability = new Ext.form.Checkbox({
         itemId :'statusavailability',
         fieldLabel: 'Limited Stock',
         name: 'statusavailability',
         checked:true,
         disabled:true
    });
   
   var txt_pricechangeable = new Ext.form.Checkbox({
         itemId :'pricechangeable',
         fieldLabel: 'Price Changeable',
         name: 'pricechangeable',
         disabled:true
    });
    
   var form_mitem = new Ext.FormPanel({
         url: 'maincontroller',
         region:'center',
         frame: true,
         autoScroll: true,
         tbar:[],
         bodyStyle:'padding:5px 5px 0',
         items: [{layout:'column',
                items:[{
                    columnWidth:.5,
                    layout: 'form', 
                    items:[
                        txt_kodeItem,txt_namaItem,hidden_kodejenis, txtBtn_namaJenis
                        ,txt_durasiminsewa
                        ]
                     },{
                     columnWidth:.5,
                     layout: 'form',
                     items:[txt_kapasitas,txt_keterangan,txt_aktif
                        ,txt_statusavailability,txt_pricechangeable]
                     }]
                },{
                xtype:'fieldset',
                title: 'Detail view',
                layout:'anchor',
                anchor: '100%',
                collapsible: true,
                autoHeight:true,
                items :[gridTarifItemIn,new Ext.Spacer({height:5}),gridTarifItemEx]
                },{
                 itemId :'formid',
                 xtype: 'hidden',
                 name: 'formid',
                 value:pformid
                },{
                 itemId :'formmode',
                 //id: 'form_mitem',
                 xtype: 'hidden',
                 name: 'formmode',
                 value:formMode
        }]
      });      

      txtBtn_namaJenis.onTriggerClick = function(e){
            if(txtBtn_namaJenis.disabled){
                return;
            }
            listObjectInWIndowBrowse = browseJenis(null);
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                hidden_kodejenis.setValue(row.get('kodejenis'));
                txtBtn_namaJenis.setValue(row.get('namajenis'));
                listObjectInWIndowBrowse.extwindow.close();
            });
       };

      /*txtBtn_namatarif.onTriggerClick = function(e){
            var sm = gridTarifItemIn.getSelectionModel();
            var record_gridTarifItemIn = sm.getSelected();
            listObjectInWIndowBrowse = browseNamaTarif(null);
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridTarifItemIn.set('namatarif',row.get('namatarif'));
                listObjectInWIndowBrowse.extwindow.close();
            });
       };*/

      var dataSubmit = new DataSubmit('maincontroller', function(){
          var xmlMdTarifItemIn = gridTarifItemIn.getStore().xmlSerialize(null,'detail',
                'md_tarifitem',false, false, false, false, 1,false);
          var xmlMdTarifItemEx = gridTarifItemEx.getStore().xmlSerialize(null,'detail',
                'md_tarifitem',false, false, false, false, 0,false);
          var xmlMItem = convertFormValues_toXMLString(form_mitem,'header','m_item',
                [xmlMdTarifItemIn,xmlMdTarifItemEx], false, 0,true);
          //console.log(xmlMItem);
          var params = {
              formid:form_mitem.getComponent('formid').getValue(),
              formmode:form_mitem.getComponent('formmode').getValue(),
              xmldata : xmlMItem};
          return params;
      });

      var customBtnCRUD = new CustomBtnCRUD (function(){} //beforeClickBaru
        ,function(){
            gridTarifItemIn.getStore().removeAll();
            gridTarifItemEx.getStore().removeAll();
            txt_kodeItem.setReadOnly(false);
        } //afterClickBaru
        ,function(){
            if(txt_kodeItem.getValue()=='')
                {
                    alert('Pilih Kode Item yang Ingin Diubah.');
                    return true;
                }
        } //beforeClickUbah
        ,function(){ //afterClickUbah
                txt_kodeItem.setReadOnly(true);
        }
        ,function(){
            if(txt_kodeItem.getValue()=='')
                {
                    alert('Pilih Kode Item yang Ingin Dihapus.');
                    return true;
                }
        } //beforeClickHapus
        ,function(success){ //afterClickHapus
            if(success){

            }else{

            }
        }
        ,function(){ //beforeClickSimpan
                txt_kodeItem.setReadOnly(false);
                if(txt_kodeItem.getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah kode item terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                else if(txt_namaItem.getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah nama item terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                else if(txtBtn_namaJenis.getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah nama jenis terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                return false;
        }
        ,function(success){ //afterClickSimpan
            if(success){
                gridTarifItemIn.reconfigure(
                    get_md_tarifItemStore(form_mitem.getComponent('formmode').getValue(),
                        SqlCustom_md_tarifItem.get_selectFilterByKodeItem(txt_kodeItem.getValue(),'I')
                    ),
                    colModel_gridTarifItemIn
                  );

              gridTarifItemEx.reconfigure(
                get_md_tarifItemStore(form_mitem.getComponent('formmode').getValue(),
                    SqlCustom_md_tarifItem.get_selectFilterByKodeItem(txt_kodeItem.getValue(),'E')
                ),
                colModel_gridTarifItemEx
              );
            }else{
                if (form_mitem.getComponent('formmode').getValue()=='UPDATE'){
                    txt_kodeItem.setReadOnly(true);
                }
            }
        }
        ,function(){
            console.log(form_mitem.getForm().getValues());
            hidden_kodejenis.setReadOnly(false);
        } //beforeClickBatal
        ,function(){} //afterClickBatal
        ,function(){} //beforeClickPrint
        ,function(){
            console.log(dataSubmit.getParams());
        } //afterClickPrint
      );
          
      /*var Item = Ext.data.Record.create([
            'kodeitem',
            'kodejenis',
            'namaitem',
            'durasiminsewa',
            'kapasitas',
            'keterangan',
            'aktif',
            'statusavailability',
            'pricechangeable'
        ]);
        var parameter = {
                formmode:'READ',
                tableoperation:'READ_ALL_ROW',
                formid:pformid
          };
        var xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:parameter,
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodeitem'
          }, Item),
          autoLoad: false
        });
        */

      var columnsheader= [{header: "Kode Item", dataIndex: 'kodeitem'},
          {header: "Nama Item", dataIndex: 'namaitem',width:200},
          {header: "Kode Jenis", dataIndex: 'kodejenis',width:100},
          {header: "Nama Jenis", dataIndex: 'namajenis',width:150},
          {header: "Durasi Min Sewa", dataIndex: 'durasiminsewa',align:'right'},
          {header: "Kapasitas", dataIndex: 'kapasitas',align:'right',width:80},
          {header: "Keterangan", dataIndex: 'keterangan',width:150},
          {header: "Aktif", dataIndex: 'aktif',xtype: 'checkcolumn',width:60},
          {header: "Status Availability", dataIndex: 'statusavailability',xtype: 'checkcolumn'},
          {header: "Price Changeable", dataIndex: 'pricechangeable',xtype: 'checkcolumn'}
      ];
      var grid = getGridView(ptitle, m_item_xmlstore,columnsheader);
      grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
          txt_kodeItem.setValue(row.get('kodeitem'));
          hidden_kodejenis.setValue(row.get('kodejenis'));
          txtBtn_namaJenis.setValue(row.get('namajenis'));
          txt_namaItem.setValue(row.get('namaitem'));
          txt_durasiminsewa.setValue(row.get('durasiminsewa'));
          txt_kapasitas.setValue(row.get('kapasitas'));
          txt_keterangan.setValue(row.get('keterangan'));
          txt_aktif.setValue(row.get('aktif'));
          txt_statusavailability.setValue(row.get('statusavailability'));
          txt_pricechangeable.setValue(row.get('pricechangeable'));

          gridTarifItemIn.reconfigure(
            get_md_tarifItemStore(form_mitem.getComponent('formmode').getValue(),
                SqlCustom_md_tarifItem.get_selectFilterByKodeItem(row.get('kodeitem'),'I'),true
            ),
            colModel_gridTarifItemIn
          );

          gridTarifItemEx.reconfigure(
            get_md_tarifItemStore(form_mitem.getComponent('formmode').getValue(),
                SqlCustom_md_tarifItem.get_selectFilterByKodeItem(row.get('kodeitem'),'E'),true
            ),
            colModel_gridTarifItemEx
          );
      });
     //   captureEvents(grid);
      setCRUDFormButton(form_mitem,m_item_xmlstore,grid,customBtnCRUD,dataSubmit);

      filterPermissionButtonForm(form_mitem,pview,padd,pupdate,pdelete,pprint,m_item_xmlstore);
      return getTwoPanelVerticalBorderLayoout(grid,form_mitem,ptitle,pformid);//form_mitem;
}

//(function(){
 //  return
/*   var coba1=     {
                            id:'node.attributes.text',
                            title: 'node.attributes.text',
                            html: 'Tab Body ' ,
                            closable:true
                        };
   tabPanel_viewport.add(coba1).show();*/
 //   })()
