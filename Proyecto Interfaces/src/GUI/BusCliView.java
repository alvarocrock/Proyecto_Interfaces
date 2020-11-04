package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DAO.ClientesDAO;
import Models.Clientes;
import Models.Usuarios;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;

public class BusCliView {
	
	private JFrame frame;
	private Usuarios usuario;
	private ClientesDAO miCliDAO;
	private JLabel LBUsuario;
	private JLabel LBNomUsu;
	private JLabel LBRegistros;
	private JButton BTSeleccionar;
	private JButton BTSalir;
	private JButton BTPrimero;
	private JButton BTAnterior;
	private JButton BTSiguiente;
	private JButton BTultimo;
	private JList<String> LSCliente;
	
	/**
	 * Create the application.
	 */

	public BusCliView(Usuarios miuser) {
		usuario=miuser;
		miCliDAO = new ClientesDAO ();
		initialize();
		// carga usuario
		LBUsuario.setText(usuario.getNick());
		LBNomUsu.setText(usuario.getNick());


	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	// Frame principal
			frame = new JFrame();
			frame.setBounds(100, 100, 470, 300);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(new MigLayout("", "[434px]", "[35px][226px]"));
			
			// panel título
			JPanel PNTitulo = new JPanel();
			PNTitulo.setForeground(Color.BLUE);
			PNTitulo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			PNTitulo.setBackground(Color.LIGHT_GRAY);
			frame.getContentPane().add(PNTitulo, "cell 0 0,growx,aligny top");
			PNTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel LBTitulo = new JLabel("Consulta de clientes");
			LBTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			LBTitulo.setForeground(Color.BLUE);
			PNTitulo.add(LBTitulo);
			
			// Split Panel
			JSplitPane splitPane = new JSplitPane();
			frame.getContentPane().add(splitPane, "cell 0 1,grow");
			
			// Panel Usuario
			JPanel PNUsuario = new JPanel();
			splitPane.setLeftComponent(PNUsuario);
			PNUsuario.setLayout(new MigLayout("", "[91px]", "[14px][14px]"));
			
			
			LBUsuario = new JLabel("Usuario Actual");
			LBUsuario.setForeground(Color.BLUE);
			PNUsuario.add(LBUsuario, "cell 0 0,alignx left,aligny center");
			
			LBNomUsu = new JLabel("Nombre de Usuario");
			PNUsuario.add(LBNomUsu, "cell 0 1,alignx left,aligny center");
			LBNomUsu.setText("Nombre usuario");
			
			// panel central
			JPanel PNCentral = new JPanel();
			splitPane.setRightComponent(PNCentral);
			PNCentral.setLayout(new MigLayout("", "[grow,center]", "[grow][grow][][][][][][]"));

			DefaultListModel<String> listModel = new DefaultListModel <String>();
			// llamar a clienteDAO cargar devolver rst
			// recorrer rst y añadir a la lista (Id, DNI Nombre Apellidos)
			listModel.addElement("Jane Doe");
			listModel.addElement("John Smith");
			listModel.addElement("Kathy Green");
			LSCliente = new JList <String>(listModel);

				
				PNCentral.add(LSCliente, "cell 0 0 1 6,grow");
				
				// panel para los botones de la botonera
				JPanel panelBotonera = new JPanel();
				PNCentral.add(panelBotonera, "cell 0 6");
				
				BTSeleccionar = new JButton("Seleccionar");
				panelBotonera.add(BTSeleccionar);
				BTSeleccionar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
		

					}
				});
				
				BTSalir = new JButton("Salir");
				panelBotonera.add(BTSalir);
				BTSalir.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});
				
				// panel registros
				JPanel panelRegistros = new JPanel();
				panelRegistros.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
				PNCentral.add(panelRegistros, "cell 0 7");
				
				BTPrimero = new JButton("<<");
				BTPrimero.setToolTipText("Primer registro.");
				BTPrimero.setForeground(Color.RED);
				BTPrimero.setFont(new Font("Tahoma", Font.BOLD, 8));
				panelRegistros.add(BTPrimero);
				BTPrimero.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Clientes miCliente = miCliDAO.primero();

					}
				});
				
				BTAnterior = new JButton("<");
				BTAnterior.setToolTipText("Registro anterior.");
				BTAnterior.setForeground(Color.RED);
				BTAnterior.setFont(new Font("Tahoma", Font.BOLD, 8));
				panelRegistros.add(BTAnterior);
				BTAnterior.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Clientes miCliente = miCliDAO.anterior(TFDni.getText());

					}
				});
				
				LBRegistros = new JLabel(" No se han encontrado registros.");
				LBRegistros.setBackground(Color.WHITE);
				LBRegistros.setBorder(new LineBorder(Color.BLUE, 1, true));
				
				panelRegistros.add(LBRegistros);
				
				BTSiguiente = new JButton(">");
				BTSiguiente.setToolTipText("Registro siguiente.");
				BTSiguiente.setForeground(Color.RED);
				BTSiguiente.setFont(new Font("Tahoma", Font.BOLD, 8));
				panelRegistros.add(BTSiguiente);
				BTSiguiente.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Clientes miCliente = miCliDAO.siguiente(TFDni.getText());

					}
				});
				
				BTultimo = new JButton(">>");
				BTultimo.setToolTipText("\u00DAltimo registro.");
				BTultimo.setForeground(Color.RED);
				BTultimo.setFont(new Font("Tahoma", Font.BOLD, 8));
				panelRegistros.add(BTultimo);
				BTultimo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Clientes miCliente = miCliDAO.ultimo();

					}
				});
				
				// Panel para los botones del control de registros
				JPanel panelBotoneras = new JPanel();
				panelBotoneras.setMaximumSize(new Dimension(1000, 60));
				panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
				PNCentral.add(panelBotoneras, "cell 0 5");
				//Border bordeRegistros = BorderFactory.createLineBorder(Color.BLUE,1);
		}

	/*
	 * Get Frame
	 */
	public Window getFrame() {
		return frame;
	}

}
