package com.mycompany.linus;

/**
 * @author irvin
 */
public class Gato implements Operable {
    private String valor;

    // Constructor que valida la entrada desde el Parser
    public Gato(Object valorInput, int linea) throws LinusSemanticException {
        if (valorInput == null) {
            throw new LinusSemanticException("El tipo 'gato' (String) no puede recibir un valor nulo.", linea);
        }
        // En el caso de Gato, aceptamos casi cualquier cosa como texto, 
        // pero aseguramos que se guarde como String.
        this.valor = valorInput.toString();
    }

    // Constructor interno para resultados de operaciones
    private Gato(String valor) {
        this.valor = valor;
    }

    @Override
    public Operable sumar(Operable otro) throws Exception {
        // Validamos que el otro sea estrictamente un Gato para poder concatenar
        if (otro instanceof Gato) {
            return new Gato(this.valor + otro.getValor().toString());
        } else {
            throw new Exception("[Error Semántico] No se puede sumar (concatenar) gato con " + otro.getTipo());
        }
    }

    @Override
    public Operable restar(Operable otro) throws Exception {
        throw new Exception("[Error Semántico] El tipo gato (String) no soporta la operación de resta.");
    }

    @Override
    public Operable multiplicar(Operable otro) throws Exception {
        throw new Exception("[Error Semántico] El tipo gato (String) no soporta la operación de multiplicación.");
    }

    @Override
    public Operable dividir(Operable otro) throws Exception {
        throw new Exception("[Error Semántico] El tipo gato (String) no soporta la operación de división.");
    }

    @Override
    public Object getValor() { return this.valor; }

    @Override
    public String getTipo() { return "gato"; }
}