package com.mycompany.linus;

import java.util.List;

/**
 * Clase de Pruebas Integrales para el Lenguaje Linus.
 * Objetivo: Validar las fases de Lexer, Parser, Semántica y Gestión de Memoria.
 */
public class TestVariables {
    public static void main(String[] args) {
        System.out.println("===========================================================");
        System.out.println("   LINUS COMPILER - PROTOCOLO DE PRUEBAS INTEGRALES        ");
        System.out.println("===========================================================\n");

        // CÓDIGO FUENTE DE PRUEBA: Incluye comentarios, espacios y jerarquía matemática
        String codigoFuente = 
            "-- 1. Declaración y tipos de datos\n" +
            "perro edad -> 20;\n" +
            "pez pi -> 3.1416;\n" +
            "gato mensaje -> \"Compilador Linus\";\n" +
            "\n" +
            "-- 2. Prueba de jerarquía de operaciones (Multiplicación antes que Suma)\n" +
            "perro calculo -> 5 + 10 * 2;\n" +
            "\n" +
            "-- 3. Prueba de concatenación (Solo Gatos)\n" +
            "gato despedida -> \"Adios \" + \"Mundo\"; -- Comentario final";

        try {
            // --- FASE LÉXICA ---
            System.out.println(">>> FASE 1: ANÁLISIS LÉXICO (Extrayendo Tokens)");
            List<Token> tokens = LexerSimulado.analizarTokens(codigoFuente);
            System.out.println("Se generaron " + tokens.size() + " tokens (Comentarios ignorados con éxito).");
            System.out.println("-----------------------------------------------------------");

            // --- FASE SINTÁCTICA Y SEMÁNTICA ---
            System.out.println(">>> FASE 2: ANÁLISIS SINTÁCTICO Y SEMÁNTICO (Parser)");
            LinusParser parser = new LinusParser(tokens);
            
            // Este método recorre las reglas gramaticales y llena la Tabla de Símbolos
            parser.analizar(); 
            System.out.println("-----------------------------------------------------------");

            // --- FASE DE ROBUSTEZ (Pruebas de Fallo) ---
            System.out.println(">>> FASE 3: PRUEBAS DE RESILIENCIA (Manejo de Errores)");
            probarError("pez error -> \"No soy un decimal\";", "Error de tipo (String en Double)");
            probarError("perro a -> 10 + \"texto\";", "Error de operación (Suma incompatible)");

        } catch (Exception e) {
            System.err.println("CRITICAL_FAILURE: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para demostrar que el compilador detecta errores correctamente.
     */
    private static void probarError(String codigoErroneo, String descripcion) {
        System.out.print("Simulando " + descripcion + "... ");
        try {
            List<Token> t = LexerSimulado.analizarTokens(codigoErroneo);
            LinusParser p = new LinusParser(t);
            p.analizar();
        } catch (Exception e) {
            System.out.println("[OK] Capturado: " + e.getMessage());
        }
    }
}