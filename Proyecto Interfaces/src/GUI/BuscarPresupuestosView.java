package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.PresupuestoDAO;
import DAO.VentasDAO;
import GUI.VerVentasView.MyKeyListener;
import Models.Presupuesto;
import Models.Usuarios;
import Models.Ventas;
import javax.swing.SwingConstants;

public class BuscarPresupuestosView {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Usuarios miuser;
	private PresupuestoDAO contro;
	private JTable TBCli;
	private JScrollPane scrollPane;
	private DefaultTableModel modeloTBCli;
	private TableRowSorter<TableModel> modeloOrdenado;
	private JTextField textField_1;
	private JTextField JTF_empleado;
	private JTextField JTF_nom_cli;
	private JTextField JTF_ape_cli;
	private JTextField JTF_fecha_registro;
	private JTextField JTF_Fecha_lim;
	private JTextField JTF_matricula;
	private JTextField JTF_precio;
	private JLabel LBRegistros;
	private JTextField JTF_id;
	private JComboBox <String> Mi_combo;
	
	public BuscarPresupuestosView(Usuarios user) {
		contro=new PresupuestoDAO();
		miuser=user;
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200,1200 );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// implementar las teclas en el frame
		KeyListener listener = new MyKeyListener();
		frame.addKeyListener(listener);
		frame.setFocusable(true);
		frame.requestFocus();
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setBackground(Color.decode("#264653"));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_titulo = new JPanel();
		panel_titulo.setBackground(Color.decode("#264653"));
		panel.add(panel_titulo);
		
		JLabel JLB_titulo = new JLabel("Consulta de ventas");
		JLB_titulo.setForeground(Color.ORANGE);
		JLB_titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_titulo.add(JLB_titulo);
		
		JPanel panel_principal = new JPanel();
		panel_principal.setBackground(Color.decode("#2A9D8F"));
		panel.add(panel_principal);
		
		
		
