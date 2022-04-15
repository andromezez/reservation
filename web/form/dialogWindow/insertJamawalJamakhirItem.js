/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function insertJamawalJamakhirItem(pkodeitem,pkodebp,pjumlahawal,pjumlahakhir,pkodetp,pnamatarif,gridSDItem,pkodetr,pIE,pjenistransaksi){
    var txtBtn_datetime_jamawal = new Ext.form.TimeField({
        fieldLabel: 'Jam awal',
        minValue: '0:00',
        maxValue: '23:00',
        increment: 15,
        format:'H:i',
        anchor:'100%'
    });
    var txtBtn_datetime_jamakhir = new Ext.form.TimeField({
        fieldLabel: 'Jam akhir',
        minValue: '0:00',
        maxValue: '23:00',
        increment: 15,
        format:'H:i',
        anchor:'100%'
    });    
        
    var formJam = new Ext.Window({
        title:'Acc Form',
        layout:'form',
        width:350,
        height: 150,        
        plain: true,
        modal:true,
        bodyStyle: 'padding:15px',
        items:[txtBtn_datetime_jamawal,txtBtn_datetime_jamakhir],
        buttons:[{
                text:'Simpan',
                handler: function(){
                    /*String pkodebp,String pkodeitem,String pjamawal,
            String pjamakhir,String pnamatarif,int pjumlah*/
                    var objParams = {
                        kodebp:pkodebp,
                        kodeitem:pkodeitem,
                        jamawal:txtBtn_datetime_jamawal.getValue(),
                        jamakhir:txtBtn_datetime_jamakhir.getValue(),
                        namatarif:pnamatarif,
                        jumlahawal:pjumlahawal,
                        jumlahakhir:pjumlahakhir,
                        IE:pIE,
                        jenistransaksi:pjenistransaksi
                    };
                    //console.log(getTimeDifference(txtBtn_datetime_jamakhir.getValue(),txtBtn_datetime_jamawal.getValue()));
                    var storeItem = get_m_itemStore(SqlCustom_m_item.get_selectFilterByKodeitem(pkodeitem));
                    storeItem.on("load",function(){
                        if(getTimeDifference(txtBtn_datetime_jamakhir.getValue(),txtBtn_datetime_jamawal.getValue())<0){
                            formJam.getEl().unmask();
                            Ext.Msg.show({
                                title: 'Error!',
                                msg: 'Error jam akhir harus lebih besar atau sama dengan jam awal',
                                icon: Ext.Msg.ERROR,
                                buttons: Ext.MessageBox.OK
                            });
                        }else if( getTimeDifference(txtBtn_datetime_jamakhir.getValue(),txtBtn_datetime_jamawal.getValue())
                            < parseInt(storeItem.getAt(0).get('durasiminsewa'))){
                            formJam.getEl().unmask();
                            Ext.Msg.show({
                                title: 'Error!',
                                msg: 'Error durasi minimal sewa untuk '+storeItem.getAt(0).get('namaitem')+' adalah : '+storeItem.getAt(0).get('durasiminsewa') + ' menit',
                                icon: Ext.Msg.ERROR,
                                buttons: Ext.MessageBox.OK
                            });
                        }else{
                            var store = get_temp_sd_itempengajuanStore('READ','',false,objParams);
                            store.on('load', function() {
                                if(store.getCount()>0){                            
                                    //    record_temp.set('userid',store.getAt(0).get('userid'));    

                                      //form_mjenis.getComponent('kodejenis').setValue(row.get('kodejenis'));
                                    var sm = gridSDItem.getSelectionModel();
                                    var record_gridSDItem = sm.getSelected();
                                    gridSDItem.getStore().remove( record_gridSDItem ) ;
                                    for(i=0;i<store.getCount();i++){
                                        if((typeof pkodetr != "undefined") && (pkodetr!=null) ){
                                            gridSDItem.getStore().insert( gridSDItem.getStore().getCount(),
                                            new sd_itemrealisasiRecord({
                                                  kodetr : pkodetr,
                                                  kodeitem  : store.getAt(i).get('kodeitem'),
                                                  namaitem: store.getAt(i).get('namaitem'),                                          
                                                  jumlahakhir :parseInt( store.getAt(i).get('jumlahakhir')),
                                                  jamawal  : store.getAt(i).get('jamawal'),
                                                  jamakhir : store.getAt(i).get('jamakhir'),
                                                  durasi  : parseInt( store.getAt(i).get('durasi')),
                                                  biaya    :parseInt( store.getAt(i).get('biaya')),
                                                  antrian : 0,
                                                  keterangan  : store.getAt(i).get('keterangan'),
                                                  biayaperjam    :parseInt( store.getAt(i).get('biayaperjam')),
                                                  statussebelum:'',
                                                  statussesudah:''
                                             })
                                           );                                       
                                        }else{
                                            gridSDItem.getStore().insert( gridSDItem.getStore().getCount(),
                                            new sd_itempengajuanRecord({
                                                  kodetp : pkodetp,
                                                  kodeitem  : store.getAt(i).get('kodeitem'),
                                                  namaitem: store.getAt(i).get('namaitem'),
                                                  jumlahawal:parseInt( store.getAt(i).get('jumlahawal')),
                                                  jumlahakhir :parseInt( store.getAt(i).get('jumlahakhir')),
                                                  jamawal  : store.getAt(i).get('jamawal'),
                                                  jamakhir : store.getAt(i).get('jamakhir'),
                                                  durasi  : parseInt( store.getAt(i).get('durasi')),
                                                  biaya    :parseInt( store.getAt(i).get('biaya')),
                                                  antrian : 0,
                                                  keterangan  : store.getAt(i).get('keterangan'),
                                                  biayaperjam    :parseInt( store.getAt(i).get('biayaperjam'))
                                             })
                                           );
                                        }

                                    }                            
                                    //Ext.MessageBox.hide();
                                    formJam.getEl().unmask();
                                    formJam.close();
                                }else{
                                    //Ext.MessageBox.hide();
                                    formJam.getEl().unmask();
                                    Ext.Msg.show({
                                       title: 'Error!',
                                       msg: 'Error generate jam awal dan jam akhir',
                                       icon: Ext.Msg.ERROR,
                                       buttons: Ext.MessageBox.OK
                                    });
                                }
                            });
                            store.load();    
                        }
                        
                    });
                    storeItem.load();
                    //Ext.MessageBox.wait('process...');
                    formJam.getEl().mask('Process...','x-mask-loading');
                }
            },{
                text: 'Batal',
                handler: function(){

                   formJam.hide();

                }
            }
        ]

    });
    formJam.show();
    return formJam;
}

