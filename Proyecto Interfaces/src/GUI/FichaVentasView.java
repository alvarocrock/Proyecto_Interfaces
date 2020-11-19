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
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Common.Constantes.TiposVeh;
import DAO.ClientesDAO;
import DAO.ConcesionarioDAO;
import DAO.VehiculosDAO;
import DAO.VentasDAO;
import GUI.FichaVehiculoView.MiWindowListener;
import GUI.FichaVehiculoView.MyKeyListener;
import Models.Clientes;
import Models.Concesionario;
import Models.Usuarios;
import Models.Vehiculos;
import Models.Ventas;

public class FichaVentasView extends JFrame {
	/**
	 * 	estados
	 */
	private static final long serialVersionUID = 1L;
	private Usuarios usuario;
	private JLabel LBUsuario;
	private JLabel LBNomUsu;
	private JTextField TFIdVentas;
	private JTextField TFDni;
	private JFrame frame;
	private JLabel LBRegistros;
	private JButton BTBorra;
	private JButton BTAnterior;
	private JButton BTNuevo;
	private JButton BTPrimero;
	private JButton BTSiguiente;
	private JButton BTultimo;
	private JButton BTBuscar;
	private JButton BTGuardar;
	private JButton BTSalir;
	private JLabel LBNomCli;
	private VentasDAO miVentaDAO;
	private JTextField TFIdVehiculo;
	private JTextField TFFechaPpto;
	private JTextField TFFechaVal;

	/**
	 * Constructor con usuario e id de cliente
	 * @param miuser
	 * @param idVeh
	 */
	public FichaVentasView(Usuarios miuser, int idVenta) {
		usuario=miuser;
		miVentaDAO = new VentasDAO();
		initialize();
		// carga usuario
		LBUsuario.setText(usuario.getNick());
		LBNomUsu.setText(usuario.getNick());
		if (idVenta==0) {
			// carga primer registro
			cargaVenta(miVentaDAO.primero());
		} else {
			// carga registro de la venta solicitada
			cargaVenta(miVentaDAO.goToIdVenta(idVenta));
		}
		refrescaReg();
	}

	/**
	 * Refresca el label de control de registros
	 */
	private void refrescaReg() {
		String p="Registro " + String.valueOf(miVentaDAO.goToIdVenta(Integer.parseInt(TFIdVentas.getText())) + " de "+ 
				String.valueOf(miVentaDAO.count())+".");
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
		// implementar las teclas en el frame
		KeyListener listener = new MyKeyListener();
		frame.addKeyListener(listener);
		// implementar windowListener
		frame.addWindowListener(new MiWindowListener());
		// dar foco
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
		
		JLabel LBTitulo = new JLabel("Ficha de ventas");
		LBTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LBTitulo.setForeground(Color.ORANGE);
		PNTitulo.add(LBTitulo);
		
		// Panel medio
		JPanel PNMedio = new JPanel();
		frame.getContentPane().add(PNMedio);
		PNMedio.setLayout(new BoxLayout(PNMedio, BoxLayout.X_AXIS));
		PNMedio.setBackground(Color.decode("#2A9D8F"));
		
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
			 JLabel LBImg = new JLabel(icon);
			 PNUsuario.add(LBImg);
          } catch (IOException e) {
             e.printStackTrace();
          }
		
		LBNomUsu = new JLabel("Nombre de Usuario");
		PNUsuario.add(LBNomUsu);
        LBNomUsu.setAlignmentX(0.5f);            
        LBNomUsu.setText(usuario.getNick());
		
		//****************
		// Menú lateral
		JPanel PNMenu = new JPanel();
		PNMenu.setBackground(Color.decode("#2A9D8F"));
		PNUsuario.add(PNMenu);
		PNMenu.setLayout(new BoxLayout(PNMenu, BoxLayout.Y_AXIS));
		
        JLabel JLB_buc_ventas = new JLabel("Buscar ventas");
		JLB_buc_ventas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VerVentasView miventa = new VerVentasView(usuario);
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
				BusCliView miBuscCli = new BusCliView(usuario);
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
				FichaClienteView miFichaClientes = new FichaClienteView(usuario,0);
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
		
		JLabel Busca_vehiculos = new JLabel("Buscar Vehiculos");
		Busca_vehiculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConsVeh busqueda= new ConsVeh(usuario);
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
		
