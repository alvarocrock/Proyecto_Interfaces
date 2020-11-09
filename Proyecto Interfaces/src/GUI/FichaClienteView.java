package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import DAO.ClientesDAO;
import Models.Clientes;
import Models.Usuarios;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

public class FichaClienteView {

	private JFrame frame;
	private Usuarios usuario;
	private ClientesDAO miCliDAO;
	private JTextField TFDni;
	private JTextField TFNombre;
	private JTextField TFApellidos;
	private JTextField TFProv;
	private JLabel LBUsuario;
	private JLabel LBNomUsu;
	private JTextField TFPob;
	private JTextField TFDir;
	private JLabel LBImg;
	private JButton BTBorra;
	private JButton BTNuevo;
	private JButton BTPrimero;
	private JButton BTSiguiente;
	private JButton BTultimo;
	private JButton BTBuscar;
	private JButton BTAnterior;
	private JLabel LBRegistros;
	private JButton BTGuardar;
	private JButton BTSalir;


	/**
	 * Constructor con usuario e id de cliente
	 * @param miuser
	 * @param idCli
	 */
	public FichaClienteView(Usuarios miuser, int idCli) {
		usuario=miuser;
		miCliDAO = new ClientesDAO ();
		initialize();
		// carga usuario
		LBNomUsu.setText(usuario.getNick());
		if (idCli==0) {
			// carga primer registro
			cargaCliente(miCliDAO.primero());
		} else {
			// carga registro
			cargaCliente(miCliDAO.goToIdCli(idCli));
		}

		// refresca LBRegistros
		refrescaReg();
	}

