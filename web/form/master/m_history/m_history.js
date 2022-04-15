/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function getFormHistory(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint)
{
  
    var columnsheader= [{header: "Kode Form", dataIndex: 'kodeform'},
          {header: "Tabel", dataIndex: 'tabel'},
          {header: "PK 1", dataIndex: 'pk1'},
          {header: "PK 1 Value", dataIndex: 'pk1value'},
          {header: "PK 2", dataIndex: 'pk2'},
          {header: "PK 2 Value", dataIndex: 'pk2value'},
          {header: "PK 3", dataIndex: 'pk3'},
          {header: "PK 3 Value", dataIndex: 'pk3value'},
          {header: "Keterangan", dataIndex: 'keterangan'},
          {header: "Created By", dataIndex: 'createdby'},
          {header: "Crated Date", dataIndex: 'createddate'},
          {header: "Updated By", dataIndex: 'updatedby'},
          {header: "Updated Date", dataIndex: 'updateddate'},
          {header: "Deleted By", dataIndex: 'deletedby'},
          {header: "Deleted Date", dataIndex: 'deleteddate'}];
     var grid = getGridView(ptitle, m_history_xmlstore,columnsheader,'center');
    // filterPermissionButtonForm(null,null,null,null,null,null,m_history_xmlstore);
     m_history_xmlstore.load();
     return getTwoPanelVerticalBorderLayoout(grid,null,ptitle,pformid);
     
}