		JLabel JLB_ficha_vehiculo = new JLabel("Ficha de Vehiculos");
		JLB_ficha_vehiculo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FichaVehiculoView miFicVeh = new FichaVehiculoView (usuario,0);
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

//***************		
		
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
			
			JLabel LBIdVentas = new JLabel("Identificador de venta");
			PNLinea1.add(LBIdVentas);
			
				TFIdVentas = new JTextField();
				TFIdVentas.setEnabled(false);
				TFIdVentas.setEditable(false);
				PNLinea1.add(TFIdVentas);
				TFIdVentas.setColumns(10);
				
				JLabel LBFechaPpto = new JLabel("Fecha Presupuesto");
				PNLinea1.add(LBFechaPpto);
				
				TFFechaPpto = new JTextField();
				TFFechaPpto.setEnabled(false);
				TFFechaPpto.setEditable(false);
				TFFechaPpto.setColumns(10);
				PNLinea1.add(TFFechaPpto);
				
				JLabel LBfFechaVal = new JLabel("Fecha validez");
				PNLinea1.add(LBfFechaVal);
				
				TFFechaVal = new JTextField();
				TFFechaVal.setEnabled(false);
				TFFechaVal.setEditable(false);
				TFFechaVal.setColumns(10);
				PNLinea1.add(TFFechaVal);
			
			// panel linea 2
			JPanel PNLinea2 = new JPanel();
			PNCentral.add(PNLinea2, "cell 0 1,grow");
			PNLinea2.setBackground(Color.decode("#2A9D8F"));
						PNLinea2.setLayout(new BoxLayout(PNLinea2, BoxLayout.Y_AXIS));
						
						JPanel PNCliente = new JPanel();
						PNLinea2.add(PNCliente);
						PNCliente.setLayout(new BoxLayout(PNCliente, BoxLayout.Y_AXIS));
						
						JPanel PNTitDatCli = new JPanel();
						PNTitDatCli.setForeground(Color.BLUE);
						PNTitDatCli.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
						PNTitDatCli.setBackground(Color.decode("#264653"));
						PNCliente.add(PNTitDatCli);
						PNTitDatCli.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						
						JLabel LBTitDatCliente = new JLabel("Datos del cliente");
						LBTitDatCliente.setForeground(Color.ORANGE);
						LBTitDatCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
						PNTitDatCli.add(LBTitDatCliente);
							
							JPanel PNDatCli = new JPanel();
							FlowLayout flowLayout_1 = (FlowLayout) PNDatCli.getLayout();
							flowLayout_1.setAlignment(FlowLayout.LEFT);
							PNCliente.add(PNDatCli);
							
							JLabel LBDni = new JLabel("DNI");
							PNDatCli.add(LBDni);
							PNDatCli.setBackground(Color.decode("#2A9D8F"));
						
							TFDni = new JTextField();
							PNDatCli.add(TFDni);
							TFDni.setColumns(10);
							
							JLabel LBIdCli = new JLabel("IdCli");
							PNDatCli.add(LBIdCli);
							
							LBNomCli = new JLabel("Nombre del cliente");
							PNDatCli.add(LBNomCli);
							
							JPanel PNEmpleado = new JPanel();
							PNLinea2.add(PNEmpleado);
							PNEmpleado.setLayout(new BoxLayout(PNEmpleado, BoxLayout.Y_AXIS));
							
							JPanel PNTitDatEmp = new JPanel();
							PNTitDatEmp.setForeground(Color.BLUE);
							PNTitDatEmp.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
							PNTitDatEmp.setBackground(new Color(38, 70, 83));
							PNEmpleado.add(PNTitDatEmp);
							PNTitDatEmp.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
							
							JLabel LBDatEmp = new JLabel("Datos del empleado");
							LBDatEmp.setForeground(Color.ORANGE);
							LBDatEmp.setFont(new Font("Tahoma", Font.PLAIN, 16));
							PNTitDatEmp.add(LBDatEmp);
							
							JPanel PNDatCli_1 = new JPanel();
							FlowLayout flowLayout = (FlowLayout) PNDatCli_1.getLayout();
							flowLayout.setAlignment(FlowLayout.LEFT);
							PNDatCli_1.setBackground(new Color(42, 157, 143));
							PNEmpleado.add(PNDatCli_1);
							
			JLabel LBIdUsuario = new JLabel("Empleado");
			PNDatCli_1.add(LBIdUsuario);
			LBIdUsuario.setToolTipText("");
			
			JLabel LBIdUser = new JLabel("IdUser");
			PNDatCli_1.add(LBIdUser);
			
