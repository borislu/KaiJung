/*
 * Ext JS Library 2.0.2
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 *
 * http://extjs.com/license
 */
// Sample desktop configuration
MyDesktop = new Ext.app.App({
    init: function(){
        Ext.QuickTips.init();
    },
   
    getModules: function(){
        return [
          new MyDesktop.BogusMenuModule1()
        , new MyDesktop.BogusMenuModule2()
        , new MyDesktop.BogusMenuModule3()
        , new MyDesktop.BogusMenuModule4()
        , new MyDesktop.BogusMenuModule5()
        , new MyDesktop.BogusMenuModule6()
        , new MyDesktop.BogusMenuModule7()
        , new MyDesktop.BogusMenuModule8()];
    },
    
    // config for the start menu
    getStartConfig: function(){
        return {
            title: 'EIS',
            iconCls: 'user',
            toolItems: [{
                text: '自訂',
                iconCls: 'settings',
                scope: this
            }, '-', {
                text: '登出',
                iconCls: 'logout',
                scope: this
            }]
        };
    }
});

/*
 * 左下的開始功能列 開啟視窗
 */
MyDesktop.BogusModule = Ext.extend(Ext.app.Module, {
    init: function(){
        
    },
    
    createWindow0101: function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus' + src.windowId);
        if (!win) {
            win = desktop.createWindow({
                id: '0101',
                title: src.text,
                width: 998,
                height: 480,
                html: '<iframe id="frameLayout" name="frameLayout" src="../../member/memberLayout.html" frameborder="0" height="100%" scrolling="auto" width="100%"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    },
    createWindow0102: function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus' + src.windowId);
        if (!win) {
            win = desktop.createWindow({
                id: '0102',
                title: src.text,
                width: 998,
                height: 480,
                html: '<iframe id="frameLayout" name="frameLayout" src="../../treatment/treatmentLayout.html" frameborder="0" height="100%" scrolling="auto" width="100%"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    },
    createWindow020101: function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus' + src.windowId);
        if (!win) {
            win = desktop.createWindow({
                id: 'w020101',
                title: src.text,
                width: 998,
                height: 480,
                html: '<iframe id="frameLayout" name="frameLayout" src="../../orderStore/orderAddLayout.html" frameborder="0" height="100%" scrolling="auto" width="100%"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    },
    createWindow020102: function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus' + src.windowId);
        if (!win) {
            win = desktop.createWindow({
                id: 'w020102',
                title: src.text,
                width: 998,
                height: 480,
                html: '<iframe id="frameLayout" name="frameLayout" src="../../orderStore/orderLayout.html" frameborder="0" height="100%" scrolling="auto" width="100%"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    },
    createWindow020103: function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus' + src.windowId);
        if (!win) {
            win = desktop.createWindow({
                id: 'w020103',
                title: src.text,
                width: 998,
                height: 480,
                html: '<iframe id="frameLayout" name="frameLayout" src="../../orderStore/orderLayout.html" frameborder="0" height="100%" scrolling="auto" width="100%"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    },
    createWindow020104: function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus' + src.windowId);
        if (!win) {
            win = desktop.createWindow({
                id: 'w020104',
                title: src.text,
                width: 998,
                height: 480,
                html: '<iframe id="frameLayout" name="frameLayout" src="../../orderStore/orderLayout.html" frameborder="0" height="100%" scrolling="auto" width="100%"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    },
    createWindow020105: function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus' + src.windowId);
        if (!win) {
            win = desktop.createWindow({
                id: 'w02005',
                title: src.text,
                width: 998,
                height: 480,
                html: '<iframe id="frameLayout" name="frameLayout" src="../../orderStore/orderLayout.html" frameborder="0" height="100%" scrolling="auto" width="100%"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    },
    createWindow0404: function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus' + src.windowId);
        if (!win) {
            win = desktop.createWindow({
                id: 'w0404',
                title: src.text,
                width: 998,
                height: 480,
                html: '<iframe id="frameLayout" name="frameLayout" src="../../sms/smsLayout.html" frameborder="0" height="100%" scrolling="auto" width="100%"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    },
    createWindow0405: function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus' + src.windowId);
        if (!win) {
            win = desktop.createWindow({
                id: 'w0405',
                title: src.text,
                width: 998,
                height: 480,
                html: '<iframe id="frameLayout" name="frameLayout" src="../../basic/brandLayout.html" frameborder="0" height="100%" scrolling="auto" width="100%"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    },
    createWindow0406: function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus' + src.windowId);
        if (!win) {
            win = desktop.createWindow({
                id: 'w0406',
                title: src.text,
                width: 998,
                height: 480,
                html: '<iframe id="frameLayout" name="frameLayout" src="../../basic/brandLayout.html" frameborder="0" height="100%" scrolling="auto" width="100%"></iframe>',
                iconCls: 'bogus',
                shim: false,
                animCollapse: false,
                constrainHeader: true
            });
        }
        win.show();
    }
 
});

MyDesktop.BogusMenuModule1 = Ext.extend(MyDesktop.BogusModule, {
    init: function(){
        this.launcher = {
            text: '業績管理',
            iconCls: 'bogus',
            handler: function(){
                return false;
            },
            menu: {
                items: [{
                    text: '日業績補登',
                    iconCls: 'bogus',
                    handler: this.createWindow0101,
                    scope: this,
                    windowId: '0101'
                }, {
                    text: '指標維護',
                    iconCls: 'bogus',
                    handler: this.createWindow0102,
                    scope: this,
                    windowId: '0102'
                }, {
                    text: '業績彙整表',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0103'
                }, {
                    text: '業務活動',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0104'
                }, {
                    text: '月業績記錄',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0105'
                }, {
                    text: '月業績總表',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0106'
                }, {
                    text: '年度業績記錄',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0107'
                }, {
                    text: '年度業績總表',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0108'
                }, {
                    text: '業績目標',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0109'
                }, {
                    text: '業績目標分析',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0110'
                }, {
                    text: '業績基本分析',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0111'
                }, {
                    text: '業績記錄分析',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0112'
                }, {
                    text: '鄰櫃日業績',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0113'
                }, {
                    text: '鄰櫃業績彙整',
                    iconCls: 'bogus',
                    handler: this.createWindow0103,
                    scope: this,
                    windowId: '0114'
                }]
            }
        }
    }
});

MyDesktop.BogusMenuModule2 = Ext.extend(MyDesktop.BogusModule, {
    init: function(){
        this.launcher = {
            text: '訂貨管理',
            iconCls: 'bogus',
            handler: function(){
                return false;
            },
            menu: {
                items: [{
                    text: '訂貨單',
                    iconCls: 'bogus',
                    handler: this.createWindow0201,
                    scope: this,
                    windowId: '0201',
                    menu: { 
                          items: [{
                              text: '新增',
                              iconCls: 'bogus',
                              handler: this.createWindow020101,
                              scope: this,
                              windowId: '020101'
                                   }, {
                              text: '查詢',
                              iconCls: 'bogus',
                              handler: this.createWindow020102,
                              scope: this,
                              windowId: '020102'
                                   }, {
                              text: '瀏覽',
                              iconCls: 'bogus',
                              handler: this.createWindow020103,
                              scope: this,
                              windowId: '020103'
                                   }, {
                              text: '修改',
                              iconCls: 'bogus',
                              handler: this.createWindow020104,
                              scope: this,
                              windowId: '020104'
                                   }, {
                              text: '刪除',
                              iconCls: 'bogus',
                              handler: this.createWindow020105,
                              scope: this,
                              windowId: '020105'
                                   }]
                           }
                }, {
                    text: '撥入單維護',
                    iconCls: 'bogus',
                    handler: this.createWindow0202,
                    scope: this,
                    windowId: '0202'
                }, {
                    text: '修改單維護',
                    iconCls: 'bogus',
                    handler: this.createWindow0203,
                    scope: this,
                    windowId: '0203'
                }, {
                    text: '揀貨單維護',
                    iconCls: 'bogus',
                    handler: this.createWindow0204,
                    scope: this,
                    windowId: '0204'
                }, {
                    text: '撥出單維護',
                    iconCls: 'bogus',
                    handler: this.createWindow0205,
                    scope: this,
                    windowId: '0205'
                }, {
                    text: '訂貨差異分析',
                    iconCls: 'bogus',
                    handler: this.createWindow0206,
                    scope: this,
                    windowId: '0206'
                }, {
                    text: '進銷存單',
                    iconCls: 'bogus',
                    handler: this.createWindow0207,
                    scope: this,
                    windowId: '0207'
                }]
            }
        }
    }
});

MyDesktop.BogusMenuModule3 = Ext.extend(MyDesktop.BogusModule, {
    init: function(){
        this.launcher = {
            text: '人員管理',
            iconCls: 'bogus',
            handler: function(){
                return false;
            },
            menu: {
                items: [{
                    text: '員工基本資料',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0301',
                    menu: {
                        items: [{
                            text: '新增',
                            iconCls: 'bogus',
                            handler: this.createWindow,
                            scope: this,
                            windowId: '030101'
                        }, {
                            text: '查詢',
                            iconCls: 'bogus',
                            handler: this.createWindow,
                            scope: this,
                            windowId: '030102'
                        }]
                    }
                }, {
                    text: '人員排班',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0302'
                }, {
                    text: '專櫃排班',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0303'
                }, {
                    text: '休假意願表',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0304'
                }, {
                    text: '人員出勤表',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0305'
                }, {
                    text: '簽到單',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0306'
                }]
            }
        }
    }
});

MyDesktop.BogusMenuModule4 = Ext.extend(MyDesktop.BogusModule, {
    init: function(){
        this.launcher = {
            text: '銷售管理',
            iconCls: 'bogus',
            handler: function(){
                return false;
            },
            menu: {
                items: [{
                    text: '客戶基本資料',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0401'
                }, {
                    text: '客戶消費記錄',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0402'
                }, {
                    text: '客戶消費分析',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0403'
                }, {
                    text: '客戶活動記錄',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0404'
                }, {
                    text: '客戶服務記錄',
                    iconCls: 'bogus',
                    handler: this.createWindow0404,
                    scope: this,
                    windowId: '0405'
                }, {
                    text: '販促方案維護',
                    iconCls: 'bogus',
                    handler: this.createWindow0405,
                    scope: this,
                    windowId: '0406'
                }]
            }
        }
    }
});

MyDesktop.BogusMenuModule5 = Ext.extend(MyDesktop.BogusModule, {
    init: function(){
        this.launcher = {
            text: '商品管理',
            iconCls: 'bogus',
            handler: function(){
                return false;
            },
            menu: {
                items: [{
                    text: '商品基本資料',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0501'
                }, {
                    text: '商品販售計劃表',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0502'
                }, {
                    text: '發貨排程',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0503'
                }, {
                    text: '品類銷售分析',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0504'
                }, {
                    text: '商品進出貨分析表',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0505'
                }, {
                    text: '商品銷貨期間表',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0506'
                }, {
                    text: '商品銷貨排行表',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0507'
                }, {
                    text: '商品銷貨毛利分析表',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0508'
                }, {
                    text: '商品銷貨總表',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0509'
                }, {
                    text: '廠商基本資料',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0510'
                }]
            }
        }
    }
});

MyDesktop.BogusMenuModule6 = Ext.extend(MyDesktop.BogusModule, {
    init: function(){
        this.launcher = {
            text: '專櫃管理',
            iconCls: 'bogus',
            handler: function(){
                return false;
            },
            menu: {
                items: [{
                    text: '專櫃基本資料',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0601'
                }, {
                    text: '拍檔新櫃提案',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0602'
                }, {
                    text: '拍檔檔期維護',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0603'
                }, {
                    text: '進撤櫃檢查表維護',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0604'
                }]
            }
        }
    }
});

MyDesktop.BogusMenuModule7 = Ext.extend(MyDesktop.BogusModule, {
    init: function(){
        this.launcher = {
            text: '基本資料維護',
            iconCls: 'bogus',
            handler: function(){
                return false;
            },
            menu: {
                items: [{
                    text: '品牌設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0701'
                }, {
                    text: '品號類別設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0702'
                }, {
                    text: '產品類別設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0703'
                }, {
                    text: '尺寸設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0704'
                }, {
                    text: '顏色設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0705'
                }, {
                    text: '系列名稱設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0706'
                }, {
                    text: '銷售類型設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0707'
                }, {
                    text: '單位設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0708'
                }, {
                    text: '廠商類別設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0709'
                }, {
                    text: '國家設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0710'
                }, {
                    text: '幣別設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0711'
                }, {
                    text: '區域設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0712'
                }, {
                    text: '部門設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0713'
                }, {
                    text: '付款條件設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0714'
                }, {
                    text: '交貨方式設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0715'
                }, {
                    text: '調整原因設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0716'
                }, {
                    text: '合作銀行設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0717'
                }, {
                    text: '發票資料設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0718'
                }, {
                    text: '營收存入設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0719'
                }, {
                    text: '指定期間設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0720'
                }]
            }
        }
    }
});

MyDesktop.BogusMenuModule8 = Ext.extend(MyDesktop.BogusModule, {
    init: function(){
        this.launcher = {
            text: '系統管理',
            iconCls: 'bogus',
            handler: function(){
                return false;
            },
            menu: {
                items: [{
                    text: '國際條碼轉檔',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0801'
                }, {
                    text: '群組設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0802'
                }, {
                    text: '權限設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0803'
                }, {
                    text: '環境設定',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0804'
                }, {
                    text: '客製化查詢作業',
                    iconCls: 'bogus',
                    handler: this.createWindow,
                    scope: this,
                    windowId: '0805'
               }]
            }
        }
    }
});


// Array data for the grid
Ext.grid.dummyData = [['3m Co', 71.72, 0.02, 0.03, '9/1 12:00am'], ['Alcoa Inc', 29.01, 0.42, 1.47, '9/1 12:00am'], ['American Express Company', 52.55, 0.01, 0.02, '9/1 12:00am'], ['American International Group, Inc.', 64.13, 0.31, 0.49, '9/1 12:00am'], ['AT&T Inc.', 31.61, -0.48, -1.54, '9/1 12:00am'], ['Caterpillar Inc.', 67.27, 0.92, 1.39, '9/1 12:00am'], ['Citigroup, Inc.', 49.37, 0.02, 0.04, '9/1 12:00am'], ['Exxon Mobil Corp', 68.1, -0.43, -0.64, '9/1 12:00am'], ['General Electric Company', 34.14, -0.08, -0.23, '9/1 12:00am'], ['General Motors Corporation', 30.27, 1.09, 3.74, '9/1 12:00am'], ['Hewlett-Packard Co.', 36.53, -0.03, -0.08, '9/1 12:00am'], ['Honeywell Intl Inc', 38.77, 0.05, 0.13, '9/1 12:00am'], ['Intel Corporation', 19.88, 0.31, 1.58, '9/1 12:00am'], ['Johnson & Johnson', 64.72, 0.06, 0.09, '9/1 12:00am'], ['Merck & Co., Inc.', 40.96, 0.41, 1.01, '9/1 12:00am'], ['Microsoft Corporation', 25.84, 0.14, 0.54, '9/1 12:00am'], ['The Coca-Cola Company', 45.07, 0.26, 0.58, '9/1 12:00am'], ['The Procter & Gamble Company', 61.91, 0.01, 0.02, '9/1 12:00am'], ['Wal-Mart Stores, Inc.', 45.45, 0.73, 1.63, '9/1 12:00am'], ['Walt Disney Company (The) (Holding Company)', 29.89, 0.24, 0.81, '9/1 12:00am']];
