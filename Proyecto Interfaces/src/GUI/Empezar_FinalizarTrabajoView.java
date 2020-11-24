package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import DAO.TrabajoHoyDAO;
import GUI.BuscarTrabajoView.MyKeyListener;
import Models.Reparacion;
import Models.Usuarios;

public class Empezar_FinalizarTrabajoView {

	private JFrame frame;
	private static final long serialVersionUID = 1L;
	private Usuarios usuario;
	private JLabel LBUsuario;
	private TrabajoHoyDAO contro;
	private DefaultTableModel modeloTBCli;
	private JPanel PNMedio;
	private JPanel PNImg;
	private JPanel PNTitUsu;
	private JLabel LBImg;
	private TableRowSorter<TableModel> modeloOrdenado;
	private JLabel LBNomUsu;
	private JPanel Paneldatos;
	private JPanel panel_1;
	private JPanel PNBTN_acept;
	private JPanel PNLinea1;
	private JPanel PNLinea2;
	private JPanel PNLinea3;
	private JPanel panelsalr;
	private JButton JBTN_salir;
	private JButton BTNEmpezar;
	private JLabel JLB_cab_cliente;
	private JPanel panel;
	private JPanel Panelbton2;
	private JButton BTN_finish;
	private JLabel JLB_babecera_estado;
	private JLabel JLB_estado;
	private JLabel JLB_cliente;
	private JLabel JLB_cab_mat;
	private JLabel JLB_mat;
	private JLabel JLB_desc_mat;
	private JLabel JLB_desc;
	private Reparacion rep;
	private JLabel JLB_llamamineto;
	/**
	 * Create the application.
	 */
	public Empezar_FinalizarTrabajoView(Usuarios miuser,int index) {
		usuario=miuser;
		contro= new TrabajoHoyDAO();
		initialize();
		cargarconfig(index);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame principal
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 650, 500);
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
		
		JLabel LBTitulo = new JLabel("Gestionde trabajo");
		LBTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LBTitulo.setForeground(Color.ORANGE);
		PNTitulo.add(LBTitulo);
		
		// Panel medio
		PNMedio = new JPanel();
		frame.getContentPane().add(PNMedio);
		PNMedio.setLayout(new BoxLayout(PNMedio, BoxLayout.X_AXIS));
		PNMedio.setBackground(Color.decode("#2A9D8F"));
		
		// Panel Usuario
		JPanel PNUsuario = new JPanel();
		PNMedio.add(PNUsuario);
		PNUsuario.setLayout(new BoxLayout(PNUsuario,BoxLayout.Y_AXIS));
		PNUsuario.setBackground(Color.decode("#2A9D8F"));
		
		// panel titulo usuario
		PNTitUsu = new JPanel();
		PNTitUsu.setBackground(Color.decode("#2A9D8F"));
		PNUsuario.add(PNTitUsu);
		PNTitUsu.setLayout(new BoxLayout(PNTitUsu, BoxLayout.X_AXIS));
		
		LBUsuario = new JLabel("Usuario Actual");
		LBUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PNTitUsu.add(LBUsuario);
		LBUsuario.setForeground(Color.ORANGE);
		
		// panel imagen
		PNImg = new JPanel();
		PNImg.setBackground(Color.decode("#2A9D8F"));
		PNUsuario.add(PNImg);
		 PNImg.setLayout(new BoxLayout(PNImg, BoxLayout.Y_AXIS));
		// carga imagen
         try {
			 BufferedImage img = ImageIO.read(new File(usuario.getFoto()));
			 ImageIcon icon = new ImageIcon(img);
			 LBImg = new JLabel(icon);
			 PNUsuario.add(LBImg);
          } catch (IOException e) {
             e.printStackTrace();
          }
		
		LBNomUsu = new JLabel("Nombre Usuario");
		PNUsuario.add(LBNomUsu);
        LBNomUsu.setAlignmentX(0.5f);            
        LBNomUsu.setText(usuario.getNick());
//****************
		// Menú lateral
		JPanel PNMenu = new JPanel();
		PNMenu.setBackground(Color.decode("#2A9D8F"));
		PNUsuario.add(PNMenu);
		PNMenu.setLayout(new BoxLayout(PNMenu, BoxLayout.Y_AXIS));
		
        
		
		
		JLabel JLB_cerrar_sesion = new JLabel("Cerrar sesi\u00F3n");
		JLB_cerrar_sesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				LoginView miLogin = new LoginView();
				miLogin.getFrame().setAlwaysOnTop(true);
				miLogin.getFrame().setVisible(true);
			}
			@Override
			public void mouseEntered (MouseEvent e) {
				JLB_cerrar_sesion.setForeground(Color.RED);
			}
			@Override
			public void mouseExited (MouseEvent e) {
				JLB_cerrar_sesion.setForeground(Color.ORANGE);
			}
		});
		JLB_cerrar_sesion.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLB_cerrar_sesion.setForeground(Color.decode("#E76F51"));
		PNMenu.add(JLB_cerrar_sesion);

