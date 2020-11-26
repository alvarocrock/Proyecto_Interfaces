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
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Common.Constantes;
import DAO.UsuarioDAO;
import Models.Usuarios;
import javax.swing.JPasswordField;

public class FichaUsuarioView extends JFrame{
/**
 * 	estados
 */
private static final long serialVersionUID = 1L;
private Usuarios usuario;
private JLabel LBUsuario;
private JLabel LBNomUsu;
private JTextField tfNick;
private JTextField TFDni;
private JFrame frame;
private JLabel LBRegistros;
private JButton BTBorra;
private JButton BTAnterior;
private JButton BTNuevo;
private JButton BTPrimero;
private JButton BTSiguiente;
private JButton BTultimo;
private JButton BTGuardar;
private JButton BTSalir;
private UsuarioDAO miUsuarioDAO;
private JPasswordField pfPasswd;
private JTextField tfRango;
private JLabel lbFoto;
private JLabel lbFecAlta;
private JLabel LBImg;
private JPanel PNLinea2;
private JLabel lbUrlFoto;
private JLabel lbIdUsuario;

/**
 * Constructor con usuario e id de cliente
 * @param miuser
 * @param idVeh
 */
public FichaUsuarioView(Usuarios miuser, int idUsuario) {
	usuario=miuser;
	miUsuarioDAO = new UsuarioDAO();
	initialize();
	// carga usuario
	LBUsuario.setText(usuario.getNick());
	LBNomUsu.setText(usuario.getNick());
	if (idUsuario==0) {
		// carga primer registro
		if (miUsuarioDAO.primero()!=null) cargaUsuario(miUsuarioDAO.primero());
	} else {
		// carga registro de la venta solicitada
		cargaUsuario(miUsuarioDAO.goToIdUsu(idUsuario));
	}
	refrescaReg();
}

/**
 * Refresca el label de control de registros
 */
private void refrescaReg() {
	int miId=miUsuarioDAO.getidbynick(tfNick.getText());
	String p="Registro " + (miUsuarioDAO.goToIdUsu(miId).getId()) + " de "+ 
			String.valueOf(miUsuarioDAO.count())+".";
	LBRegistros.setText(p);	
}

/**
 * Initialize the contents of the frame.
 */
private void initialize() {
	// Frame principal
	frame = new JFrame();
	frame.setAlwaysOnTop(true);
	frame.setBounds(100, 100, 850, 550);
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
	
	JLabel LBTitulo = new JLabel("Ficha de usuarios");
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
	 
	 LBImg = new JLabel();
	 cargaFoto(LBImg,usuario.getFoto(),PNUsuario);
	 PNUsuario.add(LBImg);
	
	
	LBNomUsu = new JLabel("Nombre de Usuario");
	PNUsuario.add(LBNomUsu);
    LBNomUsu.setAlignmentX(0.5f);            
    LBNomUsu.setText(usuario.getNick());
	
	//****************
	// Menú lateral
    JPanel panel_opciones = new JPanel();
	PNUsuario.add(panel_opciones);
	panel_opciones.setLayout(new BoxLayout(panel_opciones, BoxLayout.Y_AXIS));
	panel_opciones.setBackground(Color.decode("#2A9D8F"));
	panel_opciones.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
	
	JLabel JLB_ver_ventas = new JLabel("Ver ventas");
	JLB_ver_ventas.setBorder(new EmptyBorder(5, 0, 5, 0));
	JLB_ver_ventas.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			VerVentasView miProVen = new VerVentasView(usuario);
			miProVen.getFrame().setAlwaysOnTop(true);
			miProVen.getFrame().setVisible(true);
			frame.dispose();
		}
		@Override
		public void mouseEntered (MouseEvent e) {
			JLB_ver_ventas.setForeground(Color.RED);
			JLB_ver_ventas.setFont(new Font("Tahoma",Font.BOLD,14));
		}
		@Override
		public void mouseExited (MouseEvent e) {
			JLB_ver_ventas.setForeground(Color.ORANGE);
			JLB_ver_ventas.setFont(new Font("Tahoma",Font.PLAIN,14));
		}
	});
	
	JLabel lbFicUsu = new JLabel("Ficha Usuarios");
	lbFicUsu.setForeground(new Color(233, 196, 106));
	lbFicUsu.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lbFicUsu.setBorder(new EmptyBorder(5, 0, 5, 0));
	panel_opciones.add(lbFicUsu);
	lbFicUsu.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			FichaUsuarioView miFU = new FichaUsuarioView(usuario,0);
			miFU.getFrame().setAlwaysOnTop(true);
			miFU.getFrame().setVisible(true);
			frame.dispose();
		}
		@Override
		public void mouseEntered (MouseEvent e) {
			lbFicUsu.setForeground(Color.RED);
			lbFicUsu.setFont(new Font("Tahoma",Font.BOLD,14));

		}
		@Override
		public void mouseExited (MouseEvent e) {
			lbFicUsu.setForeground(Color.ORANGE);
			lbFicUsu.setFont(new Font("Tahoma",Font.PLAIN,14));
		}
	});
	
	JLabel lbResumenVentas = new JLabel("Resumen de ventas");
	lbResumenVentas.setForeground(new Color(233, 196, 106));
	lbResumenVentas.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lbResumenVentas.setBorder(new EmptyBorder(5, 0, 5, 0));
	panel_opciones.add(lbResumenVentas);
	lbResumenVentas.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			ResumenVentasView miRS = new ResumenVentasView(usuario);
			miRS.getFrame().setAlwaysOnTop(true);
			miRS.getFrame().setVisible(true);
			frame.dispose();
		}
		@Override
		public void mouseEntered (MouseEvent e) {
			lbResumenVentas.setForeground(Color.RED);
			lbResumenVentas.setFont(new Font("Tahoma",Font.BOLD,14));

		}
		@Override
		public void mouseExited (MouseEvent e) {
			lbResumenVentas.setForeground(Color.ORANGE);
			lbResumenVentas.setFont(new Font("Tahoma",Font.PLAIN,14));
		}
	});
	
	
	JLB_ver_ventas.setFont(new Font("Tahoma", Font.PLAIN, 14));
	JLB_ver_ventas.setForeground(Color.decode("#E9C46A"));
	panel_opciones.add(JLB_ver_ventas);
	
	JLabel LBVentas = new JLabel("Ficha Ventas");
	LBVentas.setForeground(new Color(233, 196, 106));
	LBVentas.setFont(new Font("Tahoma", Font.PLAIN, 14));
	LBVentas.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
	panel_opciones.add(LBVentas);
	LBVentas.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			FichaVentasView miFV = new FichaVentasView (usuario,0);
			miFV.getFrame().setAlwaysOnTop(true);
			miFV.getFrame().setVisible(true);
			frame.dispose();
		}
		@Override
		public void mouseEntered (MouseEvent e) {
			LBVentas.setForeground(Color.RED);
			LBVentas.setFont(new Font("Tahoma",Font.BOLD,14));

		}
		@Override
		public void mouseExited (MouseEvent e) {
			LBVentas.setForeground(Color.ORANGE);
			LBVentas.setFont(new Font("Tahoma",Font.PLAIN,14));
		}
	});
	
	JLabel JLB_buscar_cli = new JLabel("Buscar Clientes");
	JLB_buscar_cli.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
	JLB_buscar_cli.setFont(new Font("Tahoma", Font.PLAIN, 14));
	JLB_buscar_cli.setForeground(Color.decode("#E9C46A"));
	panel_opciones.add(JLB_buscar_cli);
	JLB_buscar_cli.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			BusCliView miBuscCli = new BusCliView(frame, usuario,0);
			miBuscCli.getFrame().setAlwaysOnTop(true);
			miBuscCli.getFrame().setVisible(true);
			frame.dispose();
		}
		@Override
		public void mouseEntered (MouseEvent e) {
			JLB_buscar_cli.setForeground(Color.RED);
			JLB_buscar_cli.setFont(new Font("Tahoma",Font.BOLD,14));

		}
		@Override
		public void mouseExited (MouseEvent e) {
			JLB_buscar_cli.setForeground(Color.ORANGE);
			JLB_buscar_cli.setFont(new Font("Tahoma",Font.PLAIN,14));
		}
	});

	
	JLabel JLB_ficha_cliente = new JLabel("Ficha Cliente");
	JLB_ficha_cliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
	JLB_ficha_cliente.setForeground(Color.decode("#E9C46A"));
	JLB_ficha_cliente.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
	panel_opciones.add(JLB_ficha_cliente);
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
			JLB_ficha_cliente.setFont(new Font("Tahoma",Font.BOLD,14));
		}
		@Override
		public void mouseExited (MouseEvent e) {
			JLB_ficha_cliente.setForeground(Color.ORANGE);
			JLB_ficha_cliente.setFont(new Font("Tahoma",Font.PLAIN,14));
		}
	});

	
	JLabel Busca_vehiculos = new JLabel("Busca Vehiculos");
	Busca_vehiculos.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
	Busca_vehiculos.setFont(new Font("Tahoma", Font.PLAIN, 14));
	Busca_vehiculos.setForeground(Color.decode("#E9C46A"));
	panel_opciones.add(Busca_vehiculos);
	Busca_vehiculos.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			ConsVeh busqueda= new ConsVeh(frame, usuario,1);
			busqueda.getFrame().setAlwaysOnTop(true);
			busqueda.getFrame().setVisible(true);
			frame.dispose();
		}
		@Override
		public void mouseEntered (MouseEvent e) {
			Busca_vehiculos.setForeground(Color.RED);
			Busca_vehiculos.setFont(new Font("Tahoma",Font.BOLD,14));
		}
		@Override
		public void mouseExited (MouseEvent e) {
			Busca_vehiculos.setForeground(Color.ORANGE);
			Busca_vehiculos.setFont(new Font("Tahoma",Font.PLAIN,14));
		}
	});

	
	JLabel JLB_ficha_vehiculo = new JLabel("Ficha Vehiculo");
	JLB_ficha_vehiculo.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
	JLB_ficha_vehiculo.setFont(new Font("Tahoma", Font.PLAIN, 14));
	JLB_ficha_vehiculo.setForeground(Color.decode("#E9C46A"));
	panel_opciones.add(JLB_ficha_vehiculo);
	
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
			JLB_ficha_vehiculo.setFont(new Font("Tahoma",Font.BOLD,14));
		}
		@Override
		public void mouseExited (MouseEvent e) {
			JLB_ficha_vehiculo.setForeground(Color.ORANGE);
			JLB_ficha_vehiculo.setFont(new Font("Tahoma",Font.PLAIN,14));
		}
	});
	JLabel JLB_buc_conce = new JLabel("Buscar Concesionarios");
	JLB_buc_conce.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			BuscarConcesionariosView miconceview = new BuscarConcesionariosView(usuario);
			miconceview.getFrame().setVisible(true);
			miconceview.getFrame().setAlwaysOnTop(true);
			frame.dispose();
		}
		@Override
		public void mouseEntered (MouseEvent e) {
			JLB_buc_conce.setForeground(Color.RED);
			JLB_buc_conce.setFont(new Font("Tahoma",Font.BOLD,14));
		}
		@Override
		public void mouseExited (MouseEvent e) {
			JLB_buc_conce.setForeground(Color.ORANGE);
			JLB_buc_conce.setFont(new Font("Tahoma",Font.BOLD,14));
		}
	});
	JLB_buc_conce.setFont(new Font("Tahoma", Font.PLAIN, 14));
	JLB_buc_conce.setForeground(Color.decode("#E9C46A"));
	panel_opciones.add(JLB_buc_conce);
	
	JLabel JLB_buc_ppto = new JLabel("Buscar Presupuestos");
	JLB_buc_ppto.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			BuscarPresupuestosView miconceview = new BuscarPresupuestosView(usuario);
			miconceview.getFrame().setVisible(true);
			miconceview.getFrame().setAlwaysOnTop(true);
			frame.dispose();
		}
		@Override
		public void mouseEntered (MouseEvent e) {
			JLB_buc_ppto.setForeground(Color.RED);
			JLB_buc_ppto.setFont(new Font("Tahoma",Font.BOLD,14));
		}
		@Override
		public void mouseExited (MouseEvent e) {
			JLB_buc_ppto.setForeground(Color.ORANGE);
			JLB_buc_ppto.setFont(new Font("Tahoma",Font.BOLD,14));
		}
	});
	
	JLB_buc_ppto.setFont(new Font("Tahoma", Font.PLAIN, 14));
	JLB_buc_ppto.setForeground(Color.decode("#E9C46A"));
	panel_opciones.add(JLB_buc_ppto);
	
	
	JLabel JLB_cerrar_sesion = new JLabel("Cerrar sesi\u00F3n");
	JLB_cerrar_sesion.setFont(new Font("Tahoma", Font.PLAIN, 13));
	JLB_cerrar_sesion.setForeground(Color.decode("#E76F51"));
	JLB_cerrar_sesion.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
	panel_opciones.add(JLB_cerrar_sesion);
	
	JLB_cerrar_sesion.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			salir();
		}
		@Override
		public void mouseEntered (MouseEvent e) {
			JLB_cerrar_sesion.setForeground(Color.RED);
			JLB_cerrar_sesion.setFont(new Font("Tahoma",Font.BOLD,14));
		}
		@Override
		public void mouseExited (MouseEvent e) {
			JLB_cerrar_sesion.setForeground(Color.ORANGE);
			JLB_cerrar_sesion.setFont(new Font("Tahoma",Font.PLAIN,14));
		}
	});
