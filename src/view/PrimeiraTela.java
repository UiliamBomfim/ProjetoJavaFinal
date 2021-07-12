package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.company.Comparacao.APP;
import com.company.Map.CorRGB;
import com.company.Persistencia.CorDAOSQL;
import com.company.Persistencia.ImagemDAOFile;


public class PrimeiraTela {

	private JFrame frame;
	private JTextField textField;
	private JTextField txtInformeCaminho;
	private JTextField txtSelecioneOCaminho;
	String selecionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (InstantiationException ex) {
        	System.err.println(ex);
        } catch (IllegalAccessException ex) {
        	System.err.println(ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        	System.err.println(ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrimeiraTela window = new PrimeiraTela();
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
	public PrimeiraTela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Analisar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "" + APP.percentualCores(selecionado));
			}
		});
		btnNewButton.setBounds(254, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(154, 63, 165, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtInformeCaminho = new JTextField();
		txtInformeCaminho.setText("Selecione arquivo");
		txtInformeCaminho.setBounds(10, 63, 118, 29);
		frame.getContentPane().add(txtInformeCaminho);
		txtInformeCaminho.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		
		
		CorDAOSQL dao = new CorDAOSQL();
		
		try {
			for(String p: dao.findAllSimbols() )
			{
				comboBox.addItem(p);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 selecionado = comboBox.getSelectedItem().toString();
				 System.out.println(selecionado);
			}
		});
		comboBox.setBounds(209, 141, 215, 30);
		frame.getContentPane().add(comboBox);
		
		
	
		
		txtSelecioneOCaminho = new JTextField();
		txtSelecioneOCaminho.setText("Selecione o tipo de elemento");
		txtSelecioneOCaminho.setColumns(10);
		txtSelecioneOCaminho.setBounds(10, 142, 181, 29);
		frame.getContentPane().add(txtSelecioneOCaminho);
		
		JButton Browser = new JButton("Browser");
		Browser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Procurar arquivos");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY );
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagem", "bin", "BIN" );
				chooser.setFileFilter(filter); 
				int retorno = chooser.showOpenDialog(chooser);
				
				if( retorno == JFileChooser.APPROVE_OPTION) 
				{
					File file= chooser.getSelectedFile();
					textField.setText(file.getPath());
					ImagemDAOFile.setCONTAS_FILE_NAME(file.getPath());
				
				}
			}
		});
		Browser.setBounds(345, 63, 79, 29);
		frame.getContentPane().add(Browser);
	}
}