package com.mycompany.linus;

public class Test {
    public static void main(String[] args) {
        try {
            TablaSimbolos memoria = new TablaSimbolos();

            // SIMULANDO: perro x -> 10;
            System.out.println("Ejecutando: perro morita -> 10;");
            memoria.insertar("morita", new Perro(10));

            // SIMULANDO: perro y -> x + 5;
            System.out.println("Ejecutando: perro gua -> morita + 5;");
            
            // 1. Buscamos 'x' en la memoria
            Operable varX = memoria.obtener("morita");
            
            if (varX == null) throw new Exception("Error: Variable 'morita' no declarada.");

            // 2. Realizamos la operación
            Operable cinco = new Perro(5);
            Operable resultadoY = varX.sumar(cinco);

            // 3. Guardamos 'y'
            memoria.insertar("y", resultadoY);

            // Verificamos el estado final
            memoria.imprimirMemoria();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
