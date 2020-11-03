package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextField;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;

public class RegistroVehiculosView {

	private JFrame frame;
	private JTextField JTF_matricula;
	private JTextField JTF_bastidor;
	private JTextField JTF_modelo;
	private JTextField JTF_precio;
	private JTextField textField;
	private JTextField JTF_IdCli;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroVehiculosView window = new RegistroVehiculosView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistroVehiculosView() {
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
		
		JLabel lblNewLabel_1 = new JLabel("Usuario actual");
		panel_1.add(lblNewLabel_1);
		
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
		
		JLabel JLB_fecha = new JLabel("Fecha_alta");
		panel_2.add(JLB_fecha, "cell 0 5");
		
		textField = new JTextField();
		panel_2.add(textField, "cell 2 5,alignx left");
		textField.setColumns(10);
		
		JLabel JLB_id_cli = new JLabel("DNI_cli");
		panel_2.add(JLB_id_cli, "cell 0 6");
		
		JTF_IdCli = new JTextField();
		panel_2.add(JTF_IdCli, "cell 2 6,alignx left");
		JTF_IdCli.setColumns(10);
		
		JLabel JLB_conce = new JLabel("Concesionario");
		panel_2.add(JLB_conce, "cell 0 7");
		
		JComboBox combo_conce = new JComboBox();
		panel_2.add(combo_conce, "cell 2 7,growx");
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, "cell 0 9 6 1,alignx right,aligny center");
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton BTN_cancelar = new JButton("Cancelar");
		panel_3.add(BTN_cancelar);
		
		JButton BTN_aceptar = new JButton("Aceptar");
		panel_3.add(BTN_aceptar);
	}

}
