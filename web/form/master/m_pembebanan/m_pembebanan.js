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
function getForm_mpembebanan(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint){
var txtBtn_costCenter = new Ext.form.TriggerField({
       itemId :'kodecostcenter',
       name: 'kodecostcenter',
       value:'',
       triggerClass: 'x-form-search-trigger'
   });

var txt_kodePembebanan = new Ext.form.TextField({
        itemId :'kodepembebanan',                     
        fieldLabel: 'Kode Pembebanan',
        name: 'kodepembebanan',
        value:'',
        disabled:true,
        allowBlank: false        
    });
    
var colModel_gridCostCenter = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [
                      //{header: "Kode Pembebanan", dataIndex: 'kodepembebanan'},
                      {header: "Cost Center", dataIndex: 'kodecostcenter',
                         editor: txtBtn_costCenter,width: 100},
                      {header: "Nama Cost Center", dataIndex: 'namacostcenter',width: 400}
                    ]
                });

var gridCostCenter = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 225,
                anchor:'95%',
                title:'Detail Cost Center',
                store: get_md_pembebananStore(formMode),
                selModel: new Ext.grid.RowSelectionModel({singleSelect:true}),
                colModel: colModel_gridCostCenter,
                listeners: {
                    afteredit: function(e){
                       // e.record.commit();
                    }
                },
                tbar: [{
                  text: 'Add row',
                  handler: function(){
                     gridCostCenter.getStore().insert( gridCostCenter.getStore().getCount(),
                        new md_pembebananRecord({
                            kodepembebanan:txt_kodePembebanan.getValue(),
                            kodecostcenter:'',
                            namacostcenter:''
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

   var form_mpembebanan = new Ext.FormPanel({
         url: 'maincontroller',
         region:'center',
         frame: true,
         autoScroll: true,
         tbar:[],
         items: [
                 txt_kodePembebanan,
                 {
                 itemId :'namapembebanan',
                 xtype: 'textfield',
                 fieldLabel: 'Nama Pembebanan',
                 name: 'namapembebanan',
                 width: 250,
                 value:'',
                 disabled:true,
                 allowBlank: false
                }, gridCostCenter
                ,{
                 itemId :'formid',
                 xtype: 'hidden',
                 name: 'formid',
                 value:pformid
                },{
                 itemId :'formmode',
                 //id: 'form_mpembebanan',
                 xtype: 'hidden',
                 name: 'formmode',
                 value:formMode
                }
        ]
      });

      for(i=0;i<colModel_gridCostCenter.getColumnCount();i++){
            colModel_gridCostCenter.setEditable( i, false);
      }
      gridCostCenter.getTopToolbar().disable();
      var status=0;
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
                record_gridCostCenter.set('kodecostcenter',row.get('kodecostcenter'));
                record_gridCostCenter.set('namacostcenter',row.get('namacostcenter'));
                listObjectInWIndowBrowse.extwindow.close();status=0;
            });
            listObjectInWIndowBrowse.extwindow.on('close', function(p){
                status=0;
            });
       };

       var dataSubmit = new DataSubmit('maincontroller', function(){
          var xmlMdPembebanan = gridCostCenter.getStore().xmlSerialize(null,'detail',
                'md_pembebanan',false, false, false, false, 0,false);
          var xmlMPembebanan = convertFormValues_toXMLString(form_mpembebanan,'header','m_pembebanan',
                [xmlMdPembebanan], false, 0,true);
          //console.log(xmlMPembebanan);
          //params.xmldata = xmlHTPengajuan;
          var params = {
              formid:form_mpembebanan.getComponent('formid').getValue(),
              formmode:form_mpembebanan.getComponent('formmode').getValue(),
              xmldata : xmlMPembebanan};
          return params;
      });

      var customBtnCRUD = new CustomBtnCRUD (function(){} //beforeClickBaru
        ,function(){
            gridCostCenter.getStore().removeAll();
        } //afterClickBaru
        ,function(){
            if(txt_kodePembebanan.getValue()=='')
                {
                    alert('Pilih Kode Pembebanan yang Ingin Diubah.');
                    return true;
                }
        } //beforeClickUbah
        ,function(){ //afterClickUbah
                txt_kodePembebanan.setReadOnly(true);
                
        }
        ,function(){
            if(txt_kodePembebanan.getValue()=='')
                {
                    alert('Pilih Kode Pembebanan yang Ingin Dihapus.');
                    return true;
                }
        } //beforeClickHapus
        ,function(success){ //afterClickHapus
            if(success){

            }else{

            }
        }
        ,function(){ //beforeClickSimpan
                txt_kodePembebanan.setReadOnly(false);
                if(txt_kodePembebanan.getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah kode pembebanan terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                else if(form_mpembebanan.getComponent('namapembebanan').getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah nama pembebanan terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                return false;
        }
        ,function(success){ //afterClickSimpan
            if(success){
                gridCostCenter.reconfigure(
                    get_md_pembebananStore(form_mpembebanan.getComponent('formmode').getValue(),
                        SqlCustom_md_pembebanan.get_selectFilterByKodePembebanan(txt_kodePembebanan.getValue())
                    ),
                    colModel_gridCostCenter
                  );
            }else{
                if (form_mpembebanan.getComponent('formmode').getValue()=='UPDATE'){
                    txt_kodePembebanan.setReadOnly(true);
                }
            }
        }
        ,function(){
            txt_kodePembebanan.setReadOnly(false);
        } //beforeClickBatal
        ,function(){} //afterClickBatal
        ,function(){} //beforeClickPrint
        ,function(){
            console.log(dataSubmit.getParams());
        } //afterClickPrint
      );
      /*
      var Pembebanan = Ext.data.Record.create([
            'kodepembebanan',
            'namapembebanan'
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
            idPath:'kodepembebanan'
          }, Pembebanan),
          autoLoad: false
        });*/


      var columnsheader= [{header: "Kode Pembebanan", dataIndex: 'kodepembebanan',width:170},
          {header: "Nama Pembebanan", dataIndex: 'namapembebanan',width:200}];
      var grid = getGridView(ptitle, m_pembebanan_xmlstore,columnsheader);
      //var grid = getGridView(ptitle, xmlstore,columnsheader);
      grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
          txt_kodePembebanan.setValue(row.get('kodepembebanan'));
          form_mpembebanan.getComponent('namapembebanan').setValue(row.get('namapembebanan'));

          gridCostCenter.reconfigure(
            get_md_pembebananStore(form_mpembebanan.getComponent('formmode').getValue(),
                SqlCustom_md_pembebanan.get_selectFilterByKodePembebanan(row.get('kodepembebanan'))
            ),
            colModel_gridCostCenter
          );
     });
     //   captureEvents(grid);
     

      setCRUDFormButton(form_mpembebanan,m_pembebanan_xmlstore,grid,customBtnCRUD,dataSubmit);

      filterPermissionButtonForm(form_mpembebanan,pview,padd,pupdate,pdelete,pprint,m_pembebanan_xmlstore);
      return getTwoPanelVerticalBorderLayoout(grid,form_mpembebanan,ptitle,pformid);//form_mpembebanan;
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