	/**
	 * Refresca el label de control de registros
	 */
	private void refrescaReg() {
		String p="Registro " + String.valueOf(miCliDAO.buscaDNI(TFDni.getText()) + " de "+ String.valueOf(miCliDAO.count())+".");
		LBRegistros.setText(p);
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
					
					JLabel LBTitulo = new JLabel("Ficha de clientes");
					LBTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
					LBTitulo.setForeground(Color.ORANGE);
					PNTitulo.add(LBTitulo);
					
					// Panel medio
					JPanel PNMedio = new JPanel();
					frame.getContentPane().add(PNMedio);
					PNMedio.setLayout(new BoxLayout(PNMedio, BoxLayout.X_AXIS));
					
					// Panel Usuario
					JPanel PNUsuario = new JPanel();
					PNMedio.add(PNUsuario);
					PNUsuario.setLayout(new BoxLayout(PNUsuario,BoxLayout.Y_AXIS));
					PNUsuario.setBackground(Color.decode("#2A9D8F"));
					
					// panel titulo usuario
					JPanel PNTitUsu = new JPanel();
					PNTitUsu.setBackground(Color.decode("#2A9D8F"));
					PNUsuario.add(PNTitUsu);
					PNTitUsu.setLayout(new BoxLayout(PNTitUsu, BoxLayout.X_AXIS));
					
					LBUsuario = new JLabel("Usuario Actual");
					LBUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
					PNTitUsu.add(LBUsuario);
					LBUsuario.setForeground(Color.ORANGE);
					
					// panel imagen
					JPanel PNImg = new JPanel();
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
					
					LBNomUsu = new JLabel("Nombre de Usuario");
					PNUsuario.add(LBNomUsu);
		            LBNomUsu.setAlignmentX(0.5f);            
		            LBNomUsu.setText(usuario.getNick());
					
					// panel nombre de usuario
					JPanel PNMenu = new JPanel();
					PNMenu.setBackground(Color.decode("#2A9D8F"));
					PNUsuario.add(PNMenu);
					
					// panel central
					JPanel PNCentral = new JPanel();
					PNMedio.add(PNCentral);
					PNCentral.setBackground(Color.decode("#2A9D8F"));
					PNCentral.setLayout(new BoxLayout(PNCentral, BoxLayout.Y_AXIS));
		
			// panel linea 1
			JPanel PNLinea1 = new JPanel();
			PNCentral.add(PNLinea1, "cell 0 0,grow");
			PNLinea1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNLinea1.setBackground(Color.decode("#2A9D8F"));
			
			JLabel LBDni = new JLabel("DNI/NIE");
			PNLinea1.add(LBDni);
			
				TFDni = new JTextField();
				PNLinea1.add(TFDni);
				TFDni.setColumns(10);
				
				JLabel LBNombre = new JLabel("Nombre");
				PNLinea1.add(LBNombre);
				
					TFNombre = new JTextField();
					PNLinea1.add(TFNombre);
					TFNombre.setColumns(10);
			
			// panel linea 2
			JPanel PNLinea2 = new JPanel();
			PNCentral.add(PNLinea2, "cell 0 1,grow");
			PNLinea2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNLinea2.setBackground(Color.decode("#2A9D8F"));
				
				JLabel LBApellidos = new JLabel("Apellidos");
				PNLinea2.add(LBApellidos);
				
					TFApellidos = new JTextField();
					PNLinea2.add(TFApellidos);
					TFApellidos.setColumns(10);
					
					JLabel LBDir = new JLabel("Dirección");
					PNLinea2.add(LBDir);
					
						TFDir = new JTextField();
						PNLinea2.add(TFDir);
						TFDir.setColumns(10);
			
			// panel linea 3
			JPanel PNLinea3 = new JPanel();
			PNCentral.add(PNLinea3, "cell 0 2,grow");
			PNLinea3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNLinea3.setBackground(Color.decode("#2A9D8F"));
				
			JLabel LBProv = new JLabel("Provincia");
			PNLinea3.add(LBProv);
			
			TFProv = new JTextField();
			PNLinea3.add(TFProv);
			TFProv.setColumns(10);
			
			// panel linea 4
			JPanel PNLinea4 = new JPanel();
			PNCentral.add(PNLinea4, "cell 0 3,grow");
			PNLinea4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNLinea4.setBackground(Color.decode("#2A9D8F"));
			
			JLabel LBPob = new JLabel("Población");
			PNLinea4.add(LBPob);
			
			TFPob = new JTextField();
			PNLinea4.add(TFPob);
			TFPob.setText("Málaga");
			TFPob.setColumns(10);
			
			// Panel para los botones del control de registros
			JPanel panelBotoneras = new JPanel();
			panelBotoneras.setMaximumSize(new Dimension(1000, 60));
			panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
			PNCentral.add(panelBotoneras, "cell 0 4");
			
			JPanel panelBotonera = new JPanel();
			panelBotonera.setBackground(new Color(42, 157, 143));
			panelBotoneras.add(panelBotonera);
			
			BTBuscar = new JButton("Buscar");
			panelBotonera.add(BTBuscar);
			BTBuscar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// llamada a buscar cliente
					frame.dispose();
					BusCliView miBusqueda = new BusCliView(usuario);
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
						miCliDAO.borraCliente(TFDni.getText());	
						Clientes miCliente = miCliDAO.primero();
						// cargar cliente en form
						cargaCliente(miCliente);
						refrescaReg();
					}

				}
			});
			
			BTGuardar = new JButton("Guardar");
			panelBotonera.add(BTGuardar);
			BTGuardar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// crea el nuevo cliente
					Clientes cliente=new Clientes(0,TFDni.getText(),TFNombre.getText(),TFApellidos.getText(),TFDir.getText(),
							TFProv.getText(),TFPob.getText(),Date.valueOf(LocalDate.now()));
					// comprobar si ya existe el registro
					if (miCliDAO.ComprobarCliente(TFDni.getText())) {
						// guardar el registro
						miCliDAO.updateCliente(cliente);	
					} else {
						// insertar el registro
						miCliDAO.addCliente(cliente);
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
					TFDni.setText("");
					TFDni.requestFocus();
					TFNombre.setText("");
					TFApellidos.setText("");
					TFDir.setText("");
					TFProv.setText("");
					TFPob.setText("");
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
			
			JPanel panelRegistros = new JPanel();
			panelRegistros.setBackground(Color.decode("#2A9D8F"));
			panelBotoneras.add(panelRegistros);
			panelRegistros.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
			
			BTPrimero = new JButton("<<");
			BTPrimero.setToolTipText("Primer registro.");
			BTPrimero.setForeground(Color.RED);
			BTPrimero.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTPrimero);
			BTPrimero.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Clientes miCliente = miCliDAO.primero();
					// cargar cliente en form
					cargaCliente(miCliente);
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
					Clientes miCliente = miCliDAO.anterior(TFDni.getText());
					// cargar cliente en form
					if (miCliente!=null){
						cargaCliente(miCliente);
						refrescaReg();
					}

				}
			});
			
			LBRegistros = new JLabel("No se han encontrado registros.");
			LBRegistros.setBorder(new LineBorder(Color.BLUE, 1, true));
			LBRegistros.setBackground(Color.WHITE);
			panelRegistros.add(LBRegistros);
			
			BTSiguiente = new JButton(">");
			BTSiguiente.setToolTipText("Registro siguiente.");
			BTSiguiente.setForeground(Color.RED);
			BTSiguiente.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTSiguiente);
			BTSiguiente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Clientes miCliente = miCliDAO.siguiente(TFDni.getText());
					if (miCliente!=null){
						cargaCliente(miCliente);
						refrescaReg();
					}
				}
			});
			
			BTultimo = new JButton(">>");
			BTultimo.setToolTipText("Último registro.");
			BTultimo.setForeground(Color.RED);
			BTultimo.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTultimo);
			BTultimo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Clientes miCliente = miCliDAO.ultimo();
					// cargar cliente en form
					cargaCliente(miCliente);
					refrescaReg();
				}
			});
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
		BTBuscar.setEnabled(estado);
	}

	/**
	 * Carga el formulario con los datos de un cliente 
	 * @param miCliente
	 */
	protected void cargaCliente(Clientes miCliente) {
		TFDni.setText(miCliente.getDNI());
		TFNombre.setText(miCliente.getNombre());
		TFApellidos.setText(miCliente.getApellido());
		TFDir.setText(miCliente.getDireccion());
		TFProv.setText(miCliente.getProvincia());
		TFPob.setText(miCliente.getProvincia());
	}

	/*
	 * Get Frame
	 */
	public Window getFrame() {
		return frame;
	}

}
