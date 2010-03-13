/*
 * Author: Doug Hendricks. doug[always-At]theactivegroup.com
 * Copyright 2008, Active Group, Inc.  All rights reserved.
 *
 ************************************************************************************
 *   This file is distributed on an AS IS BASIS WITHOUT ANY WARRANTY;
 *   without even the implied warranty of MERCHANTABILITY or
 *   FITNESS FOR A PARTICULAR PURPOSE.
 ************************************************************************************

/* Fixes for DOM cleanup (Important for <object> tag cleanup) */
ExtFixes = function(){
Ext.namespace('Ext.ux.plugins');
Ext.ux.plugins.ContainerMask = function(opt) {
    var options = opt||{};

    return {
        init: function(c) {
            Ext.applyIf(c,{
                showMask : function(msg, msgClass, maskClass){
                    var el;
                    if(this.rendered && (el = this[options.el] || Ext.get(options.el) || this.getEl?this.getEl():null)){
                      el.mask.call(el,msg || options.msg, msgClass || options.msgClass, maskClass || options.maskClass);
                    }
                },
                hideMask : function(){
                    var el;
                    if(this.rendered && (el = this[options.el] || Ext.get(options.el) ||  this.getEl?this.getEl():null)){
                      el.unmask.call(el);
                    }
                }
            });
            if(options.masked){
                c.on('render', c.showMask.createDelegate(c,[null]) ,c, {single:true} ) ;
            }
        }
    };
};
//Permits Container creation without items.
Ext.override(Ext.Container,{
 add : function(comp){
        if(!this.items){
            this.initItems();
        }
        var a = arguments, len = a.length;
        if(len > 1){
            for(var i = 0; i < len; i++) {
                this.add(a[i]);
            }
            return;
        }
        if(comp){
            var c = this.lookupComponent(this.applyDefaults(comp));
            var pos = this.items.length;
            if(this.fireEvent('beforeadd', this, c, pos) !== false && this.onBeforeAdd(c) !== false){
                this.items.add(c);
                c.ownerCt = this;
                this.fireEvent('add', this, c, pos);
            }
            return c;
       } else {return null;}
    },

    doLayout : function(shallow){
        if(this.rendered && this.layout){ // added last conditional
            this.layout.layout();
        }
        if(shallow !== false && this.items && this.items.items){ // added last conditional
            var cs = this.items.items;
            for(var i = 0, len = cs.length; i < len; i++) {
                var c  = cs[i];
                if(c.doLayout){
                    c.doLayout();
                }
            }
        }
    }

});

Ext.override(Ext.layout.BorderLayout, {
    destroy : function () {
        // destroy the split bars if any
        var items = this.container.items.items;
        for (var i = 0, len = items.length; i < len; i++) {
            var pos = items[i].region;
            if (pos && this[pos].split) {
                this[pos].split.destroy();
            }
        }
    }
});

 Ext.override(Ext.Element, {
        mask : function(msg, msgCls,maskCls){
            if(this.getStyle("position") == "static"){
                this.setStyle("position", "relative");
            }
            if(this._maskMsg){
                this._maskMsg.remove();
            }
            if(this._mask){
                this._mask.remove();
            }

            this._mask = Ext.DomHelper.append(this.dom, {cls:maskCls || "ext-el-mask"}, true);

            this.addClass("x-masked");

            this._mask.setDisplayed(true);

            if(typeof msg == 'string'){
                // *** FIX : create element hidden
                this._maskMsg = Ext.DomHelper.append(this.dom, {style:"visibility:hidden",cls:"ext-el-mask-msg", cn:{tag:'div'}}, true);
                var mm = this._maskMsg;
                mm.dom.className = msgCls ? "ext-el-mask-msg " + msgCls : "ext-el-mask-msg";
                mm.dom.firstChild.innerHTML = msg;
                (function(){
                    mm.setDisplayed(true);
                    mm.center(this);
                    mm.setVisible(true);
                }).defer(20,this); // *** FIX : defer things a bit, so the mask sizes over the el properly before centering
            }
            if(Ext.isIE && !(Ext.isIE7 && Ext.isStrict) && this.getStyle('height') == 'auto'){ // ie will not expand full height automatically
                   this._mask.setSize(this.dom.clientWidth, this.getHeight());
            }
            return this._mask;
        }
});
Ext.util.Format.htmlDecode = function(value)
    {
       return !value ? value : String(value).replace(/&gt;/g, ">").replace(/&lt;/g, "<").replace(/&quot;/g, '"').replace(/&amp;/g, "&");
    };
}; //Going to apply these fixes to our Iframes dynamically -- later

