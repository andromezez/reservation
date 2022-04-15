/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function get_GridFilterToolbar(arrayColumn,store,pvalue){
   // alert('testing seblm array store');
    var arrayStore = new Ext.data.ArrayStore({
                     
                     fields: ['myId','displayText'],
                     data: arrayColumn
                     });
        //alert('testing sesudah array store');
    if (typeof pvalue == "undefined"){
      pvalue = arrayColumn[0][0];
    }
      var combo=new Ext.form.ComboBox({
                     typeAhead: true,
                     triggerAction: 'all',
                     lazyRender:true,
                     mode: 'local',
                     store: arrayStore,
                     valueField: 'myId',
                     displayField: 'displayText',
                     value:pvalue
                     //value : arrayColumn[1][1]
                    });
   
                 
    var TextSearchForm = new Ext.form.TriggerField({
       itemId :'userid',
       name: 'userid',
       value:'',       
       triggerClass: 'x-form-search-trigger'
   });
   TextSearchForm.onTriggerClick = function(e){
      
       store.filter(combo.getValue(),TextSearchForm.getValue(),true,false);
       };
       return [combo,TextSearchForm];
               
        
}
