package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.Consumer;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DAO.ClientesDAO;
import DAO.VehiculosDAO;
import Models.Clientes;
import Models.Usuarios;
import Models.Vehiculos;
import net.miginfocom.swing.MigLayout;

public class FichaVehiculoView {

	private Usuarios usuario;
	private VehiculosDAO miVehDAO;
	private JLabel LBUsuario;
	private JLabel LBNomUsu;
	private JTextField TFMatricula;
	private JLabel LBRegistros;
	private JTextField TFBastidor;
	private JTextField TFMarca;
	private JTextField TFModelo;
	private JTextField TFPrecio;
	private JTextField TFIdCli;
	private JButton BTBuscar;
	private JButton BTBorra;
	private JButton BTGuardar;
	private JButton BTNuevo;
	private JButton BTSalir;
	private JButton BTPrimero;
	private JButton BTAnterior;
	private JButton BTSiguiente;
	private JButton BTultimo;
	private JFrame frame;
	private JTextField TFConce;


	/**
	 * Constructor con usuario
	*/
	  public FichaVehiculoView(Usuarios miuser) {
	
		usuario=miuser;
		miVehDAO = new VehiculosDAO();
		initialize();
		// carga usuario
		LBUsuario.setText(usuario.getNick());
		LBNomUsu.setText(usuario.getRango());
		// carga registro
		cargaVehiculo(miVehDAO.primero());
		// refresca LBRegistros
		refrescaReg();
<<<<<<< HEAD
	}
=======

	}
	*/
