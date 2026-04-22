package com.mycompany.linus;
import java_cup.runtime.Symbol;

%% // SECCIÓN 1: Configuraciones
%class Lexer
%type java_cup.runtime.Symbol
%cup
%full
%line
%column
%char
%public

%{
    // Aquí puedes agregar métodos auxiliares si los necesitas
%}

%% // SECCIÓN 2: Expresiones Regulares y Acciones

/* Espacios en blanco y comentarios */
[ \t\r\n]+          { /* Ignorar */ }
"//".* { /* Ignorar comentarios */ }
/* Palabras reservadas */
"perro" { return new Symbol(sym.PERRO, yyline, yycolumn, yytext());}
"gato"  { return new Symbol(sym.GATO, yyline, yycolumn, yytext()); }
"pez"   { return new Symbol(sym.PEZ, yyline, yycolumn, yytext()); }

/* Operadores */
"->"    { return new Symbol(sym.ASSIGN, (int) yychar, yyline, yytext()); }
">>"    { return new Symbol(sym.EQUALS, (int) yychar, yyline, yytext()); }

/* Identificadores y Números */
[a-zA-Z_][a-zA-Z0-9_]* { return new Symbol(sym.ID, (int) yychar, yyline, yytext()); }
[0-9]+                 { return new Symbol(sym.NUMBER, (int) yychar, yyline, yytext()); }

. { System.err.println("Error léxico: caracter ilegal <" + yytext() + "> en línea " + (yyline+1)); }