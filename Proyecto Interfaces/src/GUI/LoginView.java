package GUI;


import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.UsuarioDAO;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;

public class LoginView {

	private JFrame frame;
	private UsuarioDAO miuserdao;
	private JTextField JTF_usuario;
	private ImageIcon miimagen;
	private JPasswordField Jpass;
	

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
		miuserdao= new UsuarioDAO();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// el frame se hace visible desde el controlador
		// frame.setVisible(true);
		frame.pack();
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		
		JPanel Panel_login = new JPanel();
		panel.add(Panel_login, BorderLayout.NORTH);
		Panel_login.setBackground(Color.decode("#264653"));
		
		JLabel JLB_login = new JLabel("Login");
		JLB_login.setForeground(Color.ORANGE);
		JLB_login.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		Panel_login.add(JLB_login);
		
		JPanel Panel_central = new JPanel();
		panel.add(Panel_central, BorderLayout.CENTER);
		Panel_central.setBackground(Color.decode("#2A9D8F"));
		
		JPanel Panel_contenedor = new JPanel();
		Panel_central.add(Panel_contenedor);
		Panel_contenedor.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		Panel_contenedor.setBackground(Color.decode("#2A9D8F"));
		
		JPanel panel_imagen = new JPanel();
		Panel_contenedor.add(panel_imagen);
		panel_imagen.setBackground(Color.decode("#2A9D8F"));
		
		miimagen= new ImageIcon("src/png/logo_login.png");
		JLabel JLB_imagen = new JLabel(miimagen);
		panel_imagen.add(JLB_imagen);
		
		
		
		
		
		JPanel panel_login = new JPanel();
		Panel_contenedor.add(panel_login);
		panel_login.setLayout(new BorderLayout(0, 0));
		panel_login.setBackground(Color.decode("#2A9D8F"));
		
		JPanel Panel_usuario = new JPanel();
		panel_login.add(Panel_usuario, BorderLayout.NORTH);
		Panel_usuario.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		Panel_usuario.setBackground(Color.decode("#2A9D8F"));
		
		JLabel JLB_usuario = new JLabel("Usuario");
		JLB_usuario.setFont(new Font("Tahoma", Font.BOLD, 10));
		Panel_usuario.add(JLB_usuario);
		
		JTF_usuario = new JTextField();
		JTF_usuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
	                   login();
	                }
	                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
	                	if(JOptionPane.showConfirmDialog(frame, "¿Seguro que quiere salir de la aplicación?", 
	                			"Salir", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
	                		System.exit(0);;
	                	}
					}
			}
		});
		Panel_usuario.add(JTF_usuario);
		JTF_usuario.setColumns(10);
		
		JPanel panel_passwd = new JPanel();
		panel_login.add(panel_passwd, BorderLayout.CENTER);
		panel_passwd.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_passwd.setBackground(Color.decode("#2A9D8F"));
		
		JLabel JLB_passwd = new JLabel("Contrase\u00F1a");
		panel_passwd.add(JLB_passwd);
		
		Jpass = new JPasswordField();
		Jpass.setColumns(10);
		panel_passwd.add(Jpass);
		Jpass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
	                   login();
	                }
	                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
	                	if(JOptionPane.showConfirmDialog(frame, "¿Seguro que quiere salir de la aplicación?", 
	                			"Salir", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
	                		System.exit(0);;
	                	}
					}
			}
		});
		
		JPanel panel_boton = new JPanel();
		panel_login.add(panel_boton, BorderLayout.SOUTH);
		panel_boton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_boton.setBackground(Color.decode("#2A9D8F"));
		
		JButton JBT_buscar = new JButton("Iniciar Sesi\u00F3n");
		JBT_buscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
			}
		});
		JBT_buscar.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
	                   login();
	                }
	                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
	                	if(JOptionPane.showConfirmDialog(frame, "¿Seguro que quiere salir de la aplicación?", 
	                			"Salir", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
	                		System.exit(0);;
	                	}
					}				
			}			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		
		panel_boton.add(JBT_buscar);
		
		frame.pack();
	}

	/**
	 * Get JFrame
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	public void login() {
		if (miuserdao.compobarlogin(JTF_usuario.getText(),Jpass.getText())==true) {
			frame.setVisible(false);
			navegacion();
		} else {
			Jpass.setBackground(Color.decode("#FF0000"));
		}
	}
	/**
	 * navegación al menú inicial
	 */
	private void navegacion() {
		switch ( miuserdao.getuser().getRango()) {
			case "vendedor":
				//llamar a la GUI de menú inicial ventas
				MenuVentasView miMenuV = new MenuVentasView(miuserdao.getuser());
				miMenuV.getFrame().setVisible(true);
				break;
			case "mecanico":
				//llamar a la GUI de menú inicial taller
				MenuMecanicoView miMenuM = new MenuMecanicoView(miuserdao.getuser());
				miMenuM.getFrame().setVisible(true);
				break;
			case "jefe":
				//llamar a la GUI de menú inicial Jefe

				break;
			case "jefeTaller":
				//llamar a la GUI de menú inicial jefe de taller
				MenuJTallerView miMenuJT = new MenuJTallerView(miuserdao.getuser());
				miMenuJT.getFrame().setVisible(true);
				
				break;
			default:
				System.out.println("¿Comorrrrrrrr?");
		}			
	}
}
