/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



Ext.override(Ext.data.Record, {
    xmlSerialize: function(markModified, onlyModified, prettyPrint, showType, indent) {
	indent = indent ? indent : 0;
    	var result = '', nodeList = '', recordModified = false, fieldModified = false;
    	var element = this.store.reader.meta.record;
    	var newLine = prettyPrint ? '\n' : '';
    	var parentTab = prettyPrint ? Ext.util.Format.replicate('\t', indent) : '';
    	var childTab = prettyPrint ? Ext.util.Format.replicate('\t', indent + 1) : '';
	this.fields.each(function(field) {
			fieldModified = this.modified && typeof(this.modified[field.name]) !== 'undefined';
			if (fieldModified && !recordModified) { recordModified = true; };
			if (!onlyModified || onlyModified && fieldModified) {
				nodeList += childTab + '<' + field.name;
				if (showType && field.type && field.type !== 'auto') {
					nodeList += ' type=\"' + field.type + '\"';
				};
				if (markModified && fieldModified) {
					nodeList += ' modified=\"true\"';
				};
                                
				nodeList += '>'
					+  Ext.util.Format.trim(Ext.util.Format.escapeXml(parseToDateTimeOnly(this.get(field.name))))
					+ '</' + field.name + '>' + newLine;
   			};
		}, this);
		result = parentTab + '<' + element;
		if (markModified && recordModified) {
			result += ' modified=\"true\"';
		};
		result += ' id=\"' + Ext.util.Format.trim(this.id) + '\"'
		if (nodeList) {
			result += '>' + newLine + nodeList + parentTab + '</' + element + '>';
		} else {
			result += ' />';
		};
     	return result;
    }
});
Ext.override(Ext.data.Store, {
	xmlSerialize: function(arrayXmlSisipan,tableType,tableName,markModified, onlyModified, prettyPrint, showType, indent,setHeaderXML) {
		indent = indent ? indent : 0;
		var result = '', nodeList = '', recordModified = false, storeModified = false;
		//var element = this.reader.meta.record + 'List';
                var element = tableType;
		var newLine = prettyPrint ? '\n' : '';
    	        var tab = prettyPrint ? Ext.util.Format.replicate('\t', indent) : '';
		this.data.each(function(record) {
			recordModified = this.modified && this.modified[0];
			if (recordModified && !storeModified) { storeModified = true; };
			if (!onlyModified || onlyModified && recordModified) {
				nodeList += record.xmlSerialize(markModified, onlyModified, prettyPrint, showType, indent + 1) + newLine;
			};
		}, this);
                result = setHeaderXML ? '<?xml version=\"1.0\" encoding="\ISO-8859-1\"?>' :''; 
		result += tab + '<' + element + ' tablename=\"'+tableName+'\" ';
		if (markModified && storeModified) {
			result += ' modified=\"true\"';
		};
		if (nodeList) {
			result += '>' +newLine + nodeList + tab ;
                        if (arrayXmlSisipan!=null ){
                            for(i=0;i<arrayXmlSisipan.length;i++){
                                result = result+arrayXmlSisipan[i];
                            }
                        }                            
                        result += '</' + element + '>';
		} else {
			result += ' />';
		};
		return result;
	}
});





