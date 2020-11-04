package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

import Models.Usuarios;
import Models.Ventas;

import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JTextField;

import DAO.VentasDAO;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class VerVentasView {

	private JFrame frame;
	private Usuarios miuser;
	private JTextField JTF_cli;
	private JTextField JTF_id_venta;
	private JTextField JTF_empleado;
	private JTextField JTF_fecha_alta;
	private JTextField JTF_fecha_validez;
	private JTextField JTF_matriculavehiculo;
	private JList list;
	private VentasDAO contro;
	/**
	 * Create the application.
	 */
	public VerVentasView(Usuarios user) {
		contro= new VentasDAO();
		miuser=user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel JLB_titulo = new JLabel("Ver ventas");
		panel.add(JLB_titulo);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Usuario Actual");
		panel_1.add(lblNewLabel);
		
		JLabel JLB_useractual = new JLabel("");
		panel_1.add(JLB_useractual);
		JLB_useractual.setText(miuser.getNick());
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		
		
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.EAST);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JLabel JLB_lista = new JLabel("Lista ventas");
		panel_4.add(JLB_lista);
		
		list = new JList();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizardatos();
			}
		});
		panel_4.add(list);
		list.setModel(addelement());
		
		
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new MigLayout("", "[][][grow][grow]", "[][][][][][][][][]"));
		
		JLabel lblNewLabel_1 = new JLabel("Datos venta");
		panel_3.add(lblNewLabel_1, "cell 1 0");
		
		JLabel lblNewLabel_2 = new JLabel("ID Venta");
		panel_3.add(lblNewLabel_2, "cell 1 2,alignx trailing");
		
		JTF_id_venta = new JTextField();
		panel_3.add(JTF_id_venta, "cell 2 2,alignx left");
		JTF_id_venta.setColumns(10);
		
		JLabel JLB_cliente = new JLabel("Cliente");
		panel_3.add(JLB_cliente, "cell 1 3");
		
		JTF_cli = new JTextField();
		panel_3.add(JTF_cli, "cell 2 3,alignx left");
		JTF_cli.setColumns(10);
		
		JLabel JLB_empleado = new JLabel("Empleado");
		panel_3.add(JLB_empleado, "cell 1 4,alignx trailing");
		
		JTF_empleado = new JTextField();
		panel_3.add(JTF_empleado, "cell 2 4,alignx left");
		JTF_empleado.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha alta");
		panel_3.add(lblNewLabel_3, "cell 1 5,alignx trailing");
		
		JTF_fecha_alta = new JTextField();
		panel_3.add(JTF_fecha_alta, "cell 2 5,alignx left");
		JTF_fecha_alta.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha Validez");
		panel_3.add(lblNewLabel_4, "cell 1 6,alignx trailing");
		
		JTF_fecha_validez = new JTextField();
		panel_3.add(JTF_fecha_validez, "cell 2 6,alignx left");
		JTF_fecha_validez.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Matricula vehiculo");
		panel_3.add(lblNewLabel_5, "cell 1 7,alignx trailing");
		
		JTF_matriculavehiculo = new JTextField();
		panel_3.add(JTF_matriculavehiculo, "cell 2 7,alignx left");
		JTF_matriculavehiculo.setColumns(10);
		
		JButton JBT_volver = new JButton("Volver");
		panel_3.add(JBT_volver, "cell 3 8");
		
		
	}
	
	public void actualizardatos() {
		JTF_empleado.setText(list.getSelectedValue().toString());
	}
	
	public DefaultListModel addelement() {
		DefaultListModel model= new DefaultListModel<>();
		//Ventas venta= new Ventas(1,1,1,"ini","fin",(float) 3.0);
		//model.addElement("primer elemento");
		//model.addElement("segundo elemento");
		ArrayList<Integer> lista=contro.getventas();
		for (int cont=0;cont<lista.size();cont++) {
			model.addElement(lista.get(cont));
		}
		return model;
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
