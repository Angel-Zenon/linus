/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.linus;

/**
 *
 * @author irvin
 */
public class Pez implements Operable {
    private double valor;

    public Pez(double valor) {
        this.valor = valor;
    }

    @Override
    public Operable sumar(Operable otro) throws Exception {
        if (otro instanceof Pez) {
            return new Pez(this.valor + (double) otro.getValor());
        }
        throw new Exception("[Error Semantico] Pez (double) requiere otro Pez para sumar.");
    }

    @Override
    public Operable restar(Operable otro) throws Exception {
        if (otro instanceof Pez) {
            return new Pez(this.valor - (double) otro.getValor());
        }
        throw new Exception("[Error Semantico] Pez (double) requiere otro Pez para restar.");
    }

    @Override
    public Operable multiplicar(Operable otro) throws Exception {
        if (otro instanceof Pez) {
            return new Pez(this.valor * (double) otro.getValor());
        }
        throw new Exception("[Error Semantico] Pez (double) requiere otro Pez para multiplicar.");
    }

    @Override
    public Operable dividir(Operable otro) throws Exception {
        if (otro instanceof Pez) {
            if ((double) otro.getValor() == 0) throw new Exception("[Error Semántico] División por cero.");
            return new Pez(this.valor / (double) otro.getValor());
        }
        throw new Exception("[Error Semantico] Pez (double) requiere otro Pez para dividir.");
    }

    @Override
    public Object getValor() { return this.valor; }

    @Override
    public String getTipo() { return "Pez (double)"; }
}