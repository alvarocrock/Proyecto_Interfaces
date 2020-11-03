package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import Models.Usuarios;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JSplitPane;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;

public class MenuVentasView {

	private JFrame frame;
	private Usuarios usuario;

	/**
	 * Create the application.
	 */
	public MenuVentasView(Usuarios miuser) {
		usuario=miuser;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame principal
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[434px]", "[35px][226px]"));
		
		// panel título
		JPanel PNTitulo = new JPanel();
		PNTitulo.setForeground(Color.BLUE);
		PNTitulo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PNTitulo.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(PNTitulo, "cell 0 0,growx,aligny top");
		PNTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel LBTitulo = new JLabel("Menú de ventas");
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
		LBNomUsu.setText(usuario.getNick());
		
		// panel central
		JPanel PNCentral = new JPanel();
		splitPane.setRightComponent(PNCentral);
		PNCentral.setLayout(new MigLayout("", "[][center][center][center]", "[][][][][]"));
		
		JButton BTProVen = new JButton("Propuesta de ventas");
		PNCentral.add(BTProVen, "cell 3 1,alignx right,aligny top");
		
		JButton BTBusCli = new JButton("Buscar cliente");
		PNCentral.add(BTBusCli, "cell 3 2");
		
		JButton BTFicCli = new JButton("Ficha de Clientes");
		PNCentral.add(BTFicCli, "cell 3 3");
		
		JButton BTConsVeh = new JButton("Consulta de vehículos");
		PNCentral.add(BTConsVeh, "cell 3 4");
	}

	/*
	 * Get Frame
	 */
	public Window getFrame() {
		return frame;
	}

}
