package com.mycompany.linus;
import java.util.HashMap;

public class TablaSimbolos {
    // Clase que gestiona las variables y su ciclo de vida
    private HashMap<String, Operable> tabla;

    public TablaSimbolos(){
        tabla = new HashMap<>();
    }

    // Esta tabla es capaz de guardar, actualizar y devolver una variable

    // Ejemplo insertamos un tipo Perro con nombre de morita
    // perro morita = 10;
    public void insertar(String identificador, Operable valor) {
        tabla.put(identificador, valor);
    }

    public Operable obtener(String identificador) {
        
        return tabla.get(identificador);
    }

    public boolean existe(String identificador) {
        return tabla.containsKey(identificador);
    }

    public void imprimirMemoria() {
        System.out.println("\n--- ESTADO DE LA MEMORIA ---");
        if (tabla.isEmpty()) System.out.println("Memoria vacía.");
        tabla.forEach((id, obj) -> {
            System.out.println(id + " => [" + obj.getTipo() + "] Valor: " + obj.getValor());
        });
        System.out.println("----------------------------\n");
    }
}
