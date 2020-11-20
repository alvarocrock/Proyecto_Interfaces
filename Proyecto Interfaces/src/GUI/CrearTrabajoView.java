package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import DAO.TrabajoHoyDAO;
import Models.Presupuesto;
import Models.Reparacion;
import Models.Usuarios;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrearTrabajoView {

	private JFrame frame;
	protected TrabajoHoyDAO contro;
	protected Usuarios miuser;
	private JTextField JTF_DNI;
	private JTextField JTF_empleado;
	private JTextField JTF_matricula;
	private JTextField JTF_precio;
	private JTextArea JTA_desc;
	
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
		frame.setBackground(Color.WHITE);
		
		JPanel panel_general = new JPanel();
		frame.getContentPane().add(panel_general);
		panel_general.setLayout(new BoxLayout(panel_general, BoxLayout.Y_AXIS));
		panel_general.setBackground(Color.decode("#264653"));
		frame.getContentPane().add(panel_general);
		
		JPanel panel_titulo = new JPanel();
		panel_general.add(panel_titulo);
		panel_titulo.setBackground(Color.decode("#264653"));
		panel_titulo.setLayout(new BoxLayout(panel_titulo, BoxLayout.X_AXIS));
		
		JLabel Titulo = new JLabel("Crear trabajo para explotar");
		panel_titulo.add(Titulo);
		
		JPanel panel_contenido = new JPanel();
		panel_general.add(panel_contenido);
		panel_contenido.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_contenido.setBackground(Color.decode("#2A9D8F"));
		
		JPanel panel_izquierdo = new JPanel();
		panel_contenido.add(panel_izquierdo);
		panel_izquierdo.setLayout(new BoxLayout(panel_izquierdo, BoxLayout.Y_AXIS));
		panel_izquierdo.setBackground(Color.decode("#2A9D8F"));
		
		
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
		panel_contenido.setBackground(Color.decode("#2A9D8F"));
		
		JPanel panel_datos_1 = new JPanel();
		Panel_derecho.add(panel_datos_1);
		panel_datos_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_datos_1.setBackground(Color.decode("#2A9D8F"));
		
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
		panel_datos_2.setBackground(Color.decode("#2A9D8F"));
		
		JLabel JLB_precio = new JLabel("Precio");
		panel_datos_2.add(JLB_precio);
		
		JTF_precio = new JTextField();
		panel_datos_2.add(JTF_precio);
		JTF_precio.setColumns(10);
		
		JPanel panel_datos_3 = new JPanel();
		Panel_derecho.add(panel_datos_3);
		panel_datos_3.setLayout(new BoxLayout(panel_datos_3, BoxLayout.Y_AXIS));
		panel_datos_3.setBackground(Color.decode("#2A9D8F"));
		
		JLabel JLb_desc = new JLabel("Descripci\u00F3n");
		panel_datos_3.add(JLb_desc);
		
		JTA_desc = new JTextArea();
		JTA_desc.setLineWrap(true);
		JTA_desc.setTabSize(0);
		JTA_desc.setRows(8);
		panel_datos_3.add(JTA_desc);
		
		
		
		JPanel panel_botonera = new JPanel();
		Panel_derecho.add(panel_botonera);
		panel_botonera.setBackground(Color.decode("#2A9D8F"));
		
		JButton BTN_aceptar = new JButton("Aceptar");
		BTN_aceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addtarea();
			}
		});
		panel_botonera.add(BTN_aceptar);
		
		JButton BTN_volver = new JButton("Volver");
		BTN_volver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuJTallerView vista= new MenuJTallerView(miuser);
				vista.getFrame().setVisible(true);
				vista.getFrame().setAlwaysOnTop(true);
				frame.dispose();
			}
		});
		panel_botonera.add(BTN_volver);
		
		
		
		frame.pack();
	}
	
	private void addtarea() {
		//creamos un bojeto de reparacion con el contructor sin fecha fn y inicio y tiempo
		int id=contro.getlast();
		int id_cli=contro.getidclibydni(JTF_DNI.getText());
		int id_jef=miuser.getId();
		int id_mec=contro.getidmec(JTF_empleado.getText());
		String desc=JTA_desc.getText();
		Date fecha=new Date();
		String fecha_crea=1900+fecha.getYear()+"-"+fecha.getMonth()+"-"+fecha.getDay();
		int id_veh=contro.getidveh(JTF_matricula.getText());
		float precio=Float.valueOf(JTF_precio.getText());
		Reparacion r= new Reparacion(id,id_cli,id_jef,id_mec,desc,fecha_crea,id_veh,precio);
		
		contro.add(r);
	}
	
	public Window getFrame() {
		return frame;
	}
	

}
