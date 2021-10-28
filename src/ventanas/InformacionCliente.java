/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.sql.*;
import clases.Conexion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import static ventanas.GestionarClientes.id_cliente_update;

/**
 *
 * @author User
 */
public class InformacionCliente extends javax.swing.JFrame {
    
    DefaultTableModel model = new DefaultTableModel();
    int id_cliente_update = 0;
    public static int id_equipo = 0;
    String user = "";

    /**
     * Creates new form InformacionCliente
     */
    public InformacionCliente() {
        initComponents();
        user = Login.user;
        id_cliente_update = GestionarClientes.id_cliente_update;
        this.setSize(630, 460);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Añadimos la imagen de fondo al label, y la ajustamos al tamaño del label
        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono); //Añadimos la imagen al label
        this.repaint(); //Nos aseguramos que la imagen se vea

        //Conexion a la base de datos
        try {
            
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "SELECT * FROM clientes WHERE id_cliente = '" + id_cliente_update + "'");
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                this.setTitle("Información del cliente " + rs.getString("nombre_cliente") + " - Sesión de " + user);
                jLabel_Titulo.setText("Información del cliente " + rs.getString("nombre_cliente"));

                //Llenado de los campos con la base de datos
                txt_nombre.setText(rs.getString("nombre_cliente"));
                txt_email.setText(rs.getString("mail_cliente"));
                txt_telefono.setText(rs.getString("tel_cliente"));
                txt_direccion.setText(rs.getString("dir_cliente"));
                txt_ultimaModificacionPor.setText(rs.getString("ultima_modificacion"));
            }
            
            cn.close();
            
        } catch (SQLException e) {
            System.err.println("Error al cargar usuario " + e);
            JOptionPane.showMessageDialog(null, "¡¡Error al cargar!! Contacte al Administrador");
        }

        //LLenado de la tabla equipos
        try {
            
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "SELECT id_equipo, tipo_equipo, marca, estatus FROM equipos WHERE id_cliente = '" + id_cliente_update + "'");
            
            ResultSet rs = pst.executeQuery();
            
            jTable_equipos = new JTable(model);
            equipos.setViewportView(jTable_equipos);
            
            model.addColumn("ID equipo");
            model.addColumn("Tipo de Equipo");
            model.addColumn("Marca");
            model.addColumn("Estatus");
            
            while (rs.next()) {
                Object[] fila = new Object[4];
                
                for (int i = 0; i < 4; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                
                model.addRow(fila);
            }
            
            cn.close();
            
        } catch (SQLException e) {
            System.err.println("Error en el llenado de la tabla equipos " + e);
        }

        //Evento de accion para seleccionar cliente
        jTable_equipos.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = jTable_equipos.rowAtPoint(e.getPoint());
                int columna_point = 0;
                
                if (fila_point > -1) {
                    id_equipo = (int) model.getValueAt(fila_point, columna_point);
                    InformacionEquipo informacionEquipo = new InformacionEquipo();
                    informacionEquipo.setVisible(true);
                }
            }
        });
    }

    //Cambiamos el icono de la ventana
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/icon.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Titulo = new javax.swing.JLabel();
        jLabel_Nombre = new javax.swing.JLabel();
        jLabel_Mail = new javax.swing.JLabel();
        jLabel_Telefono = new javax.swing.JLabel();
        jLabel_Direccion = new javax.swing.JLabel();
        jLabel_UltimaModificacion = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        txt_ultimaModificacionPor = new javax.swing.JTextField();
        equipos = new javax.swing.JScrollPane();
        jTable_equipos = new javax.swing.JTable();
        jButton_Registrar = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();
        jButton_Imprimir_reporte = new javax.swing.JButton();
        jLabel_Footer = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Titulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel_Titulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Titulo.setText("Información del Cliente");
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel_Nombre.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel_Nombre.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Nombre.setText("Nombre :");
        getContentPane().add(jLabel_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel_Mail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel_Mail.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Mail.setText("Email :");
        getContentPane().add(jLabel_Mail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel_Telefono.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel_Telefono.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Telefono.setText("Teléfono :");
        getContentPane().add(jLabel_Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel_Direccion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel_Direccion.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_Direccion.setText("Direccion :");
        getContentPane().add(jLabel_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel_UltimaModificacion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel_UltimaModificacion.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_UltimaModificacion.setText("Ultima Modificación por :");
        getContentPane().add(jLabel_UltimaModificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        txt_nombre.setBackground(new java.awt.Color(153, 153, 255));
        txt_nombre.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txt_nombre.setForeground(new java.awt.Color(255, 255, 255));
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, -1));

        txt_email.setBackground(new java.awt.Color(153, 153, 255));
        txt_email.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txt_email.setForeground(new java.awt.Color(255, 255, 255));
        txt_email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_email.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, -1));

        txt_telefono.setBackground(new java.awt.Color(153, 153, 255));
        txt_telefono.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txt_telefono.setForeground(new java.awt.Color(255, 255, 255));
        txt_telefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_telefono.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, -1));

        txt_direccion.setBackground(new java.awt.Color(153, 153, 255));
        txt_direccion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txt_direccion.setForeground(new java.awt.Color(255, 255, 255));
        txt_direccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_direccion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, -1));

        txt_ultimaModificacionPor.setBackground(new java.awt.Color(153, 153, 255));
        txt_ultimaModificacionPor.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        txt_ultimaModificacionPor.setForeground(new java.awt.Color(255, 255, 255));
        txt_ultimaModificacionPor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ultimaModificacionPor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_ultimaModificacionPor.setEnabled(false);
        getContentPane().add(txt_ultimaModificacionPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 210, -1));

        jTable_equipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        equipos.setViewportView(jTable_equipos);

        getContentPane().add(equipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 380, 180));

        jButton_Registrar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_Registrar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_Registrar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Registrar.setText("Registrar Equipo");
        jButton_Registrar.setBorder(null);
        jButton_Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 210, 35));

        jButton_Actualizar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_Actualizar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton_Actualizar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Actualizar.setText("Actualizar Cliente");
        jButton_Actualizar.setBorder(null);
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 210, 35));

        jButton_Imprimir_reporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/impresora.png"))); // NOI18N
        jButton_Imprimir_reporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Imprimir_reporteActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Imprimir_reporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 120, 100));

        jLabel_Footer.setText("Creado por Juan Carlos Estevez Vargas");
        getContentPane().add(jLabel_Footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegistrarActionPerformed
        RegistrarEquipo registrarEquipo = new RegistrarEquipo();
        registrarEquipo.setVisible(true);
    }//GEN-LAST:event_jButton_RegistrarActionPerformed

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
        
        int validacion = 0;
        String nombre, mail, telefono, direccion;
        
        nombre = txt_nombre.getText().trim();
        mail = txt_email.getText().trim();
        telefono = txt_telefono.getText().trim();
        direccion = txt_direccion.getText().trim();

        //Validación de campos vacios
        if (nombre.equals("")) {
            txt_nombre.setBackground(Color.red);
            validacion++;
        }
        if (mail.equals("")) {
            txt_email.setBackground(Color.red);
            validacion++;
        }
        if (telefono.equals("")) {
            txt_telefono.setBackground(Color.red);
            validacion++;
        }
        if (direccion.equals("")) {
            txt_direccion.setBackground(Color.red);
            validacion++;
        }
        
        if (validacion == 0) {
            
            try {
                
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "UPDATE clientes SET nombre_cliente = ?, mail_cliente = ?, tel_cliente = ?, dir_cliente = ?, ultima_modificacion = ? "
                        + "WHERE id_cliente = '" + id_cliente_update + "'");
                
                pst.setString(1, nombre);
                pst.setString(2, mail);
                pst.setString(3, telefono);
                pst.setString(4, direccion);
                pst.setString(5, user);
                
                pst.executeUpdate();
                cn.close();
                
                Limpiar();
                
                txt_nombre.setBackground(Color.green);
                txt_email.setBackground(Color.green);
                txt_direccion.setBackground(Color.green);
                txt_telefono.setBackground(Color.green);
                txt_ultimaModificacionPor.setText(user);
                
                JOptionPane.showMessageDialog(null, "Actualización correcta");
                this.dispose();
                
            } catch (Exception e) {
                System.err.println("Error en actualizar cliente " + e);
                JOptionPane.showMessageDialog(null, "¡¡Error al actualizar cliente!! Contacata al Administrador");
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Debes llenar todos los campos");
        }

    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void jButton_Imprimir_reporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Imprimir_reporteActionPerformed
        
        Document documento = new Document(); //Creamos el documento pdf

        try {
            
            String ruta = System.getProperty("user.home"); //Ruta donde guardar el documento

            //Escribimos el documento en es escritorio de la computadora y le asignamos el nombre que recuperemos del txt nombre
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/" + txt_nombre.getText().trim() + ".pdf"));

            //Creamos una instancia de la clase image de itext, y colocamos la imagen que queremos
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/images/BannerPDF.jpg");
            header.scaleToFit(650, 1000); //Dimensiones de la imagen
            header.setAlignment(Chunk.ALIGN_CENTER); //Alineacion de la imagen

            Paragraph parrafo = new Paragraph(); //Creamos un parrafo
            parrafo.setAlignment(Paragraph.ALIGN_CENTER); //Alineación del parrafo
            parrafo.add("Información del cliente.\n \n"); //Agregamos el título
            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY)); //Asignamos fuente al parrafo

            documento.open(); //Abrimos el documento
            documento.add(header);
            documento.add(parrafo);

            //Creamos una tabla donde se mostraran los datos y añadimos las columnas
            PdfPTable tabla_clientes = new PdfPTable(5);
            tabla_clientes.addCell("ID");
            tabla_clientes.addCell("Nombre");
            tabla_clientes.addCell("Email");
            tabla_clientes.addCell("Teléfono");
            tabla_clientes.addCell("Dirección");
            
            try {
                
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "SELECT * FROM clientes WHERE id_cliente = '" + id_cliente_update + "'");
                
                ResultSet rs = pst.executeQuery();
                
                if (rs.next()) {
                    
                    do {

                        //Recuperamos los campos de la base de datos
                        tabla_clientes.addCell(rs.getString(1));
                        tabla_clientes.addCell(rs.getString(2));
                        tabla_clientes.addCell(rs.getString(3));
                        tabla_clientes.addCell(rs.getString(4));
                        tabla_clientes.addCell(rs.getString(5));
                        
                    } while (rs.next());
                    
                    documento.add(tabla_clientes);
                    
                }

                //Ceación de un nuevo parrafo
                Paragraph parrafo2 = new Paragraph();
                parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo2.add("\n\nEquipos registrados.\n\n");
                parrafo2.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));
                
                documento.add(parrafo2);

                //Creación tabla equipos registrados
                PdfPTable tabla_equipos = new PdfPTable(4);
                tabla_equipos.addCell("ID equipo");
                tabla_equipos.addCell("Tipo");
                tabla_equipos.addCell("Marca");
                tabla_equipos.addCell("Estatus");
                
                try {
                    
                    Connection cn2 = Conexion.conectar();
                    PreparedStatement pst2 = cn2.prepareStatement(
                            "SELECT id_equipo, tipo_equipo, marca, estatus FROM equipos WHERE id_cliente = '" + id_cliente_update + "'");
                    
                    ResultSet rs2 = pst2.executeQuery();
                    
                    if (rs2.next()) {
                        
                        do {
                            
                            tabla_equipos.addCell(rs2.getString(1));
                            tabla_equipos.addCell(rs2.getString(2));
                            tabla_equipos.addCell(rs2.getString(3));
                            tabla_equipos.addCell(rs2.getString(4));
                            
                        } while (rs.next());
                        
                        documento.add(tabla_equipos);
                        
                    }
                    
                } catch (DocumentException | SQLException e) {
                    System.err.println("Error al obtener datos de equipos " + e);
                }
                
            } catch (DocumentException | SQLException e) {
                System.err.println("Error al obtener datos del cliente " + e);
            }
            
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado correctamente");
            
        } catch (DocumentException | IOException e) {
            System.err.println("Error en pdf o ruta de imagen " + e);
            JOptionPane.showMessageDialog(null, "¡¡Error al generar pdf!! Contacte al Administrador");
        }

    }//GEN-LAST:event_jButton_Imprimir_reporteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane equipos;
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Imprimir_reporte;
    private javax.swing.JButton jButton_Registrar;
    private javax.swing.JLabel jLabel_Direccion;
    private javax.swing.JLabel jLabel_Footer;
    private javax.swing.JLabel jLabel_Mail;
    private javax.swing.JLabel jLabel_Nombre;
    private javax.swing.JLabel jLabel_Telefono;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_UltimaModificacion;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JTable jTable_equipos;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_ultimaModificacionPor;
    // End of variables declaration//GEN-END:variables

    public void Limpiar() {
        txt_nombre.setText("");
        txt_direccion.setText("");
        txt_email.setText("");
        txt_telefono.setText("");
    }
    
}