			JLabel LBNomUser = new JLabel("Nombre del usuario");
			PNDatCli_1.add(LBNomUser);
			
			JPanel PNVehiculo = new JPanel();
			PNEmpleado.add(PNVehiculo);
			PNVehiculo.setLayout(new BoxLayout(PNVehiculo, BoxLayout.Y_AXIS));
			PNVehiculo.setBackground(Color.decode("#2A9D8F"));
			
			JPanel PNTitDatVeh = new JPanel();
			PNTitDatVeh.setForeground(Color.BLUE);
			PNTitDatVeh.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			PNTitDatVeh.setBackground(new Color(38, 70, 83));
			PNVehiculo.add(PNTitDatVeh);
			PNTitDatVeh.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel LBDatosDelVehculo = new JLabel("Datos del vehículo");
			LBDatosDelVehculo.setForeground(Color.ORANGE);
			LBDatosDelVehculo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			PNTitDatVeh.add(LBDatosDelVehculo);
			
			// panel linea 4
			JPanel PNDatVeh1 = new JPanel();
			PNVehiculo.add(PNDatVeh1);
			PNDatVeh1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNDatVeh1.setBackground(Color.decode("#2A9D8F"));
			
			JLabel LBIdVehculo = new JLabel("Id vehículo");
			PNDatVeh1.add(LBIdVehculo);
			
			TFIdVehiculo = new JTextField();
			TFIdVehiculo.setColumns(10);
			PNDatVeh1.add(TFIdVehiculo);
			
			JLabel LBMatrícula = new JLabel("Matricula");
			PNDatVeh1.add(LBMatrícula);
			
			JLabel LBMarca = new JLabel("Marca");
			PNDatVeh1.add(LBMarca);
			
			// panel linea 5
						JPanel PNDatVeh2 = new JPanel();
						PNVehiculo.add(PNDatVeh2);
						PNDatVeh2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
						PNDatVeh2.setBackground(Color.decode("#2A9D8F"));
						
						JLabel LBModelo = new JLabel("Modelo");
						PNDatVeh2.add(LBModelo);
						
