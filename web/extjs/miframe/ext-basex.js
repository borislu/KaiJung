
 /*
  * Author: Doug Hendricks. doug[always-At]theactivegroup.com
  * Copyright 2007-2008, Active Group, Inc.  All rights reserved.
  *
  * This extension adds EventManager Support to Ext.lib.Ajax (if
  *    Ext.util.Observable is present in the stack)
  ************************************************************************************
  *   This file is distributed on an AS IS BASIS WITHOUT ANY WARRANTY;
  *   without even the implied warranty of MERCHANTABILITY or
  *   FITNESS FOR A PARTICULAR PURPOSE.
  ************************************************************************************

  License: ext-basex is licensed under the terms of the Open Source LGPL 3.0 license.
  Commercial use is permitted to the extent that the code/component(s) do NOT become
  part of another Open Source or Commercially licensed development library or toolkit
  without explicit permission.

  * Donations are welcomed: http://donate.theactivegroup.com
  */

if(!String.prototype.trim){
 String.prototype.trim = function(){
    var re = /^\s+|\s+$/g;
    return function(){ return this.replace(re, ""); };
 }();
}

if(Ext.util.Observable){
  Ext.apply( Ext.lib.Ajax ,{

   events:{request     :true,
           beforesend  :true,
           response    :true,
           exception   :true,
           abort       :true,
           timeout     :true,
      readystatechange :true
    }

   /*
     onStatus
     define eventListeners for a single (or array) of HTTP status codes.
   */
   ,onStatus:function(status,fn,scope,options){
        var args = Array.prototype.slice.call(arguments, 1);
        status = [].concat(status||[]);
        Ext.each(status,function(statusCode){
            statusCode = parseInt(statusCode,10);
            if(!isNaN(statusCode)){
                var ev = 'status:'+statusCode;
                this.events[ev] || (this.events[ev] = true);
                this.on.apply(this,[ev].concat(args));
            }
        },this);
   }
   /*
        unStatus
        unSet eventListeners for a single (or array) of HTTP status codes.
   */
   ,unStatus:function(status,fn,scope,options){
           var args = Array.prototype.slice.call(arguments, 1);
           status = [].concat(status||[]);
           Ext.each(status,function(statusCode){
                statusCode = parseInt(statusCode,10);
                if(!isNaN(statusCode)){
                    var ev = 'status:'+statusCode;
                    this.un.apply(this,[ev].concat(args));
                }
           },this);
      }
    ,onReadyState : function(){
         this.fireEvent.apply(this,['readystatechange'].concat(Array.prototype.slice.call(arguments, 0)));
    }

  }, new Ext.util.Observable());

}

 /*
  * Author: Doug Hendricks. doug[always-At]theactivegroup.com
  * Copyright 2007-2008, Active Group, Inc.  All rights reserved.
  *
  * These Ext.lib.Ajax overrides:

    - adds Synchronous Ajax Support ( options.async =false )
    - Permits IE7 to Access Local File Systems using IE's older ActiveX interface
      via the forceActiveX property
    - Pluggable Form encoder (encodeURIComponent is still the default encoder)
    - Corrects the Content-Type Headers for posting JSON (application/json)
      and XML (text/xml) data payloads and sets only one value (per RFC)
    - Adds fullStatus:{ isLocal, isOK, isError, error, status, statusText}
      object to the existing Response Object.
    - Adds standard HTTP Auth support to every request (userId, password config options)
    - options.method prevails over any method derived by the lib.Ajax stack (DELETE, PUT, HEAD etc).
    - Adds named-Priority-Queuing for Ajax Requests
  *
  */

Ext.lib.Ajax.QueueManager = function(config){

    Ext.apply(this, config||{}, {quantas : 10}); //adjustable milliseconds deferred dispatch value

    //iterable array (0-9) of prioritized queues:
    this.priorityQueues   = [[],[],[],[],[],[],[],[],[],[]];
    this.queues           = {};

    };


