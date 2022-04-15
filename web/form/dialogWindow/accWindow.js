/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function showAccWindow(pkodeform,pnamaform,pprioritas,pketerangan,record,form,dataSubmit){
    var txt_namaform = new Ext.form.TextField({
        fieldLabel: 'Nama Form',
        name: 'namaform',
        value:pnamaform,
        disabled:true,
        anchor:'95%'
    });
    var txt_prioritas = new Ext.form.TextField({
        fieldLabel: 'Prioritas',
        name: 'prioritas',
        value:pprioritas,
        disabled:true,
        anchor:'35%'
    });
    var txt_keterangan = new Ext.form.TextField({
        fieldLabel: 'Keterangan',
        name: 'keterangan',
        value:pketerangan,
        disabled:true,
        anchor:'95%'
    });
    var txt_userid = new Ext.form.TextField({
        fieldLabel: 'User ID',
        name: 'userid',
        value:record.get('userid'),        
        anchor:'50%',
        disabled:true
    });
    var txt_password = new Ext.form.TextField({
        fieldLabel: 'Password',
        name: 'password',
        inputType:'password',
        value:'',        
        anchor:'50%'
    });
    var record_temp = record;
        
    var formAcc = new Ext.Window({
        title:'Acc Form',
        layout:'form',
        width:600,
        height: 300,        
        plain: true,
        modal:true,
        bodyStyle: 'padding:15px',
        items:[txt_namaform,txt_prioritas,txt_keterangan,txt_userid,txt_password],
        buttons:[{
                text:'Simpan',
                handler: function(){
                    var store = customSelect_vm_muser_konfigurasiacc_xmlstore (
                        SqlCustom_vm_muser_konfigurasiacc.selectFilterBypuserid_ppass_pkodeform_pprioritas_pjbtn(
                            txt_userid.getValue(), txt_password.getValue(), pkodeform, pprioritas,record_temp.get('kodejabatan')));
                    store.on('load', function() {
                        if(store.getCount()>0){        
                            var statusacc_before = record_temp.get('statusacc');
                            if ((record_temp.get('statusacc') != null) && (record_temp.get('statusacc') != 0)){
                                record_temp.set('statusacc',0);
                            }else{
                                record_temp.set('statusacc',1);
                            }                         
                            if ((typeof form != "undefined")){
                                if(form.getComponent('formmode').getValue()=='READ'){
                                    //form.getComponent('formmode').setValue("UPDATE");                                    
                                    Ext.Ajax.request({
                                        url: dataSubmit.url,
                                        params: {
                                            tablename:'m_acc',
                                            tableoperation:'UPDATE_ONE_ROW',
                                            kodeform:record_temp.get('kodeform'),
                                            kodejabatan:record_temp.get('kodejabatan'),
                                            kodetransaksi:record_temp.get('kodetransaksi'),
                                            prioritas:record_temp.get('prioritas'),
                                            statusacc:record_temp.get('statusacc'),
                                            tglacc:getCurrentDate(),
                                            userid:record_temp.get('userid')
                                        },
                                        success: function(response,opt) {
                                            var jsonHasil =  Ext.util.JSON.decode(
                                                response.responseText
                                            );
                                            if (jsonHasil.success){                                                                                                
                                                //form.getComponent('formmode').setValue("READ");
                                                formAcc.getEl().unmask();
                                                Ext.Msg.show({
                                                           title: 'Success',
                                                           msg: jsonHasil.successmsg,
                                                           icon: Ext.Msg.INFO,
                                                           buttons: Ext.MessageBox.OK
                                                        });
                                                formAcc.close();
                                                //enableDisableAllFieldUnderContainer(form,'disable');                                                                                                
                                            }else{
                                                //form.getComponent('formmode').setValue("READ");
                                                record_temp.set('statusacc',statusacc_before);
                                                formAcc.getEl().unmask();
                                                Ext.Msg.show({
                                                           title: 'Error!',
                                                           msg: jsonHasil.errormsg,
                                                           icon: Ext.Msg.ERROR,
                                                           buttons: Ext.MessageBox.OK
                                                        });
                                                //enableDisableAllFieldUnderContainer(form,'disable');                                                
                                            }
                                        },
                                        failure: function(response,opt) {
                                            record_temp.set('statusacc',statusacc_before);
                                            formAcc.getEl().unmask();
                                            Ext.Msg.alert('server-side failure with status code:', response.status);
                                        }
                                    });                                    
                                }else{
                                    formAcc.getEl().unmask();
                                    formAcc.close();
                                }
                            }else{                                
                                formAcc.getEl().unmask();
                                formAcc.close();
                            }
                            //Ext.MessageBox.hide();                          
                        }else{
                            //Ext.MessageBox.hide();
                            formAcc.getEl().unmask();
                            Ext.Msg.show({
                               title: 'Error!',
                               msg: 'User id dan password anda tidak cocok',
                               icon: Ext.Msg.ERROR,
                               buttons: Ext.MessageBox.OK
                            });
                        }
                    });
                    store.load();
                    //Ext.MessageBox.wait('process...');
                    formAcc.getEl().mask('Process...','x-mask-loading');
                }
            },{
                text: 'Batal',
                handler: function(){

                   formAcc.hide();

                }
            }
        ]

    });
    formAcc.show();
}