						JLabel LBPrecio = new JLabel("Precio");
						PNDatVeh2.add(LBPrecio);

			
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
				public void actionPerformed(ActionEvent e) {
					// llamada a buscar cliente
					frame.dispose();
					VerVentasView miBusqueda = new VerVentasView(usuario);
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
						miVentaDAO.borraVenta(TFIdVentas.getText());	
						Ventas miVenta = miVentaDAO.primero();
						// cargar venta en form
						cargaVenta(miVenta);
						refrescaReg();
					}

				}
			});
			
			BTGuardar = new JButton("Guardar");
			panelBotonera.add(BTGuardar);
			BTGuardar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (comprobardatos()) {
						// crea la nueva venta
						Ventas miVenta=new Ventas(0,
								Integer.parseInt(LBIdCli.getText()),
								Integer.parseInt(LBIdUser.getText()),
								Date.valueOf(TFFechaPpto.getText()),
								Date.valueOf(TFFechaVal.getText()),
								Integer.parseInt(TFIdVehiculo.getText()),
								Float.parseFloat(LBPrecio.getText())
								);
						
						// comprobar si ya existe el registro
						if (miVentaDAO.ComprobarVenta((TFIdVentas.getText()))) {
							// guardar el registro
							miVentaDAO.updateVenta(miVenta);	
						} else {
							// insertar el registro
							miVentaDAO.addVenta(miVenta);
						}
						daBotones(true);
						refrescaReg();
					} 
				}

			});
			
			BTNuevo = new JButton("Nuevo");
			panelBotonera.add(BTNuevo);
			BTNuevo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TFIdVentas.setText("");
					TFIdVentas.requestFocus();
					TFFechaPpto.setText("");
					TFFechaVal.setText("");
					TFDni.setText("");
					LBIdCli.setText("");
					LBNomCli.setText("");
					LBIdUser.setText("");
					LBNomUser.setText("");
					TFIdVehiculo.setText("");
					LBMatrícula.setText("");
					LBMarca.setText("");
					LBModelo.setText("");
					LBPrecio.setText("");
					daBotones(false);
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
			
			JPanel panelRegistros = new JPanel();
			panelBotoneras.add(panelRegistros);
			panelRegistros.setBackground(new Color(42, 157, 143));
			panelRegistros.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
			
			BTPrimero = new JButton("<<");
			BTPrimero.setToolTipText("Primer registro.");
			BTPrimero.setForeground(Color.RED);
			BTPrimero.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTPrimero);
			BTPrimero.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Ventas miVenta = miVentaDAO.primero();
					// cargar venta en form
					cargaVenta(miVenta);
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
					Ventas miVenta = miVentaDAO.anterior(TFIdVentas.getText());
					if (miVenta!=null) {
						cargaVenta(miVenta);
						refrescaReg();
					}
				}
			});
			
			LBRegistros = new JLabel(" No se han encontrado registros.");
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
					Ventas miVenta = miVentaDAO.siguiente(TFIdVentas.getText());
					// cargar cliente en form
					if (miVenta!=null) {
						cargaVenta(miVenta);
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
					Ventas miVenta = miVentaDAO.ultimo();
					// cargar cliente en form
					cargaVenta(miVenta);
					refrescaReg();
				}
			});
	
	}
	
	/**
	 * Comprueba si los datos son correctos
	 * @return
	 */
	private boolean comprobardatos() {
		if ( !comprobarInt(TFIdVentas.getText()) || !comprobarInt(TFIdVehiculo.getText())
				|| (TFDni.getText().length()==0) || (TFFechaPpto.getText().length()==0)  
				|| (TFFechaVal.getText().length()==0)	||  (TFIdVehiculo.getText().length()==0)
				|| (TFIdVentas.getText().length()==0)
				) 
		{
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * evalua un String y comprueba que es integer
	 * @return verdadero si es un integer
	 * @param string a evaluar
	 */
	private boolean comprobarInt(String miStr) {
		boolean resultado=true;
		if (!(miStr.matches("[0-9]*"))) {
			resultado=false;
			JOptionPane.showMessageDialog(frame, "El precio no es un número válido. O no ha introducido todos los datos requeridos.");
		};
		return resultado;
	}

	
	/**
	 * Salir de la view
	 */
	protected void salir() {
		frame.dispose();
		MenuVentasView miMenuVentas = new MenuVentasView(usuario);
		miMenuVentas.getFrame().setAlwaysOnTop(true);
		miMenuVentas.getFrame().setVisible(true);;		
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
	protected void cargaVenta(Ventas ventas) {
		TFIdVentas.setText(String.valueOf(ventas.getId_ventas()));
		TFFechaPpto.setText(String.valueOf(ventas.getFechappto()));
		TFFechaVal.setText(String.valueOf(ventas.getFechavalidez()));
		// recupera datos del cliente
		ClientesDAO miCliDAO= new ClientesDAO();
		Clientes miCli = miCliDAO.g(Integer.parseInt(TFDni.getText()));
		TFDni.setText(ventas.getId_cli());
		TFPrecio.setText(String.valueOf(ventas.getPrecio()));
		TFIdCli.setText(String.valueOf(ventas.getId_cli()));
		TFConce.setText(String.valueOf(ventas.getId_conce()));
		// carga el nombre de cliente
		ClientesDAO miCliDAO = new ClientesDAO();
		Clientes miCli = miCliDAO.goToIdCli(ventas.getId_cli());
		LBNomCli.setText(miCli.getNombre()+" "+miCli.getApellido());
		// carga el nombre de concesionario
		ConcesionarioDAO miConDAO = new ConcesionarioDAO();
		Concesionario miCon = miConDAO.goToConce(Integer.parseInt(TFConce.getText()));
		LBNomConce.setText(miCon.getNombre());
		CBTipo.setSelectedItem(ventas.getTipo());
		
}

	/*
	 * Get Frame
	 */
	public JFrame getFrame() {
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
	
	/**
	 * Implementa WindowAdapter
	 * @author Roque
	 *
	 */
	public class MiWindowListener implements WindowListener {
		@Override
		public void windowActivated(java.awt.event.WindowEvent arg0) {
		}
		@Override
		public void windowClosed(java.awt.event.WindowEvent arg0) {
		}

		@Override
		public void windowClosing(java.awt.event.WindowEvent arg0) {
			salir();
		}
		@Override
		public void windowDeactivated(java.awt.event.WindowEvent arg0) {
		}
		@Override
		public void windowDeiconified(java.awt.event.WindowEvent arg0) {
		}
		@Override
		public void windowIconified(java.awt.event.WindowEvent arg0) {
		}
		@Override
		public void windowOpened(java.awt.event.WindowEvent arg0) {
		}
	}
	//**************************** fin
	

}
