/**
 * boris.lds@gmail.com
 * Version : 1.00
 * By Boris Lu Taiwan
 * License : BSD
 */
            var str = '';
            function isBarcode( character ){
                str += character;
                if( str.length == 13 ){
                    //alert('這是13位的條碼');
                    getByBarcode( str );//需要另外客製化
                    str = '';
                    }
                }
            function domo(){ 
                shortcut.add("1",function() { isBarcode( '1' ); }, {'propagate':true} ); 
                shortcut.add("2",function() { isBarcode( '2' ); }, {'propagate':true} ); 
                shortcut.add("3",function() { isBarcode( '3' ); }, {'propagate':true} ); 
                shortcut.add("4",function() { isBarcode( '4' ); }, {'propagate':true} ); 
                shortcut.add("5",function() { isBarcode( '5' ); }, {'propagate':true} ); 
                shortcut.add("6",function() { isBarcode( '6' ); }, {'propagate':true} ); 
                shortcut.add("7",function() { isBarcode( '7' ); }, {'propagate':true} ); 
                shortcut.add("8",function() { isBarcode( '8' ); }, {'propagate':true} ); 
                shortcut.add("9",function() { isBarcode( '9' ); }, {'propagate':true} ); 
                shortcut.add("0",function() { isBarcode( '0' ); }, {'propagate':true} ); 
                shortcut.add("a",function() { isBarcode( 'a' ); }, {'propagate':true} ); 
                shortcut.add("b",function() { isBarcode( 'b' ); }, {'propagate':true} ); 
                shortcut.add("c",function() { isBarcode( 'c' ); }, {'propagate':true} ); 
                shortcut.add("d",function() { isBarcode( 'd' ); }, {'propagate':true} ); 
                shortcut.add("e",function() { isBarcode( 'e' ); }, {'propagate':true} ); 
                shortcut.add("f",function() { isBarcode( 'f' ); }, {'propagate':true} ); 
                shortcut.add("g",function() { isBarcode( 'g' ); }, {'propagate':true} ); 
                shortcut.add("h",function() { isBarcode( 'h' ); }, {'propagate':true} ); 
                shortcut.add("i",function() { isBarcode( 'i' ); }, {'propagate':true} ); 
                shortcut.add("j",function() { isBarcode( 'j' ); }, {'propagate':true} ); 
                shortcut.add("k",function() { isBarcode( 'k' ); }, {'propagate':true} ); 
                shortcut.add("l",function() { isBarcode( 'l' ); }, {'propagate':true} ); 
                shortcut.add("m",function() { isBarcode( 'm' ); }, {'propagate':true} ); 
                shortcut.add("n",function() { isBarcode( 'n' ); }, {'propagate':true} ); 
                shortcut.add("o",function() { isBarcode( 'o' ); }, {'propagate':true} ); 
                shortcut.add("p",function() { isBarcode( 'p' ); }, {'propagate':true} ); 
                shortcut.add("q",function() { isBarcode( 'q' ); }, {'propagate':true} ); 
                shortcut.add("r",function() { isBarcode( 'r' ); }, {'propagate':true} ); 
                shortcut.add("s",function() { isBarcode( 's' ); }, {'propagate':true} ); 
                shortcut.add("t",function() { isBarcode( 't' ); }, {'propagate':true} ); 
                shortcut.add("u",function() { isBarcode( 'u' ); }, {'propagate':true} ); 
                shortcut.add("v",function() { isBarcode( 'v' ); }, {'propagate':true} ); 
                shortcut.add("w",function() { isBarcode( 'w' ); }, {'propagate':true} ); 
                shortcut.add("x",function() { isBarcode( 'x' ); }, {'propagate':true} ); 
                shortcut.add("y",function() { isBarcode( 'y' ); }, {'propagate':true} ); 
                shortcut.add("z",function() { isBarcode( 'z' ); }, {'propagate':true} ); 
                shortcut.add("Tab",function() { str=""; });//清除 str ，準備讀入新的條碼 
            }//domo
            jQuery(document).ready(domo);