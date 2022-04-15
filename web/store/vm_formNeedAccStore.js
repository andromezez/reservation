/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var vm_formNeedAccRecord = Ext.data.Record.create([
    'kodeform', 'namaform'
]);
        var vm_formNeedAcc_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'vm_formNeedAcc',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodeform'
          }, vm_formNeedAccRecord),
          autoLoad: false
        });
