package com.mycompany.linus;


import java.util.List;

/**
 * Analizador Sintáctico y Semántico para el lenguaje "Linus".
 * Implementa la lógica de Descenso Recurrente simulando el comportamiento de CUP.
 */
public class LinusParser {
    private List<Token> tokens;
    private int indice = 0;
    private Token tokenActual;
    private TablaSimbolos tabla;

    public LinusParser(List<Token> tokens) {
        this.tokens = tokens;
        this.tabla = new TablaSimbolos();
        if (!tokens.isEmpty()) {
            tokenActual = tokens.get(0);
        }
    }

    // --- MÉTODOS DE UTILIDAD ---

    private void avanzar() {
        indice++;
        if (indice < tokens.size()) {
            tokenActual = tokens.get(indice);
        } else {
            tokenActual = null;
        }
    }

    private void consumir(TipoToken tipoEsperado) throws Exception {
        if (tokenActual != null && tokenActual.getTipo() == tipoEsperado) {
            avanzar();
        } else {
            String encontrado = (tokenActual != null) ? tokenActual.getLexema() : "FIN DE ARCHIVO";
            int linea = (tokenActual != null) ? tokenActual.getLinea() : (tokens.isEmpty() ? 0 : tokens.get(tokens.size() - 1).getLinea());
            throw new Exception("[Error Sintáctico] Fila " + linea + 
                               ": Se esperaba " + tipoEsperado + " pero se encontró '" + encontrado + "'");
        }
    }

    // --- LÓGICA PRINCIPAL ---

    public void analizar() {
        try {
            while (tokenActual != null) {
                sentencia();
            }
            System.out.println("--- Análisis y Ejecución Terminados con Éxito ---");
            tabla.imprimirMemoria();
        } catch (Exception e) {
            // Imprime el error en la consola de NetBeans
            System.err.println(e.getMessage());
        }
    }

    /**
     * REGLA: sentencia ::= TIPO_DATO ID ASIGNACION expresion FIN_SENTENCIA
     */
    private void sentencia() throws Exception {
        if (tokenActual == null) return;

        if (tokenActual.getTipo() == TipoToken.PALABRA_RESERVADA) {
            String tipoDato = tokenActual.getLexema(); // perro, gato o pez
            consumir(TipoToken.PALABRA_RESERVADA);
            
            String nombreVar = tokenActual.getLexema();
            consumir(TipoToken.IDENTIFICADOR);
            
            consumir(TipoToken.OPERADOR); // Consume "->" o ">>"
            
            // Resolvemos la expresión (puede ser un valor simple o una suma)
            Operable resultado = expresion();
            
            // VALIDACIÓN SEMÁNTICA: ¿El objeto coincide con el tipo de la variable?
            validarTipo(tipoDato, resultado);
            
            // Guardamos en la memoria (Tabla de Símbolos)
            tabla.insertar(nombreVar, resultado);
            
            consumir(TipoToken.FIN_SENTENCIA); // Consume ";"
        } else {
            throw new Exception("[Error Sintáctico] Fila " + tokenActual.getLinea() + 
                               ": La instrucción debe iniciar con una declaración (perro/gato/pez). Encontrado: '" + tokenActual.getLexema() + "'");
        }
    }

    /**
     * REGLA: expresion ::= factor ( OPERADOR_SUMA factor )*
     */
    private Operable expresion() throws Exception {
        // Primero resolvemos lo que tenga mayor prioridad (términos)
        Operable izq = termino(); 
        
        while (tokenActual != null && (tokenActual.getLexema().equals("+") || tokenActual.getLexema().equals("-"))) {
            String operador = tokenActual.getLexema();
            consumir(TipoToken.OPERADOR);
            
            validarOperandoFaltante(); // Blindaje de seguridad
            
            Operable der = termino(); // Resolvemos el siguiente bloque
            
            // El Switch para decidir la operación
            switch (operador) {
                case "+": izq = izq.sumar(der); break;
                case "-": izq = izq.restar(der); break;
            }
        }
        return izq;
    }

    private Operable termino() throws Exception {
        Operable izq = factor(); // Llegamos a la base (número o variable)
        
        while (tokenActual != null && (tokenActual.getLexema().equals("*") || tokenActual.getLexema().equals("/"))) {
            String operador = tokenActual.getLexema();
            consumir(TipoToken.OPERADOR);
            
            validarOperandoFaltante();
            
            Operable der = factor();
            
            switch (operador) {
                case "*": izq = izq.multiplicar(der); break;
                case "/": izq = izq.dividir(der); break;
            }
        }
        return izq;
    }

    private void validarOperandoFaltante() throws Exception {
    if (tokenActual == null || tokenActual.getTipo() == TipoToken.FIN_SENTENCIA) {
        throw new Exception("[Error Sintáctico] Falta un operando después del operador.");
    }
}

    /**
     * REGLA: factor ::= NUMERO | STRING | IDENTIFICADOR
     */
    private Operable factor() throws Exception {
        if (tokenActual == null) {
            throw new Exception("[Error Sintáctico] Se esperaba un valor o variable.");
        }

        Token t = tokenActual;
        // Si lo que sigue es un punto y coma o un operador sin haber dado un valor
    

        // CASO 1: Es un valor constante (Número o Texto entre comillas)
        if (t.getTipo() == TipoToken.CONSTANTE) {
            String lexema = t.getLexema();
            avanzar();
            
            if (lexema.startsWith("\"")) {
                return new Gato(lexema.replace("\"", ""));
            } else if (lexema.contains(".")) {
                return new Pez(Double.parseDouble(lexema));
            } else {
                return new Perro(Integer.parseInt(lexema));
            }
        } 
        // CASO 2: Es una variable que ya existe en memoria
        else if (t.getTipo() == TipoToken.IDENTIFICADOR) {
            String nombre = t.getLexema();
            if (!tabla.existe(nombre)) {
                throw new Exception("[Error Semántico] Fila " + t.getLinea() + 
                                   ": La variable '" + nombre + "' no ha sido declarada.");
            }
            avanzar();
            return tabla.obtener(nombre);
        }

        throw new Exception("[Error] Fila " + t.getLinea() + 
                           ": Se esperaba un valor (número/texto) o variable, pero se encontró '" + t.getLexema() + "'");
    }

    /**
     * Verifica que el objeto generado sea compatible con la palabra reservada usada.
     */
    private void validarTipo(String tipoDeclarado, Operable objeto) throws Exception {
        String tipoObjeto = objeto.getTipo(); 
        
        // Comparamos si el tipo del objeto contiene la palabra reservada (ej: "Perro (int)" contiene "perro")
        if (!tipoObjeto.contains(tipoDeclarado)) {
            throw new Exception("[Error de Tipos] Fila " + (tokenActual != null ? tokenActual.getLinea() : "actual") + 
                               ": No se puede asignar un " + objeto.getTipo() + 
                               " a una variable declarada como '" + tipoDeclarado + "'");
        }
    }
}