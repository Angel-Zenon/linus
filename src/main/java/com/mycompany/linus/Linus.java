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
        String entrada = "perro valor -> 6;\nperro p1 -> 2;\np1;";
        /* 
        este programa genera una lista de tokens
        cada token contiene su tipo, y su lexema, ademas en la fila en la que se encuentra.
        
        mediante la clase LexerSimulado, ejecuta una funcion con las instrucciones del nuevo lenguaje
        */
        
        List<Token> lista = LexerSimulado.analizarTokens(entrada);
        lista.forEach(System.out::println);
    }
}