Ext.apply(Ext.lib.Ajax.QueueManager.prototype,{

     getQueue     : function(name){ return this.queues[name]; }

    ,createQueue  : function(config){
        if(!config)return null;

        var q = new Ext.lib.Ajax.Queue(config);
        q.manager = this;
        this.queues[q.name] = q;

        var pqa = this.priorityQueues[q.priority];
        if(pqa.indexOf(q.name)==-1)pqa.push(q.name);

        return q;
     }
     //Remove a Queue by passed name or Queue Object reference
    ,removeQueue  : function(q){
        if(q && (q = this.getQueue(q.name||q) )){
            q.clear();  //purge any pending requests
            this.priorityQueues[q.priority].remove(q);
            delete this.queues[q.name];
        }
    }
    ,start   :function(){
        if(!this.started){
         this.started = true;
         this.dispatch();
        }
    }
    ,stop    :function(){ this.started = false; }

    /* main Request dispatch loop.  This keeps the maximum allowed number of requests
     * going at any one time (based on defined queue priority).
     */
    ,dispatch   : function(){
        var lib =Ext.lib.Ajax, qm = this, qmq = qm.queues;
        disp = function(qName){
             var q = qmq[qName];
             while(q && q.pending && lib.pendingRequests && lib.activeRequests < lib.maxConcurrentRequests){
                q.requestNext();
             }
             return lib.pendingRequests > 0 ? (lib.activeRequests < lib.maxConcurrentRequests) :false;
        };

        Ext.each(this.priorityQueues ,function(queues){
            //queues == array of queue names
            if(lib.pendingRequests<1 )return false;
            return Ext.each(queues||[],disp,this) == undefined?true:false;
        }, this);

        lib.pendingRequests > 0 ? this.dispatch.defer(this.quantas,this): this.stop();

     }
});


Ext.lib.Ajax.Queue = function(config){

     config = config ? (config.name? config : {name:config}) :{} ;
     Ext.apply(this,config,
     {
        name     : 'q-default'
       ,priority : 5
       ,FIFO     : true //false implies LIFO
       ,callback : null //optional callback when queue is emptied
       ,scope    : null //scope of callback

      });
      this.requests = [];
      this.pending = false;
      //assert/resolve to 0-9
      this.priority = this.priority>9 ? 9 : (this.priority<0?0:this.priority );

};

Ext.apply(Ext.lib.Ajax.Queue.prototype,{

     add      : function(req){
        this.requests.push(req);
        this.pending = true;
        Ext.lib.Ajax.pendingRequests++;
        if(this.manager){
           this.manager.start();
        }
     }
    ,next     : function(){
        req = this.requests[this.FIFO?'shift':'pop']();
        this.pending = !!this.requests.length;
        return req;
        }
    ,clear    : function(){this.requests.length = 0;this.pending = false;}

    ,requestNext : function(){
       var req,res,lib=Ext.lib.Ajax ;
       if(req=this.next()){
           lib.pendingRequests--;
           res = lib.request.apply(lib,req);
           if(this.requests.length==0  && this.callback){
               //queue emptied callback
               this.callback.call(this.scope||null,this);
           }
           return res;
       } else return false;
    }
 });


