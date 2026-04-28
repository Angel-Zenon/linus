
package com.mycompany.linus;

/**
 *
 * @author Zenón
 */
public interface Operable {
    /* Interfaz que se implementerá en las palabras reservadas (TIPOS DE DATOS) */
    // En esta interfaz definimos las operaciones basicas con los tipos de datos
    // del lenguaje

    Operable sumar(Operable otro) throws Exception; // metodo sumar que recibe otro operable, y lanza excepciones en caso de errores EJEMPLO:
    // 10 + "A"
    Operable restar(Operable otro) throws Exception;
    Operable multiplicar(Operable otro) throws Exception;
    Operable dividir(Operable otro) throws Exception;
    
    // SOLO EM STRINGS (Gato)
    //  Operable concatenar(Operable otro) throws Exception;

    Object getValor();

    String getTipo();
}
