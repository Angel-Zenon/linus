# Lenguaje-Linus
Analizador léxico y sintáctico para la implementación de un lenguaje de programación

# Diccionario de tokens
| Clase original   | Clase en linus |
| -------------    | ------------- |
| Entero (int)     | perro  |
| Cadena (String)  | Gato  |
| Decimal (double) | pez  |
| // (comentario)  | -- |
| = | ->|
| == | >> |
| Operadores | +, - , * , / |

## Ejemplo de un proceso de Linus

1- *LexerSimulado*: Lee el texto y te da una lista de tokens.

2- *LinusParser*: Recorre los tokens. Cuando encuentra perro ID ->, sabe que debe prepararse para recibir valores.

3- *Acción Semántica* ( clases PERRO, GATO, ETC): En cuanto el Parser ve el 5, hace new Perro(5). Cuando ve el +, guarda la intención. Cuando ve el 10, hace new Perro(10). Finalmente, ejecuta el .sumar().