>>>>>>> branch 'master' of https://github.com/alvarocrock/Proyecto_Interfaces
	
	/**
	 * Constructor con usuario e id de cliente
	 * @param miuser
	 * @param idVeh
	 */
	public FichaVehiculoView(Usuarios miuser, int idVeh) {
		usuario=miuser;
		miVehDAO = new VehiculosDAO();
		initialize();
		// carga usuario
		LBUsuario.setText(usuario.getNick());
		LBNomUsu.setText(usuario.getNick());
		// carga registro
		cargaVehiculo(miVehDAO.goToIdVeh(idVeh));
		// refresca LBRegistros
		refrescaReg();
	}

	/**
	 * Refresca el label de control de registros
	 */
	private void refrescaReg() {
		String p="Registro " + String.valueOf(miVehDAO.buscaMatricula(TFMatricula.getText()) + " de "+ 
				String.valueOf(miVehDAO.count())+".");
		LBRegistros.setText(p);	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame principal
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[434px]", "[35px][226px]"));
		
		// panel título
		JPanel PNTitulo = new JPanel();
		PNTitulo.setForeground(Color.BLUE);
		PNTitulo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PNTitulo.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(PNTitulo, "cell 0 0,growx,aligny top");
		PNTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel LBTitulo = new JLabel("Ficha de vehículos");
		LBTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LBTitulo.setForeground(Color.ORANGE);
		PNTitulo.add(LBTitulo);
		PNTitulo.setBackground(Color.decode("#264653"));
		
		// Split Panel
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, "cell 0 1,grow");
		
		// Panel Usuario
		JPanel PNUsuario = new JPanel();
		splitPane.setLeftComponent(PNUsuario);
		PNUsuario.setLayout(new MigLayout("", "[91px]", "[14px][14px]"));
		PNUsuario.setBackground(Color.decode("#2A9D8F"));
		
		
		LBUsuario = new JLabel("Usuario Actual");
		LBUsuario.setForeground(Color.BLUE);
		PNUsuario.add(LBUsuario, "cell 0 0,alignx left,aligny center");
		
		LBNomUsu = new JLabel("Nombre de Usuario");
		PNUsuario.add(LBNomUsu, "cell 0 1,alignx left,aligny center");
		LBNomUsu.setText("Nombre usuario");
		
		// panel central
		JPanel PNCentral = new JPanel();
		splitPane.setRightComponent(PNCentral);
		PNCentral.setLayout(new MigLayout("", "[grow,center]", "[grow][][][][][][]"));
		PNCentral.setBackground(Color.decode("#2A9D8F"));
		
			// panel linea 1
			JPanel PNLinea1 = new JPanel();
			PNCentral.add(PNLinea1, "cell 0 0,grow");
			PNLinea1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNLinea1.setBackground(Color.decode("#2A9D8F"));
			
			JLabel LBMatricula = new JLabel("Matrícula");
			PNLinea1.add(LBMatricula);
			
				TFMatricula = new JTextField();
				PNLinea1.add(TFMatricula);
				TFMatricula.setColumns(10);
				
				JLabel LBBastidor = new JLabel("Bastidor");
				PNLinea1.add(LBBastidor);
				
					TFBastidor = new JTextField();
					PNLinea1.add(TFBastidor);
					TFBastidor.setColumns(10);
			
			// panel linea 2
			JPanel PNLinea2 = new JPanel();
			PNCentral.add(PNLinea2, "cell 0 1,grow");
			PNLinea2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNLinea2.setBackground(Color.decode("#2A9D8F"));
				
				JLabel LBMarca = new JLabel("Marca");
				PNLinea2.add(LBMarca);
				
					TFMarca = new JTextField();
					PNLinea2.add(TFMarca);
					TFMarca.setColumns(10);
					
					JLabel LBModelo = new JLabel("Modelo");
					PNLinea2.add(LBModelo);
					
						TFModelo = new JTextField();
						PNLinea2.add(TFModelo);
						TFModelo.setColumns(10);
			
			// panel linea 3
			JPanel PNLinea3 = new JPanel();
			PNCentral.add(PNLinea3, "cell 0 2,grow");
			PNLinea3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNLinea3.setBackground(Color.decode("#2A9D8F"));
				
			JLabel LBPrecio = new JLabel("Precio");
			PNLinea3.add(LBPrecio);
			
			TFPrecio = new JTextField();
			PNLinea3.add(TFPrecio);
			TFPrecio.setColumns(10);
			
			JLabel LBConcesionario = new JLabel("Concesionario");
			PNLinea3.add(LBConcesionario);
			
			TFConce = new JTextField();
			TFConce.setColumns(10);
			PNLinea3.add(TFConce);
			
			// panel linea 4
			JPanel PNLinea4 = new JPanel();
			PNCentral.add(PNLinea4, "cell 0 3,grow");
			PNLinea4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNLinea4.setBackground(Color.decode("#2A9D8F"));
			
			JLabel LBCliente = new JLabel("Cliente");
			PNLinea4.add(LBCliente);
			
			TFIdCli = new JTextField();
			PNLinea4.add(TFIdCli);
			TFIdCli.setText("Málaga");
			TFIdCli.setColumns(10);
			
			JLabel LBNomCli = new JLabel("Nombre del cliente");
			PNLinea4.add(LBNomCli);
			
			// panel para los botones de la botonera
			JPanel panelBotonera = new JPanel();
			PNCentral.add(panelBotonera, "cell 0 5");
			panelBotonera.setBackground(Color.decode("#2A9D8F"));
			
			BTBuscar = new JButton("Buscar");
			panelBotonera.add(BTBuscar);
			BTBuscar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// llamada a buscar cliente
					frame.dispose();
					ConsVeh miBusqueda = new ConsVeh(usuario);
					miBusqueda.getFrame().setAlwaysOnTop(true);
					miBusqueda.getFrame().setVisible(true);

				}
			});
			
			BTBorra = new JButton("Borrar");
			panelBotonera.add(BTBorra);
			BTBorra.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(
							frame, 
							"¿Seguro que quiere borrar el registro?", 
							"Borrar registro", 
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_NO_OPTION) {
						miVehDAO.borraVehiculo(TFMatricula.getText());	
						Vehiculos miVeh = miVehDAO.primero();
						// cargar cliente en form
						cargaVehiculo(miVeh);
						refrescaReg();
					}

				}
			});
			
			BTGuardar = new JButton("Guardar");
			panelBotonera.add(BTGuardar);
			BTGuardar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// crea el nuevo vehículo SIN ID y SIN Tipo aen espera de aclarar 
					Vehiculos miVeh=new Vehiculos(TFMatricula.getText(),TFBastidor.getText(),
							TFMarca.getText(),TFModelo.getText(),
							Float.parseFloat(TFPrecio.getText()),
							Date.valueOf(LocalDate.now()),
							Integer.parseInt(TFIdCli.getText()),
							usuario.getId(),
							Integer.parseInt(TFConce.getText()));
					
			
					// comprobar si ya existe el registro
					if (miVehDAO.Comprobarvehiculo(TFMatricula.getText())) {
						// guardar el registro
						miVehDAO.updateVehiculo(miVeh);	
					} else {
						// insertar el registro
						miVehDAO.addVehiculo(miVeh);
					}
					daBotones(true);
					refrescaReg();
				}
			});
			
			BTNuevo = new JButton("Nuevo");
			panelBotonera.add(BTNuevo);
			BTNuevo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TFMatricula.setText("");
					TFMatricula.requestFocus();
					TFBastidor.setText("");
					TFMarca.setText("");
					TFModelo.setText("");
					TFPrecio.setText("");
					TFIdCli.setText("");
					TFConce.setText("");
					daBotones(false);
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
					miMenuVentas.getFrame().setVisible(true);;
				}
			});
			
			// panel registros
			JPanel panelRegistros = new JPanel();
			panelRegistros.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
			PNCentral.add(panelRegistros, "cell 0 6");
			
			BTPrimero = new JButton("<<");
			BTPrimero.setToolTipText("Primer registro.");
			BTPrimero.setForeground(Color.RED);
			BTPrimero.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTPrimero);
			BTPrimero.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Vehiculos miveh = miVehDAO.primero();
					// cargar vehiculo en form
					cargaVehiculo(miveh);
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
					Vehiculos miVeh = miVehDAO.anterior(TFMatricula.getText());
					// cargar vehiculo en form
					cargaVehiculo(miVeh);
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
					Vehiculos miVeh = miVehDAO.siguiente(TFMatricula.getText());
					// cargar cliente en form
					cargaVehiculo(miVeh);
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
					Vehiculos miVeh = miVehDAO.ultimo();
					// cargar cliente en form
					cargaVehiculo(miVeh);
					refrescaReg();
				}
			});
			
			// Panel para los botones del control de registros
			JPanel panelBotoneras = new JPanel();
			panelBotoneras.setMaximumSize(new Dimension(1000, 60));
			panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
			PNCentral.add(panelBotoneras, "cell 0 4");
	}
	
	/**
	 * Activa/desactiva botones
	 * @param estado
	 */
	protected void daBotones(boolean estado) {
		BTBorra.setEnabled(estado);
		BTAnterior.setEnabled(estado);
		BTNuevo.setEnabled(estado);
		BTPrimero.setEnabled(estado);
		BTSiguiente.setEnabled(estado);
		BTultimo.setEnabled(estado);
	}

	/**
	 * Carga el formulario con los datos de un cliente 
	 * @param miCliente
	 */
	protected void cargaVehiculo(Vehiculos miVeh) {
		TFMatricula.setText(miVeh.getMatricula());
		TFBastidor.setText(miVeh.getBastidor());
		TFMarca.setText(miVeh.getMarca());
		TFModelo.setText(miVeh.getModelo());
		TFPrecio.setText(String.valueOf(miVeh.getPrecio()));
		TFIdCli.setText(String.valueOf(miVeh.getId_cli()));
		TFConce.setText(String.valueOf(miVeh.getId_conce()));
		
		// aqui habría que rellenar el nombre de cliente
		
		
	}

	/*
	 * Get Frame
	 */
	public JFrame getFrame() {
		return frame;
	}

}
