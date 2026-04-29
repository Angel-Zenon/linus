package com.mycompany.linus;

/**
 * * @author irvin
 */
public class Pez implements Operable {
    private double valor;

    // Constructor corregido para validar la entrada y reportar la línea del error
    public Pez(Object valorInput, int linea) throws LinusSemanticException {
        try {
            // Intentamos convertir lo que reciba el Parser a un double real
            this.valor = Double.parseDouble(valorInput.toString());
        } catch (NumberFormatException | NullPointerException e) {
            throw new LinusSemanticException("El tipo 'pez' (double) no puede recibir el valor: " + valorInput, linea);
        }
    }

    // Constructor interno para resultados de operaciones (ya validados)
    private Pez(double valor) {
        this.valor = valor;
    }

    @Override
    public Operable sumar(Operable otro) throws Exception {
        if (otro instanceof Pez) {
            return new Pez(this.valor + (double) otro.getValor());
        }
        throw new Exception("[Error Semántico] pez (double) requiere otro pez para sumar, no un " + otro.getTipo());
    }

    @Override
    public Operable restar(Operable otro) throws Exception {
        if (otro instanceof Pez) {
            return new Pez(this.valor - (double) otro.getValor());
        }
        throw new Exception("[Error Semántico] pez (double) requiere otro pez para restar.");
    }

    @Override
    public Operable multiplicar(Operable otro) throws Exception {
        if (otro instanceof Pez) {
            return new Pez(this.valor * (double) otro.getValor());
        }
        throw new Exception("[Error Semántico] pez (double) requiere otro pez para multiplicar.");
    }

    @Override
    public Operable dividir(Operable otro) throws Exception {
        if (otro instanceof Pez) {
            double divisor = (double) otro.getValor();
            if (divisor == 0) throw new Exception("[Error Semántico] División por cero detectada.");
            return new Pez(this.valor / divisor);
        }
        throw new Exception("[Error Semántico] pez (double) requiere otro pez para dividir.");
    }

    @Override
    public Object getValor() { return this.valor; }

    @Override
    public String getTipo() { return "pez"; }
}