/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function getGridView(ptitle, xmlstore,columnsheader,pregion){

    var arrayColumnsHeader=[];var pvalue;
      for (i = 0 ; i < columnsheader.length ;i++)
          {
              arrayColumnsHeader[i] =[columnsheader[i].dataIndex,columnsheader[i].header];
              if(columnsheader[i].header.substr(0, 4)=='Nama')
                  {
                      pvalue=columnsheader[i].dataIndex;
                  }
          }
   var region_temp,split_temp;
   if (typeof pregion == "undefined"){
       region_temp = 'north';
       split_temp=true;
   }
   else{
       region_temp = 'center';
       split_temp=false;
   }
   var grid = new Ext.grid.GridPanel({
                //frame: true,         
                //height: 400,
                //width: 800,
                //collapsible: true,
                region: region_temp,     // position for region
                //height: 300,
                split: split_temp,
                //autoHeight: true,
                store: xmlstore,
                colModel: new Ext.grid.ColumnModel({
                   defaultSortable: true,
                   columns: columnsheader
                }),                
                tbar: get_GridFilterToolbar(arrayColumnsHeader,xmlstore,pvalue)
            });    
    if (typeof pregion == "undefined"){
        grid.setHeight( 150);
    }

    return grid;
}