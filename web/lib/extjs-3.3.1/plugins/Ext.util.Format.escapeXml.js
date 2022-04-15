/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

Ext.util.Format.escapeXml = function(str) {
	function replaceChars(character) {
		switch (character) {
			case '<': return '&lt;';
			case '>': return '&gt;';
			case '&': return '&amp;';
			case "'": return '&apos;';
			case '"': return '&quot;';
			default: return '';
		};
	};
	return String(str).replace(/[<>&"']/g, replaceChars);
};
