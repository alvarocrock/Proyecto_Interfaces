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
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
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
import DAO.VehiculosDAO;
import Models.Clientes;
import Models.Usuarios;
import Models.Vehiculos;
import net.miginfocom.swing.MigLayout;

public class ConsVeh {

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
	private VehiculosDAO miVehDAO;
	private JLabel LBMatricula;
	private JTextField TFMatricula;
	private JPanel PNBusquedas;
	private JScrollPane scrollPane;
	private JTable TBVeh;
	private DefaultTableModel modeloTBVeh;
	private JLabel LBModelo;
	private JTextField TFModelo;
	private JLabel LBMarca;
	private JTextField TFMarca;
	private JPanel PNMedio;
	private JPanel PNMenu;
	private JPanel PNImg;
	private JPanel PNTitUsu;
	private JLabel LBImg;
	private TableRowSorter<TableModel> modeloOrdenado;
	private JLabel LBNomUsu;
	private JLabel LBCliente;
	private JTextField TFCliente;
	
	/**
	 * Constructor
	 */
	public ConsVeh(Usuarios miuser) {
		usuario=miuser;
		miVehDAO = new VehiculosDAO ();
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
			
			JLabel LBTitulo = new JLabel("Consulta de vehículos");
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
			
			LBMatricula = new JLabel("Matrícula");
			PNBusquedas.add(LBMatricula);
			
			TFMatricula = new JTextField();
			PNBusquedas.add(TFMatricula);
			TFMatricula.setColumns(10);
			TFMatricula.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(java.awt.event.KeyEvent arg0) {
				}
				@Override
				public void keyReleased(java.awt.event.KeyEvent arg0) {
				}
				@Override
				public void keyTyped(java.awt.event.KeyEvent arg0) {
					addFiltros();			
				}
			});
			
			LBMarca = new JLabel("Marca");
			PNBusquedas.add(LBMarca);
			
			TFMarca = new JTextField();
			TFMarca.setColumns(10);
			PNBusquedas.add(TFMarca);
			TFMarca.addKeyListener(new KeyListener() {
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
			
			LBModelo = new JLabel("Modelo");
			PNBusquedas.add(LBModelo);
			
			TFModelo = new JTextField();
			TFModelo.setColumns(10);
			PNBusquedas.add(TFModelo);
			TFModelo.addKeyListener(new KeyListener() {
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
			
			LBCliente = new JLabel("Cliente");
			PNBusquedas.add(LBCliente);
			
			TFCliente = new JTextField();
			TFCliente.setColumns(10);
			TFCliente.addKeyListener(new KeyListener() {
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
			PNBusquedas.add(TFCliente);
			
			// panel para la tabla
			scrollPane = new JScrollPane();
			PNCentral.add(scrollPane);
			
			// tabla
			TBVeh = new JTable();
			TBVeh.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(TBVeh);
			
			// crea modelo de la tabla
			miVehDAO = new VehiculosDAO();
			modeloTBVeh = new DefaultTableModel(){				
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
			modeloTBVeh.addColumn("Id");
			modeloTBVeh.addColumn("Matrícula");
			modeloTBVeh.addColumn("Marca");
			modeloTBVeh.addColumn("Modelo");
			modeloTBVeh.addColumn("IdCliente");
			modeloTBVeh.addColumn("Cliente");
			modeloTBVeh.addColumn("IdConce");
			modeloTBVeh.addColumn("Concesionario");
			modeloTBVeh.addColumn("Precio");

			//añade el modelo a la tabla
			TBVeh.setModel(modeloTBVeh);
			
			// hace ordenable la tabla
			modeloOrdenado = new TableRowSorter<TableModel>(modeloTBVeh);
			TBVeh.setRowSorter(modeloOrdenado);
			
			// carga datos de vehículos en la tabla
			cargaVeh(miVehDAO.cargaListaDAO());	
			
			// evento doble click de la tabla
			TBVeh.addMouseListener(new MouseAdapter(){
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
					if (TBVeh.getSelectedRow()>-1) {
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
					TBVeh.setRowSelectionInterval(0, 0);
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
					if (TBVeh.getSelectedRow()>0) 
						TBVeh.setRowSelectionInterval(TBVeh.getSelectedRow()-1,TBVeh.getSelectedRow()-1);
					refrescaReg();
				}
			});
			
			LBRegistros = new JLabel(" No se han encontrado registros.");
			LBRegistros.setBackground(Color.WHITE);
			LBRegistros.setBorder(new LineBorder(Color.BLUE, 1, true));
			
			panelRegistros.add(BTSiguiente = new JButton(">"));
			BTSiguiente.setToolTipText("Registro siguiente.");
			BTSiguiente.setForeground(Color.RED);
			BTSiguiente.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTSiguiente);
			BTSiguiente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (TBVeh.getSelectedRow()<TBVeh.getRowCount()) 
						TBVeh.setRowSelectionInterval(TBVeh.getSelectedRow()+1,TBVeh.getSelectedRow()+1);
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
					TBVeh.setRowSelectionInterval(TBVeh.getRowCount()-1,TBVeh.getRowCount()-1);
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
		
		if (TFMarca.getText().length()>0) {
			filtros.add(RowFilter.regexFilter(TFMarca.getText(),1));
		} else {
			filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",1));
		}
		
		if (TFModelo.getText().length()>0) {
			filtros.add((RowFilter.regexFilter(TFModelo.getText(),2)));
		} else {
			modeloOrdenado.setRowFilter(RowFilter.regexFilter("[a-zA-Z0-9_]",2));
		}
		
		if (TFCliente.getText().length()>0) {
			filtros.add(RowFilter.regexFilter(TFCliente.getText(),3));
		} else {
			filtros.add(RowFilter.regexFilter("[a-zA-Z0-9_]",3));
		}
		
		RowFilter<TableModel, Integer> filtroAnd = RowFilter.andFilter(filtros);
		modeloOrdenado.setRowFilter(filtroAnd);
		
	}
	protected void borraTabla() {

		while (modeloTBVeh.getRowCount()>0) {
			modeloTBVeh.removeRow(modeloTBVeh.getRowCount()-1);
		}
	}
	/**
	 * Carga la tabla conlos clientes de la base de datos
	 * @param miArray 
	 */

	protected void cargaVeh(ArrayList<Vehiculos> miArray) {
		Object [] fila = new Object[9];
		Clientes miCli;
		ClientesDAO miCliDAO=new ClientesDAO();

		for (int i=0;i<miArray.size();i++) {
			fila[0]= (int) miArray.get(i).getidVeh();
			fila[1]= miArray.get(i).getMatricula();
			fila[2]=miArray.get(i).getMarca();
			fila[3]=miArray.get(i).getModelo();
			fila[4]=miArray.get(i).getId_cli();
			// Aqui hay que cargar nombre de cliente
			fila[5]=miCliDAO.goToIdCli(miArray.get(i).getId_cli()).getNombre() 
					+ " " + miCliDAO.goToIdCli(miArray.get(i).getId_cli()).getApellido();
			fila[6]=miArray.get(i).getId_conce();
			// aqui hay que sacar nombre del concesionario
			//fila[7]=miArray.get(i).getId_conce();
			fila[8]=miArray.get(i).getPrecio();
			modeloTBVeh.addRow(fila);
		}

		
	}
	
	/**
	 * llama a ficha de clientes con el  cliente seleccionado
	 */
	protected void seleccionar() {
		// coger id_cli de la tabla

		int idVeh=(int) TBVeh.getModel().getValueAt(TBVeh.getSelectedRow(),0);
		// llamada a ficha vehículo con el idVeh
		FichaVehiculoView miFicVeh = new FichaVehiculoView(usuario,idVeh);
		miFicVeh.getFrame().setAlwaysOnTop(true);
		miFicVeh.getFrame().setVisible(true);
	}
	
	/**
	 * Refresca el label de control de registros
	 */
	private void refrescaReg() {
		
		String p="Registro " + TBVeh.getSelectedRow()+1 + " de "+ TBVeh.getRowCount()+".";
		LBRegistros.setText(p);	
	}
	/*
	 * Get Frame
	 */
	public Window getFrame() {
		return frame;
	}


}


