<%@page contentType="text/html; charset=UTF-8"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="language" content="zh-TW" />
<!-- uncomment 'base' to view this page without external files
<base href="http://jquery-border-layout.googlecode.com/svn/trunk/" />
-->

<title>專櫃訂貨明細</title>

<style type="text/css">
#auto
{
}
#auto1
{
  background: url('../icons/auto.png') no-repeat 9% 40%;
  background-color: #EEEEEE;
  width: 100px;
  height: 25px;
  padding-left: 21px; /* sliding doors padding */
  padding-bottom: 2px; /* sliding doors padding */
}
</style>

<link href="../css/rounded.css" rel="stylesheet" type="text/css" />

<!-- DEMO styles - specific to this page -->
<link rel="stylesheet" type="text/css" href="../css/eis.orderLayout.css" />
<!--[if lte IE 7]>
	<style type="text/css"> body { font-size: 85%; } </style>
<![endif]-->

<!-- DEMO scripts - specific to this page -->
<script type="text/javascript" src="../js/eis.orderLayout.js"></script>

<script type="text/javascript" src="../js/eis.common.js"></script>


<!-- REQUIRED scripts for layout widget -->
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.layout.js"></script>

<!-- OPTIONAL ui.draggable is required to resize panes -->
<script type="text/javascript" src="../js/ui.core.js"></script>
<script type="text/javascript" src="../js/ui.draggable.js"></script>

<!-- OPTIONAL animation effects for opening/closing panes -->
<script type="text/javascript" src="../js/effects.core.js"></script>
<script type="text/javascript" src="../js/effects.slide.js"></script>
<script type="text/javascript" src="../js/effects.drop.js"></script>
<script type="text/javascript" src="../js/effects.scale.js"></script>

<!-- FUTURE REFERENCE - ACCESS TO EXTERNAL LIBRARIES
<script type="text/javascript" src="http://jquery-border-layout.googlecode.com/svn/trunk/jquery.layout.js"></script>
<script type="text/javascript" src="http://ui.jquery.com/js/jquery.js"></script>
<script type="text/javascript" src="http://ui.jquery.com/js/ui.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="http://dev.jquery.com/view/tags/ui/latest/ui/ui.core.js"></script>
<script type="text/javascript" src="http://dev.jquery.com/view/tags/ui/latest/ui/ui.draggable.js"></script>
<script type="text/javascript" src="http://dev.jquery.com/view/tags/ui/latest/ui/effects.core.js"></script>
<script type="text/javascript" src="http://dev.jquery.com/view/tags/ui/latest/ui/effects.slide.js"></script>
<script type="text/javascript" src="http://dev.jquery.com/view/tags/ui/latest/ui/effects.drop.js"></script>
<script type="text/javascript" src="http://dev.jquery.com/view/tags/ui/latest/ui/effects.scale.js"></script>
<link rel="stylesheet" type="text/css" href="http://dev.jquery.com/view/tags/ui/latest/themes/flora/flora.all.css" />
-->

