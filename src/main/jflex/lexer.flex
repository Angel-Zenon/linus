package com.mycompany.linus;


// Importamos las clases necesarias para CUP
import java_cup.runtime.Symbol;

%%

/* Configuraciones */
%class Lexer
%cup
%public
%line
%column

/* IMPORTANTE: Al usar %cup, ya no se usa %type Void. 
   JFlex ahora entiende que el método yylex() devolverá un objeto 'Symbol'.
*/

%%

/* Reglas */

// Los nombres como 'sym.PALABRA' deben existir en tu archivo .cup
"hola"      { return new Symbol(sym.PALABRA, yyline, yycolumn, yytext()); }
[0-9]+      { return new Symbol(sym.NUMERO, yyline, yycolumn, yytext()); }

[ \t\r\n]+  { /* Ignorar espacios */ }

.           { System.out.println("Error léxico: " + yytext() + " en línea " + (yyline+1)); }