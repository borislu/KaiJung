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
        var tabs = new Ext.Panel({
            region: 'center'//,
            //margins:'3 3 3 0', 
            //activeTab: 0,
            //defaults:{autoScroll:true},
        });

        // Panel for the west
        var westPanel = new Ext.Panel({
            title: '手動訂貨',
            region: 'west',
            split: true,
            width: 500,
            collapsible: true,
            margins:'0 0 0 0',
            cmargins:'0 0 0 0',
        });

        // Panel for the east
        var eastPanel = new Ext.Panel({
            title: '訂貨明細',
            region: 'east',
            split: true,
            width: 470,
            collapsible: true,
            margins:'0 0 0 0',
            cmargins:'0 0 0 0',
        });

        var win = new Ext.Window({
            title: '訂貨作業',
            closable:true,
            width:1000,
            height:450,
            //border:false,
            plain:true,
            layout: 'border',
            items: [westPanel, tabs, eastPanel]
        });

        win.show(this);

        westPanel.load({
            url: "counterOrderAddMan.html" //,
            //params: {param1: "foo", param2: "bar"}, // or a URL encoded string
            //callback: yourFunction,
            //scope: yourObject, // optional scope for the callback
            //discardUrl: false,
            //nocache: false,
            //text: "Loading...",
            //timeout: 30,
            //scripts: false
        });

    });
});