//***************		
		
		// panel central
		JPanel PNCentral = new JPanel();
		PNMedio.add(PNCentral);
		PNCentral.setBackground(Color.decode("#2A9D8F"));
		PNCentral.setLayout(new BoxLayout(PNCentral, BoxLayout.Y_AXIS));
		
		Paneldatos = new JPanel();
		PNCentral.add(Paneldatos);
		Paneldatos.setLayout(new BoxLayout(Paneldatos, BoxLayout.Y_AXIS));
		
		PNLinea1 = new JPanel();
		Paneldatos.add(PNLinea1);
		
		JLB_cab_cliente = new JLabel("Cliente: ");
		PNLinea1.add(JLB_cab_cliente);
		
		JLB_cliente = new JLabel("");
		PNLinea1.add(JLB_cliente);
		
		PNLinea2 = new JPanel();
		Paneldatos.add(PNLinea2);
		
		JLB_cab_mat = new JLabel("Matricula:");
		PNLinea2.add(JLB_cab_mat);
		
		JLB_mat = new JLabel("");
		PNLinea2.add(JLB_mat);
		
		PNLinea3 = new JPanel();
		Paneldatos.add(PNLinea3);
		PNLinea3.setLayout(new BoxLayout(PNLinea3, BoxLayout.Y_AXIS));
		
		JLB_desc_mat = new JLabel("Descripcion Trabajo");
		PNLinea3.add(JLB_desc_mat);
		
		JLB_desc = new JLabel("");
		PNLinea3.add(JLB_desc);
		
		panel_1 = new JPanel();
		PNCentral.add(panel_1);
		
		PNBTN_acept = new JPanel();
		panel_1.add(PNBTN_acept);
		
		BTNEmpezar = new JButton("Start");
		BTNEmpezar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contro.setFini(rep.getId());
				cargarconfig(rep.getId());
			}
		});
		BTNEmpezar.setPreferredSize(new Dimension(100,50));
		PNBTN_acept.add(BTNEmpezar);
		
		panel = new JPanel();
		panel_1.add(panel);
		
		JLB_babecera_estado = new JLabel("Estado :");
		panel.add(JLB_babecera_estado);
		
		JLB_estado = new JLabel("");
		panel.add(JLB_estado);
		
		Panelbton2 = new JPanel();
		panel_1.add(Panelbton2);
		
		BTN_finish = new JButton("Finish");
		BTN_finish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contro.setFFN(rep.getId());
				cargarconfig(rep.getId());
				calctiempo();
				cargarconfig(rep.getId());
			}
		});
		BTN_finish.setPreferredSize(new Dimension(100,50));
		Panelbton2.add(BTN_finish);
		
		panelsalr = new JPanel();
		PNCentral.add(panelsalr);
		
		JBTN_salir = new JButton("Exit");
		JBTN_salir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuMecanicoView view = new MenuMecanicoView(usuario);
				view.getFrame().setVisible(true);
				view.getFrame().setAlwaysOnTop(true);
				frame.dispose();
			}
		});
		JBTN_salir.setPreferredSize(new Dimension(100,50));
		panelsalr.add(JBTN_salir);
		
		JLB_llamamineto = new JLabel("");
		panelsalr.add(JLB_llamamineto);
		
	}
	
	private void calctiempo() {
		int resultado=0;
		if (!rep.getFecha_ini().equals(rep.getHoraFN())) {
			String [] lista1= rep.getFecha_ini().split("-");
			String year1=lista1[0];
			String mes1=lista1[1];
			String dia1=lista1[2];
			
			String [] lista2 = rep.getFecha_fn().split("-");
			String year2=lista2[0];
			String mes2=lista2[1];
			String dia2=lista2[2];
			
			//calculamos a�os en horas
			resultado= ((Integer.valueOf(year2)-Integer.valueOf(year1))*365)*24;
			//calculamos meses
			resultado=resultado + (((Integer.valueOf(mes2)-Integer.valueOf(mes1))*30)*24);
			//calculamos dias
			resultado= resultado + ((Integer.valueOf(dia2)-Integer.valueOf(dia1))*24);
		}
			//calculamos horas
			resultado = resultado + (Integer.valueOf(rep.getHora_ini())-Integer.valueOf(rep.getHoraFN()));
			
			contro.setTfinal(resultado, rep.getId());
		
	}
	
	private void cargarconfig(int id) {
		rep= contro.gotoTrabajo(id);
		JLB_cliente.setText(contro.getnombre(rep.getId_cli())+" "+contro.getapellido(rep.getId_cli()));
		JLB_mat.setText(contro.getmatricula(rep.getId_mec()));
		JLB_desc.setText(rep.getDesc());
		if (rep.getFecha_ini().equals("0000-00-00")) {
			JLB_estado.setText("No empezado");
		} else {
			JLB_estado.setText("En proceso");
			BTNEmpezar.setVisible(false);
		}
		
		if (!rep.getFecha_fn().equals("0000-00-00")) {
			JLB_estado.setText("Finalizado");
			BTN_finish.setVisible(false);
			JLB_llamamineto.setText("Envie un carta a la direccion "+contro.getdir(rep.getId_cli())+" para avisar al cliente");
		}
		
		
	}
	
	protected void salir() {
			frame.dispose();
			MenuMecanicoView miMenuVentas = new MenuMecanicoView(usuario);
			miMenuVentas.getFrame().setAlwaysOnTop(true);
			miMenuVentas.getFrame().setVisible(true);
				
	}
	
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
	
	public Window getFrame() {
		return frame;
	}

}
