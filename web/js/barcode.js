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
                    alert('這是13位的條碼');
                    getByBarcode( str );//需要另外客製化
                    str = '';
                    }
                }
            function domo(){ 
                shortcut.add("1",function() { isBarcode( '1' ); }); 
                shortcut.add("2",function() { isBarcode( '2' ); }); 
                shortcut.add("3",function() { isBarcode( '3' ); }); 
                shortcut.add("4",function() { isBarcode( '4' ); }); 
                shortcut.add("5",function() { isBarcode( '5' ); }); 
                shortcut.add("6",function() { isBarcode( '6' ); }); 
                shortcut.add("7",function() { isBarcode( '7' ); }); 
                shortcut.add("8",function() { isBarcode( '8' ); }); 
                shortcut.add("9",function() { isBarcode( '9' ); }); 
                shortcut.add("0",function() { isBarcode( '0' ); }); 
                shortcut.add("a",function() { isBarcode( 'a' ); }); 
                shortcut.add("b",function() { isBarcode( 'b' ); }); 
                shortcut.add("c",function() { isBarcode( 'c' ); }); 
                shortcut.add("d",function() { isBarcode( 'd' ); }); 
                shortcut.add("e",function() { isBarcode( 'e' ); }); 
                shortcut.add("f",function() { isBarcode( 'f' ); }); 
                shortcut.add("g",function() { isBarcode( 'g' ); }); 
                shortcut.add("h",function() { isBarcode( 'h' ); }); 
                shortcut.add("i",function() { isBarcode( 'i' ); }); 
                shortcut.add("j",function() { isBarcode( 'j' ); }); 
                shortcut.add("k",function() { isBarcode( 'k' ); }); 
                shortcut.add("l",function() { isBarcode( 'l' ); }); 
                shortcut.add("m",function() { isBarcode( 'm' ); }); 
                shortcut.add("n",function() { isBarcode( 'n' ); }); 
                shortcut.add("o",function() { isBarcode( 'o' ); }); 
                shortcut.add("p",function() { isBarcode( 'p' ); }); 
                shortcut.add("q",function() { isBarcode( 'q' ); }); 
                shortcut.add("r",function() { isBarcode( 'r' ); }); 
                shortcut.add("s",function() { isBarcode( 's' ); }); 
                shortcut.add("t",function() { isBarcode( 't' ); }); 
                shortcut.add("u",function() { isBarcode( 'u' ); }); 
                shortcut.add("v",function() { isBarcode( 'v' ); }); 
                shortcut.add("w",function() { isBarcode( 'w' ); }); 
                shortcut.add("x",function() { isBarcode( 'x' ); }); 
                shortcut.add("y",function() { isBarcode( 'y' ); }); 
                shortcut.add("z",function() { isBarcode( 'z' ); }); 
                shortcut.add("Tab",function() { str=""; });//清除 str ，準備讀入新的條碼 
            }//domo
            jQuery(document).ready(domo);