package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.ClientesDAO;
import DAO.TrabajoHoyDAO;
import GUI.BusCliView.MyKeyListener;
import Models.Clientes;
import Models.Reparacion;
import Models.Usuarios;
import javax.swing.JComboBox;

public class BuscarTrabajoView {

	
	private JFrame frame;
	private static final long serialVersionUID = 1L;
	private Usuarios usuario;
	private JLabel LBUsuario;
	private JLabel LBRegistros;
	private JButton BTSeleccionar;
	private JButton BTSalir;
	private JButton BTPrimero;
	private JButton BTAnterior;
	private JButton BTSiguiente;
	private JButton BTultimo;
	private TrabajoHoyDAO contro;
	private JPanel PNBusquedas;
	private JScrollPane scrollPane;
	private JTable TBCli;
	private DefaultTableModel modeloTBCli;
	private JPanel PNMedio;
	private JPanel PNImg;
	private JPanel PNTitUsu;
	private JLabel LBImg;
	private TableRowSorter<TableModel> modeloOrdenado;
	private JLabel LBNomUsu;
	private JPanel Linea_1;
	private JLabel JLB_id;
	private JTextField JTF_id;
	private JLabel JLB_nom_cli;
	private JTextField JTF_nomcli;
	private JLabel JLB_ape_cli;
	private JTextField JTF_ape_cli;
	private JLabel JLB_encargado;
	private JTextField JTF_mec;
	private JPanel linea_2;
	private JLabel JLB_fecha_crea;
	private JTextField JTF_fecha_crea;
	private JLabel JLB_mat;
	private JTextField JTF_matricula;
	private JLabel JLB_fecha_ini;
	private JTextField JTF_fecha_ini;
	private JLabel JLB_fecha_fn;
	private JTextField JTF_fecha_fn;
	private JPanel linea_3;
	private JLabel JLB_tiempo_total;
	private JTextField JTF_T_Total;
	private JComboBox JCB_T_Total;
	private JLabel JLB_presupuesto;
	private JTextField JTF_precio;
	private JComboBox JCB_precio;
	private JLabel lblNewLabel;
	private JTextField JTF_jefe;
	/**
	 * Create the application.
	 */
	public BuscarTrabajoView(Usuarios user) {
		usuario=user;
		contro= new TrabajoHoyDAO();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame principal
				frame = new JFrame();
				frame.setAlwaysOnTop(true);
				frame.setBounds(100, 100, 1200, 600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
				
				// implementar las teclas en el frame
				KeyListener listener = new MyKeyListener();
				frame.addKeyListener(listener);
				frame.setFocusable(true);
				frame.requestFocus();
				
				// panel título
				JPanel PNTitulo = new JPanel();
				PNTitulo.setForeground(Color.BLUE);
				PNTitulo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				PNTitulo.setBackground(Color.LIGHT_GRAY);
				frame.getContentPane().add(PNTitulo, "cell 0 0,growx,aligny top");
				PNTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				PNTitulo.setBackground(Color.decode("#264653"));
				
				JLabel LBTitulo = new JLabel("Consulta de clientes");
				LBTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
				LBTitulo.setForeground(Color.ORANGE);
				PNTitulo.add(LBTitulo);
				
				// Panel medio
				PNMedio = new JPanel();
				frame.getContentPane().add(PNMedio);
				PNMedio.setLayout(new BoxLayout(PNMedio, BoxLayout.X_AXIS));
				PNMedio.setBackground(Color.decode("#2A9D8F"));
				
				// Panel Usuario
				JPanel PNUsuario = new JPanel();
				PNMedio.add(PNUsuario);
				PNUsuario.setLayout(new BoxLayout(PNUsuario,BoxLayout.Y_AXIS));
				PNUsuario.setBackground(Color.decode("#2A9D8F"));
				
				// panel titulo usuario
				PNTitUsu = new JPanel();
				PNTitUsu.setBackground(Color.decode("#2A9D8F"));
				PNUsuario.add(PNTitUsu);
				PNTitUsu.setLayout(new BoxLayout(PNTitUsu, BoxLayout.X_AXIS));
				
				LBUsuario = new JLabel("Usuario Actual");
				LBUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
				PNTitUsu.add(LBUsuario);
				LBUsuario.setForeground(Color.ORANGE);
				
				// panel imagen
				PNImg = new JPanel();
				PNImg.setBackground(Color.decode("#2A9D8F"));
				PNUsuario.add(PNImg);
				 PNImg.setLayout(new BoxLayout(PNImg, BoxLayout.Y_AXIS));
				// carga imagen
		         try {
					 BufferedImage img = ImageIO.read(new File(usuario.getFoto()));
					 ImageIcon icon = new ImageIcon(img);
					 LBImg = new JLabel(icon);
					 PNUsuario.add(LBImg);
		          } catch (IOException e) {
		             e.printStackTrace();
		          }
				
				LBNomUsu = new JLabel("Nombre Usuario");
				PNUsuario.add(LBNomUsu);
	            LBNomUsu.setAlignmentX(0.5f);            
	            LBNomUsu.setText(usuario.getNick());
		//****************
	    		// Menú lateral
	    		JPanel PNMenu = new JPanel();
	    		PNMenu.setBackground(Color.decode("#2A9D8F"));
	    		PNUsuario.add(PNMenu);
	    		PNMenu.setLayout(new BoxLayout(PNMenu, BoxLayout.Y_AXIS));
	    		
	            
	    		
	    		
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

		//***************		
				
				// panel central
				JPanel PNCentral = new JPanel();
				PNMedio.add(PNCentral);
				PNCentral.setBackground(Color.decode("#2A9D8F"));
				PNCentral.setLayout(new BoxLayout(PNCentral, BoxLayout.Y_AXIS));
				
				// panel busquedas
				PNBusquedas = new JPanel();
				PNCentral.add(PNBusquedas);
				PNBusquedas.setLayout(new BoxLayout(PNBusquedas, BoxLayout.Y_AXIS));
				
				Linea_1 = new JPanel();
				Linea_1.setBackground(Color.decode("#2A9D8F"));
				PNBusquedas.add(Linea_1);
				
				JLB_id = new JLabel("id");
				Linea_1.add(JLB_id);
				
				JTF_id = new JTextField();
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
				Linea_1.add(JTF_id);
				
				JLB_nom_cli = new JLabel("Nombre cliente");
				Linea_1.add(JLB_nom_cli);
				
				JTF_nomcli = new JTextField();
				JTF_nomcli.setColumns(10);
				JTF_nomcli.addKeyListener(new KeyListener() {
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
				Linea_1.add(JTF_nomcli);
				
				JLB_ape_cli = new JLabel("Apellido Cliente");
				Linea_1.add(JLB_ape_cli);
				
				JTF_ape_cli = new JTextField();
				JTF_ape_cli.setColumns(10);
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
				Linea_1.add(JTF_ape_cli);
				
				JLB_encargado = new JLabel("Encargado");
				Linea_1.add(JLB_encargado);
				
				JTF_mec = new JTextField();
				JTF_mec.setColumns(10);
				JTF_mec.addKeyListener(new KeyListener() {
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
				Linea_1.add(JTF_mec);
				
				JLB_mat = new JLabel("Matricula");
				Linea_1.add(JLB_mat);
				
				JTF_matricula = new JTextField();
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
				Linea_1.add(JTF_matricula);
				JTF_matricula.setColumns(10);
				
				lblNewLabel = new JLabel("Jefe");
				Linea_1.add(lblNewLabel);
				
				JTF_jefe = new JTextField();
				Linea_1.add(JTF_jefe);
				JTF_jefe.addKeyListener(new KeyListener() {
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
				JTF_jefe.setColumns(10);
				
				linea_2 = new JPanel();
				linea_2.setBackground(Color.decode("#2A9D8F"));
				PNBusquedas.add(linea_2);
				
				JLB_fecha_crea = new JLabel("Fecha creacion");
				linea_2.add(JLB_fecha_crea);
				
				JTF_fecha_crea = new JTextField();
				JTF_fecha_crea.addKeyListener(new KeyListener() {
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
				linea_2.add(JTF_fecha_crea);
				JTF_fecha_crea.setColumns(10);
				
				JLB_fecha_ini = new JLabel("Fecha inicio");
				linea_2.add(JLB_fecha_ini);
				
				JTF_fecha_ini = new JTextField();
				JTF_fecha_ini.addKeyListener(new KeyListener() {
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
				linea_2.add(JTF_fecha_ini);
				JTF_fecha_ini.setColumns(10);
				
				JLB_fecha_fn = new JLabel("Fecha fin");
				linea_2.add(JLB_fecha_fn);
				
				JTF_fecha_fn = new JTextField();
				JTF_fecha_fn.addKeyListener(new KeyListener() {
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
				linea_2.add(JTF_fecha_fn);
				JTF_fecha_fn.setColumns(10);
				
				linea_3 = new JPanel();
				linea_3.setBackground(Color.decode("#2A9D8F"));
				PNBusquedas.add(linea_3);
				
				JLB_tiempo_total = new JLabel("Tiempo total");
				linea_3.add(JLB_tiempo_total);
				
				JTF_T_Total = new JTextField();
				JTF_T_Total.addKeyListener(new KeyListener() {
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
				linea_3.add(JTF_T_Total);
				JTF_T_Total.setColumns(10);
				
				JCB_T_Total = new JComboBox();
				//a�adimos elementos al combobox
				JCB_T_Total.addItem("=");
				JCB_T_Total.addItem(">");
				JCB_T_Total.addItem("<");
				linea_3.add(JCB_T_Total);
				
				JLB_presupuesto = new JLabel("Precio");
				linea_3.add(JLB_presupuesto);
				
				JTF_precio = new JTextField();
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
				linea_3.add(JTF_precio);
				JTF_precio.setColumns(10);
				
				JCB_precio = new JComboBox();
				//a�adimos elementos al combobox
				JCB_precio.addItem("=");
				JCB_precio.addItem(">");
				JCB_precio.addItem("<");
				linea_3.add(JCB_precio);
				
				// panel para la tabla
				scrollPane = new JScrollPane();
				PNCentral.add(scrollPane);
				
				// tabla
				TBCli = new JTable();
				TBCli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(TBCli);
				
				// crea modelo de la tabla
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
				modeloTBCli.addColumn("ID");
				modeloTBCli.addColumn("Nombre Cliente");
				modeloTBCli.addColumn("Apellido Cliente");
				modeloTBCli.addColumn("Jefe Taller");
				modeloTBCli.addColumn("Mecanico encargado");
				modeloTBCli.addColumn("Descripci�n");
				modeloTBCli.addColumn("Fecha creacion");
				modeloTBCli.addColumn("Tiempo total");
				modeloTBCli.addColumn("Matricula");
				modeloTBCli.addColumn("Precio estimado");
				modeloTBCli.addColumn("Fecha inicio");
				modeloTBCli.addColumn("Fecha fin");
				
				//añade el modelo a la tabla
				TBCli.setModel(modeloTBCli);
				
				// hace ordenable la tabla
				modeloOrdenado = new TableRowSorter<TableModel>(modeloTBCli);
				TBCli.setRowSorter(modeloOrdenado);
				
				// carga datos de clientes en la tabla
				cargaTrabajo(contro.cargaListaDAO());	
				
				// evento doble click de la tabla
				TBCli.addMouseListener(new MouseAdapter(){
					public void mouseReleased(MouseEvent e){
					if(e.getClickCount()==2){
						frame.dispose();
						seleccionar();
					}
					}
					});
												
				// panel para los botones de la botonera
				JPanel panelBotonera = new JPanel();
				PNCentral.add(panelBotonera);
				
				BTSeleccionar = new JButton("Seleccionar");
				panelBotonera.add(BTSeleccionar);
				BTSeleccionar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (TBCli.getSelectedRow()>-1) {
							frame.dispose();
							seleccionar();
						} else {
							JOptionPane.showMessageDialog(frame, "No ha seleccionado ninguna fila");
						}
		
					}
				});
				
				BTSalir = new JButton("Salir");
				panelBotonera.add(BTSalir);
				BTSalir.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						salir();
					}
				});
				
				// panel registros
				JPanel panelRegistros = new JPanel();
				panelRegistros.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
				PNCentral.add(panelRegistros);
				
				BTPrimero = new JButton("<<");
				BTPrimero.setToolTipText("Primer registro.");
				BTPrimero.setForeground(Color.RED);
				BTPrimero.setFont(new Font("Tahoma", Font.BOLD, 8));
				panelRegistros.add(BTPrimero);
				BTPrimero.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						TBCli.setRowSelectionInterval(0, 0);
						refrescaReg();
					}
				});
				
				BTAnterior = new JButton("<");
				BTAnterior.setToolTipText("Registro anterior.");
				BTAnterior.setForeground(Color.RED);
				BTAnterior.setFont(new Font("Tahoma", Font.BOLD, 8));
				panelRegistros.add(BTAnterior);
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
				panelRegistros.add(LBRegistros);
				
				BTSiguiente = new JButton(">");
				BTSiguiente.setToolTipText("Registro siguiente.");
				BTSiguiente.setForeground(Color.RED);
				BTSiguiente.setFont(new Font("Tahoma", Font.BOLD, 8));
				panelRegistros.add(BTSiguiente);
				BTSiguiente.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (TBCli.getSelectedRow()<TBCli.getRowCount()-1) 
							TBCli.setRowSelectionInterval(TBCli.getSelectedRow()+1,TBCli.getSelectedRow()+1);
						refrescaReg();
					}
				});
				
				BTultimo = new JButton(">>");
				BTultimo.setToolTipText("\u00DAltimo registro.");
				BTultimo.setForeground(Color.RED);
				BTultimo.setFont(new Font("Tahoma", Font.BOLD, 8));
				panelRegistros.add(BTultimo);
				BTultimo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (TBCli.getSelectedRow()<TBCli.getRowCount()-1)
							TBCli.setRowSelectionInterval(TBCli.getRowCount()-1,TBCli.getRowCount()-1);
						refrescaReg();
					}
				});
				
				// Panel para los botones del control de registros
				JPanel panelBotoneras = new JPanel();
				panelBotoneras.setMaximumSize(new Dimension(1000, 60));
				panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
				PNCentral.add(panelBotoneras);
				panelBotonera.setBackground(Color.decode("#2A9D8F"));
				panelBotoneras.setBackground(Color.decode("#2A9D8F"));
				
				//frame.pack();

			}
		