<script type="text/javascript">
/*
 * complex.html
 *
 * This is a demonstration page for the $uery layout widget
 *
 *	NOTE: For best code readability, view this with a fixed-space font and tabs equal to 4-chars
 */

	var outerLayout, innerLayout;

	/*
	*#######################
	*     ON PAGE LOAD
	*#######################
	*/
	$(document).ready( function() {
		// create the OUTER LAYOUT
		outerLayout = $("body").layout( layoutSettings_Outer );

		/*******************************
		 ***  CUSTOM LAYOUT BUTTONS  ***
		 *******************************
		 *
		 * Add SPANs to the east/east panes for customer "close" and "pin" buttons
		 *
		 * COULD have hard-coded span, div, button, image, or any element to use as a 'button'...
		 * ... but instead am adding SPANs via script - THEN attaching the layout-events to them
		 *
		 * CSS will size and position the spans, as well as set the background-images
		 */

		// save selector strings to vars so we don't have to repeat it
		// must prefix paneClass with "body > " to target ONLY the outerLayout panes
		var eastSelector = "body > .ui-layout-east"; // outer-east pane

		 // CREATE SPANs for pin-buttons - using a generic class as identifiers
		$("<span></span>").addClass("pin-button").prependTo( eastSelector );

		// BIND events to pin-buttons to make them functional
		outerLayout.addPinBtn( eastSelector +" .pin-button", "east");

		 // CREATE SPANs for close-buttons - using unique IDs as identifiers
		$("<span></span>").attr("id", "east-closer" ).prependTo( eastSelector );

		// BIND layout events to close-buttons to make them functional
		outerLayout.addCloseBtn("#east-closer", "east");



		/* Create the INNER LAYOUT - nested inside the 'center pane' of the outer layout
		 * Inner Layout is create by createInnerLayout() function - on demand
		 *
			innerLayout = $("div.pane-center").layout( layoutSettings_Inner );
		 *
		 */
		innerLayout = $("div.pane-center").layout( layoutSettings_Inner );

		// DEMO HELPER: prevent hyperlinks from reloading page when a 'base.href' is set
		$("a").each(function () {
			var path = document.location.href;
			if (path.substr(path.length-1)=="#") path = path.substr(0,path.length-1);
			if (this.href.substr(this.href.length-1) == "#") this.href = path +"#";
		});

	});


	/*
	*#######################
	* INNER LAYOUT SETTINGS
	*#######################
	*
	* These settings are set in 'list format' - no nested data-structures
	* Default settings are specified with just their name, like: fxName:"slide"
	* Pane-specific settings are prefixed with the pane name + 2-underscores: north__fxName:"none"
	*/
	layoutSettings_Inner = {
		applyDefaultStyles:				false // basic styling for testing & demo purposes
	,	size:					240
	,	minSize:						20 // TESTING ONLY
	,	spacing_closed:					14
	,	north__spacing_closed:			8
	,	south__spacing_closed:			8
	,	north__togglerLength_closed:	-1 // = 100% - so cannot 'slide open'
	,	south__togglerLength_closed:	-1
	,	fxName:							"slide" // do not confuse with "slidable" option!
	,	fxSpeed_open:					1000
	,	fxSpeed_close:					2500
	,	fxSettings_open:				{ easing: "easeInQuint" }
	,	fxSettings_close:				{ easing: "easeOutQuint" }
	,	north__fxName:					"none"
	,	south__fxName:					"drop"
	,	south__fxSpeed_open:			500
	,	south__fxSpeed_close:			1000
	,	initClosed:						false
	,	center__minWidth:				200
	,	center__minHeight:				200
	};


	/*
	*#######################
	* OUTER LAYOUT SETTINGS
	*#######################
	*
	* This configuration illustrates how extensively the layout can be customized
	* ALL SETTINGS ARE OPTIONAL - and there are more available than shown below
	*
	* These settings are set in 'sub-key format' - ALL data must be in a nested data-structures
	* All default settings (applied to all panes) go inside the defaults:{} key
	* Pane-specific settings go inside their keys: north:{}, south:{}, center:{}, etc
	*/
	var layoutSettings_Outer = {
		name: "outerLayout" // NO FUNCTIONAL USE, but could be used by custom code to 'identify' a layout
		// options.defaults apply to ALL PANES - but overridden by pane-specific settings
	,	defaults: {
			size:					"auto"
		,	minSize:				50
		,	paneClass:				"pane" 		// default = 'ui-layout-pane'
		,	resizerClass:			"resizer"	// default = 'ui-layout-resizer'
		,	togglerClass:			"toggler"	// default = 'ui-layout-toggler'
		,	buttonClass:			"button"	// default = 'ui-layout-button'
		,	contentSelector:		".content"	// inner div to auto-size so only it scrolls, not the entire pane!
		,	contentIgnoreSelector:	"span"		// 'paneSelector' for content to 'ignore' when measuring room for content
		,	togglerLength_open:	35			// WIDTH of toggler on north/south edges - HEIGHT on east/west edges
		,	togglerLength_closed:	35			// "100%" OR -1 = full height
		,	hideTogglerOnSlide:		true		// hide the toggler when pane is 'slid open'
		,	togglerTip_open:		"關閉畫面"
		,	togglerTip_closed:		"開啟畫面"
		,	resizerTip:	        	"調整畫面大小"
		//	effect defaults - overridden on some panes
		,	fxName:					"slide"		// none, slide, drop, scale
		,	fxSpeed_open:			750
		,	fxSpeed_close:			1500
		,	fxSettings_open:		{ easing: "easeInQuint" }
		,	fxSettings_close:		{ easing: "easeOutQuint" }
	}
	,	east: {
			paneSelector:			"#eastContent" 			// sample: use an ID to select pane instead of a class
		,	onresize:				"innerLayout.resizeAll"	// resize INNER LAYOUT when center pane resizes
		,	minWidth:				20
		,	minHeight:				200
		,	size:					490
		,	spacing_closed:			21			// wider space when closed
		,	togglerLength_closed:	21			// make toggler 'square' - 21x21
		,	togglerAlign_closed:	"top"		// align to top of resizer
		,	togglerLength_open:		0			// NONE - using custom togglers INSIDE east-pane
		,	togglerTip_open:		"關閉右畫面"
		,	togglerTip_closed:		"開啟右畫面"
		,	resizerTip:		        "調整畫面大小"
		,	slideTrigger_open:		"click" 	// default
		//,	initClosed:				false
		//	add 'bounce' option to default 'slide' effect
		,	fxSettings_open:		{ easing: "easeOutBounce" }
		}
	,	center: {
			paneSelector:			"#mainContent" 			// sample: use an ID to select pane instead of a class
		,	onresize:				"innerLayout.resizeAll"	// resize INNER LAYOUT when center pane resizes
//		,	minWidth:				20
//		,	minHeight:				200
		}
	};

