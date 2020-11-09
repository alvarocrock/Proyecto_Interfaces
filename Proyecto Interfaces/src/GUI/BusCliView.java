package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.sun.glass.events.KeyEvent;

import DAO.ClientesDAO;
import Models.Clientes;
import Models.Usuarios;
import javax.swing.JOptionPane;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Component;

public class BusCliView {
	
	private JFrame frame;
	private Usuarios usuario;
	private JLabel LBUsuario;
	private JLabel LBRegistros;
	private JButton BTSeleccionar;
	private JButton BTSalir;
	private JButton BTPrimero;
	private JButton BTAnterior;
	private JButton BTSiguiente;
	private JButton BTultimo;
	private ClientesDAO miClienteDAO;
	private JLabel LBL_idbusc;
	private JTextField TFId;
	private JPanel PNBusquedas;
	private JScrollPane scrollPane;
	private JTable TBCli;
	private DefaultTableModel modeloTBCli;
	private JLabel LBNombre;
	private JTextField TFNombre;
	private JLabel LBDNI;
	private JTextField TFDNI;
	private JPanel PNMedio;
	private JPanel PNMenu;
	private JPanel PNImg;
	private JPanel PNTitUsu;
	private JLabel LBImg;
	private TableRowSorter<TableModel> modeloOrdenado;
	private JLabel LBNomUsu;
	private JLabel LBApellidos;
	private JTextField TFApellidos;
	
	/**
	 * Create the application.
	 */

	public BusCliView(Usuarios miuser) {
		usuario=miuser;
		miClienteDAO = new ClientesDAO ();
		initialize();

	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	// Frame principal
			frame = new JFrame();
			frame.setAlwaysOnTop(true);
			frame.setBounds(100, 100, 900, 400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
			
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
		

			
			// panel nombre de usuario
			PNMenu = new JPanel();
			PNUsuario.add(PNMenu);
			
			// panel central
			JPanel PNCentral = new JPanel();
			PNMedio.add(PNCentral);
			PNCentral.setBackground(Color.decode("#2A9D8F"));
			PNCentral.setLayout(new BoxLayout(PNCentral, BoxLayout.Y_AXIS));
			
			// panel busquedas
			PNBusquedas = new JPanel();
			PNCentral.add(PNBusquedas);
			PNBusquedas.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			LBL_idbusc = new JLabel("Id");
			PNBusquedas.add(LBL_idbusc);
			
			TFId = new JTextField();
			PNBusquedas.add(TFId);
			TFId.setColumns(10);
			TFId.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(java.awt.event.KeyEvent arg0) {
				}
				@Override
				public void keyReleased(java.awt.event.KeyEvent arg0) {
				}
				@Override
				public void keyTyped(java.awt.event.KeyEvent arg0) {
					// no hay texto en el TField
					if (TFId.getText().length()==0 ) {
						// la tecla pulsada es imprimible
						int asc = (int) arg0.getKeyChar();
						if(asc>=20) {
							modeloOrdenado.setRowFilter(RowFilter.numberFilter(
									ComparisonType.EQUAL,
									Integer.parseInt(String.valueOf(arg0.getKeyChar()).trim())
									,0));	
						} else {
							// la tecla pulsada NO es imprimible
							modeloOrdenado.setRowFilter(RowFilter.numberFilter(
									ComparisonType.NOT_EQUAL,
									0,0));

						}
					// hay texto en el TField
					} else {
						modeloOrdenado.setRowFilter(RowFilter.numberFilter(
								ComparisonType.EQUAL,
								Integer.parseInt(TFId.getText() + String.valueOf(arg0.getKeyChar()))
								,0));	
					}
				
				}
			});
			
			LBDNI = new JLabel("DNI");
			PNBusquedas.add(LBDNI);
			
