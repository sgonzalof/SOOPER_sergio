package sooper.demo.tkrun;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class SupermercadoView {

	protected JFrame frmSupermercado;
	private JTextField textField;
	private JTable table;
	private JButton btnEmbolsar;
	private JScrollPane scrollPane_1;
	private JTable tablaEmbolsados;
	private JButton btnEnvioAlmacen;
	private SupermercadoController controller;
	private DefaultTableModel modeloArticulo;
	private DefaultTableModel modeloListaEmbolsados;



	

	 public SupermercadoView(SupermercadoController controlador) {
		// TODO Auto-generated constructor stub
		initialize(controlador);
	} 

	private void initialize(SupermercadoController controlador) {

		modeloArticulo = new DefaultTableModel(new Object[][] {
		},
		new String[] {
				"idArticulo", "idPedido", "Volumen"
			}
		);
		
		modeloListaEmbolsados = new DefaultTableModel(new Object[][] {
		},
		new String[] {
				"idArticulo", "idContenedor"
			});
		
		
		frmSupermercado = new JFrame();
		frmSupermercado.getContentPane().setLayout(new MigLayout("", "[grow]", "[][][grow][][grow][][]"));
		frmSupermercado.setBounds(0,0,500,500);
		this.controller = controlador; //aqui ya tengo vinculada la vista con el controlador que creo en el swingMain
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
			}
		});
	
		textField.setText("<introduce aqui el id Pedido>");
		frmSupermercado.getContentPane().add(textField, "cell 0 0,growx");
		textField.setColumns(10);
		
		JButton btnAniadirPedido = new JButton("Añadir Pedido");
		btnAniadirPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.AniadirArticulosPedido(Integer.parseInt(textField.getText()));
				
			}
		});
		
		frmSupermercado.getContentPane().add(btnAniadirPedido, "cell 0 1");
		
		JScrollPane scrollPane = new JScrollPane();
		frmSupermercado.getContentPane().add(scrollPane, "cell 0 2,grow");
		
		table = new JTable();
		table.setModel(modeloArticulo);
		table.getColumnModel().getColumn(2).setPreferredWidth(149);
		scrollPane.setViewportView(table);

		btnEmbolsar = new JButton("Embolsar");
		btnEmbolsar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.embolsarArticulos();
			}
		});
		frmSupermercado.getContentPane().add(btnEmbolsar, "cell 0 3");
		
		
		scrollPane_1 = new JScrollPane();
		frmSupermercado.getContentPane().add(scrollPane_1, "cell 0 4,grow");
		
		tablaEmbolsados = new JTable();
		tablaEmbolsados.setModel(modeloListaEmbolsados);
		scrollPane_1.setViewportView(tablaEmbolsados);
		
		btnEnvioAlmacen = new JButton("Enviar a almacén");
		btnEnvioAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			
				GestionAlmacenView vistaAlmacen = new GestionAlmacenView();
				
				//gestionAlmacenVista.getFrame().setVisible(true);
			}
		});
		
		frmSupermercado.getContentPane().add(btnEnvioAlmacen, "cell 0 5");
		frmSupermercado.setVisible(true);
	}
	
	public JFrame getFrame() {
		return this.frmSupermercado;
	}
	
public void rellenaListaArticulos(Object[] rowArticulo) {
		
		this.modeloArticulo.addRow(rowArticulo);//para añadir una fila a la tabla lo primero que tengo que añadir es la 
												//fila al modelo correspondiente a esa tabla
		this.table.setModel(modeloArticulo); //una vez la tenga añadida al modelo, muestro el modelo en la tabla
		
	}

public JTable getTableArticulosEmbolsar() {

	return table;
	}


public DefaultTableModel getTableModelEmbolsarArticulos() {

	return modeloArticulo;
}

public DefaultTableModel getTableModelEmbolsados() {

	return modeloListaEmbolsados;
	
	}
}

