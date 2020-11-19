package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.TitledBorder;

import Models.Usuarios;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MenuVentasView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Usuarios usuario;
	private ImageIcon imagenbienvenida;
	private ImageIcon imagenuser;

	/**
	 * Create the application.
	 */
	public MenuVentasView(Usuarios miuser) {
		usuario=miuser;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame principal
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.decode("#2A9D8F"));
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		// implementar las teclas en el frame
		KeyListener listener = new MyKeyListener();
		frame.addKeyListener(listener);
		frame.setFocusable(true);
		frame.requestFocus();
		
		// panel título
		JPanel PNTitulo = new JPanel();
		PNTitulo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PNTitulo.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(PNTitulo);
		PNTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		PNTitulo.setBackground(Color.decode("#264653"));
		PNTitulo.setMaximumSize(new Dimension(2000,30));;
		
		JLabel LBTitulo = new JLabel("Menú de ventas");
		LBTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LBTitulo.setForeground(Color.decode("#F4A261"));
		PNTitulo.add(LBTitulo);
		
		JPanel panel_general = new JPanel();
		frame.getContentPane().add(panel_general);
		panel_general.setBackground(Color.decode("#2A9D8F"));
		panel_general.setLayout(new BoxLayout(panel_general, BoxLayout.X_AXIS));
		
		JPanel PNIzquierdo = new JPanel();
		panel_general.add(PNIzquierdo);
		PNIzquierdo.setBackground(Color.decode("#2A9D8F"));
		PNIzquierdo.setLayout(new BoxLayout(PNIzquierdo, BoxLayout.Y_AXIS));
		
		JPanel panel_user_actual = new JPanel();
		panel_user_actual.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 128, 128), Color.ORANGE, null, null));
		PNIzquierdo.add(panel_user_actual);
		panel_user_actual.setBackground(Color.decode("#2A9D8F"));
		panel_user_actual.setBorder(BorderFactory.createEmptyBorder(0,15,15,15));
		panel_user_actual.setLayout(new BoxLayout(panel_user_actual, BoxLayout.Y_AXIS));
		
		JLabel LBCurrUsu = new JLabel("Usuario actual");
		LBCurrUsu.setHorizontalAlignment(SwingConstants.CENTER);
		LBCurrUsu.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		panel_user_actual.add(LBCurrUsu);
		
		imagenuser = new ImageIcon(usuario.getFoto());
		JLabel JLB_image_user = new JLabel(imagenuser);
		JLB_image_user.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		panel_user_actual.add(JLB_image_user);
		
		JLabel JLB_USER_actual = new JLabel("ACTUAL");
		panel_user_actual.add(JLB_USER_actual);
		JLB_USER_actual.setText(usuario.getNick());
		JLB_USER_actual.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		
		JPanel panel_opciones = new JPanel();
		PNIzquierdo.add(panel_opciones);
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
				BusCliView miBuscCli = new BusCliView(usuario);
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
				ConsVeh busqueda= new ConsVeh(usuario);
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
		JLabel JLB_buc_conce = new JLabel("Buscar conce");
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

		
		JPanel PNDerecho = new JPanel();
		panel_general.add(PNDerecho);
		PNDerecho.setBackground(Color.decode("#2A9D8F"));
		PNDerecho.setLayout(new BoxLayout(PNDerecho, BoxLayout.Y_AXIS));
		
		JLabel JLB_vienvenida = new JLabel("");
		JLB_vienvenida.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PNDerecho.add(JLB_vienvenida);
		JLB_vienvenida.setText("Bienvenido al sistema "+usuario.getNick());
		
		imagenbienvenida=new ImageIcon("src/png/bienvenida.png");
		JLabel JLB_imagen = new JLabel(imagenbienvenida);
		PNDerecho.add(JLB_imagen);
		
		frame.pack();
		
	}

	
	/*
	 * salir de la vista
	 */
	protected void salir() {
		frame.dispose();
		LoginView miLogin = new LoginView();
		miLogin.getFrame().setAlwaysOnTop(true);
		miLogin.getFrame().setVisible(true);		
	}

	/*
	 * Getters
	 */
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
	
//************************************************************* fin
}