</script>
<script type="text/javascript">
  var rowArray;
  $(document).ready(function(){
      rowArray = new array2DVar(2,10);
      //rowArray[1][1] = '';
      //rowArray[1][2] = '';
      $("#append").click( function(){ updateDetail(); } ); // button
      $("#update").click( function(){ updateDetail(); } ); // button
      $("#delete").click( function(){ updateDetail(); } ); // button
      $("#articleNo").change( function(){ rowArray[1][1] = $('#articleNo').val(); } );
      //$("#barcode").change( function(){ rowArray[1][2] = $('#barcode').val(); } );
/*      
           if ( $("#colorC1").attr('checked') ) {    rowArray[1][2] = $('#color1').val();    }
      else if ( $("#colorC2").attr('checked') ) {    rowArray[1][2] = $('#color2').val();    }
      else if ( $("#colorC3").attr('checked') ) {    rowArray[1][2] = $('#color3').val();    }
      else if ( $("#colorC4").attr('checked') ) {    rowArray[1][2] = $('#color4').val();    }
      else if ( $("#colorC5").attr('checked') ) {    rowArray[1][2] = $('#color5').val();    }
*/
      
      $("#barcode").change( function(){ rowArray[1][10] = $('#altno').val(); } );
  });
  function array2DVar(x,y) { // 定義二維陣列原型
      this.length = x;
      this.x = x; // x 維度長度
      this.y = y; // y 維度長度
      for(var i = 0; i < this.length; i++){ // 初始各元素值為 null
        this[i] = new Array(y); // this 代表物件本身
      }
  }
  function updateDetail() { // 加入訂貨
           if ( $("#colorC1").attr('checked') ) {    rowArray[1][2] = $('#color1').val();    }
      else if ( $("#colorC2").attr('checked') ) {    rowArray[1][2] = $('#color2').val();    }
      else if ( $("#colorC3").attr('checked') ) {    rowArray[1][2] = $('#color3').val();    }
      else if ( $("#colorC4").attr('checked') ) {    rowArray[1][2] = $('#color4').val();    }
      else if ( $("#colorC5").attr('checked') ) {    rowArray[1][2] = $('#color5').val();    }

      $('#r1').val( rowArray[1][1] ) ;
      //alert('r1: '+ $('#r1').val() );
      $('#r2').val( rowArray[1][2] ) ;
      $('#r3').val( rowArray[1][3] ) ;
      $('#r4').val( rowArray[1][4] ) ;
      $('#r5').val( rowArray[1][5] ) ;
      $('#r6').val( rowArray[1][6] ) ;
      $('#r7').val( rowArray[1][7] ) ;
      $('#r8').val( rowArray[1][8] ) ;
      $('#r9').val( rowArray[1][9] ) ;
      $('#r10').val( rowArray[1][10] ) ;
      //checkboxToRadio();
  }
  function checkboxToRadio() { // 將 checkbox 視為 radio
      alert( $( "#counterOrderAddManForm1" ) );
      alert( $( "color[]".length ));
  }
  /*
  function detailRows() {
      alert( "enter detailRows" );
      $('#r1').val( rowArray[1][1] ) ;
      //alert('r1: '+ $('#r1').val() );
      $('#r2').val( rowArray[1][2] ) ;
      //alert( "r2 "+ $('#r2').val() );
  }*/
  function changeListFrame ( url ){  
//          var o = document.getElementById('mainFrame');
//          o.src = url;
//          o.contentWindow.location.href=url;
  }
