package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DAO.ConcesionarioDAO;
import DAO.PresupuestoDAO;
import DAO.VentasDAO;
import GUI.FichaConce.MyKeyListener;
import Models.Concesionario;
import Models.Presupuesto;
import Models.Usuarios;
import Models.Ventas;

import javax.swing.SwingConstants;

public class FichaPPTO extends JFrame {

	private static final long serialVersionUID = 1L;
	private Usuarios usuario;
	private PresupuestoDAO contro;
	private JLabel LBUsuario;
	private JLabel LBNomUsu;
	private JLabel LBRegistros;
	private JFrame frame;
	private JTextField JTF_id_presu;
	private JLabel JTF_nombre_cli;
	private JButton BTBuscar;
	private JButton BTBorra;
	private JButton BTGuardar;
	private JButton BTNuevo;
	private JButton BTSalir;
	private JButton BTPrimero;
	private JButton BTAnterior;
	private JButton BTSiguiente;
	private JButton BTultimo;
	private JLabel LBNomCli;
	private JTextField JTF_DNI_cli;
	private JTextField JTF_empleado;
	private JTextField JTF_fechappto;
	private JTextField JTF_fecha_validez;
	private JTextField JTF_matricula;
	private JTextField JTF_precio_ppto;
	private JTextField JTF_estado;
	private JButton BTN_vender;
	
