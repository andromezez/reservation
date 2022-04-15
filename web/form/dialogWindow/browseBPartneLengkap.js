/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function browseBPartneLengkap(Store,sqlString){    
      var columnsheader= [{header: "Kode BP", dataIndex: 'kodebp'},
          {header: "NIK", dataIndex: 'nik',width:100},
          {header: "Nama", dataIndex: 'namabp',width:200},
          {header: "Perusahaan", dataIndex: 'namaperusahaan',width:200},
          {header: "Organisasi", dataIndex: 'namaorg',width:300},
          {header: "Jabatan", dataIndex: 'namajabatan',width:200},
      ];
      
      var arrayColumnsHeader=[];var pvalue;
      for (i = 0 ; i < columnsheader.length ;i++)
          {
              arrayColumnsHeader[i] =[columnsheader[i].dataIndex,columnsheader[i].header];
              
              if(columnsheader[i].header.substr(0, 4)=='Nama')
                  {
                      pvalue=columnsheader[i].dataIndex;
                  }
          }
      
      var storeTemp;
      if(Store==null){
        storeTemp = vm_hris_bpartnerLengkap_xmlstore;
      }else{
        storeTemp = Store;
      }
      var rb_Karyawan=new Ext.form.Radio({
        //xtype     : 'radio',
              name      : 'radio',
              itemId :'rbkaryawan',
              margin: '0 5 0 0',
              checked : true,
              width: 100,              
              boxLabel: 'Karyawan'
    });
    var rb_Customer=new Ext.form.Radio({
        //xtype     : 'radio',
              name      : 'radio',
              itemId :'rbcustomer',
              margin: '0 5 0 0',
              checked : false,              
              boxLabel: 'Customer'
    });
    rb_Karyawan.on('check',function(a,b){
        if(b==true)
            {
                storeTemp.setBaseParam( 'sql',sqlString );
                pbar.wait({interval: 100});
                storeTemp.load();
            }
    });
    rb_Customer.on('check',function(a,b){
        if(b==true)
            {                   
                storeTemp.setBaseParam( 'sql', SqlCustom_vm_hris_bpartnerLengkap.selectCustomer());
                pbar.wait({interval: 100});
                storeTemp.load();
            }
    });
   
    var pbar = new Ext.ProgressBar({
       width:150
    });
      var grid = new Ext.grid.GridPanel({                
               /* viewConfig: {
                    forceFit: true
                },*/
                region:'center',
                store: storeTemp,
                colModel: new Ext.grid.ColumnModel({
                   defaultSortable: true,
                   columns: columnsheader
                }),
                tbar: [get_GridFilterToolbar(arrayColumnsHeader,storeTemp,pvalue),'-',rb_Karyawan,rb_Customer],
                bbar: ['->',pbar]
            });
      /*grid.on('rowdblclick', function(grid, rowIndex, e) {
          var row = grid.getStore().getAt(rowIndex);
           record_vm_hris_bpartnerLengkapRecord.set(arrayField_vm_hris_bpartnerLengkap[0],row.get(arrayField_vm_hris_bpartnerLengkap[0]));
           record_vm_hris_bpartnerLengkapRecord.set(arrayField_vm_hris_bpartnerLengkap[1],row.get(arrayField_vm_hris_bpartnerLengkap[1]));
           record_vm_hris_bpartnerLengkapRecord.set(arrayField_vm_hris_bpartnerLengkap[2],row.get(arrayField_vm_hris_bpartnerLengkap[2]));
           record_vm_hris_bpartnerLengkapRecord.set(arrayField_vm_hris_bpartnerLengkap[3],row.get(arrayField_vm_hris_bpartnerLengkap[3]));
           record_vm_hris_bpartnerLengkapRecord.set(arrayField_vm_hris_bpartnerLengkap[4],row.get(arrayField_vm_hris_bpartnerLengkap[4]));
           record_vm_hris_bpartnerLengkapRecord.set(arrayField_vm_hris_bpartnerLengkap[5],row.get(arrayField_vm_hris_bpartnerLengkap[5]));
           record_vm_hris_bpartnerLengkapRecord.set(arrayField_vm_hris_bpartnerLengkap[6],row.get(arrayField_vm_hris_bpartnerLengkap[6]));
           record_vm_hris_bpartnerLengkapRecord.set(arrayField_vm_hris_bpartnerLengkap[7],row.get(arrayField_vm_hris_bpartnerLengkap[7]));
           record_vm_hris_bpartnerLengkapRecord.set(arrayField_vm_hris_bpartnerLengkap[8],row.get(arrayField_vm_hris_bpartnerLengkap[8]));
           record_vm_hris_bpartnerLengkapRecord.set(arrayField_vm_hris_bpartnerLengkap[9],row.get(arrayField_vm_hris_bpartnerLengkap[9]));
           record_vm_hris_bpartnerLengkapRecord.set(arrayField_vm_hris_bpartnerLengkap[10],row.get(arrayField_vm_hris_bpartnerLengkap[10]));
         /* record_vm_hris_bpartnerLengkapRecord.set('kodebp',row.get('kodebp'));
          record_vm_hris_bpartnerLengkapRecord.set('nik',row.get('nik'));
          record_vm_hris_bpartnerLengkapRecord.set('namabp',row.get('namabp'));
          record_vm_hris_bpartnerLengkapRecord.set('kodeperusahaan',row.get('kodeperusahaan'));
          record_vm_hris_bpartnerLengkapRecord.set('namaperusahaan',row.get('namaperusahaan'));
          record_vm_hris_bpartnerLengkapRecord.set('kodeorg',row.get('kodeorg'));
          record_vm_hris_bpartnerLengkapRecord.set('namaorg',row.get('namaorg'));
          record_vm_hris_bpartnerLengkapRecord.set('kodejabatan',row.get('kodejabatan'));
          record_vm_hris_bpartnerLengkapRecord.set('namajabatan',row.get('namajabatan'));
          record_vm_hris_bpartnerLengkapRecord.set('kodecostcenter',row.get('kodecostcenter'));
          record_vm_hris_bpartnerLengkapRecord.set('userid',row.get('userid'));*/
          /*if(fieldKodebp!=null){
            fieldKodebp.setValue(row.get('kodebp'));
          }
          if(fieldNik!= null){
            fieldNik.setValue(row.get('nik'));
          }
          if(fieldNamaBp!=null){
            fieldNamaBp.setValue(row.get('namabp'));
          }          
          if(fieldKodePerusahaan!= null){
            fieldKodePerusahaan.setValue(row.get('kodeperusahaan'));
          }
          if(fieldNamaPerusahaan!= null){
            fieldNamaPerusahaan.setValue(row.get('namaperusahaan'));
          }
          if(fieldKodeOrg!=null){
            fieldKodeOrg.setValue(row.get('kodeorg'));
          }
          if(fieldNamaOrg!=null){
            fieldNamaOrg.setValue(row.get('namaorg'));
          }
          if(fieldKodeJabatan!=null){
            fieldKodeJabatan.setValue(row.get('kodejabatan'));
          }
          if(fieldNamaJabatan!=null){
            fieldNamaJabatan.setValue(row.get('namajabatan'));
          }
          if(fieldKodeCostCenter!=null){
            fieldKodeCostCenter.setValue(row.get('kodecostcenter'));
          }*/
          //console.log(form.getForm().getValues());
     //     formBrowse.close();
    //  });*/
    var formBrowse = new Ext.Window({
        title:'Daftar Bussiness Partner',
        layout:'border',
        width:800,
        height: 400,
        modal:true,
        items:[grid]
    });
    pbar.wait({interval: 100});

    storeTemp.on('load',function(){        
        pbar.reset();
        pbar.updateProgress(1,'complete');
    });
    storeTemp.load();
    formBrowse.show();    
    return (new ListObjectInWIndowBrowse(grid, formBrowse));
}