//***************		
	
	// panel central
	JPanel PNCentral = new JPanel();
	PNMedio.add(PNCentral);
	PNCentral.setBackground(Color.decode("#2A9D8F"));
	PNCentral.setLayout(new BoxLayout(PNCentral, BoxLayout.Y_AXIS));
	
		// panel linea 1
		JPanel PNLinea1 = new JPanel();
		PNCentral.add(PNLinea1);
		PNLinea1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		PNLinea1.setMaximumSize(new Dimension(750,200));
		PNLinea1.setBackground(Color.decode("#2A9D8F"));
	
		JLabel LBDni = new JLabel("DNI");
		PNLinea1.add(LBDni);
		
		TFDni = new JTextField();
		PNLinea1.add(TFDni);
		TFDni.setColumns(10);
		
		JLabel lbNick = new JLabel("Nick");
		PNLinea1.add(lbNick);
		
		tfNick = new JTextField();
		PNLinea1.add(tfNick);
		tfNick.setColumns(10);
		
		JLabel lbPasswd = new JLabel("Contraseña");
		PNLinea1.add(lbPasswd);
		
		pfPasswd = new JPasswordField();
		pfPasswd.setColumns(10);
		pfPasswd.setText("");
		PNLinea1.add(pfPasswd);
		
		JLabel lbRango = new JLabel("Rango");
		PNLinea1.add(lbRango);
		
		tfRango = new JTextField();
		PNLinea1.add(tfRango);
		tfRango.setColumns(10);
		
		lbIdUsuario = new JLabel("id");
		lbIdUsuario.setEnabled(false);
		lbIdUsuario.setVisible(false);
		PNLinea1.add(lbIdUsuario);
		
		// panel linea 2
		PNLinea2 = new JPanel();
		PNCentral.add(PNLinea2, "cell 0 1,grow");
		PNLinea2.setBackground(Color.decode("#2A9D8F"));
		PNLinea2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JLabel lbFechaAlta = new JLabel("Fecha de alta");
		PNLinea2.add(lbFechaAlta);
		lbFecAlta = new JLabel("fecAlta");
		lbFecAlta.setForeground(Color.BLUE);
		PNLinea2.add(lbFecAlta);
		
		JButton BTBuscarFoto = new JButton(" ");
		BTBuscarFoto.setIcon(new ImageIcon(FichaVentasView.class.getResource("/png/lupa.png")));
		PNLinea2.add(BTBuscarFoto);
		BTBuscarFoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory((new File (System.getProperty("user.dir") + "\\src\\png")));
				int seleccion = fileChooser.showOpenDialog(frame);
				
				if (seleccion == JFileChooser.APPROVE_OPTION)
				{
				   File fichero = fileChooser.getSelectedFile();
				   cargaFoto(lbFoto, fichero.getAbsolutePath(), PNLinea2);
				   Path path = FileSystems.getDefault().getPath(fichero.getAbsolutePath());
				   lbUrlFoto.setText(path.toString());
				}
			}
		});
		
		lbFoto = new JLabel();
		PNLinea2.add(lbFoto);
		// carga foto
		cargaFoto(lbFoto,usuario.getFoto(),PNLinea2);
		
		
		lbUrlFoto = new JLabel();
		lbUrlFoto.setVisible(false);
		PNLinea2.add(lbUrlFoto);
		
		// Panel para los botones del control de registros	
		JPanel panelBotoneras = new JPanel();
		panelBotoneras.setMaximumSize(new Dimension(1000, 60));
		panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
		PNCentral.add(panelBotoneras, "cell 0 4");
		
		JPanel panelBotonera = new JPanel();
		panelBotonera.setBackground(new Color(42, 157, 143));
		panelBotoneras.add(panelBotonera);
		
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
					miUsuarioDAO.borraUsuario(Integer.parseInt(lbIdUsuario.getText()));	
					Usuarios miUsuario = miUsuarioDAO.primero();
					// cargar venta en form
					cargaUsuario(miUsuario);
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
					// crea el nuevo usuario
					Usuarios usuario=new Usuarios(TFDni.getText(), 
							tfNick.getText(), 
							Constantes.encriptar(pfPasswd.getPassword()),
							tfRango.getText(),
							String.valueOf(Date.valueOf(LocalDate.now())),
							Integer.valueOf(lbIdUsuario.getText()),
							lbUrlFoto.getText()
							);
					
					// comprobar si ya existe el registro
					if (miUsuarioDAO.ComprobarUsuario((Integer.parseInt(lbIdUsuario.getText())))) {
						// guardar el registro
						miUsuarioDAO.updateUsuario(usuario);	
					} else {
						// insertar el registro
						miUsuarioDAO.addUsuario(usuario);
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
				TFDni.setText("");
				TFDni.requestFocus();
				tfNick.setText("");
				pfPasswd.setText("");
				tfRango.setText("");
				lbFoto.setText("");
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
				Usuarios miUsuario = miUsuarioDAO.primero();
				// cargar venta en form
				cargaUsuario(miUsuario);
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
				Usuarios miUsuario = miUsuarioDAO.anterior(Integer.parseInt(lbIdUsuario.getText()));
				if (miUsuario!=null) {
					cargaUsuario(miUsuario);
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
				Usuarios miUsuario = miUsuarioDAO.siguiente(Integer.parseInt(lbIdUsuario.getText()));
				// cargar usuario en form
				if (miUsuario!=null) {
					cargaUsuario(miUsuario);
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
				Usuarios miUsuario = miUsuarioDAO.ultimo();
				// cargar cliente en form
				cargaUsuario(miUsuario);
				refrescaReg();
			}
		});

}

