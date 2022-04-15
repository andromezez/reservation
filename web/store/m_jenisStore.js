/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


var m_jenisRecord = Ext.data.Record.create([
            'kodejenis',
            'namajenis'
        ]);
        var m_jenis_xmlstore = new Ext.data.Store({
            proxy : new Ext.data.HttpProxy ({
                url: 'maincontroller'//?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid
            }),
            baseParams:{                
                tablename:'m_jenis',
                tableoperation:'READ_ALL_ROW'                
          },
          //url: 'maincontroller?formmode=READ&tableoperation=READ_ALL_ROW&formid='+pformid,
          reader: new Ext.data.XmlReader({
            record:'row',
            idPath:'kodejenis'
          }, m_jenisRecord),
          autoLoad: false
        });

