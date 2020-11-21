package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import DAO.TrabajoHoyDAO;
import GUI.LoginView.MyKeyListener;
import Models.Usuarios;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuJTallerView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	Usuarios miuser;
	/**
	 * Create the application.
	 * @param usuarios 
	 */
	public MenuJTallerView(Usuarios usuarios) {
		miuser=usuarios;
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
		
		JLabel LBTitulo = new JLabel("Menú jefe de taller");
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
		
		
		JLabel LBUsuario = new JLabel("Usuario Actual");
		LBUsuario.setForeground(Color.BLUE);
		PNUsuario.add(LBUsuario, "cell 0 0,alignx left,aligny center");
		
		JLabel LBNomUsu = new JLabel("Nombre de Usuario");
		PNUsuario.add(LBNomUsu, "cell 0 1,alignx left,aligny center");
		LBNomUsu.setText(miuser.getNick());
		
		// panel central
		JPanel PNCentral = new JPanel();
		splitPane.setRightComponent(PNCentral);
		PNCentral.setLayout(new MigLayout("", "[][][center][center][center]", "[][][]"));
		PNCentral.setBackground(Color.decode("#2A9D8F"));
		
		JButton BT_ficha_cli = new JButton("Ficha clientes   ");
		BT_ficha_cli.setIcon(new ImageIcon(MenuMecanicoView.class.getResource("/png/Clientes.png")));
		PNCentral.add(BT_ficha_cli, "flowx,cell 0 1,alignx right,aligny top");
		BT_ficha_cli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BusCliView miBuscCli = new BusCliView(miuser);
				miBuscCli.getFrame().setAlwaysOnTop(true);
				miBuscCli.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		
		JButton BT_vertrabajo = new JButton("Ver trabajo");
		BT_vertrabajo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarTrabajoView view= new BuscarTrabajoView(miuser);
				view.getFrame().setVisible(true);
				view.getFrame().setAlwaysOnTop(true);
				frame.dispose();
			}
		});
		BT_vertrabajo.setIcon(new ImageIcon(MenuMecanicoView.class.getResource("/png/Trabajo.png")));
		PNCentral.add(BT_vertrabajo, "cell 2 1");
		BT_vertrabajo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BuscarTrabajoView view = new BuscarTrabajoView(miuser);
				view.getFrame().setVisible(true);
				view.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		
		JButton BTAsignar = new JButton("Asignar trabajo");
		BTAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CrearTrabajoView vista= new CrearTrabajoView(miuser);
				vista.getFrame().setVisible(true);
				vista.getFrame().setAlwaysOnTop(true);
				frame.dispose();
			}
		});
		BTAsignar.setIcon(new ImageIcon(MenuJTallerView.class.getResource("/png/RegTra.png")));
		PNCentral.add(BTAsignar, "cell 0 2");
		JButton BTSalir = new JButton("Salir           ");
		PNCentral.add(BTSalir, "cell 2 2");
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

	public void salir() {
		frame.dispose();
		LoginView miLogin = new LoginView();
		miLogin.getFrame().setAlwaysOnTop(true);
		miLogin.getFrame().setVisible(true);		
	}
	
	//******************* fin
}
