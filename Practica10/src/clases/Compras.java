/**
 * @author Stiven Gonzalez
 * 
 */

package clases;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.List;
import java.util.StringJoiner;

public class Compras {

    private String codigo;
    private String nombreArticulo;
    private String Descripcion;
    private String Precio;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }

    public void crearArchivoRecursosHumanos() {
        try {
            File objetoArchivo = new File("Compras.txt");
            if (objetoArchivo.createNewFile()) {
                JOptionPane.showMessageDialog(null, "Se ha creado correctamente el archivo: " + objetoArchivo.getName());
            } else {
                JOptionPane.showMessageDialog(null, "El archivo ya existe");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al crear el archivo");
        }
    }

    public void agregarRegistrosCompras() {
        try {
            FileWriter fw = new FileWriter("Compras.txt", true);

            fw.write(getCodigo());
            fw.write(",");
            fw.write(getNombreArticulo());
            fw.write(",");
            fw.write(getDescripcion());
            fw.write(",");
            fw.write(getPrecio());
            fw.write("\n");
            fw.close();

            JOptionPane.showMessageDialog(null, "Se registró correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al registrar" + e.toString());
        }
    }

    public void mostrarTotalCompras(JTable tablaTotalCompras) {
        String nombreArchivo = "Compras.txt";
        File file = new File(nombreArchivo);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String primeraLinea = br.readLine().trim();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("Codigo");
            model.addColumn("NombreArticulo");
            model.addColumn("Descripcion");
            model.addColumn("Precio");

            tablaTotalCompras.setModel(model);

            Object[] tableLines = br.lines().toArray();

            for (int i = 0; i < tableLines.length; i++) {
                String line = tableLines[i].toString().trim();
                String[] datarow = line.split(",");
                model.addRow(datarow);
                tablaTotalCompras.setModel(model);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error" + e.toString());
        }
    }

    public void seleccionarCompras(JTable tablaCompras) {
        try {
            int fila = tablaCompras.getSelectedRow();

            if (fila >= 0) {
                setCodigo(tablaCompras.getValueAt(fila, 0).toString());
                setNombreArticulo(tablaCompras.getValueAt(fila, 1).toString());
                setDescripcion(tablaCompras.getValueAt(fila, 2).toString());
                setPrecio(tablaCompras.getValueAt(fila, 3).toString());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error" + e.toString());
        }
    }

public void EliminarCompras (JTable tablaCompras, JTextField codigo) {
	
	//Eliminacion visual de la tabla
	DefaultTableModel model = (DefaultTableModel)tablaCompras.getModel();
	
	for (int i = 0; i < model.getRowCount(); i++) {
		
		if(((String)model.getValueAt(i, 0)).equals(codigo.getText())) {	
			model.removeRow(i);
			break;
			
		}
	}

	//Limpieza del archivo .txt
	
	try {
		PrintWriter writer = new PrintWriter("Compras.txt");
		writer.print("");
		writer.close();
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null,"Ocurrió un problema"+ e.toString());
	}
	
	//Creacion de los nuevos registros luego de la eliminaci�n
	
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Compras.txt")))) {
		StringJoiner joiner = new StringJoiner(",");
		
		for (int col = 0; col < tablaCompras.getColumnCount(); col++) {
			joiner.add(tablaCompras.getColumnName(col));
		}
		
		System.out.println(joiner.toString());
		bw.write(joiner.toString());
		bw.newLine();
		
		for (int row = 0; row < tablaCompras.getRowCount(); row++) {
			 joiner = new StringJoiner(",");		
			for (int col = 0; col < tablaCompras.getColumnCount(); col++) {
				
				Object obj = tablaCompras.getValueAt(row, col);
				String value = obj == null ? "null" :obj.toString();
				joiner.add(value);
				
			}
			
			
			bw.write(joiner.toString());
			bw.newLine();
			JOptionPane.showMessageDialog(null, "Se elimino correctamente");
		}

		
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Ocurrio un error");
	}
	
	
	
}

public void EditarCompras(JTable tablaCompras) {
	
	//Limpieza del archivo .txt
	
		try {
			PrintWriter writer = new PrintWriter("Compras.txt");
			writer.print("");
			writer.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Ocurrio un problema"+ e.toString());
		}
		
		//Creacion de los nuevos registros luego de la eliminaci�n
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Compras.txt")))) {
			StringJoiner joiner = new StringJoiner(",");
			for (int col = 0; col < tablaCompras.getColumnCount(); col++) {
				joiner.add(tablaCompras.getColumnName(col));
			}			
			System.out.println(joiner.toString());
			bw.write(joiner.toString());
			bw.newLine();			
			for (int row = 0; row < tablaCompras.getRowCount(); row++) {
				joiner = new StringJoiner(",");
				for (int col = 0; col < tablaCompras.getColumnCount(); col++) {					
					Object obj = tablaCompras.getValueAt(row, col);
					String value = obj == null ? "null" :obj.toString();
					joiner.add(value);					
				}			
				System.out.println(joiner.toString());
				bw.write(joiner.toString());
				bw.newLine();
				JOptionPane.showMessageDialog(null, "Se modifico correctamente");
			}		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocurrio un error");
		}		
}
}