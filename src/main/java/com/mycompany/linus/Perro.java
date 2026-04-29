package com.mycompany.linus;

/**
 * @author irvin
 */
public class Perro implements Operable {
    private int valor;

    // Nuevo constructor para validar la entrada desde el Parser
    public Perro(Object valorInput, int linea) throws LinusSemanticException {
        try {
            // Intentamos convertir el valor recibido a un entero (int)
            this.valor = Integer.parseInt(valorInput.toString());
        } catch (NumberFormatException | NullPointerException e) {
            throw new LinusSemanticException("El tipo 'perro' (int) no puede recibir el valor: " + valorInput, linea);
        }
    }

    // Constructor interno para resultados de operaciones
    private Perro(int valor) {
        this.valor = valor;
    }

    @Override
    public Operable sumar(Operable otro) throws Exception {
        if (otro instanceof Perro) {
            return new Perro(this.valor + (int) otro.getValor());
        } else {
            throw new Exception("[Error Semántico] No se puede sumar perro (int) con " + otro.getTipo());
        }
    }

    @Override
    public Operable restar(Operable otro) throws Exception {
        if (otro instanceof Perro) {
            return new Perro(this.valor - (int) otro.getValor());
        } else {
            throw new Exception("[Error Semántico] No se puede restar perro (int) con " + otro.getTipo());
        }
    }

    @Override
    public Operable multiplicar(Operable otro) throws Exception {
        if (otro instanceof Perro) {
            return new Perro(this.valor * (int) otro.getValor());
        } else {
            throw new Exception("[Error Semántico] No se puede multiplicar perro (int) con " + otro.getTipo());
        }
    }

    @Override
    public Operable dividir(Operable otro) throws Exception {
        if (otro instanceof Perro) {
            int divisor = (int) otro.getValor();
            if (divisor == 0) throw new Exception("[Error Semántico] División por cero.");
            return new Perro(this.valor / divisor);
        } else {
            throw new Exception("[Error Semántico] No se puede dividir perro (int) con " + otro.getTipo());
        }
    }

    @Override
    public String getTipo() {
        return "perro";
    }

    @Override
    public Object getValor() {
        return this.valor;
    }
}