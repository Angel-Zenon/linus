# Lenguaje-Linus

> Analizador lexico y sintactico para la implementacion de un lenguaje de programacion personalizado.

---

## Descripcion del Proyecto

**Linus** es un lenguaje de programacion disenado e implementado por nuestro equipo como proyecto academico. El objetivo principal es construir un compilador funcional que incluya analisis lexico, sintactico y semantico, utilizando herramientas profesionales como **JFlex** y **Java CUP**.

El lenguaje utiliza una sintaxis unica y creativa, donde los tipos de datos tradicionales son reemplazados por nombres de animales, haciendo el aprendizaje mas amigable y divertido.

---

## Tecnologias Utilizadas

| Tecnologia | Descripcion |
|------------|-------------|
| **Java 23** | Lenguaje base del compilador |
| **JFlex** | Generador de analizadores lexicos |
| **Java CUP** | Generador de parsers LALR |
| **Maven** | Gestor de dependencias y construccion |

---

## Diccionario de Tokens

Nuestro lenguaje reemplaza las palabras reservadas tradicionales por terminos mas creativos:

| Tipo Original | Palabra en Linus | Descripcion |
|---------------|------------------|-------------|
| `int` | `perro` | Tipo de dato entero |
| `String` | `gato` | Tipo de dato cadena de texto |
| `double` | `pez` | Tipo de dato decimal |
| `//` | `--` | Comentario de linea |
| `=` | `->` | Operador de asignacion |
| `==` | `>>` | Operador de igualdad |
| `+`, `-`, `*`, `/` | `+`, `-`, `*`, `/` | Operadores aritmeticos |


## Ejemplo de un proceso de Linus

1- *LexerSimulado*: Lee el texto y te da una lista de tokens.

2- *LinusParser*: Recorre los tokens. Cuando encuentra perro ID ->, sabe que debe prepararse para recibir valores.

3- *Acción Semántica* ( clases PERRO, GATO, ETC): En cuanto el Parser ve el 5, hace new Perro(5). Cuando ve el +, guarda la intención. Cuando ve el 10, hace new Perro(10). Finalmente, ejecuta el .sumar().