		JPanel panel_izquierdo = new JPanel();
		panel_izquierdo.setBackground(Color.decode("#2A9D8F"));
		panel_principal.add(panel_izquierdo);
		panel_izquierdo.setLayout(new BoxLayout(panel_izquierdo, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Usuario actual");
		panel_izquierdo.add(lblNewLabel);
		
		
		ImageIcon imagenuser = new ImageIcon(miuser.getFoto());
		JLabel JLB_image_user = new JLabel(imagenuser);
		panel_izquierdo.add(JLB_image_user);
		
		JLabel JLB_USER_actual = new JLabel("ACTUAL");
		panel_izquierdo.add(JLB_USER_actual);
		JLB_USER_actual.setText(miuser.getNick());
		
		
		JLabel JLB_buscar_cli = new JLabel("Buscar Clientes");
		JLB_buscar_cli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BusCliView miBuscCli = new BusCliView(frame,miuser);
				miBuscCli.getFrame().setAlwaysOnTop(true);
				miBuscCli.getFrame().setVisible(true);
				frame.dispose();
			}
			@Override
			public void mouseEntered (MouseEvent e) {
				JLB_buscar_cli.setForeground(Color.RED);
			}
			@Override
			public void mouseExited (MouseEvent e) {
				JLB_buscar_cli.setForeground(Color.ORANGE);
			}
		});
		JLB_buscar_cli.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLB_buscar_cli.setForeground(Color.decode("#E9C46A"));
		panel_izquierdo.add(JLB_buscar_cli);
		
		JLabel JLB_ficha_cliente = new JLabel("Ficha Cliente");
		JLB_ficha_cliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FichaClienteView miFichaClientes = new FichaClienteView(miuser,0);
				miFichaClientes.getFrame().setAlwaysOnTop(true);
				miFichaClientes.getFrame().setVisible(true);
				frame.dispose();
			}
			@Override
			public void mouseEntered (MouseEvent e) {
				JLB_ficha_cliente.setForeground(Color.RED);
			}
			@Override
			public void mouseExited (MouseEvent e) {
				JLB_ficha_cliente.setForeground(Color.ORANGE);
			}
		});
		JLB_ficha_cliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLB_ficha_cliente.setForeground(Color.decode("#E9C46A"));
		panel_izquierdo.add(JLB_ficha_cliente);
		
		JLabel Busca_vehiculos = new JLabel("Busca Vehiculos");
		Busca_vehiculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConsVeh busqueda= new ConsVeh(miuser);
				busqueda.getFrame().setAlwaysOnTop(true);
				busqueda.getFrame().setVisible(true);
				frame.dispose();
			}
			@Override
			public void mouseEntered (MouseEvent e) {
				Busca_vehiculos.setForeground(Color.RED);
			}
			@Override
			public void mouseExited (MouseEvent e) {
				Busca_vehiculos.setForeground(Color.ORANGE);
			}
		});
		Busca_vehiculos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Busca_vehiculos.setForeground(Color.decode("#E9C46A"));
		panel_izquierdo.add(Busca_vehiculos);
		
		JLabel JLB_ficha_vehiculo = new JLabel("Ficha Vehiculo");
		JLB_ficha_vehiculo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FichaVehiculoView miFicVeh = new FichaVehiculoView (miuser,0);
				miFicVeh.getFrame().setVisible(true);
				miFicVeh.getFrame().setAlwaysOnTop(true);
				frame.dispose();
			}
			@Override
			public void mouseEntered (MouseEvent e) {
				JLB_ficha_vehiculo.setForeground(Color.RED);
			}
			@Override
			public void mouseExited (MouseEvent e) {
				JLB_ficha_vehiculo.setForeground(Color.ORANGE);
			}
		});
		JLB_ficha_vehiculo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLB_ficha_vehiculo.setForeground(Color.decode("#E9C46A"));
		panel_izquierdo.add(JLB_ficha_vehiculo);
		
		JLabel JLB_cerrar_sesion = new JLabel("Cerrar sesi\u00F3n");
		JLB_cerrar_sesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				LoginView miLogin = new LoginView();
				miLogin.getFrame().setAlwaysOnTop(true);
				miLogin.getFrame().setVisible(true);
			}
			@Override
			public void mouseEntered (MouseEvent e) {
				JLB_cerrar_sesion.setForeground(Color.RED);
			}
			@Override
			public void mouseExited (MouseEvent e) {
				JLB_cerrar_sesion.setForeground(Color.ORANGE);
			}
		});
		JLB_cerrar_sesion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLB_cerrar_sesion.setForeground(Color.decode("#E76F51"));
		panel_izquierdo.add(JLB_cerrar_sesion);
		
		
		JPanel panel_derecho = new JPanel();
		panel_principal.add(panel_derecho);
		panel_derecho.setBackground(Color.decode("#2A9D8F"));
		panel_derecho.setLayout(new BoxLayout(panel_derecho, BoxLayout.X_AXIS));
		
		JPanel panel_cont_p_d = new JPanel();
		panel_derecho.add(panel_cont_p_d);
		panel_cont_p_d.setBackground(Color.decode("#2A9D8F"));
		panel_cont_p_d.setLayout(new BoxLayout(panel_cont_p_d, BoxLayout.Y_AXIS));
		
		// panel de busqueda
		
		JPanel panel_busqueda = new JPanel();
		panel_cont_p_d.add(panel_busqueda);
		panel_busqueda.setBackground(Color.decode("#2A9D8F"));
		panel_busqueda.setLayout(new BoxLayout(panel_busqueda, BoxLayout.Y_AXIS));
		
		JPanel panel_lin_1 = new JPanel();
		panel_lin_1.setBackground(Color.decode("#2A9D8F"));
		panel_busqueda.add(panel_lin_1);
		
		JLabel ID_PPTO = new JLabel("ID Presupuesto");
		ID_PPTO.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_lin_1.add(ID_PPTO);
		
		JTF_id = new JTextField();
		JTF_id.setText("");
		panel_lin_1.add(JTF_id);
		JTF_id.setColumns(10);
		JTF_id.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent arg0) {
			}
			@Override
			public void keyReleased(java.awt.event.KeyEvent arg0) {
				addFiltros(arg0);
			}
			@Override
			public void keyTyped(java.awt.event.KeyEvent arg0) {
				
			}
		});
		
		JLabel JLB_empleado = new JLabel("Empleado");
		panel_lin_1.add(JLB_empleado);
		
		JTF_empleado = new JTextField();
		JTF_empleado.setColumns(10);
		panel_lin_1.add(JTF_empleado);
		JTF_empleado.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent arg0) {
			}
			@Override
			public void keyReleased(java.awt.event.KeyEvent arg0) {
				addFiltros(arg0);
			}
			@Override
			public void keyTyped(java.awt.event.KeyEvent arg0) {
				
			}
		});
		
		JLabel JLB_nombre_cliente = new JLabel("Nombre Cliente");
		panel_lin_1.add(JLB_nombre_cliente);
		
		JTF_nom_cli = new JTextField();
		JTF_nom_cli.setColumns(10);
		panel_lin_1.add(JTF_nom_cli);
		JTF_nom_cli.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent arg0) {
			}
			@Override
			public void keyReleased(java.awt.event.KeyEvent arg0) {
				addFiltros(arg0);
			}
			@Override
			public void keyTyped(java.awt.event.KeyEvent arg0) {
				
			}
		});
		
		JLabel JLB_apellido_cli = new JLabel("Apellido cliente");
		panel_lin_1.add(JLB_apellido_cli);
		
		JTF_ape_cli = new JTextField();
		JTF_ape_cli.setColumns(10);
		panel_lin_1.add(JTF_ape_cli);
		JTF_ape_cli.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent arg0) {
			}
			@Override
			public void keyReleased(java.awt.event.KeyEvent arg0) {
				addFiltros(arg0);
			}
			@Override
			public void keyTyped(java.awt.event.KeyEvent arg0) {
				
			}
		});
		
		JPanel panel_lin_2 = new JPanel();
		panel_lin_2.setBackground(Color.decode("#2A9D8F"));
		panel_busqueda.add(panel_lin_2);
		
		JLabel JLB_fecha_ini = new JLabel("Fecha registro");
		panel_lin_2.add(JLB_fecha_ini);
		
		JTF_fecha_registro = new JTextField();
		JTF_fecha_registro.setColumns(10);
		panel_lin_2.add(JTF_fecha_registro);
		JTF_fecha_registro.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent arg0) {
			}
			@Override
			public void keyReleased(java.awt.event.KeyEvent arg0) {
				addFiltros(arg0);
			}
			@Override
			public void keyTyped(java.awt.event.KeyEvent arg0) {
				
			}
		});
		
		JLabel JLB_fecha_lim = new JLabel("Fecha limite");
		panel_lin_2.add(JLB_fecha_lim);
		
		
		JTF_Fecha_lim = new JTextField();
		JTF_Fecha_lim.setColumns(10);
		panel_lin_2.add(JTF_Fecha_lim);
		JTF_Fecha_lim.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent arg0) {
			}
			@Override
			public void keyReleased(java.awt.event.KeyEvent arg0) {
				addFiltros(arg0);
			}
			@Override
			public void keyTyped(java.awt.event.KeyEvent arg0) {
				
			}
		});
		
		JLabel JLB_mat_veh = new JLabel("Matricula");
		panel_lin_2.add(JLB_mat_veh);
		
		JTF_matricula = new JTextField();
		JTF_matricula.setColumns(10);
		panel_lin_2.add(JTF_matricula);
		JTF_matricula.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent arg0) {
			}
			@Override
			public void keyReleased(java.awt.event.KeyEvent arg0) {
				addFiltros(arg0);
			}
			@Override
			public void keyTyped(java.awt.event.KeyEvent arg0) {
				
			}
		});
		
		JLabel JLB_precio = new JLabel("precio");
		panel_lin_2.add(JLB_precio);
		
		JTF_precio = new JTextField();
		JTF_precio.setColumns(10);
		panel_lin_2.add(JTF_precio);
		JTF_precio.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent arg0) {
			}
			@Override
			public void keyReleased(java.awt.event.KeyEvent arg0) {
				addFiltros(arg0);
			}
			@Override
			public void keyTyped(java.awt.event.KeyEvent arg0) {
				
			}
		});
		
		Mi_combo = new JComboBox();
		Mi_combo.addItem("=");
		Mi_combo.addItem("<");
		Mi_combo.addItem(">");
		panel_lin_2.add(Mi_combo);
		
		//panel de tabla
		JPanel panel_tabla = new JPanel();
		panel_cont_p_d.add(panel_tabla);
		panel_tabla.setLayout(new BoxLayout(panel_tabla, BoxLayout.X_AXIS));
		scrollPane = new JScrollPane();
		panel_tabla.add(scrollPane);
		
		// tabla
		
		TBCli = new JTable();
		TBCli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(TBCli);
		
		
		//crear modelo de la tabla
		modeloTBCli = new DefaultTableModel(){
			/**
			 * definimos el modelo de la tabla con la 1ª columna integer
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public Class getColumnClass(int columna) {
				// primera columna integer
				if (columna == 0)
					return Integer.class;
				return String.class;
			}
			@Override
			public boolean isCellEditable (int row, int column)
			   {
			       // Aquí devolvemos true o false según queramos que una celda
			       // identificada por fila,columna (row,column), sea o no editable
			       if (column >=0)
			          return false;
			       return true;
			   }
		};
		
		// carga columnas de la tabla
		modeloTBCli.addColumn("Id");
		modeloTBCli.addColumn("Empleado");
		modeloTBCli.addColumn("Nombre");
		modeloTBCli.addColumn("Apellidos");
		modeloTBCli.addColumn("Fecha Alta");
		modeloTBCli.addColumn("Fecha limite");
		modeloTBCli.addColumn("Matricula");
		modeloTBCli.addColumn("precio");
		//añade el modelo a la tabla
		TBCli.setModel(modeloTBCli);
					
		// hace ordenable la tabla
		modeloOrdenado = new TableRowSorter<TableModel>(modeloTBCli);
		TBCli.setRowSorter(modeloOrdenado);
		
		textField_1 = new JTextField();
		scrollPane.setColumnHeaderView(textField_1);
		textField_1.setColumns(10);
		
		cargargetpresupuestos(contro.getpresupuestos());
		
		
		//evento
		TBCli.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
			if(e.getClickCount()==2){
				frame.dispose();
				seleccionar();
			}
			}
			});
		
		//panel de botonera
		JPanel panel_botonera = new JPanel();
		panel_botonera.setBackground(Color.decode("#2A9D8F"));
		panel_cont_p_d.add(panel_botonera);
		
		JButton JBT_seleccionar = new JButton("Seleccionar");
		panel_botonera.add(JBT_seleccionar);
		
		JButton JBT_salir = new JButton("Salir");
		panel_botonera.add(JBT_salir);
		JBT_salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		
		//panel de registros
		
		JPanel panel_registros = new JPanel();
		panel_registros.setBackground(Color.decode("#2A9D8F"));
		panel_cont_p_d.add(panel_registros);
		
		JButton BTPrimero = new JButton("<<");
		BTPrimero.setToolTipText("Primer registro.");
		BTPrimero.setForeground(Color.RED);
		BTPrimero.setFont(new Font("Tahoma", Font.BOLD, 8));
		panel_registros.add(BTPrimero);
		BTPrimero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TBCli.setRowSelectionInterval(0, 0);
				refrescaReg();
			}
		});
		
		JButton BTAnterior = new JButton("<");
		BTAnterior.setToolTipText("Registro anterior.");
		BTAnterior.setForeground(Color.RED);
		BTAnterior.setFont(new Font("Tahoma", Font.BOLD, 8));
		panel_registros.add(BTAnterior);
		BTAnterior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (TBCli.getSelectedRow()>0) 
					TBCli.setRowSelectionInterval(TBCli.getSelectedRow()-1,TBCli.getSelectedRow()-1);
					refrescaReg();
			}
		});
		
		LBRegistros = new JLabel(" No se han encontrado registros.");
		LBRegistros.setBackground(Color.WHITE);
		LBRegistros.setBorder(new LineBorder(Color.BLUE, 1, true));
		
		panel_registros.add(LBRegistros);
		
		JButton BTSiguiente = new JButton(">");
		BTSiguiente.setToolTipText("Registro siguiente.");
		BTSiguiente.setForeground(Color.RED);
		BTSiguiente.setFont(new Font("Tahoma", Font.BOLD, 8));
		panel_registros.add(BTSiguiente);
		BTSiguiente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (TBCli.getSelectedRow()<TBCli.getRowCount()) 
					TBCli.setRowSelectionInterval(TBCli.getSelectedRow()+1,TBCli.getSelectedRow()+1);
				refrescaReg();
			}
		});
		
		JButton BTultimo = new JButton(">>");
		BTultimo.setToolTipText("\u00DAltimo registro.");
		BTultimo.setForeground(Color.RED);
		BTultimo.setFont(new Font("Tahoma", Font.BOLD, 8));
		panel_registros.add(BTultimo);
		BTultimo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TBCli.setRowSelectionInterval(TBCli.getRowCount()-1,TBCli.getRowCount()-1);
				refrescaReg();
			}
		});
		
		// Panel para los botones del control de registros
		JPanel panelBotoneras = new JPanel();
		panelBotoneras.setMaximumSize(new Dimension(1000, 60));
		panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
		panelBotoneras.setBackground(Color.decode("#2A9D8F"));
		
		frame.pack();

	}
	
	public void cargargetpresupuestos(ArrayList<Presupuesto> lista) {
		Object [] fila = new Object[8];

		for (int i=0;i<lista.size();i++) {
			fila[0]=(int) lista.get(i).getId();
			fila[1]=contro.getnick(lista.get(i).getId_emple());
			fila[2]=contro.getnombrecli(lista.get(i).getId_cli());
			fila[3]=contro.getapellidocli(lista.get(i).getId_cli());
			fila[4]=lista.get(i).getFecha_ppto();
			fila[5]=lista.get(i).getFecha_validez();
			fila[6]=contro.getmatricula(lista.get(i).getId_veh());
			fila[7]=lista.get(i).getPrecio();
			modeloTBCli.addRow(fila);
		}
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	protected void seleccionar() {
		int id_Conce=(int) TBCli.getModel().getValueAt(TBCli.getSelectedRow(),0);
		// llamada a ficha concesionario
		FichaPPTO miFicppto = new FichaPPTO(miuser,id_Conce);
		miFicppto.getFrame().setAlwaysOnTop(true);
		miFicppto.getFrame().setVisible(true);
		frame.dispose();
	}
	
	protected void addFiltros(KeyEvent e) {
		ArrayList <RowFilter<TableModel,Integer>> filtros = new ArrayList <RowFilter<TableModel,Integer>>();
		int ascii = e.getKeyChar();
		if ((JTF_id.getText().length()>0) && (ascii>47) && (ascii<58)) {
			   filtros.add(RowFilter.numberFilter(
			     ComparisonType.EQUAL,
			     Integer.parseInt(String.valueOf(JTF_id.getText()))
			     ,0)); 
			   } else {
			    filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",1));
		
			   }
		if (JTF_empleado.getText().length()>0) {
			filtros.add(RowFilter.regexFilter(JTF_empleado.getText(),1));
		} else {
			filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",1));
		}
		
		if (JTF_nom_cli.getText().length()>0) {
			filtros.add(RowFilter.regexFilter(JTF_nom_cli.getText(),2));
		} else {
			filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",2));
		}
		
		if (JTF_ape_cli.getText().length()>0) {
			filtros.add(RowFilter.regexFilter(JTF_ape_cli.getText(),3));
		} else {
			filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",3));
		}
		if (JTF_fecha_registro.getText().length()>0) {
			filtros.add(RowFilter.regexFilter(JTF_fecha_registro.getText(),4));
		} else {
			filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",4));
		}
		
		if (JTF_Fecha_lim.getText().length()>0) {
			filtros.add(RowFilter.regexFilter(JTF_Fecha_lim.getText(),5));
		} else {
			filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",5));
		}
		
		if (JTF_matricula.getText().length()>0) {
			filtros.add(RowFilter.regexFilter(JTF_matricula.getText(),6));
		} else {
			filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",6));
		}
		
		if ((JTF_precio.getText().length()>0)) {
			if (Mi_combo.getSelectedItem().toString()=="=") {
			   filtros.add(RowFilter.numberFilter(
			     ComparisonType.EQUAL,
			     Float.parseFloat(String.valueOf(JTF_precio.getText()))
			     ,7));
			} else if (Mi_combo.getSelectedItem().toString()=="<") {
				  	filtros.add(RowFilter.numberFilter(
					ComparisonType.BEFORE,
					Float.parseFloat(String.valueOf(JTF_precio.getText()))
					,7));
				}else if (Mi_combo.getSelectedItem().toString()==">") {
				  	filtros.add(RowFilter.numberFilter(
					ComparisonType.AFTER,
					Float.parseFloat(String.valueOf(JTF_precio.getText()))
					,7));
				}
			}   
			else {
			    filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",7));
		
			   }
			
		
		RowFilter<TableModel, Integer> filtroAnd = RowFilter.andFilter(filtros);
		modeloOrdenado.setRowFilter(filtroAnd);
		
	}
	
	/**
	 * Refresca el label de control de registros
	 */
	private void refrescaReg() {
		String p="Registro " + TBCli.getSelectedRow()+1 + " de "+ TBCli.getRowCount()+".";
		LBRegistros.setText(p);	
	}
	
	/*
	 * Implementa keyEvents
	 */
	public class MyKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent arg0) {
			int ascii = (int) arg0.getKeyChar();
			System.out.println(ascii);
			switch (arg0.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					salir();
					break;	
			}
			
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
		}
	
		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

	/*
	 * salir de la vista
	 */
	public void salir() {
		frame.dispose();
		MenuVentasView miMenuVentas = new MenuVentasView(miuser);
		miMenuVentas.getFrame().setAlwaysOnTop(true);
		miMenuVentas.getFrame().setVisible(true);
	}
	

}
