/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.util.Format.replicate = function(str, times) {
	var out = '';
	for (var i = 0; i < times; i++) { out += str; };
	return out;
};

