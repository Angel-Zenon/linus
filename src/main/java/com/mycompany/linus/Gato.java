/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.linus;

/**
 *
 * @author irvin
 */
public class Gato implements Operable {
    private String valor;

    public Gato(String valor) {
        this.valor = valor;
    }

    @Override
    public Operable sumar(Operable otro) throws Exception {
        // Validamos que el otro sea estrictamente un Gato para poder concatenar
        if (otro instanceof Gato) {
            return new Gato(this.valor + otro.getValor().toString());
        } else {
            throw new Exception("[Error Semántico] No se puede sumar (concatenar) Gato(String) con " + otro.getTipo());
        }
    }

    @Override
    public Operable restar(Operable otro) throws Exception {
        throw new Exception("[Error Semantico] No se puede restar en el tipo Gato (String).");
    }

    @Override
    public Operable multiplicar(Operable otro) throws Exception {
        throw new Exception("[Error Semantico] No se puede multiplicar en el tipo Gato (String).");
    }

    @Override
    public Operable dividir(Operable otro) throws Exception {
        throw new Exception("[Error Semantico] No se puede dividir en el tipo Gato (String).");
    }

    @Override
    public Object getValor() { return this.valor; }

    @Override
    public String getTipo() { return "Gato (String)"; }
}