package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import DAO.ClientesDAO;
import DAO.ConcesionarioDAO;
import DAO.TrabajoHoyDAO;
import GUI.FichaConce.MyKeyListener;
import Models.Concesionario;
import Models.Reparacion;
import Models.Usuarios;
import javax.swing.JTextArea;

public class FichaTrabajo {

	private static final long serialVersionUID = 1L;
	private Usuarios usuario;
	private TrabajoHoyDAO contro;
	private JLabel LBUsuario;
	private JLabel LBNomUsu;
	private JFrame frame;
	private JTextField JTFId;
	private JTextField JTFNombreCli;
	private JButton BTBuscar;
	private JButton BTBorra;
	private JButton BTGuardar;
	private JButton BTSalir;
	private JLabel LBNomCli;
	private JTextField JTF_Jefe;
	private JTextField JTF_mec;
	private JTextField JTF_desc;
	private JTextField JTF_matricula;
	private JTextField JTF_precio;
	/**
	 * Create the application.
	 */
	public FichaTrabajo(Usuarios miuser,int id) {
		usuario=miuser;
		contro = new TrabajoHoyDAO();
		initialize();
		// carga usuario
		LBUsuario.setText(usuario.getNick());
		LBNomUsu.setText(usuario.getNick());
		if (id==0) {
			// carga primer registro
			cargatrabajo(contro.first());
		} else {
			// carga registro del usuario solicitado
			cargatrabajo(contro.gotoTrabajo(id));
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 900, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		
		// implementar las teclas en el frame
		KeyListener listener = new MyKeyListener();
		frame.addKeyListener(listener);
		frame.setFocusable(true);
		frame.requestFocus();
		
		// panel título
		JPanel PNTitulo = new JPanel();
		PNTitulo.setForeground(Color.BLUE);
		PNTitulo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PNTitulo.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(PNTitulo, "cell 0 0,growx,aligny top");
		PNTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		PNTitulo.setBackground(Color.decode("#264653"));
		
		JLabel LBTitulo = new JLabel("Ficha de trabajos");
		LBTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LBTitulo.setForeground(Color.ORANGE);
		PNTitulo.add(LBTitulo);
		
		// Panel medio
		JPanel PNMedio = new JPanel();
		frame.getContentPane().add(PNMedio);
		PNMedio.setLayout(new BoxLayout(PNMedio, BoxLayout.X_AXIS));
		
		// Panel Usuario
		JPanel PNUsuario = new JPanel();
		PNMedio.add(PNUsuario);
		PNUsuario.setLayout(new BoxLayout(PNUsuario,BoxLayout.Y_AXIS));
		PNUsuario.setBackground(Color.decode("#2A9D8F"));
		
		// panel titulo usuario
		JPanel PNTitUsu = new JPanel();
		PNTitUsu.setBackground(Color.decode("#2A9D8F"));
		PNUsuario.add(PNTitUsu);
		PNTitUsu.setLayout(new BoxLayout(PNTitUsu, BoxLayout.X_AXIS));
		
		LBUsuario = new JLabel("Usuario Actual");
		LBUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PNTitUsu.add(LBUsuario);
		LBUsuario.setForeground(Color.ORANGE);
		
		// panel imagen
		JPanel PNImg = new JPanel();
		PNImg.setBackground(Color.decode("#2A9D8F"));
		PNUsuario.add(PNImg);
		 PNImg.setLayout(new BoxLayout(PNImg, BoxLayout.Y_AXIS));
		// carga imagen
         try {
			 BufferedImage img = ImageIO.read(new File(usuario.getFoto()));
			 ImageIcon icon = new ImageIcon(img);
			 JLabel LBImg = new JLabel(icon);
			 PNUsuario.add(LBImg);
          } catch (IOException e) {
             e.printStackTrace();
          }
		
		LBNomUsu = new JLabel("Nombre de Usuario");
		PNUsuario.add(LBNomUsu);
        LBNomUsu.setAlignmentX(0.5f);            
        LBNomUsu.setText(usuario.getNick());
		
		// panel nombre de usuario
		JPanel PNMenu = new JPanel();
		PNMenu.setBackground(new Color(42, 157, 143));
		PNUsuario.add(PNMenu);

//***************		
		
		// panel central
		JPanel PNCentral = new JPanel();
		PNMedio.add(PNCentral);
		PNCentral.setBackground(Color.decode("#2A9D8F"));
		PNCentral.setLayout(new BoxLayout(PNCentral, BoxLayout.Y_AXIS));
		
			// panel linea 1
			JPanel PNLinea1 = new JPanel();
			PNCentral.add(PNLinea1, "cell 0 0,grow");
			PNLinea1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNLinea1.setBackground(Color.decode("#2A9D8F"));
			
			JLabel LBIdConce = new JLabel("Identificador");
			PNLinea1.add(LBIdConce);
			
				JTFId = new JTextField();
				JTFId.setEnabled(false);
				JTFId.setEditable(false);
				PNLinea1.add(JTFId);
				JTFId.setColumns(10);
			
			// panel linea 2
			JPanel PNLinea2 = new JPanel();
			PNCentral.add(PNLinea2, "cell 0 1,grow");
			PNLinea2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNLinea2.setBackground(Color.decode("#2A9D8F"));
				
				JLabel LBNombreCli = new JLabel("Nombre");
				PNLinea2.add(LBNombreCli);
				
					JTFNombreCli = new JTextField();
					PNLinea2.add(JTFNombreCli);
					JTFNombreCli.setColumns(50);
					
					JPanel PNLinea3 = new JPanel();
					PNCentral.add(PNLinea3);
					PNLinea3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					PNLinea3.setBackground(Color.decode("#2A9D8F"));
					
					JLabel lblNewLabel = new JLabel("Jefe");
					PNLinea3.add(lblNewLabel);
					
					JTF_Jefe = new JTextField();
					PNLinea3.add(JTF_Jefe);
					JTF_Jefe.setColumns(10);
					
					JPanel PNLinea4 = new JPanel();
					PNCentral.add(PNLinea4);
					PNLinea4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					PNLinea4.setBackground(Color.decode("#2A9D8F"));
					
					JLabel lblNewLabel_1 = new JLabel("Mecanico");
					PNLinea4.add(lblNewLabel_1);
					
					JTF_mec = new JTextField();
					PNLinea4.add(JTF_mec);
					JTF_mec.setColumns(10);
					
					JPanel PNLinea5 = new JPanel();
					PNCentral.add(PNLinea5);
					PNLinea5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					PNLinea5.setBackground(Color.decode("#2A9D8F"));
					
					JLabel JLB_desc = new JLabel("Descripcion");
					PNLinea5.add(JLB_desc);
					
					JTF_desc = new JTextField();
					PNLinea5.add(JTF_desc);
					JTF_desc.setColumns(60);
					
					JPanel PNLinea6 = new JPanel();
					PNCentral.add(PNLinea6);
					PNLinea6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					
					JLabel JLB_matricula = new JLabel("Vehiculo");
					PNLinea6.add(JLB_matricula);
					PNLinea6.setBackground(Color.decode("#2A9D8F"));
					
					JTF_matricula = new JTextField();
					PNLinea6.add(JTF_matricula);
					JTF_matricula.setColumns(10);
					
					JPanel PNlinea7 = new JPanel();
					PNCentral.add(PNlinea7);
					PNlinea7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					
					JLabel lblNewLabel_2 = new JLabel("Precio");
					PNlinea7.add(lblNewLabel_2);
					PNlinea7.setBackground(Color.decode("#2A9D8F"));
					
					JTF_precio = new JTextField();
					PNlinea7.add(JTF_precio);
					JTF_precio.setColumns(10);
					
			
			// Panel para los botones del control de registros
			JPanel panelBotoneras = new JPanel();
			panelBotoneras.setMaximumSize(new Dimension(1000, 60));
			panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
			PNCentral.add(panelBotoneras, "cell 0 4");
			
			JPanel panelBotonera = new JPanel();
			panelBotonera.setBackground(new Color(42, 157, 143));
			panelBotoneras.add(panelBotonera);
			
			BTBuscar = new JButton("Buscar");
			panelBotonera.add(BTBuscar);
			BTBuscar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// llamada a buscar cliente
					frame.dispose();
					BuscarTrabajoView miBusqueda = new BuscarTrabajoView(usuario);
					miBusqueda.getFrame().setAlwaysOnTop(true);
					miBusqueda.getFrame().setVisible(true);

				}
			});
			
			BTBorra = new JButton("Borrar");
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
						contro.delete(Integer.parseInt(JTFId.getText()));	
						Reparacion rep = contro.first();
						// cargar cliente en form
						cargatrabajo(rep);
					}

				}
			});
			
			BTGuardar = new JButton("Guardar");
			panelBotonera.add(BTGuardar);
			BTGuardar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (comprobardatos()) {
						// crea el nuevo concesionario
						Reparacion rep=contro.gotoTrabajo(Integer.parseInt(JTFId.getText()));
								
						// comprobar si ya existe el registro
						if (contro.comprobartrabajo(Integer.parseInt(JTFId.getText()))) {
							// guardar el registro
							contro.update(rep);	
						} 
						daBotones(true);
					} 
				}

			});
			
			BTSalir = new JButton("Salir");
			panelBotonera.add(BTSalir);
			BTSalir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					salir();
				}
			});
	}
	
	
	
	/**
	 * Comprueba si los datos son correctos
	 * @return
	 */
	private boolean comprobardatos() {
		if (JTFNombreCli.getText().length()==0)  
		{
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Sale de la vista
	 */
	protected void salir() {
		frame.dispose();
		MenuJTallerView miMenuVentas = new MenuJTallerView(usuario);
		miMenuVentas.getFrame().setAlwaysOnTop(true);
		miMenuVentas.getFrame().setVisible(true);;		
	}

	/**
	 * Activa/desactiva botones
	 * @param estado
	 */
	protected void daBotones(boolean estado) {
		BTBorra.setEnabled(estado);
		BTBuscar.setEnabled(estado);
	}

	/**
	 * Carga el formulario con los datos de un cliente 
	 * @param miCliente
	 */
	protected void cargatrabajo(Reparacion rep) {
		JTFId.setText(String.valueOf(rep.getId()));
		JTFNombreCli.setText(contro.getnombre(rep.getId_cli())+" "+contro.getapellido(rep.getId_cli()));
		JTF_desc.setText(rep.getDesc());
		JTF_Jefe.setText(contro.getnick(rep.getId_jefe()));
		JTF_mec.setText(contro.getnick(rep.getId_mec()));
		JTF_precio.setText(String.valueOf(rep.getPrecio()));
		JTF_matricula.setText(contro.getmatricula(rep.getId_veh()));
	}

	/*
	 * Get Frame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/*
	 * Implementa keyEvents
	 */
	public class MyKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent arg0) {
			int ascii = (int) arg0.getKeyChar();
			System.out.println(ascii);
			switch (arg0.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					salir();
					break;	
			}
			
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
		}
	
		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}
	
	

}
