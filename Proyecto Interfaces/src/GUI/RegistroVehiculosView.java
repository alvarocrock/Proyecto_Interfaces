package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.JSplitPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import DAO.VehiculosDAO;
import Models.Concesionario;
import Models.Usuarios;

import javax.swing.JTextField;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistroVehiculosView {

	private JFrame frame;
	VehiculosDAO contro;
	private JTextField JTF_matricula;
	private JTextField JTF_bastidor;
	private JTextField JTF_modelo;
	private JTextField JTF_precio;
	private JTextField JTF_IdCli;
	private JComboBox combo_conce;
	private Usuarios miuser;
	private JTextField JTF_tipo;
	private final JLabel JLB_tipo = new JLabel("tipo vehiculo");

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroVehiculosView window = new RegistroVehiculosView(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 */
	public RegistroVehiculosView(Usuarios user) {
		miuser=user;
		contro= new VehiculosDAO();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Registro vehiculos");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel Usuario_actual = new JLabel("Usuario actual");
		panel_1.add(Usuario_actual);
		
		JLabel user = new JLabel("");
		panel_1.add(user);
		if (miuser!=null) {
		user.setText(miuser.getNick());
		}
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new MigLayout("", "[grow][][grow][grow][][]", "[][][][][][][][][][grow]"));
		
		JLabel JLB_matricula = new JLabel("Matricula");
		panel_2.add(JLB_matricula, "cell 0 1");
		
		JTF_matricula = new JTextField();
		panel_2.add(JTF_matricula, "cell 2 1,alignx left");
		JTF_matricula.setColumns(10);
		
		JLabel JLB_BASTIDOR = new JLabel("Bastidor");
		panel_2.add(JLB_BASTIDOR, "cell 0 2");
		
		JTF_bastidor = new JTextField();
		panel_2.add(JTF_bastidor, "cell 2 2,alignx left");
		JTF_bastidor.setColumns(10);
		
		JLabel JLB_modelo = new JLabel("Modelo");
		panel_2.add(JLB_modelo, "cell 0 3");
		
		JTF_modelo = new JTextField();
		panel_2.add(JTF_modelo, "cell 2 3,alignx left");
		JTF_modelo.setColumns(10);
		
		JLabel JLB_precio = new JLabel("Precio");
		panel_2.add(JLB_precio, "cell 0 4");
		
		JTF_precio = new JTextField();
		panel_2.add(JTF_precio, "cell 2 4,alignx left");
		JTF_precio.setColumns(10);
		
		JLabel JLB_id_cli = new JLabel("DNI_cli");
		panel_2.add(JLB_id_cli, "cell 0 5");
		
		JTF_IdCli = new JTextField();
		panel_2.add(JTF_IdCli, "cell 2 5,alignx left");
		JTF_IdCli.setColumns(10);
		
		JLabel JLB_conce = new JLabel("Concesionario");
		panel_2.add(JLB_conce, "cell 0 6");
		
		
		combo_conce = new JComboBox();
		panel_2.add(combo_conce, "cell 2 6,growx");
		rellenarcombo();
		panel_2.add(JLB_tipo, "cell 0 7");
		
		JTF_tipo = new JTextField();
		panel_2.add(JTF_tipo, "cell 2 7,alignx left");
		JTF_tipo.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, "cell 0 9 6 1,alignx right,aligny center");
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton BTN_cancelar = new JButton("Cancelar");
		BTN_cancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuVentasView miMenuV = new MenuVentasView(miuser);
				miMenuV.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		panel_3.add(BTN_cancelar);
		
		JButton BTN_aceptar = new JButton("Aceptar");
		BTN_aceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				add();
			}
		});
		panel_3.add(BTN_aceptar);
	}
	
	public void add() {
		String matricula=JTF_matricula.getText();
		String bastidor=JTF_bastidor.getText();
		String marca=JTF_matricula.getText();
		String modelo=JTF_modelo.getText();
		float precio=Float.parseFloat(JTF_precio.getText());
		int id_cli = contro.getidcli(JTF_IdCli.getText());
		int id_user = miuser.getId();
		int id_conce = contro.getidconce(combo_conce.getSelectedItem().toString());
		String tipo= JLB_tipo.getText(); 
		if (id_cli!=0) {
		contro.crearregistro(matricula, bastidor, marca, modelo, precio, id_cli, id_user, id_conce, tipo);
		}else if (matricula.equals("")|| bastidor.equals("")||marca.equals("")||modelo.equals("")||tipo.equals("")) {
			JOptionPane.showMessageDialog(null, "Porfavor rellene todas las casillas", "Message", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Porfavor inserte un dni que exista en la bbdd", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void rellenarcombo() {
		ArrayList<Concesionario> miarray=contro.arrayconce();
		for (int cont=0;cont<miarray.size();cont++) {
			combo_conce.addItem(miarray.get(cont).getNombre());
		}
		
	}

	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}

}