</script>

</head>
<body>

<div id="mainContent" class="ui-layout-main">

        <div class="header">手動訂貨</div>
               <span id="west1"></span>
               <%@include file="counterOrderAddMan.html"%>
               <!--
               <script type="text/javascript">
               $.get("http://localhost:8980/KaiJung/xava/module.jsp?application=KaiJung&module=Customer",function(data){
               $("#west1").html(data);
               });
               </script>
               -->
        <div class="footer">
            <!--<button name="auto" id="auto" class="rounded"><span>&nbsp;&nbsp;&nbsp;&nbsp;自動訂貨</span></button>-->
            <input name="auto" id="auto" value="自動訂貨" type="button" />
            <input name="delete" id="delete" value="清 除" type="reset" />
            <!--<input type="image" src="../icons/film.png" align=absmiddle onclick="clearForm( document.getElementById('counterOrderAddManForm1') );">-->
            <input name="append" id="append" value="加入訂貨" type="button" />
            <input type="button" name="update" id="update" value="修 改" />
            <input type="button" name="delete" id="delete" value="刪 除" />
            <!--<input type="image" src="../icons/film.png" align=absmiddle onclick="location.href='index.html'">-->
        </div>

	       <div class="ui-layout-south">
               <div class="header">建議訂貨
                    <input type="image" src="../icons/film.png" align=absmiddle onclick="location.href='index.html'">
                    <input type="image" src="../icons/film.png" align=absmiddle onclick="clearForm( document.getElementById('counterOrderAddManForm1') );">
               </div>
<jsp:include page="/xava/module.jsp" >
    <jsp:param name="application" value="KaiJung" />
    <jsp:param name="module" value="Customer" />
</jsp:include>
               <!--
               <%@include file="/xava/module.jsp?application=KaiJung&module=Customer"%>
               <span id="west2"></span>
               <script type="text/javascript">
               $.get("counterOrderAddAuto.html",function(data){
               $("#west2").html(data);
               });
               </script>
               -->
        </div>
</div>

<div id="eastContent" class="ui-layout-east">

        <div class="header">專櫃訂貨明細
                <input type="button" name="print" id="print" value="修 改" />
                <input type="button" name="print" id="print" value="列 印" />
                <input type="button" name="excel" id="excel" value="Excel" />
                <input type="button" name="pdf" id="pdf" value="PDF" />
                <input type="button" name="send" id="send" value="訂單送出" style="position:relative;left:1px;" />
        </div>
        <%@include file="counterOrderDetail.inc"%>
        <!--
        <span id="east1"></span>
        <script type="text/javascript">
        $.get("counterOrderDetail.html",function(data){
        $("#east1").html(data);
        });
        </script>
        -->
</div>

</body>
</html> 
