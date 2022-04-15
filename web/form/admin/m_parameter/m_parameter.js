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
function getForm_mparameter(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint){

   var form_mparameter = new Ext.FormPanel({
         url: 'maincontroller',
         region:'center',
         //closable:true,
        // renderTo: Ext.get('m_parameterForm'),//Ext.getBody(),
        // title:ptitle,
        // id:pformid,
         frame: true,
         autoScroll: true,
         tbar:[],
         items: [{
                 itemId :'parname',
                 xtype: 'textfield',
                 fieldLabel: 'Parname',
                 name: 'parname',
                 value:'', width: 250,
                 //readOnly:true,
                 disabled:true,
                 allowBlank: false
                },{
                 itemId :'parvalue',
                 xtype: 'textfield',
                 fieldLabel: 'Parvalue',
                 name: 'parvalue',
                 width: 250,
                 value:'',
                 disabled:true,
                 allowBlank: false
                },{
                 itemId :'partype',
                 xtype: 'textfield',
                 fieldLabel: 'Partype',
                 name: 'partype',
                 width: 250,
                 value:'',
                 disabled:true
                },{
                 itemId :'formid',
                 xtype: 'hidden',
                 name: 'formid',
                 value:pformid
                },{
                 itemId :'formmode',
                 //id: 'form_mparameter',
                 xtype: 'hidden',
                 name: 'formmode',
                 value:formMode
                }
        ]
      });

      var customBtnCRUD = new CustomBtnCRUD (function(){} //beforeClickBaru
        ,function(){} //afterClickBaru
        ,function(){
            if(form_mparameter.getComponent('parname').getValue()=='')
                {
                    alert('Pilih Nama Parameter yang Ingin Diubah.');
                    return true;
                }
        } //beforeClickUbah
        ,function(){ //afterClickUbah
                form_mparameter.getComponent('parname').setReadOnly(true);
        }
        ,function(){
            if(form_mparameter.getComponent('parname').getValue()=='')
                {
                    alert('Pilih Nama Parameter yang Ingin Dihapus.');
                    return true;
                }
        } //beforeClickHapus
        ,function(success){ //afterClickHapus
            if(success){

            }else{

            }
        }
        ,function(){ //beforeClickSimpan
                form_mparameter.getComponent('parname').setReadOnly(false);
                if(form_mparameter.getComponent('parname').getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah parname terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                else if(form_mparameter.getComponent('parvalue').getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah parvalue terlebih dahulu.',
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
                if (form_mparameter.getComponent('formmode').getValue()=='UPDATE'){
                    form_mparameter.getComponent('parname').setReadOnly(true);
                }
            }
        }
        ,function(){
            form_mparameter.getComponent('parname').setReadOnly(false);
        } //beforeClickBatal
        ,function(){} //afterClickBatal
        ,function(){} //beforeClickPrint
        ,function(){} //afterClickPrint
      );


      /*var Parameter = Ext.data.Record.create([
            'parname',
            'parvalue',
            'partype'
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
            idPath:'parname'
          }, Parameter),
          autoLoad: false
        });
        */

      var columnsheader= [{header: "Parname", dataIndex: 'parname',width:300},
          {header: "Parvalue", dataIndex: 'parvalue'},
          {header: "Partype", dataIndex: 'partype'}];
      var grid = getGridView(ptitle, m_parameter_xmlstore,columnsheader);
      grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
          form_mparameter.getComponent('parname').setValue(row.get('parname'));
          form_mparameter.getComponent('parvalue').setValue(row.get('parvalue'));
          form_mparameter.getComponent('partype').setValue(row.get('partype'));
      });
     //   captureEvents(grid);

     var dataSubmit = new DataSubmit('maincontroller', function(){
          var params = {
              formid:form_mparameter.getComponent('formid').getValue(),
              formmode:form_mparameter.getComponent('formmode').getValue(),
              xmldata : convertFormValues_toXMLString(form_mparameter,'header','m_parameter',
                null, false, 0,true)};
          return params;
      });

      setCRUDFormButton(form_mparameter,m_parameter_xmlstore,grid,customBtnCRUD,dataSubmit);

      filterPermissionButtonForm(form_mparameter,pview,padd,pupdate,pdelete,pprint,m_parameter_xmlstore);
      return getTwoPanelVerticalBorderLayoout(grid,form_mparameter,ptitle,pformid);//form_mparameter;
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
