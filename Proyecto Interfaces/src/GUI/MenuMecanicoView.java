package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import GUI.LoginView.MyKeyListener;
import Models.Usuarios;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MenuMecanicoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Usuarios miUsuario;

	/**
	 * Create the application.
	 */
	public MenuMecanicoView(Usuarios Usuario) {
		miUsuario=Usuario;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame principal
		frame = new JFrame();
		frame.setBounds(100, 100, 463, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[434px]", "[35px][226px]"));
		
		// implementar las teclas en el frame
		KeyListener listener = new MyKeyListener();
		frame.addKeyListener(listener);
		frame.setFocusable(true);
		frame.requestFocus();
		
		// panel título
		JPanel PNTitulo = new JPanel();
		PNTitulo.setForeground(Color.ORANGE);
		PNTitulo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PNTitulo.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(PNTitulo, "cell 0 0,growx,aligny top");
		PNTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		PNTitulo.setBackground(Color.decode("#264653"));
		
		JLabel LBTitulo = new JLabel("Menú de taller");
		LBTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LBTitulo.setForeground(Color.BLUE);
		PNTitulo.add(LBTitulo);
		
		// Split Panel
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, "cell 0 1,grow");
		
		// Panel Usuario
		JPanel PNUsuario = new JPanel();
		splitPane.setLeftComponent(PNUsuario);
		PNUsuario.setLayout(new MigLayout("", "[91px]", "[14px][14px][][][][]"));
		PNUsuario.setBackground(Color.decode("#2A9D8F"));
		PNUsuario.setBackground(Color.decode("#2A9D8F"));
		
		
		JLabel LBUsuario = new JLabel("Usuario Actual");
		LBUsuario.setForeground(Color.BLUE);
		PNUsuario.add(LBUsuario, "cell 0 0,alignx left,aligny center");
		
		JLabel LBNomUsu = new JLabel("Nombre de Usuario");
		PNUsuario.add(LBNomUsu, "cell 0 1,alignx left,aligny center");
		LBNomUsu.setText(miUsuario.getNick());
		
		// panel central
		JPanel PNCentral = new JPanel();
		splitPane.setRightComponent(PNCentral);
		PNCentral.setLayout(new MigLayout("", "[][][center][center][center]", "[][][]"));
		PNCentral.setBackground(Color.decode("#2A9D8F"));
		PNCentral.setBackground(Color.decode("#2A9D8F"));
		
		JButton JBNBusc_cli = new JButton("Ficha clientes");
		JBNBusc_cli.setIcon(new ImageIcon(MenuMecanicoView.class.getResource("/png/Clientes.png")));
		PNCentral.add(JBNBusc_cli, "flowx,cell 0 1,alignx right,aligny top");
		JBNBusc_cli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BusCliView miFichaClientes = new BusCliView(frame,miUsuario);
				miFichaClientes.getFrame().setAlwaysOnTop(true);
				miFichaClientes.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JButton JBTN_vertrabajo = new JButton("Ver trabajo");
		JBTN_vertrabajo.setIcon(new ImageIcon(MenuMecanicoView.class.getResource("/png/Trabajo.png")));
		PNCentral.add(JBTN_vertrabajo, "cell 2 1");
		JBTN_vertrabajo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BuscarTrabajoView miVerTra = new BuscarTrabajoView(miUsuario);
				miVerTra.getFrame().setAlwaysOnTop(true);
				miVerTra.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		
		JButton BTSalir = new JButton("Salir");
		PNCentral.add(BTSalir, "cell 0 2");
		BTSalir.setSelectedIcon(new ImageIcon(MenuMecanicoView.class.getResource("/png/Salida.png")));
		BTSalir.setIcon(new ImageIcon(MenuMecanicoView.class.getResource("/png/Salida.png")));
		BTSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				salir();
			}
		});
	}
	
	/*
	 * salir de la view
	 */
	protected void salir() {
		frame.dispose();
		LoginView miLogin = new LoginView();
		miLogin.getFrame().setAlwaysOnTop(true);
		miLogin.getFrame().setVisible(true);		
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
	
	//******************* fin
}


