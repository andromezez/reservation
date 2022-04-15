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
var allowStore = new Ext.data.ArrayStore({
               fields: ['id', 'allow_name'],
               data : [['2',''],['1','1'],['0','0']]
            });
function rendererAllow_name(val) {
             return allowStore.queryBy(function(rec){
                return rec.data.id == val;
              }).itemAt(0).get('allow_name');
   }
function getForm_muser(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint){

   var txtBtn_namaBp = new Ext.form.TriggerField({
       itemId :'namabp',
       fieldLabel: 'Nama BP',
       name: 'namabp',
       value:'',
       disabled:true,
       allowBlank: false,
       editable:false,
       triggerClass: 'x-form-search-trigger',
       width: 350
   });
   var txt_kodebp = new Ext.form.TextField({
        itemId :'kodebp',
                     fieldLabel: 'Kode BP',
                     name: 'kodebp',
                     value:'',
                     disabled:true,
                     width: 200
    });
   var txt_nik = new Ext.form.TextField({
        itemId :'nik',
                     fieldLabel: 'NIK',
                     name: 'nik',
                     value:'',
                     disabled:true,
                     width: 200
    });
   /*var txtBtn_costcenter = new Ext.form.TriggerField({
       itemId :'costcenter',
       fieldLabel: 'Cost Center',
       name: 'costcenter',
       value:'',
       disabled:true,
       allowBlank: false,
       triggerClass: 'x-form-search-trigger',
       width: 200
   });*/
 
   var txtBtn_kodeform = new Ext.form.TriggerField({
       itemId :'kodeform',
       name: 'kodeform',
       value:'',
       triggerClass: 'x-form-search-trigger'
   });
   var cb_pview = new Ext.form.Checkbox({
         itemId :'pview',
         name: 'pview',
         checked:false,
         disabled:false
    });
   var cb_padd = new Ext.form.Checkbox({
         itemId :'padd',
         name: 'padd',
         checked:false,
         disabled:false
    });
   var cb_pupdate = new Ext.form.Checkbox({
         itemId :'pupdate',
         name: 'pupdate',
         checked:false,
         disabled:false
    });
   var cb_pdelete = new Ext.form.Checkbox({
         itemId :'pdelete',
         name: 'pdelete',
         checked:false,
         disabled:false
    });
   var cb_pprint = new Ext.form.Checkbox({
         itemId :'pprint',
         name: 'pprint',
         checked:false,
         disabled:false
    }); 
   
   var cmb_pview=new Ext.form.ComboBox({
                     typeAhead: true,
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: allowStore,
                     valueField: 'id',
                     displayField: 'allow_name'
                    });

  var cmb_padd=new Ext.form.ComboBox({
                     typeAhead: true,
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: allowStore,
                     valueField: 'id',
                     displayField: 'allow_name'
                    });
  var cmb_pupdate=new Ext.form.ComboBox({
                     typeAhead: true,
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: allowStore,
                     valueField: 'id',
                     displayField: 'allow_name'
                    });

  var cmb_pdelete=new Ext.form.ComboBox({
                     typeAhead: true,
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: allowStore,
                     valueField: 'id',
                     displayField: 'allow_name'
                    });

  var cmb_pprint=new Ext.form.ComboBox({
                     typeAhead: true,
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: allowStore,
                     valueField: 'id',
                     displayField: 'allow_name'
                    });
   var cmb_pbatal_trans=new Ext.form.ComboBox({
                     typeAhead: true,
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: allowStore,
                     valueField: 'id',
                     displayField: 'allow_name'
                    }); 
   
   var txt_userid = new Ext.form.TextField({
        itemId :'userid',
        fieldLabel: 'User ID',
        name: 'userid',
        value:'',
        disabled:true,
        allowBlank: false
    });

   var colModel_gridPermission = new Ext.grid.ColumnModel({
                   defaultSortable: true,
                    columns: [
                      {header: "User ID", dataIndex: 'userid',width:100},
                      {header: "Kode Form", dataIndex: 'kodeform',
                         editor: txtBtn_kodeform},
                      {header: "Nama Form", dataIndex: 'namaform',width:250},                      
                      {xtype: 'checkcolumn',header: 'View',
                        dataIndex: 'pview',width:50
                      },
                      /*{header: "View", dataIndex: 'pview',
                        editor: cmb_pview, renderer:rendererAllow_name}*/
                      {xtype: 'checkcolumn',header: 'Add',
                        dataIndex: 'padd',width:50
                      },
                      /*{header: "Add", dataIndex: 'padd',
                        editor: cmb_padd, renderer:rendererAllow_name},*/
                      {xtype: 'checkcolumn',header: 'Update',
                        dataIndex: 'pupdate',width:50
                      },
                      /*{header: "update", dataIndex: 'pupdate',
                        editor: cmb_pupdate, renderer:rendererAllow_name},*/
                      {xtype: 'checkcolumn',header: 'Delete',
                        dataIndex: 'pdelete',width:50
                      },
                      /*{header: "Delete", dataIndex: 'pdelete',
                        editor: cmb_pdelete, renderer:rendererAllow_name},*/
                      {xtype: 'checkcolumn',header: 'Print',
                        dataIndex: 'pprint',width:50
                      },
                      /*{header: "Print", dataIndex: 'pprint',
                        editor: cmb_pprint, renderer:rendererAllow_name},*/
                      {xtype: 'checkcolumn',header: 'Batal Trans',
                        dataIndex: 'batal_trans',width:70
                      }
                      /*{header: "Batal Trans", dataIndex: 'batal_trans',
                        editor: cmb_pbatal_trans, renderer:rendererAllow_name}*/                      
                    ]
                });

   var gridPermission = new Ext.grid.EditorGridPanel({
                frame: true,
                height: 225,
                anchor:'95%',
                title:'Daftar Form yang Diijinkan',
                //store: get_m_permissionStore(formMode,SqlCustom_m_permission.get_selectFilterByUserId('admin')),
                store: get_m_permissionStore(formMode),
                selModel: new Ext.grid.RowSelectionModel({singleSelect:true}),
                colModel: colModel_gridPermission,
                listeners: {
                    afteredit: function(e){
                       // e.record.commit();
                    }
                },
                tbar: [{
                  text: 'Add row',
                  handler: function(){
                     gridPermission.getStore().insert( gridPermission.getStore().getCount(),
                        new m_permissionRecord({
                            kodeform:'',
                            namaform:'',
                            userid:txt_userid.getValue(),
                            pview:1,
                            padd:1,
                            pupdate:1,
                            pdelete:1,
                            pprint:1,
                            batal_trans:'0'
                         })
                      );
                    }
                  },{
                  text: 'Delete row',
                  handler: function(btn){
                    var sm = gridPermission.getSelectionModel();
                    sel = sm.getSelected();
                    gridPermission.getStore().remove(sel);
                  }
                  },{
                  text: 'Copy from',
                  handler: function(btn){                    
                    var listObjectInWIndowBrowse=browseUser(get_m_userStore('READ',SqlCustom_m_user.get_selectAllExceptUserid(txt_userid.getValue())));
                    listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                      var row = grid.getStore().getAt(rowIndex);
                      var store = get_m_permissionStore('READ',
                            SqlCustom_m_permission.get_selectFilterByUserId(row.get('userid'))
                        );
                      store.on('load',function(){
                          gridPermission.reconfigure(store,colModel_gridPermission);
                          for(i=0;i<gridPermission.getStore().getCount();i++){
//                              console.log('userid before edit:'+gridPermission.getStore().getAt(i).get('userid'));
                              gridPermission.getStore().getAt(i).set('userid',txt_userid.getValue());                              
  //                            console.log('userid after edit:'+gridPermission.getStore().getAt(i).get('userid'));
                          }
                          listObjectInWIndowBrowse.extwindow.close();
                      });                      

                      //console.log(form.getForm().getValues());                      
                    });
                  }
              }]
   });

   for(i=0;i<colModel_gridPermission.getColumnCount();i++){
       colModel_gridPermission.setEditable( i, false);
   }
   gridPermission.getTopToolbar().disable();

   var form_muser = new Ext.FormPanel({
         url: 'maincontroller',
         region:'center',
         //closable:true,
        // renderTo: Ext.get('m_userForm'),//Ext.getBody(),
        // title:ptitle,
         //id:pformid,
         frame: true,
         autoScroll: true,
         tbar:[],
         items: [txt_userid,txtBtn_namaBp,txt_kodebp,txt_nik
                ,{
                 itemId :'pass',
                 xtype: 'hidden',
                 //fieldLabel: 'Password',
                 name: 'pass',
                 width: 200
                }//,txtBtn_costcenter
                ,{
                 itemId :'aktif',
                 xtype: 'checkbox',
                 fieldLabel: 'Aktif',
                 name: 'aktif',
                 disabled:true,
                 checked:true,
                 inputValue: '1'
                },gridPermission
                ,{
                itemId :'formid',
                xtype: 'hidden',
                 name: 'formid',
                 value:pformid
                },{
                 itemId :'formmode',
                 //id: 'form_muser',
                 xtype: 'hidden',
                 name: 'formmode',
                 value:formMode
                }
        ]
      });

      txtBtn_namaBp.onTriggerClick = function(e){
           /* browseBPartneLengkap(hidden_kodebp, txt_NIK, txtBtn_namaPemohon, null, txt_perusahaan,
                hidden_kodeorg, txt_organisasi, null, null,null,null,record_vm_hris_bpartnerLengkapRecord);*/
          if(txtBtn_namaBp.disabled){
                return;
            }
          var sql=SqlCustom_vm_hris_bpartnerLengkap.selectKaryawanPemohonAll();
            listObjectInWIndowBrowse = browseBPartneLengkap(customSelect_vm_hris_bpartnerLengkap_xmlstore(SqlCustom_vm_hris_bpartnerLengkap.selectKaryawanPemohonAll()),sql);
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                txt_kodebp.setValue(row.get('kodebp'));
                txt_nik.setValue(row.get('nik'));
                txtBtn_namaBp.setValue(row.get('namabp'));
                listObjectInWIndowBrowse.extwindow.close();
            });
       };

      /*txtBtn_costcenter.onTriggerClick = function(e){
            listObjectInWIndowBrowse = browseCostCenter(null);
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                txtBtn_costcenter.setValue(row.get('kodecostcenter'));
                listObjectInWIndowBrowse.extwindow.close();
            });
       };*/
      var status=0;  
      txtBtn_kodeform.onTriggerClick = function(e){
            var sm = gridPermission.getSelectionModel();
            var record_gridpermission = sm.getSelected();
           // browseCostCenter(/*null,null,null,*/record_vm_hris_costcenterRecord);
           if (status==1)
               {return;}
           else if (status==0)
                {listObjectInWIndowBrowse = browseForm(get_m_formmenuStore(formMode,SqlCustom_m_formmenu.get_selectFilterByUrl()));
                    status=1;}
            
            listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);
                record_gridpermission.set('kodeform',row.get('kodeform'));
                record_gridpermission.set('namaform',row.get('namaform'));
                listObjectInWIndowBrowse.extwindow.close();
                status=0;
            });
            listObjectInWIndowBrowse.extwindow.on('close', function(p){
                status=0;
            });
       };

      var dataSubmit = new DataSubmit('maincontroller', function(){
          var xmlMPermission = gridPermission.getStore().xmlSerialize(null,'detail',
                'm_permission',false, false, false, false, 0,false);
          var xmlMUser = convertFormValues_toXMLString(form_muser,'header','m_user',
                [xmlMPermission], false, 0,true);
          //console.log(xmlMUser);
          var params = {
              formid:form_muser.getComponent('formid').getValue(),
              formmode:form_muser.getComponent('formmode').getValue(),
              xmldata : xmlMUser};
          return params;
      });

      var customBtnCRUD = new CustomBtnCRUD (function(){} //beforeClickBaru
        ,function(){
            gridPermission.getStore().removeAll();
        } //afterClickBaru
        ,function(){
            if(txt_userid.getValue()=='')
                {
                    alert('Pilih User Id yang Ingin Diubah.');
                    return true;
                }
        } //beforeClickUbah
        ,function(){ //afterClickUbah
                txt_userid.setReadOnly(true);
                txt_nik.disable();
                txt_kodebp.disable();
        }
        ,function(){
            if(txt_userid.getValue()=='')
                {
                    alert('Pilih User Id yang Ingin Dihapus.');
                    return true;
                }} //beforeClickHapus
        ,function(success){ //afterClickHapus
            if(success){

            }else{

            }
        }
        ,function(){ //beforeClickSimpan
                txt_userid.setReadOnly(false);
                txt_nik.enable();
                txt_kodebp.enable();
                txtBtn_namaBp.enable();
                if(txt_userid.getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah user id terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                else if(txtBtn_namaBp.getValue()==''){
                    Ext.Msg.show({
                       title: 'Warning!',
                       msg: 'Data tidak bisa disimpan. Isilah nama bp terlebih dahulu.',
                       icon: Ext.Msg.WARNING,
                       buttons: Ext.MessageBox.OK
                    });                
                    return true;
                }
                return false;
        }
        ,function(success){ //afterClickSimpan
            if(success){
                gridPermission.reconfigure(
                    get_m_permissionStore(form_muser.getComponent('formmode').getValue(),
                        SqlCustom_m_permission.get_selectFilterByUserId(txt_userid.getValue())
                    ),
                    colModel_gridPermission
                  );
              
            }else{
                if (form_muser.getComponent('formmode').getValue()=='UPDATE'){
                    form_muser.getComponent('userid').setReadOnly(true);
                }
                    //txt_nik.disable();
                    //txt_kodebp.disable();
            }
        }
        ,function(){
            txt_userid.setReadOnly(false);
        } //beforeClickBatal
        ,function(){} //afterClickBatal
        ,function(){} //beforeClickPrint
        ,function(){
            console.log(form_muser.getForm().getValues());
        } //afterClickPrint
      );

      /*var User = Ext.data.Record.create([
            'userid',
            'kodebp',
            //'pass',
            'costcenter',
            'aktif'
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
            idPath:'userid'
          }, User),
          autoLoad: false
        });
*/

      var columnsheader= [{header: "User ID", dataIndex: 'userid'},
          {header: "Nama BP", dataIndex: 'namabp',width:250},
          {header: "Kode BP", dataIndex: 'kodebp'},
          {header: "NIK", dataIndex: 'nik'},
          //{header: "Cost Center", dataIndex: 'costcenter'},
          {header: "Aktif", dataIndex: 'aktif',xtype: 'checkcolumn'}];
      var grid = getGridView(ptitle, m_user_xmlstore,columnsheader);
      grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
          txt_userid.setValue(row.get('userid'));
          //form_muser.getComponent('namabp').setValue(row.get('namabp'));
          txtBtn_namaBp.setValue(row.get('namabp'));
          txt_nik.setValue(row.get('nik'));
          /*get_m_userStore(form_muser.getComponent('formmode').getValue(),
                SqlCustom_m_user.get_selectFilterByUserId(row.get('userid'))
            ), txtBtn_namaUser, txt_nik
*/
          form_muser.getComponent('kodebp').setValue(row.get('kodebp'));
          //form_muser.getComponent('costcenter').setValue(row.get('costcenter'));
          form_muser.getComponent('aktif').setValue(row.get('aktif'));

          gridPermission.reconfigure(
            get_m_permissionStore(form_muser.getComponent('formmode').getValue(),
                SqlCustom_m_permission.get_selectFilterByUserId(row.get('userid'))
            ),
            colModel_gridPermission
          );
      });
     //   captureEvents(grid);
      setCRUDFormButton(form_muser,m_user_xmlstore,grid,customBtnCRUD,dataSubmit);
      filterPermissionButtonForm(form_muser,pview,padd,pupdate,pdelete,pprint,m_user_xmlstore);
      return getTwoPanelVerticalBorderLayoout(grid,form_muser,ptitle,pformid);//form_muser;
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
