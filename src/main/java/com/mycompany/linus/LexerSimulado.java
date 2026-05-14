package com.mycompany.linus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexerSimulado {

    public static List<Token> analizarTokens(String codigo) {
        List<Token> tokens = new ArrayList<>();
        int numLinea = 1;

        // 1. Definición de las expresiones regulares
        // Agregamos regexComentario: busca -- y todo lo que sigue hasta el final de la línea
        String regexComentario = "--.*"; 
        String regexTipoDato = "\\b(perro|gato|pez)\\b"; 
        String regexIdentificador = "\\b[a-z][a-zA-Z0-9]*\\b"; 
        String regexCadena = "\"[^\"]*\"";
        String regexAsignacion = "(->|>>)";
        String regexNum = "\\d+(\\.\\d+)?";
        String regexOp = "[\\+\\-\\*/]";
        String regexFin = ";";

        // 2. Unión de patrones (El comentario va PRIMERO para evitar conflictos con el operador '-')
        Pattern pattern = Pattern.compile(
            "(?<COMMENT>" + regexComentario + ")|" +
            "(?<TIPODATO>" + regexTipoDato + ")|" +
            "(?<ID>" + regexIdentificador + ")|" +
            "(?<ASIG>" + regexAsignacion + ")|" +
            "(?<STR>" + regexCadena + ")|" +
            "(?<NUM>" + regexNum + ")|" +
            "(?<OP>" + regexOp + ")|" +
            "(?<FIN>" + regexFin + ")|" +
            "(?<SALTO>\\n)"
        );

        Matcher matcher = pattern.matcher(codigo);

        while (matcher.find()) {
            // Manejo de Salto de Línea
            if (matcher.group("SALTO") != null) {
                numLinea++;
                continue;
            }

            // Manejo de Comentarios: Los detectamos pero NO agregamos nada a la lista de tokens
            if (matcher.group("COMMENT") != null) {
                // Al usar 'continue', saltamos al siguiente hallazgo sin crear un Token
                continue; 
            }

            // Clasificación de Tokens Reales
            if (matcher.group("TIPODATO") != null)
                tokens.add(new Token(TipoToken.PALABRA_RESERVADA, matcher.group(), numLinea));
            else if (matcher.group("ID") != null)
                tokens.add(new Token(TipoToken.IDENTIFICADOR, matcher.group(), numLinea));
            else if (matcher.group("ASIG") != null)
                tokens.add(new Token(TipoToken.OPERADOR, matcher.group(), numLinea));
            else if (matcher.group("STR") != null)
                tokens.add(new Token(TipoToken.CONSTANTE, matcher.group(), numLinea));
            else if (matcher.group("NUM") != null)
                tokens.add(new Token(TipoToken.CONSTANTE, matcher.group(), numLinea));
            else if (matcher.group("OP") != null)
                tokens.add(new Token(TipoToken.OPERADOR, matcher.group(), numLinea));
            else if (matcher.group("FIN") != null)
                tokens.add(new Token(TipoToken.FIN_SENTENCIA, matcher.group(), numLinea));
            else 
                tokens.add(new Token(TipoToken.ERROR, matcher.group(), numLinea));
        }

        return tokens;
    }
}
