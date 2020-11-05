package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.sun.glass.events.MouseEvent;

import DAO.ClientesDAO;
import DAO.VehiculosDAO;
import Models.Clientes;
import Models.Usuarios;
import net.miginfocom.swing.MigLayout;

public class ConsVeh {

	private JFrame frame;
	private Usuarios usuario;
	private VehiculosDAO miVehDAO;
	private JLabel LBUsuario;
	private JLabel LBNomUsu;
	private JLabel LBRegistros;
	private DefaultListModel<String> listModel;
	private JList<String> LSVehi;
	private JLabel LBTitLista;
	private JButton BTSeleccionar;
	private JButton BTSalir;
	private JButton BTAnterior;
	private JButton BTSiguiente;
	private JButton BTultimo;

	/**
	 * Create the application.
	 */
	public ConsVeh(Usuarios miuser) {
		usuario=miuser;
		miVehDAO = new VehiculosDAO();
		initialize();
		// carga usuario
		LBUsuario.setText(usuario.getNick());
		LBNomUsu.setText(usuario.getNick());
		refrescaReg();

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
			
			JLabel LBTitulo = new JLabel("Consulta de vehículos");
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
			PNCentral.setLayout(new MigLayout("", "[grow,left]", "[][grow][grow][][][][][][]"));

				// Crea el modelo de lista
				listModel = new DefaultListModel <String>();
				// llama a clienteDAO devuelve rst con los datos y llama a cargar la lista
				miVehDAO = new VehiculosDAO();
				cargaLista(miVehDAO.cargaListaDAO());
				// crea y configura la Jlista
				LSVehi = new JList <String>(listModel);
				LSVehi.setBorder(BorderFactory.createEmptyBorder(5, 5	, 5, 5));
				LSVehi.setVisibleRowCount(5);
				LSVehi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				LSVehi.setSelectedIndex(0);
				LSVehi.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
			            if (me.getClickCount() == 2) {
	
			            }
			         }
				});
				
				// label de la lista
				LBTitLista = new JLabel("ID        Matrícula       Marca              Modelo              Precio");
				PNCentral.add(LBTitLista, "cell 0 0");
				PNCentral.add(LSVehi, "cell 0 1 1 6,grow");
				
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
				
				JButton BTPrimero = new JButton("<<");
				BTPrimero.setToolTipText("Primer registro.");
				BTPrimero.setForeground(Color.RED);
				BTPrimero.setFont(new Font("Tahoma", Font.BOLD, 8));
				panelRegistros.add(BTPrimero);
				BTPrimero.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						LSVehi.setSelectedIndex(0);
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
						if (LSVehi.getSelectedIndex()>0) LSVehi.setSelectedIndex(LSVehi.getSelectedIndex()-1);
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
						if (LSVehi.getSelectedIndex() < LSVehi.getModel().getSize()-1) 
							LSVehi.setSelectedIndex(LSVehi.getSelectedIndex()+1);
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
						LSVehi.setSelectedIndex(LSVehi.getModel().getSize()-1);
						refrescaReg();
					}
				});
				
				// Panel para los botones del control de registros
				JPanel panelBotoneras = new JPanel();
				panelBotoneras.setMaximumSize(new Dimension(1000, 60));
				panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
				PNCentral.add(panelBotoneras, "cell 0 6");
		}

	/**
	 * llama a ficha de vehículos con el  cliente seleccionado
	 */
	protected void seleccionar() {
		// coger id_vehiculo de la lista
		String linea = LSVehi.getSelectedValue();
		String campos [] = linea.split(" | ");
		int idVeh=Integer.parseInt(campos[0]);
		// llamada a ficha vehículos con el id_vehiculo
		frame.dispose();
		RegistroVehiculosView miRegVeh = new RegistroVehiculosView(usuario,idVeh);
		miRegVeh.getFrame().setAlwaysOnTop(true);
		miRegVeh.getFrame().setVisible(true);
	}
	
	/**
	 * recorre array y añade a la lista (Id, matriculo marca modelo precio)
	 * @param Array con los datos
	 */
	private void cargaLista(ArrayList<String> miArray) {
		// recorre array y añade a la lista (Id, matriculo marca modelo precio)
		for (int i=0;i<miArray.size();i++) {
			listModel.addElement(miArray.get(i));
		}
		
	}
	
	/**
	 * Refresca el label de control de registros
	 */
	private void refrescaReg() {
		// coger id_cli de la lista
		String linea = LSVehi.getSelectedValue();
		String campos [] = linea.split(" | ");
		int idVeh=Integer.parseInt(campos[0]);
		String p="Registro " + idVeh + " de "+ LSVehi.getModel().getSize()+".";
		LBRegistros.setText(p);	
	}
	
	/*
	 * Get Frame
	 */
	public Window getFrame() {
		return frame;
	}

}


