package GUI;


import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.UsuarioDAO;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class LoginView {

	private JFrame frame;
	private JTextField JTF_usuario;
	private UsuarioDAO miuserdao;
	private JPasswordField JPass;
	

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel JLB_login = new JLabel("Login");
		JLB_login.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(JLB_login);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(5, 5, 0, 4));
		
		
		
		JLabel JLB_usuario = new JLabel("Usuario");
		panel_1.add(JLB_usuario);
		
		JTF_usuario = new JTextField();
		panel_1.add(JTF_usuario);
		JTF_usuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Contrase\u00F1a");
		panel_1.add(lblNewLabel);
		
		JPass = new JPasswordField();
		panel_1.add(JPass);
		
		JLabel label_2 = new JLabel("");
		panel_1.add(label_2);
		
		
		JButton BTN_iniciar_sesion = new JButton("IniciarSesion");
		BTN_iniciar_sesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (miuserdao.compobarlogin(JTF_usuario.getText(),JPass.getText())==true) {
					frame.setVisible(false);
					JOptionPane.showMessageDialog(null, "login correcto", "resultado login", JOptionPane.INFORMATION_MESSAGE);
					navegacion();
				} else {
					JOptionPane.showMessageDialog(null, "login incorrecto", "resultado login", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		
		
		panel_1.add(BTN_iniciar_sesion);
		
		// el frame se hace visible desde el controlador
		// frame.setVisible(true);
		frame.pack();
	}

	/**
	 * Get JFrame
	 * @return
	 */
	public JFrame getFrame() {
		return frame;
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
				MenuJTallerView miMenuJT = new MenuJTallerView(miuserdao.getuser());
				miMenuJT.getFrame().setVisible(true);
				break;
			case "jefeTaller":
				//llamar a la GUI de menú inicial jefe de taller
				
				break;
			default:
				System.out.println("¿Comorrrrrrrr?");
		}			
	}
}
