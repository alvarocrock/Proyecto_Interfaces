package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MenuMecanicoView {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public MenuMecanicoView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame principal
		frame = new JFrame();
		frame.setBounds(100, 100, 463, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[434px]", "[35px][226px]"));
		
		// panel título
		JPanel PNTitulo = new JPanel();
		PNTitulo.setForeground(Color.BLUE);
		PNTitulo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PNTitulo.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(PNTitulo, "cell 0 0,growx,aligny top");
		PNTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel LBTitulo = new JLabel("Menú de taller");
		LBTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LBTitulo.setForeground(Color.BLUE);
		PNTitulo.add(LBTitulo);
		
		// Split Panel
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, "cell 0 1,grow");
		
		// Panel Usuario
		JPanel PNUsuario = new JPanel();
		splitPane.setLeftComponent(PNUsuario);
		PNUsuario.setLayout(new MigLayout("", "[91px]", "[14px][14px][][][][]"));
		
		
		JLabel LBUsuario = new JLabel("Usuario Actual");
		LBUsuario.setForeground(Color.BLUE);
		PNUsuario.add(LBUsuario, "cell 0 0,alignx left,aligny center");
		
		JLabel LBNomUsu = new JLabel("Nombre de Usuario");
		PNUsuario.add(LBNomUsu, "cell 0 1,alignx left,aligny center");
		
		// panel central
		JPanel PNCentral = new JPanel();
		splitPane.setRightComponent(PNCentral);
		PNCentral.setLayout(new MigLayout("", "[][][center][center][center]", "[][][]"));
		
		JButton BTProVen = new JButton("Ficha clientes");
		BTProVen.setIcon(new ImageIcon(MenuMecanicoView.class.getResource("/png/Clientes.png")));
		PNCentral.add(BTProVen, "flowx,cell 0 1,alignx right,aligny top");
		
		JButton BTBusCli = new JButton("Ver trabajo");
		BTBusCli.setIcon(new ImageIcon(MenuMecanicoView.class.getResource("/png/Trabajo.png")));
		PNCentral.add(BTBusCli, "cell 2 1");
		
		JButton BTSalir = new JButton("Salir");
		PNCentral.add(BTSalir, "cell 0 2");
		BTSalir.setSelectedIcon(new ImageIcon(MenuMecanicoView.class.getResource("/png/Salida.png")));
		BTSalir.setIcon(new ImageIcon(MenuMecanicoView.class.getResource("/png/Salida.png")));
	}
	
	/*
	 * Get Frame
	 */
	public Window getFrame() {
		return frame;
	}
}


