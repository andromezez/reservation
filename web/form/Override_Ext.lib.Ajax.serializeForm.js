/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


Ext.lib.Ajax.serializeForm = function(_94){
    if(typeof _94=="string"){
        _94=(document.getElementById(_94)||document.forms[_94]);
    }
    var el,_96,val,_98,_99="",_9a=false;
    for(var i=0;i<_94.elements.length;i++){
        el=_94.elements[i];
        _98=_94.elements[i].disabled;
        _96=_94.elements[i].name;
        val=_94.elements[i].value;
        if(!_98&&_96){
            switch(el.type){
                case"select-one":
                case"select-multiple":
                    for(var j=0;j<el.options.length;j++){
                        if(el.options[j].selected){
                            if(Ext.isIE){
                                _99+=encodeURIComponent(_96)+"="+encodeURIComponent(el.options[j].attributes["value"].specified?el.options[j].value:el.options[j].text)+"&";
                            }else{
                                _99+=encodeURIComponent(_96)+"="+encodeURIComponent(el.options[j].hasAttribute("value")?el.options[j].value:el.options[j].text)+"&";
                            }
                        }
                    }
                    break;case"radio":
                case"checkbox":
                    if(el.checked){
                        _99+=encodeURIComponent(_96)+"=1&";
                    }else{
                        _99+=encodeURIComponent(_96)+"=0&";
                    }
                break;
                case"file":
                case undefined:
                case"reset":
                case"button":break;
                case"submit":
                    if(_9a==false){
                        _99+=encodeURIComponent(_96)+"="+encodeURIComponent(val)+"&";_9a=true;
                    }
                    break;
                default:
                    _99+=encodeURIComponent(_96)+"="+encodeURIComponent(val)+"&";
                    break;
            }
        }
    }
    _99=_99.substr(0,_99.length-1);
    return _99;
}