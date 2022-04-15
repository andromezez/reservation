/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function CustomBtnCRUD (beforeClickBaru,afterClickBaru,beforeClickUbah,afterClickUbah,
        beforeClickHapus,afterClickHapus,beforeClickSimpan,afterClickSimpan,
        beforeClickBatal,afterClickBatal,beforeClickPrint,afterClickPrint) {
            this.beforeClickBaru = beforeClickBaru;
            this.afterClickBaru = afterClickBaru
            this.beforeClickUbah = beforeClickUbah;
            this.afterClickUbah = afterClickUbah;
            this.beforeClickHapus = beforeClickHapus;
            this.afterClickHapus = afterClickHapus;
            this.beforeClickSimpan = beforeClickSimpan;
            this.afterClickSimpan = afterClickSimpan;
            this.beforeClickBatal = beforeClickBatal;
            this.afterClickBatal = afterClickBatal;
            this.beforeClickPrint = beforeClickPrint;
            this.afterClickPrint = afterClickPrint;
      }

function DataSubmit (url,getParamsFunc) {
    this.url=url;
    this.getParams = getParamsFunc;   
}
function RequestReport (url,reportName) {
    this.url=url;
    this.params = {
        reportname:reportName
    };   
}
function setCRUDFormButton(form,xmlstore,grid,customBtnCRUD,dataSubmit,requestReport){

    var btnBaru = new Ext.Button ({
              //xtype: 'button',
              text: 'BARU',
              itemId :'btn_baru',
              disabled : false/*,
              handler: function(){
                //form.getComponent('can_enable_visible_readonly').setReadOnly(false);                                
               // alert(form.getComponent('formmode').value);
               //console.log(form.getForm().getValues());
                //alert(form.getComponent('can_enable_visible_readonly'));
              }*/
              });
    var btnUbah = new Ext.Button ({
              //xtype: 'button',
              text: 'UBAH',
              itemId :'btn_ubah',
              disabled : false/*,
              handler: function(){}*/
              });
    var btnSimpan = new Ext.Button ({
              //xtype: 'button',
              text: 'SIMPAN',
              itemId :'btn_simpan',
              disabled : true/*,
              handler: function(){}*/
              });
    var btnHapus = new Ext.Button ({
              //xtype: 'button',
              text: 'HAPUS',
              itemId :'btn_hapus',
              disabled : false/*,
              handler: function(){}*/
              });
    var btnBatal = new Ext.Button ({
              //xtype: 'button',
              text: 'BATAL',
              itemId :'btn_batal',
              disabled : true/*,
              handler: function(){}*/
              });
    var btnPrint = new Ext.Button ({
              //xtype: 'button',
              text: 'PRINT LAPORAN',
              itemId :'btn_print',
              disabled : false/*,
              handler: function(){}*/
             });
            
    btnBaru.on('click', function() {
          customBtnCRUD.beforeClickBaru ();
          form.getForm().reset();
          enableDisableAllFieldUnderContainer(form,'enable');
          form.getComponent('formmode').setValue("INSERT");
          btnBaru.disable();
          btnUbah.disable();
          btnHapus.disable();
          btnSimpan.enable();
          btnBatal.enable();
          if(grid!=null)
            grid.disable();
          customBtnCRUD.afterClickBaru();
    });
    btnUbah.on('click', function() {        
        var keluar = customBtnCRUD.beforeClickUbah();
        if(keluar) return;
        enableDisableAllFieldUnderContainer(form,'enable');
        form.getComponent('formmode').setValue("UPDATE");
        btnBaru.disable();
        btnUbah.disable();
        btnHapus.disable();
        btnSimpan.enable();
        btnBatal.enable();
        if(grid!=null)
            grid.disable();
        customBtnCRUD.afterClickUbah();
    });
    btnHapus.on('click', function() {
        var keluar = customBtnCRUD.beforeClickHapus();
        if(keluar) return;
        Ext.Msg.confirm('Konfirmasi!', 'Anda yakin ingin menghapus?', function(btn, text){
           if (btn == 'yes'){
                enableDisableAllFieldUnderContainer(form,'enable');
                if(grid!=null)
                    grid.disable();
                form.getComponent('formmode').setValue("DELETE");
                form.getEl().mask('Delete...','x-mask-loading');
                Ext.Ajax.request({
                    url: dataSubmit.url,
                    params: dataSubmit.getParams(),
                    success: function(response,opt) {
                        var jsonHasil =  Ext.util.JSON.decode(
                            response.responseText
                        );
                        if (jsonHasil.success){
                            xmlstore.load();
                            form.getForm().reset();
                            form.getComponent('formmode').setValue("READ");
                            form.getEl().unmask();
                            Ext.Msg.show({
                                       title: 'Success',
                                       msg: jsonHasil.successmsg,
                                       icon: Ext.Msg.INFO,
                                       buttons: Ext.MessageBox.OK
                                    });
                            enableDisableAllFieldUnderContainer(form,'disable');
                            if(grid!=null)
                                grid.enable();
                            customBtnCRUD.afterClickHapus(true);
                        }else{
                            form.getEl().unmask();
                            Ext.Msg.show({
                                       title: 'Error!',
                                       msg: jsonHasil.errormsg,
                                       icon: Ext.Msg.ERROR,
                                       buttons: Ext.MessageBox.OK
                                    });
                            enableDisableAllFieldUnderContainer(form,'disable');
                            if(grid!=null) grid.enable();
                            customBtnCRUD.afterClickHapus(false);
                        }
                    },
                    failure: function(response,opt) {
                        form.getEl().unmask();
                        Ext.Msg.alert('server-side failure with status code:', response.status);
                    }
                });
           } else {
              // abort, abort!
           }
        });
        

        /*          form.getForm().submit({
                    success: function(formsubmitted, action) {
                        xmlstore.load();
                        form.getForm().reset();
                        form.getComponent('formmode').setValue("READ");
                        //btnBaru.disable();
                        //btnUbah.disable();
                        //btnHapus.disabled=true;
                        //btnSimpan.enable();
                        //btnBatal.enable();
                        Ext.Msg.alert('Success', action.result.successmsg);
                        enableDisableAllFieldUnderContainer(form,'disable');
                        grid.enable();
                        customBtnCRUD.afterClickHapus(true);
                    },
                    failure: function(formsubmitted, action){
                        if (action.failureType == Ext.form.Action.CLIENT_INVALID) {
                            Ext.Msg.alert("Cannot submit","Some fields are still invalid");
                        } else if (action.failureType === Ext.form.Action.CONNECT_FAILURE) {
                            Ext.Msg.alert('Failure', 'Server communication failure: '+
                                action.response.status+' '+action.response.statusText);
                        } else if (action.failureType === Ext.form.Action.SERVER_INVALID){
                            Ext.Msg.alert('Warning', action.result.errormsg);
                        }
                        enableDisableAllFieldUnderContainer(form,'disable');
                        grid.enable();
                        customBtnCRUD.afterClickHapus(false);
                    }
                  });*/
        //readOnlyAllFormField(form,false);
        
    });
    btnSimpan.on('click', function() {                          
                  var keluar = customBtnCRUD.beforeClickSimpan();
                  if(keluar) return;
                  form.getEl().mask('Save...','x-mask-loading');
                  Ext.Ajax.request({
                     url: dataSubmit.url,
                     params: dataSubmit.getParams(),
                     success: function(response,opt) {                        
                        var jsonHasil =  Ext.util.JSON.decode(
                          response.responseText
                        );
                        if (jsonHasil.success){
                            xmlstore.load();
                            form.getComponent('formmode').setValue("READ");
                            enableDisableAllFieldUnderContainer(form,'disable');
                            btnBaru.enable();
                            btnUbah.enable();
                            btnHapus.enable();
                            btnSimpan.disable();
                            btnBatal.disable();
                            if(grid!=null) grid.enable();
                            form.getEl().unmask();
                            Ext.Msg.show({
                               title: 'Success',
                               msg: jsonHasil.successmsg,
                               icon: Ext.Msg.INFO,
                               buttons: Ext.MessageBox.OK
                            });
                            customBtnCRUD.afterClickSimpan(true);
                        }else{
                            form.getEl().unmask();
                            Ext.Msg.show({
                               title: 'Error!',
                               msg: jsonHasil.errormsg,
                               icon: Ext.Msg.ERROR,
                               buttons: Ext.MessageBox.OK
                            });
                            customBtnCRUD.afterClickSimpan(false);
                        }                                                
                     },
                     failure: function(response,opt) {
                         form.getEl().unmask();
                         Ext.Msg.alert('server-side failure with status code:', response.status);
                     }
                  });
                  
                  /*form.getForm().submit({
                    success: function(formsubmitted, action) {
                        xmlstore.load();
                        form.getComponent('formmode').setValue("READ");
                        enableDisableAllFieldUnderContainer(form,'disable');
                        btnBaru.enable();
                        btnUbah.enable();
                        btnHapus.enable();
                        btnSimpan.disable();
                        btnBatal.disable();
                        grid.enable();
                        Ext.Msg.alert('Success', action.result.successmsg);
                        customBtnCRUD.afterClickSimpan(true);
                    },
                    failure: function(formsubmitted, action){
                        if (action.failureType == Ext.form.Action.CLIENT_INVALID) {
                            Ext.Msg.alert("Cannot submit","Some fields are still invalid");
                        } else if (action.failureType === Ext.form.Action.CONNECT_FAILURE) {
                            Ext.Msg.alert('Failure', 'Server communication failure: '+
                                action.response.status+' '+action.response.statusText);
                        } else if (action.failureType === Ext.form.Action.SERVER_INVALID){
                            Ext.Msg.alert('Error', action.result.errormsg);
                        }
                        customBtnCRUD.afterClickSimpan(false);
                    }
                  });*/
    });
    btnBatal.on('click', function() {
        //console.log(form.getForm().getValues().toSource());
       // console.log(Ext.util.JSON.decode(form.getForm().getValues().toSource()));
        customBtnCRUD.beforeClickBatal();
        form.getForm().reset();
                 form.getComponent('formmode').setValue("READ");
                 enableDisableAllFieldUnderContainer(form,'disable');
                 btnBaru.enable();
                 btnUbah.enable();
                 btnHapus.enable();
                 btnSimpan.disable();
                 btnBatal.disable();
                 if(grid!=null) grid.enable();
                 customBtnCRUD.afterClickBatal();
    });
    btnPrint.on('click', function() {
       customBtnCRUD.beforeClickPrint();
       if (typeof requestReport != "undefined"){
            /*Ext.Ajax.request({
                url: requestReport.url,
                params: requestReport.params,
                success: function(response,opt) {                        

                 },
                 failure: function(response,opt) {                
                    Ext.Msg.alert('server-side failure with status code:', response.status);
                 }
            });*/
            window.open(requestReport.url+'?'+Ext.urlEncode(requestReport.params));
       }
       
        customBtnCRUD.afterClickPrint();
    });
             form.getTopToolbar().addItem(btnBaru);
             form.getTopToolbar().addItem(btnUbah);
             form.getTopToolbar().addItem(btnHapus);
             form.getTopToolbar().addItem(btnSimpan);
             form.getTopToolbar().addItem(btnBatal);
             form.getTopToolbar().addItem(btnPrint);
//var CRUDButton;
 //     return CRUDButton;
}
