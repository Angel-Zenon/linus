package com.mycompany.linus;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    /*! EN ESTA CLASE CAPTURAREMOS LOS ERRORES SEMANTICOS de nuestro lenguaje */
    List<Token> tokens = new ArrayList<>();
    private int indice = 0;
    private Token tokenActual;

    public Parser(List<Token> tokens ) {
        this.tokens = tokens;
        if (!tokens.isEmpty()) {
            // Si los tokens no estan vacios guardamos el primero coo el Actual
            tokenActual = tokens.get(0);
        }
    }

    // metodo que revisa el tipo de cada token
    public void consumirToken(TipoToken tipoEsperado)throws Exception {
        if(tokenActual != null && tokenActual.getTipo() == tipoEsperado) {
            avanzar();
        } else {
            throw new Exception("[Error Sintáctico] Fila " + (tokenActual != null ? tokenActual.getLinea() : "final") +        ": Se esperaba " + tipoEsperado + " pero se encontró " + (tokenActual != null ? tokenActual.getLexema() : "FIN"));
        }
    }

    private void avanzar() {
        indice ++;
        if (indice < tokens.size() ) tokenActual = tokens.get(indice);
        else tokenActual = null;
    }

    public void analizar() {
        try {
            while (tokenActual!=null) {
                sentencia();
            }
            System.out.println("ANALISIS SINTACTICO EXITOSO");
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    public void sentencia() throws Exception {
        // Si el primer elemento del codigo es una palaba reservada
        // se evaluan los tokens que le siguen
        if (tokenActual.getTipo() == TipoToken.PALABRA_RESERVADA) {
            consumirToken(TipoToken.PALABRA_RESERVADA); // consume perro/Gato/Pez
            consumirToken(TipoToken.IDENTIFICADOR); // consume valor/p1/var/constante
            consumirToken(TipoToken.OPERADOR); // consume ->;

            // ! AQUI VALIDAREMOS SI ES NUMERO O STRING, pero de mientras el orden
            consumirToken(TipoToken.CONSTANTE); // 8
            consumirToken(TipoToken.FIN_SENTENCIA); // ;
        } else {
            throw new Exception("[Error Sintáctico] Fila " + tokenActual.getLinea() +  ": No se permite una instrucción que empiece con '" + tokenActual.getLexema() + "'. Debe ser una declaración (perro/gato/pez).");
        }
    }

}
