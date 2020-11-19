package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import DAO.TrabajoHoyDAO;
import Models.Usuarios;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class CrearTrabajoView {

	private JFrame frame;
	protected TrabajoHoyDAO contro;
	protected Usuarios miuser;
	private JTextField JTF_DNI;
	private JTextField JTF_empleado;
	private JTextField JTF_matricula;
	private JTextField JTF_precio;
	private JTextField JTF_id;
	
	/**
	 * Create the application.
	 */
	public CrearTrabajoView(Usuarios user) {
		miuser=user;
		contro=new TrabajoHoyDAO();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel_titulo = new JPanel();
		frame.getContentPane().add(panel_titulo);
		panel_titulo.setLayout(new BoxLayout(panel_titulo, BoxLayout.X_AXIS));
		
		JLabel Titulo = new JLabel("Crear trabajo para explotar");
		panel_titulo.add(Titulo);
		
		JPanel panel_contenido = new JPanel();
		frame.getContentPane().add(panel_contenido);
		panel_contenido.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_izquierdo = new JPanel();
		panel_contenido.add(panel_izquierdo);
		panel_izquierdo.setLayout(new BoxLayout(panel_izquierdo, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel_izquierdo.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Usuario Actual");
		panel.add(lblNewLabel);
		
		BufferedImage img;
		
		try {
			img = ImageIO.read(new File(miuser.getFoto()));
			ImageIcon icon = new ImageIcon(img);
			JLabel JLB_foto = new JLabel(icon);
			panel_izquierdo.add(JLB_foto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel JLB_nombre = new JLabel(miuser.getNick());
		panel_izquierdo.add(JLB_nombre);
		
		
		JPanel Panel_derecho = new JPanel();
		panel_contenido.add(Panel_derecho);
		Panel_derecho.setLayout(new BoxLayout(Panel_derecho, BoxLayout.Y_AXIS));
		
		JPanel panel_datos_1 = new JPanel();
		Panel_derecho.add(panel_datos_1);
		panel_datos_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel JLB_dni_cli = new JLabel("DNI Cliente");
		panel_datos_1.add(JLB_dni_cli);
		
		JTF_DNI = new JTextField();
		panel_datos_1.add(JTF_DNI);
		JTF_DNI.setColumns(10);
		
		JLabel JLb_emplado = new JLabel("Empleado");
		panel_datos_1.add(JLb_emplado);
		
		JTF_empleado = new JTextField();
		panel_datos_1.add(JTF_empleado);
		JTF_empleado.setColumns(10);
		
		JLabel JLB_matricula = new JLabel("Matricula");
		panel_datos_1.add(JLB_matricula);
		
		JTF_matricula = new JTextField();
		panel_datos_1.add(JTF_matricula);
		JTF_matricula.setColumns(10);
		
		JPanel panel_datos_2 = new JPanel();
		Panel_derecho.add(panel_datos_2);
		
		JLabel JLB_precio = new JLabel("Precio");
		panel_datos_2.add(JLB_precio);
		
		JTF_precio = new JTextField();
		panel_datos_2.add(JTF_precio);
		JTF_precio.setColumns(10);
		
		JLabel JLB_id = new JLabel("Identificador");
		panel_datos_2.add(JLB_id);
		
		JTF_id = new JTextField();
		panel_datos_2.add(JTF_id);
		JTF_id.setColumns(10);
		
		JPanel panel_datos_3 = new JPanel();
		Panel_derecho.add(panel_datos_3);
		panel_datos_3.setLayout(new BoxLayout(panel_datos_3, BoxLayout.Y_AXIS));
		
		JLabel JLb_desc = new JLabel("Descripci\u00F3n");
		panel_datos_3.add(JLb_desc);
		
		JTextArea JTA_desc= new JTextArea();
		JTA_desc.setRows(8);
		panel_datos_3.add(JTA_desc);
		
		JPanel panel_botonera = new JPanel();
		Panel_derecho.add(panel_botonera);
		
		JButton BTN_aceptar = new JButton("Aceptar");
		panel_botonera.add(BTN_aceptar);
		
		JButton BTN_volver = new JButton("Volver");
		panel_botonera.add(BTN_volver);
	}

}
