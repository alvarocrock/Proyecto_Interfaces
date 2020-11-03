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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.sun.webkit.ContextMenu.ShowContext;

import DAO.ClientesDAO;
import Models.Usuarios;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

public class FichaClienteView {

	private JFrame frame;
	private Usuarios usuario;
	private ClientesDAO miCliDAO;
	private JTextField TFIdCli;
	/**
	 * Create the application.
	 */
	public FichaClienteView(Usuarios miuser) {
		usuario=miuser;
		miCliDAO = new ClientesDAO ();
		initialize();
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
		
		JLabel LBTitulo = new JLabel("Ficha de clientes");
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
		
		
		JLabel LBUsuario = new JLabel("Usuario Actual");
		LBUsuario.setForeground(Color.BLUE);
		PNUsuario.add(LBUsuario, "cell 0 0,alignx left,aligny center");
		
		JLabel LBNomUsu = new JLabel("Nombre de Usuario");
		PNUsuario.add(LBNomUsu, "cell 0 1,alignx left,aligny center");
		LBNomUsu.setText("Nombre usuario");
		
		// panel central
		JPanel PNCentral = new JPanel();
		splitPane.setRightComponent(PNCentral);
		PNCentral.setLayout(new MigLayout("", "[grow,center]", "[grow][][][][][][]"));
		
			// panel linea 1
			JPanel PNLinea1 = new JPanel();
			PNCentral.add(PNLinea1, "cell 0 0,grow");
			PNLinea1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			JLabel LBDni = new JLabel("DNI/NIE");
			PNLinea1.add(LBDni);
			
				JTextField TFDni = new JTextField();
				PNLinea1.add(TFDni);
				TFDni.setColumns(10);
				
				JLabel LBNombre = new JLabel("Nombre");
				PNLinea1.add(LBNombre);
				
					JTextField TFNombre = new JTextField();
					PNLinea1.add(TFNombre);
					TFNombre.setColumns(10);
			
			// panel linea 2
			JPanel PNLinea2 = new JPanel();
			PNCentral.add(PNLinea2, "cell 0 1,grow");
			PNLinea2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				
				JLabel LBApellidos = new JLabel("Apellidos");
				PNLinea2.add(LBApellidos);
				
					JTextField TFApellidos = new JTextField();
					PNLinea2.add(TFApellidos);
					TFApellidos.setColumns(10);
					
					JLabel LBDir = new JLabel("Dirección");
					PNLinea2.add(LBDir);
					
						JTextField TFDir = new JTextField();
						PNLinea2.add(TFDir);
						TFDir.setColumns(10);
			
			// panel linea 3
			JPanel PNLinea3 = new JPanel();
			PNCentral.add(PNLinea3, "cell 0 2,grow");
			PNLinea3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				
				JLabel LBProv = new JLabel("Provincia");
				PNLinea3.add(LBProv);
				
				JTextField TFProv = new JTextField();
				PNLinea3.add(TFProv);
				TFProv.setColumns(10);
				
				JLabel LBPob = new JLabel("Población");
				PNLinea3.add(LBPob);
				
				JTextField TFPob = new JTextField();
				PNLinea3.add(TFPob);
				TFPob.setColumns(10);
			
			// panel linea 4
			JPanel PNLinea4 = new JPanel();
			PNCentral.add(PNLinea4, "cell 0 3,grow");
			PNLinea4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			JLabel LBIdCli = new JLabel("Cliente");
			PNLinea4.add(LBIdCli);
			
			TFIdCli = new JTextField();
			TFIdCli.setColumns(10);
			PNLinea4.add(TFIdCli);
			
			JLabel LNNomCli = new JLabel("Nombre del cliente");
			PNLinea4.add(LNNomCli);
			
			// panel para los botones de la botonera
			JPanel panelBotonera = new JPanel();
			PNCentral.add(panelBotonera, "cell 0 5");
			
			JButton BTBorra = new JButton("Borrar");
			panelBotonera.add(BTBorra);
			BTBorra.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(
							frame, 
							"¿Seguro que quiere borrar el registro?", 
							"Borrar registro", 
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_NO_OPTION) {
						miCliDAO.borraCliente(TFDni.getText());	
					}

				}
			});
			
			JButton BTGuardar = new JButton("Guardar");
			panelBotonera.add(BTGuardar);
			BTGuardar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// crea el nuevo cliente
					Clientes cliente=new Clientes(TFDni.getText(),TFNombre.getText(),TFApellidos.getText(),TFDir.getText(),
							TFProv.getText(),TFPob.getText(),Date.valueOf(LocalDate.now()), TFIdCli.getText());
					// comprobar si ya existe el registro
					if (miCliDAO.ComprobarCliente(TFDni.getText())) {
						// guardar el registro
						miCliDAO.updateCliente(cliente);	
					} else {
						// insertar oel registro
						miCliDAO.addCliente(cliente);
					}

					
				}
			});
			
			JButton BTNuevo = new JButton("Nuevo");
			panelBotonera.add(BTNuevo);
			
			JButton BTSalir = new JButton("Salir");
			panelBotonera.add(BTSalir);
			
			// panel registros
			JPanel panelRegistros = new JPanel();
			panelRegistros.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
			PNCentral.add(panelRegistros, "cell 0 6");
			
			JButton BTPrimero = new JButton("<<");
			BTPrimero.setToolTipText("Primer registro.");
			BTPrimero.setForeground(Color.RED);
			BTPrimero.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTPrimero);
			
			JButton BTAnterior = new JButton("<");
			BTAnterior.setToolTipText("Registro anterior.");
			BTAnterior.setForeground(Color.RED);
			BTAnterior.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTAnterior);
			
			JLabel LBRegistros = new JLabel(" No se han encontrado registros.");
			LBRegistros.setBackground(Color.WHITE);
			LBRegistros.setBorder(new LineBorder(Color.BLUE, 1, true));
			
			panelRegistros.add(LBRegistros);
			
			JButton BTSiguiente = new JButton(">");
			BTSiguiente.setToolTipText("Registro siguiente.");
			BTSiguiente.setForeground(Color.RED);
			BTSiguiente.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTSiguiente);
			
			JButton BTultimo = new JButton(">>");
			BTultimo.setToolTipText("\u00DAltimo registro.");
			BTultimo.setForeground(Color.RED);
			BTultimo.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTultimo);
			
			// Panel para los botones del control de registros
			JPanel panelBotoneras = new JPanel();
			panelBotoneras.setMaximumSize(new Dimension(1000, 60));
			panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
			PNCentral.add(panelBotoneras, "cell 0 4");
			Border bordeRegistros = BorderFactory.createLineBorder(Color.BLUE,1);
	}

	/*
	 * Get Frame
	 */
	public Window getFrame() {
		return frame;
	}

}