ExtFixes();


//Ext.useShims = true;
var Demo = function(){

  var _urlDelim

  ,createBox = function(t, s) {
          return ["<div class=\"msg\">"
                , "<div class=\"x-box-tl\"><div class=\"x-box-tr\"><div class=\"x-box-tc\"></div></div></div>"
                , "<div class=\"x-box-ml\"><div class=\"x-box-mr\"><div class=\"x-box-mc\"><h3>"
                , t, "</h3>", s
                , "</div></div></div>"
                , "<div class=\"x-box-bl\"><div class=\"x-box-br\"><div class=\"x-box-bc\"></div></div></div>"
                , "</div>"].join("");
     }
   ,MM = new Ext.ux.ModuleManager({
        disableCaching :true
       ,cacheResponses :true  // internally cache everything
       ,noExecute      :true  // Do not want anything eval'ed, just cached for later use.

       });

  return {
    init    : function(){
        Ext.QuickTips.init();
        Ext.QuickTips.getQuickTip().interceptTitles = true;

        Ext.QuickTips.enable();
        var get = Ext.getCmp;

        this.tree = get('demoTree');
        this.tabs = get('demoTabs');
        var sm = this.tree.getSelectionModel();

        this.tree.on('click', this.handlers.nodeSelect.createDelegate(this,[sm],0));

        //This site sits in the examples/miframe path, so adjust modulePath to gather the basics
        //relative to the standard Ext distribution
        MM.modulePath = '../../';

        //.js is assumed, but you can load anything
        if(this.requires('resources/css/ext-all.css'
                     ,'adapter/ext/ext-base'
                     ,'examples/miframe/ext-all'
                     ,'examples/miframe/ext-basex'
                     ,'examples/examples.css'
                     ,'examples/tabs/tabs-example.css'
                     ,'examples/miframe/transform'

                     )){

             this.balloon(this.tabs.el, 't', 'ModuleManager:','Required Source Files are Cached');
        } else {
             this.balloon(this.tabs.el, 't', 'ModuleManager Failure:','Some Resources are NOT Cached');
         }
    },

    //Give Demo a dynamic script loader instance
    ModuleManager : MM,  //exposed for FireBug Browsing

    requires      : MM.load.createDelegate(MM),

    modules       : {},

    handlers:{

           /*
           * Add a tabPanel based on selected nodeId

           */
           nodeSelect:function( smodel, treeNode, e ){

                    //A folder or already selected
                    if(!treeNode.isLeaf()) {return false;}

                    var NA = treeNode.attributes || {};
                    var id = NA.id.split('\/')[2] || false; //derive a tab id from node.id

                    var tab;

                    tab = this.tabs.getItem(id) ||
                       ( tab = this.tabs.add(
                                 Ext.apply({
                                    xtype      : 'iframepanel'
                                   ,id         : id || (id=Ext.id())
                                   ,title      : NA.tabTitle || NA.text || id
                                   ,listeners  : NA.events || false
                                   ,autoScroll :false
                                   ,loadMask   : false
                                   ,tbar : toolBars
                                  },NA.config || {}))
                        );

                    this.tabs.setActiveTab(tab);


            }
            ,exception:  function(frame,ex){

                this.balloon(this.tabs.el, 't', 'Iframe Exception',ex.description||ex.text||ex);


            },
            callback  : function(frame){

                this.balloon(Demo.tabs.activeTab.el, 't', 'callBack Fired',Demo.tabs.activeTab.getFrame().src||'');

            },
            //Simple cross-frame messaging.
            message  : function(frame, message){
                this.balloon(Demo.tabs.activeTab.el, 't', 'Message Received',message.domain + ' Sent: ' + (message.data));
                frame.sendMessage('Acknowledged'); //send back an Ack message
            }
    },

    balloon : function(alignTarget,align, title, format){

        if(!this.balloonCt){
            this.balloonCt = Ext.DomHelper.insertFirst(document.body, {id:'demo-balloon-div'}, true);
          }
          this.balloonCt.alignTo(alignTarget||Ext.getBody(), align||'t-t');
          var s = String.format.apply(String, Array.prototype.slice.call(arguments, 3));
          var m = Ext.DomHelper.append(this.balloonCt, {html:createBox(title||'', s)}, true);
          m.slideIn('t').pause(1).ghost("t", {remove:true});
     },
     /*
     Ext'ize the frame, inject CSS rules from gathered style-sheets, and
     eval the collected code modules into the frame's window context.
     */
     transform:  function(iframe){

         if(!iframe.domWritable()){alert('Not writable');return;}

         var iwin = iframe.getWindow();
         var exception;
         var MM = this.ModuleManager;

         forEach(MM.modules,function(module,name){

            //All css is injected into document's head section
            if(module.extension == 'css'){

                /* For an IFrame we have to adjust the relative url's in the CSS Rules specifically for ext-all.css */
                MM.styleAdjust = (name == 'ext-all.css'?{pattern:/url\(\s*\.\.\//ig, replacement:'url(../../resources/'}:null);
                MM.applyStyle(module,null,iwin);


            } else {
                exception = function(i,ex,module){
                    this.balloon(this.tabs.el, 't', 'Iframe Exception:'+module,(ex.description||ex.message||ex));
                }.createDelegate(this,[module.name],true);

                iframe.on('exception',exception,this);
                iframe.execScript(module.content); //write script tags for debugging.
                iframe.un('exception',exception,this);
            }

         },this);

         iframe.execScript("Ext.lib.Ajax.forceActiveX = true;");
         //dom is already in-place so we tell (the revised) EventManger "it's so."
         iframe.execScript("Ext.isReady=true");

         //load our ExtFixes in the frame and execute it.
         iframe.loadFunction('ExtFixes',true, true);

     },
     dynamic: function(){}
     ,getLocationAbsolute : function(){
                var d= _urlDelim = location.href.indexOf('\/') != -1 ? '\/':'\\';
                var u=location.href.split(d);
                u.pop(); //this page
                return u.join(d);
           }
     ,getSiteRoot :  function(){
             var url = this.getLocationAbsolute().split(_urlDelim );
             url.pop();
             return url.join(_urlDelim);
      }
  };
}();

var evs = {
     documentloaded :function(iframe){this.balloon(iframe.ownerCt.el, 't', 'Document Loaded !','');}
    ,domready       :function(iframe){this.balloon(iframe.ownerCt.el, 't', 'DomReady!','');}
    ,exception      :function(iframe,ex){this.balloon(iframe.ownerCt.el, 't', 'Exception',ex); }

    ,scope          :Demo
    }
var toolBars = [{
   text:'Refresh',
       handler: function(){
           var panel = Ext.getCmp('demoTabs').activeTab;
           if(panel){
               panel.loadMask.msg='Reloading your selection...';
               panel.getUpdater().refresh(Demo.handlers.callback.createDelegate(Demo));

           }
       }
        },'-',
    {
    split:true,
    text:'Drop Down Menu',
    handler: null,
    menu:{
        id:'reading-menu',
        cls:'reading-menu',
        width:200,
        listeners:{     //mask all frames while menu is visible.
            beforeshow : Ext.ux.ManagedIFrame.Manager.showDragMask,
            hide       : Ext.ux.ManagedIFrame.Manager.hideDragMask,
            scope      : Ext.ux.ManagedIFrame.Manager

        },
        items: [{
            text:'Bottom',
            checked:true,
            group:'rpgroup',
            scope:this,
            iconCls:'preview-bottom'
        },{
            text:'Right',
            checked:false,
            group:'rpgroup',
            scope:this,
            iconCls:'preview-right'
        },{
            text:'Hidden',
            checked:false,
            group:'rpgroup',
            scope:this,
            iconCls:'preview-hide'
        }]
    }
  }
];

var sampleNodes = [
  {'text':'Transformations'
    ,'id':'\/transform'
    ,'leaf':false
    ,iconCls:'folder'
    ,expanded:true
    ,children: [
            {
             text:'via UpdateManager'
            ,id:'\/transform\/updater'
            ,leaf:true
            ,tabTitle:'UpdateManager Transformed'

            ,config : { loadMask        : {hideOnReady:true, msg:'Masked Until domready...'}
                       ,disableMessaging: false
                       ,fitToParent: true
                       ,autoLoad   : {url       : 'inline.html'
                                         ,scripts   : true     //to render the NASA image only
                                         ,nocache   : true
                                         ,callback  : Demo.handlers.callback.createDelegate(Demo)
                                         }

                     }
            ,events:{
                       documentloaded :evs.documentloaded
                      ,domready     : Demo.transform
                      ,exception    : Demo.handlers.exception
                      ,message      : Demo.handlers.message
                      ,scope        : Demo
                      }
             }
            ,
            {
             text:'From Server'
            ,id:'\/transform\/server'
            ,leaf:true
            ,tabTitle:'Transformed from Server'

            ,config : { loadMask        : {hideOnReady:false, msg:'Masked Until documentloaded...',disabled:false} //unMask on documentloaded
                       ,defaultSrc      : 'inline.html'
                       ,disableMessaging: false
                       ,fitToParent: true

                     }
            ,events:{
                       documentloaded :evs.documentloaded
                      ,domready     : Demo.transform
                      ,exception    : Demo.handlers.exception
                      ,message      : Demo.handlers.message
                      ,scope        : Demo
                      }
             }

        ]
    }
    ,
 {'text':'Remote Sites'
     ,'id':'\/remote'
     ,'leaf':false
     ,iconCls:'folder'
     ,expanded:true
     ,children: [
             {
              text          :'Ajaxian'
             ,id            :'\/remote\/ajaxian'
             ,leaf          :true
             ,tabTitle      :'Ajaxian'

             ,config : { loadMask   : {hideOnReady:true, msg:'Masked Until domready...'}
                        ,defaultSrc : 'http://www.ajaxian.com'
                        ,eventsFollowInternalLinks : true
                      }
             ,events:evs
             },
             {
               text         :'Extjs'
              ,id           :'\/remote\/extjs'
              ,leaf         :true
              ,tabTitle     :'Ext Forums'

              ,config : { loadMask   : {hideOnReady:true, msg:'Masked Until domready...'}
                         ,defaultSrc : {url:'http://www.extjs.com/forum', callback:Demo.handlers.callback.createDelegate(Demo)}
                       }
              ,events:evs
             },
             {
               text         :'Google'
              ,id           :'\/remote\/google'
              ,leaf         :true
              ,tabTitle     :'Google'

              ,config : { loadMask   : {hideOnReady:true, msg:'Masked Until domready...'}
                         ,defaultSrc : 'http://www.google.com/'
                       }
              ,events:evs
             }
          ]
    }
 ];

Ext.BLANK_IMAGE_URL = '../../resources/images/default/s.gif';
Ext.onReady(function(){

   view = new Ext.Viewport({
        layout:'border',
        listeners:{ render: Demo.init,
                    scope : Demo,
                    single : true},
        items:[
            new Ext.BoxComponent({ // raw element
                region:'north',
                el: 'header',
                height:32
            }),
            {
                region      :'west',
                id          :'demos',
                title       :'Demos and Notes',
                split       :true,
                width       :200,
                minSize     : 175,
                maxSize     : 400,
                collapsible : true,
                cmargins    :'5 5 5 5',
                layout      :'accordion',
                layoutConfig:{
                     animate        :false
                    ,activeOnTop    :false
                    ,autoWidth      :true
                    ,autoHeight     :true
                    ,fill           :true
                },

                items:[
                    {
                        id          :'demoTree',
                        xtype       :'treepanel',
                        autoScroll  :true,
                        title       : 'Demos',
                        loader      : new Ext.tree.TreeLoader(),
                        rootVisible :false,
                        lines       :false,
                        autoScroll  :true,
                        root        : new Ext.tree.AsyncTreeNode({
                                        text:'Online',
                                        expanded:true,
                                        children:sampleNodes
                                    })
                   }
                ]
             },
             { xtype            :'tabpanel',
               region           :'center',
               id               :'demoTabs',
               activeTab        :0,
               enableTabScroll  :true,
               monitorResize    :true,

               deferredRender   :false,
               layoutOnTabChange:true,
               autoDestroy      :true,
               defaults:{
                 closable  :true
                }
               ,items:{ title           :'BackGrounder'
                       ,contentEl       :'background'
                       ,closable        :false
                       ,autoScroll      :true
                       }

            }
    ]
  });
  if(view){
   Ext.EventManager.on(window,   "beforeunload",  view.destroy ,view,{single:true});
  }
});
