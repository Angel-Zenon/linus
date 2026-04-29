/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.linus;
import java.util.List;
/**
 *
 * @author angelzenon
 */
public class Linus {

    public static void main(String[] args) {

        // String codigo = "Gato nombre -> \"Ángel \";\n" +
        //             "Gato apellido -> \"Zenón\";\n" +
        //             "Gato completo -> nombre + apellido;";

        String codigo = "perro x -> 2 + 3 * 4;";
        try {
            System.out.println("FASE 1  IDENTIFICANDO Y  CLASIFICANDO LOS TOKENS");
            List<Token> tokens = LexerSimulado.analizarTokens(codigo);
            tokens.forEach(System.out::println);
            System.out.println("FASE 2 Analizando cada token");
            LinusParser parser = new LinusParser(tokens);
            parser.analizar();
        } catch (Exception e) { System.err.println(e.getLocalizedMessage());}

        
    }
}
