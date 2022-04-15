<%-- 
    Document   : index
    Created on : 22 Feb 11, 13:49:37
    Author     : Eddy
--%>

<%@page import="com.wonokoyo.model.strukturdata.M_User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%    
    M_User user = (M_User)session.getAttribute("userdetil");
%>
<html>
  <head>
    <title>Sistem Sewa Ruang</title>    
    <link rel="stylesheet" type="text/css"
           href="lib/extjs-3.3.1/resources/css/ext-all.css" />
    <style type="text/css">
        .my-panel-class {
            font-family: tahoma,sans-serif;
        }
    </style>
    
     <script type="text/javascript" >var useridsession = <%= "'"+user.USERID+"'" %>;</script>
     <script type="text/javascript" src="lib/extjs-3.3.1/adapter/ext/ext-base.js?n=1"></script>
     <script type="text/javascript" src="lib/extjs-3.3.1/ext-all-debug.js?n=1"></script>
     <script type="text/javascript" src="lib/extjs-3.3.1/plugins/XmlTreeLoader.js?n=1"></script>
     <script type="text/javascript" src="lib/extjs-3.3.1/plugins/TabCloseMenu.js?n=1"></script>
     <script type="text/javascript" src="lib/extjs-3.3.1/plugins/Ext.util.Format.escapeXml.js?n=1"></script>
     <script type="text/javascript" src="lib/extjs-3.3.1/plugins/Ext.util.Format.replicate.js?n=1"></script>
     <script type="text/javascript" src="utilities/DateTime.js?n=1"></script>
     <script type="text/javascript" src="lib/extjs-3.3.1/plugins/FormValues_XmlSerialize.js?n=1"></script>
     <script type="text/javascript" src="lib/extjs-3.3.1/plugins/Store_XmlSerialize.js?n=1"></script>
     <script type="text/javascript" src="lib/extjs-3.3.1/plugins/IFrameComponent.js?n=1"></script>        
     <script type="text/javascript" src="form/Override_Ext.lib.Ajax.serializeForm.js?n=1"></script>
     <script type="text/javascript" src="lib/extjs-3.3.1/plugins/CheckColumn.js?n=1"></script>   
     <script type="text/javascript" src="form/enableDisableAllFieldUnderContainer.js?n=1"></script>
     <script type="text/javascript" src="form/ReadOnlyAllFormField.js?n=1"></script>     
     <script type="text/javascript" src="form/CRUDFormButton.js?n=1"></script>
     <script type="text/javascript" src="form/GridView.js?n=1"></script>
     <script type="text/javascript" src="form/TwoPanelVerticalBorderLayout.js?n=1"></script>
     <script type="text/javascript" src="form/filterPermissionButtonForm.js?n=1"></script>
     <script type="text/javascript" src="form/RendererCurrencyFormat.js?n=1"></script>
     <script type="text/javascript" src="form/GridFilterToolbar.js?n=1"></script>          
     
     <link type="text/css" rel="stylesheet" href="lib/extjs-3.3.1/plugins/DateTimePicker/ux-date-time-field.css"/>
     <script type="text/javascript" src="lib/extjs-3.3.1/plugins/DateTimePicker/date-time-field-debug.js?n=1"></script>          
     
     <link type="text/css" rel="stylesheet" href="lib/extjs-3.3.1/plugins/multiselect/MultiSelect.css"/>
     <script type="text/javascript" src="lib/extjs-3.3.1/plugins/multiselect/MultiSelect.js?n=1"></script>     

     <script type="text/javascript" src="store/m_jenisStore.js?n=1"></script>
     <script type="text/javascript" src="store/vm_hris_bpartnerLengkapStore.js?n=1"></script>
     <script type="text/javascript" src="store/m_menuStrore.js?n=1"></script>
     <script type="text/javascript" src="store/m_parameterStore.js?n=1"></script>
     <script type="text/javascript" src="store/mh_konfigurasiaccStore.js?n=1"></script>
     <script type="text/javascript" src="store/m_pembebananStore.js?n=1"></script>
     <script type="text/javascript" src="store/m_settingaccStore.js?n=1"></script>
     <script type="text/javascript" src="store/m_userStore.js?n=1"></script>
     <script type="text/javascript" src="store/m_itemStore.js?n=1"></script>
     <script type="text/javascript" src="store/m_historyStore.js?n=1"></script>
     <script type="text/javascript" src="store/vm_formNeedAccStore.js?n=1"></script>          
     <script type="text/javascript" src="store/vm_hris_costcenterStore.js?n=1"></script>
     <script type="text/javascript" src="store/d_costCenterPengajuanStore.js?n=1"></script>
     <script type="text/javascript" src="store/d_costCenterRealisasiStore.js?n=1"></script>
     <script type="text/javascript" src="store/md_pembebananStore.js?n=1"></script>
     <script type="text/javascript" src="store/d_tglpengajuanStore.js?n=1"></script>
     <script type="text/javascript" src="store/d_tglrealisasiStore.js?n=1"></script>
     <script type="text/javascript" src="store/sd_itemrealisasiStore.js?n=1"></script>
     <script type="text/javascript" src="store/sd_itempengajuanStore.js?n=1"></script>     
     <script type="text/javascript" src="store/m_permissionStore.js?n=1"></script>
     <script type="text/javascript" src="store/md_konfigurasiaccStore.js?n=1"></script>
     <script type="text/javascript" src="store/m_accStore.js?n=1"></script>
     <script type="text/javascript" src="store/vm_muser_konfigurasiaccStore.js?n=1"></script>
     <script type="text/javascript" src="store/md_tarifItemStore.js?n=1"></script>
     <script type="text/javascript" src="store/t_pengajuanStore.js?n=1"></script>
     <script type="text/javascript" src="store/t_realisasiStore.js?n=1"></script>
     <script type="text/javascript" src="store/vm_hariliburStore.js?n=1"></script>
     <script type="text/javascript" src="store/temp_sd_itempengajuanStore.js?n=1"></script>

     <script type="text/javascript" src="form/dialogWindow/ListObjectInWIndowBrowse.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/browseBPartneLengkap.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/browseFormNeedAcc.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/browseJenis.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/browseCostCenter.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/browseItem.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/accWindow.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/ApproveWindow.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/browseForm.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/browseUser.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/browseJabatanByKodeBp.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/browseHPengajuan.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/browseHRealisasi.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/ubahPassword.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/insertJamawalJamakhirItem.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/browseYgAccTransaksi.js?n=1"></script>
     <script type="text/javascript" src="form/dialogWindow/copySDItemToOtherDate.js?n=1"></script>

     <script type="text/javascript" src="form/transaksi/accTransaksi/AccTable.js?n=1">
     </script>
     <script type="text/javascript" src="form/master/m_history/m_history.js?n=1"></script>
     <script type="text/javascript" src="form/master/m_jenis/m_jenis.js?n=1"></script>
     <script type="text/javascript" src="form/admin/m_menu/m_menu.js?n=1"></script>
     <script type="text/javascript" src="form/master/m_settingacc/m_settingacc.js?n=1"></script>     
     <script type="text/javascript" src="form/master/m_konfigurasiacc/m_konfigurasiacc.js?n=1"></script>
     <script type="text/javascript" src="form/system/m_user/m_user.js?n=1"></script>
     <script type="text/javascript" src="form/master/m_pembebanan/m_pembebanan.js?n=1"></script>
     <script type="text/javascript" src="form/master/m_item/m_item.js?n=1"></script>
     <script type="text/javascript" src="form/admin/m_parameter/m_parameter.js?n=1"></script>
     <script type="text/javascript" src="form/transaksi/accTransaksi/AccTable.js?n=1"></script>
     <script type="text/javascript" src="form/transaksi/t_pengajuan/t_pengajuan.js?n=1"></script>
     <script type="text/javascript" src="form/transaksi/t_perubahan/t_perubahan.js?n=1"></script>
     <script type="text/javascript" src="form/transaksi/t_realisasi/t_realisasi.js?n=1"></script>
     <script type="text/javascript" src="form/transaksi/list_sewaruang/listsewaruang.js?n=1"></script>
     <script type="text/javascript" src="form/laporan/l_rekapreservasi/l_rekapreservasi.js?n=1"></script>
     <script type="text/javascript" src="form/laporan/l_rekapsewaruang/l_rekapsewaruang.js?n=1"></script>
     <script type="text/javascript">
         if (Ext.BLANK_IMAGE_URL.substr(0, 5) != 'data:') {
            Ext.BLANK_IMAGE_URL = 'lib/extjs-3.3.1/resources/images/default/s.gif';
         }         
         Ext.app.BookLoader = Ext.extend(Ext.ux.tree.XmlTreeLoader, {
            processAttributes : function(attr){
                if(attr.titleLevel1){
                    attr.text = attr.titleLevel1;
                    //attr.iconCls = 'author-' + attr.gender;
                    attr.loaded = true;
                    //attr.expanded = true;
                    if(attr.daun == 'true'){
                        attr.leaf = true;
                    }else{
                        attr.leaf = false;
                    }
                }
                else if(attr.titleLevel2){
                    attr.text = attr.titleLevel2;
                    //attr.iconCls = 'book';
                    attr.loaded = true;
                    if(attr.daun == 'true'){
                        attr.leaf = true;
                    }else{
                        attr.leaf = false;
                    }
                }else if(attr.titleLevel3){
                    attr.text = attr.titleLevel3;
                    if(attr.daun == 'true'){
                        attr.leaf = true;
                    }else{
                        attr.leaf = false;
                    }
                }
            }
         });
         Ext.onReady(function(){
/*
             var panelMenuMaster=new Ext.Panel({
                title: 'Master'
             });
             var panelMenuTrans=new Ext.Panel({
                title: 'Transaksi'
              });

*/          Ext.QuickTips.init();
            var tabPanel_viewport = new Ext.TabPanel({
                        id:'tabpanel-viewport',
                        region: 'center',
                        resizeTabs:true,
                        minTabWidth: 115,
                        tabWidth:135,
                        enableTabScroll:true,
                        defaults: {autoScroll:true},
                        activeTab: 0,
                        /*items: [{
                            xtype: 'container',
                            title: 'Movie Descriptions',
                            layout: 'accordion',
                            defaults: {
                                border: false
                            },
                            items: [{
                                title: 'Office Space',
                                html:'accordion 1'
                            },{
                                title: 'Super Troopers',
                                html:'accordion 2'
                            },{
                                title: 'American Beauty',
                                html:'accordion 12'
                            }]
                        }],*/
                        margins: '0 5 5 0',
                        plugins: new Ext.ux.TabCloseMenu()
                  });

            var viewport = new Ext.Viewport({
                layout: "border",
                //defaults: {
                //    bodyStyle: 'padding:5px;'
               // },
                items: [{
                    id:'toolbar-viewport',
                    region: "north",
                    //html: 'North',
                    //bodyStyle: '',
                    xtype: 'toolbar',
                     height: 27,
                    margins: '0 0 5 0',
                    items:[{
                           xtype:'label',
                           text:'PROGRAM SEWA RUANG DAN IJIN LEMBUR',
                           style: {
                                'font-weight': 'bold',
                                'padding-left': '3px'
                                //'font-size': 'large'
                            }
                        },'->',
                        {
                            text: 'Change Password',
                            xtype: 'button',
                            handler: function(){
                                showUbahPassWindow(<%= "'"+user.USERID+"'" %>,<%= "'"+user.NAMABP+"'" %>,<%= "'"+user.KODEBP+"'" %>,<%= "'"+user.AKTIF+"'" %>);
                            }
                        },
                        '-',
                        {
                            text: 'Logout',
                            xtype: 'button',
                            handler: function(){
                                window.location=<%= "\"" + response.encodeURL("maincontroller?logout=true")+"\""  %>;
                            }
                        }
                    ]
                  },new Ext.tree.TreePanel({
                        id:'treemenu-viewport',
                        region:'west',
                        title:'Menu',
                        split:true,
                        width: 200,
                        minSize: 175,
                        maxSize: 400,
                        collapsible: true,
                        margins:'0 0 5 5',
                        loader: new Ext.app.BookLoader({
                            dataUrl:'maincontroller?treemode=VIEW_TREEMENU'
                        }),
                        rootVisible:false,
                        lines:false,
                        autoScroll:true,
                        root: new Ext.tree.AsyncTreeNode()
                  }),tabPanel_viewport/*,{
                    region: 'east',
                    split: true,
                    width: 200,
                    html: 'East',
                    margins: '0 5 0 0'
                  },{
                    region: 'south',
                    html: 'South',
                    margins: '5 5 5 5'
                }*/]
            });


            var tree_viewport = Ext.getCmp('treemenu-viewport');
            var treeEvent_viewport = tree_viewport.getSelectionModel();
            treeEvent_viewport.on('beforeselect', function(treeEvent_viewport, node){
                return node.isLeaf();
            });
            treeEvent_viewport.on('selectionchange', function(treeEvent_viewport, node){
                //ds.loadForum(node.id);
                //Ext.getCmp('main-view').setTitle(node.text);
                if(Ext.get(node.attributes.id) == null){
                    if (node.attributes.id=="01-03-01"){
//                     document.body.innerHTML = document.body.innerHTML ;
                       /* Ext.Ajax.request({
                           url: node.attributes.url,
                           success: function(response, opts) {
                              //var obj = Ext.decode(response.responseText);
                              //console.dir(obj);
                              //document.body.innerHTML = document.body.innerHTML+response.responseText;
                              tabPanel_viewport.add(response).show();
                           },
                           failure: function(response, opts) {
                              console.log('server-side failure with status code ' + response.status);
                           }
                        });*/
                        /*var oHead = document.getElementsByTagName('HEAD').item(0);
                        var oScript= document.createElement("script");
                        oScript.type = "text/javascript";
                        oScript.id ="script_"+node.attributes.id;
                        oScript.src="form/master/m_jenis/m_jenis.js";
                        oHead.appendChild( oScript);*/
                        var form_mjenis = getForm_mjenis(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        //form_mjenis.id=node.attributes.id;
                        //form_mjenis.title= node.attributes.text;
                        tabPanel_viewport.add(form_mjenis).show();
                    }
                    else if(node.attributes.id=="01-02-01"){
                        var form_mmenu = getForm_mmenu(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_mmenu).show();
                    }
                    else if(node.attributes.id=="01-03-03"){
                        var form_msettingacc = getForm_msettingacc(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_msettingacc).show();
                    }
                    else if(node.attributes.id=="01-03-02"){
                        var form_mkonfigurasiacc = getForm_mkonfigurasiacc(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_mkonfigurasiacc).show();
                    }
                    else if(node.attributes.id=="01-01-01"){                        
                        var form_muser = getForm_muser(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);                        
                        tabPanel_viewport.add(form_muser).show();                        
                    }
                    else if(node.attributes.id=="01-03-04"){
                        var form_mpembebanan = getForm_mpembebanan(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_mpembebanan).show();
                    }
                    else if(node.attributes.id=="01-03-05"){
                        var form_mitem = getForm_mitem(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_mitem).show();
                    }
                    else if(node.attributes.id=="01-03-13"){
                        var form_mhistory = getFormHistory(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_mhistory).show();
                    }
                    else if(node.attributes.id=="01-04-01"){
                        var form_tpengajuan = getForm_tpengajuan(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_tpengajuan).show();
                    }
                    else if(node.attributes.id=="01-04-02"){
                        var form_tperubahan = getForm_tperubahan(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint,node.attributes.batal_trans);
                        tabPanel_viewport.add(form_tperubahan).show();
                        
                    }else if(node.attributes.id=="01-04-03"){
                        var form_trealisasi = getForm_trealisasi(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_trealisasi).show();
                    }
                    else if(node.attributes.id=="01-02-02"){
                        var form_mparameter = getForm_mparameter(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_mparameter).show();
                    }
                    else if(node.attributes.id=="01-04-04"){
                        var form_listsewaruang = getListSewaruang(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                        node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_listsewaruang).show();
                    }
                    else if(node.attributes.id=="01-05-01"){
                        var form_rekapreservasi = getForm_lrekapreservasi(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_rekapreservasi).show();
                    }
                    else if(node.attributes.id=="01-05-02"){
                        var form_rekapsewaruang = getForm_lrekapsewaruang(node.attributes.id,node.attributes.text,'READ',node.attributes.pview,
                                node.attributes.padd,node.attributes.pupdate,node.attributes.pdelete,node.attributes.pprint);
                        tabPanel_viewport.add(form_rekapsewaruang).show();
                    }
                    else{
                         tabPanel_viewport.add({
                            id:node.attributes.id,
                            title: node.attributes.text,
                            html: 'Tab Body ' ,
                            closable:true
                        }).show();
                    }
                 }else{
                    tabPanel_viewport.setActiveTab(node.attributes.id);
                 }
            });
         });

     </script>
  </head>
  <body>
      
  </body>
</html>