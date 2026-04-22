/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.linus;

/**
 *
 * @author angelzenon
 */
public class Token {
    private TipoToken tipo;
    private String lexema;
    private int linea;
    
    public Token(TipoToken tipo, String lexema, int linea) {
        this.tipo = tipo;
        this.lexema = lexema;
        this.linea = linea;
    }
    
    public TipoToken getTipo() { return this.tipo; }
    public String getLexema() { return this.lexema; }
    public int getLinea() { return this.linea; }
    
    
    @Override
    public String toString() {
        // De aqui sacaremos los valores de la tabla que pidió la profesora
        return String.format("Fila: %d | Tipo: %-15s | Lexema: %s", linea, tipo, lexema);    
    }
    
    
}
