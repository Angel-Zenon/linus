package com.mycompany.linus;

/**
 * @author irvin
 */
public class Pez implements Operable {
    private double valor;
    private static final int MAX_DECIMALES = 7;

    // Constructor con validación desde el Parser
    public Pez(Object valorInput, int linea) throws LinusSemanticException {
        try {
            String strValor = valorInput.toString();
            
            //Validar límite de decimales
            String[] partes = strValor.split("\\.");
            if (partes.length == 2 && partes[1].length() > MAX_DECIMALES) {
                throw new LinusSemanticException(
                    "El decimal '" + strValor + "' excede el límite de " + MAX_DECIMALES + " decimales.", 
                    linea
                );
            }
            
            this.valor = Double.parseDouble(strValor);
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