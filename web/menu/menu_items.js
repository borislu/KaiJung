// items structure
// each item is the array of one or more properties:
// [text, link, settings, subitems ...]
// use the builder to export errors free structure if you experience problems with the syntax

var MENU_ITEMS = [
	['EIS', null, null],
	['業績管理', null, null,
		['日業績補登', 'javascript: onclick=Application.evalCode(\"dailyMakeup\", true)', null, 
			['新增', 'javascript: onclick=Application.evalCode(\"dailyAdd\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"dailySearch\", true)']
		],
		['指標維護', 'javascript: onclick=Application.evalCode(\"benchmark\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"benchmarkAdd\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"benchmarkSearch\", true)']
		],
		['業績彙整表', 'javascript: onclick=Application.evalCode(\"dailySalesSum\", true)', null,
			['查詢', 'javascript: onclick=Application.evalCode(\"dailySearch\", true)']
		],
		['業務活動', 'javascript: onclick=Application.evalCode(\"salesACtivityList\", true)', null, 
			['新增', 'javascript: onclick=Application.evalCode(\"salesActivityAdd\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"salesActivitySearch\", true)'] // 最後一筆，不能有逗號
		],
		['月業績記錄', 'javascript: onclick=Application.evalCode(\"salesMonthly\", true)', null, 
			['新增', 'javascript: onclick=Application.evalCode(\"salesMonthlyAdd\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"salesMonthlySearch\", true)'] // 最後一筆，不能有逗號
		],
		['月業績總表', 'javascript: onclick=Application.evalCode(\"salesMonthlySumList\", true)', null
		],
		['年度業績記錄', 'javascript: onclick=Application.evalCode(\"salesAnnualList\", true)', null, 
			['新增', 'javascript: onclick=Application.evalCode(\"salesAnnualAdd\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"salesAnnualSearch\", true)'] // 最後一筆，不能有逗號
		],
		['年度業績總表', 'javascript: onclick=Application.evalCode(\"salesAnnualSumList\", true)', null
		],
		['業績目標', null, null,
			['新增', 'javascript: onclick=Application.evalCode(\"goalAdd\", true)', null],
			['查詢', 'javascript: onclick=Application.evalCode(\"goalSearch\", true)', null],
		],
		['業績目標分析', 'javascript: onclick=Application.evalCode(\"salesGoal\", true)', null],
		['業績基本分析', 'javascript: onclick=Application.evalCode(\"salesBasicAnalysis\", true)', null],
		['業績記錄分析', 'javascript: onclick=Application.evalCode(\"salesGoal\", true)', null
		],
		['鄰櫃日業績', 'javascript: onclick=Application.evalCode(\"opponentDaily\", true)', null, 
			['新增', 'javascript: onclick=Application.evalCode(\"opponentDaily\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"opponentDaily\", true)'] // 最後一筆，不能有逗號
		],
		['鄰櫃業績彙整', null, null]
	],
	['訂貨管理', null, null,
		['訂貨單', null, null,
			['新增', 'javascript: onclick=Application.evalCode(\"orderStore\", true)', null],
			['查詢', 'javascript: onclick=Application.evalCode(\"orderStore\", true)', null],
			['瀏覽', 'javascript: onclick=Application.evalCode(\"orderStore\", true)', null],
			['修改', 'javascript: onclick=Application.evalCode(\"orderStore\", true)', null],
			['刪除', 'javascript: onclick=Application.evalCode(\"orderStore\", true)', null]
		],
		['撥入單維護', 'javascript: onclick=Application.evalCode(\"receiveList\", true)', null, 
			['撥入', 'javascript: onclick=Application.evalCode(\"receiveList\", true)'] // 最後一筆，不能有逗號
	 ],
		['修改單維護', 'javascript: onclick=Application.evalCode(\"customerizing\", true)', null, 
			['新增', 'javascript: onclick=Application.evalCode(\"customerizing\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerizing\", true)'],
			['修改', 'javascript: onclick=Application.evalCode(\"customerizing\", true)'],
			['刪除', 'javascript: onclick=Application.evalCode(\"customerizing\", true)'] // 最後一筆，不能有逗號
		],
		['揀貨單維護', 'javascript: onclick=Application.evalCode(\"pickList\", true)', null, 
			['查詢', 'javascript: onclick=Application.evalCode(\"pickSearch\", true)'],
			['修改', 'javascript: onclick=Application.evalCode(\"pickUpdate\", true)'],
			['刪除', 'javascript: onclick=Application.evalCode(\"pickList\", true)'] // 最後一筆，不能有逗號
		],
		['撥出單維護', 'javascript: onclick=Application.evalCode(\"sendFrame\", true)', null, 
			['查詢', 'javascript: onclick=Application.evalCode(\"sendSearch\", true)'] // 最後一筆，不能有逗號
	        ],	
	        ['做貨單維護', 'javascript: onclick=Application.evalCode(\"counterOrder\", true)', null, 
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"counterOrderSearch\", true)'] // 最後一筆，不能有逗號
		],
	        ['調退貨維護', 'javascript: onclick=Application.evalCode(\"transferFrame\", true)', null, 
			['新增', 'javascript: onclick=Application.evalCode(\"transferFrame\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"transferSearch\", true)'] // 最後一筆，不能有逗號
		],
	        ['補貨單維護', 'javascript: onclick=Application.evalCode(\"makeupFrame\", true)', null, 
			['新增', 'javascript: onclick=Application.evalCode(\"makeupFrame\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"makeupSearch\", true)'] // 最後一筆，不能有逗號
		],
	        ['庫位調整維護', 'javascript: onclick=Application.evalCode(\"wareFrame\", true)', null, 
			['新增', 'javascript: onclick=Application.evalCode(\"wareFrame\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"wareAdjustSearch\", true)'] // 最後一筆，不能有逗號
		],
		['訂貨差異分析', 'javascript: onclick=Application.evalCode(\"orderDifference\", true)', null],
		['進銷存單', 'javascript: onclick=Application.evalCode(\"oss\", true)', null]
	],
	['人員管理', null, null,
		['員工基本資料', 'javascript: onclick=Application.evalCode(\"employee\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"employee\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"employee\", true)']
		],
		['人員排班', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],
		['專櫃排班', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],
		['休假意願表', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],
		['人員出勤表', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],
		['簽到單', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		]
	],
	['銷售管理', null, null,
		['客戶基本資料', 'javascript: onclick=Application.evalCode(\"customer\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerSearch\", true)']
		],
		['客戶消費記錄', '../demo4/index.html'],
		['客戶消費分析', '../demo5/index.html'],
		['客戶活動記錄', '../demo6/index.html'],
		['客戶服務記錄', '../demo6/index.html'],
		['販促方案維護', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],		
	],
	['商品管理', null, null,
		['商品基本資料', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['價格設定', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],
		['商品販售計劃表', null, null, 
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢'] // 最後一筆，不能有逗號
		],		
		['發貨排程', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],		
		['品類銷售分析', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
		['商品進出貨分析表', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
		['商品銷貨期間表', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
		['商品銷貨排行表', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
		['商品銷貨毛利分析表', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
		['商品銷貨總表', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
		['廠商基本資料', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null, 
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢'] // 最後一筆，不能有逗號
		]
	],
	['專櫃管理', null, null,
		['專櫃基本資料', 'javascript: onclick=Application.evalCode(\"wareList\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['進櫃商品新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['進櫃商品維護', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],		
		['拍檔新櫃提案', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],		
		['拍檔檔期維護', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],		
		['進撤櫃檢查表維護', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		]
	],
	['基本資料維護', null, null,
		['品牌設定', 'javascript: onclick=Application.evalCode(\"brand\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"brand\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"brand\", true)']
		],
		['品號類別設定', 'javascript: onclick=Application.evalCode(\"prdNo\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"prdNo\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"prdNo\", true)']
		],
		['產品類別設定', 'javascript: onclick=Application.evalCode(\"prdCategory\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"prdCategory\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"prdCategory\", true)']
		],
		['產品類型設定', 'javascript: onclick=Application.evalCode(\"prdType\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"prdType\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"prdType\", true)']
		],
		['尺寸設定', 'javascript: onclick=Application.evalCode(\"size\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"size\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"size\", true)']
		],
		['顏色設定', 'javascript: onclick=Application.evalCode(\"itemColor\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"itemColor\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"itemColor\", true)']
		],
		['系列名稱設定', 'javascript: onclick=Application.evalCode(\"size\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],
		['銷售類型設定', 'javascript: onclick=Application.evalCode(\"salesType\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"salesType\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"salesType\", true)']
		],
		['單位設定', 'javascript: onclick=Application.evalCode(\"unit\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"unit\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"unit\", true)']
		],
		['廠商類別設定', 'javascript: onclick=Application.evalCode(\"supplierType\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"supplierType\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"supplierType\", true)']
		],
		['國家設定', 'javascript: onclick=Application.evalCode(\"country\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"country\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"country\", true)']
		],
		['幣別設定', 'javascript: onclick=Application.evalCode(\"currency\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"currency\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"currency\", true)']
		],
		['區域設定', 'javascript: onclick=Application.evalCode(\"area\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"area\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"area\", true)']
		],
		['部門設定', 'javascript: onclick=Application.evalCode(\"department\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"department\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"department\", true)']
		],
		['付款條件設定', 'javascript: onclick=Application.evalCode(\"payWay\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"payWay\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"payWay\", true)']
		],
		['交貨方式設定', 'javascript: onclick=Application.evalCode(\"size\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],
		['調整原因設定', 'javascript: onclick=Application.evalCode(\"adjustReason\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"adjustReason\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"adjustReason\", true)']
		],
		['合作銀行設定', 'javascript: onclick=Application.evalCode(\"bank\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"bank\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"bank\", true)']
		],
		['發票資料設定', 'javascript: onclick=Application.evalCode(\"invoice\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"invoice\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"invoice\", true)']
		],
		['營收存入設定', 'javascript: onclick=Application.evalCode(\"saving\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"saving\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"saving\", true)']
		],
		['指定期間設定', 'javascript: onclick=Application.evalCode(\"period\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"period\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"period\", true)']
		]
	],
	['系統管理', null, null,
		['國際條碼轉檔', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],		
		['群組設定', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],		
		['權限設定', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],		
		['環境設定', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		],
		['客製化查詢作業', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)', null,
			['新增', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)'],
			['查詢', 'javascript: onclick=Application.evalCode(\"customerMaintain\", true)']
		]
	] //,
	//['', null, null,
	//]
];

