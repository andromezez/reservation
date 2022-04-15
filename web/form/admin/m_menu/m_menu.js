/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getForm_mmenu(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint){
   var txtBtn_kodeParent = new Ext.form.TriggerField({
       itemId :'kodeparent',
       fieldLabel: 'Kode Parent',
       name: 'kodeparent',
       value:'',
       disabled:true,
       editable:false,
       triggerClass: 'x-form-search-trigger',
       width: 250
   });
   var form_mmenu = new Ext.FormPanel({
         url: 'maincontroller',
         region:'center',
         //closable:true,
        // renderTo: Ext.get('m_menuForm'),//Ext.getBody(),
         //title:ptitle,
         //id:pformid,
         frame: true,
         autoScroll: true,
         tbar: [],
         items: [{
                 itemId :'kodeform',
                 xtype: 'textfield',
                 fieldLabel: 'Kode Form',
                 name: 'kodeform',
                 width: 200,
                 disabled:true,
                 allowBlank: false
                },{
                 itemId :'namaform',
                 xtype: 'textfield',
                 fieldLabel: 'Nama Form',
                 name: 'namaform',
                 disabled:true,
                 allowBlank: false,
                 width: 250
                },txtBtn_kodeParent
                ,{
                 itemId :'url',
                 xtype: 'textfield',
                 fieldLabel: 'URL',
                 name: 'url',
                 disabled:true,
                 width: 400
                },{
                itemId :'formid',
                xtype: 'hidden',
                 name: 'formid',
                 value:pformid
                },{
                 itemId :'formmode',
                 //id: 'form_mmenu',
                 xtype: 'hidden',
                 name: 'formmode',
                 value:formMode
                }
        ]
      });

      txtBtn_kodeParent.onTriggerClick = function(e){
            if(txtBtn_kodeParent.disabled){
                return;
            }
            listObjectInWIndowBrowse = browseForm(null);
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                txtBtn_kodeParent.setValue(row.get('kodeform'));
                listObjectInWIndowBrowse.extwindow.close();
            });
       };

      var customBtnCRUD = new CustomBtnCRUD (function(){} //beforeClickBaru
        ,function(){} //afterClickBaru
        ,function(){
            if(form_mmenu.getComponent('kodeform').getValue()=='')
                {
                    alert('Pilih Kode Form yang Ingin Diubah.');
                    return true;
                }
        } //beforeClickUbah
        ,function(){ //afterClickUbah
                form_mmenu.getComponent('kodeform').setReadOnly(true);
        }
        ,function(){
            if(form_mmenu.getComponent('kodeform').getValue()=='')
                {
                    alert('Pilih Kode Form yang Ingin Dihapus.');
                    return true;
                }
        } //beforeClickHapus
        ,function(success){ //afterClickHapus
            if(success){

            }else{

            }
        }
        ,function(){ //beforeClickSimpan
                form_mmenu.getComponent('kodeform').setReadOnly(false);
                if(form_mmenu.getComponent('kodeform').getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah kode form terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                else if(form_mmenu.getComponent('namaform').getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah nama form terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                return false;
        }
        ,function(success){ //afterClickSimpan
            if(success){

            }else{
                if (form_mmenu.getComponent('formmode').getValue()=='UPDATE'){
                  form_mmenu.getComponent('kodeform').setReadOnly(true);
                }
            }
        }
        ,function(){
            form_mmenu.getComponent('kodeform').setReadOnly(false);
        } //beforeClickBatal
        ,function(){} //afterClickBatal
        ,function(){} //beforeClickPrint
        ,function(){console.log(form_mmenu.getForm().getValues());} //afterClickPrint
      );
/*
      var Menu = Ext.data.Record.create([
            'kodeform',
            'namaform',
            'kodeparent',
            'url'
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
            idPath:'kodeform'
          }, Menu),
          autoLoad: false
        });
*/
      var columnsheader= [{header: "Kode Form", dataIndex: 'kodeform'},
          {header: "Nama Form", dataIndex: 'namaform',width:230},
          {header: "Kode Parent", dataIndex: 'kodeparent'},
          {header: "URL", dataIndex: 'url',width:300}];
      var grid = getGridView(ptitle, m_menu_xmlstore, columnsheader);
      grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
          form_mmenu.getComponent('kodeform').setValue(row.get('kodeform'));
          form_mmenu.getComponent('namaform').setValue(row.get('namaform'));
          txtBtn_kodeParent.setValue(row.get('kodeparent'));
          form_mmenu.getComponent('url').setValue(row.get('url'));
      });
      //   captureEvents(grid);

      var dataSubmit = new DataSubmit('maincontroller', function(){
          var params = {
              formid:form_mmenu.getComponent('formid').getValue(),
              formmode:form_mmenu.getComponent('formmode').getValue(),
              xmldata : convertFormValues_toXMLString(form_mmenu,'header','m_menu',
                null, false, 0,true)};
          return params;
      });

      setCRUDFormButton(form_mmenu,m_menu_xmlstore,grid,customBtnCRUD,dataSubmit);
      filterPermissionButtonForm(form_mmenu,pview,padd,pupdate,pdelete,pprint,m_menu_xmlstore);
      return getTwoPanelVerticalBorderLayoout(grid,form_mmenu,ptitle,pformid)
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
