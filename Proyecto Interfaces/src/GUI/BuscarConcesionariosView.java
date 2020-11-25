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

import DAO.ConcesionarioDAO;
import GUI.VerVentasView.MyKeyListener;
import Models.Concesionario;
import Models.Usuarios;
import Models.Ventas;

public class BuscarConcesionariosView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Usuarios miUser;
	private ConcesionarioDAO contro;
	private JTable TBCli;
	private JScrollPane scrollPane;
	private DefaultTableModel modeloTBCli;
	private TableRowSorter<TableModel> modeloOrdenado;
	private JTextField textField_1;
	private JTextField JTF_nombre;
	private JLabel LBRegistros;
	private JTextField JTF_id;
	
	public BuscarConcesionariosView(Usuarios user) {
		contro=new ConcesionarioDAO();
		miUser=user;
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
		
		
		// Menú lateral
		JPanel PNMenu = new JPanel();
		PNMenu.setBackground(Color.decode("#2A9D8F"));
		panel_principal.add(PNMenu);
		PNMenu.setLayout(new BoxLayout(PNMenu, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Usuario actual");
		PNMenu.add(lblNewLabel);
		
		
		ImageIcon imagenuser = new ImageIcon(miUser.getFoto());
		JLabel JLB_image_user = new JLabel(imagenuser);
		PNMenu.add(JLB_image_user);
		
		JLabel JLB_USER_actual = new JLabel("ACTUAL");
		PNMenu.add(JLB_USER_actual);
		JLB_USER_actual.setText(miUser.getNick());
		
		
		JLabel JLB_buc_ventas = new JLabel("Buscar ventas");
		JLB_buc_ventas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VerVentasView miventa = new VerVentasView(miUser);
				miventa.getFrame().setVisible(true);
				miventa.getFrame().setAlwaysOnTop(true);
				frame.dispose();
			}
			@Override
			public void mouseEntered (MouseEvent e) {
				JLB_buc_ventas.setForeground(Color.RED);
			}
			@Override
			public void mouseExited (MouseEvent e) {
				JLB_buc_ventas.setForeground(Color.ORANGE);
			}
		});
		JLB_buc_ventas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLB_buc_ventas.setForeground(Color.decode("#E9C46A"));
		PNMenu.add(JLB_buc_ventas);
		
		JLabel JLB_buscar_cli = new JLabel("Buscar Clientes");
		JLB_buscar_cli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BusCliView miBuscCli = new BusCliView(frame, miUser,0);
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
		PNMenu.add(JLB_buscar_cli);
		
		JLabel JLB_ficha_cliente = new JLabel("Ficha Cliente");
		JLB_ficha_cliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FichaClienteView miFichaClientes = new FichaClienteView(miUser,0);
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
		PNMenu.add(JLB_ficha_cliente);
		
		JLabel Busca_vehiculos = new JLabel("Busca Vehiculos");
		Busca_vehiculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConsVeh busqueda= new ConsVeh(frame,miUser,0);
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
		PNMenu.add(Busca_vehiculos);
		
		JLabel JLB_ficha_vehiculo = new JLabel("Ficha Vehiculo");
		JLB_ficha_vehiculo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FichaVehiculoView miFicVeh = new FichaVehiculoView (miUser,0);
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
		PNMenu.add(JLB_ficha_vehiculo);
		
		
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
		PNMenu.add(JLB_cerrar_sesion);
		
		
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
		
		JLabel ID_conce = new JLabel("ID Concesionario");
		panel_lin_1.add(ID_conce);
		
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
		
		JLabel JLB_nombre = new JLabel("Nombre");
		panel_lin_1.add(JLB_nombre);
		
		JTF_nombre = new JTextField();
		JTF_nombre.setColumns(10);
		panel_lin_1.add(JTF_nombre);
		JTF_nombre.addKeyListener(new KeyListener() {
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
		modeloTBCli.addColumn("Nombre");
		//añade el modelo a la tabla
		TBCli.setModel(modeloTBCli);
					
		// hace ordenable la tabla
		modeloOrdenado = new TableRowSorter<TableModel>(modeloTBCli);
		TBCli.setRowSorter(modeloOrdenado);
		
		textField_1 = new JTextField();
		scrollPane.setColumnHeaderView(textField_1);
		textField_1.setColumns(10);
		
		cargarConce(contro.getconce());
				
		// doble click de la tabla
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
	
	/**
	 * Carga los concesionarios en la tabla
	 * @param lista
	 */
	public void cargarConce(ArrayList<Concesionario> lista) {
		Object [] fila = new Object[2];

		for (int i=0;i<lista.size();i++) {
			fila[0]=(int) lista.get(i).getId();
			fila[1]=lista.get(i).getNombre();
			modeloTBCli.addRow(fila);
		}
		
	}
	
	/**
	 * Getter del frame
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Llama a la ficha de concesionario con el Id seleccionado
	 */
	protected void seleccionar() {
		// coger id_conce de la tabla
		int id_Conce=(int) TBCli.getModel().getValueAt(TBCli.getSelectedRow(),0);
		// llamada a ficha concesionario
		FichaConce miFicConce = new FichaConce(miUser,id_Conce);
		miFicConce.getFrame().setAlwaysOnTop(true);
		miFicConce.getFrame().setVisible(true);
	}
	
	/**
	 * Carga los filtros	
	 * @param e
	 */
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
		if (JTF_nombre.getText().length()>0) {
			filtros.add(RowFilter.regexFilter(JTF_nombre.getText(),1));
		} else {
			filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",1));
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
	 * Implementa keyEvents en el frame
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
		MenuVentasView miMenuVentas = new MenuVentasView(miUser);
		miMenuVentas.getFrame().setAlwaysOnTop(true);
		miMenuVentas.getFrame().setVisible(true);
	}

}
