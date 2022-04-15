/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function showUbahPassWindow(puserid,pnamauser,pkodebp,paktif){                
var txt_userid = new Ext.form.TextField({
        fieldLabel: 'User Id',
        name: 'userid',
        value:puserid,
        disabled:true,
        anchor:'70%'
    });    
    
var txt_namauser = new Ext.form.TextField({
        fieldLabel: 'Nama User',
        name: 'namauser',
        value:pnamauser,
        disabled:true,
        anchor:'100%'
    }); 

var txt_passlama = new Ext.form.TextField({
        fieldLabel: 'Password Lama',
        name: 'passlama',        
        value:'',        
        anchor:'100%',
        inputType : 'password'
    });

var txt_passbaru = new Ext.form.TextField({
        fieldLabel: 'Password Baru',
        name: 'passbaru',
        value:'',        
        anchor:'100%',
        inputType : 'password'
    });

var txt_konfirpassbaru = new Ext.form.TextField({
        fieldLabel: 'Konfirm Pass Baru',
        name: 'konfirpassbaru',
        value:'',
        anchor:'100%',
        inputType : 'password'
    });
    
var formUbahPass = new Ext.Window({
        title:'Ubah Password',
        layout:'form',
        width:400,
        height: 220,        
        labelWidth:120,
        plain: true,
        modal:true,
        bodyStyle: 'padding:15px',
        items:[txt_userid,txt_namauser,txt_passlama,txt_passbaru,txt_konfirpassbaru],
        buttons:[{
                text:'Simpan',
                handler: function(){
                    if(txt_passbaru.getValue() == txt_konfirpassbaru.getValue()){
                        var store = get_m_userStore ('READ',
                            SqlCustom_m_user.get_selectFilterByUserIdAndPass(txt_userid.getValue(), txt_passlama.getValue()));                        
                        store.on('load', function() {
                            if(store.getCount()>0){
                                Ext.Ajax.request({
                                     url: 'maincontroller',
                                     params: {
                                            tablename:'m_user',
                                            tableoperation:'UPDATE_ONE_ROW',
                                            userid:txt_userid.getValue(), 
                                            kodebp:pkodebp, 
                                            pass:txt_konfirpassbaru.getValue(), 
                                            aktif:paktif
                                     },
                                     success: function(response,opt) {                        
                                        var jsonHasil =  Ext.util.JSON.decode(
                                          response.responseText
                                        );
                                        if (jsonHasil.success){
                                            
                                            formUbahPass.getEl().unmask();
                                            Ext.Msg.show({
                                               title: 'Success',
                                               msg: 'Ubah password berhasil',
                                               icon: Ext.Msg.SUCCESS,
                                               buttons: Ext.MessageBox.OK
                                            });

                                            formUbahPass.close();
                                            
                                        }else{
                                            formUbahPass.getEl().unmask();
                                            Ext.Msg.show({
                                               title: 'Error!',
                                               msg: jsonHasil.errormsg,
                                               icon: Ext.Msg.ERROR,
                                               buttons: Ext.MessageBox.OK
                                            });                                            
                                        }                                                
                                     },
                                     failure: function(response,opt) {
                                         formUbahPass.getEl().unmask();
                                         Ext.Msg.alert('server-side failure with status code:', response.status);
                                     }
                                });                                
                                
                            }else{
                                //Ext.MessageBox.hide();
                                formUbahPass.getEl().unmask();
                                Ext.Msg.show({
                                   title: 'Error!',
                                   msg: 'Password lama anda tidak cocok',
                                   icon: Ext.Msg.ERROR,
                                   buttons: Ext.MessageBox.OK
                                });
                            }
                        });
                        store.load();                    
                        formUbahPass.getEl().mask('Process...','x-mask-loading');
                    }else{
                        Ext.Msg.show({
                            title: 'Error!',
                            msg: 'Konfirm Pass Baru anda tidak cocok',
                            icon: Ext.Msg.ERROR,
                            buttons: Ext.MessageBox.OK
                        });
                        txt_konfirpassbaru.focus();
                    }
                    
                }
            },{
                text: 'Batal',
                handler: function(){

                   formUbahPass.hide();

                }
            }
        ]

    });
    formUbahPass.show();
}