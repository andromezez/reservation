/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function filterPermissionButtonForm(formObj,pview,padd,pupdate,pdelete,pprint,xmlstore,batal_trans){
      if(pview=="1"){
        xmlstore.load();
        if(padd=="1"){
            formObj.getTopToolbar().getComponent('btn_baru').enable();
        }else{
            formObj.getTopToolbar().getComponent('btn_baru').disable();
        }
        if(pupdate=="1"){
            formObj.getTopToolbar().getComponent('btn_ubah').enable();
        }else{
            formObj.getTopToolbar().getComponent('btn_ubah').disable();
        }
        if(pdelete=="1"){
            formObj.getTopToolbar().getComponent('btn_hapus').enable();
        }else{
            formObj.getTopToolbar().getComponent('btn_hapus').disable();
        }
        if(pprint=="1"){
            formObj.getTopToolbar().getComponent('btn_print').enable();
        }else{
            formObj.getTopToolbar().getComponent('btn_print').disable();
        }
        if(typeof batal_trans=="undefined"){
            
        }else{
            if(batal_trans=="1"){
                formObj.getTopToolbar().getComponent('btn_bataltrans').enable();
            }
            else{
            formObj.getTopToolbar().getComponent('btn_bataltrans').disable();
            }
        }
      }else{
        formObj.getTopToolbar().getComponent('btn_baru').disable();
        formObj.getTopToolbar().getComponent('btn_ubah').disable();
        formObj.getTopToolbar().getComponent('btn_hapus').disable();
        formObj.getTopToolbar().getComponent('btn_print').disable();
        formObj.getTopToolbar().getComponent('btn_bataltrans').disable();
      }      
}