/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
var m_pembebananRecord = Ext.data.Record.create([
            'kodepembebanan',
            'namapembebanan'
        ]);
        var m_pembebanan_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{
                tablename:'m_pembebanan',
                tableoperation:'READ_ALL_ROW'
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodepembebanan'
          }, m_pembebananRecord),
          autoLoad: false
        });

