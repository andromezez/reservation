/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * plug Indonesian currency renderer into formatter
 */
Ext.util.Format.IndonesianCurrency = function(v)
{
	v = (Math.round((v-0)*100))/100;
	v = (v == Math.floor(v)) ? v + ".00" : ((v*10 == Math.floor(v*10)) ? v + "0" : v);
	return ('Rp. ' + v);//.replace(/\./, ',');
};

Ext.util.Format.CurrencyFactory = function(decimalNumber, decSeparator, thousandSeparator, currencySymbol, symbolInFront) {
    return function(n) {
        decimalNumber = Math.abs(decimalNumber) + 1 ? decimalNumber : 2;
        decSeparator = decSeparator || ".";
        thousandSeparator = thousandSeparator || ",";

        var m = /(\d+)(?:(\.\d+)|)/.exec(n + ""),
            x = m[1].length > 3 ? m[1].length % 3 : 0;

        if(symbolInFront){
            return (n < 0? '-' : '') // preserve minus sign
                + currencySymbol + " "
                + (x ? m[1].substr(0, x) + thousandSeparator : "")
                + m[1].substr(x).replace(/(\d{3})(?=\d)/g, "$1" + thousandSeparator)
                + (decimalNumber? decSeparator + (+m[2] || 0).toFixed(decimalNumber).substr(2) : "");
        }else{
            return (n < 0? '-' : '') // preserve minus sign
                + (x ? m[1].substr(0, x) + thousandSeparator : "")
                + m[1].substr(x).replace(/(\d{3})(?=\d)/g, "$1" + thousandSeparator)
                + (decimalNumber? decSeparator + (+m[2] || 0).toFixed(decimalNumber).substr(2) : "")
                + " " + currencySymbol;
        }
    };
};