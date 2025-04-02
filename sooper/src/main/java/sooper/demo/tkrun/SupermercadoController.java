package sooper.demo.tkrun;

import java.util.List;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;



public class SupermercadoController {

	private SupermercadoModel model;
	private SupermercadoView view;


		
	public void setVistaModel( SupermercadoView v , SupermercadoModel m) {
		this.model = m;
		this.view = v;
		//no hay inicializacion especifica del modelo, solo de la vista
		this.view.getFrame().setVisible(true);
	}
	
	//a partir de aqui, implementamos los metodos de las funcionalidades correspondientes
	
	public void AniadirArticulosPedido(int i) {
		//metodo para rellenar la tabla de articulos y pedidos
		int j;
		List<Object[]> lista=model.AniadirArticulosPedido(i);
		//una vez que me devuelva el modelo el resultado de la consulta, analizo la lista devuelta:
		
		for (j=0; j<lista.size();j++) {
			view.rellenaListaArticulos(lista.get(j));
		}
		
	};
	

	public SupermercadoView getView() {
		return view;
	}

	public void setView(SupermercadoView view) {
		this.view = view;
	}

	public SupermercadoModel getModel() {
		return model;
	}

	public void setModel(SupermercadoModel model) {
		this.model = model;
	}
	
	

	public void embolsarArticulos() {
		int idArticuloInt = getIdArticulo();
		int[] indexSeleccionados = getIndexArticulo();
		
		this.model.embolsarArticulo(idArticuloInt);
		
        DefaultTableModel tableModelEmbolsarArticulos = (DefaultTableModel) view.getTableModelEmbolsarArticulos();
		
        // cogemos el id del articulo a cambiar en bd, cogemos los index de las filas seleccionadas en la jTAble
        // y llamamos al modelo de la tabla para borrar las filas    


    	Object[] fila = new Object[2];  
    	
        for (int selectedRowIndex : indexSeleccionados) {      	     	
        	

        	              	
      
        	Object valorTablaEmbolsados = this.view.getTableModelEmbolsarArticulos().getValueAt(selectedRowIndex, 0);
        	
        	fila[0] = valorTablaEmbolsados;
        	fila[1] = "999";
        	// esta linea guarda el valor que queremos poner en la tabla embolsados
        	this.view.getTableModelEmbolsados().addRow(fila); // añadimos fila null al modelo de la tabla embolsados
        	this.view.getTableModelEmbolsados().setValueAt(valorTablaEmbolsados, selectedRowIndex, 0);
        	this.view.getTableModelEmbolsados().setValueAt("999", selectedRowIndex, 1);

        	
        	
        	this.view.getTableModelEmbolsarArticulos().removeRow(selectedRowIndex);
     	
        	
        	// esto borra las filas seleccionadas de la tabla (table usando el modelo de tabla tableModelEmbolsarArticulos)
        	// con getIndexArticulo tenemos los indices de las filas que vamos a borrar de la tabla
      


        }

		


		
	}
	
	
	private Integer getIdArticulo() {
		
		//int idArticulo = (int) this.view.getTableArticulosEmbolsar().getValueAt(this.view.getTableArticulosEmbolsar().getSelectedRow(),0);
		//int idArticulo = (int) tableArticulo.getValueAt(tableArticulo.getSelectedRow(), 0); 
			
		//return idArticulo;    tengo que mirar si esto funcionaria asi tb

		
		String idArticulo;
		idArticulo = this.view.getTableArticulosEmbolsar().getValueAt(this.view.getTableArticulosEmbolsar().getSelectedRow(),0).toString();
		Integer idArticuloInt = (Integer.parseInt(idArticulo));
		
		return idArticuloInt;
		
		

	}
	
	private int[] getIndexArticulo() {
		int[] indexSeleccionados;
		indexSeleccionados = this.view.getTableArticulosEmbolsar().getSelectedRows();
		return indexSeleccionados;
		
		
		// esto me devuelve los index de las filas seleccionadas en la JTable Articulos embolsar
		
	}
	
	


}

