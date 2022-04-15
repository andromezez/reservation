/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function getForm_msettingacc(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint){
   
   var txtBtn_namaForm = new Ext.form.TriggerField({
       itemId :'namaform',
       fieldLabel: 'Nama Form',
       name: 'namaform',
       value:'',
       disabled:true,
       editable:false,
       allowBlank: false,
       triggerClass: 'x-form-search-trigger',
       width: 350
   });
   var hidden_kodeform = new Ext.form.Hidden({
            itemId :'kodeform',
            name: 'kodeform',
            value:''
   });
   var form_msettingacc = new Ext.FormPanel({
         url: 'maincontroller',
         region:'center',
         frame: true,
         autoScroll: true,
         tbar: [],
         items: [
                 hidden_kodeform, txtBtn_namaForm
                ,{
                 itemId :'accform',
                 xtype: 'checkbox',
                 fieldLabel: 'Acc Form',
                 checked : true,
                 disabled:true,
                 name: 'accform'                 
                }/*,{
                 itemId :'editfinalapprove',
                 xtype: 'checkbox',
                 fieldLabel: 'Edit Final Approve',
                 disabled:true,
                 name: 'editfinalapprove'
                }*/
                ,{
                 itemId :'editfinalapprove',
                 xtype: 'hidden',
                 name: 'editfinalapprove',
                 value:0   
                },{
                 itemId :'formid',
                 xtype: 'hidden',
                 name: 'formid',
                 value:pformid
                },{
                 itemId :'formmode',
                 //id: 'form_msettingacc',
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
            //browseFormNeedAcc(hidden_kodeform, txtBtn_namaForm);
            listObjectInWIndowBrowse = browseForm(get_m_formmenuStore('READ',SqlCustom_m_formmenu.get_selectAllNotInMsettingAcc()));
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                hidden_kodeform.setValue(row.get('kodeform'));
                txtBtn_namaForm.setValue(row.get('namaform'));
                listObjectInWIndowBrowse.extwindow.close();
            });
       };

      var customBtnCRUD = new CustomBtnCRUD (function(){} //beforeClickBaru
        ,function(){
            hidden_kodeform.setReadOnly(false);
            //txtBtn_namaForm.setReadOnly(false);
            //txtBtn_namaForm.disable();
        } //afterClickBaru
        ,function(){
            if(txtBtn_namaForm.getValue()=='')
                {
                    alert('Pilih Form yang Ingin Diubah.');
                    return true;
                }
        } //beforeClickUbah
        ,function(){ //afterClickUbah
                hidden_kodeform.setReadOnly(true);
                //txtBtn_namaForm.setReadOnly(true);
                //txtBtn_namaForm.disable();
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
            hidden_kodeform.setReadOnly(false);
            //txtBtn_namaForm.setReadOnly(false);
        }
        ,function(success){ //afterClickSimpan
            if(success){

            }else{
                if (form_msettingacc.getComponent('formmode').getValue()=='UPDATE'){
                    hidden_kodeform.setReadOnly(true);
                }
                //txtBtn_namaForm.setReadOnly(true);
            }
        }
        ,function(){
            console.log(form_msettingacc.getForm().getValues());
            hidden_kodeform.setReadOnly(false);
            //form_msettingacc.getComponent('kodeform').setReadOnly(false);
        } //beforeClickBatal
        ,function(){} //afterClickBatal
        ,function(){} //beforeClickPrint
        ,function(){} //afterClickPrint
      );
/*
      var SettingAcc = Ext.data.Record.create([
            'kodeform',
            'accform',
            'editfinalapprove'
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
          }, SettingAcc),
          autoLoad: false
        });
*/

      var columnsheader= [{header: "Kode Form", dataIndex: 'kodeform'},
          {header: "Nama Form", dataIndex: 'namaform',width:200},
          {header: "Acc Form", dataIndex: 'accform',xtype: 'checkcolumn',width:70}
          //,{header: "Edit Final Approve", dataIndex: 'editfinalapprove',xtype: 'checkcolumn',hidden:true}
      ];
      var grid = getGridView(ptitle, m_settingacc_xmlstore,columnsheader);
      grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
          form_msettingacc.getComponent('kodeform').setValue(row.get('kodeform'));
          txtBtn_namaForm.setValue(row.get('namaform'));
          form_msettingacc.getComponent('accform').setValue(row.get('accform'));
          form_msettingacc.getComponent('editfinalapprove').setValue(row.get('editfinalapprove'));
      });
     //   captureEvents(grid);

     var dataSubmit = new DataSubmit('maincontroller', function(){
          var params = {
              formid:form_msettingacc.getComponent('formid').getValue(),
              formmode:form_msettingacc.getComponent('formmode').getValue(),
              xmldata : convertFormValues_toXMLString(form_msettingacc,'header','m_settingacc',
                null, false, 0,true)};
          return params;
      });

      setCRUDFormButton(form_msettingacc,m_settingacc_xmlstore,grid,customBtnCRUD,dataSubmit);
      filterPermissionButtonForm(form_msettingacc,pview,padd,pupdate,pdelete,pprint,m_settingacc_xmlstore);
      return getTwoPanelVerticalBorderLayoout(grid,form_msettingacc,ptitle,pformid);
}//document.getElementById("form_msettingacc")
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
