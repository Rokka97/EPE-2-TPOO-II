import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Jframe con toda la ventana
public class InterfazGrafica extends JFrame implements ActionListener {

    // Importacion de clases
    Calculos calc = new Calculos();
    Empleado empleado = new Empleado();
    // Componentes de la Ventana
    private JTextField nombrefield, sueldoBrutoField, impuestoField, afpField, saludField, sueldoLiquidoField;
    private JTextPane resumenPane;
    private JButton calcularButton;
    // Elementos JCombobox
    private String[] stringComboBox = {"Seleccionar","Junior","Master","Senior"};  // String Con las Opciones
    private String seleccionBox;                                                   // String para guardar la seleccion
    private JComboBox<String> tipoEmpleadoBox;
    // Variables para poder obtener la posicion del mouse
    private int posX;
    private int posY;

    // Inicio del metodo Constructor
    public InterfazGrafica() {
        // Creacion de la ventana
        setTitle("Liquidación de sueldo");
        setSize(750, 570);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centra la ventana al abrir
        setResizable(false);          // Prohibe la cambiar al tamaño de la ventana
        setUndecorated(true);         // Quita la barra superior de la ventana

        // colores Personalizados
        Color colorBase = new Color(77, 115, 190);
        Color colorBoton = new Color(177, 206,148);

        // Tipografia
        Font font = new Font("Roboto", Font.PLAIN, 18);
        Font fontTitulo = new Font("Roboto", Font.PLAIN, 24);

        // Panel principal que sostiene los Jpane adicionales
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel del titulo
        JPanel tituloPanel = new JPanel();
        JLabel tituloLabel = new JLabel("<html><u><font color='white'>Liquidacion de sueldo</font></u></html>");
        tituloLabel.setFont(fontTitulo);
        tituloPanel.setBackground(colorBase);
        tituloPanel.add(tituloLabel);

        // Algoritmo para poder arrastrar la ventana con el tituloPanel
        tituloPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });
        tituloPanel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent evt) {
                int newX = evt.getXOnScreen() - posX;
                int newY = evt.getYOnScreen() - posY;
                setLocation(newX, newY);
            }
        });

        //Panel de Inferior
        JPanel salirPanel = new JPanel(new BorderLayout());
        salirPanel.setBackground(colorBase);
        // Boton de Cierre de ventana
        JButton salirButton = new JButton("Salir");
        salirButton.setForeground(Color.white);
        salirButton.setBackground(Color.RED);
        salirButton.setFont(font);
        // ActionListener del boton de salida
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        salirPanel.add(salirButton,BorderLayout.EAST);

        // Panel de los datos
        JPanel dataPanel = new JPanel(new GridBagLayout());
        dataPanel.setBackground(colorBase);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(4,8,4,8);

       // Imagen de IPCHILE.
        JLabel logoLabel = new JLabel();
        ImageIcon imagen = new ImageIcon("src/image/Logo 3.png");
        logoLabel.setIcon(imagen);
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 3;
        constraints.weighty = 0.1;
        constraints.weightx = 0.1;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.anchor = GridBagConstraints.CENTER;
        dataPanel.add(logoLabel,constraints);

        // Creacion de los JLabels que se utilizaran
        JLabel nombreLabel = new JLabel("Nombre :");            // Nombre
        nombreLabel.setFont(font);
        nombreLabel.setForeground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weighty = 1.0;
        constraints.weightx = 0;
        constraints.fill = GridBagConstraints.EAST;
        constraints.anchor = GridBagConstraints.EAST;
        dataPanel.add(nombreLabel,constraints);

        JLabel sueldoBrutoLabel = new JLabel("Sueldo Bruto :");      // sueldo bruto
        sueldoBrutoLabel.setFont(font);
        sueldoBrutoLabel.setForeground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 1;
        dataPanel.add(sueldoBrutoLabel,constraints);

        JLabel tipoEmpleadoLabel = new JLabel("Cargo Empleado :");
        tipoEmpleadoLabel.setFont(font);
        tipoEmpleadoLabel.setForeground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 2;
        dataPanel.add(tipoEmpleadoLabel,constraints);

        JLabel impuestoLabel = new JLabel("Impuesto");            // impuesto
        impuestoLabel.setForeground(Color.white);
        impuestoLabel.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = 4;
        dataPanel.add(impuestoLabel,constraints);

        JLabel afpLabel = new JLabel("AFP");                     // afp
        afpLabel.setFont(font);
        afpLabel.setForeground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 5;
        dataPanel.add(afpLabel,constraints);

        JLabel saludLabel = new JLabel("Salud");               // salud
        saludLabel.setFont(font);
        saludLabel.setForeground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 6;
        dataPanel.add(saludLabel,constraints);

        JLabel sueldoLiquidoLabel = new JLabel("Sueldo liquido");  // sueldo liquido
        sueldoLiquidoLabel.setFont(font);
        sueldoLiquidoLabel.setForeground(Color.white);
        constraints.gridx = 0;
        constraints.gridy = 7;
        dataPanel.add(sueldoLiquidoLabel,constraints);

        JLabel resumenLabel = new JLabel("Resumen :");          // Resumen label
        resumenLabel.setFont(font);
        resumenLabel.setForeground(Color.white);
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        dataPanel.add(resumenLabel,constraints);

        // Creacion de los Jtextfield

        nombrefield = new JTextField(50);                      // Nombre
        nombrefield.setHorizontalAlignment(SwingConstants.CENTER);
        nombrefield.setFont(font);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        dataPanel.add(nombrefield,constraints);

        sueldoBrutoField= new JTextField(10);                  // Sueldo Bruto
        sueldoBrutoField.setHorizontalAlignment(SwingConstants.CENTER);
        sueldoBrutoField.setFont(font);
        constraints.gridx = 1;
        constraints.gridy = 1;
        dataPanel.add(sueldoBrutoField,constraints);

        impuestoField = new JTextField(10);                   // impuesto
        impuestoField.setEditable(false);
        impuestoField.setFont(font);
        impuestoField.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 4;
        dataPanel.add(impuestoField,constraints);

        afpField = new JTextField(10);                                  // AFP
        afpField.setEditable(false);
        afpField.setFont(font);
        afpField.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 5;
        dataPanel.add(afpField,constraints);

        saludField = new JTextField(10);                               // Salud
        saludField.setEditable(false);
        saludField.setFont(font);
        saludField.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 6;
        dataPanel.add(saludField,constraints);

        sueldoLiquidoField = new JTextField(10);                     // Sueldo Liquido
        sueldoLiquidoField.setEditable(false);
        sueldoLiquidoField.setFont(font);
        sueldoLiquidoField.setHorizontalAlignment(SwingConstants.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 7;
        dataPanel.add(sueldoLiquidoField,constraints);

        // Creacion jtextPane de Resumen
        resumenPane = new JTextPane();
        resumenPane.setContentType("text/html");
        resumenPane.setEditable(false);
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 4;
        constraints.weighty = 1.0;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        dataPanel.add(resumenPane,constraints);

        // Creacion del Boton Calcular
        calcularButton = new JButton("Calcular");
        calcularButton.setFont(font);
        calcularButton.setBackground(colorBoton);
        calcularButton.setForeground(Color.white);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weighty = 1.0;
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.EAST;
        dataPanel.add(calcularButton,constraints);
        // Asignar ActionListener al Botón
        calcularButton.addActionListener(this);

        // ComboBox Tipo de empleado
        tipoEmpleadoBox = new JComboBox(stringComboBox);
        tipoEmpleadoBox.setFont(font);
        constraints.gridx = 1;
        constraints.gridy = 2;
        dataPanel.add(tipoEmpleadoBox,constraints);
        // Action Listener JComboBox
        tipoEmpleadoBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionBox = (String) tipoEmpleadoBox.getSelectedItem();
            }
        });

        // Action listener del ComboBox

        // agregar paneles al Panel Principal(main)
        mainPanel.add(tituloPanel,BorderLayout.NORTH);
        mainPanel.add(dataPanel,BorderLayout.CENTER);
        mainPanel.add(salirPanel, BorderLayout.SOUTH);

        // agregar el panel Principal al Jframe
        add(mainPanel);
    }
    // Eventos del Boton
    public void actionPerformed(ActionEvent e){
        try {
            // obtencion de datos.
            empleado.setNombre(nombrefield.getText());
            empleado.setSueldoBruto(Double.parseDouble(sueldoBrutoField.getText()));

            // Calculos de los descuentos.
            double impuesto = calc.calcularImpuesto(empleado.getSueldoBruto());   // impuesto
            impuestoField.setText(String.valueOf(impuesto));

            double afp = calc.calcularAFP(empleado.getSueldoBruto());             // AFP
            afpField.setText(String.valueOf(afp));

            double salud = calc.calcularSalud(empleado.getSueldoBruto());         // salud
            saludField.setText(String.valueOf(salud));
                                                                                // sueldo liquido
            double sueldoLiquido = calc.calcularSueldoLiquido(empleado.getSueldoBruto(), impuesto,afp,salud);
            sueldoLiquidoField.setText(String.valueOf(sueldoLiquido));

            // Redaccion del resumen y mostrarlo
            String resumen ="Nombre del empleado = " + empleado.getNombre() + "<br>" +
                    "Sueldo Bruto = " + empleado.getSueldoBruto() + "<br>" +
                    "Descuento impuesto = -" + impuesto + "<br>" +
                    "Descuento AFP = -" + afp + "<br>" +
                    "Descuento Salud = -" + salud + "<br>" +
                    "Sueldo Liquido = "+ sueldoLiquido + "<br>"+
                    "Cargo empleado = " + seleccionBox;
            resumenPane.setText(resumen);

        }catch (NumberFormatException ex){
            // Exepcion en caso de valores no validos
            JOptionPane.showMessageDialog(this, "ingrese valores validos");
        }
    }

}