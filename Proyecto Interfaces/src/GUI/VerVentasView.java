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
import java.awt.Color;
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
import java.awt.Font;

public class VerVentasView {

	private JFrame frame;
	private Usuarios miuser;
	private JTextField JTF_cli_nombre;
	private JTextField JTF_id_venta;
	private JTextField JTF_empleado;
	private JTextField JTF_fecha_alta;
	private JTextField JTF_fecha_validez;
	private JTextField JTF_matriculavehiculo;
	private JList list;
	private VentasDAO contro;
	private JTextField JTF_precio;
	private JTextField JTF_ape_cli;
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
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setBackground(Color.decode("#264653"));
		
		JLabel JLB_titulo = new JLabel("Ver ventas");
		panel.add(JLB_titulo);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		panel_1.setBackground(Color.decode("#2A9D8F"));
		
		JLabel lblNewLabel = new JLabel("Usuario Actual");
		panel_1.add(lblNewLabel);
		
		JLabel JLB_useractual = new JLabel("");
		panel_1.add(JLB_useractual);
		JLB_useractual.setText(miuser.getNick());
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.setBackground(Color.decode("#2A9D8F"));
		
		
		
		
		JPanel panel_4 = new JPanel();
		//panel_4.setBounds(30,30,40,40);
		panel_2.add(panel_4, BorderLayout.EAST);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		panel_4.setBackground(Color.decode("#2A9D8F"));
		
		JLabel JLB_lista = new JLabel("Lista ventas");
		panel_4.add(JLB_lista);
		
		list = new JList();
		list.setVisibleRowCount(30);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actualizardatos();
			}
		});
		
		JLabel lblNewLabel_6 = new JLabel("ID_venta, cliente, usuario");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 8));
		panel_4.add(lblNewLabel_6);
		panel_4.add(list);
		list.setModel(addelement());
		
		
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new MigLayout("", "[][][grow][grow]", "[][][][][][][][][][][]"));
		panel_3.setBackground(Color.decode("#2A9D8F"));
		
		JLabel lblNewLabel_1 = new JLabel("Datos venta");
		panel_3.add(lblNewLabel_1, "cell 1 0");
		
		JLabel lblNewLabel_2 = new JLabel("ID Venta");
		panel_3.add(lblNewLabel_2, "cell 1 2,alignx trailing");
		
		JTF_id_venta = new JTextField();
		JTF_id_venta.setEditable(false);
		panel_3.add(JTF_id_venta, "cell 2 2,alignx left");
		JTF_id_venta.setColumns(10);
		
		JLabel JLB_nom_cliente = new JLabel("Nombre  Cliente");
		panel_3.add(JLB_nom_cliente, "cell 1 3");
		
		JTF_cli_nombre = new JTextField();
		JTF_cli_nombre.setEditable(false);
		panel_3.add(JTF_cli_nombre, "cell 2 3,alignx left");
		JTF_cli_nombre.setColumns(10);
		
		JButton JBT_volver = new JButton("Volver");
		JBT_volver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuVentasView miMenuV = new MenuVentasView(miuser);
				miMenuV.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		
		JLabel JLB_ape_cli = new JLabel("Apellido cliente");
		panel_3.add(JLB_ape_cli, "cell 1 4,alignx trailing");
		
		JTF_ape_cli = new JTextField();
		JTF_ape_cli.setEditable(false);
		panel_3.add(JTF_ape_cli, "cell 2 4,growx");
		JTF_ape_cli.setColumns(10);
		
		JLabel JLB_empleado = new JLabel("Empleado");
		panel_3.add(JLB_empleado, "cell 1 5,alignx trailing");
		
		JTF_empleado = new JTextField();
		JTF_empleado.setEditable(false);
		panel_3.add(JTF_empleado, "cell 2 5,alignx left");
		JTF_empleado.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Fecha alta");
		panel_3.add(lblNewLabel_3, "cell 1 6,alignx trailing");
		
		JTF_fecha_alta = new JTextField();
		JTF_fecha_alta.setEditable(false);
		panel_3.add(JTF_fecha_alta, "cell 2 6,alignx left");
		JTF_fecha_alta.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Fecha Validez");
		panel_3.add(lblNewLabel_4, "cell 1 7,alignx trailing");
		
		JTF_fecha_validez = new JTextField();
		JTF_fecha_validez.setEditable(false);
		panel_3.add(JTF_fecha_validez, "cell 2 7,alignx left");
		JTF_fecha_validez.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Matricula vehiculo");
		panel_3.add(lblNewLabel_5, "cell 1 8,alignx trailing");
		
		JTF_matriculavehiculo = new JTextField();
		JTF_matriculavehiculo.setEditable(false);
		panel_3.add(JTF_matriculavehiculo, "cell 2 8,alignx left");
		JTF_matriculavehiculo.setColumns(10);
		
		JLabel JLB_precio = new JLabel("                    Precio");
		panel_3.add(JLB_precio, "cell 1 9,alignx trailing");
		
		JTF_precio = new JTextField();
		JTF_precio.setEditable(false);
		panel_3.add(JTF_precio, "cell 2 9,alignx left");
		JTF_precio.setColumns(10);
		panel_3.add(JBT_volver, "cell 3 10");
		
		
	}
	
	public void actualizardatos() {
		String [] Lista=list.getSelectedValue().toString().split("-");
		int id=Integer.parseInt(Lista[0]);
		Ventas miventa=contro.getuserbyid(id);
		JTF_cli_nombre.setText(contro.getnombrecli(miventa.getId_cli()));
		JTF_ape_cli.setText(contro.getapellidocli(miventa.getId_cli()));
		JTF_empleado.setText(contro.getnick(miventa.getId_emple()));
		JTF_id_venta.setText(String.valueOf(miventa.getId_ventas()));
		JTF_matriculavehiculo.setText(contro.getmatricula(miventa.getId_vehiculo()));
		JTF_fecha_alta.setText(miventa.getFechappto());
		JTF_fecha_validez.setText(miventa.getFechavalidez());
		JTF_precio.setText(String.valueOf(miventa.getPrecio()));

		
	}
	
	public DefaultListModel addelement() {
		DefaultListModel model= new DefaultListModel<>();
		//Ventas venta= new Ventas(1,1,1,"ini","fin",(float) 3.0);
		//model.addElement("primer elemento");
		//model.addElement("segundo elemento");
		ArrayList<String> lista=(ArrayList<String>) contro.getventas();
		for (int cont=0;cont<lista.size();cont++) {
			model.addElement(lista.get(cont));
		}
		return model;
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
