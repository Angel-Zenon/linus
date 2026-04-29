package com.mycompany.linus;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author irvin
 */
public class LinusSemanticException extends Exception {
    private int linea;

    public LinusSemanticException(String mensaje, int linea) {
        super("[Error Semántico] " + mensaje + " (Línea: " + linea + ")");
        this.linea = linea;
    }

    public int getLinea() { return linea; }
}