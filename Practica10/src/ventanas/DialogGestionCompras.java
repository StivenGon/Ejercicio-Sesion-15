/**
 * @author Stiven Gonzalez
 * 
 */

package ventanas;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogGestionCompras extends JDialog {
    private JTextField txtCodigo;
    private JTextField txtNombreArticulo;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTable tbListaCompras;
    
    public static void main(String[] args) {
        try {
            DialogGestionCompras dialog = new DialogGestionCompras();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DialogGestionCompras() {
setTitle("GESTION DE COMPRAS");
setBounds(100, 100, 750, 330);
getContentPane().setLayout(null);

JPanel panel = new JPanel();
panel.setBorder(new TitledBorder(null, "Datos de Compras", TitledBorder.LEADING, TitledBorder.TOP, null, null));
panel.setBounds(10, 55, 239, 260);
getContentPane().add(panel);
panel.setLayout(null);

txtCodigo = new JTextField();
txtCodigo.setBounds(132, 24, 86, 20);
panel.add(txtCodigo);
txtCodigo.setColumns(10);

JLabel lblNewLabel_3 = new JLabel("Codigo");
lblNewLabel_3.setBounds(10, 27, 46, 14);
panel.add(lblNewLabel_3);

JLabel lblNewLabel_4 = new JLabel("Nombre Articulo");
lblNewLabel_4.setBounds(10, 68, 112, 14);
panel.add(lblNewLabel_4);

JLabel lblNewLabel_5 = new JLabel("Descripcion");
lblNewLabel_5.setBounds(10, 106, 112, 14);
panel.add(lblNewLabel_5);

txtNombreArticulo = new JTextField();
txtNombreArticulo.setBounds(132, 65, 86, 20);
panel.add(txtNombreArticulo);
txtNombreArticulo.setColumns(10);

txtDescripcion = new JTextField();
txtDescripcion.setBounds(132, 103, 86, 20);
panel.add(txtDescripcion);
txtDescripcion.setColumns(10);

JLabel lblNewLabel_6 = new JLabel("Precio");
lblNewLabel_6.setBounds(10, 143, 112, 14);
panel.add(lblNewLabel_6);

txtPrecio = new JTextField();
txtPrecio.setBounds(132, 140, 86, 20);
panel.add(txtPrecio);
txtPrecio.setColumns(10);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.Compras objetoCompras = new clases.Compras();
                objetoCompras.setCodigo(txtCodigo.getText());
                objetoCompras.setNombreArticulo(txtNombreArticulo.getText());
                objetoCompras.setDescripcion(txtDescripcion.getText());
                objetoCompras.setPrecio(txtPrecio.getText());
                // ver este aca
                objetoCompras.agregarRegistrosCompras();
                //
                txtCodigo.setText("");
                txtNombreArticulo.setText("");
                txtDescripcion.setText("");
                txtPrecio.setText("");
                objetoCompras.mostrarTotalCompras(tbListaCompras);
            }
        });
        btnGuardar.setBounds(10, 168, 208, 29);
        panel.add(btnGuardar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.Compras objetoCompras = new clases.Compras();
                if (tbListaCompras.getSelectedRow() >= 0) {
                    objetoCompras.EditarCompras(tbListaCompras);
                    objetoCompras.mostrarTotalCompras(tbListaCompras);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila para editar.");
                }
            }
        });
        btnEditar.setBounds(10, 200, 99, 23);
        panel.add(btnEditar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clases.Compras objetoCompras = new clases.Compras();
				objetoCompras.EliminarCompras(tbListaCompras, txtCodigo);
			}
		
        });
        btnEliminar.setBounds(119, 200, 99, 23);
        panel.add(btnEliminar);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "Lista de Compras", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(275, 54, 430, 215);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 30, 410, 175);
        panel_1.add(scrollPane);

        tbListaCompras = new JTable();
        scrollPane.setViewportView(tbListaCompras);

        JButton btnCrearArchivoCompras = new JButton("Crear Archivo de Compras");
        btnCrearArchivoCompras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.Compras objetoCompras = new clases.Compras();
                // ver este tambien
                objetoCompras.crearArchivoRecursosHumanos();
                //
            }
        });
        btnCrearArchivoCompras.setBounds(10, 21, 255, 23);
        getContentPane().add(btnCrearArchivoCompras);

        JButton btnMostrarCompras = new JButton("Mostrar Compras");
        btnMostrarCompras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.Compras objetoCompras = new clases.Compras();
                objetoCompras.mostrarTotalCompras(tbListaCompras);
            }
        });
        btnMostrarCompras.setBounds(275, 21, 200, 23);
        getContentPane().add(btnMostrarCompras);

        JButton btnSeleccionar = new JButton("Seleccionar Compra");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clases.Compras objetoCompras = new clases.Compras();
                objetoCompras.seleccionarCompras(tbListaCompras);
                txtCodigo.setText(objetoCompras.getCodigo());
                txtNombreArticulo.setText(objetoCompras.getNombreArticulo());
                txtDescripcion.setText(objetoCompras.getDescripcion());
                txtPrecio.setText(objetoCompras.getPrecio());
            }
        });
        btnSeleccionar.setBounds(480, 20, 220, 23);
        getContentPane().add(btnSeleccionar);
    }
}
