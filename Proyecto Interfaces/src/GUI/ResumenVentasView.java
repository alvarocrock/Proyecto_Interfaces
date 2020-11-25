package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;

import Models.Usuarios;
import Models.Ventas;
import Models.vXeData;
import Models.vXmData;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import DAO.ClientesDAO;
import DAO.UsuarioDAO;
import DAO.VehiculosDAO;
import DAO.VentasDAO;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JComboBox;

public class ResumenVentasView extends JFrame{

	/**
	 * serial version id
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private Usuarios miuser;
	private VentasDAO contro;
	private JTable TBCli;
	private JScrollPane spGrafico;
	private DefaultTableModel modeloTBCli;
	private TableRowSorter<TableModel> modeloOrdenado;
	private JTextField textField_1;
	private JLabel LBRegistros;
	/**
	 * Create the application.
	 */
	public ResumenVentasView(Usuarios user) {
		contro= new VentasDAO();
		miuser=user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200,1200 );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// implementar las teclas en el frame
		KeyListener listener = new MyKeyListener();
		frame.addKeyListener(listener);
		frame.setFocusable(true);
		frame.requestFocus();
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setBackground(Color.decode("#264653"));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_titulo = new JPanel();
		panel_titulo.setBackground(Color.decode("#264653"));
		panel.add(panel_titulo);
		
		JLabel JLB_titulo = new JLabel("Resumen de ventas");
		JLB_titulo.setForeground(Color.ORANGE);
		JLB_titulo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_titulo.add(JLB_titulo);
		
		JPanel panel_principal = new JPanel();
		panel_principal.setBackground(Color.decode("#2A9D8F"));
		panel.add(panel_principal);
		
		
		