	/**
	 * Create the application.
	 */
	public FichaPPTO(Usuarios miuser, int idConce) {
		usuario=miuser;
		contro = new PresupuestoDAO();
		initialize();
		// carga usuario
		LBUsuario.setText(usuario.getNick());
		LBNomUsu.setText(usuario.getNick());
		if (idConce==0) {
			// carga primer registro
			cargaPPTO(contro.primero());
		} else {
			// carga registro del usuario solicitado
			cargaPPTO(contro.goToPPTO_byid(idConce));
		}
		refrescaReg();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/**
	 * Refresca el label de control de registros
	 */
	private void refrescaReg() {
		String valor = String.valueOf(contro.goToPPTO(Integer.parseInt(JTF_id_presu.getText())).getId());
		String p="Registro " + valor + " de "+ String.valueOf(contro.count())+".";
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
		
		JLabel LBTitulo = new JLabel("Ficha de PPTO");
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
			 JLabel LBImg = new JLabel(icon);
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
		PNMenu.setBackground(new Color(42, 157, 143));
		PNUsuario.add(PNMenu);
		
		//****************
		// Menú lateral
		PNMenu = new JPanel();
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
				BusCliView miBuscCli = new BusCliView(frame, usuario,0);
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
		
		JLabel Busca_vehiculos = new JLabel("Busca Vehiculos");
		Busca_vehiculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConsVeh busqueda= new ConsVeh(frame, usuario,0);
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
			
			JLabel LBIdConce = new JLabel("Identificador");
			PNLinea1.add(LBIdConce);
			
				JTF_id_presu = new JTextField();
				JTF_id_presu.setEditable(false);
				JTF_id_presu.setEnabled(false);
				PNLinea1.add(JTF_id_presu);
				JTF_id_presu.setColumns(10);
			
			
			JPanel PNLinea4 = new JPanel();
			PNLinea4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNCentral.add(PNLinea4);
			PNLinea4.setBackground(new Color(42, 157, 143));
			
			JLabel JLB_empleado = new JLabel("Empleado");
			PNLinea4.add(JLB_empleado);
			
			JTF_empleado = new JTextField();
			PNLinea4.add(JTF_empleado);
			JTF_empleado.setColumns(10);
			
			JPanel PNLinea5 = new JPanel();
			PNLinea5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNCentral.add(PNLinea5);
			PNLinea5.setBackground(new Color(42, 157, 143));
			
			JLabel JLB_fechappto = new JLabel("Fecha creaci\u00F3n");
			PNLinea5.add(JLB_fechappto);
			
			JTF_fechappto = new JTextField();
			PNLinea5.add(JTF_fechappto);
			JTF_fechappto.setColumns(10);
			
			JPanel PNLinea6 = new JPanel();
			PNLinea6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNCentral.add(PNLinea6);
			PNLinea6.setBackground(new Color(42, 157, 143));
			
			JLabel JLB_fecha_validez = new JLabel("Fecha Validez");
			PNLinea6.add(JLB_fecha_validez);
			
			JTF_fecha_validez = new JTextField();
			PNLinea6.add(JTF_fecha_validez);
			JTF_fecha_validez.setColumns(10);
			
			JPanel PNLinea7 = new JPanel();
			PNLinea7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNCentral.add(PNLinea7);
			
			JLabel JLB_matricula_vehiculo = new JLabel("Mtricula vehiculo");
			PNLinea7.add(JLB_matricula_vehiculo);
			PNLinea7.setBackground(new Color(42, 157, 143));
			
			JTF_matricula = new JTextField();
			PNLinea7.add(JTF_matricula);
			JTF_matricula.setColumns(10);
			
			JPanel PNLinea8 = new JPanel();
			PNLinea8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNCentral.add(PNLinea8);
			PNLinea8.setBackground(new Color(42, 157, 143));
			
			JLabel JLB_precio_ppto = new JLabel("Precio PPTO");
			PNLinea8.add(JLB_precio_ppto);
			
			JTF_precio_ppto = new JTextField();
			JTF_precio_ppto.setText("");
			PNLinea8.add(JTF_precio_ppto);
			JTF_precio_ppto.setColumns(10);
			
			JPanel PNLinea9 = new JPanel();
			PNLinea9.setBackground(new Color(42, 157, 143));
			PNCentral.add(PNLinea9);
			PNLinea9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			JLabel lblNewLabel = new JLabel("Estado");
			PNLinea9.add(lblNewLabel);
			
			JTF_estado = new JTextField();
			PNLinea9.add(JTF_estado);
			JTF_estado.setColumns(10);
			
			
			
			// Panel para los botones del control de registros
			
		JPanel panelBotoneras = new JPanel();
		panelBotoneras.setMaximumSize(new Dimension(1000, 60));
		panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
		
		
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
				BuscarPresupuestosView miBusqueda = new BuscarPresupuestosView(usuario);
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
					contro.borrappto(Integer.parseInt(JTF_id_presu.getText()));	
					Presupuesto presu = contro.primero();
					// cargar cliente en form
					cargaPPTO(presu);
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
					// crea el nuevo concesionario
					int id=Integer.parseInt(JTF_id_presu.getText());
					int id_cli= contro.getidclibydni(JTF_DNI_cli.getText());
					int id_emple=contro.getidemplebynick(JTF_empleado.getText());
					int id_veh=contro.getidvehbymat(JTF_matricula.getText());
					String estado=contro.goToPPTO(Integer.parseInt(JTF_id_presu.getText())).getEstado();
					Presupuesto mipresu=new Presupuesto(id,id_cli,id_emple,JTF_fechappto.getText(),JTF_fecha_validez.getText(),id_veh,Float.parseFloat(JTF_precio_ppto.getText()),estado);
							
					// comprobar si ya existe el registro
					if (contro.comprobarppto(Integer.parseInt(JTF_id_presu.getText()))) {
						// guardar el registro
						contro.updateppto(mipresu);	
					} else {
						// insertar el registro
						contro.addpresu(mipresu);
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
				JTF_nombre_cli.setText("");
				JTF_DNI_cli.setText("");
				JTF_empleado.setText(usuario.getNick());
				JTF_fecha_validez.setText("");
				JTF_fechappto.setText("");
				JTF_matricula.setText("");
				JTF_id_presu.setText( String.valueOf(contro.ultimo().getId()+1));
				JTF_precio_ppto.setText("");
				JTF_nombre_cli.requestFocus();
				JTF_estado.setText("");
				daBotones(false);
			}
		});
		
		BTSalir = new JButton("Salir");
		panelBotonera.add(BTSalir);
		
