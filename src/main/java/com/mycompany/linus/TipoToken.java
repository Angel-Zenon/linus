/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.linus;

/**
 *
 * @author angelzenon
 */
public enum TipoToken {
    // OPERADOR ES UNA PALABRA RESERVADA, 
    // 
    TIPO_DATO, // perro, gato, pez
    PALABRA_RESERVADA, 
    IDENTIFICADOR,    // nombres de variables
    OPERADOR,         // -> , >> , + , - TAMBIEN ES PALABRA RESERVADA
    CONSTANTE,        // 6, "hola"
    FIN_SENTENCIA,    // ; ES PALABRA RESERVADA TAMBIEN
    COMENTARIO, // --
    ERROR             // cualquier cosa que no reconozcamos
}
