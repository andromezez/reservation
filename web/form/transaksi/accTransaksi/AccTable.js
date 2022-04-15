/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function generate_accTable(formMode,pkodeform,pnamaform,pkodetransaksi,gridAccBefore,form,dataSubmit){
    var gridAcc;var colModel_gridAcc ;
    var accStore;
    var mh_konfigurasiaccStore;    
    var status=0;
        
    if(gridAccBefore==null){
        form.on('afterlayout',function(){
                form.getEl().mask('Loading...','x-mask-loading');
            }, this, {single: true}
        );
        //form.getForm().getEl().mask('Loading...','x-mask-loading');
        var m_settingacc_store = get_m_settingaccStore(formMode,
                SqlCustom_m_settingacc.get_selectFilterByKodeForm(pkodeform));
        m_settingacc_store.on('load', function() {            
            if(m_settingacc_store.getCount()>0){                
                var txt_btn_namabp = new Ext.form.TriggerField({
                                   name: 'txtBtn',
                                   value:'',
                                   triggerClass: 'x-form-search-trigger',
                                   editable:false
                              });
                var statusaccStore = new Ext.data.ArrayStore({
                           fields: ['id', 'approve_name'],
                           data : [['1','approved'],['0','unapproved']]
                        });
                function rendererStatusaccStore(val) {
                         return statusaccStore.queryBy(function(rec){
                            return rec.data.id == val;
                          }).itemAt(0).get('approve_name');
                }
                var txt_btn_statusacc = new Ext.form.TriggerField({
                                   name: 'txtBtn',
                                   value:'',
                                   triggerClass: 'x-form-search-trigger',
                                   editable:false
                              });
                accStore = get_m_accStore(formMode,SqlCustom_m_acc.get_selectFilterByKodetransKodeform(pkodeform, pkodetransaksi));                
                colModel_gridAcc = new Ext.grid.ColumnModel({
                           defaultSortable: false,
                            columns: [
                              {header: "Keterangan", dataIndex: 'keterangan'},
                              {header: "Nama Karyawan", dataIndex: 'namabp',width:250,
                                 editor:txt_btn_namabp},
                              {header: "Jabatan", dataIndex: 'namajabatan',width:200},
                              {header: "Tgl. Approval", dataIndex: 'tglacc', renderer: Ext.util.Format.dateRenderer( 'd M Y')},
                              {header: "Status", dataIndex: 'statusacc',
                                  editor:txt_btn_statusacc,renderer:rendererStatusaccStore}
                            ]
                        });
                gridAcc = new Ext.grid.EditorGridPanel({
                        frame: true,
                        height: 160,
                        anchor:'100%',
                        itemId:'gridAcc',
                        title:'Persetujuan',
                        store: accStore,
                        selModel: new Ext.grid.RowSelectionModel({singleSelect: true}),
                        colModel: colModel_gridAcc                        
                });
                txt_btn_statusacc.onTriggerClick = function(e){
                    var sm = gridAcc.getSelectionModel();
                    var record_gridAcc = sm.getSelected();    
                    var bolehAcc=false;
                    var selected_index = gridAcc.getStore().indexOf(record_gridAcc);
                    var record_sblm = gridAcc.getStore().getAt(selected_index-1);
                    var record_sesudah = gridAcc.getStore().getAt(selected_index+1);
                    var errorMsg = "";
                    if((gridAcc.getStore().indexOf(record_gridAcc)>0) &&
                        (gridAcc.getStore().indexOf(record_gridAcc)<(gridAcc.getStore().getCount()-1))){                       
                       if((record_sblm.get('statusacc')==1)&&(record_sesudah.get('statusacc')==0)){
                           bolehAcc = true;
                       }else{
                           errorMsg = "record sebelumnya harus sudah di acc dan record sesudahnya harus belum di acc";
                       }                       
                    }else if(gridAcc.getStore().indexOf(record_gridAcc)==0){
                       if((record_sesudah.get('statusacc')==0)){
                           bolehAcc = true;
                       }else{
                           errorMsg = "record sesudahnya harus belum di acc";
                       } 
                    }else if(gridAcc.getStore().indexOf(record_gridAcc)==(gridAcc.getStore().getCount()-1)){
                       if((record_sblm.get('statusacc')==1)){
                           bolehAcc = true;
                       }else{
                           errorMsg = "record sebelumnya harus sudah di acc";
                       } 
                    }
                    if(bolehAcc){
                        showAccWindow(pkodeform,pnamaform,
                            record_gridAcc.get('prioritas'),
                            record_gridAcc.get('keterangan'),
                            record_gridAcc,
                            form,
                            dataSubmit
                        );
                    }else{
                        Ext.Msg.show({
                            title: 'Error!',
                            msg: errorMsg,
                            icon: Ext.Msg.ERROR,
                            buttons: Ext.MessageBox.OK
                        });
                    }
                };
                txt_btn_namabp.onTriggerClick = function(e){
                    var sm = gridAcc.getSelectionModel();
                    var record_gridAcc = sm.getSelected();
                    if (status==1)
                        {return;}
                    else if (status==0)
                        {var listObjectInWIndowBrowse = browseYgAccTransaksi(pkodeform,record_gridAcc.get('prioritas'));
                         status=1;}
                                        
                    listObjectInWIndowBrowse.extgrid.on('rowdblclick', function(grid, rowIndex, e) {
                        var row = listObjectInWIndowBrowse.extgrid.getStore().getAt(rowIndex);                       
                        record_gridAcc.set('userid',row.get('userid'));
                        record_gridAcc.set('kodejabatan',row.get('kodejabatan'));
                        record_gridAcc.set('namajabatan',row.get('namajabatan'));
                        record_gridAcc.set('namabp',row.get('namabp'));
                        record_gridAcc.set('statusacc',0);
                        listObjectInWIndowBrowse.extwindow.close();status=0;
                    });
                    listObjectInWIndowBrowse.extwindow.on('close', function(p){
                        status=0;
                    });
                };
                mh_konfigurasiaccStore = get_mh_konfigurasiaccStore(formMode,
                        SqlCustom_mh_konfigurasiacc.get_selectFilterByKodeForm(pkodeform));                
                mh_konfigurasiaccStore.on('load', function() {
                    for(i=0;i<mh_konfigurasiaccStore.getTotalCount();i++){
                        gridAcc.getStore().insert( gridAcc.getStore().getCount(),
                            new m_accRecord({
                                userid:'',
                                kodeform:mh_konfigurasiaccStore.getAt(i).get('kodeform'),
                                kodetransaksi:pkodetransaksi,
                                tglacc:getCurrentDate(),
                                prioritas:mh_konfigurasiaccStore.getAt(i).get('prioritas'),
                                kodejabatan:'',
                                namajabatan:'',
                                namabp:'',
                                keterangan:mh_konfigurasiaccStore.getAt(i).get('keterangan'),
                                statusacc:0
                            })
                        );
                    }
                    form.getEl().unmask();
                });
                for(i=0;i<colModel_gridAcc.getColumnCount();i++){
                    colModel_gridAcc.setEditable( i, false);
                }                
                form.add(gridAcc);
                form.doLayout();
            }else{
                form.getEl().unmask();
            }            
        });                
    }
    else{
        if(formMode =='READ' || formMode == 'INSERT') {
            if(pkodetransaksi==null || pkodetransaksi==''){
                gridAccBefore.getStore().removeAll();
                mh_konfigurasiaccStore = get_mh_konfigurasiaccStore(formMode,
                    SqlCustom_mh_konfigurasiacc.get_selectFilterByKodeForm(pkodeform));
                mh_konfigurasiaccStore.on('load', function() {
                   for(i=0;i<mh_konfigurasiaccStore.getTotalCount();i++){
                        gridAccBefore.getStore().insert( gridAccBefore.getStore().getCount(),
                            new m_accRecord({
                                userid:'',
                                kodeform:mh_konfigurasiaccStore.getAt(i).get('kodeform'),
                                kodetransaksi:pkodetransaksi,
                                tglacc:getCurrentDate(),
                                prioritas:mh_konfigurasiaccStore.getAt(i).get('prioritas'),
                                kodejabatan:'',
                                namajabatan:'',
                                namabp:'',
                                keterangan:mh_konfigurasiaccStore.getAt(i).get('keterangan'),
                                statusacc:0
                            })
                        );
                   }
                });
            }else{
                /*var txt_btn = new Ext.form.TriggerField({
                                   name: 'txtBtn',
                                   value:'',
                                   triggerClass: 'x-form-search-trigger',
                                   editable:false
                              });*/
                accStore = get_m_accStore(formMode,SqlCustom_m_acc.get_selectFilterByKodetransKodeform(pkodeform, pkodetransaksi));
                colModel_gridAcc=gridAccBefore.getColumnModel();
                /*colModel_gridAcc = new Ext.grid.ColumnModel({
                       defaultSortable: true,
                        columns: [
                          {header: "Keterangan", dataIndex: 'keterangan'},
                          {header: "Nama Karyawan", dataIndex: 'namabp',
                             editor:txt_btn},
                          {header: "Jabatan", dataIndex: 'namajabatan'},
                          {header: "Tgl. Approval", dataIndex: 'tglacc', renderer: Ext.util.Format.dateRenderer( 'd M Y')}
                        ]
                    });   */             
                gridAccBefore.reconfigure(accStore,colModel_gridAcc);
                /*txt_btn.onTriggerClick = function(e){
                    var sm = gridAccBefore.getSelectionModel();
                    var record_gridAcc = sm.getSelected();                    
                    showAccWindow(pkodeform,pnamaform,
                        record_gridAcc.get('prioritas'),
                        record_gridAcc.get('keterangan'),
                        record_gridAcc
                    );
                };*/
            }
        }        
    }    
}
