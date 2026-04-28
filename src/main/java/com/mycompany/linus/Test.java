package com.mycompany.linus;

public class Test {
    public static void main(String[] args) {
        // Prueba unitaria de la clase Perro
        try {
            System.out.println("--- Simulando: perro x -> 5 + 10; ---");

            // 1. El Parser detecta el número 5 y el 10
            Operable p1 = new Perro(5);
            Operable p2 = new Perro(10);

            // 2. El Parser detecta el operador '+' y llama al método del primer operando
            Operable resultadoSuma = p1.sumar(null);

            // 3. Imprimimos para ver si el polimorfismo funcionó
            System.out.println("Resultado: " + resultadoSuma.getValor());
            System.out.println("Tipo resultante: " + resultadoSuma.getTipo());
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
