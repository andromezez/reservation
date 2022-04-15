/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function enableDisableSingleItem(item,status){   
    if( (item.getXType()== 'checkbox') || (item.getXType()== 'checkboxgroup') ||
        (item.getXType()== 'combo') || (item.getXType()== 'compositefield') ||
        (item.getXType()== 'datefield') || (item.getXType()== 'htmleditor') ||
        (item.getXType()== 'numberfield') || (item.getXType()== 'radio') ||
        (item.getXType()== 'radiogroup') || (item.getXType()== 'textarea') ||
        (item.getXType()== 'textfield') || (item.getXType()== 'timefield') ||
        (item.getXType()== 'trigger')
    ){
        if (status == 'enable'){
            item.enable();
        }else if(status == 'disable'){
            item.disable();
        }        
    }else if((item.getXType()== 'editorgrid')){
        //console.log(item.getItemId());
        //alert('testing : '+ arrayItems[i].getItemId());
        if (status == 'enable'){
            if(item.getItemId()!='gridAcc'){
                item.getTopToolbar().enable();
            }
            column_model = item.getColumnModel();
            for(i=0;i<column_model.getColumnCount();i++){
               column_model.setEditable( i, true);
            }
            //arrayItems[i].enable();
        }else if(status == 'disable'){
            item.getStore().removeAll();
            if(item.getItemId()!='gridAcc'){
                item.getTopToolbar().disable();
            }
            column_model = item.getColumnModel();
            for(i=0;i<column_model.getColumnCount();i++){
               column_model.setEditable( i, false);
            }
            //arrayItems[i].disable();
        }
    }else if((item.getXType()== 'form')||(item.getXType()== 'panel')
        ||(item.getXType()== 'container')||(item.getXType()== 'fieldset')){
        findUnderContainer2(item,status);
    }
}
function findUnderContainer2(container,status){
    container.items.each(function(item) {
          enableDisableSingleItem(item,status);
    });
}
function findUnderContainer(container,status){
    var arrayItems = container.findByType( 'checkbox', false );
    var i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }        
        i++;
    }
    arrayItems = container.findByType( 'checkboxgroup', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'combo', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'compositefield', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'datefield', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'htmleditor', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'numberfield', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'radio', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'radiogroup', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'textarea', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'textfield', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'timefield', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'trigger', false );
    i = 0;
    while(i<arrayItems.length){
        if (status == 'enable'){
            arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].disable();
        }
        i++;
    }
    arrayItems = container.findByType( 'editorgrid', false );
    i = 0;
    while(i<arrayItems.length){
        console.log(arrayItems[i].getItemId());
        //alert('testing : '+ arrayItems[i].getItemId());
        if (status == 'enable'){
            arrayItems[i].getTopToolbar().enable();
            column_model = arrayItems[i].getColumnModel();
            for(i=0;i<column_model.getColumnCount();i++){
               column_model.setEditable( i, true);
            }
            //arrayItems[i].enable();
        }else if(status == 'disable'){
            arrayItems[i].getStore().removeAll();
            arrayItems[i].getTopToolbar().disable();
            column_model = arrayItems[i].getColumnModel();
            for(i=0;i<column_model.getColumnCount();i++){
               column_model.setEditable( i, false);
            }
            //arrayItems[i].disable();            
        }
        i++;
    }
}

function findFieldset(container,status){
    arrayItems = container.findByType( 'fieldset', false );
    i = 0;
    while(i<arrayItems.length){
        //findUnderContainer(arrayItems[i], status);
        arrayItems[i].items.each(function(item) {
          enableDisableSingleItem(item,status);
        });
        i++;
    }
}

function enableDisableAllFieldUnderContainer(container,status){
    findUnderContainer2(container, status);
    //findFieldset(container,status);
}
