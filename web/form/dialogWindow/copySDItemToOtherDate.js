/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function showCopySDItemToOtherDateWindow(store_dtgl,store_sdItem,fromDate,selectedRecord_store_dtgl,isPengajuan){                
    var dt_from = new Ext.form.TextField({
        fieldLabel: 'From',
        name: 'datefrom',
        value:parseToDateOnly(fromDate),        
        readOnly:true,
        width:150
    });                 
    
    
    var dt_to = new Ext.ux.form.MultiSelect({        
        fieldLabel: 'to',
        name: 'multiselect',
        width: 150,                
        allowBlank:false,
        autoHeight:true,
        store:(function(){
                   var arrayData = [];
                   var indexSelected = store_dtgl.indexOf(selectedRecord_store_dtgl);
                   var j=0;
                   for(i=0;i<store_dtgl.getCount();i++){
                       if(i!=indexSelected ){       
                           if(store_dtgl.getAt(i).get('namatarif')==selectedRecord_store_dtgl.get('namatarif')){
                                arrayData[j] =[i,parseToDateOnly(store_dtgl.getAt(i).get('tglawal'))];
                                j++;
                           }
                       }
                   }
                   return arrayData;
               })()            
    });
    var note= new Ext.form.Label({
        width: 150,
        text : '*note : Hanya bisa dicopykan ke tanggal dengan jenis hari yang sama dengan tanggal asal.'
    });
    /*var dt_to = new Ext.form.ComboBox({        
        itemId:'dt_to',
        hiddenName: 'dt_to',        
        mode: 'local',
        store: dateStore,
        displayField:'datevalue',
        valueField:'index_store_dtgl',
        width:150
    });*/
    /*var dt_until = new Ext.form.ComboBox({                
        itemId:'dt_until',
        hiddenName: 'dt_until',        
        mode: 'local',
        store: dateStore,
        displayField:'datevalue',
        valueField:'index_store_dtgl',
        width:150
    });*/
    
    var formCopy = new Ext.Window({
        title:'Copy to',
        layout:'form',
        width:275,
        height: 300,        
        labelWidth:50,        
        plain: true,
        modal:true,        
        bodyStyle: 'padding:15px',        
        items:[dt_from,dt_to,note],
        buttons:[{
                text:'Simpan',
                handler: function(){
                    
                    var arrayRecordSdItemWantToCopy=[];
                    for(i=0;i<store_sdItem.getCount();i++){
                       arrayRecordSdItemWantToCopy[i]=store_sdItem.getAt(i);
                    }
                                        
                    var arraySelected_index_store_dtgl = dt_to.getValue().split(',');                    
                    
                    store_sdItem.clearFilter();
                    for(i=0;i<arraySelected_index_store_dtgl.length;i++){
                        var record_store_dtgl = store_dtgl.getAt(arraySelected_index_store_dtgl[i]);
                        for(j=0;j<arrayRecordSdItemWantToCopy.length;j++){
                            //clone the object
                            //var record_toAdded = eval ( arrayRecordSdItemWantToCopy[j].toSource() );
                            //var record_toAdded=null;
                            if(isPengajuan){
                                store_sdItem.insert( store_sdItem.getCount(), new sd_itempengajuanRecord({
                                    kodetp:record_store_dtgl.get('kodetp'),//arrayRecordSdItemWantToCopy[j].get('kodetp'),
                                    kodeitem:arrayRecordSdItemWantToCopy[j].get('kodeitem'),
                                    namaitem:arrayRecordSdItemWantToCopy[j].get('namaitem'),
                                    jumlahawal:arrayRecordSdItemWantToCopy[j].get('jumlahawal'),
                                    jumlahakhir:arrayRecordSdItemWantToCopy[j].get('jumlahakhir'),
                                    jamawal:arrayRecordSdItemWantToCopy[j].get('jamawal'),
                                    jamakhir:arrayRecordSdItemWantToCopy[j].get('jamakhir'),
                                    durasi:arrayRecordSdItemWantToCopy[j].get('durasi'),
                                    biaya:arrayRecordSdItemWantToCopy[j].get('biaya'),
                                    antrian:arrayRecordSdItemWantToCopy[j].get('antrian'),
                                    keterangan:arrayRecordSdItemWantToCopy[j].get('keterangan'),
                                    biayaperjam:arrayRecordSdItemWantToCopy[j].get('biayaperjam'),
                                    jumlahpeserta:arrayRecordSdItemWantToCopy[j].get('jumlahpeserta')
                                }));                                                
                               // record_toAdded.set('kodetp',record_store_dtgl.get('kodetp'));
                            }else{
                                store_sdItem.insert( store_sdItem.getCount(),  new sd_itemrealisasiRecord({
                                    kodetr:record_store_dtgl.get('kodetr'),//arrayRecordSdItemWantToCopy[j].get('kodetr'),
                                    kodeitem:arrayRecordSdItemWantToCopy[j].get('kodeitem'),
                                    namaitem:arrayRecordSdItemWantToCopy[j].get('namaitem'),                                    
                                    jumlahakhir:arrayRecordSdItemWantToCopy[j].get('jumlahakhir'),
                                    jamawal:arrayRecordSdItemWantToCopy[j].get('jamawal'),
                                    jamakhir:arrayRecordSdItemWantToCopy[j].get('jamakhir'),
                                    durasi:arrayRecordSdItemWantToCopy[j].get('durasi'),
                                    biaya:arrayRecordSdItemWantToCopy[j].get('biaya'),                                    
                                    keterangan:arrayRecordSdItemWantToCopy[j].get('keterangan'),
                                    biayaperjam:arrayRecordSdItemWantToCopy[j].get('biayaperjam'),
                                    statussebelum:arrayRecordSdItemWantToCopy[j].get('statussebelum'),
                                    statussesudah:arrayRecordSdItemWantToCopy[j].get('statussesudah'),
                                    jumlahpeserta:arrayRecordSdItemWantToCopy[j].get('jumlahpeserta')
                                }));

                               // record_toAdded.set('kodetr',record_store_dtgl.get('kodetr'));
                            }
                            //store_sdItem.insert( store_sdItem.getCount(),record_toAdded);
                        }
                        
                    }
                    if(isPengajuan){
                       store_sdItem.filter('kodetp', selectedRecord_store_dtgl.get('kodetp'));
                    }else{
                       store_sdItem.filter('kodetr', selectedRecord_store_dtgl.get('kodetr'));
                    }                    
                    formCopy.close();
                    
                }
            },{
                text: 'Batal',
                handler: function(){

                   formCopy.hide();

                }
            }
        ]

    });
    formCopy.show();
}