		/**
		 * Salir de la vista
		 */
		protected void salir() {
			if (usuario.getRango().equals("jefeTaller")) {
				MenuJTallerView view= new MenuJTallerView(usuario);
				view.getFrame().setVisible(true);
				view.getFrame().setAlwaysOnTop(true);
				frame.dispose();
			} else {
			frame.dispose();
				MenuMecanicoView miMenuVentas = new MenuMecanicoView(usuario);
				miMenuVentas.getFrame().setAlwaysOnTop(true);
				miMenuVentas.getFrame().setVisible(true);
			
			}		
		}

		/**
		 * Carga la tabla conlos clientes de la base de datos
		 * @param miArray 
		 */
		protected void cargaTrabajo(ArrayList<Reparacion> miarray) {
			Object [] fila = new Object[12];

			for (int i=0;i<miarray.size();i++) {
				fila[0]=miarray.get(i).getId();
				fila[1]=contro.getnombre(miarray.get(i).getId_cli());
				fila[2]=contro.getapellido(miarray.get(i).getId_cli());
				fila[3]=contro.getnick(miarray.get(i).getId_jefe());
				fila[4]=contro.getnick(miarray.get(i).getId_mec());
				fila[5]=miarray.get(i).getDesc();
				fila[6]=miarray.get(i).getFecha_repara();
				if (miarray.get(i).getTiempo()!=null) {
				fila[7]=miarray.get(i).getTiempo();
				}
				fila[8]=contro.getmatricula(miarray.get(i).getId_veh());
				fila[9]=miarray.get(i).getPrecio();
				fila[10]=miarray.get(i).getFecha_ini();
				fila[11]=miarray.get(i).getFecha_ini();
				modeloTBCli.addRow(fila);
			}

		}
		
