/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function showApproveWindow(pformmode,pkodecostcenter,ppresentase,pnominal,puserid,record,form,dataSubmit){
    var approvStore = new Ext.data.ArrayStore({
               fields: ['id', 'approve_name'],
               data : [['1','approved'],['0','unapproved']]
            });
    var cmb_approval = new Ext.form.ComboBox({
        //xtype: 'combo',
        fieldLabel: 'Approve',
        itemId:'approval',
        hiddenName: 'approval',        
        mode: 'local',
        store: approvStore,
        value:'1',
        displayField:'approve_name',
        valueField:'id',
        anchor:'50%'
    });
    var txt_kodeCostCenter = new Ext.form.TextField({
        fieldLabel: 'Kode cost center',
        name: 'kodecostcenter',
        value:pkodecostcenter,
        disabled:true,
        anchor:'95%'
    });
    
    var txt_presentase = new Ext.form.NumberField({
        fieldLabel: 'Presentase',
        name: 'keterangan',
        value:ppresentase,
        disabled:true,
        anchor:'95%'
    });
    var txt_nominal = new Ext.form.NumberField({
        fieldLabel: 'Nominal',
        name: 'nominal',
        value:pnominal,
        disabled:true,
        anchor:'95%'
    });
    var txt_userid = new Ext.form.TextField({
        fieldLabel: 'User Id',
        name: 'userid',
        value:puserid,
        disabled:true,
        anchor:'50%'
    });    
    var txt_password = new Ext.form.TextField({
        fieldLabel: 'Password',
        name: 'password',
        inputType:'password',
        value:'',        
        anchor:'50%'
    });
    var record_temp = record;
        
    var formApp = new Ext.Window({
        title:'Approval Form',
        layout:'form',
        width:600,
        height: 300,        
        plain: true,
        modal:true,
        bodyStyle: 'padding:15px',
        items:[txt_kodeCostCenter,txt_presentase,txt_nominal,cmb_approval,txt_userid,txt_password],
        buttons:[{
                text:'Simpan',
                handler: function(){
                    var store = get_m_userStore (pformmode,
                        SqlCustom_m_user.get_selectFilterByUserIdAndPass(puserid, txt_password.getValue()));                        
                    store.on('load', function() {
                        if(store.getCount()>0){
                            var app_before = record_temp.get('approval');
                            record_temp.set('approval',cmb_approval.getValue());                            
                            //Ext.MessageBox.hide();
                            if ((typeof form != "undefined")){
                                if(form.getComponent('formmode').getValue()=='READ'){  
                                    var ptablename = '',pkodeheader='';
                                    if ((form.getComponent('formid').getValue()=='01-04-02') ||
                                        (form.getComponent('formid').getValue()=='01-04-01')){
                                        ptablename = 'd_costcenterpengajuan'; 
                                        pkodeheader =record_temp.get('kodepengajuan');
                                    }else if(form.getComponent('formid').getValue()=='01-04-03'){
                                        ptablename = 'd_costcenterrealisasi';
                                        pkodeheader = record_temp.get('koderealisasi');
                                    }
                                    Ext.Ajax.request({
                                        url: dataSubmit.url,
                                        params: {
                                            tablename:ptablename,
                                            tableoperation:'UPDATE_ONE_ROW',
                                            approval:record_temp.get('approval'),
                                            kodecostcenter:record_temp.get('kodecostcenter'),
                                            kodeheader:pkodeheader,
                                            nominal:record_temp.get('nominal'),
                                            presentase:record_temp.get('presentase'),
                                            userid:record_temp.get('userid')
                                        },
                                        success: function(response,opt) {
                                            var jsonHasil =  Ext.util.JSON.decode(
                                                response.responseText
                                            );
                                            if (jsonHasil.success){                                                                                                                                                
                                                formApp.getEl().unmask();
                                                Ext.Msg.show({
                                                           title: 'Success',
                                                           msg: jsonHasil.successmsg,
                                                           icon: Ext.Msg.INFO,
                                                           buttons: Ext.MessageBox.OK
                                                        });   
                                                formApp.close();
                                            }else{
                                                record_temp.set('approval',app_before);
                                                formApp.getEl().unmask();
                                                Ext.Msg.show({
                                                           title: 'Error!',
                                                           msg: jsonHasil.errormsg,
                                                           icon: Ext.Msg.ERROR,
                                                           buttons: Ext.MessageBox.OK
                                                        });                                                
                                            }
                                        },
                                        failure: function(response,opt) {
                                            record_temp.set('approval',app_before);
                                            formApp.getEl().unmask();
                                            Ext.Msg.alert('server-side failure with status code:', response.status);
                                        }
                                    });                                    
                                }else{
                                    formApp.getEl().unmask();
                                    formApp.close();
                                }
                            }else{
                                formApp.getEl().unmask();
                                formApp.close();
                            }
                        }else{
                            //Ext.MessageBox.hide();
                            formApp.getEl().unmask();
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
                    formApp.getEl().mask('Process...','x-mask-loading');
                }
            },{
                text: 'Batal',
                handler: function(){

                   formApp.hide();

                }
            }
        ]

    });
    formApp.show();
    return formApp;
}