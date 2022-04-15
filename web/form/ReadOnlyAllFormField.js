/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function readOnlyAllFormField(form,status){
    var arrayItems = form.findByType( 'checkbox', false );
    var i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'checkboxgroup', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'combo', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'compositefield', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'datefield', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'htmleditor', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'numberfield', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'radio', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'radiogroup', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'textarea', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'textfield', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'timefield', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
    arrayItems = form.findByType( 'trigger', false );
    i = 0;
    while(i<arrayItems.length){
        arrayItems[i].setReadOnly( status );
        i++;
    }
}