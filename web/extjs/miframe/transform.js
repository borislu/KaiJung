

/* Since transform.js is injected into the frame's established DOM,
 * Ext.EventManager is told the DOM IS ready
 */
Ext.isReady = true;

//ext-basex is loaded, so tell IE to use ActiveX for local files
Ext.lib.Ajax.forceActiveX = true;

Ext.onReady(function(){

    var tabs = new Ext.TabPanel({
        renderTo: 'tabs1',
        width:450,
        activeTab: 0,
        frame:true,
        defaults:{autoHeight: true},
        items:[
            {contentEl:'script', title: 'Short Text'},
            {contentEl:'markup', title: 'Long Text'}
        ]
    });
    var _urlDelim
       ,getLocationAbsolute = function(){
               var d= _urlDelim = location.href.indexOf('\/') != -1 ? '\/':'\\';
               var u=location.href.split(d);
               u.pop(); //this page
               return u.join(d);
          }
      ,getSiteRoot = function(){
            var url = getLocationAbsolute().split(_urlDelim );
            url.pop();
            return url.join(_urlDelim);
      };



    // second tabs built from JS
    var tabs2 = new Ext.TabPanel({
        renderTo: document.body,
        activeTab: 0,
        width:600,
        height:250,
        plain:true,
        defaults:{autoScroll: true},
        items:[{
                title: 'Normal Tab',
                html: "My content was added during construction."
            },{
                title: 'Ajax Tab 1',
                autoLoad:{url:'../tabs/ajax1.htm'}
            },{
                title: 'Ajax Tab 2',
                autoLoad: {url:'../tabs/ajax2.htm', params: 'foo=bar&wtf=1',method:'GET'}
            },{
                title: 'Event Tab',
                listeners: {activate: handleActivate},
                html: "I am tab 4's content. I also have an event listener attached."
            },{
                title: 'Disabled Tab',
                disabled:true,
                html: "Can't see me cause I'm disabled"
            }
        ]
    });

    function handleActivate(tab){

        alert(tab.title + ' was activated.');
    }

    // cross-frame message example
    //subscribe to any messages received from frameHost
    if(typeof onhostmessage == 'function'){
        onhostmessage(function(message){

           alert('Cross-Frame Response: '+ message.data);
        },null,true);
    }

    if(typeof sendMessage == 'function'){
        //notify frameHost that transformation is complete
        sendMessage('sendMessage: transform Complete');
    }

});