Ext.apply( Ext.lib.Ajax ,
{
  queueManager      : new Ext.lib.Ajax.QueueManager(),

  //the Current number of active Ajax requests.
  activeRequests    : 0,

  //the Current number of pending Queued requests.
  pendingRequests   : 0,

  //Specify the maximum allowed during concurrent Queued browser (XHR) requests
  maxConcurrentRequests : Ext.isGecko?4:2 ,

  /* set True as needed, to coerce IE to use older ActiveX interface */
  forceActiveX:false,

  /* Global default may be toggled at any time */
  async       :true,

  createXhrObject:function(transactionId)
        {
            var obj={  status:{isError:false}
                     , tId:transactionId}, http;
            try
            {
              if(Ext.isIE7 && !!this.forceActiveX){throw("IE7forceActiveX");}
              obj.conn= new XMLHttpRequest();
            }
            catch(eo)
            {
                for (var i = 0; i < this.activeX.length; ++i) {
                    try
                    {
                        obj.conn= new ActiveXObject(this.activeX[i]);

                        break;
                    }
                    catch(e) {
                    }
                }
            }
            finally
            {
                obj.status.isError = typeof(obj.conn) == 'undefined';
            }
            return obj;

        }

        /* Replaceable Form encoder */
    ,encoder : encodeURIComponent

    ,serializeForm : function(form) {
                if(typeof form == 'string') {
                    form = (document.getElementById(form) || document.forms[form]);
                }

                var el, name, val, disabled, data = '', hasSubmit = false;
                for (var i = 0; i < form.elements.length; i++) {
                    el = form.elements[i];
                    disabled = form.elements[i].disabled;
                    name = form.elements[i].name;
                    val = form.elements[i].value;

                    if (!disabled && name){
                        switch (el.type)
                                {
                            case 'select-one':
                            case 'select-multiple':
                                for (var j = 0; j < el.options.length; j++) {
                                    if (el.options[j].selected) {
                                        if (Ext.isIE) {
                                            data += this.encoder(name) + '=' + this.encoder(el.options[j].attributes['value'].specified ? el.options[j].value : el.options[j].text) + '&';
                                        }
                                        else {
                                            data += this.encoder(name) + '=' + this.encoder(el.options[j].hasAttribute('value') ? el.options[j].value : el.options[j].text) + '&';
                                        }
                                    }
                                }
                                break;
                            case 'radio':
                            case 'checkbox':
                                if (el.checked) {
                                    data += this.encoder(name) + '=' + this.encoder(val) + '&';
                                }
                                break;
                            case 'file':

                            case undefined:

                            case 'reset':

                            case 'button':

                                break;
                            case 'submit':
                                if(hasSubmit == false) {
                                    data += this.encoder(name) + '=' + this.encoder(val) + '&';
                                    hasSubmit = true;
                                }
                                break;
                            default:
                                data += this.encoder(name) + '=' + this.encoder(val) + '&';
                                break;
                        }
                    }
                }
                data = data.substr(0, data.length - 1);
                return data;
    }
    ,getHttpStatus: function(reqObj){

            var statObj = {  status:0
                    ,statusText:''
                    ,isError:false
                    ,isLocal:false
                    ,isOK:false
                    ,error:null};

            try {
                if(!reqObj){throw('noobj');}
                statObj.status = reqObj.status;

                statObj.isLocal = !reqObj.status && location.protocol == "file:" ||
                           Ext.isSafari && reqObj.status === undefined;

                statObj.isOK = (statObj.isLocal || (statObj.status > 199 && statObj.status < 300));
                statObj.statusText = reqObj.statusText || '';
               } catch(e){ //status may not avail/valid yet (or called too early).
                         }

            return statObj;

     }
    ,handleTransactionResponse:function(o, callback, isAbort){

        callback = callback || {};
        var responseObject=null;
        this.activeRequests--;

        if(!o.status.isError){
            o.status = this.getHttpStatus(o.conn);
            /* create and enhance the response with proper status and XMLDOM if necessary */
            responseObject = this.createResponseObject(o, callback.argument, isAbort);
        }

        if(o.status.isError){
         /* checked again in case exception was raised - ActiveX was disabled during XML-DOM creation?
          * And mixin everything the XHR object had to offer as well
          */
           responseObject = Ext.apply({},responseObject||{},this.createExceptionObject(o.tId, callback.argument, (isAbort ? isAbort : false)));

        }

        responseObject.options = o.options;
        responseObject.fullStatus = o.status;

        if(!this.events || this.fireEvent('status:'+o.status.status ,o.status.status, o, responseObject, callback, isAbort) !== false){

             if (o.status.isOK && !o.status.isError) {
                if(!this.events || this.fireEvent('response',o, responseObject, callback, isAbort) !== false){
                    if (callback.success) {
                        callback.success.call(callback.scope||null,responseObject);
                    }
                }
             } else {
                  if(!this.events || this.fireEvent('exception',o ,responseObject, callback, isAbort) !== false){
                    if (callback.failure) {
                        callback.failure.call(callback.scope||null,responseObject);
                    }
                  }
             }
        }

        if(o.options.async){
            this.releaseObject(o);
            responseObject = null;
        }else{
            this.releaseObject(o);
            return responseObject;
        }

    },

    createResponseObject:function(o, callbackArg, isAbort){
        var obj = {responseXML   :null,
                   responseText  :'',
                   responseStream : null,
                   getResponseHeader : {},
                   getAllResponseHeaders : ''
                   };

        var headerObj = {},headerStr='';

        if(isAbort !== true){
            try{  //to catch bad encoding problems here
                obj.responseText = o.conn.responseText;
                obj.responseStream = o.conn.responseStream||null;
            }catch(e){
                o.status.isError = true;
                o.status.error = e;
            }

            try{
                obj.responseXML = o.conn.responseXML || null;
            } catch(ex){}

            try{
                headerStr = o.conn.getAllResponseHeaders()||'';
            } catch(ex){}
        }

        if(o.status.isLocal){

           o.status.isOK = !o.status.isError && ((o.status.status = (!!obj.responseText.length)?200:404) == 200);

           if(o.status.isOK && (!obj.responseXML || obj.responseXML.childNodes.length === 0)){

                var xdoc=null;
                try{   //ActiveX may be disabled
                    if(typeof(DOMParser) == 'undefined'){
                        xdoc=new ActiveXObject("Microsoft.XMLDOM");
                        xdoc.async="false";
                        xdoc.loadXML(obj.responseText);
                    }else{
                        var domParser=null;
                        try{  //Opera 9 will fail parsing non-XML content, so trap here.
                            domParser = new DOMParser();
                            xdoc = domParser.parseFromString(obj.responseText, 'application\/xml');
                        }catch(ex){}
                        finally{domParser = null;}
                    }
                } catch(exd){
                    o.status.isError = true;
                    o.status.error = exd;
                }
                obj.responseXML = xdoc;
            }
            if(obj.responseXML){
                var parseBad =  (obj.responseXML.documentElement && obj.responseXML.documentElement.nodeName == 'parsererror') ||
                            (obj.responseXML.parseError || 0) !== 0 ||
                            obj.responseXML.childNodes.length === 0;
                if(!parseBad){
                    headerStr = 'Content-Type: ' + (obj.responseXML.contentType || 'text\/xml') + '\n' + headerStr ;
                }
            }
        }
        var header = headerStr.split('\n');
        for (var i = 0; i < header.length; i++) {
            var delimitPos = header[i].indexOf(':');
            if (delimitPos != -1) {
                headerObj[header[i].substring(0, delimitPos)] = header[i].substring(delimitPos + 2);
            }
        }

        obj.tId = o.tId;
        obj.status = o.status.status;
        obj.statusText = o.status.statusText;
        obj.getResponseHeader = headerObj;
        obj.getAllResponseHeaders = headerStr;
        obj.fullStatus = o.status;

        if (typeof callbackArg != 'undefined') {
            obj.argument = callbackArg;
        }

        return obj;
    },
    setDefaultPostHeader:function(contentType){
        this.defaultPostHeader = contentType;
    },

    setDefaultXhrHeader:function(bool){
        this.useDefaultXhrHeader = bool||false;
    },
    request : function(method, uri, cb, data, options) {

        options = Ext.apply({
               async    :this.async || false,
               headers  :false,
               userId   :null,
               password :null,
               xmlData  :null,
               jsonData :null,
               queue    :null
               }, options||{});

        if(!this.events || this.fireEvent('request', method, uri, cb, data, options) !== false){

               //Named priority queues
               if(options.queue && !options.queued){
                   var q = options.queue, qname = q.name || q, qm=this.queueManager;
                   q = qm.getQueue(qname) || qm.createQueue(q);
                   options.queued = true;
                   q.add([method, uri, cb, data, options]);

                   return { tId:this.transactionId++ ,queued : true };
               }


               var hs = options.headers;
               if(hs){
                    for(var h in hs){
                        if(hs.hasOwnProperty(h)){
                            this.initHeader(h, hs[h], false);
                        }
                    }
               }
               var cType = this.headers['Content-Type']||null;
               //remove to ensure only ONE is passed later.(per RFC)
               delete this.headers['Content-Type'];
               if(options.xmlData){
                    cType || (cType = 'text/xml');
                    method = 'POST';
                    data = options.xmlData;
               } else if(options.jsonData){
                    cType || (cType = 'application/json');
                    method = 'POST';
                    data = typeof options.jsonData == 'object' ? Ext.encode(options.jsonData) : options.jsonData;
               }
               if(data){
                    cType || (cType = this.useDefaultHeader?this.defaultPostHeader:null );
                    if(cType)this.initHeader('Content-Type', cType , false );
               }

                //options.method prevails over any derived method.
               return this.makeRequest(options.method || method, uri, cb, data, options);
        }
        return null;

    }

    ,makeRequest:function(method, uri, callback, postData, options){
        var o = this.getConnectionObject();

        if (!o || o.status.isError) {
                return Ext.apply(o,this.handleTransactionResponse(o, callback));
        } else {
                this.activeRequests++;
                o.options = options;
                try{
                    o.conn.open(method, uri, options.async, options.userId, options.password);
                    o.conn.onreadystatechange=this.onReadyState ?
                           this.onReadyState.createDelegate(this,[o],0):Ext.emptyFn;
                } catch(ex){
                    o.status.isError = true;
                    o.status.error = ex;
                    return Ext.apply(o,this.handleTransactionResponse(o, callback));
                }

                if (this.useDefaultXhrHeader) {
                    if (!this.defaultHeaders['X-Requested-With']) {
                    this.initHeader('X-Requested-With', this.defaultXhrHeader, true);
                    }
                }

                if (this.hasDefaultHeaders || this.hasHeaders) {
                    this.setHeader(o);
                }

                if(o.options.async){ //Timers for syncro calls won't work here, as it's a blocking call
                    this.handleReadyState(o, callback);
                }

                try{
                  if(!this.events || this.fireEvent('beforesend', o, method, uri, callback, postData, options) !== false){
                       o.conn.send(postData || null);
                  }
                } catch(ex){
                    o.status.isError = true;
                    o.status.error = ex;
                    return Ext.apply(o,this.handleTransactionResponse(o, callback));
                    }

                return options.async?o:Ext.apply(o,this.handleTransactionResponse(o, callback));
            }
    }

   ,abort:function(o, callback, isTimeout){
            if (this.isCallInProgress(o)) {
                o.conn.abort();
                window.clearInterval(this.poll[o.tId]);
                delete this.poll[o.tId];
                if (isTimeout) {
                    delete this.timeout[o.tId];
                }
                if(this.events){
                    this.fireEvent(isTimeout?'timeout':'abort', o, callback)
                }

                this.handleTransactionResponse(o, callback, true);

                return true;
            }
            else {
                return false;
            }
    }

    ,clearAuthenticationCache:function(url) {
       // Default to a non-existing page (give error 500).
       // An empty page is better, here.
       url || ( url = '.force_logout');
       try{

         if (Ext.isIE) {
           // IE clear HTTP Authentication
           document.execCommand("ClearAuthenticationCache");
         }
         else {
           // create an xmlhttp object
           var xmlhttp;
           if( xmlhttp = this.createXhrObject()){
               // prepare invalid credentials
               xmlhttp.conn.open("GET", url , true, "logout", "logout");
               // send the request to the server
               xmlhttp.conn.send("");
               // abort the request
               xmlhttp.conn.abort();
               xmlhttp.conn = null;
               xmlhttp = null;
           }
         }
       } catch(e) {
         // There was an error
         return;
       }
     }


});


