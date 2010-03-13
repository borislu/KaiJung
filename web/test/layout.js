/*
 * Ext JS Library 2.0.2
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

Ext.onReady(function(){
    
    var button = Ext.get('show-btn');

    button.on('click', function(){

        // tabs for the center
        var tabs = new Ext.TabPanel({
            region: 'center',
            margins:'3 3 3 0', 
            activeTab: 0,
            defaults:{autoScroll:true},

                          items:[{
                              title: '手動訂貨',
                              autoLoad :{ url: 'counterOrder/counterOrderAddMan.html'}
                          },{
                              title: '建議訂貨',
                              autoLoad :{ url: 'http://localhost:8080/ZhiPin/xava/module.jsp?application=ZhiPin&module=Sms'}
                          }]
        });

        // Panel for the west
        var nav = new Ext.Panel({
            title: 'Navigation',
            region: 'west',
            split: true,
            width: 200,
            collapsible: true,
            margins:'3 0 3 3',
            cmargins:'3 3 3 3'
        });

        var win = new Ext.Window({
            title: 'Layout Window',
            closable:true,
            width:600,
            height:350,
            //border:false,
            plain:true,
            layout: 'border',

            items: [nav, tabs]
        });

        win.show(this);

    });
});