			TFDNI = new JTextField();
			TFDNI.setColumns(10);
			PNBusquedas.add(TFDNI);
			TFDNI.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(java.awt.event.KeyEvent arg0) {
				}
				@Override
				public void keyReleased(java.awt.event.KeyEvent arg0) {
					addFiltros();
				}
				@Override
				public void keyTyped(java.awt.event.KeyEvent arg0) {
				}

			});
			
			LBNombre = new JLabel("Nombre");
			PNBusquedas.add(LBNombre);
			
			TFNombre = new JTextField();
			TFNombre.setColumns(10);
			PNBusquedas.add(TFNombre);
			TFNombre.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(java.awt.event.KeyEvent arg0) {
				}
				@Override
				public void keyReleased(java.awt.event.KeyEvent arg0) {
					addFiltros();
				}
				@Override
				public void keyTyped(java.awt.event.KeyEvent arg0) {
				}
			});
			
			LBApellidos = new JLabel("Apellidos");
			PNBusquedas.add(LBApellidos);
			
			TFApellidos = new JTextField();
			TFApellidos.setColumns(10);
			TFApellidos.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(java.awt.event.KeyEvent arg0) {
				}
				@Override
				public void keyReleased(java.awt.event.KeyEvent arg0) {
					addFiltros();
				}
				@Override
				public void keyTyped(java.awt.event.KeyEvent arg0) {
				}
			});
			
			// panel busquedas
			PNBusquedas.add(TFApellidos);
			
			// panel para la tabla
			scrollPane = new JScrollPane();
			PNCentral.add(scrollPane);
			
			// tabla
			TBCli = new JTable();
			TBCli.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(TBCli);
			
			// crea modelo de la tabla
			miClienteDAO = new ClientesDAO();
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
			modeloTBCli.addColumn("DNI");
			modeloTBCli.addColumn("Nombre");
			modeloTBCli.addColumn("Apellidos");
			modeloTBCli.addColumn("Dirección");
			//añade el modelo a la tabla
			TBCli.setModel(modeloTBCli);
			
			// hace ordenable la tabla
			modeloOrdenado = new TableRowSorter<TableModel>(modeloTBCli);
			TBCli.setRowSorter(modeloOrdenado);
			
			// carga datos de clientes en la tabla
			cargaCli(miClienteDAO.cargaListaDAO());	
			
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
					frame.dispose();
					MenuVentasView miMenuVentas = new MenuVentasView(usuario);
					miMenuVentas.getFrame().setAlwaysOnTop(true);
					miMenuVentas.getFrame().setVisible(true);
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
					if (TBCli.getSelectedRow()<TBCli.getRowCount()) 
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

		}

	protected void addFiltros() {
		ArrayList <RowFilter<TableModel,Integer>> filtros = new ArrayList <RowFilter<TableModel,Integer>>();
		
		if (TFDNI.getText().length()>0) {
			filtros.add(RowFilter.regexFilter(TFDNI.getText(),1));
		} else {
			filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",1));
		}
		
		if (TFNombre.getText().length()>0) {
			filtros.add((RowFilter.regexFilter(TFNombre.getText(),2)));
		} else {
			modeloOrdenado.setRowFilter(RowFilter.regexFilter("[a-zA-Z0-9_]",2));
		}
		
		if (TFApellidos.getText().length()>0) {
			filtros.add(RowFilter.regexFilter(TFApellidos.getText(),3));
		} else {
			filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",3));
		}
		
		RowFilter<TableModel, Integer> filtroAnd = RowFilter.andFilter(filtros);
		modeloOrdenado.setRowFilter(filtroAnd);
		
	}
	protected void borraTabla() {

		while (modeloTBCli.getRowCount()>0) {
			modeloTBCli.removeRow(modeloTBCli.getRowCount()-1);
		}
	}
	/**
	 * Carga la tabla conlos clientes de la base de datos
	 * @param miArray 
	 */

	protected void cargaCli(ArrayList<Clientes> miArray) {
		Object [] fila = new Object[5];

		for (int i=0;i<miArray.size();i++) {
			fila[0]=(int) miArray.get(i).getId();
			fila[1]=miArray.get(i).getDNI();
			fila[2]=miArray.get(i).getNombre();
			fila[3]=miArray.get(i).getApellido();
			fila[4]=miArray.get(i).getDireccion();
			modeloTBCli.addRow(fila);
		}

		
	}
	
	/**
	 * llama a ficha de clientes con el  cliente seleccionado
	 */
	protected void seleccionar() {
		// coger id_cli de la tabla

		int idCli=(int) TBCli.getModel().getValueAt(TBCli.getSelectedRow(),0);
		// llamada a menu ventas con el idcli
		FichaClienteView miMenuVentas = new FichaClienteView(usuario,idCli);
		miMenuVentas.getFrame().setAlwaysOnTop(true);
		miMenuVentas.getFrame().setVisible(true);
	}
	
	/**
	 * Refresca el label de control de registros
	 */
	private void refrescaReg() {
		
		String p="Registro " + TBCli.getSelectedRow()+1 + " de "+ TBCli.getRowCount()+".";
		LBRegistros.setText(p);	
	}
	/*
	 * Get Frame
	 */
	public Window getFrame() {
		return frame;
	}

}
