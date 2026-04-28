/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.linus;
import java.util.ArrayList;
import java.util.List;
 // Importamos las expresiones regulares
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LexerSimulado {
    
    public static  List<Token> analizarTokens(String codigo) {
        // esta funcion recibe una cadena de texto, la cual es el codigó del lenguaje linus
        // crea una lista de tokens e inicializamos la linea en 1
        List<Token> tokens = new ArrayList<>();
        int numLinea = 1;
        
        // Definicion de las esprecuiones regulares para analizar los tokens
        // ejemplo: perro resultado -> 10 ;
        String regexTipoDato = "\\b(perro|Gato|pez)\\b"; // perro
        String regexIdentificador   = "\\b[a-z][a-zA-Z0-9]*\\b"; // ejemplo: resultado
        String regexAsignacion = "(->|>>)"; // ->
        String regexNum  = "\\b\\d+\\b"; // 10 
        String regexFin  = ";"; // ;
        //! queda pendiende lo de los comentarios
        
        // Se unen las expresiones regulaes en un solo patron
        // explicar en la documentacion del proyeto que es un patron (pattern) en regex de java
        Pattern pattern = Pattern.compile(
            "(?<TIPODATO>" + regexTipoDato + ")|" +
            "(?<ID>" + regexIdentificador + ")|" +
            "(?<ASIG>" + regexAsignacion + ")|" +
            "(?<NUM>" + regexNum + ")|" +
            "(?<FIN>" + regexFin + ")|" +
            "(?<SALTO>\\n)"
        ); 
        
        Matcher matcher = pattern.matcher(codigo);

        while (matcher.find()) {
            if (matcher.group("SALTO") != null) {
                numLinea++;
                continue;
            }
            // explicar esta parte del codigo 
            if (matcher.group("TIPODATO") != null) 
                tokens.add(new Token(TipoToken.PALABRA_RESERVADA, matcher.group(), numLinea));
            else if (matcher.group("ID") != null) 
                tokens.add(new Token(TipoToken.IDENTIFICADOR, matcher.group(), numLinea));
            else if (matcher.group("ASIG") != null) 
                tokens.add(new Token(TipoToken.OPERADOR, matcher.group(), numLinea));
            
            else if (matcher.group("NUM") != null) 
                tokens.add(new Token(TipoToken.CONSTANTE, matcher.group(), numLinea));
            else if (matcher.group("FIN") != null) 
                tokens.add(new Token(TipoToken.FIN_SENTENCIA, matcher.group(), numLinea));
        }
        
        return tokens;
    }
    
}
