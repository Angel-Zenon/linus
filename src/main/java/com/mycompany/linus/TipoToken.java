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
    PALABRA_RESERVADA, // perro, gato, pez
    IDENTIFICADOR,    // nombres de variables
    OPERADOR,         // -> , >> , + , -
    CONSTANTE,        // 6, "hola"
    FIN_SENTENCIA,    // ;
    COMENTARIO, // --
    ERROR             // cualquier cosa que no reconozcamos
}
