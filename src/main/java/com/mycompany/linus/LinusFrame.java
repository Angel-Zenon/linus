package com.mycompany.linus;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class LinusFrame extends JFrame {
    
    private JTextArea txtEditor;
    private JButton btnEjecutar;
    private JButton btnLimpiar;
    private JTable tblTokens;
    private DefaultTableModel modeloTabla;
    private JButton btnVerDiccionario;
    private JTextArea txtDiccionario;
    private JTextField txtTipoDatoDetectado;
    private JTextArea txtExcepciones;
    private JTextArea txtResultados;

    public LinusFrame() {
        super("Editor de codigo Linus");
        configurarVentana();
        inicializarComponentes();
        redirigirConsolas();
    }

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        JPanel pnlToolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnEjecutar = new JButton("Ejecutar");
        btnLimpiar = new JButton("Limpiar todo");
        btnVerDiccionario = new JButton("Ver diccionario");
        
        pnlToolbar.add(btnEjecutar);
        pnlToolbar.add(btnLimpiar);
        pnlToolbar.add(btnVerDiccionario);
        add(pnlToolbar, BorderLayout.NORTH);

        txtEditor = new JTextArea();
        txtEditor.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scrollEditor = new JScrollPane(txtEditor);
        scrollEditor.setBorder(BorderFactory.createTitledBorder("Editor"));

        String[] columnas = {"Token", "Lexema", "Patrón", "Reservada"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tblTokens = new JTable(modeloTabla);
        JScrollPane scrollTokens = new JScrollPane(tblTokens);
        scrollTokens.setBorder(BorderFactory.createTitledBorder("Tabla de Tokens"));

        txtDiccionario = new JTextArea();
        txtDiccionario.setEditable(false);
        txtDiccionario.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane scrollDiccionario = new JScrollPane(txtDiccionario);
        scrollDiccionario.setBorder(BorderFactory.createTitledBorder("Diccionario"));

        JPanel pnlTipoDato = new JPanel(new BorderLayout());
        pnlTipoDato.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlTipoDato.add(new JLabel("Tipo de dato detectado: "), BorderLayout.WEST);
        txtTipoDatoDetectado = new JTextField();
        txtTipoDatoDetectado.setEditable(false);
        pnlTipoDato.add(txtTipoDatoDetectado, BorderLayout.CENTER);

        JPanel pnlDiccionarioContainer = new JPanel(new BorderLayout());
        pnlDiccionarioContainer.add(scrollDiccionario, BorderLayout.CENTER);
        pnlDiccionarioContainer.add(pnlTipoDato, BorderLayout.SOUTH);

        JSplitPane splitRightTop = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollTokens, pnlDiccionarioContainer);
        splitRightTop.setResizeWeight(0.5);

        JSplitPane splitTop = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollEditor, splitRightTop);
        splitTop.setResizeWeight(0.5);

        txtExcepciones = new JTextArea();
        txtExcepciones.setEditable(false);
        txtExcepciones.setForeground(Color.RED);
        txtExcepciones.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane scrollExcepciones = new JScrollPane(txtExcepciones);
        scrollExcepciones.setBorder(BorderFactory.createTitledBorder("Excepciones"));

        txtResultados = new JTextArea();
        txtResultados.setEditable(false);
        txtResultados.setForeground(new Color(0, 102, 0));
        txtResultados.setFont(new Font("Consolas", Font.PLAIN, 12));
        JScrollPane scrollResultados = new JScrollPane(txtResultados);
        scrollResultados.setBorder(BorderFactory.createTitledBorder("Resultados memoria"));

        JSplitPane splitBottom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollResultados, scrollExcepciones);
        splitBottom.setResizeWeight(0.5);

        JSplitPane splitMain = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitTop, splitBottom);
        splitMain.setResizeWeight(0.7);

        add(splitMain, BorderLayout.CENTER);

        btnEjecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAnalisis();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtEditor.setText("");
                txtExcepciones.setText("");
                txtResultados.setText("");
                txtDiccionario.setText("");
                txtTipoDatoDetectado.setText("");
                modeloTabla.setRowCount(0);
            }
        });

        btnVerDiccionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDiccionario();
            }
        });
    }

    private void ejecutarAnalisis() {
        txtExcepciones.setText("");
        txtResultados.setText("");
        txtTipoDatoDetectado.setText("");
        modeloTabla.setRowCount(0);

        String texto = txtEditor.getText();
        if (texto.trim().isEmpty()) return;

        try {
            // CONEXIÓN AL CÓDIGO BASE: Análisis de tokens a través del LexerSimulado
            List<Token> listaTokens = LexerSimulado.analizarTokens(texto);

            for (Token t : listaTokens) {
                String tipoStr = t.getTipo().name();
                String lexema = t.getLexema();
                String patron = obtenerPatron(t.getTipo());
                String reservada = (t.getTipo() == TipoToken.PALABRA_RESERVADA || 
                                    t.getTipo() == TipoToken.OPERADOR || 
                                    t.getTipo() == TipoToken.FIN_SENTENCIA) ? "Sí" : "No";
                modeloTabla.addRow(new Object[]{tipoStr, lexema, patron, reservada});
            }

            // CONEXIÓN AL CÓDIGO BASE: Instanciación del Parser con los tokens generados
            LinusParser parser = new LinusParser(listaTokens);
            
            // CONEXIÓN AL CÓDIGO BASE: Ejecución del análisis sintáctico y semántico
            parser.analizar();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void mostrarDiccionario() {
        String dic = "Diccionario del Lenguaje Linus:\n\n" +
                "PALABRA_RESERVADA : \\b(perro|Gato|pez)\\b\n" +
                "IDENTIFICADOR     : \\b[a-z][a-zA-Z0-9]*\\b\n" +
                "ASIGNACION        : (->|>>)\n" +
                "CADENA            : \"[^\"]*\"\n" +
                "NUMERO            : \\d+(\\.\\d+)?\n" +
                "OPERADOR          : [\\+\\-\\*/]\n" +
                "FIN_SENTENCIA     : ;\n" +
                "COMENTARIO        : --\n";
        txtDiccionario.setText(dic);
    }

    private String obtenerPatron(TipoToken tipo) {
        switch (tipo) {
            case PALABRA_RESERVADA: return "\\b(perro|Gato|pez)\\b";
            case IDENTIFICADOR: return "\\b[a-z][a-zA-Z0-9]*\\b";
            case OPERADOR: return "(->|>>)|[\\+\\-\\*/]";
            case CONSTANTE: return "\"[^\"]*\" | \\d+(\\.\\d+)?";
            case FIN_SENTENCIA: return ";";
            case COMENTARIO: return "--";
            default: return "N/A";
        }
    }

    private void redirigirConsolas() {
        System.setOut(new PrintStream(new CustomOutputStream(txtResultados, false)));
        System.setErr(new PrintStream(new CustomOutputStream(txtExcepciones, true)));
    }

    class CustomOutputStream extends OutputStream {
        private JTextArea textArea;
        private boolean isError;
        private StringBuilder buffer = new StringBuilder();

        public CustomOutputStream(JTextArea textArea, boolean isError) {
            this.textArea = textArea;
            this.isError = isError;
        }

        @Override
        public void write(int b) {
            textArea.append(String.valueOf((char) b));
            textArea.setCaretPosition(textArea.getDocument().getLength());

            if (!isError) {
                if (b == '\n') {
                    verificarLineaParaTipoDato(buffer.toString());
                    buffer.setLength(0);
                } else {
                    buffer.append((char) b);
                }
            }
        }

        @Override
        public void write(byte[] b, int off, int len) {
            String text = new String(b, off, len);
            SwingUtilities.invokeLater(() -> {
                textArea.append(text);
                textArea.setCaretPosition(textArea.getDocument().getLength());
            });
            
            if (!isError) {
                for (int i = 0; i < len; i++) {
                    char c = (char) b[off + i];
                    if (c == '\n') {
                        verificarLineaParaTipoDato(buffer.toString());
                        buffer.setLength(0);
                    } else {
                        buffer.append(c);
                    }
                }
            }
        }

        private void verificarLineaParaTipoDato(String linea) {
            int idx = linea.indexOf("=> [");
            if (idx != -1) {
                String varName = linea.substring(0, idx).trim();
                int endIdx = linea.indexOf("]", idx);
                if (endIdx != -1) {
                    String tipo = linea.substring(idx + 4, endIdx).trim();
                    if (tipo.contains("(")) {
                        tipo = tipo.substring(0, tipo.indexOf("(")).trim();
                    }
                    final String tipoFinal = tipo;
                    SwingUtilities.invokeLater(() -> {
                        txtTipoDatoDetectado.setText("Variable '" + varName + "' declarada como " + tipoFinal);
                    });
                }
            }
        }
    }
}