/*
forEach Iteration
  based on previous work by: Dean Edwards (http://dean.edwards.name/weblog/2006/07/enum/)
  Gecko already supports forEach for Arrays : see http://developer.mozilla.org/en/docs/Core_JavaScript_1.5_Reference:Objects:Array:forEach
*/

/* Fix for Opera, which does not seem to include the map function on Array's */

Ext.applyIf( Array.prototype,{
   map : function(fun,scope){
    var len = this.length;
    if(typeof fun != "function"){
        throw new TypeError();
    }
    var res = new Array(len);

    for(var i = 0; i < len; i++){
        if(i in this){
        try{res[i] = fun.call(scope||this, this[i], i, this);}catch(e){}
        }
    }
        return res;
     }
  ,forEach : function(block, scope) {
    var i=0,length = this.length;
    while(i < length){
       try{block.apply(scope||this, [this[i], i++, this]);}catch(e){}
      }
   }
});

   // generic enumeration
Ext.applyIf(Function.prototype,{
   forEach : function(object, block, context) {
        context = context || object;
        for (var key in object) {
        if (typeof this.prototype[key] == "undefined") {
            try{block.apply(context, [object[key], key, object]);}catch(e){}
        }
       }

      }
});

   // character enumeration
Ext.applyIf(String.prototype,{
   forEach : function(block, context) {
        var str = this.toString();
        context = context || this;
        var ar = str.split("")||[];
        ar.forEach( function(chr, index) {
            try{block.apply(context,[ chr, index, str]);}catch(e){}
        },ar);
   }
});

   // globally resolve forEach enumeration