/**
 * Comprueba si los datos son correctos
 * @return
 */
private boolean comprobardatos() {
	if ( !Constantes.comprobarDNI(TFDni.getText())
			|| (TFDni.getText().length()==0) || (pfPasswd.getText().length()==0)  
			|| (tfNick.getText().length()==0)	||  (tfRango.getText().length()==0)
			) 
	{
		return false;
	} else {
		return true;
	}
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
	//BTBuscar.setEnabled(estado);
}

/**
 * Carga el formulario con los datos de un usuario
 * @param ventas
 */
protected void cargaUsuario(Usuarios usuario) {
	TFDni.setText(usuario.getDni());
	tfNick.setText(usuario.getNick());
	pfPasswd.setText(usuario.getPasswd());
	tfRango.setText(usuario.getRango());
	String fecha = new SimpleDateFormat("dd-MM-yyyy").format(Date.valueOf(usuario.getFecha()));
	lbFecAlta.setText(fecha);
// transforma los slash de la ruta
	Path path = FileSystems.getDefault().getPath(usuario.getFoto());
	lbUrlFoto.setText(path.toString());
	cargaFoto (lbFoto, lbUrlFoto.getText(),PNLinea2);
	lbIdUsuario.setText(String.valueOf(usuario.getId()));
}

/**
 * carga la foto del usuario en el label pasado como parámetro, en el panel dado y desde la ruta dada
 * @param lbFoto2
 * @param imgStr
 */
private void cargaFoto(JLabel lbFoto2, String imgStr, JPanel panel) {
	// carga imagen usuario
    try {
		 BufferedImage img = ImageIO.read(new File(imgStr));
		 ImageIcon icon = new ImageIcon(img);
		 lbFoto2.setIcon(icon);;
		 panel.add(lbFoto2);
     } catch (IOException e) {
        e.printStackTrace();
     }	

}

/**
 * getters
 * @param tFDni
 */
public JTextField getTFDni() {
	return TFDni;
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
