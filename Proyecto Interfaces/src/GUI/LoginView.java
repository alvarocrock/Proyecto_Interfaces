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

public class LoginView {

	private JFrame frame;
	private JTextField JTF_usuario;
	private JTextField JTF_passwd;
	private UsuarioDAO miuserdao;
	

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
		
		JLabel label = new JLabel("");
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("");
		panel_1.add(label_1);
		
		JLabel JLB_usuario = new JLabel("Usuario");
		panel_1.add(JLB_usuario);
		
		JTF_usuario = new JTextField();
		panel_1.add(JTF_usuario);
		JTF_usuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Contrase\u00F1a");
		panel_1.add(lblNewLabel);
		
		JTF_passwd = new JTextField();
		JTF_passwd.setColumns(10);
		panel_1.add(JTF_passwd);
		
		JLabel label_2 = new JLabel("");
		panel_1.add(label_2);
		
		
		JButton BTN_iniciar_sesion = new JButton("IniciarSesion");
		BTN_iniciar_sesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (miuserdao.compobarlogin(JTF_usuario.getText(),JTF_passwd.getText())==true) {
					JOptionPane.showMessageDialog(null, "login correcto", "resultado login", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
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
	
}