		BTN_vender = new JButton("Vender");
		BTN_vender.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentasDAO ventas=new VentasDAO();
				Presupuesto presu= contro.goToPPTO(Integer.parseInt(JTF_id_presu.getText()));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.sql.Date fn=null;
				java.sql.Date ppto=null;
		        try {
					Date parsed1 = sdf.parse(presu.getFecha_validez());
					Date parsed = sdf.parse(presu.getFecha_ppto());
					fn = new java.sql.Date(parsed.getDate());
					ppto = new java.sql.Date(parsed.getDate());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        
				Ventas miventa= new Ventas(1,presu.getId_cli(),presu.getId_emple(),ppto,fn,presu.getId_veh(),presu.getPrecio());
				ventas.addVenta(miventa);
				contro.actualizarvendido(Integer.parseInt(JTF_id_presu.getText()));
				cargaPPTO(contro.goToPPTO(Integer.parseInt(JTF_id_presu.getText())));
			}
		});
		panelBotonera.add(BTN_vender);
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
				Presupuesto miconce = contro.primero();
				// cargar vehiculo en form
				cargaPPTO(miconce);
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
				Presupuesto miconce = contro.anterior(JTF_id_presu.getText());
				if (miconce!=null) {
					cargaPPTO(miconce);
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
				Presupuesto miConce = contro.siguiente(JTF_id_presu.getText());
				// cargar cliente en form
				if (miConce!=null) {
					cargaPPTO(miConce);
					refrescaReg();
				}
			}
		});
		
		BTultimo = new JButton(">>");
		BTultimo.setToolTipText("Último registro.");
		BTultimo.setForeground(Color.RED);
		BTultimo.setFont(new Font("Tahoma", Font.BOLD, 8));
		panelRegistros.add(BTultimo);
		
		JPanel PNLinea3 = new JPanel();
		PNCentral.add(PNLinea3);
		PNLinea3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		PNLinea3.setBackground(new Color(42, 157, 143));
		
		JLabel JLB_DNI_cli = new JLabel("DNI Cliente");
		PNLinea3.add(JLB_DNI_cli);
		
		JTF_DNI_cli = new JTextField();
		PNLinea3.add(JTF_DNI_cli);
		JTF_DNI_cli.setColumns(15);
		
			JTF_nombre_cli = new JLabel();
			PNLinea3.add(JTF_nombre_cli);
		
		
			PNCentral.add(panelBotoneras, "cell 0 4");
			
			
			
			
			BTultimo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Presupuesto mipresu = contro.ultimo();
					// cargar cliente en form
					cargaPPTO(mipresu);
					refrescaReg();
				}
			});
			
			
	}
	
	/**
	 * Comprueba si los datos son correctos
	 * @return
	 */
	private boolean comprobardatos() {
		if (JTF_DNI_cli.getText().length()==0 || JTF_empleado.getText().length()==0
			|| JTF_fecha_validez.getText().length()==0|| JTF_fechappto.getText().length()==0 ||
			JTF_matricula.getText().length()==0 || JTF_precio_ppto.getText().length()==0 || JTF_estado.getText().length()==0)
		{
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Sale de la vista
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
		BTN_vender.setEnabled(estado);
		
	}

	/**
	 * Carga el formulario con los datos de un cliente 
	 * @param miCliente
	 */
	protected void cargaPPTO(Presupuesto mipresu) {
		int id=mipresu.getId();
		JTF_id_presu.setText(String.valueOf(id));
		JTF_nombre_cli.setText(contro.getnombrecli(mipresu.getId_cli())+" "+contro.getapellidocli(mipresu.getId_cli()));
		JTF_DNI_cli.setText(contro.getDNIcli(mipresu.getId_cli()));
		JTF_fecha_validez.setText(mipresu.getFecha_validez());
		JTF_fechappto.setText(mipresu.getFecha_ppto());
		JTF_matricula.setText(contro.getmatricula(mipresu.getId_veh()));
		JTF_precio_ppto.setText(String.valueOf(mipresu.getPrecio()));
		JTF_empleado.setText(contro.getnick(mipresu.getId_emple()));
		JTF_estado.setText(mipresu.getEstado());
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
	
	//**************************** fin

}