var forEach = function(object, block, context) {
        context = context || object;
    if (object) {
        var resolve = Object; // default
        if (object instanceof Function) {
            // functions have a "length" property
            resolve = Function;
        } else if (object.forEach instanceof Function) {
            // the object implements a custom forEach method so use that
            object.forEach(block, context);
            return;
        }
        resolve.forEach(object, block, context);
    }
};

if(Ext.util.Observable){
   /*
    * @class Ext.ux.ModuleManager
    * Version:  RC-2
    * Author: Doug Hendricks. doug[always-At]theactivegroup.com
    * Copyright 2007-2008, Active Group, Inc.  All rights reserved.
    *
    ************************************************************************************
    *   This file is distributed on an AS IS BASIS WITHOUT ANY WARRANTY;
    *   without even the implied warranty of MERCHANTABILITY or
    *   FITNESS FOR A PARTICULAR PURPOSE.
    ************************************************************************************

    License: Ext.ux.ModuleManager is licensed under the terms of the Open Source
    LGPL 3.0 license.  Commercial use is permitted to the extent
    that the code/component(s) do NOT become part of another Open Source or Commercially
    licensed development library or toolkit without explicit permission.

    Donations are welcomed: http://donate.theactivegroup.com

    License details: http://www.gnu.org/licenses/lgpl.html


   Sample Usage:

    YourApp.CodeLoader = new Ext.ux.ModuleManager({modulePath:yourBasePath });
    YourApp.CodeLoader.on({
            'beforeload':function(manager, module, response){

                //return false to prevent the script from being executed.
                return module.extension == 'js';

            }
            ,scope:YourApp.CodeLoader
            });

    //Create a useful 'syntax' for you App.

    YourApp.needs = YourApp.CodeLoader.load.createDelegate(YourApp.CodeLoader);
    YourApp.provide = YourApp.CodeLoader.provides.createDelegate(YourApp.CodeLoader);

    YourApp.needs('ext/layouts','js/dragdrop','js/customgrid','style/custom.css');

*/

  (function(){

    Ext.ux.ModuleManager = function(config){

        Ext.apply(this,
            config||{},
            { modulePath:function(){  //based on current page
                   var d= location.href.indexOf('\/') != -1 ? '\/':'\\';
                   var u=location.href.split(d);
                   u.pop(); //this page
                   return u.join(d) + d;
                    }()
             });


        this.addEvents({
                /**
                * @event loadexception
                * Fires when any exception is raised
                * returning false prevents any subsequent pending module load requests
                * @param {Ext.ux.ModuleManager} this
                * @param {String} module -- the module object
                * @param {Object} error -- An error object containing: httpStatus, httpStatusText, error object
                */
                "loadexception" : true,

                /**
               * @event alreadyloaded
               * Fires when the ModuleManager determines that the requested module has already been loaded
               * @param {Ext.ux.ModuleManager} this
               * @param {String} module -- the module object
               */
                "alreadyloaded" : true,

               /**
                * @event load
                * Fires when the retrieved content has been successfully loaded
                * @param {Ext.ux.ModuleManager} this
                * @param {String} module -- the module name
                * @param {Object} response - the Ajax response object
                * @param {String} contents -- the raw text content retrieved
                * @param {Boolean} executed -- true if the resource was executed into the target context.
                */
                "load" : true,

                /**
                 * @event beforeload
                 * Fires when the request has successfully completed and just prior to eval
                 * returning false prevents the content (of this module) from being loaded (eval'ed)
                 * @param {Ext.ux.ModuleManager} this
                 * @param {String} module -- the module name
                 * @param {Object} response - the Ajax response object
                 * @param {String} contents -- the raw text content retrieved
                 */
                "beforeload" : true,

                /**
                 * @event complete
                 * Fires when all module load request have completed (successfully or not)
                 * @param {Ext.ux.ModuleManager} this
                 * @param {Boolen} success
                 * @param {Array} loaded -- the modules now available as a result of (or previously -- already loaded) the last load operation.
                 * @param {Array} executed -- modules that were executed (evaled) as a result of (or previously executed) the last load operation.
                 */
                "complete" : true
        });
        Ext.ux.ModuleManager.superclass.constructor.call(this);

    };

    Ext.extend(Ext.ux.ModuleManager, Ext.util.Observable,{

     disableCaching: false

    ,modules : {}

    ,method:'GET'

    ,noExecute    : false

    ,asynchronous : false

    ,cacheResponses : false

    ,loaded:function(name){
        var module;
        return (module = this.getModule(name)) && module.loaded===true;
    }
    ,getModule : function(name){
        return this.modules[name] || false;
    }

    /* A mechanism for modules to identify their presence */
    ,provides : function(){
        Ext.each(arguments,function(module){
           var modName = module.trim().split('\/').pop().toLowerCase()
              ,fullName   = module.indexOf('.') !== -1 ? module.trim() : module.trim() + '.js';

           this.modules[modName] || (this.modules[modName] =
             {
                 name       :modName.trim()
                ,fullName   :fullName.trim()
                ,extension  :fullName.split('.').pop().trim()
                ,path       :''
                ,url        :''
                ,loaded     :true
                ,contentType:''
                ,content    : null
                });

        },this);

    }
    /*
     load external resources in dependency order




    */

    ,load:function(modList){
      var opt,modules;
      if(Ext.isArray(modList) || arguments.length == 1){
          modules= [].concat(modList);
          opt = arguments[1]||{};
      }else{
          modules= Array.prototype.slice.call(arguments, 0);
          opt = {};
      }

      var result           = true
         ,keepItUp         = true
         ,StopIter         = "StopIteration"
         ,options          = Ext.apply({async:this.asynchronous,headers:this.headers||false},opt)
         ,executed         = []
         ,loaded           = []
         ,noExecute        = options.noExecute || this.noExecute || false
         ,cacheResponses   = options.cacheResponses || this.cacheResponses
         ,disableCaching   = this.disableCaching || options.disableCaching || false
         ,method           = options.method || this.method || 'GET'
         ,modulePath       = options.modulePath || this.modulePath
         ,forced           = options.forced || this.forced
         ,params           = options.params || null
         ,data             = null

         ,callback         = {
            success:function(response){
                var module     = response.argument.module
               ,moduleName = response.argument.module.name
               ,executable = (module.extension=="js" && !noExecute);

               try{
                     module.contentType = response.getResponseHeader['Content-Type'] || '';
                     module.content = cacheResponses ? response.responseText : null;

                     if(this.fireEvent('beforeload', this, module, response, response.responseText) !== false){
                        this.currentModule = moduleName;
                        if(!options.single){this.modules[moduleName] = module;}
                        module.loaded = true;
                        loaded.push(module);
                        var exception = executable?this.globalEval( response.responseText, options.target ):true;
                        if(exception===true){

                            if(executable){executed.push(module);}

                            try{
                              this.fireEvent('load', this, module, response, response.responseText, executable);
                            }catch(ex){}

                        } else {
                            //coerce to actual module URL
                            throw Ext.applyIf({fileName:module.url ,lineNumber:exception.lineNumber||0 },exception );

                        }
                     }

                }catch(ex) {
                   keepItUp = this.fireEvent('loadexception', this, module, {
                        error         :ex
                       ,httpStatus    :response.status
                       ,httpStatusText:response.statusText
                       });
                      result = false;
                }

           }
           ,failure:function(response){
               var module = response.argument.module;
               module.contentType = response.getResponseHeader?response.getResponseHeader['Content-Type'] || '':'';

               keepItUp = this.fireEvent('loadexception', this, module,{
                    error         :response.fullStatus.error
                   ,httpStatus    :response.status
                   ,httpStatusText:response.statusText
                   });
               result = false;
           }
           ,scope:this
          };

          if(params ){
               if(typeof params == "function"){
                    params = params.call(options.scope||window, options);
               }
               if(typeof params == "object"){
                    params = Ext.urlEncode(params);
               }
               data=params; //setup for post
           }
           //setup single-use listeners for the current request chain
           if(options.listeners){
               this.on(options.listeners);
           }

       /* Iterate the desired modules in there implied dependency order */
       try{

         Ext.each(modules, function(module){
             //strip relative path leaving module name
             var moduleName = module.trim().split('\/').pop()//.toLowerCase()
                ,fullModule = (module.indexOf('.') !== -1 ? module : module + '.js')//.toLowerCase()
                ,moduleObj = {
                         name       :moduleName.trim()
                        ,fullName   :fullModule.trim()
                        ,extension  :fullModule.split('.').pop().trim()
                        ,path       :modulePath
                        ,url        :modulePath + fullModule
                        ,loaded     :false
                        ,contentType    :''
                        ,content    : null
                       }
                ,executable = (moduleObj.extension=="js" && !noExecute)
                ,mod = this.getModule(moduleName);

             if(!mod || (mod && !mod.loaded) || forced){

                   if(method == 'GET'){
                       fullModule += (params?'?'+params:'');
                       if(disableCaching === true){
                          fullModule += (params?'&':'?')+'_dc=' + (new Date().getTime());
                       }
                       data = null;
                   }

                   Ext.apply(callback,{argument:{module:moduleObj}});

                   Ext.lib.Ajax.request(method,modulePath + fullModule, callback, data,options);
             } else {

                   keepItUp = this.fireEvent('alreadyloaded', this, mod);
                   if(executable){
                       executed.push(mod);
                   }
                   loaded.push(mod);
             }
             if(keepItUp===false){throw StopIter;}
          },this);

       } catch(ex){
        if (ex != StopIter)
            {throw ex;}
       }

      this.fireEvent('complete', this, result, loaded, executed);
      if(options.callback){ options.callback.call(options.scope||this, result, loaded ); }
      this.forced = false;
      //cleanup single-use listeners for the current request chain
      if(options.listeners){
         this.un(options.listeners);
       }
      return result;
    }

    ,globalEval: function( data , scope, context ) {
        scope || (scope = window);

        data = String(data||"").trim();

        if(data.length===0){return false;}
        try{
            if(scope.execScript){
                // window.execScript in IE fails when scripts include HTML comment tag.
                scope.execScript(data.replace(/^<!--/,"").replace(/-->$/, ""));
            }else if (Ext.isSafari){
                // safari doesn't provide a synchronous global eval
                scope.setTimeout(data, 0);
            }else{
                // context (target namespace) is only support on Gecko.
                eval.call(scope,data,context || null);
            }
            return true;
        }catch(ex){return ex;}

    }
    ,styleAdjust  : null

    ,applyStyle : function(module, styleRules, target){

         //All css is injected into document's head section
        var doc = (target||window).document;
        var ct = (styleRules || module.content || '')+'';
        var head;
        if(doc && !!ct.length && (head = doc.getElementsByTagName("head")[0])){

            if (this.styleAdjust && this.styleAdjust.pattern) {
               // adjust CSS (eg. urls (images etc))
               ct = ct.replace(this.styleAdjust.pattern, this.styleAdjust.replacement||'');
            }

            var rules = (module||{}).element = doc.createElement("style");
            rules.setAttribute("type", "text/css");
            if(Ext.isIE){
                   head.appendChild(rules);
                   rules.styleSheet.cssText = ct;
            }else{
                   try{
                       rules.appendChild(doc.createTextNode(ct));
                   }catch(e){
                       rules.cssText = ct;
                   }
                   head.appendChild(rules);
            }
        }
        return rules; //the style element created
    }

    /*Helper: Remove a style element
     * @param {Ext.ux.ModuleManager.module or dom Node}
     */
    ,removeStyle  : function(module){
        module && module.element ? Ext.removeNode(module.element): Ext.removeNode(module);
    }
 });

}());
}