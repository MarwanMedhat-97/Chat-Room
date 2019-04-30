import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Register {

	public JFrame frmRegisteration;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frmRegisteration.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
		conn = new DBHandler().getConn();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegisteration = new JFrame();
		frmRegisteration.getContentPane().setBackground(Color.DARK_GRAY);
		frmRegisteration.setTitle("Registeration");
		frmRegisteration.setBounds(100, 100, 497, 509);
		frmRegisteration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
					
				if(textField.getText().equals("") ||textField_1.getText().equals("")||textField_2.getText().equals("") ||textField_3.getText().equals("") ||textField_4.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Fill all Fields to complete Registeration");
						
				}
				else {
					try {
						String query = "insert into user(username,password,Nickname,Gender,Age) values(\""+textField.getText()+
								"\",\""+textField_1.getText()+"\",\""+textField_2.getText()+"\",\""+textField_3.getText()+"\",33)";
					//System.out.println(query); //for debugging
							ps = conn.prepareStatement(query);
							int done =ps.executeUpdate();
							
							if(done==1)
							{
								 JOptionPane.showMessageDialog(null, "Registered Succesfully");
								 Client cl = new Client(textField.getText());
								 cl.frmClient.setVisible(true);
								 frmRegisteration.dispose();
								 
							}else{
				                JOptionPane.showMessageDialog(null, "Registeration Failed");
				            }
						} catch (SQLException e) {
							// TODO Auto-generated catch block
					//		e.printStackTrace();
						}
				}
						
			}
		});
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setForeground(Color.YELLOW);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.YELLOW);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setForeground(Color.YELLOW);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.YELLOW);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setForeground(Color.YELLOW);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmRegisteration.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(159)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAge)
								.addComponent(lblNewLabel)
								.addComponent(lblPassword)
								.addComponent(lblNickname)
								.addComponent(lblGender))
							.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(178, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(91)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNickname)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGender)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAge)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(58))
		);
		frmRegisteration.getContentPane().setLayout(groupLayout);
	}
}
