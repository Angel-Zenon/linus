package com.mycompany.linus;

public class TestVariables {
    public static void main(String[] args) {
        System.out.println("========== PRUEBAS DE ANALIZADOR SEMANTICO ==========\n");

        // --- PRUEBA 1: ÉXITO CON PEZ (double) ---
        try {
            System.out.println("Test 1: pez x -> 15.5 + 4.5;");
            Operable z1 = new Pez(15.5);
            Operable z2 = new Pez(4.5);
            Operable resPez = z1.sumar(z2); // Cambié null por z2
            System.out.println("Resultado: " + resPez.getValor() + " [" + resPez.getTipo() + "]\n");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // --- PRUEBA 2: ÉXITO CON GATO (String - Concatenación) ---
        try {
            System.out.println("Test 2: gato m -> \"Hola \" + \"Mundo\";");
            Operable g1 = new Gato("Hola ");
            Operable g2 = new Gato("Mundo");
            Operable resGato = g1.sumar(g2);
            System.out.println("Resultado: " + resGato.getValor() + " [" + resGato.getTipo() + "]\n");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // --- PRUEBA 3: ERROR SEMÁNTICO (Mezcla prohibida) ---
        try {
            System.out.println("Test 3: perro a -> 10 + pez(5.0);");
            Operable a = new Perro(10);
            Operable b = new Pez(5.0);
            a.sumar(b); // Esto DEBE disparar el catch
        } catch (Exception e) {
            System.out.println("Respuesta esperada -> " + e.getMessage() + "\n");
        }

        // --- PRUEBA 4: ERROR OPERACIÓN NO VÁLIDA (Gato no resta) ---
        try {
            System.out.println("Test 4: gato c -> \"Adios\" - \"Tristeza\";");
            Operable c = new Gato("Adios");
            Operable d = new Gato("Tristeza");
            c.restar(d); 
        } catch (Exception e) {
            System.out.println("Respuesta esperada -> " + e.getMessage() + "\n");
        }
    }
}