		/**
		 * llama a ficha de clientes con el  cliente seleccionado
		 */
		protected void seleccionar() {
			// coger id_cli de la tabla
			if (usuario.getRango().equals("jefeTaller")) {
			int id=(int) TBCli.getModel().getValueAt(TBCli.getSelectedRow(),0);
			// llamada a ficha clientes con el idcli
			FichaTrabajo ficha = new FichaTrabajo(usuario,id);
			ficha.getFrame().setAlwaysOnTop(true);
			ficha.getFrame().setVisible(true);
			} else {
				
			}
		}
		
		/**
		 * Refresca el label de control de registros
		 */
		private void refrescaReg() {
			String p="Registro " + (TBCli.getSelectedRow()+1) + " de "+ TBCli.getRowCount()+".";
			LBRegistros.setText(p);	
		}
		/*
		 * Get Frame
		 */
		public Window getFrame() {
			return frame;
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
		
		protected void addFiltros(KeyEvent e) {
			ArrayList <RowFilter<TableModel,Integer>> filtros = new ArrayList <RowFilter<TableModel,Integer>>();
			int ascii = e.getKeyChar();
			//filtro para el id
			if ((JTF_id.getText().length()>0) && (ascii>47) && (ascii<58)) {
				   filtros.add(RowFilter.numberFilter(
				     ComparisonType.EQUAL,
				     Integer.parseInt(String.valueOf(JTF_id.getText()))
				     ,0)); 
				   } else {
				    filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",0));
				   }
			//filtro nombre lciente
			if (JTF_nomcli.getText().length()>0) {
				filtros.add(RowFilter.regexFilter(JTF_nomcli.getText(),1));
			} else {
				filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",1));
			}
			
			//filtro apellido cliente
			if (JTF_ape_cli.getText().length()>0) {
				filtros.add(RowFilter.regexFilter(JTF_ape_cli.getText(),2));
			} else {
				filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",2));
			}
			
			//filtro jefe
			if (JTF_jefe.getText().length()>0) {
				filtros.add(RowFilter.regexFilter(JTF_jefe.getText(),3));
			} else {
				filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",3));
			}
			
			//filtro mecanico encargado
			
			if (JTF_mec.getText().length()>0) {
				filtros.add(RowFilter.regexFilter(JTF_mec.getText(),4));
			} else {
				filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",4));
			}
			
			//filtro fecha creacion
			if (JTF_fecha_crea.getText().length()>0) {
				filtros.add(RowFilter.regexFilter(JTF_fecha_crea.getText(),6));
			} else {
				filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",6));
			}
			
			//filtro fecha_creacion
			if (JTF_fecha_crea.getText().length()>0) {
				filtros.add(RowFilter.regexFilter(JTF_fecha_crea.getText(),6));
			} else {
				filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",6));
			}
			//filtro tiempo total
			if ((JTF_T_Total.getText().length()>0)) {
				if (JCB_T_Total.getSelectedItem().toString()=="=") {
				   filtros.add(RowFilter.numberFilter(
				     ComparisonType.EQUAL,
				     Float.parseFloat(String.valueOf(JTF_T_Total.getText()))
				     ,7));
				} else if (JCB_T_Total.getSelectedItem().toString()=="<") {
					  	filtros.add(RowFilter.numberFilter(
						ComparisonType.BEFORE,
						Float.parseFloat(String.valueOf(JTF_T_Total.getText()))
						,7));
					}else if (JCB_T_Total.getSelectedItem().toString()==">") {
					  	filtros.add(RowFilter.numberFilter(
						ComparisonType.AFTER,
						Float.parseFloat(String.valueOf(JTF_T_Total.getText()))
						,7));
					}
				} 
			//filtro matricula
			if (JTF_matricula.getText().length()>0) {
				filtros.add(RowFilter.regexFilter(JTF_matricula.getText(),8));
			} else {
				filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",8));
			}
			
			//filtro precio
			if ((JTF_precio.getText().length()>0)) {
				if (JCB_precio.getSelectedItem().toString()=="=") {
				   filtros.add(RowFilter.numberFilter(
				     ComparisonType.EQUAL,
				     Float.parseFloat(String.valueOf(JTF_precio.getText()))
				     ,9));
				} else if (JCB_precio.getSelectedItem().toString()=="<") {
					  	filtros.add(RowFilter.numberFilter(
						ComparisonType.BEFORE,
						Float.parseFloat(String.valueOf(JTF_precio.getText()))
						,9));
					}else if (JCB_precio.getSelectedItem().toString()==">") {
					  	filtros.add(RowFilter.numberFilter(
						ComparisonType.AFTER,
						Float.parseFloat(String.valueOf(JTF_precio.getText()))
						,9));
					}
				}
			//filtro fecha ini
			if (JTF_fecha_ini.getText().length()>0) {
				filtros.add(RowFilter.regexFilter(JTF_fecha_ini.getText(),10));
			} else {
				filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",10));
			}
			
			
			//filtro fecha fn
			
			if (JTF_fecha_fn.getText().length()>0) {
				filtros.add(RowFilter.regexFilter(JTF_fecha_fn.getText(),11));
			} else {
				filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",11));
			}
			
			
			RowFilter<TableModel, Integer> filtroAnd = RowFilter.andFilter(filtros);
			modeloOrdenado.setRowFilter(filtroAnd);
			}
		
			
			

}
