/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function getListSewaruang(pformid,ptitle,formMode,pview,padd,pupdate,pdelete,pprint){
    
      var iframe_listsewaruang = new Ext.ux.IFrameComponent({ id: id, url: 'form/transaksi/list_sewaruang/listsewaruang.html',region: 'center' });
            
      
      return getTwoPanelVerticalBorderLayoout(iframe_listsewaruang,null,ptitle,pformid);//form_mjenis;
}