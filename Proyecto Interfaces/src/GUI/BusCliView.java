package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;



import DAO.ClientesDAO;
import Models.Usuarios;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import java.awt.event.MouseEvent;

public class BusCliView {
	
	private JFrame frame;
	private Usuarios usuario;
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
	private ClientesDAO miClienteDAO;
	private DefaultListModel<String> listModel;
	private JLabel LBL_idbusc;
	private JTextField JTF_ID_busqueda;
	private JButton BTBuscar;
	
	/**
	 * Create the application.
	 */

	public BusCliView(Usuarios miuser) {
		usuario=miuser;
		new ClientesDAO ();
		initialize();
		// carga usuario
		LBUsuario.setText(usuario.getNick());
		LBNomUsu.setText(usuario.getNick());
		//refrescaReg();


	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	// Frame principal
			frame = new JFrame();
			frame.setBounds(100, 100, 470, 500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(new MigLayout("", "[434px]", "[35px][226px]"));
			
			// panel título
			JPanel PNTitulo = new JPanel();
			PNTitulo.setForeground(Color.BLUE);
			PNTitulo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			PNTitulo.setBackground(Color.LIGHT_GRAY);
			frame.getContentPane().add(PNTitulo, "cell 0 0,growx,aligny top");
			PNTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			PNTitulo.setBackground(Color.decode("#264653"));
			
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
			PNUsuario.setBackground(Color.decode("#2A9D8F"));
			
			
			LBUsuario = new JLabel("Usuario Actual");
			LBUsuario.setForeground(Color.BLUE);
			PNUsuario.add(LBUsuario, "cell 0 0,alignx left,aligny center");
			
			LBNomUsu = new JLabel("Nombre de Usuario");
			PNUsuario.add(LBNomUsu, "cell 0 1,alignx left,aligny center");
			LBNomUsu.setText("Nombre usuario");
			
			// panel central
			JPanel PNCentral = new JPanel();
			splitPane.setRightComponent(PNCentral);
			PNCentral.setLayout(new MigLayout("", "[grow,center]", "[][grow][grow][][][][][][]"));
			PNCentral.setBackground(Color.decode("#2A9D8F"));

				// Crea el modelo de lista
				listModel = new DefaultListModel <String>();
				// llama a clienteDAO devuelve rst con los datos y llama a cargar la lista
				miClienteDAO = new ClientesDAO();
				cargaLista(miClienteDAO.cargaListaDAO());
				
				LBL_idbusc = new JLabel("Introduce un id a buscar");
				PNCentral.add(LBL_idbusc, "flowx,cell 0 0");
				// crea y configura la Jlista
				LSCliente = new JList <String>(listModel);
				LSCliente.setBorder(BorderFactory.createEmptyBorder(5, 5	, 5, 5));
				LSCliente.setVisibleRowCount(5);
				LSCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				PNCentral.add(LSCliente, "cell 0 1 1 6,grow");
				
				// panel para los botones de la botonera
				JPanel panelBotonera = new JPanel();
				PNCentral.add(panelBotonera, "cell 0 7");
				
				BTSeleccionar = new JButton("Seleccionar");
				panelBotonera.add(BTSeleccionar);
				BTSeleccionar.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						seleccionar();
					}
				});
				
				BTSalir = new JButton("Salir");
				panelBotonera.add(BTSalir);
				BTSalir.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						MenuVentasView miMenuVentas = new MenuVentasView(usuario);
						miMenuVentas.getFrame().setAlwaysOnTop(true);
						miMenuVentas.getFrame().setVisible(true);
					}
				});
				
				// panel registros
				JPanel panelRegistros = new JPanel();
				panelRegistros.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
				PNCentral.add(panelRegistros, "cell 0 8");
				
				BTPrimero = new JButton("<<");
				BTPrimero.setToolTipText("Primer registro.");
				BTPrimero.setForeground(Color.RED);
				BTPrimero.setFont(new Font("Tahoma", Font.BOLD, 8));
				panelRegistros.add(BTPrimero);
				BTPrimero.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						LSCliente.setSelectedIndex(0);
						refrescaReg();
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
						if (LSCliente.getSelectedIndex()>0) LSCliente.setSelectedIndex(LSCliente.getSelectedIndex()-1);
						refrescaReg();
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
						if (LSCliente.getSelectedIndex() < LSCliente.getModel().getSize()-1) 
							LSCliente.setSelectedIndex(LSCliente.getSelectedIndex()+1);
						refrescaReg();
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
						LSCliente.setSelectedIndex(LSCliente.getModel().getSize()-1);
						refrescaReg();
					}
				});
				
				// Panel para los botones del control de registros
				JPanel panelBotoneras = new JPanel();
				panelBotoneras.setMaximumSize(new Dimension(1000, 60));
				panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
				PNCentral.add(panelBotoneras, "cell 0 6");
				panelBotonera.setBackground(Color.decode("#2A9D8F"));
				panelBotoneras.setBackground(Color.decode("#2A9D8F"));
				
				// celda busqueda
				JTF_ID_busqueda = new JTextField();
				PNCentral.add(JTF_ID_busqueda, "cell 0 0");
				JTF_ID_busqueda.setColumns(10);
				
				BTBuscar = new JButton("Buscar");
				
				BTBuscar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						buscar(JTF_ID_busqueda.getText());
					}
				});
				PNCentral.add(BTBuscar, "cell 0 0");
		}

	/**
	 * llama a ficha de clientes con el  cliente seleccionado
	 */
	protected void seleccionar() {
		// coger id_cli de la lista
		String linea = LSCliente.getSelectedValue();
		String campos [] = linea.split(" | ");
		int idCli=Integer.parseInt(campos[0]);
		// llamada a menu ventas con el idcli
		FichaClienteView miMenuVentas = new FichaClienteView(usuario,idCli);
		miMenuVentas.getFrame().setAlwaysOnTop(true);
		miMenuVentas.getFrame().setVisible(true);
	}
	
	/**
	 * recorre array y añade a la lista (Id, DNI Nombre Apellidos)
	 * @param cargaListaDAO
	 */
	private void cargaLista(ArrayList<String> miArray) {
		// recorre array y añade a la lista (Id, DNI Nombre Apellidos)
		for (int i=0;i<miArray.size();i++) {
			listModel.addElement(miArray.get(i));
		}
		
	}
	
	private void buscar(String id) {
		listModel.clear();
		ArrayList<String> miArray=miClienteDAO.cargaListaDAO();
		for (int i=0;i<miArray.size();i++) {
			String campos [] = miArray.get(i).toString().split(" | ");
			if (campos[0].matches(id)) {
			listModel.addElement(miArray.get(i));
			}
		}
	}
	
	/**
	 * Refresca el label de control de registros
	 */
	private void refrescaReg() {
		// coger id_cli de la lista
		String linea = LSCliente.getSelectedValue();
		String campos [] = linea.split(" | ");
		int idCli=Integer.parseInt(campos[0]);
		String p="Registro " + idCli + " de "+ LSCliente.getModel().getSize()+".";
		LBRegistros.setText(p);	
	}
	/*
	 * Get Frame
	 */
	public Window getFrame() {
		return frame;
	}

}
