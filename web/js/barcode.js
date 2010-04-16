/**
 * boris.lds@gmail.com
 * Version : 1.01 
 * Update : 2010/4/16
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
                shortcut.add("1",function() { isBarcode( '1' ); }, {'disable_in_input':true } ); 
                shortcut.add("2",function() { isBarcode( '2' ); }, {'disable_in_input':true } ); 
                shortcut.add("3",function() { isBarcode( '3' ); }, {'disable_in_input':true } ); 
                shortcut.add("4",function() { isBarcode( '4' ); }, {'disable_in_input':true } ); 
                shortcut.add("5",function() { isBarcode( '5' ); }, {'disable_in_input':true } ); 
                shortcut.add("6",function() { isBarcode( '6' ); }, {'disable_in_input':true } ); 
                shortcut.add("7",function() { isBarcode( '7' ); }, {'disable_in_input':true } ); 
                shortcut.add("8",function() { isBarcode( '8' ); }, {'disable_in_input':true } ); 
                shortcut.add("9",function() { isBarcode( '9' ); }, {'disable_in_input':true } ); 
                shortcut.add("0",function() { isBarcode( '0' ); }, {'disable_in_input':true } ); 
                shortcut.add("a",function() { isBarcode( 'a' ); }, {'disable_in_input':true } ); 
                shortcut.add("b",function() { isBarcode( 'b' ); }, {'disable_in_input':true } ); 
                shortcut.add("c",function() { isBarcode( 'c' ); }, {'disable_in_input':true } ); 
                shortcut.add("d",function() { isBarcode( 'd' ); }, {'disable_in_input':true } ); 
                shortcut.add("e",function() { isBarcode( 'e' ); }, {'disable_in_input':true } ); 
                shortcut.add("f",function() { isBarcode( 'f' ); }, {'disable_in_input':true } ); 
                shortcut.add("g",function() { isBarcode( 'g' ); }, {'disable_in_input':true } ); 
                shortcut.add("h",function() { isBarcode( 'h' ); }, {'disable_in_input':true } ); 
                shortcut.add("i",function() { isBarcode( 'i' ); }, {'disable_in_input':true } ); 
                shortcut.add("j",function() { isBarcode( 'j' ); }, {'disable_in_input':true } ); 
                shortcut.add("k",function() { isBarcode( 'k' ); }, {'disable_in_input':true } ); 
                shortcut.add("l",function() { isBarcode( 'l' ); }, {'disable_in_input':true } ); 
                shortcut.add("m",function() { isBarcode( 'm' ); }, {'disable_in_input':true } ); 
                shortcut.add("n",function() { isBarcode( 'n' ); }, {'disable_in_input':true } ); 
                shortcut.add("o",function() { isBarcode( 'o' ); }, {'disable_in_input':true } ); 
                shortcut.add("p",function() { isBarcode( 'p' ); }, {'disable_in_input':true } ); 
                shortcut.add("q",function() { isBarcode( 'q' ); }, {'disable_in_input':true } ); 
                shortcut.add("r",function() { isBarcode( 'r' ); }, {'disable_in_input':true } ); 
                shortcut.add("s",function() { isBarcode( 's' ); }, {'disable_in_input':true } ); 
                shortcut.add("t",function() { isBarcode( 't' ); }, {'disable_in_input':true } ); 
                shortcut.add("u",function() { isBarcode( 'u' ); }, {'disable_in_input':true } ); 
                shortcut.add("v",function() { isBarcode( 'v' ); }, {'disable_in_input':true } ); 
                shortcut.add("w",function() { isBarcode( 'w' ); }, {'disable_in_input':true } ); 
                shortcut.add("x",function() { isBarcode( 'x' ); }, {'disable_in_input':true } ); 
                shortcut.add("y",function() { isBarcode( 'y' ); }, {'disable_in_input':true } ); 
                shortcut.add("z",function() { isBarcode( 'z' ); }, {'disable_in_input':true } ); 
                shortcut.add("Tab",function() { str=""; });//清除 str ，準備讀入新的條碼 
            }//domo
            jQuery(document).ready(domo);