/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function convertFormValues_toXMLString(form,rootName, tableName,arrayXmlSisipan,prettyPrint,indent,setHeaderXML){
    
     var params = Ext.urlDecode(form.getForm().getValues(true));
          //params.arraycoba1 = [1,2,3,4,5];
          //params.arraycoba2 = [5,4,3,2,1];
     var newLine = prettyPrint ? '\n' : '';
     var tab = prettyPrint ? Ext.util.Format.replicate('\t', indent) : '';
     var result = setHeaderXML ? '<?xml version=\"1.0\" encoding="\ISO-8859-1\"?>' :''; 
     result +=tab+ '<'+rootName+' tablename=\"'+tableName+'\" >'+newLine;
     for (var key in params) {
        if (params.hasOwnProperty(key)) {
            result=tab+result+'<'+key+'>'+newLine;
            result=result+Ext.util.Format.trim(Ext.util.Format.escapeXml(params[key]))+newLine;
            result = result + '</'+key+'>'+newLine;
        }
     }

     if (arrayXmlSisipan != null){
        for(i=0;i<arrayXmlSisipan.length;i++){
            result = result+arrayXmlSisipan[i];
        }
     }
     result = result + tab+'</'+rootName+'>';
     return result;
}

