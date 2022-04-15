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
function getForm_mjenis(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint){
    
   var form_mjenis = new Ext.FormPanel({
         url: 'maincontroller',
         region:'center',         
         //closable:true,
        // renderTo: Ext.get('m_jenisForm'),//Ext.getBody(),
        // title:ptitle,        
        // id:pformid,
         frame: true,
         autoScroll: true,
         bodyStyle: 'padding:7px',
         //waitMsgTarget: true,
         tbar:[],
         items: [{
                 itemId :'kodejenis',
                 xtype: 'textfield',
                 fieldLabel: 'Kode Jenis',
                 name: 'kodejenis',
                 value:'',
                 //readOnly:true,
                 disabled:true,
                 allowBlank: false
                },{
                 itemId :'namajenis',
                 xtype: 'textfield',
                 fieldLabel: 'Nama Jenis',
                 name: 'namajenis',
                 width: 200,
                 value:'',
                 disabled:true,
                 allowBlank: false
                },{
                 itemId :'formid',
                 xtype: 'hidden',
                 name: 'formid',
                 value:pformid
                },{
                 itemId :'formmode',
                 //id: 'form_mjenis',
                 xtype: 'hidden',
                 name: 'formmode',
                 value:formMode                 
                }
        ]
      });

      var dataSubmit = new DataSubmit('maincontroller', function(){
          //var params = Ext.urlDecode(form_mjenis.getForm().getValues(true));
          var params = {
              formid:form_mjenis.getComponent('formid').getValue(),
              formmode:form_mjenis.getComponent('formmode').getValue(),
              xmldata : convertFormValues_toXMLString(form_mjenis,'header','m_jenis',
                null, false, 0,true)};
          return params;
      });

      var customBtnCRUD = new CustomBtnCRUD (function(){} //beforeClickBaru
        ,function(){} //afterClickBaru
        ,function(){
             if(form_mjenis.getComponent('kodejenis').getValue()=='')
                {
                    alert('Pilih Kode Jenis yang Ingin Diubah.');
                    return true;
                }
        } //beforeClickUbah
        ,function(){ //afterClickUbah
                form_mjenis.getComponent('kodejenis').setReadOnly(true);
        }
        ,function(){
            if(form_mjenis.getComponent('kodejenis').getValue()=='')
                {
                    alert('Pilih Kode Jenis yang Ingin Dihapus.');
                    return true;
                }
        } //beforeClickHapus
        ,function(success){ //afterClickHapus
            if(success){
               
            }else{
                   
            }
        }
        ,function(){ //beforeClickSimpan
                form_mjenis.getComponent('kodejenis').setReadOnly(false);
                if(form_mjenis.getComponent('kodejenis').getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah kode jenis terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                else if(form_mjenis.getComponent('namajenis').getValue()==''){
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
               
            }else{
                if (form_mjenis.getComponent('formmode').getValue()=='UPDATE'){
                    form_mjenis.getComponent('kodejenis').setReadOnly(true);
                }
            }
        }
        ,function(){
            form_mjenis.getComponent('kodejenis').setReadOnly(false);
        } //beforeClickBatal
        ,function(){} //afterClickBatal
        ,function(){} //beforeClickPrint
        ,function(){
            console.log(dataSubmit.getParams());
        } //afterClickPrint
      );
        
      
      /*var Jenis = Ext.data.Record.create([
            'kodejenis',
            'namajenis'
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
            idPath:'kodejenis'
          }, Jenis),
          autoLoad: false
        });*/
      var columnsheader= [{header: "Kode Jenis", dataIndex: 'kodejenis',width:130},
          {header: "Nama Jenis", dataIndex: 'namajenis',width:200}];
      
      var grid = getGridView(ptitle, m_jenis_xmlstore,columnsheader);
      grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
          form_mjenis.getComponent('kodejenis').setValue(row.get('kodejenis'));
          form_mjenis.getComponent('namajenis').setValue(row.get('namajenis'));         
      });
     //   captureEvents(grid);
    
      //var objParams =form_mjenis.getForm().getValues(true); //Ext.util.JSON.decode(form_mjenis.getForm().getValues());
      
      
      //m_jenis
      setCRUDFormButton(form_mjenis,m_jenis_xmlstore,grid,customBtnCRUD,dataSubmit,new  RequestReport ('maincontroller','m_jenis'));
      
      filterPermissionButtonForm(form_mjenis,pview,padd,pupdate,pdelete,pprint,m_jenis_xmlstore);
      
      return getTwoPanelVerticalBorderLayoout(grid,form_mjenis,ptitle,pformid);//form_mjenis;
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
