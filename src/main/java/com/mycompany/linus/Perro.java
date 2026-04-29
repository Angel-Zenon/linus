package com.mycompany.linus;

public class Perro implements Operable{
    private int valor;

    public Perro(int valor) {this.valor = valor;}

    @Override
    public Operable sumar(Operable otro) throws Exception {
        if (otro instanceof Perro) { // Si el otro es una instancia de esta misma clase
            return new Perro(this.valor + (int) otro.getValor());
        } else {
            throw new Exception("[Error Semántico] No se puede sumar Perro(int) con" + otro.getTipo());
        }
    }

    // Implementamos los demas metodos 
    @Override 
    public Operable restar(Operable otro) throws Exception {
        if (otro instanceof Perro) {
            return new Perro(this.valor - (int) otro.getValor());
        } else throw new Exception("[Error Semántico] No se puede restar Perro(int) con" + otro.getTipo());
    }

    @Override 
    public Operable multiplicar(Operable otro) throws Exception {
        if (otro instanceof Perro) {
            return new Perro(this.valor * (int) otro.getValor());
        } else throw new Exception("[Error Semántico] No se puede multiplicar Perro(int) con" + otro.getTipo());
    }

    @Override 
    public Operable dividir(Operable otro) throws Exception {
        if (otro instanceof Perro) {
            return new Perro(this.valor / (int) otro.getValor());
        } else throw new Exception("[Error Semántico] No se puede dividir Perro(int) con" + otro.getTipo());
    }

    @Override 
    public String getTipo() {return "perro";}

    @Override
    public Object getValor() {return this.valor;}
}
