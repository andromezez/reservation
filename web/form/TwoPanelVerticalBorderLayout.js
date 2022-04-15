/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function getTwoPanelVerticalBorderLayoout(panelAtas,panelBawah,ptitle,pformid){
   var arrayItems=[];
   arrayItems[0] = panelAtas;
   if (panelBawah!=null)
       arrayItems[1] = panelBawah;
       
   var myBorderPanel = new Ext.Panel({                
        title: ptitle,
        layout: 'border',
        id:pformid,
        closable:true,
        items: arrayItems/*[panelAtas{
            //region: 'north',     // position for region
            //    height: 400,
            //    split: true,
            //items:[panelAtas]//,         // enable resizing
            
        }*//*,panelBawah{
            
            region:'center',
            layout: 'border',
            
            
            items:[{
                region: 'north',
                layout:'hbox',                
                height:30,
                items:[{
                    xtype:'button',
                    text: 'Button 1'
                   
                },{
                    xtype:'button',
                    text: 'Button 2'
                    
                }]
                
            },
                panelBawah
            ]
            
        }*///]
    });
    return myBorderPanel;
}