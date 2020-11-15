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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
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
import DAO.VehiculosDAO;
import GUI.FichaVehiculoView.MyKeyListener;
import Models.Clientes;
import Models.Concesionario;
import Models.Usuarios;
import Models.Vehiculos;

public class FichaConce extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuarios usuario;
	private ConcesionarioDAO miConceDAO;
	private JLabel LBUsuario;
	private JLabel LBNomUsu;
	private JLabel LBRegistros;
	private JFrame frame;
	private JTextField TFIdConce;
	private JTextField TFNombre;
	private JButton BTBuscar;
	private JButton BTBorra;
	private JButton BTGuardar;
	private JButton BTNuevo;
	private JButton BTSalir;
	private JButton BTPrimero;
	private JButton BTAnterior;
	private JButton BTSiguiente;
	private JButton BTultimo;
	private JLabel LBNomCli;


	/**
	 * Constructor con usuario e id de concesionario
	 * @param miuser
	 * @param idConce
	 */
	public FichaConce(Usuarios miuser, int idConce) {
		usuario=miuser;
		miConceDAO = new ConcesionarioDAO();
		initialize();
		// carga usuario
		LBUsuario.setText(usuario.getNick());
		LBNomUsu.setText(usuario.getNick());
		if (idConce==0) {
			// carga primer registro
			cargaConce(miConceDAO.primero());
		} else {
			// carga registro del usuario solicitado
			cargaConce(miConceDAO.goToIdConce(idConce));
		}
		refrescaReg();
	}

	/**
	 * Refresca el label de control de registros
	 */
	private void refrescaReg() {
		String p="Registro " + String.valueOf(miConceDAO.goToConce(Integer.parseInt(TFIdConce.getText())) + " de "+ 
				String.valueOf(miConceDAO.count())+".");
		LBRegistros.setText(p);	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame principal
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
		
		JLabel LBTitulo = new JLabel("Ficha de vehículos");
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
			
				TFIdConce = new JTextField();
				TFIdConce.setEnabled(false);
				TFIdConce.setEditable(false);
				PNLinea1.add(TFIdConce);
				TFIdConce.setColumns(10);
			
			// panel linea 2
			JPanel PNLinea2 = new JPanel();
			PNCentral.add(PNLinea2, "cell 0 1,grow");
			PNLinea2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			PNLinea2.setBackground(Color.decode("#2A9D8F"));
				
				JLabel LBNombre = new JLabel("Nombre");
				PNLinea2.add(LBNombre);
				
					TFNombre = new JTextField();
					PNLinea2.add(TFNombre);
					TFNombre.setColumns(50);
			
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
					ConsVeh miBusqueda = new ConsVeh(usuario);
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
						miConceDAO.borraConce(Integer.parseInt(TFIdConce.getText()));	
						Concesionario miConce = miConceDAO.primero();
						// cargar cliente en form
						cargaConce(miConce);
						refrescaReg();
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
						Concesionario miConce=new Concesionario(TFNombre.getText(),0);
								
						// comprobar si ya existe el registro
						if (miConceDAO.comprobarConce(Integer.parseInt(TFIdConce.getText()))) {
							// guardar el registro
							miConceDAO.updateConce(miConce);	
						} else {
							// insertar el registro
							miConceDAO.addConce(miConce);
						}
						daBotones(true);
						refrescaReg();
					} 
				}

			});
			
			BTNuevo = new JButton("Nuevo");
			panelBotonera.add(BTNuevo);
			BTNuevo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					TFNombre.setText("");
					TFNombre.requestFocus();
					daBotones(false);
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
			
			JPanel panelRegistros = new JPanel();
			panelBotoneras.add(panelRegistros);
			panelRegistros.setBackground(new Color(42, 157, 143));
			panelRegistros.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
			
			BTPrimero = new JButton("<<");
			BTPrimero.setToolTipText("Primer registro.");
			BTPrimero.setForeground(Color.RED);
			BTPrimero.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTPrimero);
			BTPrimero.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Concesionario miveh = miConceDAO.primero();
					// cargar vehiculo en form
					cargaConce(miveh);
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
					Concesionario miConce = miConceDAO.anterior(TFIdConce.getText());
					if (miConce!=null) {
						cargaConce(miConce);
						refrescaReg();
					}
				}
			});
			
			LBRegistros = new JLabel(" No se han encontrado registros.");
			LBRegistros.setBorder(new LineBorder(Color.BLUE, 1, true));
			LBRegistros.setBackground(Color.WHITE);
			panelRegistros.add(LBRegistros);
			
			BTSiguiente = new JButton(">");
			BTSiguiente.setToolTipText("Registro siguiente.");
			BTSiguiente.setForeground(Color.RED);
			BTSiguiente.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTSiguiente);
			BTSiguiente.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Concesionario miConce = miConceDAO.siguiente(TFIdConce.getText());
					// cargar cliente en form
					if (miConce!=null) {
						cargaConce(miConce);
						refrescaReg();
					}
				}
			});
			
			BTultimo = new JButton(">>");
			BTultimo.setToolTipText("Último registro.");
			BTultimo.setForeground(Color.RED);
			BTultimo.setFont(new Font("Tahoma", Font.BOLD, 8));
			panelRegistros.add(BTultimo);
			BTultimo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Concesionario miConce = miConceDAO.ultimo();
					// cargar cliente en form
					cargaConce(miConce);
					refrescaReg();
				}
			});
	}
	
	/**
	 * Comprueba si los datos son correctos
	 * @return
	 */
	private boolean comprobardatos() {
		if (TFNombre.getText().length()==0)  
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
		MenuVentasView miMenuVentas = new MenuVentasView(usuario);
		miMenuVentas.getFrame().setAlwaysOnTop(true);
		miMenuVentas.getFrame().setVisible(true);;		
	}

	/**
	 * Activa/desactiva botones
	 * @param estado
	 */
	protected void daBotones(boolean estado) {
		BTBorra.setEnabled(estado);
		BTAnterior.setEnabled(estado);
		BTNuevo.setEnabled(estado);
		BTPrimero.setEnabled(estado);
		BTSiguiente.setEnabled(estado);
		BTultimo.setEnabled(estado);
		BTBuscar.setEnabled(estado);
	}

	/**
	 * Carga el formulario con los datos de un cliente 
	 * @param miCliente
	 */
	protected void cargaConce(Concesionario miConce) {
		TFIdConce.setText(String.valueOf(miConce.getId()));
		TFNombre.setText(miConce.getNombre());
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
	
	//**************************** fin
}