		JPanel panel_izquierdo = new JPanel();
		panel_izquierdo.setBackground(Color.decode("#2A9D8F"));
		panel_principal.add(panel_izquierdo);
		panel_izquierdo.setLayout(new BoxLayout(panel_izquierdo, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Usuario actual");
		panel_izquierdo.add(lblNewLabel);
		
		
		ImageIcon imagenuser = new ImageIcon(miuser.getFoto());
		JLabel JLB_image_user = new JLabel(imagenuser);
		panel_izquierdo.add(JLB_image_user);
		
		JLabel JLB_USER_actual = new JLabel("ACTUAL");
		panel_izquierdo.add(JLB_USER_actual);
		JLB_USER_actual.setText(miuser.getNick());
		
		
		JLabel JLB_buscar_cli = new JLabel("Buscar Clientes");
		JLB_buscar_cli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BusCliView miBuscCli = new BusCliView(frame, miuser);
				miBuscCli.getFrame().setAlwaysOnTop(true);
				miBuscCli.getFrame().setVisible(true);
				frame.dispose();
			}
			@Override
			public void mouseEntered (MouseEvent e) {
				JLB_buscar_cli.setForeground(Color.RED);
			}
			@Override
			public void mouseExited (MouseEvent e) {
				JLB_buscar_cli.setForeground(Color.ORANGE);
			}
		});
		JLB_buscar_cli.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLB_buscar_cli.setForeground(Color.decode("#E9C46A"));
		panel_izquierdo.add(JLB_buscar_cli);
		
		JLabel JLB_ficha_cliente = new JLabel("Ficha Cliente");
		JLB_ficha_cliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FichaClienteView miFichaClientes = new FichaClienteView(miuser,0);
				miFichaClientes.getFrame().setAlwaysOnTop(true);
				miFichaClientes.getFrame().setVisible(true);
				frame.dispose();
			}
			@Override
			public void mouseEntered (MouseEvent e) {
				JLB_ficha_cliente.setForeground(Color.RED);
			}
			@Override
			public void mouseExited (MouseEvent e) {
				JLB_ficha_cliente.setForeground(Color.ORANGE);
			}
		});
		JLB_ficha_cliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLB_ficha_cliente.setForeground(Color.decode("#E9C46A"));
		panel_izquierdo.add(JLB_ficha_cliente);
		
		JLabel Busca_vehiculos = new JLabel("Busca Vehiculos");
		Busca_vehiculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ConsVeh busqueda= new ConsVeh(frame, miuser);
				busqueda.getFrame().setAlwaysOnTop(true);
				busqueda.getFrame().setVisible(true);
				frame.dispose();
			}
			@Override
			public void mouseEntered (MouseEvent e) {
				Busca_vehiculos.setForeground(Color.RED);
			}
			@Override
			public void mouseExited (MouseEvent e) {
				Busca_vehiculos.setForeground(Color.ORANGE);
			}
		});
		Busca_vehiculos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Busca_vehiculos.setForeground(Color.decode("#E9C46A"));
		panel_izquierdo.add(Busca_vehiculos);
		
		JLabel JLB_ficha_vehiculo = new JLabel("Ficha Vehiculo");
		JLB_ficha_vehiculo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FichaVehiculoView miFicVeh = new FichaVehiculoView (miuser,0);
				miFicVeh.getFrame().setVisible(true);
				miFicVeh.getFrame().setAlwaysOnTop(true);
				frame.dispose();
			}
			@Override
			public void mouseEntered (MouseEvent e) {
				JLB_ficha_vehiculo.setForeground(Color.RED);
			}
			@Override
			public void mouseExited (MouseEvent e) {
				JLB_ficha_vehiculo.setForeground(Color.ORANGE);
			}
		});
		JLB_ficha_vehiculo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		JLB_ficha_vehiculo.setForeground(Color.decode("#E9C46A"));
		panel_izquierdo.add(JLB_ficha_vehiculo);
		
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
		panel_izquierdo.add(JLB_cerrar_sesion);
		
		
		JPanel panel_derecho = new JPanel();
		panel_principal.add(panel_derecho);
		panel_derecho.setBackground(Color.decode("#2A9D8F"));
		panel_derecho.setLayout(new BoxLayout(panel_derecho, BoxLayout.X_AXIS));
		
		JPanel panel_cont_p_d = new JPanel();
		panel_derecho.add(panel_cont_p_d);
		panel_cont_p_d.setBackground(Color.decode("#2A9D8F"));
		panel_cont_p_d.setLayout(new BoxLayout(panel_cont_p_d, BoxLayout.Y_AXIS));
		
		// panel de busqueda
		
		JPanel panel_busqueda = new JPanel();
		panel_cont_p_d.add(panel_busqueda);
		panel_busqueda.setBackground(Color.decode("#2A9D8F"));
		panel_busqueda.setLayout(new BoxLayout(panel_busqueda, BoxLayout.Y_AXIS));
		
		//panel del gráfico
		JPanel pnGrafico = new JPanel();
		panel_cont_p_d.add(pnGrafico);
		pnGrafico.setLayout(new BoxLayout(pnGrafico, BoxLayout.Y_AXIS));
		
		//------------------------------------------------------
		// Fuente de Datos
        DefaultPieDataset dataC = new DefaultPieDataset();
        VentasDAO miVenDAO = new VentasDAO();
        UsuarioDAO miUsuDAO = new UsuarioDAO ();
        ArrayList <vXmData> miArray = miVenDAO.VentaXMes();
        for (int i=0 ; i<miArray.size();i++) {
        	dataC.setValue(miArray.get(i).getMes()+" - "+String.valueOf(miArray.get(i).getVenta()),miArray.get(i).getVenta());
        }

        
        DefaultCategoryDataset dataB = new DefaultCategoryDataset();
        ArrayList<Integer> ArrayUsuInt = miVenDAO.getUsuVentas();
        ArrayList<Usuarios> ArrayUsu = miUsuDAO.getUsuVentas(ArrayUsuInt);
        ArrayList <vXeData> miArrayB = miVenDAO.VentaXEmp(ArrayUsu);
        for (int i=0 ; i<miArrayB.size();i++) {
        	dataB.setValue(miArrayB.get(i).getVenta(),miArrayB.get(i).getyAxis(),miArrayB.get(i).getVendedor());
        }

 
        // Creando el Grafico
        JFreeChart grafCircular = ChartFactory.createPieChart("Ventas por meses", dataC,true, true, false);
        JFreeChart grafBarras = ChartFactory.createBarChart3D("Ventas por empleado", "Empleados", "Ventas", dataB
        		,PlotOrientation.VERTICAL,true,true,false);
        
        // Mostrar Grafico
        pnGrafico.add(new ChartPanel (grafCircular));
        pnGrafico.add(new ChartPanel(grafBarras));
        
        // -----------------------------------------------------
		//panel de botonera
		JPanel panel_botonera = new JPanel();
		panel_botonera.setBackground(Color.decode("#2A9D8F"));
		panel_cont_p_d.add(panel_botonera);
		
		JButton JBT_seleccionar = new JButton("Seleccionar");
		panel_botonera.add(JBT_seleccionar);
		
		JButton JBT_salir = new JButton("Salir");
		panel_botonera.add(JBT_salir);
		JBT_salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		
		//panel de registros
		
		JPanel panel_registros = new JPanel();
		panel_registros.setBackground(Color.decode("#2A9D8F"));
		panel_cont_p_d.add(panel_registros);
		
		JButton BTPrimero = new JButton("<<");
		BTPrimero.setToolTipText("Primer registro.");
		BTPrimero.setForeground(Color.RED);
		BTPrimero.setFont(new Font("Tahoma", Font.BOLD, 8));
		panel_registros.add(BTPrimero);
		BTPrimero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TBCli.setRowSelectionInterval(0, 0);
				refrescaReg();
			}
		});
		
		JButton BTAnterior = new JButton("<");
		BTAnterior.setToolTipText("Registro anterior.");
		BTAnterior.setForeground(Color.RED);
		BTAnterior.setFont(new Font("Tahoma", Font.BOLD, 8));
		panel_registros.add(BTAnterior);
		BTAnterior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (TBCli.getSelectedRow()>0) 
					TBCli.setRowSelectionInterval(TBCli.getSelectedRow()-1,TBCli.getSelectedRow()-1);
					refrescaReg();
			}
		});
		
		LBRegistros = new JLabel(" No se han encontrado registros.");
		LBRegistros.setBackground(Color.WHITE);
		LBRegistros.setBorder(new LineBorder(Color.BLUE, 1, true));
		
		panel_registros.add(LBRegistros);
		
		JButton BTSiguiente = new JButton(">");
		BTSiguiente.setToolTipText("Registro siguiente.");
		BTSiguiente.setForeground(Color.RED);
		BTSiguiente.setFont(new Font("Tahoma", Font.BOLD, 8));
		panel_registros.add(BTSiguiente);
		BTSiguiente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (TBCli.getSelectedRow()<TBCli.getRowCount()) 
					TBCli.setRowSelectionInterval(TBCli.getSelectedRow()+1,TBCli.getSelectedRow()+1);
				refrescaReg();
			}
		});
		
		JButton BTultimo = new JButton(">>");
		BTultimo.setToolTipText("\u00DAltimo registro.");
		BTultimo.setForeground(Color.RED);
		BTultimo.setFont(new Font("Tahoma", Font.BOLD, 8));
		panel_registros.add(BTultimo);
		BTultimo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TBCli.setRowSelectionInterval(TBCli.getRowCount()-1,TBCli.getRowCount()-1);
				refrescaReg();
			}
		});
		
		// Panel para los botones del control de registros
		JPanel panelBotoneras = new JPanel();
		panelBotoneras.setMaximumSize(new Dimension(1000, 60));
		panelBotoneras.setLayout(new BoxLayout(panelBotoneras, BoxLayout.Y_AXIS));
		panelBotoneras.setBackground(Color.decode("#2A9D8F"));
		
		frame.pack();

	}
		

	public void cargarVentas(ArrayList<Ventas> lista) {
		Object [] fila = new Object[8];
			
		for (int i=0;i<lista.size();i++) {
			fila[0]=(int) lista.get(i).getId_ventas();
			fila[1]=contro.getnick(lista.get(i).getId_emple());
			fila[2]=contro.getnombrecli(lista.get(i).getId_cli());
			fila[3]=contro.getapellidocli(lista.get(i).getId_cli());
			fila[4]=lista.get(i).getFechappto();
			fila[5]=lista.get(i).getFechavalidez();
			fila[6]=contro.getmatricula(lista.get(i).getId_vehiculo());
			fila[7]=lista.get(i).getPrecio();
			modeloTBCli.addRow(fila);
		}
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	protected void seleccionar() {
		//implementar navegacion a la ficha
	}
	
	/**
	 * Refresca el label de control de registros
	 */
	private void refrescaReg() {
		String p="Registro " + TBCli.getSelectedRow()+1 + " de "+ TBCli.getRowCount()+".";
		LBRegistros.setText(p);	
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

	/*
	 * salir de la vista
	 */
	public void salir() {
		frame.dispose();
		MenuVentasView miMenuVentas = new MenuVentasView(miuser);
		miMenuVentas.getFrame().setAlwaysOnTop(true);
		miMenuVentas.getFrame().setVisible(true);
	}
	
//************************************************************* fin

}
