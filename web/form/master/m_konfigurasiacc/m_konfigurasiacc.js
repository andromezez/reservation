/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function getForm_mkonfigurasiacc(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint){
   var txt_kodeAcc = new Ext.form.Hidden({
        itemId :'kodeacc',        
        name: 'kodeacc',
        value:''                
    });
   var status=0;
   var txtBtn_namaForm = new Ext.form.TriggerField({
       itemId :'namaform',
       fieldLabel: 'Nama Form',
       name: 'namaform',
       value:'',
       editable:false,
       disabled:true,
       allowBlank: false,
       triggerClass: 'x-form-search-trigger',
       width: 350
   });

   var hidden_kodeform = new Ext.form.Hidden({
            itemId :'kodeform',
            name: 'kodeform',
            value:''
   });

   var txtBtn_userid = new Ext.form.TriggerField({
       itemId :'userid',
       name: 'userid',
       value:'',
       triggerClass: 'x-form-search-trigger'
   });

   var txtBtn_kodejabatan = new Ext.form.TriggerField({
       itemId :'kodejabatan',
       name: 'kodejabatan',
       value:'',
       triggerClass: 'x-form-search-trigger'
   });
   
    var colModel_gridMdKonfigurasiAcc = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [
                      //{header: "Kode Acc", dataIndex: 'kodeacc'},
                      {header: "User ID", dataIndex: 'userid',
                         editor: txtBtn_userid},
                      {header: "Kode BP", dataIndex: 'kodebp'},
                      {header: "Nama BP", dataIndex: 'namabp',width:250},
                      {header: "Kode Jabatan", dataIndex: 'kodejabatan',
                        editor: txtBtn_kodejabatan},
                      {header: "Nama Jabatan", dataIndex: 'namajabatan',width:250},
                      {xtype: 'checkcolumn',header: 'Default Acc',
                        dataIndex: 'defaultacc',width:70}
                    ]
                });

   var gridMdKonfigurasiAcc = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 225,
                anchor:'95%',
                title:'Daftar User',
                store: get_md_konfigurasiaccStore(formMode),
                selModel: new Ext.grid.RowSelectionModel({singleSelect:true}),
                colModel: colModel_gridMdKonfigurasiAcc,
                listeners: {
                    afteredit: function(e){
                       // e.record.commit();
                    }
                },
                tbar: [{
                  text: 'Add row',
                  handler: function(){
                     gridMdKonfigurasiAcc.getStore().insert( gridMdKonfigurasiAcc.getStore().getCount(),
                        new md_konfigurasiaccRecord({
                            kodeacc:txt_kodeAcc.getValue(),
                            userid:'',
                            kodebp:'',
                            namabp:'',
                            kodejabatan:'',
                            namajabatan:'',
                            defaultacc:''
                         })
                      );
                    }
                  },{
                  text: 'Delete row',
                  handler: function(btn){
                    var sm = gridMdKonfigurasiAcc.getSelectionModel();
                    sel = sm.getSelected();
                    gridMdKonfigurasiAcc.getStore().remove(sel);
                  }
                }]
   });

   for(i=0;i<colModel_gridMdKonfigurasiAcc.getColumnCount();i++){
       colModel_gridMdKonfigurasiAcc.setEditable( i, false);
   }
   gridMdKonfigurasiAcc.getTopToolbar().disable();

   var form_mkonfigurasiacc = new Ext.FormPanel({
         url: 'maincontroller',
         region:'center',
         //closable:true,
         //title:ptitle,
        // id:pformid,
         frame: true,
         autoScroll: true,
         tbar:[],
         items: [txt_kodeAcc, hidden_kodeform, txtBtn_namaForm
                ,{
                 itemId :'prioritas',
                 xtype: 'numberfield',
                 fieldLabel: 'Prioritas',
                 name: 'prioritas',
                 allowBlank: false,
                 disabled:true,
                 width: 50
                },{
                 itemId :'keterangan',
                 xtype: 'textfield',
                 fieldLabel: 'Keterangan',
                 name: 'keterangan',
                 disabled:true,
                 width: 250
                },gridMdKonfigurasiAcc
                ,{
                itemId :'formid',
                xtype: 'hidden',
                 name: 'formid',
                 value:pformid
                },{
                 itemId :'formmode',
                 //id: 'form_mkonfigurasiacc',
                 xtype: 'hidden',
                 name: 'formmode',
                 value:formMode
                }
        ]
      });

      txtBtn_namaForm.onTriggerClick = function(e){
            if(txtBtn_namaForm.disabled){
                return;
            }
             if (status==1)
               {return;}
           else if (status==0)
                {listObjectInWIndowBrowse = browseFormNeedAcc(null);status=1;}
            
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                hidden_kodeform.setValue(row.get('kodeform'));
                txtBtn_namaForm.setValue(row.get('namaform'));
                listObjectInWIndowBrowse.extwindow.close();
                status=0;
            });
            listObjectInWIndowBrowse.extwindow.on('close', function(p){
                status=0;
            });
       };

      txtBtn_userid.onTriggerClick = function(e){
            var sm = gridMdKonfigurasiAcc.getSelectionModel();
            var record_gridMdKonfigurasiAcc = sm.getSelected();
           // browseCostCenter(/*null,null,null,*/record_vm_hris_costcenterRecord);
           if (status==1)
               {return;}
           else if (status==0)
                {listObjectInWIndowBrowse = browseUser(null);status=1;}
            
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridMdKonfigurasiAcc.set('userid',row.get('userid'));
                record_gridMdKonfigurasiAcc.set('kodebp',row.get('kodebp'));
                record_gridMdKonfigurasiAcc.set('namabp',row.get('namabp'));
                listObjectInWIndowBrowse.extwindow.close();
                status=0;
            });
            listObjectInWIndowBrowse.extwindow.on('close', function(p){
                status=0;
            });
       };

      txtBtn_kodejabatan.onTriggerClick = function(e){
            var sm = gridMdKonfigurasiAcc.getSelectionModel();
            var record_gridMdKonfigurasiAcc = sm.getSelected();
           // browseCostCenter(/*null,null,null,*/record_vm_hris_costcenterRecord);
           if (status==1)
               {return;}
           else if (status==0)
               {listObjectInWIndowBrowse = browseJabatanByKodeBp(customSelect_vm_hris_bpartnerLengkap_xmlstore(SqlCustom_vm_hris_bpartnerLengkap.get_selectFilterByKodeBp(record_gridMdKonfigurasiAcc.get('kodebp'))));
                status=1;}
            
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridMdKonfigurasiAcc.set('kodejabatan',row.get('kodejabatan'));
                record_gridMdKonfigurasiAcc.set('namajabatan',row.get('namajabatan'));
                listObjectInWIndowBrowse.extwindow.close();
                status=0;
            });
            listObjectInWIndowBrowse.extwindow.on('close', function(p){
                status=0;
            });
             
       };

      var dataSubmit = new DataSubmit('maincontroller', function(){
          var xmlMdKonfigurasiAcc = gridMdKonfigurasiAcc.getStore().xmlSerialize(null,'detail',
                'md_konfigurasiacc',false, false, false, false, 0,false);
          var xmlMKonfigurasiAcc = convertFormValues_toXMLString(form_mkonfigurasiacc,'header','m_konfigurasiacc',
                [xmlMdKonfigurasiAcc], false, 0,true);
          //console.log(xmlMKonfigurasiAcc);
          var params = {
              formid:form_mkonfigurasiacc.getComponent('formid').getValue(),
              formmode:form_mkonfigurasiacc.getComponent('formmode').getValue(),
              xmldata : xmlMKonfigurasiAcc};
          return params;
      });
      var columnsheader= [
          {header: "Kode Form", dataIndex: 'kodeform'},
          {header: "Nama Form", dataIndex: 'namaform'},
          {header: "Prioritas", dataIndex: 'prioritas',align:'right'},
          {header: "Keterangan", dataIndex: 'keterangan'}];
      var grid = getGridView(ptitle, mh_konfigurasiacc_xmlstore,columnsheader);
      grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
          txt_kodeAcc.setValue(row.get('kodeacc'));
          form_mkonfigurasiacc.getComponent('kodeform').setValue(row.get('kodeform'));
          txtBtn_namaForm.setValue(row.get('namaform'));
          form_mkonfigurasiacc.getComponent('prioritas').setValue(row.get('prioritas'));
          form_mkonfigurasiacc.getComponent('keterangan').setValue(row.get('keterangan'));

          gridMdKonfigurasiAcc.reconfigure(
            get_md_konfigurasiaccStore(form_mkonfigurasiacc.getComponent('formmode').getValue(),
                SqlCustom_md_konfigurasiacc.get_selectFilterByKodeAcc(row.get('kodeacc'))
            ),
            colModel_gridMdKonfigurasiAcc
          );
      });
      var customBtnCRUD = new CustomBtnCRUD (function(){} //beforeClickBaru
        ,function(){
            gridMdKonfigurasiAcc.getStore().removeAll();
            form_mkonfigurasiacc.getComponent('kodeform').setReadOnly(false); 
            if (grid.getStore().getCount()>0)
                {
                    var higestKodeAcc = parseInt(grid.getStore().getAt(0).get('kodeacc'));
                    for(i=1;i<grid.getStore().getCount();i++){
                        if(higestKodeAcc<parseInt(grid.getStore().getAt(i).get('kodeacc')))
                        higestKodeAcc = parseInt(grid.getStore().getAt(i).get('kodeacc'));
                        }
                    higestKodeAcc=higestKodeAcc+1;
                }
            else{higestKodeAcc=1}
            
            txt_kodeAcc.setValue(higestKodeAcc);
        } //afterClickBaru
        ,function(){
            if(txtBtn_namaForm.getValue()=='')
                {
                    alert('Pilih Form yang Ingin Diubah.');
                    return true;
                }
        } //beforeClickUbah
        ,function(){ //afterClickUbah
                //txt_kodeAcc.setReadOnly(true);
                
        }
        ,function(){
            if(txtBtn_namaForm.getValue()=='')
                {
                    alert('Pilih Form yang Ingin Dihapus.');
                    return true;
                }
        } //beforeClickHapus
        ,function(success){ //afterClickHapus
            if(success){

            }else{

            }
        }
        ,function(){ //beforeClickSimpan
                //txt_kodeAcc.setReadOnly(false);
                /*if(txt_kodeAcc.getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah kode acc terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }*/
                if(txtBtn_namaForm.getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah nama form terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                else if(form_mkonfigurasiacc.getComponent('prioritas').getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah prioritas terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                return false;
        }
        ,function(success){ //afterClickSimpan
            if(success){
                gridMdKonfigurasiAcc.reconfigure(
                    get_md_konfigurasiaccStore(form_mkonfigurasiacc.getComponent('formmode').getValue(),
                        SqlCustom_md_konfigurasiacc.get_selectFilterByKodeAcc(txt_kodeAcc.getValue())
                    ),
                    colModel_gridMdKonfigurasiAcc
                  );
              
            }else{
                if (form_mkonfigurasiacc.getComponent('formmode').getValue()=='UPDATE'){
                    //txt_kodeAcc.setReadOnly(true);
                }
            }
        }
        ,function(){
            //txt_kodeAcc.setReadOnly(false);
            console.log(form_mkonfigurasiacc.getForm().getValues());
            hidden_kodeform.setReadOnly(false);
        } //beforeClickBatal
        ,function(){} //afterClickBatal
        ,function(){} //beforeClickPrint
        ,function(){} //afterClickPrint
      );

     /* var KonfigurasiAcc = Ext.data.Record.create([
            'kodeacc',
            'kodeform',
            'prioritas',
            'keterangan'
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
            idPath:'kodeacc'
          }, KonfigurasiAcc),
          autoLoad: false
        });*/



      var columnsheader= [{header: "Kode Acc", dataIndex: 'kodeacc'},
          {header: "Kode Form", dataIndex: 'kodeform'},
          {header: "Nama Form", dataIndex: 'namaform',width:170},
          {header: "Prioritas", dataIndex: 'prioritas',align:'right'},
          {header: "Keterangan", dataIndex: 'keterangan'}];
      var grid = getGridView(ptitle, mh_konfigurasiacc_xmlstore,columnsheader);
      grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
          txt_kodeAcc.setValue(row.get('kodeacc'));
          form_mkonfigurasiacc.getComponent('kodeform').setValue(row.get('kodeform'));
          txtBtn_namaForm.setValue(row.get('namaform'));
          form_mkonfigurasiacc.getComponent('prioritas').setValue(row.get('prioritas'));
          form_mkonfigurasiacc.getComponent('keterangan').setValue(row.get('keterangan'));

          gridMdKonfigurasiAcc.reconfigure(
            get_md_konfigurasiaccStore(form_mkonfigurasiacc.getComponent('formmode').getValue(),
                SqlCustom_md_konfigurasiacc.get_selectFilterByKodeAcc(row.get('kodeacc'))
            ),
            colModel_gridMdKonfigurasiAcc
          );
      });

     //   captureEvents(grid);
      setCRUDFormButton(form_mkonfigurasiacc,mh_konfigurasiacc_xmlstore,grid,customBtnCRUD,dataSubmit);
      filterPermissionButtonForm(form_mkonfigurasiacc,pview,padd,pupdate,pdelete,pprint,mh_konfigurasiacc_xmlstore);
      return getTwoPanelVerticalBorderLayoout(grid,form_mkonfigurasiacc,ptitle,pformid);
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
